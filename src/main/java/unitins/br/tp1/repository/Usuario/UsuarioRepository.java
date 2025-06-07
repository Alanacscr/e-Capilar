package unitins.br.tp1.repository.Usuario;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import unitins.br.tp1.model.Usuario.Usuario;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {

    public Usuario findByNome(String nome) {
        return find("SELECT u FROM Usuario u WHERE u.nome = ?1 ", nome).firstResult();
    }

    public Usuario findById(Long id) {
        return find("SELECT u FROM Usuario u WHERE u.id = ?1 ", id).firstResult();
    }

    public Usuario findByEmailAndSenha(String email, String senha) {
        return find("SELECT u FROM Usuario u WHERE u.email = ?1 AND u.senha = ?2", email, senha).firstResult();
    }

    public Usuario findByEmail(String email) {
        return find("SELECT u FROM Usuario u WHERE u.email = ?1 ", email).firstResult();
    }

}
