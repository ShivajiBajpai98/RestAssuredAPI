package apitesting.httpmethods;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class SendPOSTRequestUsingRestAssured2 {

    public static void main(String[] args) {
        // Send a POST request with request body as a JSON string
        String bodyString = "{\"email\":\"eve.holt@reqres.in\",\"password\":\"pistol\"}";
        Response response1 = given()
                .contentType(ContentType.JSON)
                .body(bodyString)
                .post("https://reqres.in/api/register");

        // Print the response body
        response1.prettyPrint();
        System.out.println("Status Code: " + response1.getStatusCode());

        // Send a POST request with request body as a HashMap
        HashMap<String, String> requestBody = new HashMap<>();
        requestBody.put("email", "eve.holt@reqres.in");
        requestBody.put("password", "pistol");
        Response response2 = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("https://reqres.in/api/register");

        // Print the response body
        response2.prettyPrint();
        System.out.println("Status Code: " + response2.getStatusCode());
    }
}
