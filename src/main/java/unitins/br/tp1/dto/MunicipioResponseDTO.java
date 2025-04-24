package unitins.br.tp1.dto;

import unitins.br.tp1.model.Municipio;

public record MunicipioResponseDTO(
        Long id,
        String nome) {

    public static MunicipioResponseDTO valueOf(Municipio municipio) {
        if (municipio == null)
            return null;
        return new MunicipioResponseDTO(municipio.getId(), municipio.getNome());
    }
}
