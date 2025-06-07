package unitins.br.tp1.repository.Usuario;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import unitins.br.tp1.model.Usuario.Telefone;

@ApplicationScoped
public class TelefoneRepository implements PanacheRepository<Telefone> {

     public Telefone findByNumero(String numero) {
        return find("SELECT t FROM Telefone t WHERE t.numero = ?1 ", numero).firstResult();
    }

}