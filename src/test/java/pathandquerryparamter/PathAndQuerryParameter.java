package pathandquerryparamter;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PathAndQuerryParameter {
    private static final String ENDPOINT_URL = "https://reqres.in/api/{resourceName}";
    private static final String RESOURCE_NAME = "users";
    private static final String PAGE_QUERY_PARAM_NAME = "page";
    private static final String ID_QUERY_PARAM_NAME = "id";

    @Test
    void testPathAndQuerryParamete() {
        given()
                .pathParam("resourceName", RESOURCE_NAME)
                .queryParam(PAGE_QUERY_PARAM_NAME, 2)
                .queryParam(ID_QUERY_PARAM_NAME, 5)
                .when()
                .get(ENDPOINT_URL)
                .then()
                .statusCode(200)
                .log().all();
    }
}
