package hamcrest;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PrintJsonValue {

    @Test
    public void printJsonValue() {
        // Send a GET request to the specified URL and validate the response
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200) // Verify that the response has a status code of 200
                .body("data.id[0]", equalTo(7)) // Validate that the value of "data.id[0]" is equal to 7
                .log().all(); // Print the response details

        // Note: You can replace "data.id[0]" with the desired JSON path expression to validate a different value.
        // For example, "data.email[0]" will validate the value of the first email in the response.
        // we can modify the expected value in the equalTo() matcher to match your requirements.
    }
}
