package apitesting.authorizations;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class OAuth2AuthorizationTest {
    @Test
    public void testOAuth2Authorization() {
        String bearerToken = "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";

        given()
                .auth().oauth2(bearerToken)
                .when()
                .get("https://github.com")
                .then()
                .statusCode(200)
                .log().all();
    }
}
