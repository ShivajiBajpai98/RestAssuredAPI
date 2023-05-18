package apitesting.authorizations;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class APIKeyAuthorizationTest {
    @Test
    public void testAPIKeyAuthorization() {
        given()
                .queryParam("Shivaji Token", "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")
                .when()
                .get("https://github.com")
                .then()
                .statusCode(200)
                .log().all();
    }
}
