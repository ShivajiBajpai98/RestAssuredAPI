package petstoreswagger.project;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static org.hamcrest.Matchers.equalTo;

public class PetActions {
    private RequestSpecification request;

    public PetActions() {
        // Set the base URI for the API
        String baseUri = getProperty("baseUri");
        RestAssured.baseURI = baseUri;

        // Create the request specification
        request = RestAssured.given();
    }

    public String getProperty(String propertyName) {
        // Load the properties file
        Properties properties = new Properties();
        String configFilePath = "src" + File.separator + "test" + File.separator + "java" + File.separator +
                "petstoreswagger" + File.separator + "project" + File.separator + "configuring1.properties";
        try {
            FileInputStream fis = new FileInputStream(configFilePath);
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Read the property value
        return properties.getProperty(propertyName);
    }

    public void addPetToStore() {
        // Set the request headers and content type
        request.header("Content-Type", "application/json");

        // Read the request payload from the JSON file
        String requestPayloadPath = "src" + File.separator + "test" + File.separator + "java" + File.separator +
                "petstoreswagger" + File.separator + "project" + File.separator + "requestPayload.json";
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

    public void findPetById() {
        int petId = 941107;

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
        response.then().body("id", equalTo(petId));
    }

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
        response.then().body("status", Matchers.everyItem(equalTo(status)));
    }

    public void uploadFile() {
        int petId = 941107;
        String filePath = "src" + File.separator + "test" + File.separator + "java" + File.separator +
                "petstoreswagger" + File.separator + "project" + File.separator + "dog.jpg";

        // Set the request content type as multipart form data
        request.header("Content-Type", "multipart/form-data");

        // Send the POST request to upload the file for the pet
        Response response = request
                .multiPart("file", new File(filePath))
                .pathParam("petId", petId)
                .post("/pet/{petId}/uploadImage");

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

    public void updatePet() {
        int petId = 941107;

        // Set the request headers and content type
        request.header("Content-Type", "application/json");

        // Read the request payload from the JSON file
        String requestPayloadPath = "src" + File.separator + "test" + File.separator + "java" + File.separator +
                "petstoreswagger" + File.separator + "project" + File.separator + "updatePayload.json";
        String requestBody = null;
        try {
            requestBody = new String(Files.readAllBytes(Paths.get(requestPayloadPath)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set the request body
        request.body(requestBody);

        // Send the PUT request to update the pet with JSON data
        Response response = request.put("/pet/" + petId);

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

    public void deletePet() {
        int petId = 941107;

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
        response.then().body("message", equalTo(String.valueOf(petId)));
    }
}
