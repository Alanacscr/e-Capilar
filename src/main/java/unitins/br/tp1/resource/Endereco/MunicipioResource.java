package unitins.br.tp1.resource.Endereco;


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
import unitins.br.tp1.dto.Endereco.MunicipioDTO;
import unitins.br.tp1.service.Endereco.MunicipioService;

@Path("municipios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MunicipioResource {

    @Inject
    MunicipioService service;

    @GET
    public Response buscarTodos() {
        return Response.status(Status.OK).entity(service.findAll()).build();
    }

    @GET
    @Path("/nome/{nome}")
    public Response buscarPorNome(String nome) {
        return Response.status(Status.OK).entity(service.findByNome(nome)).build();
    }

    @GET
    @Path("/estado/{id}")
    public Response buscarPorEstado(Long id) { 
        return Response.status(Status.OK).entity(service.findByEstado(id)).build();
    }

    @POST
    public Response incluir(@Valid MunicipioDTO dto) {
        return Response.status(Status.CREATED).entity(service.create(dto)).build(); 
    }

    @PUT
    @Path("/{id}")
    public Response alterar(Long id, MunicipioDTO dto) {
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
