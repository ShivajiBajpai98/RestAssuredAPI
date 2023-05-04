package equivalencepartitioning;

import org.json.JSONObject;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import io.restassured.RestAssured;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class EquivalencePartitioningTest {
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
    public void testEquivalencePartitions() {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        // Test valid input
        JSONObject requestParams = new JSONObject();
        requestParams.put("firstName", "Shivaji");
        requestParams.put("job", "Software Engineer");


        request.body(requestParams.toString());

        Response response = request.post("/api/users");
        response.prettyPrint();

        int statusCode = response.getStatusCode();

       // Assert.assertEquals(statusCode, 201);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(statusCode, 400);

        // Test invalid input
        requestParams.put("firstName", "");
        requestParams.put("job", "");


        request.body(requestParams.toString());

        Response response2 = request.post("/api/users");
        response2.prettyPrint();

      int  statusCode2 = response2.getStatusCode();
        //Assert.assertEquals(statusCode2, 400);
        softAssert.assertEquals(statusCode2, 400);
        softAssert.assertAll();
    }
}

