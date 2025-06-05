package unitins.br.tp1.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import unitins.br.tp1.model.Pedido;

public record PedidoResponseDTO(
        Long id,
        LocalDateTime dataHora,
        UsuarioResponseDTO usuario,
        List<ItemPedidoResponseDTO> itens,
        @Schema(description = "Valor total do pedido", example = "100.00")
        Double total) {

    public static PedidoResponseDTO valueOf(Pedido pedido) {
        if (pedido == null) {
            return null;
        }

        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getDataHora(),
                UsuarioResponseDTO.valueOf(pedido.getUsuario()),
                pedido.getItens().stream().map(i -> ItemPedidoResponseDTO.valueOf(i)).toList(),
                pedido.getTotalPedido() 
        );
    }

}