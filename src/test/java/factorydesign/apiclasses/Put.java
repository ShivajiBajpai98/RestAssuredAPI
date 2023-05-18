package factorydesign.apiclasses;

import factorydesign.apiinterface.ApiTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Put implements ApiTest {
    @Override
    public void execute() {
        RequestSpecification request = given();
        request.contentType(ContentType.JSON);
        request.body("{\"name\":\"John\", \"job\":\"Engineer\"}");

        Response response = request.put("https://reqres.in/api/users/2");
        response.prettyPrint();
    }
}
