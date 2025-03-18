package unitins.br.tp1.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import unitins.br.tp1.dto.ProdutoDTO;
import unitins.br.tp1.dto.ProdutoResponseDTO;
import unitins.br.tp1.model.Cor;
import unitins.br.tp1.model.Produto;
import unitins.br.tp1.repository.ProdutoRepository;

@ApplicationScoped
public class ProdutoServiceImpl implements ProdutoService {

    @Inject
    ProdutoRepository produtoRepository;

    @Override
    @Transactional
    public ProdutoResponseDTO create(ProdutoDTO produto) {
        Produto novoProduto = new Produto();
        novoProduto.setNome(produto.nome());
        novoProduto.setDescricao(produto.descricao());
        novoProduto.setPreco(produto.preco());
        novoProduto.setQuantidadeEstoque(produto.quantidadeEstoque());

        novoProduto.setCor(Cor.valueOf(produto.idCor()));

        // realizando inclusao
        produtoRepository.persist(novoProduto);

        return ProdutoResponseDTO.valueOf(novoProduto);
    }

    @Override
    @Transactional
    public void update(long id, ProdutoDTO produto) {
        Produto edicaoProduto = produtoRepository.findById(id);

        edicaoProduto.setNome(produto.nome());
        edicaoProduto.setDescricao(produto.descricao());
        edicaoProduto.setPreco(produto.preco());
        edicaoProduto.setQuantidadeEstoque(produto.quantidadeEstoque());
        edicaoProduto.setCor(Cor.valueOf(produto.idCor()));
    }

    @Override
    @Transactional
    public void delete(long id) {
        produtoRepository.deleteById(id);
    }

    @Override
    public ProdutoResponseDTO findById(long id) {
        return ProdutoResponseDTO.valueOf(produtoRepository.findById(id));
    }

    @Override
    public ProdutoResponseDTO findByNome(String nome) {
        return ProdutoResponseDTO.valueOf(produtoRepository.findByNome(nome));
    }

    @Override
    public List<ProdutoResponseDTO> findAll() {
        return produtoRepository.findAll().stream().map(e -> ProdutoResponseDTO.valueOf(e)).toList();
    }

}