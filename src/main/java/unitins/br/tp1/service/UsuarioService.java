package unitins.br.tp1.service;

import java.util.List;

import unitins.br.tp1.dto.UsuarioDTO;
import unitins.br.tp1.dto.UsuarioResponseDTO;

public interface UsuarioService {
    
    UsuarioResponseDTO create(UsuarioDTO usuario);
    void update(long id, UsuarioDTO usuario);
    void delete(long id);
    UsuarioResponseDTO findById(long id);
    UsuarioResponseDTO findByNome(String nome);
    List<UsuarioResponseDTO> findAll();
}
