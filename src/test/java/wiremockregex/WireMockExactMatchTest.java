package wiremockregex;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;

public class WireMockExactMatchTest {
    private static final int WIREMOCK_PORT = 8080;
    private static WireMockServer wireMockServer;

    @BeforeClass
    public void setup() {
        startWireMockServer();
        configureRestAssured();
    }

    @AfterClass
    public void teardown() {
        resetWireMockServer();
        stopWireMockServer();
    }

    @Test
    public void testExactMatching() {
        stubFor(get(urlEqualTo("/api/books"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"id\": 1, \"name\": \"Book ABC\"}")));

        Response response = given()
                .when()
                .get("/api/books")
                .then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", Matchers.equalTo(1))
                .body("name", Matchers.equalTo("Book ABC"))
                .extract().response();
    }

    private void startWireMockServer() {
        wireMockServer = new WireMockServer(WIREMOCK_PORT);
        wireMockServer.start();
        WireMock.configureFor("localhost", WIREMOCK_PORT);
    }

    private void configureRestAssured() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = WIREMOCK_PORT;
    }

    private void resetWireMockServer() {
        WireMock.reset();
    }

    private void stopWireMockServer() {
        wireMockServer.stop();
    }
}
