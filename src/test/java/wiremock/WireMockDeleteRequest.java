package wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WireMockDeleteRequest {

    private static final int PORT = 8080;
    private static final String HOST = "localhost";

    @BeforeClass
    public static void setup() {
        WireMock.configureFor(HOST, PORT);
        stubFor(delete(urlEqualTo("/api/example"))
                .willReturn(aResponse()
                        .withStatus(204)));
    }

    @Test
    public void testMockedApi() {
        RestAssured.given()
                .baseUri("http://" + HOST)
                .port(PORT)
                .contentType(ContentType.JSON)
                .when()
                .delete("/api/example")
                .then()
                .statusCode(204);
    }

    @AfterClass
    public static void teardown() {
        WireMock.reset();
    }
}
