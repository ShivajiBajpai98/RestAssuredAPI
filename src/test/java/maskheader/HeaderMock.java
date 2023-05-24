package maskheader;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class HeaderMock {

    public static void main(String[] args) {
        // Start WireMock server
        WireMockServer wireMockServer = new WireMockServer();
        wireMockServer.start();

        // Configure WireMock to stub the desired endpoint
        WireMock.configureFor("localhost", wireMockServer.port());
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/api/users"))
                .willReturn(WireMock.aResponse().withStatus(200).withBody("Mocked response")));

        // Mask header information by removing it
        Response response = RestAssured.given()
                .header("Header-To-Remove", "")  // Replace "Header-To-Remove" with the actual header you want to remove
                .get("http://localhost:" + wireMockServer.port() + "/api/users");

        // Print response
        System.out.println("Response status code: " + response.getStatusCode());
        System.out.println("Response body: " + response.getBody().asString());

        // Stop WireMock server
        wireMockServer.stop();
    }
}
