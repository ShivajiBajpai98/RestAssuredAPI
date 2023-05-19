package factorydesign.apiclasses;

import factorydesign.apiinterface.ApiTest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.get;

public class Get implements ApiTest {
    @Override
    public void execute() {
        // Define the URL for the GET request
        String url = "https://reqres.in/api/users?page=2";

        // Perform the GET request using RestAssured
        Response response = get(url);

        // Print the response body for visualization
        response.prettyPrint();
    }
}
