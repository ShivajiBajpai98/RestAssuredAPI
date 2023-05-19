package log;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RestAssuredWireMockExample {
    private static final Logger LOGGER = LogManager.getLogger(RestAssuredWireMockExample.class);

    public static void main(String[] args) {
        // Start WireMock server
        WireMockServer wireMockServer = new WireMockServer();
        wireMockServer.start();

        // Configure WireMock stub
        configureStub();

        // Perform RestAssured request
        RequestSpecification requestSpec = RestAssured.given();
        Response response = requestSpec.get("http://localhost:8080/api/users");

        // Log RestAssured request and response
        LOGGER.info("RestAssured Request: {}", requestSpec.log().all());
        LOGGER.info("RestAssured Response: {}", response.getBody().asString());

        // Stop WireMock server
        wireMockServer.stop();
    }

    private static void configureStub() {
        WireMock.configureFor("localhost", 8080);
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/api/users"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"id\": 1, \"name\": \"John Doe\"}")));
    }
}
