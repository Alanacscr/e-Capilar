package unitins.br.tp1.dto;

import unitins.br.tp1.model.Pagamento.Pagamento;

public record PagamentoResponseDTO(
    Long id,
    String tipoPagamento  // Ex: "Boleto", "Cartão de Crédito", etc.
) {

    public static PagamentoResponseDTO valueOf(Pagamento pagamento) {
        if (pagamento == null) {
            return null;
        }

        return new PagamentoResponseDTO(
                pagamento.getId(),
                pagamento.getTipoPagamento()  // Exemplo de campo, pode ser ajustado conforme a necessidade
        );
    }
}
