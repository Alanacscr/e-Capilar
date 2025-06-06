package unitins.br.tp1.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import unitins.br.tp1.dto.ItemPedidoDTO;
import unitins.br.tp1.dto.PagamentoDTO;
import unitins.br.tp1.dto.PedidoDTO;
import unitins.br.tp1.dto.PedidoResponseDTO;
import unitins.br.tp1.model.ItemPedido;
import unitins.br.tp1.model.Pedido;
import unitins.br.tp1.model.Produto;
import unitins.br.tp1.model.Usuario;
import unitins.br.tp1.model.Pagamento.Boleto;
import unitins.br.tp1.model.Pagamento.Pagamento;
import unitins.br.tp1.repository.PedidoRepository;
import unitins.br.tp1.repository.ProdutoRepository;
import unitins.br.tp1.repository.UsuarioRepository;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService {

    @Inject
    PedidoRepository pedidoRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    ProdutoRepository produtoRepository;

    @Override
    public List<PedidoResponseDTO> findByEmail(String email) {
        List<Pedido> pedidos = pedidoRepository.findByUsuario(email);
        List<PedidoResponseDTO> pedidosDTO = pedidos.stream()
                .map(PedidoResponseDTO::valueOf)
                .collect(Collectors.toList());
        return pedidosDTO;
    }

    @Override
    public PedidoResponseDTO findById(long idPedido, String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado com o email: " + email);
        }

        Pedido pedido = pedidoRepository.findById(idPedido);

        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não encontrado com o id: " + idPedido);
        }

        if (!pedido.getUsuario().equals(usuario)) {
            throw new IllegalArgumentException(
                    "O pedido com o id " + idPedido + " não pertence ao usuário com o email: " + email);
        }

        return PedidoResponseDTO.valueOf(pedido);
    }

    @Override
    @Transactional
    public PedidoResponseDTO create(PedidoDTO pedidoDTO, String email) {
        Usuario usuario = usuarioRepository.findById(pedidoDTO.idUsuario());
        Pedido pedido = new Pedido();
        pedido.setDataHora(LocalDateTime.now());

        double totalCalculado = 0.0;
        List<ItemPedido> listaItem = new ArrayList<>();

        for (ItemPedidoDTO itemDTO : pedidoDTO.itens()) {
            Produto produto = produtoRepository.findById(itemDTO.idProduto());

            ItemPedido item = new ItemPedido();
            item.setPedido(pedido);
            item.setProduto(produto);
            item.setPreco(produto.getPreco());
            item.setQuantidade(itemDTO.qtd());

            totalCalculado += item.getPreco() * item.getQuantidade();

            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - itemDTO.qtd());
            listaItem.add(item);
        }

        if (Math.abs(totalCalculado - pedidoDTO.total()) > 0.01) {
            throw new IllegalArgumentException("O total do pedido não corresponde ao valor calculado.");
        }

        pedido.setTotalPedido(totalCalculado);
        pedido.setUsuario(usuario);

        // Salvar os itens no pedido
        pedido.setItens(listaItem);

        // Adicionando o pagamento de forma genérica
        if (pedidoDTO.pagamento() != null) {
            Pagamento pagamento;

            // Verificando o tipo de pagamento e instanciando a classe correta
            if ("Boleto".equalsIgnoreCase(pedidoDTO.pagamento().tipoPagamento())) {
                pagamento = new Boleto(); // Instanciando um pagamento Boleto

                // Se a data de vencimento for fornecida no DTO, usamos ela
                if (pedidoDTO.pagamento().dataVencimento() != null) {
                    ((Boleto) pagamento).setDataVencimento(pedidoDTO.pagamento().dataVencimento());
                }
            } else {
                pagamento = null; // Pagamento genérico ou outro tipo
            }

            // Configurando os atributos do pagamento
            pagamento.setValor(pedido.getTotalPedido());
            pagamento.setTipoPagamento(pedidoDTO.pagamento().tipoPagamento());

            // Associando o pagamento ao pedido
            pedido.setPagamento(pagamento);
        }

        pedidoRepository.persist(pedido);

        return PedidoResponseDTO.valueOf(pedido);
    }

    @Override
    @Transactional
    public PedidoResponseDTO adicionarPagamento(Long idPedido, PagamentoDTO pagamentoDTO) {
        // Buscar o pedido pelo ID
        Pedido pedido = pedidoRepository.findById(idPedido);

        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não encontrado.");
        }

        // Criando o pagamento com base no tipo
        Pagamento pagamento;

        // Verificando o tipo de pagamento e instanciando a classe correta
        if ("Boleto".equalsIgnoreCase(pagamentoDTO.tipoPagamento())) {
            pagamento = new Boleto(); // Instanciando um pagamento Boleto
        } else {
            pagamento = null; // Pagamento genérico ou outro tipo
        }

        // Configurando os atributos do pagamento
        pagamento.setValor(pedido.getTotalPedido()); // O valor do pagamento é igual ao total do pedido
        pagamento.setTipoPagamento(pagamentoDTO.tipoPagamento());

        // Associando o pagamento ao pedido
        pedido.setPagamento(pagamento);

        // Atualizando o pedido no banco de dados
        pedidoRepository.getEntityManager().merge(pedido);

        return PedidoResponseDTO.valueOf(pedido);
    }

    @Override
    public PedidoResponseDTO buscarPedido(Long idPedido) {
        // Buscar o pedido pelo ID e retornar o DTO
        Pedido pedido = pedidoRepository.findById(idPedido);
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não encontrado.");
        }
        return PedidoResponseDTO.valueOf(pedido);
    }

    @Override
    @Transactional
    public PedidoResponseDTO atualizarPedido(PedidoDTO pedidoDTO) {
        // Buscar o pedido pelo ID
        Pedido pedido = pedidoRepository.findById(pedidoDTO.idUsuario());
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não encontrado.");
        }

        // Atualizando os itens, valores e o pagamento
        double totalCalculado = 0.0;
        List<ItemPedido> listaItem = new ArrayList<>();

        for (ItemPedidoDTO itemDTO : pedidoDTO.itens()) {
            Produto produto = produtoRepository.findById(itemDTO.idProduto());

            ItemPedido item = new ItemPedido();
            item.setPedido(pedido);
            item.setProduto(produto);
            item.setPreco(produto.getPreco());
            item.setQuantidade(itemDTO.qtd());

            totalCalculado += item.getPreco() * item.getQuantidade();
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - itemDTO.qtd());
            listaItem.add(item);
        }

        if (Math.abs(totalCalculado - pedidoDTO.total()) > 0.01) {
            throw new IllegalArgumentException("O total do pedido não corresponde ao valor calculado.");
        }

        pedido.setTotalPedido(totalCalculado);
        pedido.setItens(listaItem);

        // Atualizando o pagamento
        if (pedidoDTO.pagamento() != null) {
            Pagamento pagamento;
            if ("Boleto".equalsIgnoreCase(pedidoDTO.pagamento().tipoPagamento())) {
                pagamento = new Boleto();
            } else {
                pagamento = null;
            }
            pagamento.setValor(pedido.getTotalPedido());
            pagamento.setTipoPagamento(pedidoDTO.pagamento().tipoPagamento());
            pedido.setPagamento(pagamento);
        }

        pedidoRepository.getEntityManager().merge(pedido);

        return PedidoResponseDTO.valueOf(pedido);
    }
}
