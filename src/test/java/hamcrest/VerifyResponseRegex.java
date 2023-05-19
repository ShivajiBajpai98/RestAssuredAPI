package hamcrest;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.matchesPattern;

public class VerifyResponseRegex {

    @Test
    public void verifyResponseRegex() {
        // Send a GET request to the specified URL
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200) // Verify that the response has a status code of 200
                .body("data.email[0]", matchesPattern("[a-z]+\\.[a-z]+@[a-z]+\\.in"));
        // Verify that the email value at index 0 in the 'data' array matches the provided regex pattern
    }
}
