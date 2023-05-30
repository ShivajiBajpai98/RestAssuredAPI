package petstoreswagger.pratice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AddNewPetWithImage {
    public static void main(String[] args) {
        // Set the base URI for the API
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        // Create the request specification
        RequestSpecification request = RestAssured.given();

        // Set the request headers and content type
        request.header("Content-Type", "multipart/form-data");

        // Define the request payload with the pet details
        request.multiPart("id", "12211221");
        request.multiPart("name", "Vulture1");
        request.multiPart("status", "available");
        request.multiPart("additionalMetadata", "Additional metadata");
        request.multiPart("file", new java.io.File("src/test/resources/petstore1.jpg"));

        // Send the POST request to add the new pet with image
        Response response = request.post("/pet");

        // Get the response status code
        int statusCode = response.getStatusCode();

        // Optionally, you can print the response body for further inspection
        String responseBody = response.getBody().asString();
        System.out.println("Response body: " + responseBody);

        // Check the status code to confirm the pet was added successfully
        if (statusCode == 200) {
            System.out.println("Pet added successfully!");
        } else {
            System.out.println("Failed to add the pet. Status code: " + statusCode);
        }
    }
}
