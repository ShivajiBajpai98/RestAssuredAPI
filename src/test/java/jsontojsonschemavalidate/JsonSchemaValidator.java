package jsontojsonschemavalidate;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class JsonSchemaValidator {

    @Test
    void testJsonSchemaValidator() {
        // Set the base URI for the API
        baseURI = "https://reqres.in/api";

        // Send a GET request to retrieve users
        given()
                .get("/users?page=2")
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("listuserschema.json")) // Validate the response body against the JSON schema
                .statusCode(200) // Verify that the response has a status code of 200
                .log().all(); // Print the response details for visualization
    }
}
