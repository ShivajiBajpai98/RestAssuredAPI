package enhancedequivalencepartitioning;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EquivalencePartitioningInvalidData extends BaseTest {

        // Test invalid input
        @Test(dataProvider = "userData")
        public void testBoundaryValuesInvalidData (JSONObject user)
        {
            System.out.println("Test lower boundary value");
            RequestSpecification request = RestAssured.given();
            request.header("Content-Type", "application/json");
            request.body(user.toString());

            Response response = request.post("/api/users");

            int statusCode = response.getStatusCode();
            Assert.assertEquals(statusCode, 400);


            String responseBody = response.getBody().asString();
            System.out.println("Response Body is =>  " + responseBody);
        }

        @DataProvider(name = "userData")
        public Object[][] userData ()  {
            String jsonPath = "src" + File.separator + "test" + File.separator + "resources" + File.separator + "testLowerBoundaryValue.json";

            String jsonContent = null;
            try {
                jsonContent = new String(Files.readAllBytes(Paths.get(jsonPath)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            JSONObject jsonObject = new JSONObject(jsonContent);
            return new Object[][]{{jsonObject}};
        }
    }
