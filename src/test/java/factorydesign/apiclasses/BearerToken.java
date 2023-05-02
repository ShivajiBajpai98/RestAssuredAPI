package factorydesign.apiclasses;

import factorydesign.apiinterface.ApiTest;

import static io.restassured.RestAssured.given;

public class BearerToken implements ApiTest
{
    @Override
    public void Response()
    {
        String brearToken= "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";


        given().headers("Authorization","Bearer"+brearToken)

                .when().get("https://github.com")

                .then().statusCode(200).log().all();


    }
}
