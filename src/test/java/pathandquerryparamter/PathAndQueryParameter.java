package pathandquerryparamter;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PathAndQueryParameter {
    private static final String ENDPOINT_URL = "https://reqres.in/api/{resourceName}";
    private static final String RESOURCE_NAME = "users";
    private static final String PAGE_QUERY_PARAM_NAME = "page";
    private static final String ID_QUERY_PARAM_NAME = "id";

    @Test
    public void testPathAndQueryParameter() {
        given()
                .pathParam("resourceName", RESOURCE_NAME)  // Set the path parameter 'resourceName' to 'users'
                .queryParam(PAGE_QUERY_PARAM_NAME, 2)  // Set the query parameter 'page' to 2
                .queryParam(ID_QUERY_PARAM_NAME, 5)  // Set the query parameter 'id' to 5
                .when()
                .get(ENDPOINT_URL)  // Perform a GET request to the endpoint URL
                .then()
                .statusCode(200)  // Assert that the response status code is 200
                .log().all();  // Log the request and response details
    }
}
