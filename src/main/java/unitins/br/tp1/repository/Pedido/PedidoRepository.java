package unitins.br.tp1.repository.Pedido;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import unitins.br.tp1.model.Pedido.Pedido;

@ApplicationScoped
public class PedidoRepository implements PanacheRepository<Pedido> {

    public List<Pedido> findByUsuario(String email) {
        return find("SELECT p FROM Pedido p WHERE p.usuario.email = ?1 ", email).list();
    }

}
