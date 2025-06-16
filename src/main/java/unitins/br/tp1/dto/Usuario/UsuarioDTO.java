package unitins.br.tp1.dto.Usuario;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO (

    @NotBlank(message = "O nome deve ser informado.")
    String nome,
    @NotBlank(message = "O email deve ser informado.")
    @Email
    String email,
    @NotBlank(message = "A senha deve ser informada.")
    String senha, 
    @Min(value = 1, message = "O valor mínimo para o perfil é 1.")
    @JsonIgnore
    Integer idPerfil,
    @Min(value = 1, message = "O valor mínimo para o telefone é 1.")
    Long idTelefone,
    @Min(value = 1, message = "O valor mínimo para o endereco é 1.")
    Long idEndereco) {

}
