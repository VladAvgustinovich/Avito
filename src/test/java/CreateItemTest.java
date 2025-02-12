import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@DisplayName("Проверка ручки создания нового элемента POST /api/1/item/{id} ")
public class CreateItemTest {


    private static final String BASE_URL = "https://qa-internship.avito.com/api/1/item";
    private static final int VALID_SELLER_ID = 148888;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @DisplayName("Тест 1: Проверка корректности запроса (200 OK)")
    @Test
    public void testCreateItemSuccess() {
        String requestBody = "{ \"sellerID\": " + VALID_SELLER_ID + ", \"name\": \"Test Ad\", \"price\": 1000, \"statistics\": { \"contacts\": 10, \"likes\": 5, \"viewCount\": 100 } }";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post()
                .then()
                .statusCode(200)
                .body("status", notNullValue());
    }

    @DisplayName("Тест 2: Ошибка при нулевом значении sellerID (400 Bad Request)")
    @Test
    public void testCreateItemWithZeroSellerID() {
        String requestBody = "{ \"sellerID\": 0, \"name\": \"Test Ad\", \"price\": 1000, \"statistics\": { \"contacts\": 10, \"likes\": 5, \"viewCount\": 100 } }";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post()
                .then()
                .statusCode(400)
                .body("result.message", notNullValue());
    }
    @DisplayName("Тест 3: Ошибка при отрицательном значении sellerID (400 Bad Request)")
    @Test
    public void testCreateItemWithNegativeSellerID() {
        String requestBody = "{ \"sellerID\": -123456, \"name\": \"Test Ad\", \"price\": 1000, \"statistics\": { \"contacts\": 10, \"likes\": 5, \"viewCount\": 100 } }";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post()
                .then()
                .statusCode(400)
                .body("result.message",notNullValue());
    }
    @DisplayName("Тест 4: Ошибка при отсутствии поля sellerID (400 Bad Request)")
    @Test
    public void testCreateItemWithoutSellerID() {
        String requestBody = "{ \"name\": \"Test Ad\", \"price\": 1000, \"statistics\": { \"contacts\": 10, \"likes\": 5, \"viewCount\": 100 } }";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post()
                .then()
                .statusCode(400)
                .body("result.message", notNullValue());
    }
    @DisplayName("Тест 5: Ошибка при значении `sellerID`, превышающем максимальное (400 Bad Request)")
    @Test
    public void testCreateItemWithExceedingSellerID() {
        String requestBody = "{ \"sellerID\": 1000000, \"name\": \"Test Ad\", \"price\": 1000, \"statistics\": { \"contacts\": 10, \"likes\": 5, \"viewCount\": 100 } }";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post()
                .then()
                .statusCode(400)
                .body("result.message", notNullValue());  // Проверяем, что поле message в объекте result не равно null
    }

    @DisplayName("Тест 6: Ошибка при пустом значении `price` (400 Bad Request)")
    @Test
    public void testCreateItemWithoutPrice() {
        String requestBody = "{ \"sellerID\": " + VALID_SELLER_ID + ", \"name\": \"Test Ad\", \"statistics\": { \"contacts\": 10, \"likes\": 5, \"viewCount\": 100 } }";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post()
                .then()
                .statusCode(400)
                .body("result.message", notNullValue());  // Проверяем, что поле message в объекте result не равно null
    }

    @DisplayName("Тест 7: Ошибка при отрицательном значении `price` (400 Bad Request)")
    @Test
    public void testCreateItemWithNegativePrice() {
        String requestBody = "{ \"sellerID\": " + VALID_SELLER_ID + ", \"name\": \"Test Ad\", \"price\": -500, \"statistics\": { \"contacts\": 10, \"likes\": 5, \"viewCount\": 100 } }";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post()
                .then()
                .statusCode(400)
                .body("result.message", notNullValue());  // Проверяем, что поле message в объекте result не равно null
    }
    @DisplayName("Тест 8: Ошибка при отрицательных значениях в статистике (400 Bad Request)")
    @Test
    public void testCreateItemWithNegativeStatistics() {
        String requestBody = "{ \"sellerID\": " + VALID_SELLER_ID + ", \"name\": \"Test Ad\", \"price\": 1000, \"statistics\": { \"contacts\": -5, \"likes\": -3, \"viewCount\": -10 } }";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post()
                .then()
                .statusCode(400)
                .body("result.message", notNullValue());  // Проверяем, что поле message в объекте result не равно null
    }
    @DisplayName("Тест 9: Ошибка при пустом значении `name` (400 Bad Request)")
    @Test
    public void testCreateItemWithoutName() {
        String requestBody = "{ \"sellerID\": " + VALID_SELLER_ID + ", \"price\": 1000, \"statistics\": { \"contacts\": 10, \"likes\": 5, \"viewCount\": 100 } }";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post()
                .then()
                .statusCode(400)
                .body("result.message", notNullValue());  // Проверяем, что поле message в объекте result не равно null
    }
}
