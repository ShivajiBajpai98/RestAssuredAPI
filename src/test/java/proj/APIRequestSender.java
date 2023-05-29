package proj;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

public class APIRequestSender {

    /**
     * Sends a GET request to the specified URL with the provided headers, username, and password.
     *
     * @param url      the URL to send the request to
     * @param headers  the headers to include in the request
     * @param username the username for basic authentication
     * @param password the password for basic authentication
     * @return the response received from the GET request
     */
    @Test
    public static Response get(String url, Map<String, String> headers, String username, String password) {
        return RestAssured.given()
                .baseUri(url)
                .auth().basic(username, password)
                .headers(headers)
                .contentType(ContentType.JSON)
                .when()
                .get()
                .then()
                .extract().response();
    }

    /**
     * Sends a POST request to the specified URL with the provided body, headers, username, and password.
     *
     * @param url      the URL to send the request to
     * @param body     the body of the request
     * @param headers  the headers to include in the request
     * @param username the username for basic authentication
     * @param password the password for basic authentication
     * @return the response received from the POST request
     */
    @Test
    public static Response post(String url, String body, Map<String, String> headers, String username, String password) {
        return RestAssured.given()
                .baseUri(url)
                .auth().basic(username, password)
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post()
                .then()
                .extract().response();
    }

    /**
     * Sends a PUT request to the specified URL with the provided body, headers, username, and password.
     *
     * @param url      the URL to send the request to
     * @param body     the body of the request
     * @param headers  the headers to include in the request
     * @param username the username for basic authentication
     * @param password the password for basic authentication
     * @return the response received from the PUT request
     */
    @Test
    public static Response put(String url, String body, Map<String, String> headers, String username, String password) {
        return RestAssured.given()
                .baseUri(url)
                .auth().basic(username, password)
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .put()
                .then()
                .extract().response();
    }

    /**
     * Sends a DELETE request to the specified URL with the provided headers, username, and password.
     *
     * @param url      the URL to send the request to
     * @param headers  the headers to include in the request
     * @param username the username for basic authentication
     * @param password the password for basic authentication
     * @return the response received from the DELETE request
     */
    @Test
    public static Response delete(String url, Map<String, String> headers, String username, String password) {
        return RestAssured.given()
                .baseUri(url)
                .auth().basic(username, password)
                .headers(headers)
                .contentType(ContentType.JSON)
                .when()
                .delete()
                .then()
                .extract().response();
    }
}
