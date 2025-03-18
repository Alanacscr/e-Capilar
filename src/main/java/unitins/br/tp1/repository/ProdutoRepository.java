package unitins.br.tp1.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import unitins.br.tp1.model.Produto;

@ApplicationScoped
public class ProdutoRepository implements PanacheRepository<Produto> {

    public Produto findByNome(String nome) {
        return find("SELECT p FROM Produto p WHERE p.nome = ?1 ", nome).firstResult();
    }

}
