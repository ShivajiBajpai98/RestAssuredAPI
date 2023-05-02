package factorydesign.apiclasses;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import factorydesign.apiinterface.ApiTest;
import io.restassured.http.ContentType;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Put implements ApiTest {
    @Override
    public void Response() {
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
                    .when()
                    .put("/api/users/2")
                    .then()
                    .statusCode(200)
                    .log()
                    .all();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
