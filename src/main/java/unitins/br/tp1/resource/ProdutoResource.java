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
import unitins.br.tp1.dto.ProdutoDTO;
import unitins.br.tp1.dto.ProdutoResponseDTO;
import unitins.br.tp1.service.ProdutoService;
import jakarta.ws.rs.core.MediaType;

@Path("produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {

    @Inject
    ProdutoService service;

    @GET
    public List<ProdutoResponseDTO> buscarTodos() {
        return service.findAll();
    }

    @GET
    @Path("/nome/{nome}")
    public ProdutoResponseDTO buscarPorNome(String nome) {
        return service.findByNome(nome);
    }

    @POST
    public ProdutoResponseDTO incluir(ProdutoDTO dto) {
        return service.create(dto);
    }

    @PUT
    @Path("/{id}")
    public void alterar(Long id, ProdutoDTO dto) {
        service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void apagar(Long id) {
        service.delete(id);
    }

}