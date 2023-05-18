package factorydesign.apiclasses;

import factorydesign.apiinterface.ApiTest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Delete implements ApiTest {
    @Override
    public void execute() {
        RequestSpecification request = given();

        Response response = request.delete("https://reqres.in/api/users/2");
        response.prettyPrint();
    }
}
