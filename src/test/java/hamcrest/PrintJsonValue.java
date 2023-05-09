package hamcrest;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PrintJsonValue {

    @Test
    public void printJsonValue() {
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("data.id[0]", equalTo(7))
                .log().all(); // Replace 8 with the desired ID to get the values for that ID
        // You can also replace "email" with "first_name", "last_name" or "avatar" to get the value of that field.
    }
}
