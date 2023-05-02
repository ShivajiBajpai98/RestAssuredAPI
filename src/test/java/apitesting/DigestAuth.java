package apitesting;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DigestAuth
{

    @Test
    void testDigestAuth()
    {
        given().auth().digest("postman","password").

                when().get("https://postman-echo.com/basic-auth").

                then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                .log()
                .all();
    }
}
