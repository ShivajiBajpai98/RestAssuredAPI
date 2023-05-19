package apitesting.authorizations;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PreemptiveAuthTest {
    @Test
    public void testPreemptiveAuth() {
        // Test method to verify preemptive authentication

        given()
                .auth().preemptive().basic("postman", "password")
                // Setting preemptive basic authentication with username "postman" and password "password"

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
