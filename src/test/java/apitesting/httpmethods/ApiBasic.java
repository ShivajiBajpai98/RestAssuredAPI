package apitesting.httpmethods;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiBasic {
    @Test
    void testApiBasic() {
        // Test method to verify basic API functionality

        given()
                .baseUri("https://reqres.in/api")
                .header("Content-Type", "application/json")
                .queryParam("page", 2)
                // Setting the base URI, request header, and query parameter

                .when()
                .get("/users")
                // Sending a GET request to "/users" endpoint

                .then()
                .statusCode(200)
                // Verifying that the response status code is 200 (OK)

                .body("data.id[0]", equalTo(7))
                // Verifying that the value of "data.id[0]" in the response body is equal to 7

                .body("data.email", hasItems("michael.lawson@reqres.in", "lindsay.ferguson@reqres.in"))
                // Verifying that the response body contains the specified email addresses in the "data.email" field

                .log().all();
        // Logging all the response details
    }
}
