package factorydesign.apiclasses;

import factorydesign.apiinterface.ApiTest;

import static io.restassured.RestAssured.given;

public class APIKey implements ApiTest {
    @Override
    public void Response() {
        given().queryParam("Shivaji Token","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").


                when().
                get("https://github.com").
                then().statusCode(200).log().all();

    }
}
