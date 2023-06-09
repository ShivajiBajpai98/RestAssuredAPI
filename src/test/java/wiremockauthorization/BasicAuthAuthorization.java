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

public class BasicAuthAuthorization {
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
    public void testBasicAuthAuthorization() {
        // Stubbing the API response using WireMock
        stubFor(get(urlEqualTo("/api/books/1"))
                .withBasicAuth("username", "password")
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"id\": 1, \"name\": \"shivaji\"}")));

        // Making the authorized API request with basic authentication
        Response response = given()
                .auth()
                .preemptive()
                .basic("username", "password")
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
