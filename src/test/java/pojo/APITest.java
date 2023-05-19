package pojo;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import com.google.gson.Gson;
import pojo.Page;
import pojo.User;

public class APITest {

    public static void main(String[] args) {

        // Set base URI and path
        RestAssured.baseURI = "https://reqres.in";
        String path = "/api/users?page=2";

        // Create request
        RequestSpecification request = RestAssured.given();

        // Send request and get response
        Response response = request.get(path);

        // Deserialize JSON response to Page object using Gson library
        Page page = new Gson().fromJson(response.getBody().asString(), Page.class);

        // Print response
        System.out.println("Page number: " + page.getPage());
        System.out.println("Total users: " + page.getTotal());
        System.out.println("Users:");
        for (User user : page.getData()) {
            System.out.println("  - " + user.getFirst_name() + " " + user.getLast_name() + " (" + user.getEmail() + ")");
        }
    }
}
