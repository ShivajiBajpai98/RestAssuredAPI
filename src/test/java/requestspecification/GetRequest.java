package requestspecification;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetRequest {
    public static void main(String[] args) {
        // Set base URI
        RestAssured.baseURI = "https://reqres.in/api";

        // Create RequestSpecification
        RequestSpecification requestSpec = RestAssured.given();

        // Add query parameters
        requestSpec.queryParam("page", 2);
        requestSpec.queryParam("per_page", 10);

        // Send GET request
        Response response = requestSpec.get("/users");

        // Print response body
        String responseBody = response.getBody().asString();
        System.out.println(responseBody);
    }
}
