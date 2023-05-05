package enhanchedboundaryvalueanalysis;




import org.json.JSONObject;
        import org.testng.Assert;
        import org.testng.annotations.DataProvider;
        import org.testng.annotations.Test;
        import io.restassured.RestAssured;
        import io.restassured.response.Response;
        import io.restassured.specification.RequestSpecification;
        import java.io.File;
        import java.io.IOException;
        import java.nio.file.Files;
        import java.nio.file.Paths;

  public class TestBoundaryBetweenValidAndInvalidInput extends BaseTest {
    @Test(dataProvider = "userData")
    public void testPostRequest(JSONObject user) {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(user.toString());

        Response response = request.post("/api/users");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201);


        String responseBody = response.getBody().asString();
        System.out.println("Response Body is =>  " + responseBody);
    }

    @DataProvider(name = "userData")
    public Object[][] userData() throws IOException {
        String jsonPath = "src" + File.separator + "test" + File.separator + "resources" + File.separator + "test.json";

        String jsonContent = new String(Files.readAllBytes(Paths.get(jsonPath)));
        JSONObject jsonObject = new JSONObject(jsonContent);
        return new Object[][]{{jsonObject}};
    }
}

