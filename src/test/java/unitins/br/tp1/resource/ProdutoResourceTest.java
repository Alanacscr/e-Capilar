package unitins.br.tp1.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import unitins.br.tp1.dto.Produto.ProdutoDTO;
import unitins.br.tp1.dto.Produto.ProdutoResponseDTO;
import unitins.br.tp1.service.Produto.ProdutoService;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

@QuarkusTest
public class ProdutoResourceTest {

        @Inject
        ProdutoService produtoService;

        @Test
        void testBuscarTodos() {
                given()
                                .when().get("/produtos")
                                .then()
                                .statusCode(200);
        }

        @Test
        void testBuscarPorNome() {
                String nome = "Igora";

                given()
                                .when()
                                .get("/produtos/nome/" + nome)
                                .then()
                                .statusCode(200)
                                .body("size()", greaterThanOrEqualTo(0));
        }

        @Test
        void testIncluir() {
                ProdutoDTO produto = new ProdutoDTO(
                                "Tinta 01", "Boa", 79.9f, 2,
                                2);

                given()
                                .contentType(ContentType.JSON)
                                .body(produto)
                                .when().post("/produtos")
                                .then()
                                .statusCode(201)
                                .body(
                                                "id", notNullValue(),
                                                "nome", is("Tinta 01"),
                                                "descricao", is("Boa"),
                                                "preco", is(79.9f),
                                                "quantidadeEstoque", is(2)

                                );
        }

        static Long id = null;

        @Test
        void testAlterar() {
                ProdutoDTO produto = new ProdutoDTO(
                                "Tinta 01", "Boa", 10.0, 2,
                                2);

                id = produtoService.create(produto).id();

                ProdutoDTO produtoAlterado = new ProdutoDTO(
                                "Tinta 02", "Boa 2", 11.0, 20,
                                3);

                given()
                                .contentType(ContentType.JSON)
                                .body(produtoAlterado)
                                .when().put("/produtos/" + id)
                                .then()
                                .statusCode(204);

                ProdutoResponseDTO response = produtoService.findById(id);
                assertThat(response.nome(), is("Tinta 02"));
                assertThat(response.descricao(), is("Boa 2"));
                assertThat(response.preco(), is(11.0));
                assertThat(response.quantidadeEstoque(), is(20));
        }

        @Test
        void testApagar() {
                given()
                                .when().delete("/produtos/" + id)
                                .then()
                                .statusCode(204);

                ProdutoResponseDTO response = produtoService.findById(id);
                assertNull(response);

        }

}
