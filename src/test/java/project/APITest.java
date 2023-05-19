package project;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Rule;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;

public class APITest {
    private static final String BASE_URL = "http://localhost";
    private static final int WIREMOCK_PORT = 8080;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(WIREMOCK_PORT);

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.port = WIREMOCK_PORT;
    }

    @Test
    public void testGETRequest() {
        stubFor(get(urlEqualTo("/endpoint"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{ \"key\": \"value\" }")));

        given()
                .when()
                .get("/endpoint")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);

        verify(getRequestedFor(urlEqualTo("/endpoint")));
    }

    @Test
    public void testPOSTRequest() {
        String requestBody = "{ \"key\": \"value\" }";

        stubFor(post(urlEqualTo("/endpoint"))
                .withRequestBody(equalToJson(requestBody))
                .willReturn(aResponse()
                        .withStatus(201)));

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/endpoint")
                .then()
                .statusCode(201);

        verify(postRequestedFor(urlEqualTo("/endpoint"))
                .withRequestBody(equalToJson(requestBody)));
    }

    @Test
    public void testPUTRequest() {
        String requestBody = "{ \"key\": \"updatedValue\" }";

        stubFor(put(urlEqualTo("/endpoint/123"))
                .withRequestBody(equalToJson(requestBody))
                .willReturn(aResponse()
                        .withStatus(200)));

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("/endpoint/123")
                .then()
                .statusCode(200);

        verify(putRequestedFor(urlEqualTo("/endpoint/123"))
                .withRequestBody(equalToJson(requestBody)));
    }

    @Test
    public void testPATCHRequest() {
        String requestBody = "{ \"key\": \"updatedValue\" }";

        stubFor(patch(urlEqualTo("/endpoint/123"))
                .withRequestBody(equalToJson(requestBody))
                .willReturn(aResponse()
                        .withStatus(200)));

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .patch("/endpoint/123")
                .then()
                .statusCode(200);

        verify(patchRequestedFor(urlEqualTo("/endpoint/123"))
                .withRequestBody(equalToJson(requestBody)));
    }

    @Test
    public void testDELETERequest() {
        stubFor(delete(urlEqualTo("/endpoint/123"))
                .willReturn(aResponse()
                        .withStatus(204)));

        given()
                .when()
                .delete("/endpoint/123")
                .then()
                .statusCode(204);

        verify(deleteRequestedFor(urlEqualTo("/endpoint/123")));
    }
}
