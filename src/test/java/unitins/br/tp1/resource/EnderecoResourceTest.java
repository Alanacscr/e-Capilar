package unitins.br.tp1.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import unitins.br.tp1.dto.EnderecoDTO;
import unitins.br.tp1.dto.EnderecoResponseDTO;
import unitins.br.tp1.service.EnderecoService;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

@QuarkusTest
public class EnderecoResourceTest {

        @Inject
        EnderecoService enderecoService;

        @Test
        void testBuscarTodos() {
                given()
                                .when().get("/enderecos")
                                .then()
                                .statusCode(200);
        }

        @Test
        void testBuscarPorBairro() {
                String bairro = "Taquaralto";

                given()
                                .when()
                                .get("/enderecos/bairro/" + bairro)
                                .then()
                                .statusCode(200)
                                .body("size()", greaterThanOrEqualTo(0));
        }

        @Test
        void testBuscarPorMunicipio() {
                Long idMunicipio = 1L;
                given()
                                .when()
                                .get("/enderecos/municipio/" + idMunicipio)
                                .then()
                                .statusCode(200)
                                .body("size()", greaterThanOrEqualTo(0));
        }

        @Test
        void testIncluir() {
                EnderecoDTO endereco = new EnderecoDTO(
                                "Rua P0", 12, "Plano Norte", "77065-890", "Nenhum",
                                1L);

                given()
                                .contentType(ContentType.JSON)
                                .body(endereco)
                                .when().post("/enderecos")
                                .then()
                                .statusCode(201)
                                .body(
                                                "id", notNullValue(),
                                                "logradouro", is("Rua P0"),
                                                "numero", is(12),
                                                "bairro", is("Plano Norte"),
                                                "cep", is("77065-890"),
                                                "complemento", is("Nenhum")

                                );
        }

        static Long id = null;

        @Test
        void testAlterar() {
                EnderecoDTO endereco = new EnderecoDTO(
                                "Rua P0", 12, "Plano Norte", "77065-890", "Nenhum",
                                1L);

                id = enderecoService.create(endereco).id();

                EnderecoDTO enderecoAlterado = new EnderecoDTO(
                                "Rua P1", 12, "Plano Sul", "70000-000", "Nada",
                                2L);

                given()
                                .contentType(ContentType.JSON)
                                .body(enderecoAlterado)
                                .when().put("/enderecos/" + id)
                                .then()
                                .statusCode(204);

                EnderecoResponseDTO response = enderecoService.findById(id);
                assertThat(response.logradouro(), is("Rua P1"));
                assertThat(response.numero(), is(12));
                assertThat(response.bairro(), is("Plano Sul"));
                assertThat(response.cep(), is("70000-000"));
                assertThat(response.complemento(), is("Nada"));
        }

        @Test
        void testApagar() {
                given()
                                .when().delete("/enderecos/" + id)
                                .then()
                                .statusCode(204);

                EnderecoResponseDTO response = enderecoService.findById(id);
                assertNull(response);

        }

}
