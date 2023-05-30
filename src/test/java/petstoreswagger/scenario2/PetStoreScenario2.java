package petstoreswagger.scenario2;

import static org.hamcrest.Matchers.equalTo;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PetStoreScenario2 {
    private RequestSpecification request;
    private String baseUri;
    private Properties properties;

    @BeforeClass
    public void setup() {
        // Load the properties file
        properties = new Properties();
        String configFilePath = "src" + File.separator + "test" + File.separator + "java" + File.separator + "petstoreswagger" + File.separator + "scenario2" + File.separator + "configscenario3.properties";
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(configFilePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Read the baseUri from the properties file
        baseUri = properties.getProperty("baseUri");

        // Set the base URI for the API
        RestAssured.baseURI = baseUri;

        // Create the request specification
        request = RestAssured.given();
    }

    @Test(priority = 1)
    public void addPetToStore() {
        // Set the request headers and content type
        request.header("Content-Type", "application/json");

        // Read the request payload from the JSON file
        String requestPayloadPath = "src" + File.separator + "test" + File.separator + "java" + File.separator + "petstoreswagger" + File.separator + "scenario2" + File.separator + "pet.json";
        String requestBody = null;
        try {
            requestBody = new String(Files.readAllBytes(Paths.get(requestPayloadPath)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set the request body
        request.body(requestBody);

        // Send the POST request to add the new pet with JSON data
        Response response = request.post("/pet");

        // Get the response status code
        int statusCode = response.getStatusCode();
        System.out.println("Add Pet to Store - Status Code: " + statusCode);

        // Print the response body
        String responseBody = response.getBody().asString();
        System.out.println("Add Pet to Store - Response Body:");
        System.out.println(responseBody);

        // Check the status code to confirm the pet was added successfully
        response.then().statusCode(200);
    }

    @Test(priority = 2, dependsOnMethods = "addPetToStore")
    public void findPetById() {
        // Get the pet ID from the previous response or use a specific ID
        int petId = 1221;

        // Send the GET request to find the pet by ID
        Response response = RestAssured.given()
                .pathParam("petId", petId)
                .get("/pet/{petId}");

        // Get the response status code
        int statusCode = response.getStatusCode();
        System.out.println("Find Pet by ID - Status Code: " + statusCode);

        // Print the response body
        String responseBody = response.getBody().asString();
        System.out.println("Find Pet by ID - Response Body:");
        System.out.println(responseBody);

        // Check the status code to confirm the pet was found successfully
        response.then().statusCode(200);

        // Verify the pet ID in the response body
        response.then().body("id", equalTo(petId));
    }

    @Test(priority = 3, dependsOnMethods = "findPetById")
    public void findPetByStatus() {
        String status = "available";

        // Send the GET request to find pets by status
        Response response = RestAssured.given()
                .queryParam("status", status)
                .get("/pet/findByStatus");

        // Get the response status code
        int statusCode = response.getStatusCode();
        System.out.println("Find Pet by Status - Status Code: " + statusCode);

        // Print the response body
        String responseBody = response.getBody().asString();
        System.out.println("Find Pet by Status - Response Body:");
        System.out.println(responseBody);

        // Check the status code to confirm pets were found successfully
        response.then().statusCode(200);

        // Verify the status of all pets in the response body
        response.then().body("status", Matchers.everyItem(equalTo(status)));
    }

    @Test(priority = 4, dependsOnMethods = "findPetByStatus")
    public void deletePetFromStore() {
        // Get the pet ID from the previous response or use a specific ID
        int petId = 1221;

        // Send the DELETE request to delete the pet by ID
        Response response = RestAssured.given()
                .pathParam("petId", petId)
                .delete("/pet/{petId}");

        // Get the response status code
        int statusCode = response.getStatusCode();
        System.out.println("Delete Pet from Store - Status Code: " + statusCode);

        // Check the status code to confirm the pet was deleted successfully
        response.then().statusCode(200);

        // Verify the response body message
        response.then().body("message", Matchers.containsString("Pet deleted"));
    }
}
