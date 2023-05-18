package apitesting.authorizations;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;


public class BearerTokenAuthorizationTest {
    @Test
    public void testBearerTokenAuthorization() {
        String bearerToken = "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";

        given()
                .header("Authorization", "Bearer " + bearerToken)
                .when()
                .get("https://github.com")
                .then()
                .statusCode(200)
                .log().all();
    }
}
