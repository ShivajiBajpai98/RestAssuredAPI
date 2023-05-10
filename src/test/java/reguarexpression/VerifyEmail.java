package reguarexpression;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

public class VerifyEmail
{

    @Test
    void testVerifyEmail()
    {
        given().
                get("https://reqres.in/api/users?page=2").
                then().
                assertThat().
                body("data[2].email", matchesRegex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"));
    }
}
