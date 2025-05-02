package unitins.br.tp1.dto;

import jakarta.validation.constraints.NotBlank;

public record MunicipioDTO(
        @NotBlank(message = "O municipio deve ser informado.")
        String nome,
        Long idEstado) {
}
