package cookiesandheaders;

import io.restassured.http.Cookie;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class Cookiens {

    private static final String COOKIE_NAME = "AEC";
   // private static final String COOKIE_VALUE = "AUEFqZfIFJSNhkhCw4N2t4qYTyVLkCyUgxB9VbArGjpDFaS52I4U0Yo50sA";

  /*  @Test
    void testSetCookie() {
        given()
                .when()
                .get("https://www.google.com/")
                .then()
                .cookie(COOKIE_NAME, COOKIE_VALUE)
                .log().all();
    }*/

    @Test
    void testGetCookiesInfo() {

        Response response = given().when().get("https://www.google.com/");

        // Single cookie info
        String cookieValue = response.getCookie(COOKIE_NAME);
        System.out.println("The value of Cookie is: " + cookieValue);

        // all cookies info
        Map<String, String> cookiesValue = response.getCookies();
        System.out.println(cookiesValue.keySet());
        for (String key : cookiesValue.keySet()) {
            String cookiesValueString = response.getCookie(key);
            System.out.println("key: " + key + " " + "cookiesValueString: " + cookiesValueString);
        }

    }
}
