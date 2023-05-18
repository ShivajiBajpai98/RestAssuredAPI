package wiremockhttpmethodsimproved.getrequest;

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
import static org.hamcrest.Matchers.equalTo;

public class WireMockRestAssuredExample {

    private WireMockServer wireMockServer;

    @BeforeClass
    public void setup() {
        // Start the WireMock server on a dynamic port
        wireMockServer = new WireMockServer();
        wireMockServer.start();
        WireMock.configureFor(wireMockServer.port());

        // Configure RestAssured to use the WireMock server
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = wireMockServer.port();
    }

    @AfterClass
    public void teardown() {
        // Stop the WireMock server
        wireMockServer.stop();
    }

    @Test
    public void exampleTest() {
        // Stub the API response
        stubFor(get(urlEqualTo("/api/books"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("[{\"id\": 1, \"title\": \"Book 1\"}, {\"id\": 2, \"title\": \"Book 2\"}]")));

        // Make a request to the API and capture the response
        Response response = makeGetRequest("/api/books");

        // Validate the response
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("[0].title", equalTo("Book 1"))
                .body("[1].title", equalTo("Book 2"));

        // Print the response body
        System.out.println("Response Body: " + response.getBody().asString());
    }

    private Response makeGetRequest(String path) {
        return given()
                .when()
                .get(path)
                .then()
                .extract().response();
    }
}
