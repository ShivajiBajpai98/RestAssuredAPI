package wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WireMockPutRequest {

    private static final int PORT = 8080;
    private static final String HOST = "localhost";

    @BeforeClass
    public static void setup() {
        WireMock.configureFor(HOST, PORT);
        stubFor(put(urlEqualTo("/api/example"))
                .withRequestBody(equalToJson("{\"id\": 789, \"name\": \"Alice Johnson\"}"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"message\": \"Data successfully updated.\"}")));
    }

    @Test
    public void testMockedApi() {
        RestAssured.given()
                .baseUri("http://" + HOST)
                .port(PORT)
                .contentType(ContentType.JSON)
                .body("{\"id\": 789, \"name\": \"Alice Johnson\"}")
                .when()
                .put("/api/example")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("message", Matchers.equalTo("Data successfully updated."));
    }

    @AfterClass
    public static void teardown() {
        WireMock.reset();
    }
}
