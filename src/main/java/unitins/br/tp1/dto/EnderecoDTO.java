package unitins.br.tp1.dto;

public record EnderecoDTO(
        String logradouro,
        Integer numero,
        String bairro,
        String cep,
        String complemento,
        Long idMunicipio) {

}
