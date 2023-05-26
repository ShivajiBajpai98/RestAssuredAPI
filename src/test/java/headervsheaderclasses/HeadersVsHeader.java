package headervsheaderclasses;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.testng.Assert.assertEquals;

public class HeadersVsHeader {

    private static final int WIREMOCK_PORT = 8081;

    @BeforeSuite
    public void setUp() {
        startWireMockServer();
    }

    @AfterSuite
    public void tearDown() {
        stopWireMockServer();
    }

    @Test
    public void testRequestWithHeaders() {
        // Configure WireMock response
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/api/users"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"message\": \"Success\"}")));

        // Create individual Header objects
        Header contentTypeHeader = new Header("Content-Type", "application/json");
        Header authorizationHeader = new Header("Authorization", "Bearer token123");

        // Send the request with individual headers
        Response response = RestAssured.given()
                .header(contentTypeHeader)
                .header(authorizationHeader)
                .get("http://localhost:" + WIREMOCK_PORT + "/api/users");

        // Verify the response
        assertEquals(response.getStatusCode(), 200);
        assertEquals(response.getHeader("Content-Type"), "application/json");
        assertEquals(response.getBody().jsonPath().getString("message"), "Success");
    }

    @Test
    public void testRequestWithHeadersObject() {
        // Configure WireMock response
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/api/users"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"message\": \"Success\"}")));

        // Create Headers object
        Headers headers = new Headers(
                new Header("Content-Type", "application/json"),
                new Header("Authorization", "Bearer token123")
        );

        // Send the request with Headers object
        Response response = RestAssured.given()
                .headers(headers)
                .get("http://localhost:" + WIREMOCK_PORT + "/api/users");

        // Verify the response
        assertEquals(response.getStatusCode(), 200);
        assertEquals(response.getHeader("Content-Type"), "application/json");
        assertEquals(response.getBody().jsonPath().getString("message"), "Success");
    }

    private static void startWireMockServer() {
        WireMockConfiguration config = wireMockConfig().port(WIREMOCK_PORT);
        configureFor("localhost", WIREMOCK_PORT);
        WireMockServer wireMockServer = new WireMockServer(config);
        wireMockServer.start();
    }

    private static void stopWireMockServer() {
        WireMockServer wireMockServer = new WireMockServer();
        wireMockServer.stop();
    }
}
