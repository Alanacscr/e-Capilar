package unitins.br.tp1.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import unitins.br.tp1.model.Usuario;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {

     public Usuario findByNome(String nome) {
        return find("SELECT u FROM Usuario u WHERE u.nome = ?1 ", nome).firstResult();
    }

}
