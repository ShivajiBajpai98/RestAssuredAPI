package apitesting.httpmethods;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetUser {
    @Test
    public void testGetUser() {
        // Test method to perform a GET request and validate the response

        Response response = get("https://reqres.in/api/users?page=2");

        // Print the status code, response time, and formatted response body
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Time: " + response.getTime());
        System.out.println("Response Body:");
        System.out.println(response.getBody().prettyPrint());
        System.out.println("Status Line: " + response.getStatusLine());

        // Validate the status code using TestNG assertion
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void testGetUserWithAssertion() {
        // Test method to perform a GET request and validate the response using RestAssured assertions

        baseURI = "https://reqres.in/";

        get("api/users?page=2")
                .then()
                .statusCode(200);
    }
}
