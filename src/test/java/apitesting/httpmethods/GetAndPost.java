package apitesting.httpmethods;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetAndPost {
    @Test
    public void testGet() {
        // Test method to perform a GET request and validate the response

        baseURI = "https://reqres.in/";
        get("api/users?page=2")
                .then()
                .statusCode(200)
                .body("data[4].first_name", equalTo("George"))
                .body("data.first_name", hasItems("George", "Byron"));
    }

    @Test
    public void testPost() {
        // Test method to perform a POST request with JSON payload

        // Create a map to hold the request payload data
        Map<String, String> elements = new HashMap<>();
        elements.put("name", "shivaji");
        elements.put("job", "Engineer");

        // Create an instance of ObjectMapper to convert the map to JSON
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Convert the map to JSON string
            String json = objectMapper.writeValueAsString(elements);

            baseURI = "https://reqres.in/";

            given()
                    .header("Content-Type", "application/json")
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(json)
                    .when()
                    .post("/api/users")
                    .then()
                    .statusCode(201)
                    .log()
                    .all();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
