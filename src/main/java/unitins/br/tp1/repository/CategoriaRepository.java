package unitins.br.tp1.repository;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import unitins.br.tp1.model.Categoria;

@ApplicationScoped
public class CategoriaRepository implements PanacheRepository<Categoria> {

    public List<Categoria> findByNome(String nome) {
        return find("SELECT c FROM Categoria c WHERE c.nome LIKE ?1 ", "%" + nome + "%").list();
    }

    public List<Categoria> findByProduto(Long idProduto) {
        return find("SELECT c FROM Categoria c WHERE c.produto.id = ?1", idProduto).list();
    }

}