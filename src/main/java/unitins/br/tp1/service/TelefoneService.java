package unitins.br.tp1.service;

import java.util.List;

import unitins.br.tp1.dto.TelefoneDTO;
import unitins.br.tp1.dto.TelefoneResponseDTO;

public interface TelefoneService {
    TelefoneResponseDTO create(TelefoneDTO telefone);
    void update(long id, TelefoneDTO telefone);
    void delete(long id);
    TelefoneResponseDTO findById(long id);
    TelefoneResponseDTO findByNumero(String numero);
    List<TelefoneResponseDTO> findAll();
}
