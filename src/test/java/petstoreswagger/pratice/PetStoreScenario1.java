package petstoreswagger.pratice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PetStoreScenario1 {
    private RequestSpecification request;

    @BeforeClass
    public void setup() {
        // Set the base URI for the API
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        // Create the request specification
        request = RestAssured.given();
    }

    @Test(priority = 1)
    public void addPetToStore() {
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

        // Print the response body
        String responseBody = response.getBody().asString();
        System.out.println("Add Pet to Store Response Body:");
        System.out.println(responseBody);

        // Check the status code to confirm the pet was added successfully
        Assert.assertEquals(statusCode, 200, "Failed to add the pet. Status code: " + statusCode);
    }

    @Test(priority = 2)
    public void findPetById() {
        int petId = 941107;

        // Send the GET request to find the pet by ID
        Response response = RestAssured.given()
                .pathParam("petId", petId)
                .get("/pet/{petId}");

        // Get the response status code
        int statusCode = response.getStatusCode();

        // Print the response body
        String responseBody = response.getBody().asString();
        System.out.println("Find Pet by ID Response Body:");
        System.out.println(responseBody);

        // Check the status code to confirm the pet was found successfully
        Assert.assertEquals(statusCode, 200, "Failed to find the pet by ID. Status code: " + statusCode);
    }

    @Test(priority = 3)
    public void findPetByStatus() {
        String status = "available";

        // Send the GET request to find pets by status
        Response response = RestAssured.given()
                .queryParam("status", status)
                .get("/pet/findByStatus");

        // Get the response status code
        int statusCode = response.getStatusCode();

        // Print the response body
        String responseBody = response.getBody().asString();
        System.out.println("Find Pet by Status Response Body:");
        System.out.println(responseBody);

        // Check the status code to confirm pets were found successfully
        Assert.assertEquals(statusCode, 200, "Failed to find pets by status. Status code: " + statusCode);
    }
}
