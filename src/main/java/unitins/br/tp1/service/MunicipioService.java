package unitins.br.tp1.service;

import java.util.List;

import unitins.br.tp1.dto.MunicipioDTO;
import unitins.br.tp1.dto.MunicipioResponseDTO;

public interface MunicipioService {

    MunicipioResponseDTO create(MunicipioDTO municipio);
    void update(long id, MunicipioDTO municipio);
    void delete(long id);
    MunicipioResponseDTO findById(long id);
    List<MunicipioResponseDTO> findByNome(String nome);
    List<MunicipioResponseDTO> findByEstado(Long idEstado);
    List<MunicipioResponseDTO> findAll();
    
}