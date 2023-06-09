package apitesting.httpmethods;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class PutPatchDelete {
    @Test
    public void testPutRequest() {
        // Test method to perform a PUT request

        // Create a map of elements
        Map<String, String> elements = new HashMap<>();
        elements.put("name", "shivaji");
        elements.put("job", "Engineer");

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Convert the map to JSON string
            String json = objectMapper.writeValueAsString(elements);
            System.out.println(json);

            baseURI = "https://reqres.in/";

            given()
                    .header("Content-Type", "application/json")
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(json)
                    .when()
                    .put("/api/users/2")
                    .then()
                    .statusCode(200)
                    .log()
                    .all();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPatchRequest() {
        // Test method to perform a PATCH request

        // Create a map of elements
        Map<String, String> elements = new HashMap<>();
        elements.put("name", "shivaji");
        elements.put("job", "Engineer");

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Convert the map to JSON string
            String json = objectMapper.writeValueAsString(elements);
            System.out.println(json);

            baseURI = "https://reqres.in/";

            given()
                    .header("Content-Type", "application/json")
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(json)
                    .when()
                    .patch("/api/users/2")
                    .then()
                    .statusCode(200)
                    .log()
                    .all();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteRequest() {
        // Test method to perform a DELETE request

        baseURI = "https://reqres.in/";

        when()
                .delete("/api/users/2")
                .then()
                .statusCode(204)
                .log()
                .all();
    }
}
