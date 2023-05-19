package cookies;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class Cookies {

    private static final String COOKIE_NAME = "AEC";

    @Test
    public void testGetCookiesInfo() {
        // Send a GET request to the specified URL
        Response response = given().when().get("https://www.google.com/");

        // Retrieve the value of a single cookie
        String cookieValue = response.getCookie(COOKIE_NAME);
        System.out.println("The value of Cookie " + COOKIE_NAME + " is: " + cookieValue);

        // Retrieve information about all cookies
        Map<String, String> cookiesValue = response.getCookies();
        System.out.println("All cookies:");

        // Iterate through each cookie and print its key-value pair
        for (String key : cookiesValue.keySet()) {
            String cookieValueString = response.getCookie(key);
            System.out.println("Key: " + key + ", Value: " + cookieValueString);
        }
    }
}
