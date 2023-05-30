package petstoreswagger.pratice;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.File;

public class PetStoreTests2 {

    @Test
    public void scenario1() {
        // Scenario 1: Pet Post - upload image, find pet by id (get), Get - find pet by status

        // Upload image
      /*  String imageUrl = "src/test/resources/petstore1.jpg";
        Response uploadResponse = RestAssured.given()
                .multiPart("file", new File(imageUrl))
                .post("https://petstore.swagger.io/v2/pet/{petId}/uploadImage", 461189304);

        // Print upload image response
        System.out.println("Upload Image Response:");
        uploadResponse.prettyPrint();*/

        // Find pet by ID
        int petId = 941107;
        Response findPetByIdResponse = RestAssured.given()
                .pathParam("petId", petId)
                .get("https://petstore.swagger.io/v2/pet/{petId}");

        // Print find pet by ID response
        System.out.println("Find Pet by ID Response:");
        findPetByIdResponse.prettyPrint();

      /*  // Find pet by status
        String status = "available";
        Response findPetByStatusResponse = RestAssured.given()
                .queryParam("status", status)
                .get("https://petstore.swagger.io/v2/pet/findByStatus");

        // Print find pet by status response
        System.out.println("Find Pet by Status Response:");
        findPetByStatusResponse.prettyPrint();*/

        // Assert and validate the responses as needed
    }
}
