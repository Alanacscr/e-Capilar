package unitins.br.tp1.service.Pedido;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import unitins.br.tp1.dto.Pagamento.BoletoDTO;
import unitins.br.tp1.dto.Pagamento.PixDTO;
import unitins.br.tp1.dto.Pedido.ItemPedidoDTO;
import unitins.br.tp1.dto.Pedido.PedidoDTO;
import unitins.br.tp1.dto.Pedido.PedidoResponseDTO;
import unitins.br.tp1.model.Pagamento.Boleto;
import unitins.br.tp1.model.Pagamento.Pagamento;
import unitins.br.tp1.model.Pagamento.Pix;
import unitins.br.tp1.model.Pedido.ItemPedido;
import unitins.br.tp1.model.Pedido.Pedido;
import unitins.br.tp1.model.Produto.Produto;
import unitins.br.tp1.model.Usuario.Usuario;
import unitins.br.tp1.repository.Pedido.PedidoRepository;
import unitins.br.tp1.repository.Produto.ProdutoRepository;
import unitins.br.tp1.repository.Usuario.UsuarioRepository;

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

        // Verificando o tipo de pagamento e instanciando a classe correta
        if (pedidoDTO.pagamento() != null) {
            Pagamento pagamento = null;

            // Verificando se o pagamento é do tipo BoletoDTO
            if (pedidoDTO.pagamento() instanceof BoletoDTO boletoDTO) {
                pagamento = new Boleto(); // Instanciando um pagamento Boleto

                // Se a data de vencimento for fornecida no DTO, usamos ela
                if (boletoDTO.dataVencimento() != null) {
                    ((Boleto) pagamento).setDataVencimento(boletoDTO.dataVencimento());
                }

                // Definindo o código de barras
                if (boletoDTO.codigoBarras() != null) {
                    ((Boleto) pagamento).setCodigoBarras(boletoDTO.codigoBarras());
                }
            }
            // Verificando se o pagamento é do tipo PixDTO
            else if (pedidoDTO.pagamento() instanceof PixDTO pixDTO) {
                pagamento = new Pix(); // Instanciando um pagamento Pix

                // Se a chave Pix for fornecida no DTO, usamos ela
                if (pixDTO.chavePix() != null) {
                    ((Pix) pagamento).setChavePix(pixDTO.chavePix());
                }
            }

            if (pagamento != null) {
                // Configurando os atributos comuns do pagamento
                pagamento.setValor(pedido.getTotalPedido());
                pagamento.setTipoPagamento(pedidoDTO.pagamento() instanceof BoletoDTO ? "Boleto" : "Pix");

                // Associando o pagamento ao pedido
                pedido.setPagamento(pagamento);
            }
        }

        pedidoRepository.persist(pedido);

        return PedidoResponseDTO.valueOf(pedido);
    }

    @Override
@Transactional
public PedidoResponseDTO adicionarPagamentoPix(Long idPedido, PixDTO pixDTO) {
    // Buscar o pedido pelo ID
    Pedido pedido = pedidoRepository.findById(idPedido);

    if (pedido == null) {
        throw new IllegalArgumentException("Pedido não encontrado.");
    }

    // Criando o pagamento Pix
    Pix pagamentoPix = new Pix(); // Instanciando um pagamento Pix
    pagamentoPix.setValor(pedido.getTotalPedido());
    pagamentoPix.setTipoPagamento("Pix");
    pagamentoPix.setChavePix(pixDTO.chavePix()); // Definindo chavePix

    // Associando o pagamento ao pedido
    pedido.setPagamento(pagamentoPix);

    // Atualizando o pedido no banco de dados
    pedidoRepository.getEntityManager().merge(pedido);

    return PedidoResponseDTO.valueOf(pedido);
}


    @Override
    @Transactional
    public PedidoResponseDTO adicionarPagamentoBoleto(Long idPedido, BoletoDTO boletoDTO) {
        // Buscar o pedido pelo ID
        Pedido pedido = pedidoRepository.findById(idPedido);

        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não encontrado.");
        }

        // Criando o pagamento Boleto
        Boleto pagamentoBoleto = new Boleto(); // Instanciando um pagamento Boleto
        pagamentoBoleto.setValor(pedido.getTotalPedido());
        pagamentoBoleto.setTipoPagamento("Boleto");
        pagamentoBoleto.setDataVencimento(boletoDTO.dataVencimento()); // Definindo data de vencimento
        pagamentoBoleto.setCodigoBarras(boletoDTO.codigoBarras()); // Definindo código de barras

        // Associando o pagamento ao pedido
        pedido.setPagamento(pagamentoBoleto);

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
}
