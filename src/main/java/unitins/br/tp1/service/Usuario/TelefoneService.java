package unitins.br.tp1.service.Usuario;

import java.util.List;

import unitins.br.tp1.dto.Usuario.TelefoneDTO;
import unitins.br.tp1.dto.Usuario.TelefoneResponseDTO;

public interface TelefoneService {
    TelefoneResponseDTO create(TelefoneDTO telefone);
    void update(long id, TelefoneDTO telefone);
    void delete(long id);
    TelefoneResponseDTO findById(long id);
    TelefoneResponseDTO findByNumero(String numero);
    List<TelefoneResponseDTO> findAll();
}
