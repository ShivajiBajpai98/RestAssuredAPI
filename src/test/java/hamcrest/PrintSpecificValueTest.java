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
        int id = 12; // Specify the ID of the user whose details you want to print
        String key = "email"; // Specify the key for the value you want to print
        String expectedValue = "rachel.howell@reqres.in"; // Specify the expected value of the key

        // Send a GET request to the specified URL and retrieve the response
        Response response = RestAssured.get("https://reqres.in/api/users?page=2");
        // Extract the JSON response body using JsonPath
        JsonPath jsonPath = response.jsonPath();

        // Get the actual value from the JSON response based on the provided ID and key
        String actualValue = jsonPath.get("data.find { it.id == " + id + " }." + key);

        // Assert that the actual value matches the expected value using Hamcrest matcher
        assertThat(actualValue, Matchers.equalTo(expectedValue));

        // Print the key value for the user with the specified ID
        System.out.println(key + " value for user with ID " + id + ": " + actualValue);
    }
}
