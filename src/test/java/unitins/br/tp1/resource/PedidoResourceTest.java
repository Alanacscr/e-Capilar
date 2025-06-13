package unitins.br.tp1.resource;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import unitins.br.tp1.service.Pedido.PedidoService;
import jakarta.inject.Inject;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


@QuarkusTest
public class PedidoResourceTest {

    @Inject
    PedidoService service;

    @Test
    @TestSecurity(user = "test", roles = { "Administrador" })
    void testBuscarPedidoEmail() {
        String email = "alana@gmail.com";

        given()
                .when()
                .get("/pedido/email/" + email)
                .then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(0));
    }

}
