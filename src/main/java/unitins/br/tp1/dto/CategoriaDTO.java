package unitins.br.tp1.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoriaDTO(
    @NotBlank(message = "A categoria deve ser informada.")
    String nome,
    Long idProduto) {
    
}