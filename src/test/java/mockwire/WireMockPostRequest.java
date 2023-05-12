package mockwire;

import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WireMockPostRequest {

    private static final int PORT = 8080;
    private static final String HOST = "localhost";

    @BeforeClass
    public static void setup() {
        WireMock.configureFor(HOST, PORT);
        stubFor(post(urlEqualTo("/api/example"))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"id\": 456, \"name\": \"Jane Smith\"}")));
    }

    @Test
    public void testMockedApi() {
        RestAssured.given()
                .baseUri("http://" + HOST)
                .port(PORT)
                .contentType(ContentType.JSON)
                .body("{\"id\": 789, \"name\": \"Alice Johnson\"}")
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
        WireMock.reset();
    }
}
