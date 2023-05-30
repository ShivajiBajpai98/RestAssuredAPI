package petstoreswagger.pratice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PetStoreTest {
    private int petId;

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @Test(priority = 1)
    public void addNewPetWithImage() {
        String requestBody = "{ \"name\": \"pegion\", \"photoUrls\": [\"https://www.google.com/url?sa=i&url=https%3A%2F%2Fwallpapersafari.com%2Fw%2FFK1LVx&psig=AOvVaw2TzFJxTSZITaT72LX2f8om&ust=1685520650995000&source=images&cd=vfe&ved=0CBEQjRxqFwoTCLiJs-_LnP8CFQAAAAAdAAAAABAE\"], \"id\": \"941107\", \"category\": { \"id\": \"987654321\", \"name\": \"bird\" }, \"tags\": [ { \"id\": \"123\", \"name\": \"excellent\" } ], \"status\": \"available\" }";

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("/pet");

        int statusCode = response.getStatusCode();

        Assert.assertEquals(statusCode, 200, "Failed to add the pet. Status code: " + statusCode);

        petId = 941107;
    }

    @Test(priority = 2)
    public void findPetById() {
        Response response = RestAssured.given()
                .pathParam("petId", petId)
                .get("/pet/{petId}");

        int statusCode = response.getStatusCode();

        String responseBody = response.getBody().asString();
        System.out.println("Find Pet by ID Response Body:");
        System.out.println(responseBody);

        Assert.assertEquals(statusCode, 200, "Failed to find the pet by ID. Status code: " + statusCode);

        String imageUrl = response.jsonPath().getString("photoUrls[0]");

        System.out.println("Uploaded Image URL: " + imageUrl);
    }

    @AfterClass
    public void tearDown() {
        // Clean up by deleting the added pet
        Response response = RestAssured.given()
                .pathParam("petId", petId)
                .delete("/pet/{petId}");

        int statusCode = response.getStatusCode();

        Assert.assertEquals(statusCode, 200, "Failed to delete the pet. Status code: " + statusCode);
    }
}
