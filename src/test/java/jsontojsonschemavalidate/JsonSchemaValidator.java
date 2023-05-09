package jsontojsonschemavalidate;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class JsonSchemaValidator
{

    @Test
    void testJsonSchemaValidator()
    {

        baseURI="https://reqres.in/api";

        given().get("/users?page=2")
                .then().assertThat().body(matchesJsonSchemaInClasspath("listuserschema.json")).statusCode(200).log().all();

    }

}
