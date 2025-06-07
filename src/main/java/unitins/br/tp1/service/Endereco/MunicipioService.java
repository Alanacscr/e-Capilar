package unitins.br.tp1.service.Endereco;

import java.util.List;

import unitins.br.tp1.dto.Endereco.MunicipioDTO;
import unitins.br.tp1.dto.Endereco.MunicipioResponseDTO;

public interface MunicipioService {

    MunicipioResponseDTO create(MunicipioDTO municipio);
    void update(long id, MunicipioDTO municipio);
    void delete(long id);
    MunicipioResponseDTO findById(long id);
    List<MunicipioResponseDTO> findByNome(String nome);
    List<MunicipioResponseDTO> findByEstado(Long idEstado);
    List<MunicipioResponseDTO> findAll();
    
}