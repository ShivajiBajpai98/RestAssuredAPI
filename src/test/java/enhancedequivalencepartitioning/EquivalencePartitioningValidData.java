package enhancedequivalencepartitioning;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EquivalencePartitioningValidData extends BaseTest {

    @Test(dataProvider = "userData")
    public void testUpperBoundaryValue(JSONObject user) {
        System.out.println("Test upper boundary value");
        request.body(user.toString());

        Response response = request.post("/api/users");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 400);

        String responseBody = response.getBody().asString();
        System.out.println("Response Body is =>  " + responseBody);
    }

    @DataProvider(name = "userData")
    public Object[][] userData() {
        String jsonPath = "src" + File.separator + "test" + File.separator + "resources" + File.separator + "testUpperBoundaryValue.json";
        return new Object[][]{{getJSONFromJSONFile(jsonPath)}};
    }

    private JSONObject getJSONFromJSONFile(String filePath) {
        String jsonContent = null;
        try {
            jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JSONObject(jsonContent);
    }
}
