package maskheader;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;


public class HideHeader {

    public static void main(String[] args) {
        // Start WireMock server
        WireMockServer wireMockServer = new WireMockServer();
        wireMockServer.start();

        // Configure WireMock to stub the desired endpoint
        WireMock.configureFor("localhost", wireMockServer.port());
        WireMock.stubFor(WireMock.post(WireMock.urlEqualTo("/something"))
                .willReturn(WireMock.aResponse().withStatus(200).withBody("Mocked response")));

        // Create the filter to hide header information
        Filter hideHeaderFilter = (requestSpec, responseSpec, ctx) -> {
            FilterableRequestSpecification filteredRequestSpec =  requestSpec;
            filteredRequestSpec.removeHeader("Header-To-Hide");
            return ctx.next(filteredRequestSpec, responseSpec);
        };

        // Create the filter to show header information
        Filter showHeaderFilter = (requestSpec, responseSpec, ctx) -> {
            FilterableRequestSpecification filteredRequestSpec =  requestSpec;
            System.out.println("Request Headers: " + filteredRequestSpec.getHeaders());
            return ctx.next(filteredRequestSpec, responseSpec);
        };

        // Request body
        String requestBody = "{\"key\": \"value\"}";

        // Send POST request with the filters and request body to WireMock server
        Response response = RestAssured.given()
                .filter(hideHeaderFilter)
                .filter(showHeaderFilter)
                .body(requestBody)
                .post("http://localhost:" + wireMockServer.port() + "/something");

        // Print response
        System.out.println("Response status code: " + response.getStatusCode());
        System.out.println("Response body: " + response.getBody().asString());

        // Stop WireMock server
        wireMockServer.stop();
    }
}
