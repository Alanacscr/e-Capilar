package unitins.br.tp1.dto.Endereco;

import unitins.br.tp1.model.Endereco.Municipio;

public record MunicipioResponseDTO(
        Long id,
        String nome,
        EstadoResponseDTO estado) {

    public static MunicipioResponseDTO valueOf(Municipio municipio) {
        if (municipio == null)
            return null;
        return new MunicipioResponseDTO(municipio.getId(), municipio.getNome(),
        EstadoResponseDTO.valueOf(municipio.getEstado()
        ));
    }
}
