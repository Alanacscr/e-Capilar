package unitins.br.tp1.dto.Pagamento;

public record PagamentoDTO(
    Double valor,
    String tipoPagamento // Ex: "Boleto", "Cartão de Crédito", etc.
) { }
