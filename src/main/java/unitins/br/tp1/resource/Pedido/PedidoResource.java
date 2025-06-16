package unitins.br.tp1.resource.Pedido;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import unitins.br.tp1.dto.Pagamento.BoletoDTO;
import unitins.br.tp1.dto.Pagamento.PixDTO;
import unitins.br.tp1.dto.Pedido.PedidoDTO;
import unitins.br.tp1.dto.Pedido.PedidoResponseDTO;
import unitins.br.tp1.service.Pedido.PedidoService;

@Path("pedido")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoResource {

    @Inject
    JsonWebToken jwt;

    @Inject
    PedidoService pedidoService;

    private static final Logger LOG = Logger.getLogger(PedidoResource.class);

    @GET
    @RolesAllowed({ "Administrador" })
    public Response buscarTodos() {
        LOG.info("Entrou no método buscarTodos");

        return Response.status(Status.OK).entity(pedidoService.findAll()).build();
    }

    @GET
    @RolesAllowed("Administrador")
    @Path("/email/{email}")
    public Response buscarPorEmail(String email) {
        LOG.info("Entrou no método buscarPorEmail");
        LOG.debug("O parametro informado foi: " + email);

        return Response.status(Status.OK).entity(pedidoService.findByEmail(email)).build();
    }

    @POST
    @RolesAllowed("Cliente")
    @Path("/criarpedido")
    public Response criarPedido(PedidoDTO dto) {
        LOG.info("Entrou no método criarPedido");

        // Obtendo o email do token
        String email = jwt.getSubject();

        // Criando o pedido no banco de dados
        PedidoResponseDTO pedido = pedidoService.create(dto, email);

        return Response.ok().entity(pedido).build();
    }

    @POST
    @RolesAllowed("Cliente")
    @Path("/{idPedido}/pagamento/pix")
    public Response adicionarPagamentoPix(@PathParam("idPedido") Long idPedido, PixDTO pixDTO) {
        LOG.info("Entrou no método adicionarPagamentoPix");

        // Chama o serviço para adicionar o pagamento Pix ao pedido
        PedidoResponseDTO pedido = pedidoService.adicionarPagamentoPix(idPedido, pixDTO);
        return Response.ok().entity(pedido).build();
    }

    @POST
    @RolesAllowed("Cliente")
    @Path("/{idPedido}/pagamento/boleto")
    public Response adicionarPagamentoBoleto(@PathParam("idPedido") Long idPedido, BoletoDTO boletoDTO) {
        LOG.info("Entrou no método adicionarPagamentoBoleto");

        // Chama o serviço para adicionar o pagamento Boleto ao pedido
        PedidoResponseDTO pedido = pedidoService.adicionarPagamentoBoleto(idPedido, boletoDTO);
        return Response.ok().entity(pedido).build();
    }

    @DELETE
    @RolesAllowed("Administrador")
    @Path("/{id}")
    @Transactional
    public Response cancelar(Long id) {
        LOG.info("Entrou no método Cancelar");

        pedidoService.cancelar(id);
        return Response.noContent().build();
    }
}
