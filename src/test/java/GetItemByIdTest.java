import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@DisplayName("Проверка ручки на получение элементов по id  GET /api/1/item/{id}")
public class GetItemByIdTest {

    private static final String BASE_URL = "https://qa-internship.avito.com/api/1";


    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @DisplayName("Тест 1: Проверка корректного ответа для существующего объявления (200 OK)")
    @Test
    public void testGetItemByIdSuccess() {
        String validItemId = "c5c19b38-cc97-4ffa-a121-7387c82878c4";

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/item/" + validItemId)
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("sellerId", notNullValue())
                .body("name", notNullValue())
                .body("price", notNullValue())
                .body("statistics", notNullValue())
                .body("statistics.contacts", notNullValue())
                .body("statistics.likes", notNullValue())
                .body("statistics.viewCount", notNullValue())
                .body("createdAt", notNullValue());
    }
    @DisplayName("Тест 2: Ошибка при запросе с несуществующим `id` (400 Bad Request)")
    @Test
    public void testGetItemByIdNotFound() {
        String invalidId = "99999"; // Несуществующий ID

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/item/" + invalidId)
                .then()
                .statusCode(400)
                .body("result", notNullValue())
                .body("result.message", is("передан некорректный идентификатор объявления"));

    }
    @DisplayName("Тест 3: Ошибка при запросе с некорректным `id` (400 Bad Request)")
    @Test
    public void testGetItemByIdInvalidId() {
        String invalidId = "abc123"; // Некорректный ID

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/item/" + invalidId)
                .then()
                .statusCode(400)
                .body("result", is(notNullValue()))
                .body("result.message", is("передан некорректный идентификатор объявления"));
    }

}
