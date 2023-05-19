package apitesting.authorizations;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class OAuth2AuthorizationTest {
    @Test
    public void testOAuth2Authorization() {
        // Test method to verify OAuth2 authorization

        String bearerToken = "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";
        // Bearer token value used for OAuth2 authorization

        given()
                .auth().oauth2(bearerToken)
                // Setting OAuth2 authentication with the Bearer token value

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
