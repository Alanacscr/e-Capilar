package unitins.br.tp1.dto;

import java.time.LocalDateTime;

public record PagamentoDTO(
    String tipoPagamento, // Ex: "Boleto", "Cartão de Crédito", etc.
    LocalDateTime dataVencimento
) { }
