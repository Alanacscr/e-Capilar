package unitins.br.tp1.dto.Endereco;

import unitins.br.tp1.model.Endereco.Endereco;

public record EnderecoResponseDTO(
        Long id,
        String logradouro,
        Integer numero,
        String bairro,
        String cep,
        String complemento,
        MunicipioResponseDTO municipio) {

    public static EnderecoResponseDTO valueOf(Endereco endereco) {
        if (endereco == null)
            return null;
        return new EnderecoResponseDTO(
                endereco.getId(),
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getBairro(),
                endereco.getCep(),
                endereco.getComplemento(),
                MunicipioResponseDTO.valueOf(endereco.getMunicipio()));
    }
}
