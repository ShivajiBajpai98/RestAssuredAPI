package factorydesign.apiclasses;

import factorydesign.apiinterface.ApiTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DigestAuth implements ApiTest {
    @Override
    public void Response() {
        given().auth().digest("postman","password").

                when().get("https://postman-echo.com/basic-auth").

                then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                .log()
                .all();
    }
}
