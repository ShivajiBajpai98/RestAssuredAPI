package restassuredwithproperties;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PutRequest {
    private Properties prop;

    @BeforeTest
    public void loadProperties() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream("src/test/resources/configuring1.properties");
        prop.load(fis);
    }

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = prop.getProperty("baseURI");
    }

    @Test
    public void testPutRequest() {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        // Create a JSON object for request parameters
        JSONObject requestParams = new JSONObject();
        requestParams.put("firstName", "Shivaji");
        requestParams.put("job", "Engineer");

        // Set the request body
        request.body(requestParams.toString());

        // Send PUT request
        Response response = request.put("/api/users/2");
        response.prettyPrint();

        // Verify the status code
        int statusCode = response.getStatusCode();
        System.out.println("Status Code: " + statusCode);
        Assert.assertEquals(statusCode, 200);
    }
}
