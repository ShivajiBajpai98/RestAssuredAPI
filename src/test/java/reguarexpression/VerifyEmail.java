package regexpression;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class VerifyEmail {

    @Test
    public void testVerifyEmail() {
        // Send GET request to retrieve user data
        given()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .assertThat()
                .body("data[2].email", matchesRegex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"));
    }
}
