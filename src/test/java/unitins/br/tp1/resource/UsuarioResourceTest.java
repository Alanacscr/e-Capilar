// package unitins.br.tp1.resource;

// import io.quarkus.test.junit.QuarkusTest;
// import io.restassured.http.ContentType;
// import jakarta.inject.Inject;
// import unitins.br.tp1.dto.UsuarioDTO;
// import unitins.br.tp1.dto.UsuarioResponseDTO;
// import unitins.br.tp1.service.UsuarioService;

// import static io.restassured.RestAssured.given;
// import static org.hamcrest.Matchers.*;
// import static org.hamcrest.CoreMatchers.is;
// import static org.hamcrest.MatcherAssert.assertThat;
// import static org.junit.jupiter.api.Assertions.assertNull;

// import org.junit.jupiter.api.Test;

// @QuarkusTest
// public class UsuarioResourceTest {

//         @Inject
//         UsuarioService usuarioService;

//         @Test
//         void testBuscarTodos() {
//                 given()
//                                 .when().get("/usuarios")
//                                 .then()
//                                 .statusCode(200);
//         }

//         @Test
//         void testBuscarPorNome() {
//                 String nome = "Alana";

//                 given()
//                                 .when()
//                                 .get("/usuarios/nome/" + nome)
//                                 .then()
//                                 .statusCode(200)
//                                 .body("size()", greaterThanOrEqualTo(0));
//         }

//         @Test
//         void testIncluir() {
//                 UsuarioDTO usuario = new UsuarioDTO(
//                                 "Janio", "janio@gmail.com", "111", 1, 2L);

//                 given()
//                                 .contentType(ContentType.JSON)
//                                 .body(usuario)
//                                 .when().post("/usuarios")
//                                 .then()
//                                 .statusCode(201)
//                                 .body(
//                                                 "id", notNullValue(),
//                                                 "nome", is("Janio"),
//                                                 "email", is("janio@gmail.com"),
//                                                 "senha", is("111")

//                                 );
//         }

//         static Long id = null;

//         @Test
//         void testAlterar() {
//                 UsuarioDTO usuario = new UsuarioDTO(
//                                 "Janio", "janio@gmail.com", "111", 1, 2L);

//                 id = usuarioService.create(usuario).id();

//                 UsuarioDTO usuarioAlterado = new UsuarioDTO(
//                                 "Carlos", "carlos@gmail.com", "321", 2, 2L);

//                 given()
//                                 .contentType(ContentType.JSON)
//                                 .body(usuarioAlterado)
//                                 .when().put("/usuarios/" + id)
//                                 .then()
//                                 .statusCode(204);

//                 UsuarioResponseDTO response = usuarioService.findById(id);
//                 assertThat(response.nome(), is("Carlos"));
//                 assertThat(response.email(), is("carlos@gmail.com"));
//                 assertThat(response.senha(), is("321"));
//         }

//         @Test
//         void testApagar() {
//                 given()
//                                 .when().delete("/usuarios/" + id)
//                                 .then()
//                                 .statusCode(204);

//                 UsuarioResponseDTO response = usuarioService.findById(id);
//                 assertNull(response);

//         }

// }
