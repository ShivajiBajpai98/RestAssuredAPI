package wiremockget;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.AfterClass;
import org
        .testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.configure;
import static com.github.tomakehurst.wiremock.client.WireMock.reset;
import static wiremockget.WireMockHelper.stubPostRequestFromFile;


public class WireMockPostRequestTest {


    @BeforeClass
    public static void setup() throws IOException {
        configure();
        stubPostRequestFromFile("/api/example", 201, "application/json", "src/test/resources/post_response.json");
    }

    @Test
    public void testMockedApi() {
        RestAssured.given()
                .baseUri("http://localhost")
                .port(8080)
                .contentType(ContentType.JSON)
                .body("{\"id\": 456, \"name\": \"Jane Smith\"}")
                .when()
                .post("/api/example")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("id", Matchers.equalTo(456))
                .body("name", Matchers.equalTo("Jane Smith"));
    }

    @AfterClass
    public static void teardown() {
        reset();
    }
}