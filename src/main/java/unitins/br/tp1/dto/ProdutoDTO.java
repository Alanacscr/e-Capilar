package unitins.br.tp1.dto;

public record ProdutoDTO(
    String nome,
    String descricao,
    double preco,
    Integer quantidadeEstoque,
    Integer idCor) {
    
}
