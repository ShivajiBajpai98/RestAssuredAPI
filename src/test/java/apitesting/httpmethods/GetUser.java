package apitesting.httpmethods;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetUser {
    @Test
    public void test() {
        Response response = get("https://reqres.in/api/users?page=2");
        System.out.println(response.getStatusCode());
        System.out.println(response.getTime());
        System.out.println(response.getBody().prettyPrint());
        System.out.println("Status Line: " + response.getStatusLine());

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void test2() {
        baseURI = "https://reqres.in/";
        get("api/users?page=2")
                .then()
                .statusCode(200);
    }
}
