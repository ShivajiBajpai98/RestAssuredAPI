package wiremockauthorization;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;

public class OAuth2Authorization {
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
    public void testOAuth2Authorization() {
        // Stubbing the API response using WireMock
        stubFor(get(urlEqualTo("/api/books/1"))
                .withHeader("Authorization", equalTo("Bearer your-access-token"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"id\": 1, \"name\": \"Shivaji\"}")));

        // Making the authorized API request with OAuth2 Bearer token
        Response response = given()
                .header("Authorization", "Bearer your-access-token")
                .when()
                .get("/api/books/1")
                .then()
                .log().all() // Print the response
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();
    }

    private void startWireMockServer() {
        wireMockServer = new WireMockServer();
        wireMockServer.start();
        WireMock.configureFor("localhost", wireMockServer.port());
        System.out.println("WireMock server started on port " + wireMockServer.port());
    }

    private void configureRestAssured() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = WIREMOCK_PORT;
    }

    private void resetWireMockServer() {
        WireMock.reset();
        System.out.println("WireMock server reset");
    }

    private void stopWireMockServer() {
        wireMockServer.stop();
        System.out.println("WireMock server stopped");
    }
}
