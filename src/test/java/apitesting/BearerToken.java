package apitesting;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class BearerToken
{
    @Test
    void TestBearerToken()
    {
        String brearToken= "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";


                given().headers("Authorization","Bearer"+brearToken)

                .when().get("https://github.com")

                .then().statusCode(200).log().all();

    }


}
