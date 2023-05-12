package mockwire;

import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WireMockGetRequest {

    private static final int PORT = 8080;
    private static final String HOST = "localhost";

    @BeforeClass
    public static void setup() {
        WireMock.configureFor(HOST, PORT);
        stubFor(get(urlEqualTo("/api/example"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"id\": 123, \"name\": \"John Doe\"}")));
    }

    @Test
    public void testMockedApi() {
        RestAssured.given()
                .baseUri("http://" + HOST)
                .port(PORT)
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
        WireMock.reset();
    }
}
