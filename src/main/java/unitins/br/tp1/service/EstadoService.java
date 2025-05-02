package unitins.br.tp1.service;

import java.util.List;

import unitins.br.tp1.dto.EstadoDTO;
import unitins.br.tp1.dto.EstadoResponseDTO;

public interface EstadoService {

    EstadoResponseDTO create(EstadoDTO estado);
    void update(long id, EstadoDTO estado);
    void delete(long id);
    EstadoResponseDTO findById(long id);
    EstadoResponseDTO findBySigla(String sigla);
    List<EstadoResponseDTO> findAll();
    
}
