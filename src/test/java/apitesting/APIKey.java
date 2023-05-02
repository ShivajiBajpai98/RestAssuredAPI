package apitesting;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class APIKey
{
    @Test
    void testAPIKey()
    {
        given().queryParam("Shivaji Token","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").


                when().
                get("https://github.com").
                then().statusCode(200).log().all();

    }
}
