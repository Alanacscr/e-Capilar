package unitins.br.tp1.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

import org.junit.jupiter.api.Test;
import io.quarkus.test.security.TestSecurity;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import unitins.br.tp1.dto.Produto.CategoriaDTO;
import unitins.br.tp1.dto.Produto.CategoriaResponseDTO;
import unitins.br.tp1.service.Produto.CategoriaService;

@QuarkusTest
public class CategoriaResourceTest {

        @Inject
        CategoriaService categoriaService;

        @Test
        @TestSecurity(user = "test", roles = { "Cliente", "Administrador" })
        void testBuscarTodos() {
                given()
                                .when().get("/categorias")
                                .then()
                                .statusCode(200);
        }

        @Test
        @TestSecurity(user = "test", roles = { "Cliente", "Administrador" })
        void testBuscarPorCategoria() {
                String categoria = "fuleira";

                given()
                                .when()
                                .get("/categorias/nome/" + categoria)
                                .then()
                                .statusCode(200)
                                .body("size()", greaterThanOrEqualTo(0));
        }

        @Test
        @TestSecurity(user = "test", roles = { "Administrador" })
        void testIncluir() {
                CategoriaDTO categoria = new CategoriaDTO("Basic", 1L);

                given()
                                .contentType(ContentType.JSON)
                                .body(categoria)
                                .when()
                                .post("/categorias")
                                .then()
                                .statusCode(201)
                                .body(
                                                "id", notNullValue(),
                                                "nome", is("Basic"));
        }

        static Long id = null;

        @Test
        @TestSecurity(user = "test", roles = { "Administrador" })
        void testAlterar() {
                CategoriaDTO categoria = new CategoriaDTO("Premium - Alterado", 1L);

                id = categoriaService.create(categoria).id();

                CategoriaDTO categoriaAlterado = new CategoriaDTO(
                                "Premium - Alterado",
                                1L);

                given()
                                .contentType(ContentType.JSON)
                                .body(categoriaAlterado)
                                .when().put("/categorias/" + id)
                                .then()
                                .statusCode(204);

                CategoriaResponseDTO response = categoriaService.findById(id);
                assertThat(response.nome(), is("Premium - Alterado"));
        }

        @Test
        @TestSecurity(user = "test", roles = { "Cliente", "Administrador" })
        void testBuscarPorProduto() {
                Long idProduto = 1L;
                given()
                                .when()
                                .get("/categorias/produto/" + idProduto)
                                .then()
                                .statusCode(200)
                                .body("size()", greaterThanOrEqualTo(0));
        }

        @Test
        @TestSecurity(user = "test", roles = { "Administrador" })
        void testApagar() {
                given()
                                .when().delete("/categorias/" + id)
                                .then()
                                .statusCode(204);

                CategoriaResponseDTO response = categoriaService.findById(id);
                assertNull(response);

        }

}
