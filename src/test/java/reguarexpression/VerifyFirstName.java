package regexpression;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class VerifyFirstName {

    @Test
    public void testVerifyFirstName() {
        // Send GET request to retrieve user data
        given()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .assertThat()
                .body("data[0].first_name", matchesRegex("^[A-Za-z]+$"));
    }
}
