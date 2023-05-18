package factorydesign.apiclasses;

import factorydesign.apiinterface.ApiTest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.get;

public class Get implements ApiTest {
    @Override
    public void execute() {
        String url = "https://reqres.in/api/users?page=2";
        Response response = get(url);
        response.prettyPrint();
    }
}
