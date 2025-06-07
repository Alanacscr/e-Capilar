package unitins.br.tp1.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import unitins.br.tp1.dto.Endereco.EstadoDTO;
import unitins.br.tp1.dto.Endereco.EstadoResponseDTO;
import unitins.br.tp1.service.Endereco.EstadoService;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

import org.junit.jupiter.api.Test;

@QuarkusTest
public class EstadoResourceTest {

        @Inject
        EstadoService estadoService;

        @Test
        void testBuscarTodos() {
                given()
                                .when().get("/estados")
                                .then()
                                .statusCode(200);
        }

        @Test
        void testBuscarPorSigla() {
                String sigla = "TO";

                given()
                                .when()
                                .get("/estados/sigla/" + sigla)
                                .then()
                                .statusCode(200)
                                .body("size()", greaterThanOrEqualTo(0));
        }

        @Test
        void testIncluir() {
                EstadoDTO estado = new EstadoDTO(
                                "TO",
                                "Toca");

                given()
                                .contentType(ContentType.JSON)
                                .body(estado)
                                .when().post("/estados")
                                .then()
                                .statusCode(201)
                                .body(
                                                "sigla", is("TO"),
                                                "nome", is("Toca")

                                );
        }

        static Long id = null;

        @Test
        void testAlterar() {
                EstadoDTO estado = new EstadoDTO(
                                "TO",
                                "Tocana");

                id = estadoService.create(estado).id();

                EstadoDTO estadoAlterado = new EstadoDTO(
                                "TO",
                                "Tocantins - Alterado");

                given()
                                .contentType(ContentType.JSON)
                                .body(estadoAlterado)
                                .when().put("/estados/" + id)
                                .then()
                                .statusCode(204);

                EstadoResponseDTO response = estadoService.findById(id);
                assertThat(response.sigla(), is("TO"));
                assertThat(response.nome(), is("Tocantins - Alterado"));
        }

        @Test
        void testApagar() {
                given()
                                .when().delete("/estados/" + id)
                                .then()
                                .statusCode(204);

                EstadoResponseDTO response = estadoService.findById(id);
                assertNull(response);

        }

}
