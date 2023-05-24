package maskheader;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;



public class HideHeader2 {

    public static void main(String[] args) {
        // Start WireMock server
        WireMockServer wireMockServer = new WireMockServer();
        wireMockServer.start();

        // Configure WireMock to stub the desired endpoint
        WireMock.configureFor("localhost", wireMockServer.port());
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/api/users"))
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

        // Send GET request with the filter to WireMock server to hide header information
        Response responseHide = RestAssured.given()
                .filter(hideHeaderFilter)
                .get("https://reqres.in/api/users");

        // Print response with hidden header information
        System.out.println("Response status code (Hide): " + responseHide.getStatusCode());
        System.out.println("Response body (Hide): " + responseHide.getBody().asString());

        // Send GET request with the filter to WireMock server to show header information
        Response responseShow = RestAssured.given()
                .filter(showHeaderFilter)
                .get("https://reqres.in/api/users");

        // Print response with shown header information
        System.out.println("Response status code (Show): " + responseShow.getStatusCode());
        System.out.println("Response body (Show): " + responseShow.getBody().asString());

        // Stop WireMock server
        wireMockServer.stop();
    }
}
