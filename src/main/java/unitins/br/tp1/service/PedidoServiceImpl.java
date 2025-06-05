package unitins.br.tp1.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import unitins.br.tp1.dto.ItemPedidoDTO;
import unitins.br.tp1.dto.PedidoDTO;
import unitins.br.tp1.dto.PedidoResponseDTO;
import unitins.br.tp1.model.ItemPedido;
import unitins.br.tp1.model.Pedido;
import unitins.br.tp1.model.Produto;
import unitins.br.tp1.model.Usuario;
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
        // Buscar todos os pedidos do usuário
        List<Pedido> pedidos = pedidoRepository.findByUsuario(email);

        // Converter os pedidos para PedidoResponseDTO
        List<PedidoResponseDTO> pedidosDTO = pedidos.stream()
                .map(PedidoResponseDTO::valueOf)
                .collect(Collectors.toList());

        return pedidosDTO;
    }

    @Override
    public PedidoResponseDTO findById(long idPedido, String email) {
        // Buscar o usuário pelo email
        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario == null) {
            // Se o usuário não for encontrado, lançar uma exceção ou retornar um erro
            throw new IllegalArgumentException("Usuário não encontrado com o email: " + email);
        }

        // Buscar o pedido pelo ID
        Pedido pedido = pedidoRepository.findById(idPedido);

        if (pedido == null) {
            // Se o pedido não for encontrado, lançar uma exceção ou retornar um erro
            throw new IllegalArgumentException("Pedido não encontrado com o id: " + idPedido);
        }

        // Verificar se o pedido pertence ao usuário
        if (!pedido.getUsuario().equals(usuario)) {
            throw new IllegalArgumentException(
                    "O pedido com o id " + idPedido + " não pertence ao usuário com o email: " + email);
        }

        // Converter o pedido para PedidoResponseDTO
        return PedidoResponseDTO.valueOf(pedido);
    }

    @Override
    @Transactional
    public PedidoResponseDTO create(PedidoDTO pedidoDTO, String email) {
        // Obter o usuário pelo id
        Usuario usuario = usuarioRepository.findById(pedidoDTO.idUsuario());

        // Criar o pedido
        Pedido pedido = new Pedido();
        pedido.setDataHora(LocalDateTime.now());

        double totalCalculado = 0.0;

        List<ItemPedido> listaItem = new ArrayList<>();
        for (ItemPedidoDTO itemDTO : pedidoDTO.itens()) {
            Produto produto = produtoRepository.findById(itemDTO.idProduto());

            // Criar o item do pedido
            ItemPedido item = new ItemPedido();
            item.setPedido(pedido);
            item.setProduto(produto);
            item.setPreco(produto.getPreco());
            item.setQuantidade(itemDTO.qtd());

            // Calcular o valor total do item (preço * quantidade)
            totalCalculado += item.getPreco() * item.getQuantidade();

            // Atualizar o estoque do produto
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - itemDTO.qtd());

            listaItem.add(item);
        }

        // Verificar se o total calculado é igual ao total do DTO
        if (Math.abs(totalCalculado - pedidoDTO.total()) > 0.01) {
            throw new IllegalArgumentException("O total do pedido não corresponde ao valor calculado.");
        }

        // Definir o total do pedido
        pedido.setTotalPedido(totalCalculado);
        pedido.setUsuario(usuario);

        // Salvar os itens no pedido
        pedido.setItens(listaItem);

        pedidoRepository.persist(pedido);

        // Retornar o DTO de resposta
        return PedidoResponseDTO.valueOf(pedido);
    }

}
