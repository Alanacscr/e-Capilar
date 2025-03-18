package unitins.br.tp1.service;

import java.util.List;

import unitins.br.tp1.dto.ProdutoDTO;
import unitins.br.tp1.dto.ProdutoResponseDTO;

public interface ProdutoService {

    ProdutoResponseDTO create(ProdutoDTO produto);
    void update(long id, ProdutoDTO produto);
    void delete(long id);
    ProdutoResponseDTO findById(long id);
    ProdutoResponseDTO findByNome(String nome);
    List<ProdutoResponseDTO> findAll();
    
}
