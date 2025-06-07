package unitins.br.tp1.service.Produto;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import unitins.br.tp1.dto.Produto.CategoriaDTO;
import unitins.br.tp1.dto.Produto.CategoriaResponseDTO;
import unitins.br.tp1.model.Produto.Categoria;
import unitins.br.tp1.model.Produto.Produto;
import unitins.br.tp1.repository.Produto.CategoriaRepository;
import unitins.br.tp1.repository.Produto.ProdutoRepository;

@ApplicationScoped
public class CategoriaServiceImpl implements CategoriaService {

    @Inject
    CategoriaRepository categoriaRepository;

    @Inject
    ProdutoRepository produtoRepository;

    @Override
    @Transactional
    public CategoriaResponseDTO create(CategoriaDTO dto) {
        Categoria novoCategoria = new Categoria();
        novoCategoria.setNome(dto.nome());

        // buscando o produto pelo id
        Produto produto = produtoRepository.findById(dto.idProduto());

        novoCategoria.setProduto(produto);

        // realizando inclusao
        categoriaRepository.persist(novoCategoria);

        return CategoriaResponseDTO.valueOf(novoCategoria);
    }

    @Override
    @Transactional
    public void update(long id, CategoriaDTO dto) {
        Categoria edicaoCategoria = categoriaRepository.findById(id);

        edicaoCategoria.setNome(dto.nome());
        // buscando o produto pelo id
        Produto produto = produtoRepository.findById(dto.idProduto());
        edicaoCategoria.setProduto(produto);
    }

    @Override
    @Transactional
    public void delete(long id) {
        categoriaRepository.deleteById(id);
    }

    @Override
    public CategoriaResponseDTO findById(long id) {
        return CategoriaResponseDTO.valueOf(categoriaRepository.findById(id));
    }

    @Override
    public List<CategoriaResponseDTO> findByProduto(Long idProduto) {
        return categoriaRepository.findByProduto(idProduto)
        .stream().map(e -> CategoriaResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<CategoriaResponseDTO> findByNome(String nome) {
        return categoriaRepository.findByNome(nome)
        .stream().map(e -> CategoriaResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<CategoriaResponseDTO> findAll() {
        return categoriaRepository.findAll().stream().map(e -> CategoriaResponseDTO.valueOf(e)).toList();
    }
    
}