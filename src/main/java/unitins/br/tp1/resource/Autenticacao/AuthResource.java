package unitins.br.tp1.resource.Autenticacao;

import org.jboss.logging.Logger;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import unitins.br.tp1.dto.Autenticacao.AuthDTO;
import unitins.br.tp1.dto.Usuario.UsuarioResponseDTO;
import unitins.br.tp1.service.Autenticacao.HashService;
import unitins.br.tp1.service.Autenticacao.JwtService;
import unitins.br.tp1.service.Usuario.UsuarioService;

@Path("auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    HashService hashService;

    @Inject
    JwtService jwtService;

    @Inject
    UsuarioService usuarioService;

    private static final Logger LOG = Logger.getLogger(AuthResource.class);

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(AuthDTO dto) {
        LOG.info("Entrou no método login");

        String hash = null;
        try {
            hash = hashService.getHashSenha(dto.senha());
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }

        UsuarioResponseDTO usuario = usuarioService.findByEmailAndSenha(dto.email(), hash);

        if (usuario == null) 
          return Response.noContent().build();
        
        String token = jwtService.generateJwt(usuario.email(), usuario.perfil().getNOME());
        return Response.ok().header("Authorization", token).build();
            
    }
    
}
