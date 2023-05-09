package hamcrest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class PrintSpecificValueTest {

    @Test
    public void testPrintSpecificValue() {
        int id = 12; // specify the ID of the user whose details you want to print
        String key = "email"; // specify the key for the value you want to print
        String expectedValue = "rachel.howell@reqres.in"; // specify the expected value of the key

        Response response = RestAssured.get("https://reqres.in/api/users?page=2");
        JsonPath jsonPath = response.jsonPath();

        String actualValue = jsonPath.get("data.find { it.id == " + id + " }." + key);
        assertThat(actualValue, Matchers.equalTo(expectedValue));
        System.out.println(key + " value for user with ID " + id + ": " + actualValue);
    }
}
