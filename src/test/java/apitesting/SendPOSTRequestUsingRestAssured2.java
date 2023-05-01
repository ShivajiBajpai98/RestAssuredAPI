package apitesting;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

class SendPOSTRequestUsingRestAssured2
{

	public static void main(String[] args) {


           String bodyString = "{\"email\":\"eve.holt@reqres.in\",\"password\":\"pistol\"}";
            Response response = given().contentType(ContentType.JSON).body(bodyString).post("https://reqres.in/api/register");
            response.prettyPrint();
            System.out.println(response.getStatusCode());




        HashMap<String, String> map = new HashMap<String, String>();
        map.put("email", "eve.holt@reqres.in");
        map.put("password", "pistol");
        Response response2 = given().contentType(ContentType.JSON).body(map).post("https://reqres.in/api/register");
        response2.prettyPrint();
        System.out.println(response2.getStatusCode());







    }
}
