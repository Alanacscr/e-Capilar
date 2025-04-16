package unitins.br.tp1.dto;

public record UsuarioDTO (
    String nome,
    String email,
    String senha, 
    Integer idPerfil,
    Long idTelefone) {

}
