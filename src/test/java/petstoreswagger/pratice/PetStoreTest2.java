package petstoreswagger.pratice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PetStoreTest2 {

    @Test
    public void addNewPetToStore() throws IOException {
        // Read the JSON payload from a file
        String payloadFilePath = "src/test/java/petstoreswagger/finalproj/payload.json";
        String requestPayload = new String(Files.readAllBytes(Paths.get(payloadFilePath)));

        // Send the POST request to add the new pet to the store with JSON data
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestPayload)
                .post("https://petstore.swagger.io/v2/pet");

        // Get the response status code
        int statusCode = response.getStatusCode();
        System.out.println("Add New Pet to Store - Status Code: " + statusCode);

        // Print the response body
        String responseBody = response.getBody().asString();
        System.out.println("Add New Pet to Store - Response Body:");
        System.out.println(responseBody);

        // Check the status code to confirm the pet was added successfully
        response.then().statusCode(200);
    }


    @Test
    public void uploadImageWithPetId() {
        // Set the petId and additionalMetadata values
        long petId = 12321;
        String additionalMetadata = "Additional data to pass to the server";

        // Set the file path for uploading
        String filePath = "src/test/java/petstoreswagger/finalproj/Cute_dog.jpg";

        // Send the POST request to upload the file
        Response response = RestAssured.given()
                .multiPart("petId", petId)
                .multiPart("additionalMetadata", additionalMetadata)
                .multiPart("file", new File(filePath))
                .post("https://petstore.swagger.io/v2/pet/" + petId + "/uploadImage");

        // Get the response status code
        int statusCode = response.getStatusCode();
        System.out.println("Upload Image with Pet ID - Status Code: " + statusCode);

        // Print the response body
        String responseBody = response.getBody().asString();
        System.out.println("Upload Image with Pet ID - Response Body:");
        System.out.println(responseBody);

        // Check the status code to confirm the file was uploaded successfully
        response.then().statusCode(200);
    }

    @Test
    public void getPetById() {
        // Set the petId value
        long petId = 12321;

        // Send the GET request to retrieve the pet by ID
        Response response = RestAssured.given()
                .pathParam("petId", petId)
                .get("https://petstore.swagger.io/v2/pet/{petId}");

        // Get the response status code
        int statusCode = response.getStatusCode();
        System.out.println("Get Pet by ID - Status Code: " + statusCode);

        // Print the response body
        String responseBody = response.getBody().asString();
        System.out.println("Get Pet by ID - Response Body:");
        System.out.println(responseBody);

        // Check the status code to confirm the pet was retrieved successfully
        response.then().statusCode(200);
    }

    @Test
    public void updatePetInStoreWithFormData() {
        // Set the petId, name, and status values
        long petId = 12321;
        String name = "New Doggie";
        String status = "updated";

        // Send the POST request to update the pet in the store with form data
        Response response = RestAssured.given()
                .contentType(ContentType.URLENC)
                .formParam("name", name)
                .formParam("status", status)
                .post("https://petstore.swagger.io/v2/pet/" + petId);

        // Get the response status code
        int statusCode = response.getStatusCode();
        System.out.println("Update Pet in Store with Form Data - Status Code: " + statusCode);

        // Print the response body
        String responseBody = response.getBody().asString();
        System.out.println("Update Pet in Store with Form Data - Response Body:");
        System.out.println(responseBody);

        // Check the status code to confirm the pet was updated successfully
        response.then().statusCode(200);
    }
    @Test
    public void updateExistingPet() throws IOException {
        // Read the JSON payload for updating an existing pet from a file
        String payloadFilePath = "src/test/java/petstoreswagger/finalproj/updatepayload.json";
        String requestPayload = new String(Files.readAllBytes(Paths.get(payloadFilePath)));

        // Send the PUT request to update the existing pet
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestPayload)
                .put("https://petstore.swagger.io/v2/pet");

        // Get the response status code
        int statusCode = response.getStatusCode();
        System.out.println("Update Existing Pet - Status Code: " + statusCode);

        // Print the response body
        String responseBody = response.getBody().asString();
        System.out.println("Update Existing Pet - Response Body:");
        System.out.println(responseBody);

        // Check the status code to confirm the pet was updated successfully
        response.then().statusCode(200);
    }
    @Test
    public void findPetsByStatus() {
        // Set the status value for filtering
        String status = "available";

        // Send the GET request to find pets by status
        Response response = RestAssured.given()
                .queryParam("status", status)
                .get("https://petstore.swagger.io/v2/pet/findByStatus");

        // Get the response status code
        int statusCode = response.getStatusCode();
        System.out.println("Find Pets by Status - Status Code: " + statusCode);

        // Print the response body
        String responseBody = response.getBody().asString();
        System.out.println("Find Pets by Status - Response Body:");
        System.out.println(responseBody);

        // Check the status code to confirm the pets were retrieved successfully
        response.then().statusCode(200);
    }

   @Test
    public void deletePetById() {
        // Set the petId value
        long petId = 12321;

        // Send the DELETE request to delete the pet by ID
        Response response = RestAssured.given()
                .pathParam("petId", petId)
                .delete("https://petstore.swagger.io/v2/pet/{petId}");

        // Get the response status code
        int statusCode = response.getStatusCode();
        System.out.println("Delete Pet by ID - Status Code: " + statusCode);

        // Check the status code to confirm the pet was deleted successfully
        response.then().statusCode(200);
    }
}









