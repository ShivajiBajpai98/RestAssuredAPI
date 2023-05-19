package factorydesign.apiclasses;

import factorydesign.apiinterface.ApiTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Post implements ApiTest {
    @Override
    public void execute() {
        // Create a RequestSpecification object
        RequestSpecification request = given();

        // Set the content type to JSON
        request.contentType(ContentType.JSON);

        // Set the request body as JSON
        request.body("{\"name\":\"John\", \"job\":\"Engineer\"}");

        // Send the POST request to the specified URL
        Response response = request.post("https://reqres.in/api/users");

        // Print the response body for visualization
        response.prettyPrint();
    }
}
