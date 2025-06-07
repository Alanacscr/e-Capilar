package unitins.br.tp1.dto.Pagamento;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record BoletoDTO(
    @JsonIgnore
    String tipoPagamento, 
    LocalDateTime dataVencimento, 
    String codigoBarras  
) { }
