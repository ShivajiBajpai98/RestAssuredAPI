package apitesting;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;

public class RestAssuredOptionsExample {
    public static void main(String[] args) {
        // Set the base URI for the API endpoint
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Send an OPTIONS request to retrieve the allowed methods for the /posts endpoint
        Response response = RestAssured.request(Method.OPTIONS, "/posts");

        // Print the allowed methods specified in the response headers
        System.out.println("Allowed methods: " + response.getHeader("Allow"));
    }
}

