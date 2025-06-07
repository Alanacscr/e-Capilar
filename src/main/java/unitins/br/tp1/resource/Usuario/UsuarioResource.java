package unitins.br.tp1.resource.Usuario;

import org.eclipse.microprofile.jwt.JsonWebToken;
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
import unitins.br.tp1.dto.Usuario.UsuarioDTO;
import unitins.br.tp1.dto.Usuario.UsuarioResponseDTO;
import unitins.br.tp1.service.Usuario.UsuarioService;

@Path("usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    JsonWebToken jwt;

    @Inject
    UsuarioService service;

    @GET
    @RolesAllowed({"Cliente", "Administrador"})
    @Path("/perfil")
    public Response buscarUsuarioLogado() { 

        // obtendo o email do token
        String email = jwt.getSubject();

        // buscando no banco o usuario pelo seu email
        UsuarioResponseDTO usuario = service.findByEmail(email);

        return Response.ok().entity(usuario).build();
    }

    @GET
    public Response buscarTodos() {
        return Response.status(Status.OK).entity(service.findAll()).build();
    }

    @GET
    @Path("/nome/{nome}")
    public Response buscarPorNome(String nome) {
        return Response.status(Status.OK).entity(service.findByNome(nome)).build();
    }

    @POST
    public Response incluir(@Valid UsuarioDTO dto) {
        return Response.status(Status.CREATED).entity(service.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response alterar(Long id, UsuarioDTO dto) {
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