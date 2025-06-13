package unitins.br.tp1.resource.Usuario;

import org.jboss.logging.Logger;

import jakarta.annotation.security.RolesAllowed;
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
import unitins.br.tp1.dto.Usuario.TelefoneDTO;
import unitins.br.tp1.service.Usuario.TelefoneService;

@Path("telefones")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TelefoneResource {

    @Inject
    TelefoneService service;

    private static final Logger LOG = Logger.getLogger(TelefoneResource.class);

    @GET
    @RolesAllowed("Administrador")
    public Response buscarTodos() {
        LOG.info("Entrou no método buscarTodos");

        return Response.status(Status.OK).entity(service.findAll()).build();
    }

    @GET
    @RolesAllowed({ "Cliente", "Administrador" })
    @Path("/numero/{numero}")
    public Response buscarPorNumero(String numero) {
        LOG.info("Entrou no método buscarPorNumero");
        LOG.debug("O parametro informado foi: " + numero);

        return Response.status(Status.OK).entity(service.findByNumero(numero)).build();
    }

    @POST
    @RolesAllowed({ "Cliente", "Administrador" })
    public Response incluir(@Valid TelefoneDTO dto) {
        LOG.info("Entrou no método incluir");

        return Response.status(Status.CREATED).entity(service.create(dto)).build();
    }

    @PUT
    @RolesAllowed({ "Cliente", "Administrador" })
    @Path("/{id}")
    public Response alterar(Long id, TelefoneDTO dto) {
        LOG.info("Entrou no método alterar");

        service.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @RolesAllowed("Administrador")
    @Path("/{id}")
    @Transactional
    public Response apagar(Long id) {
        LOG.info("Entrou no método apagar");

        service.delete(id);
        return Response.noContent().build();
    }

}
