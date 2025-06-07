package unitins.br.tp1.repository.Endereco;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import unitins.br.tp1.model.Endereco.Estado;

@ApplicationScoped
public class EstadoRepository implements PanacheRepository<Estado> {

    public Estado findBySigla(String sigla) {
        return find("SELECT e FROM Estado e WHERE e.sigla = ?1 ", sigla).firstResult();
    }
}
