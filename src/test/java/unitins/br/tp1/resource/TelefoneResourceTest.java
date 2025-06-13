package unitins.br.tp1.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import unitins.br.tp1.dto.Usuario.TelefoneDTO;
import unitins.br.tp1.dto.Usuario.TelefoneResponseDTO;
import unitins.br.tp1.service.Usuario.TelefoneService;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

@QuarkusTest
public class TelefoneResourceTest {

    @Inject
    TelefoneService telefoneService;

    @Test
    @TestSecurity(user = "test", roles = { "Administrador" })
    void testBuscarTodos() {
        given()
                .when().get("/telefones")
                .then()
                .statusCode(200);
    }

    @Test
    @TestSecurity(user = "test", roles = { "Cliente", "Administrador" })
    void testBuscarPorNumero() {
        String numero = "99215-1290"; 

        given()
                .when()
                .get("/telefones/numero/" + numero)
                .then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(0)); 
    }

    @Test
    @TestSecurity(user = "test", roles = { "Cliente", "Administrador" })
    void testIncluir() {
        TelefoneDTO Telefone = new TelefoneDTO(
                "062",
                "99218-0000");

        given()
                .contentType(ContentType.JSON)
                .body(Telefone)
                .when().post("/telefones")
                .then()
                .statusCode(201)
                .body(
                        "codigoArea", is("062"),
                        "numero", is("99218-0000")

                );
    }

    static Long id = null;

    @Test
    @TestSecurity(user = "test", roles = { "Cliente", "Administrador" })
    void testAlterar() {
        TelefoneDTO telefone = new TelefoneDTO(
                "062",
                "99218-0000");

        id = telefoneService.create(telefone).id();

        TelefoneDTO TelefoneAlterado = new TelefoneDTO(
                "065",
                "99000-0000");

        given()
                .contentType(ContentType.JSON)
                .body(TelefoneAlterado)
                .when().put("/telefones/" + id)
                .then()
                .statusCode(204);

        TelefoneResponseDTO response = telefoneService.findById(id);
        assertThat(response.codigoArea(), is("065"));
        assertThat(response.numero(), is("99000-0000"));
    }

    @Test
    @TestSecurity(user = "test", roles = { "Administrador" })
    void testApagar() {
        given()
                .when().delete("/telefones/" + id)
                .then()
                .statusCode(204);

        TelefoneResponseDTO response = telefoneService.findById(id);
        assertNull(response);

    }

}
