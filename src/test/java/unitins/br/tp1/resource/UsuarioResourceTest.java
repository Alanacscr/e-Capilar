package unitins.br.tp1.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import jakarta.inject.Inject;
import unitins.br.tp1.service.Usuario.UsuarioService;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;

@QuarkusTest
public class UsuarioResourceTest {

    @Inject
    UsuarioService usuarioService;

    @Test
    @TestSecurity(user = "test", roles = { "Administrador" })
    void testBuscarTodos() {
        given()
                .when().get("/usuarios")
                .then()
                .statusCode(200);
    }

    @Test
    @TestSecurity(user = "test", roles = { "Administrador" })
    void testBuscarPorNome() {
        String nome = "Alana";

        given()
                .when()
                .get("/usuarios/nome/" + nome)
                .then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(0));
    }

}
