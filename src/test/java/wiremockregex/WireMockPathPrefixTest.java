package wiremockregex;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import static org.hamcrest.Matchers.equalTo;

public class WireMockPathPrefixTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    @Test
    public void testEndpointWithPrefix() {
        stubFor(get(urlPathMatching("/api/.*"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("Response from /api/ endpoint")));

        RestAssured.given()
                .when()
                .get("/api/users")
                .then()
                .statusCode(200)
                .body(equalTo("Response from /api/ endpoint"));
    }

    @Test
    public void testEndpointWithoutPrefix() {
        stubFor(get(urlPathMatching("/public/.*"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("Response from /public/ endpoint")));

        RestAssured.given()
                .when()
                .get("/public/users")
                .then()
                .statusCode(200)
                .body(equalTo("Response from /public/ endpoint"));
    }
}
