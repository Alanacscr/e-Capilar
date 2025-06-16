package unitins.br.tp1.dto.Pedido;

import java.time.LocalDateTime;
import java.util.List;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record PedidoDTO(
    LocalDateTime dataHora,
    Long idUsuario,
    List<ItemPedidoDTO> itens,
    @Schema(description = "Valor total do pedido", example = "100.00")
    Double total,
    @JsonIgnore
    Object pagamento,
    @JsonIgnore
    String statusPedido
) { }
