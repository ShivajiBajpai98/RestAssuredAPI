package apitesting;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Preemptive
{
    @Test
    void testPreemptiveAuth()
    {
        given().auth().preemptive().basic("postman","password").

                when().get("https://postman-echo.com/basic-auth").

                then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                .log()
                .all();
    }
}
