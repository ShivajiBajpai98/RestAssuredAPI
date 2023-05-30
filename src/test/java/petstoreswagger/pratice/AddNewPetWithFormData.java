package petstoreswagger.pratice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AddNewPetWithFormData {
    public static void main(String[] args) {
        // Set the base URI for the API
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        // Create the request specification
        RequestSpecification request = RestAssured.given();

        // Set the request headers and content type
        request.header("Content-Type", "application/json");

        // Define the request payload with the pet details
        String requestBody = "{\n" +
                "  \"name\": \"pegion\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"https://www.google.com/url?sa=i&url=https%3A%2F%2Fwallpapersafari.com%2Fw%2FFK1LVx&psig=AOvVaw2TzFJxTSZITaT72LX2f8om&ust=1685520650995000&source=images&cd=vfe&ved=0CBEQjRxqFwoTCLiJs-_LnP8CFQAAAAAdAAAAABAE\"\n" +
                "  ],\n" +
                "  \"id\": \"941107\",\n" +
                "  \"category\": {\n" +
                "    \"id\": \"987654321\",\n" +
                "    \"name\": \"bird\"\n" +
                "  },\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": \"123\",\n" +
                "      \"name\": \"excellent\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

        // Set the request body
        request.body(requestBody);

        // Send the POST request to add the new pet with JSON data
        Response response = request.post("/pet");

        // Get the response status code
        int statusCode = response.getStatusCode();

        // Optionally, you can print the response body for further inspection
        String responseBody = response.getBody().asString();
        System.out.println("Response body: " + responseBody);

        // Check the status code to confirm the pet was added successfully
        if (statusCode == 200) {
            System.out.println("Pet added successfully!");

            // Find pet by ID
            int petId = 941107;
            Response findPetByIdResponse = RestAssured.given()
                    .pathParam("petId", petId)
                    .get("/pet/{petId}");

            // Print find pet by ID response
            System.out.println("Find Pet by ID Response:");
            findPetByIdResponse.prettyPrint();
        } else {
            System.out.println("Failed to add the pet. Status code: " + statusCode);
        }
    }
}
