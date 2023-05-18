package wiremockauthorization;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.*;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.*;

public class RestAssuredAuthorizationTest {

    private WireMockServer wireMockServer;

    @BeforeClass
    public void setupClass() {
        wireMockServer = new WireMockServer();
        wireMockServer.start();
        configureFor("localhost", wireMockServer.port());
    }

    @AfterClass
    public void teardownClass() {
        wireMockServer.stop();
    }

    @BeforeMethod
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = wireMockServer.port();
    }

    // Test with API Key
    @Test
    public void testApiKeyAuthorization() {
        String apiKey = "your-api-key";
        performAuthorizedRequest(apiKey, "/api/books", 200);
    }

    // Test with Basic Authentication
    @Test
    public void testBasicAuthAuthorization() {
        String username = "username";
        String password = "password";
        String basicAuthHeader = "Basic " + java.util.Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
        performAuthorizedRequest(basicAuthHeader, "/api/books", 200);
    }

    // Test with Bearer Token
    @Test
    public void testBearerTokenAuthorization() {
        String bearerToken = "your-token";
        String authorizationHeader = "Bearer " + bearerToken;
        performAuthorizedRequest(authorizationHeader, "/api/books", 200);
    }

    // Test with Digest Authentication
    @Test
    public void testDigestAuthAuthorization() {
        String username = "username";
        String password = "password";
        String authorizationHeader = "Digest " + java.util.Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
        performAuthorizedRequest(authorizationHeader, "/api/books", 200);
    }

    // Test with OAuth 2.0
    @Test
    public void testOAuth2Authorization() {
        String accessToken = "access-token";
        String authorizationHeader = "Bearer " + accessToken;
        performAuthorizedRequest(authorizationHeader, "/api/books", 200);
    }

    // Test with Preemptive Authorization
    @Test
    public void testPreemptiveAuthorization() {
        String username = "username";
        String password = "password";
        String basicAuthHeader = "Basic " + java.util.Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
        performAuthorizedRequest(basicAuthHeader, "/api/books", 200);
    }

    private void performAuthorizedRequest(String authorizationHeader, String endpoint, int expectedStatusCode) {
        stubEndpoint(endpoint, expectedStatusCode);
        sendRequest(authorizationHeader, endpoint, expectedStatusCode);
    }

    private void stubEndpoint(String endpoint, int statusCode) {
        stubFor(get(urlEqualTo(endpoint)).willReturn(aResponse().withStatus(statusCode)));
    }

    private void sendRequest(String authorizationHeader, String endpoint, int expectedStatusCode) {
        Response response = given()
                .header("Authorization", authorizationHeader)
                .when()
                .get(endpoint)
                .then()
                .assertThat()
                .statusCode(expectedStatusCode)
                .extract()
                .response();

        System.out.println("Response Body: " + response.getBody().asString());
    }
}
