package unitins.br.tp1.dto.Pedido;

import unitins.br.tp1.dto.Produto.ProdutoResponseDTO;
import unitins.br.tp1.model.Pedido.ItemPedido;

public record ItemPedidoResponseDTO(
    Long id,
    ProdutoResponseDTO produto,
    Integer qtd,
    Double preco) {

     public static ItemPedidoResponseDTO valueOf(ItemPedido itempedido) {
         if (itempedido == null)
             return null;
         return new ItemPedidoResponseDTO(itempedido.getId(), 
         ProdutoResponseDTO.valueOf(itempedido.getProduto()),
         itempedido.getQuantidade(), itempedido.getPreco());
     }
    
}
