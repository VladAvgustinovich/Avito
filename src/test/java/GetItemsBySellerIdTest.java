import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@DisplayName("Проверка ручки на получение элементов по id продавца GET /api/1/{sellerID}/item")
public class GetItemsBySellerIdTest {

    private static final String BASE_URL = "https://qa-internship.avito.com/api/1/";

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @DisplayName("Тест 1: Проверка корректного ответа для существующего sellerID (200 OK)")
    @Test
    public void testGetItemsByExistingSellerId() {

        String existingSellerId = "148888"; // Пример существующего sellerID

        given()
                .contentType(ContentType.JSON)
                .when()
                .get(existingSellerId + "/item")
                .then()
                .statusCode(200)
                .body("id", is(notNullValue()))
                .body("sellerId", is(notNullValue()))
                .body("name", is(notNullValue()))
                .body("price", is(notNullValue()))
                .body("statistics.likes", is(notNullValue()))
                .body("statistics.viewCount", is(notNullValue()))
                .body("statistics.contacts", is(notNullValue()))
                .body("createdAt", is(notNullValue()));
    }
    @DisplayName("Тест 2: Проверка корректного ответа для не существующего sellerID (400 Bad Request)")
    @Test
    public void testGetItemsByNonExistingSellerId() {
        String nonExistingSellerId = "150"; // Пример несуществующего sellerID

        given()
                .contentType(ContentType.JSON)
                .when()
                .get(nonExistingSellerId + "/item")
                .then()
                .statusCode(400)
                .body("result", is(notNullValue()))
                .body("result.message", is("передан некорректный идентификатор продавца")) // Ожидаемое сообщение
                .body("result.messages", is(notNullValue()));
    }
}
