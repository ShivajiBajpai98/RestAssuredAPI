package boundaryvalueanalysis;

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
import org.testng.asserts.SoftAssert;

public class BoundaryValueAnalysisTest {
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
    public void testBoundaryValues() {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        SoftAssert softAssert = new SoftAssert();

        // Test lower boundary value
        System.out.println("Test lower boundary value");
        JSONObject requestParams = new JSONObject();
        requestParams.put("firstName", "");
        requestParams.put("job", "");

        request.body(requestParams.toString());

        Response response = request.post("/api/users");


        int statusCode1 = response.getStatusCode();
        System.out.println("statusCode:"+statusCode1);
        //Assert.assertEquals(statusCode, 400);
        softAssert.assertEquals(statusCode1, 400);
        response.prettyPrint();
/////////////////////////////////////////////////////////////////////////////////

        // Test upper boundary value
        System.out.println("Test upper boundary value");
        requestParams.put("firstName", "1234567890123456789012345678901234567890123456789012345678901234567890");
        requestParams.put("job", "1234567890123456789012345678901234567890123456789012345678901234567890");

        request.body(requestParams.toString());

       Response response2 = request.post("/api/users");


       int statusCode2 = response2.getStatusCode();
        System.out.println("statusCode:"+statusCode2);
       // Assert.assertEquals(statusCode, 400);
        softAssert.assertEquals(statusCode2, 400);
        response2.prettyPrint();
////////////////////////////////////////////////////////////////////////////////////////////////


        // Test boundary between valid and invalid input
        System.out.println("Test boundary between valid and invalid input");
        requestParams.put("firstName", "Shivaji");
        requestParams.put("job", "Software Engineer");


        request.body(requestParams.toString());

       Response response3 = request.post("/api/users");
        int statusCode3 = response.getStatusCode();
        response.prettyPrint();


        //Assert.assertEquals(statusCode, 201);
        System.out.println("statusCode:"+statusCode3);
        softAssert.assertEquals(statusCode3, 201);
        response3.prettyPrint();
        softAssert.assertAll();
    }
}

