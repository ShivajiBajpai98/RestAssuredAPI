package restassuredwithproperties;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GetRequest {

    @Test
    public void getRequestTest() throws IOException {

        // Load properties file
        Properties prop = new Properties();
        FileInputStream input = new FileInputStream("src/test/resources/config.properties");
        prop.load(input);

        // Set base URI
        RestAssured.baseURI = prop.getProperty("baseURI");

        // Send GET request
        Response response = RestAssured.given()
                .when()
                .get(prop.getProperty("endpoint"))
                .then()
                .extract().response();

        // Verify the status code
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);

        // Verify response body contains expected data
        String responseBody = response.getBody().asString();
        System.out.println(responseBody);
        Assert.assertTrue(responseBody.contains(prop.getProperty("expectedData")));
    }
}
