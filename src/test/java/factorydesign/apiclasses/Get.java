package factorydesign.apiclasses;

import factorydesign.apiinterface.ApiTest;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.get;

public class Get implements ApiTest {
    @Override
    public void Response() {
        String url="https://reqres.in/api/users?page=2";
        Response response= get(url);
        System.out.println(response.getStatusCode());
        System.out.println(response.getTime());
        System.out.println(response.getBody().prettyPrint());
        System.out.println("Status Line: "+response.getStatusLine());

        int statusCode=response.getStatusCode();
        Assert.assertEquals(statusCode,200);


    }
    }

