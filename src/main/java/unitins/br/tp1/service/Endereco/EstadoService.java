package unitins.br.tp1.service.Endereco;

import java.util.List;

import unitins.br.tp1.dto.Endereco.EstadoDTO;
import unitins.br.tp1.dto.Endereco.EstadoResponseDTO;

public interface EstadoService {

    EstadoResponseDTO create(EstadoDTO estado);
    void update(long id, EstadoDTO estado);
    void delete(long id);
    EstadoResponseDTO findById(long id);
    EstadoResponseDTO findBySigla(String sigla);
    List<EstadoResponseDTO> findAll();
    
}
