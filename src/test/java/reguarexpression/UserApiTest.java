package reguarexpression;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserApiTest {

    @Test
    public void testGetUsers() {
        Response response = RestAssured. get("https://reqres.in/api/users?page=2");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);

        // Assert the number of users
        int numOfUsers = response.path("data.size()");
        System.out.println(numOfUsers);
       /* Assert.assertEquals(numOfUsers, 3);

        // Assert the properties of each user
        Assert.assertEquals((Double) response.path("data[0].id"), 1);
        Assert.assertEquals(response.path("data[0].email"), "george.bluth@reqres.in");
        Assert.assertEquals(response.path("data[0].first_name"), "George");
        Assert.assertEquals(response.path("data[0].last_name"), "Bluth");
        //Assert.assertTrue(response.path("data[0].avatar").contains(".jpg"));*/
    }
}
