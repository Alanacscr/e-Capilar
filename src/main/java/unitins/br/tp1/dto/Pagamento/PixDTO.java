package unitins.br.tp1.dto.Pagamento;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record PixDTO(
    @JsonIgnore
    String tipoPagamento,  
    String chavePix  
) { }