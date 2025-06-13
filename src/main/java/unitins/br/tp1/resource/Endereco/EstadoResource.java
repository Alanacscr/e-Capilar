package unitins.br.tp1.resource.Endereco;

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
import unitins.br.tp1.dto.Endereco.EstadoDTO;
import unitins.br.tp1.dto.Endereco.EstadoResponseDTO;
import unitins.br.tp1.service.Endereco.EstadoService;

@Path("estados")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstadoResource {

    @Inject
    EstadoService service;

    private static final Logger LOG = Logger.getLogger(EstadoResource.class);

    @GET
    @RolesAllowed("Administrador")
    public Response buscarTodos() {
        LOG.info("Entrou no método buscarTodos");

        return Response.ok().entity(service.findAll()).build();
    }

    @GET
    @RolesAllowed({ "Cliente", "Administrador" })
    @Path("/sigla/{sigla}")
    public Response buscarPorSigla(String sigla) {
        LOG.info("Entrou no método buscarPorSigla");
        LOG.debug("O parametro informado foi: " + sigla);

        EstadoResponseDTO dto = service.findBySigla(sigla);

        LOG.debug("Os dados de retorno são: Estado= " + dto.nome() + ", Sigla= " + dto.sigla());

        return Response.ok().entity(dto).build();
    }

    @POST
    @RolesAllowed("Administrador")
    public Response incluir(@Valid EstadoDTO dto) {
        LOG.info("Entrou no método incluir");

        return Response.status(Status.CREATED).entity(service.create(dto)).build();
    }

    @PUT
    @RolesAllowed("Administrador")
    @Path("/{id}")
    public Response alterar(Long id, EstadoDTO dto) {
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
