import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@DisplayName("Проверка ручки на получение статистики по id  GET /api/1/statistic/{id}")
public class GetStatisticByIdTest {

    private static final String BASE_URL = "https://qa-internship.avito.com/api/1/";

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @DisplayName("Тест 1: Проверка корректного ответа для существующего объявления (200 OK)")
    @Test
    public void testGetStatisticsByExistingId() {
        String existingId = "c5c19b38-cc97-4ffa-a121-7387c82878c4"; // Пример существующего ID

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("statistic/" + existingId)
                .then()
                .statusCode(200)
                .body("contacts", is(notNullValue()))
                .body("likes", is(notNullValue()))
                .body("viewCount", is(notNullValue()));
    }

    @DisplayName("Тест 2: Ошибка при запросе с некорректным `id` (400 Bad Request)")
    @Test
    public void testGetStatisticsByInvalidId() {
        String invalidId = "123456"; // Пример некорректного ID

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("statistic/" + invalidId)
                .then()
                .statusCode(400)
                .body("result", is(notNullValue()))
                .body("result.message", is("передан некорректный идентификатор объявления"));// Ожидаемое сообщение

    }
}
