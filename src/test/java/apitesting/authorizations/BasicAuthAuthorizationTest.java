package apitesting.authorizations;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class BasicAuthAuthorizationTest {
    @Test
    public void testBasicAuthAuthorization() {
        // Test method to verify Basic Authentication authorization

        given()
                .auth().preemptive().basic("postman", "password")
                // Setting the Basic Authentication credentials with username "postman" and password "password"

                .when()
                .get("https://postman-echo.com/basic-auth")
                // Sending a GET request to the specified URL

                .then()
                .statusCode(200)
                // Verifying that the response status code is 200 (OK)

                .body("authenticated", equalTo(true))
                // Verifying that the response body contains a key "authenticated" with a value of true

                .log().all();
        // Logging all the response details
    }
}
