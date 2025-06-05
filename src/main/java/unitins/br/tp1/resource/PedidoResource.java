package unitins.br.tp1.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import unitins.br.tp1.dto.PedidoDTO;
import unitins.br.tp1.dto.PedidoResponseDTO;
import unitins.br.tp1.service.PedidoService;

@Path("pedido")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoResource {

    @Inject
    JsonWebToken jwt;

    @Inject
    PedidoService pedidoService;

    @GET
    @Path("/email/{email}")
    public Response buscarPorEmail(String email) {
        return Response.status(Status.OK).entity(pedidoService.findByEmail(email)).build();
    }
    
    @POST
    // @RolesAllowed({"Cliente"})
    @Path("/criarpedido")
    public Response criarPedido(PedidoDTO dto) { 

        // obtendo o email do token
        String email = jwt.getSubject();

        // criando no bd o pedido 
        PedidoResponseDTO pedido = pedidoService.create(dto, email);

        return Response.ok().entity(pedido).build();
    }

}