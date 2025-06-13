package unitins.br.tp1.resource.Produto;

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
import unitins.br.tp1.dto.Produto.CategoriaDTO;
import unitins.br.tp1.service.Produto.CategoriaService;

@Path("categorias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoriaResource {

    @Inject
    CategoriaService service;

    private static final Logger LOG = Logger.getLogger(CategoriaResource.class);

    @GET
    @RolesAllowed({ "Cliente", "Administrador" })
    public Response buscarTodos() {
        LOG.info("Entrou no método buscarTodos");

        return Response.status(Status.OK).entity(service.findAll()).build();
    }

    @GET
    @RolesAllowed({ "Cliente", "Administrador" })
    @Path("/produto/{id}")
    public Response buscarPorProduto(Long id) {
        LOG.info("Entrou no método buscarPorProduto");
        LOG.debug("O parametro informado foi: " + id);

        return Response.status(Status.OK).entity(service.findByProduto(id)).build();
    }

    @GET
    @RolesAllowed({ "Cliente", "Administrador" })
    @Path("/nome/{nome}")
    public Response buscarPorNome(String nome) {
        LOG.info("Entrou no método buscarPorNome");
        LOG.debug("O parametro informado foi: " + nome);

        return Response.status(Status.OK).entity(service.findByNome(nome)).build();
    }

    @POST
    @RolesAllowed("Administrador")
    public Response incluir(CategoriaDTO dto) {
        LOG.info("Entrou no método incluir");

        return Response.status(Status.CREATED).entity(service.create(dto)).build();
    }

    @PUT
    @RolesAllowed("Administrador")
    @Path("/{id}")
    public Response alterar(Long id, CategoriaDTO dto) {
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