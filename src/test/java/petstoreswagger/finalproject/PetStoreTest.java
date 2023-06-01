package petstoreswagger.finalproject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PetStoreTest extends BaseTest {

   // @Test
    public void addNewPetToStore() throws IOException {
        test = extent.createTest("Add New Pet to Store");
        String requestPayload = new String(Files.readAllBytes(Paths.get(payloadFilePath)));

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestPayload)
                .post(baseUrl);

        validateResponse(response, 200);

        logInfo("Status Code: " + response.getStatusCode());
        logInfo("Response Body: " + response.getBody().asString());

        response.then().statusCode(200);
        test.pass("Add New Pet to Store - Test Passed");
    }

  //  @Test
    public void uploadImageWithPetId() {
        test = extent.createTest("Upload Image with Pet ID");
        String additionalMetadata = "Additional data to pass to the server";
        String filePath = "src" + File.separator + "test" + File.separator + "java" + File.separator + "petstoreswagger" + File.separator + "finalproj" + File.separator + "Cute_dog.jpg";

        Response response = RestAssured.given()
                .multiPart("petId", petId)
                .multiPart("additionalMetadata", additionalMetadata)
                .multiPart("file", new File(filePath))
                .post(baseUrl + "/" + petId + "/uploadImage");

        validateResponse(response, 200);

        logInfo("Status Code: " + response.getStatusCode());
        logInfo("Response Body: " + response.getBody().asString());

        response.then().statusCode(200);
        test.pass("Upload Image with Pet ID - Test Passed");
    }

   // @Test
    public void getPetById() {
        test = extent.createTest("Get Pet by ID");
        Response response = RestAssured.given()
                .pathParam("petId", petId)
                .get(baseUrl + "/{petId}");

        validateResponse(response, 200);

        logInfo("Status Code: " + response.getStatusCode());
        logInfo("Response Body: " + response.getBody().asString());

        response.then().statusCode(200);
        test.pass("Get Pet by ID - Test Passed");
    }

   // @Test
    public void updatePetInStoreWithFormData() throws IOException {
        test = extent.createTest("Update Pet in Store with Form Data");

        String filePath = "src" + File.separator + "test" + File.separator + "java" + File.separator + "petstoreswagger" + File.separator + "finalproj" + File.separator + "updatepayload.json";
        String requestPayload = new String(Files.readAllBytes(Paths.get(filePath)));

        Response response = RestAssured.given()
                .contentType(ContentType.URLENC)
                .body(requestPayload)
                .post(baseUrl + "/" + petId);

        validateResponse(response, 200);

        logInfo("Status Code: " + response.getStatusCode());
        logInfo("Response Body: " + response.getBody().asString());

        response.then().statusCode(200);
        test.pass("Update Pet in Store with Form Data - Test Passed");
    }

   // @Test
    public void deletePetById() {
        test = extent.createTest("Delete Pet by ID");
        Response response = RestAssured.given()
                .pathParam("petId", petId)
                .delete(baseUrl + "/{petId}");

        validateResponse(response, 200);

        logInfo("Status Code: " + response.getStatusCode());
        logInfo("Response Body: " + response.getBody().asString());

        response.then().statusCode(200);
        test.pass("Delete Pet by ID - Test Passed");
    }
}
