package unitins.br.tp1.resource.Endereco;

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
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import unitins.br.tp1.dto.Endereco.EnderecoDTO;
import unitins.br.tp1.service.Endereco.EnderecoService;

@Path("enderecos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EnderecoResource {

    @Inject
    EnderecoService service;

    @GET
    public Response buscarTodos() {
        return Response.status(Status.OK).entity(service.findAll()).build();
    }

    @GET
    @Path("/municipio/{id}")
    public Response buscarPorMunicipio(Long id) {
        return Response.status(Status.OK).entity(service.findByMunicipio(id)).build();
    }

    @GET
    @Path("/bairro/{bairro}")
    public Response buscarPorBairro(String bairro) {
        return Response.status(Status.OK).entity(service.findByBairro(bairro)).build();
    }

    @POST
    public Response incluir(EnderecoDTO dto) {
        return Response.status(Status.CREATED).entity(service.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response alterar(Long id, EnderecoDTO dto) {
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