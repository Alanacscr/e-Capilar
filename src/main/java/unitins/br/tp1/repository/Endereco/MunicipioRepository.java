package unitins.br.tp1.repository.Endereco;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import unitins.br.tp1.model.Endereco.Municipio;

@ApplicationScoped
public class MunicipioRepository implements PanacheRepository<Municipio> {

    public List<Municipio> findByNome(String nome) {
        return find("SELECT m FROM Municipio m WHERE m.nome LIKE ?1 ", "%" + nome + "%").list();
    }

    public List<Municipio> findByEstado(Long idEstado) {
        return find("SELECT m FROM Municipio m WHERE m.estado.id = ?1", idEstado).list();
    }

}
