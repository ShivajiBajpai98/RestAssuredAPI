package petstoreswagger.pratice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PetStoreTest3 {

    private String baseUrl;
    private long petId;
    private String name;
    private String status;
    private String payloadFilePath;
    private String updatePayloadFilePath;
    private String imageFilePath;

    @BeforeClass
    public void loadProperties() throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src/test/java/petstoreswagger/finalproj/test.properties");
        properties.load(fileInputStream);

        baseUrl = properties.getProperty("base.url");
        petId = Long.parseLong(properties.getProperty("pet.id"));
        name = properties.getProperty("name");
        status = properties.getProperty("status");
        payloadFilePath = properties.getProperty("payload.file.path");
        updatePayloadFilePath = properties.getProperty("update.payload.file.path");
        imageFilePath = properties.getProperty("image.file.path");
    }

    @Test
    public void addNewPetToStore() throws IOException {
        String requestPayload = new String(Files.readAllBytes(Paths.get(payloadFilePath)));

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestPayload)
                .post(baseUrl);

        int statusCode = response.getStatusCode();
        System.out.println("Add New Pet to Store - Status Code: " + statusCode);

        String responseBody = response.getBody().asString();
        System.out.println("Add New Pet to Store - Response Body:");
        System.out.println(responseBody);

        response.then().statusCode(200);
    }

    @Test
    public void uploadImageWithPetId() {
        String additionalMetadata = "Additional data to pass to the server";
        String filePath = "src/test/java/petstoreswagger/finalproj/Cute_dog.jpg";

        Response response = RestAssured.given()
                .multiPart("petId", petId)
                .multiPart("additionalMetadata", additionalMetadata)
                .multiPart("file", new File(filePath))
                .post(baseUrl + "/v2/pet/" + petId + "/uploadImage");

        int statusCode = response.getStatusCode();
        System.out.println("Upload Image with Pet ID - Status Code: " + statusCode);

        String responseBody = response.getBody().asString();
        System.out.println("Upload Image with Pet ID - Response Body:");
        System.out.println(responseBody);

        response.then().statusCode(200);
    }

    @Test
    public void getPetById() {
        Response response = RestAssured.given()
                .pathParam("petId", petId)
                .get(baseUrl + "/{petId}");

        int statusCode = response.getStatusCode();
        System.out.println("Get Pet by ID - Status Code: " + statusCode);

        String responseBody = response.getBody().asString();
        System.out.println("Get Pet by ID - Response Body:");
        System.out.println(responseBody);

        response.then().statusCode(200);
    }

    @Test
    public void updatePetInStoreWithFormData() {
        String name = "New Doggie";
        String status = "updated";

        Response response = RestAssured.given()
                .contentType(ContentType.URLENC)
                .formParam("name", name)
                .formParam("status", status)
                .post(baseUrl + "/" + petId);

        int statusCode = response.getStatusCode();
        System.out.println("Update Pet in Store with Form Data - Status Code: " + statusCode);

        String responseBody = response.getBody().asString();
        System.out.println("Update Pet in Store with Form Data - Response Body:");
        System.out.println(responseBody);

        response.then().statusCode(200);
    }

    /*@Test
    public void deletePetById() {
        Response response = RestAssured.given()
                .pathParam("petId", petId)
                .delete(baseUrl + "/{petId}");

        int statusCode = response.getStatusCode();
        System.out.println("Delete Pet by ID - Status Code: " + statusCode);

        String responseBody = response.getBody().asString();
        System.out.println("Delete Pet by ID - Response Body:");
        System.out.println(responseBody);

        response.then().statusCode(200);
    }*/
}
