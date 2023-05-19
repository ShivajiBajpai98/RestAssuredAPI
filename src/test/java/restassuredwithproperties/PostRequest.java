package restassuredwithproperties;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequest {
    private Properties prop;

    @BeforeTest
    public void loadProperties() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
        prop.load(fis);
    }

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = prop.getProperty("baseURI");
    }

    @Test
    public void testPostRequest() {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        // Create a JSON object for request parameters
        JSONObject requestParams = new JSONObject();
        requestParams.put("firstName", "Shivaji");
        requestParams.put("job", "Software engineer");

        // Set the request body
        request.body(requestParams.toString());

        // Send POST request
        Response response = request.post("/api/users");
        response.prettyPrint();

        // Verify the status code
        int statusCode = response.getStatusCode();
        System.out.println("Status code: " + statusCode);
        Assert.assertEquals(statusCode, 201);
    }
}
