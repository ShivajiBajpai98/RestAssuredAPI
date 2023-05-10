package reguarexpression;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class VerifySupportField
{
    @Test
    void testVerifySupportField() {
        given().
                get("https://reqres.in/api/users?page=2").
                then().
                assertThat().
                body("support.text", matchesRegex("^To keep ReqRes free,.*$"));
    }
}
