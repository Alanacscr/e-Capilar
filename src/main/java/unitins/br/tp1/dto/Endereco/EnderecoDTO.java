package unitins.br.tp1.dto.Endereco;

import jakarta.validation.constraints.NotBlank;

public record EnderecoDTO(
        @NotBlank(message = "O logradouro deve ser informado.")
        String logradouro,
        @NotBlank(message = "O numero deve ser informado.")
        Integer numero,
        @NotBlank(message = "O bairro deve ser informado.")
        String bairro,
        @NotBlank(message = "O cep deve ser informado.")
        String cep,
        String complemento,
        Long idMunicipio) {

}
