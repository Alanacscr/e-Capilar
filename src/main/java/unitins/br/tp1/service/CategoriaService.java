package unitins.br.tp1.service;

import java.util.List;

import unitins.br.tp1.dto.CategoriaDTO;
import unitins.br.tp1.dto.CategoriaResponseDTO;

public interface CategoriaService {

    CategoriaResponseDTO create(CategoriaDTO Categoria);
    void update(long id, CategoriaDTO Categoria);
    void delete(long id);
    CategoriaResponseDTO findById(long id);
    List<CategoriaResponseDTO> findByProduto(Long idProduto);
    List<CategoriaResponseDTO> findByNome(String nome);
    List<CategoriaResponseDTO> findAll();
    
}