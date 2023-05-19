package apitesting.authorizations;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class BearerTokenAuthorizationTest {
    @Test
    public void testBearerTokenAuthorization() {
        // Test method to verify Bearer Token authorization

        String bearerToken = "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";
        // Bearer token value used for authorization

        given()
                .header("Authorization", "Bearer " + bearerToken)
                // Setting the Authorization header with the Bearer token value

                .when()
                .get("https://github.com")
                // Sending a GET request to the specified URL

                .then()
                .statusCode(200)
                // Verifying that the response status code is 200 (OK)

                .log().all();
        // Logging all the response details
    }
}
