package unitins.br.tp1.dto.Produto;

import unitins.br.tp1.model.Produto.Cor;
import unitins.br.tp1.model.Produto.Produto;

public record ProdutoResponseDTO(
        Long id,
        String nome,
        String descricao,
        double preco,
        Integer quantidadeEstoque,
        Cor cor) {

    public static ProdutoResponseDTO valueOf(Produto produto) {
        if (produto == null)
            return null;
        return new ProdutoResponseDTO(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getPreco(),
                produto.getQuantidadeEstoque(), produto.getCor());
    }

}
