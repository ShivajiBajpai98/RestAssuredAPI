package apitesting;

import static io.restassured.RestAssured.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;

public class GetPost
{
    @Test
    public void testGet()
    {
        baseURI="https://reqres.in/";
        get("api/users?page=2")
                .then()
                .statusCode(200)
                .body("data[4].first_name",equalTo("George"))
                .body("data.first_name", hasItems("George" ,"Byron"));




    }

    @Test
    public void convertMapToJson() {
        Map<String, String> elements = new HashMap();
        elements.put("name","shivaji");
        elements.put ( "job", "Engineer");


        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String json = objectMapper.writeValueAsString(elements);
            System.out.println(json);

            baseURI="https://reqres.in/";

         given()
                    .header("Content-Type","application/Json")
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(json)
                    .when().post("/api/users")
                    .then()
                    .statusCode(201)
                   .log()
                    .all();


        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}

