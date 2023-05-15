
package wiremockbasicsimproved;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ApplicationSix {
    public static void main(String[] args) {
        WireMockServerManagerOne serverManager = new WireMockServerManagerOne();
        serverManager.startServer();

        RestAssuredConfigTwo restAssuredConfig = new RestAssuredConfigTwo(serverManager);
        restAssuredConfig.configureRestAssured();

        ApiStubberThree apiStubber = new ApiStubberThree();
        apiStubber.stubGetApiResponse("/api/books", 200, "[{\"id\": 1, \"title\": \"Book 1\"}, {\"id\": 2, \"title\": \"Book 2\"}]");
        apiStubber.stubPostApiResponse("/api/books", 201, "{\"id\": 3, \"title\": \"Book 3\"}");
        apiStubber.stubPutApiResponse("/api/books/3", 200, "{\"id\": 3, \"title\": \"Updated Book\"}");
        apiStubber.stubPatchApiResponse("/api/books/3", 200, "{\"id\": 3, \"title\": \"Patched Book\"}");
        apiStubber.stubDeleteApiResponse("/api/books/3", 204, "");

        ApiRequesterFour apiRequester = new ApiRequesterFour();
        ApiResponseValidatorFive apiResponseValidator = new ApiResponseValidatorFive();

        // GET Request
        Response getResponse = apiRequester.makeGetRequest("/api/books");
        apiResponseValidator.validateStatusCode(getResponse, 200);
        apiResponseValidator.validateContentType(getResponse, ContentType.JSON);
        apiResponseValidator.validateResponseBody(getResponse, "[{\"id\": 1, \"title\": \"Book 1\"}, {\"id\": 2, \"title\": \"Book 2\"}]");

        // POST Request
        Response postResponse = apiRequester.makePostRequest("/api/books", "{\"title\": \"Book 3\"}");
        apiResponseValidator.validateStatusCode(postResponse, 201);
        apiResponseValidator.validateContentType(postResponse, ContentType.JSON);
        apiResponseValidator.validateResponseBody(postResponse, "{\"id\": 3, \"title\": \"Book 3\"}");

        // PUT Request
        Response putResponse = apiRequester.makePutRequest("/api/books/3", "{\"title\": \"Updated Book\"}");
        apiResponseValidator.validateStatusCode(putResponse, 200);
        apiResponseValidator.validateContentType(putResponse, ContentType.JSON);
        apiResponseValidator.validateResponseBody(putResponse, "{\"id\": 3, \"title\": \"Updated Book\"}");

        // PATCH Request
        Response patchResponse = apiRequester.makePatchRequest("/api/books/3", "{\"title\": \"Patched Book\"}");
        apiResponseValidator.validateStatusCode(patchResponse, 200);
        apiResponseValidator.validateContentType(patchResponse, ContentType.JSON);
        apiResponseValidator.validateResponseBody(patchResponse, "{\"id\": 3, \"title\": \"Patched Book\"}");

        // DELETE Request
        Response deleteResponse = apiRequester.makeDeleteRequest("/api/books/3");
        apiResponseValidator.validateStatusCode(deleteResponse, 204);

        serverManager.stopServer();
    }
}


