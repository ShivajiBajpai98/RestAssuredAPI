package factorydesign.apiclasses;

import factorydesign.apiinterface.ApiTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BasicAuth implements ApiTest {
    @Override
    public void Response()
    {
        given().auth().basic("postman","password").

                when().get("https://postman-echo.com/basic-auth").

                then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                .log()
                .all();

    }
}
