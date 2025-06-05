package unitins.br.tp1.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public record PedidoDTO(

    // pagamento
    LocalDateTime dataHora,
    Long idUsuario,
    List<ItemPedidoDTO> itens,
    @Schema(description = "Valor total do pedido", example = "100.00")
    Double total
    
) {
    
}
