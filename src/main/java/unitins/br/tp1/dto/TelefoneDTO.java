package unitins.br.tp1.dto;

import jakarta.validation.constraints.NotBlank;

public record TelefoneDTO(
        @NotBlank(message = "O código de área deve ser informado.")
        String codigoArea,
        @NotBlank(message = "O número deve ser informado.")
        String numero) {

}
