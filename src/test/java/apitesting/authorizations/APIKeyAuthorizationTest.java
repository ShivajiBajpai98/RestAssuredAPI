package apitesting.authorizations;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class APIKeyAuthorizationTest {
    @Test
    public void testAPIKeyAuthorization() {
        // Test method to verify API key authorization

        given()
                .queryParam("Shivaji Token", "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")
                // Setting the query parameter "Shivaji Token" with the API key value

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
