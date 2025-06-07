package unitins.br.tp1.dto.Produto;

import unitins.br.tp1.model.Produto.Categoria;

public record CategoriaResponseDTO(
    Long id,
    String nome,
    ProdutoResponseDTO produto) {

    public static CategoriaResponseDTO valueOf(Categoria categoria) {
        if (categoria == null)
            return null;
        return new CategoriaResponseDTO(
            categoria.getId(), 
            categoria.getNome(), 
            ProdutoResponseDTO.valueOf(categoria.getProduto())
        );
    }
   
}