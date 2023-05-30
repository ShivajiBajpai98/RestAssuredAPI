package petstoreswagger.proj;

import org.testng.annotations.Test;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;



    public class PetStoreAPITests extends TestBase {

        @Test
        public void addPetToStore() throws IOException {
            // Read the request body from a JSON file
            String requestBody = new String(Files.readAllBytes(Paths.get("src/test/java/petstoreswagger/proj/pet.json")));

            // Send the request to add the pet to the store
            Response addPetResponse = given()
                    .header("Content-Type", "application/json")
                    .body(requestBody)
                    .post("/pet");

            // Assert the response status code is 200 (OK)
            assertEquals(addPetResponse.getStatusCode(), 200);

    }

        @Test
        public void scenario1() {
            // Upload Image
            String imageUrl = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fpixabay.com%2Fimages%2Fsearch%2Fcat%2F&psig=AOvVaw1yARQ_t2_sapfus_KwAWvK&ust=1685547943288000&source=images&cd=vfe&ved=0CBEQjRxqFwoTCNiqrvCxnf8CFQAAAAAdAAAAABAE";
            Response uploadImageResponse = given()
                    .multiPart("file", imageUrl)
                    .post("/pet/{petId}/uploadImage", 123454321);

            assertEquals(uploadImageResponse.getStatusCode(), 200);
            uploadImageResponse.prettyPrint(); // Print the response

            // Find Pet by ID (GET)
            RequestSpecification request = given();
            Response findPetByIdResponse = request.get("/pet/{petId}", 123454321);

            assertEquals(findPetByIdResponse.getStatusCode(), 200);
            findPetByIdResponse.prettyPrint(); // Print the response

            // Get - Find Pet by Status
            Response findPetByStatusResponse = given()
                    .queryParam("status", "available")
                    .get("/pet/findByStatus");

            assertEquals(findPetByStatusResponse.getStatusCode(), 200);
            findPetByStatusResponse.prettyPrint(); // Print the response
        }


   /* @Test
    public void scenario2() {
        // Add a Pet to the Store
        String requestBody = "{\"id\": 12345, \"name\": \"Buddy\", \"status\": \"available\"}";
        Response addPetResponse = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post("/pet");

        assertEquals(addPetResponse.getStatusCode(), 200);
        // Add assertions for pet creation if needed

        // Find Pet by ID
        RequestSpecification request = given();
        Response findPetByIdResponse = request.get("/pet/{petId}", 12345);

        assertEquals(findPetByIdResponse.getStatusCode(), 200);
        // Add assertions for pet details if needed

        // Find Pet by Status
        Response findPetByStatusResponse = given()
                .queryParam("status", "available")
                .get("/pet/findByStatus");

        assertEquals(findPetByStatusResponse.getStatusCode(), 200);
        // Add assertions for pet status if needed

        // Delete Pet
        Response deletePetResponse = given()
                .delete("/pet/{petId}", 12345);

        assertEquals(deletePetResponse.getStatusCode(), 200);
        // Add assertions for pet deletion if needed
    }

    @Test
    public void scenario3() {
        // Add a Pet
        String requestBody = "{\"id\": 12345, \"name\": \"Buddy\", \"status\": \"available\"}";
        Response addPetResponse = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post("/pet");

        assertEquals(addPetResponse.getStatusCode(), 200);
        // Add assertions for pet creation if needed

        // Upload Images
        String imageUrl1 = "https://example.com/image1.jpg";
        String imageUrl2 = "https://example.com/image2.jpg";

        Response uploadImage1Response = given()
                .multiPart("file", imageUrl1)
                .post("/pet/{petId}/uploadImage", 12345);

        assertEquals(uploadImage1Response.getStatusCode(), 200);
        // Add assertions for image1 upload if needed

        Response uploadImage2Response = given()
                .multiPart("file", imageUrl2)
                .post("/pet/{petId}/uploadImage", 12345);

        assertEquals(uploadImage2Response.getStatusCode(), 200);
        // Add assertions for image2 upload if needed

        // Update an Existing Pet
        String updateRequestBody = "{\"name\": \"BuddyUpdated\", \"status\": \"sold\"}";
        Response updatePetResponse = given()
                .header("Content-Type", "application/json")
                .body(updateRequestBody)
                .put("/pet");

        assertEquals(updatePetResponse.getStatusCode(), 200);
        // Add assertions for pet update if needed

        // Delete Pet
        Response deletePetResponse = given()
                .delete("/pet/{petId}", 12345);

        assertEquals(deletePetResponse.getStatusCode(), 200);
        // Add assertions for pet deletion if needed
    }*/
}
