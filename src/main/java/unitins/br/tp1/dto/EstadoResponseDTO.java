package unitins.br.tp1.dto;

import unitins.br.tp1.model.Estado;

public record EstadoResponseDTO(
    Long id,
    String sigla,
    String nome) {

    public static EstadoResponseDTO valueOf(Estado estado) {
        if (estado == null)
            return null;
        return new EstadoResponseDTO(estado.getId(), estado.getSigla(), estado.getNome());
    }

}
