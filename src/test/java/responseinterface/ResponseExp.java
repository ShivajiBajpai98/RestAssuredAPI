package responseinterface;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ResponseExp {
    public static void main(String[] args) {
        // Send GET request
        Response response = RestAssured.get("https://reqres.in/api/users/2");

        // Get status code
        int statusCode = response.getStatusCode();
        System.out.println("Status Code: " + statusCode);

        // Get response body
        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);

        // Get a specific header
        String contentType = response.getHeader("Content-Type");
        System.out.println("Content-Type: " + contentType);

        // Validate response body using assertions
        response.then().assertThat().statusCode(200);
        response.then().assertThat().contentType("application/json");
    }
}

