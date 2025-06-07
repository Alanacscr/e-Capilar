package unitins.br.tp1.service.Usuario;

import java.util.List;

import unitins.br.tp1.dto.Usuario.UsuarioDTO;
import unitins.br.tp1.dto.Usuario.UsuarioResponseDTO;

public interface UsuarioService {
    
    UsuarioResponseDTO create(UsuarioDTO usuario);
    void update(long id, UsuarioDTO usuario);
    void delete(long id);
    UsuarioResponseDTO findById(long id);
    UsuarioResponseDTO findByNome(String nome);
    List<UsuarioResponseDTO> findAll();
    UsuarioResponseDTO findByEmailAndSenha(String email, String senha);
    UsuarioResponseDTO findByEmail(String email);
}
