package factorydesign.apiclasses;

import factorydesign.apiinterface.ApiTest;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.when;

public class Delete implements ApiTest {
    @Override
    public void Response() {

        baseURI="https://reqres.in/";


        when().delete("/api/users/2")
                .then()
                .statusCode(204)
                .log()
                .all();

    }
}
