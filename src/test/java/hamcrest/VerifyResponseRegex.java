package hamcrest;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.matchesPattern;

public class VerifyResponseRegex {

    @Test
    public void verifyResponseRegex() {
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("data.email[0]", matchesPattern("[a-z]+\\.[a-z]+@[a-z]+\\.in"));
    }
}
