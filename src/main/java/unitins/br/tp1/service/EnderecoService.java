package unitins.br.tp1.service;

import java.util.List;

import unitins.br.tp1.dto.EnderecoDTO;
import unitins.br.tp1.dto.EnderecoResponseDTO;

public interface EnderecoService {

    EnderecoResponseDTO create(EnderecoDTO endereco);
    void update(long id, EnderecoDTO endereco);
    void delete(long id);
    EnderecoResponseDTO findById(long id);
    List<EnderecoResponseDTO> findByMunicipio(Long idMunicipio);
    List<EnderecoResponseDTO> findByBairro(String bairro);
    List<EnderecoResponseDTO> findAll();
    
}