package wiremockget;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.configure;

import static com.github.tomakehurst.wiremock.client.WireMock.reset;
import static wiremockget.WireMockHelper.stubGetRequestFromFile;

public class WireMockGetRequestTest {

    @BeforeClass
    public static void setup() throws IOException {
        configure();
        stubGetRequestFromFile("/api/example", 200, "application/json", "src/test/resources/get_response.json");
    }

    @Test
    public void testMockedApi() {
        RestAssured.given()
                .baseUri("http://localhost")
                .port(8080)
                .contentType(ContentType.JSON)
                .when()
                .get("/api/example")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", Matchers.equalTo(123))
                .body("name", Matchers.equalTo("John Doe"));
    }

    @AfterClass
    public static void teardown() {
        reset();
    }
}
