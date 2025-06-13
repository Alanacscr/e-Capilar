package unitins.br.tp1.resource.Endereco;

import org.jboss.logging.Logger;

import jakarta.annotation.security.RolesAllowed;
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

    private static final Logger LOG = Logger.getLogger(EnderecoResource.class);

    @GET
    @RolesAllowed("Administrador")
    public Response buscarTodos() {
        LOG.info("Entrou no método buscarTodos");
        
        return Response.status(Status.OK).entity(service.findAll()).build();
    }

    @GET
    @RolesAllowed("Administrador")
    @Path("/municipio/{id}")
    public Response buscarPorMunicipio(Long id) {
        LOG.info("Entrou no método buscarPorMunicipio");
        LOG.debug("O parametro informado foi: " + id);

        return Response.status(Status.OK).entity(service.findByMunicipio(id)).build();
    }

    @GET
    @RolesAllowed("Administrador")
    @Path("/bairro/{bairro}")
    public Response buscarPorBairro(String bairro) {
        LOG.info("Entrou no método buscarPorBairro");
        LOG.debug("O parametro informado foi: " + bairro);
        
        return Response.status(Status.OK).entity(service.findByBairro(bairro)).build();
    }

    @POST
    @RolesAllowed({"Cliente", "Administrador"})
    public Response incluir(EnderecoDTO dto) {
        LOG.info("Entrou no método incluir");
        
        return Response.status(Status.CREATED).entity(service.create(dto)).build();
    }

    @PUT
    @RolesAllowed({"Cliente", "Administrador"})
    @Path("/{id}")
    public Response alterar(Long id, EnderecoDTO dto) {
        LOG.info("Entrou no método alterar");
        
        service.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @RolesAllowed({"Cliente", "Administrador"})
    @Path("/{id}")
    @Transactional
    public Response apagar(Long id) {
        LOG.info("Entrou no método apagar");

        service.delete(id);
        return Response.noContent().build();
    }

}