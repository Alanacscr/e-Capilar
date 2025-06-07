package unitins.br.tp1.dto.Produto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CategoriaDTO(
    @NotBlank(message = "A categoria deve ser informada.")
    String nome,
    @Min(value = 1, message = "O valor mínimo para a produto é 1.")
    Long idProduto) {
    
}