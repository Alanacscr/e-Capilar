package unitins.br.tp1.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
 import jakarta.ws.rs.core.Response.Status;
import unitins.br.tp1.dto.CategoriaDTO;
import unitins.br.tp1.service.CategoriaService;

@Path("categorias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoriaResource {

    @Inject
    CategoriaService service;

    @GET
    public Response buscarTodos() { 
        return Response.status(Status.OK).entity(service.findAll()).build();
    }

    @GET
    @Path("/produto/{id}")
    public Response buscarPorProduto(Long id) { 
        return Response.status(Status.OK).entity(service.findByProduto(id)).build();
    }

    @GET
    @Path("/nome/{nome}")
    public Response  buscarPorNome(String nome) { 
        return Response.status(Status.OK).entity(service.findByNome(nome)).build();
    }

    @POST
    public Response incluir(@Valid CategoriaDTO dto) {
        return Response.status(Status.CREATED).entity(service.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response alterar(Long id, CategoriaDTO dto) {
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response apagar(Long id) {
        return Response.noContent().build();
    }

}