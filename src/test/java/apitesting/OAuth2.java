package apitesting;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class OAuth2
{
    @Test
    void TestOAuth2()
    {
        String brearToken= "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";


        given().auth().oauth2(brearToken)

                .when().get("https://github.com")

                .then().statusCode(200).log().all();

    }
}
