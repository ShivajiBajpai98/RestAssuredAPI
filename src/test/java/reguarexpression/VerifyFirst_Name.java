package reguarexpression;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class VerifyFirst_Name
{

/*verifying that the first_name field of the first user in the data array matches
 a regular expression. The regular expression ^[A-Za-z]+$ matches any string that
 contains only alphabetical characters.*/
    @Test
    void testVerifyFirst_Name() {
        given().
                get("https://reqres.in/api/users?page=2").
                then().
                assertThat().
                body("data[0].first_name", matchesRegex("^[A-Za-z]+$"));
    }
}
