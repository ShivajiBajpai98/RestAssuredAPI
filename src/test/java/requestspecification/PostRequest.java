package requestspecification;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequest {

    public static void main(String[] args) {
        // Create a RequestSpecification object
        RequestSpecification requestSpec = RestAssured.given();

        // Set base URI
        requestSpec.baseUri("https://reqres.in");

        // Set headers
        requestSpec.header("Content-Type", "application/json");

        // Set request body
        String requestBody = "{\"name\": \"John\", \"job\": \"Engineer\"}";
        requestSpec.body(requestBody);

        // Send the request and get the response
        Response response = requestSpec.post("/api/users");

        // Print response details
        System.out.println("Status code: " + response.getStatusCode());
        System.out.println("Response body: " + response.getBody().asString());
    }
}
