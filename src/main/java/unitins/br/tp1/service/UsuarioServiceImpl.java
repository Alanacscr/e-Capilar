package unitins.br.tp1.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import unitins.br.tp1.dto.UsuarioDTO;
import unitins.br.tp1.dto.UsuarioResponseDTO;
import unitins.br.tp1.model.Perfil;
import unitins.br.tp1.model.Telefone;
import unitins.br.tp1.model.Usuario;
import unitins.br.tp1.repository.TelefoneRepository;
import unitins.br.tp1.repository.UsuarioRepository;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    UsuarioRepository usuarioRepository;
    @Inject
    TelefoneRepository telefoneRepository;

    @Override
    @Transactional
    public UsuarioResponseDTO create(UsuarioDTO usuario) {
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(usuario.nome());
        novoUsuario.setEmail(usuario.email());
        novoUsuario.setSenha(usuario.senha());
        novoUsuario.setPerfil(Perfil.valueOf(usuario.idPerfil()));

        // buscando o telefone pelo id
        Telefone telefone = telefoneRepository.findById(usuario.idTelefone());
        novoUsuario.setTelefone(telefone);

        // realizando inclusao
        usuarioRepository.persist(novoUsuario);

        return UsuarioResponseDTO.valueOf(novoUsuario);
    }

    @Override
    @Transactional
    public void update(long id, UsuarioDTO usuario) {
        Usuario edicaoUsuario = usuarioRepository.findById(id);
        edicaoUsuario.setNome(usuario.nome());
        edicaoUsuario.setEmail(usuario.email());
        edicaoUsuario.setSenha(usuario.senha());
        edicaoUsuario.setPerfil(Perfil.valueOf(usuario.idPerfil()));

        // buscando o telefone pelo id
        Telefone telefone = telefoneRepository.findById(usuario.idTelefone());
        edicaoUsuario.setTelefone(telefone);
    }

    @Override
    @Transactional
    public void delete(long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public UsuarioResponseDTO findById(long id) {
        return UsuarioResponseDTO.valueOf(usuarioRepository.findById(id));
    }

    @Override
    public UsuarioResponseDTO findByNome(String nome) {
        return UsuarioResponseDTO.valueOf(usuarioRepository.findByNome(nome));
    }

    @Override
    public List<UsuarioResponseDTO> findAll() {
        return usuarioRepository.findAll().stream().map(e -> UsuarioResponseDTO.valueOf(e)).toList();
    }

    @Override
    public UsuarioResponseDTO findByEmailAndSenha(String email, String senha) {
        return UsuarioResponseDTO.valueOf(
                usuarioRepository.findByEmailAndSenha(email, senha)
            );

    }

    @Override
    public UsuarioResponseDTO findByEmail(String email) {
        return UsuarioResponseDTO.valueOf(
            usuarioRepository.findByEmail(email)
        );
    }

}
