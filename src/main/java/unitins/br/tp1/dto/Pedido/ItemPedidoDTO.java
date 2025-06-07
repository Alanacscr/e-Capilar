package unitins.br.tp1.dto.Pedido;

public record ItemPedidoDTO(
    Long idProduto,
    Integer qtd,
    Double preco
) {
    
}
