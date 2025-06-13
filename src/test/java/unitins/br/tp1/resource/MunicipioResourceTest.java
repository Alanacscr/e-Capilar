package unitins.br.tp1.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import unitins.br.tp1.dto.Endereco.MunicipioDTO;
import unitins.br.tp1.dto.Endereco.MunicipioResponseDTO;
import unitins.br.tp1.service.Endereco.MunicipioService;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

@QuarkusTest
public class MunicipioResourceTest {

        @Inject
        MunicipioService municipioService;

        @Test
        @TestSecurity(user = "test", roles = { "Cliente", "Administrador" })
        void testBuscarTodos() {
                given()
                                .when().get("/municipios")
                                .then()
                                .statusCode(200);
        }

        @Test
        @TestSecurity(user = "test", roles = { "Cliente", "Administrador" })
        void testBuscarPorNome() {
                String nome = "Palmas";

                given()
                                .when()
                                .get("/municipios/nome/" + nome)
                                .then()
                                .statusCode(200)
                                .body("size()", greaterThanOrEqualTo(0));
        }

        @Test
        @TestSecurity(user = "test", roles = { "Cliente", "Administrador" })
        void testBuscarPorEstado() {
                Long idEstado = 1L;
                given()
                                .when()
                                .get("/municipios/estado/" + idEstado)
                                .then()
                                .statusCode(200)
                                .body("size()", greaterThanOrEqualTo(0));
        }

        @Test
        @TestSecurity(user = "test", roles = { "Administrador" })
        void testIncluir() {
                MunicipioDTO municipio = new MunicipioDTO(
                                "Petrolina",
                                1L);

                given()
                                .contentType(ContentType.JSON)
                                .body(municipio)
                                .when().post("/municipios")
                                .then()
                                .statusCode(201)
                                .body(
                                                "id", notNullValue(),
                                                "nome", is("Petrolina")

                                );
        }

        static Long id = null;

        @Test
        @TestSecurity(user = "test", roles = { "Administrador" })
        void testAlterar() {
                MunicipioDTO municipio = new MunicipioDTO(
                                "Petrolina",
                                1L);

                id = municipioService.create(municipio).id();

                MunicipioDTO municipioAlterado = new MunicipioDTO(
                                "Petropólis",
                                2L);

                given()
                                .contentType(ContentType.JSON)
                                .body(municipioAlterado)
                                .when().put("/municipios/" + id)
                                .then()
                                .statusCode(204);

                MunicipioResponseDTO response = municipioService.findById(id);
                assertThat(response.nome(), is("Petropólis"));
        }

        @Test
        @TestSecurity(user = "test", roles = { "Administrador" })
        void testApagar() {
                given()
                                .when().delete("/municipios/" + id)
                                .then()
                                .statusCode(204);

                MunicipioResponseDTO response = municipioService.findById(id);
                assertNull(response);

        }

}
