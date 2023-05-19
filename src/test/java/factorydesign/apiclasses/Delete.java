package factorydesign.apiclasses;

import factorydesign.apiinterface.ApiTest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Delete implements ApiTest {
    @Override
    public void execute() {
        // Create a request specification
        RequestSpecification request = given();

        // Perform the DELETE request using RestAssured
        Response response = request.delete("https://reqres.in/api/users/2");

        // Print the response body for visualization
        response.prettyPrint();
    }
}
