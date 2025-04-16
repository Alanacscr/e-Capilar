package unitins.br.tp1.resource;

import java.util.List;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import unitins.br.tp1.dto.TelefoneDTO;
import unitins.br.tp1.dto.TelefoneResponseDTO;
import unitins.br.tp1.service.TelefoneService;

@Path("telefones")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TelefoneResource {

    @Inject
    TelefoneService service;

    @GET
    public List<TelefoneResponseDTO> buscarTodos() {
        return service.findAll();
    }

    @GET
    @Path("/numero/{numero}")
    public TelefoneResponseDTO buscarPorNumero(String numero) {
        return service.findByNumero(numero);
    }

    @POST
    public TelefoneResponseDTO incluir(TelefoneDTO dto) {
        return service.create(dto);
    }

    @PUT
    @Path("/{id}")
    public void alterar(Long id, TelefoneDTO dto) {
        service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void apagar(Long id) {
        service.delete(id);
    }

}
