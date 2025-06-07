package unitins.br.tp1.dto.Produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record ProdutoDTO(

    @NotBlank(message = "O nome do produto deve ser informado.")
    String nome,
    @NotBlank(message = "A  descrição deve ser informada.")
    String descricao,
    @NotBlank(message = "O preço deve ser informado.")
    double preco,
    @NotBlank(message = "A quantidade de estoque deve ser informada.")
    @PositiveOrZero(message = "A quantidade de estoque deve ser igual ou maior que validação.")
    Integer quantidadeEstoque,
    Integer idCor) {
    
}
