package unitins.br.tp1.resource;

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
import unitins.br.tp1.service.ProdutoService;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {

    @Inject
    ProdutoService service;

    @GET
    // @RolesAllowed("Administrador")
    public Response buscarTodos() {
        return Response.status(Status.OK).entity(service.findAll()).build();
    }

    @GET
    // @RolesAllowed("Cliente")
    @Path("/nome/{nome}")
    public Response buscarPorNome(String nome) {
        return Response.status(Status.OK).entity(service.findByNome(nome)).build();
    }

    @POST
    public Response incluir(ProdutoDTO dto) {
        return Response.status(Status.CREATED).entity(service.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response alterar(Long id, ProdutoDTO dto) {
        service.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response apagar(Long id) {
        service.delete(id);
        return Response.noContent().build();
    }

}