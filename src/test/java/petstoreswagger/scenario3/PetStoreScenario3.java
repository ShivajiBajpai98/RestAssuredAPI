package petstoreswagger.scenario3;

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

import static org.hamcrest.Matchers.equalTo;

public class PetStoreScenario3 {
    private RequestSpecification request;
    private String baseUri;
    private Properties properties;
    private int petId;

    @BeforeClass
    public void setup() {
        // Load the properties file
        properties = new Properties();
        String configFilePath = "src" + File.separator + "test" + File.separator + "java" + File.separator + "petstoreswagger" + File.separator + "scenario3" + File.separator + "configscenario3.properties";
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
        String requestPayloadPath = "src" + File.separator + "test" + File.separator + "java" + File.separator + "petstoreswagger" + File.separator + "scenario3" + File.separator + "pet.json";
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

        // Extract the pet ID from the response
        petId = response.then().extract().path("id");

        // Check the status code to confirm the pet was added successfully
        response.then().statusCode(200);
    }

    @Test(priority = 2)
    public void uploadFile() {
        String filePath = "src" + File.separator + "test" + File.separator + "java" + File.separator + "petstoreswagger" + File.separator + "scenario3" + File.separator + "pegion.jpg";

        // Set the request content type as multipart form data
        request.header("Content-Type", "multipart/form-data");

        // Set the pet ID as a path parameter
        request.pathParam("petId", petId);

        // Set the file to be uploaded
        request.multiPart(new File(filePath));

        // Send the POST request to upload the file for the pet
        Response response = request.post("/pet/{petId}/uploadImage");

        // Get the response status code
        int statusCode = response.getStatusCode();
        System.out.println("Upload File - Status Code: " + statusCode);

        // Print the response body
        String responseBody = response.getBody().asString();
        System.out.println("Upload File - Response Body:");
        System.out.println(responseBody);

        // Check the status code to confirm the file was uploaded successfully
        response.then().statusCode(200);
        response.then().body("message", Matchers.containsString("File uploaded"));
    }

    @Test(priority = 3)
    public void updatePet() {
        // Read the update payload from the JSON file
        String updatePayloadPath = "src" + File.separator + "test" + File.separator + "java" + File.separator + "petstoreswagger" + File.separator + "scenario3" + File.separator + "updatePayload.json";
        String updateBody = null;
        try {
            updateBody = new String(Files.readAllBytes(Paths.get(updatePayloadPath)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create a new RequestSpecification instance
        RequestSpecification updateRequest = RestAssured.given();

        // Set the base URI for the API
        updateRequest.baseUri(baseUri);

        // Set the request headers and content type
        updateRequest.header("Content-Type", "application/json");

        // Set the request body
        updateRequest.body(updateBody);

        // Send the PUT request to update the pet
        // Send the PUT request to update the pet
        Response response = request.put("/pet");

        // Get the response status code
        int statusCode = response.getStatusCode();
        System.out.println("Update Pet - Status Code: " + statusCode);

        // Print the response body
        String responseBody = response.getBody().asString();
        System.out.println("Update Pet - Response Body:");
        System.out.println(responseBody);

        // Check the status code to confirm the pet was updated successfully
        response.then().statusCode(200);
    }




    @Test(priority = 4)
    public void deletePet() {
        // Send the DELETE request to delete the pet by ID
        Response response = RestAssured.given()
                .pathParam("petId", petId)
                .delete("/pet/{petId}");

        // Get the response status code
        int statusCode = response.getStatusCode();
        System.out.println("Delete Pet - Status Code: " + statusCode);

        // Print the response body
        String responseBody = response.getBody().asString();
        System.out.println("Delete Pet - Response Body:");
        System.out.println(responseBody);

        // Check the status code to confirm the pet was deleted successfully
        response.then().statusCode(200);
    }
}
