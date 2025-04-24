package unitins.br.tp1.repository;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import unitins.br.tp1.model.Endereco;

@ApplicationScoped
public class EnderecoRepository implements PanacheRepository<Endereco> {

    public List<Endereco> findByBairro(String bairro) {
        return find("SELECT e FROM Endereco e WHERE e.bairro LIKE ?1 ", "%" + bairro + "%").list();
    }

     public List<Endereco> findByMunicipio(Long idMunicipio) {
        return find("SELECT e FROM Endereco e WHERE e.municipio.id = ?1", idMunicipio).list();
    }

}