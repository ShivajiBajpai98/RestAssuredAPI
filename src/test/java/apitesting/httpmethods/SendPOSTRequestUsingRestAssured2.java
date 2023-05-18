package apitesting.httpmethods;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class SendPOSTRequestUsingRestAssured2 {

    public static void main(String[] args) {
        // Request body as a JSON string
        String bodyString = "{\"email\":\"eve.holt@reqres.in\",\"password\":\"pistol\"}";
        Response response1 = given()
                .contentType(ContentType.JSON)
                .body(bodyString)
                .post("https://reqres.in/api/register");
        response1.prettyPrint();
        System.out.println("Status Code: " + response1.getStatusCode());

        // Request body as a HashMap
        HashMap<String, String> requestBody = new HashMap<>();
        requestBody.put("email", "eve.holt@reqres.in");
        requestBody.put("password", "pistol");
        Response response2 = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("https://reqres.in/api/register");
        response2.prettyPrint();
        System.out.println("Status Code: " + response2.getStatusCode());
    }
}
