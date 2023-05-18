package apitesting.httpmethods;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiBasic {
    @Test
    void testApiBasic() {
        given()
                .baseUri("https://reqres.in/api")
                .header("Content-Type", "application/json")
                .queryParam("page", 2)
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .body("data.id[0]", equalTo(7))
                .body("data.email", hasItems("michael.lawson@reqres.in", "lindsay.ferguson@reqres.in"))
                .log().all();
    }
}
