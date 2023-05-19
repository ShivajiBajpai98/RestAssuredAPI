package regexpression;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserApiTest {

    @Test
    public void testGetUsers() {
        // Send GET request to retrieve user data
        Response response = RestAssured.get("https://reqres.in/api/users?page=2");

        // Get the status code from the response
        int statusCode = response.getStatusCode();

        // Assert the status code
        Assert.assertEquals(statusCode, 200);

        // Get the number of users in the response
        int numOfUsers = response.path("data.size()");
        System.out.println("Number of users: " + numOfUsers);


    }
}
