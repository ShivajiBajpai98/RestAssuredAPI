package wiremockbasicsimproved;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ApiResponseValidatorFive {
    /*public void validateStatusCode(Response response, int expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);
    }

    public void validateContentType(Response response, ContentType expectedContentType) {
        response.then().contentType(expectedContentType);
    }

    public void validateResponseBody(Response response, String expectedBody) {
        response.then().body(equalTo(expectedBody));
    }

*/
    public void validateStatusCode(Response response, int expectedStatusCode) {
        assertThat("Status code mismatch", response.getStatusCode(), equalTo(expectedStatusCode));
    }

    public void validateContentType(Response response, ContentType expectedContentType) {
        assertThat("Content type mismatch", response.getContentType(), equalTo(expectedContentType.toString()));
    }

    public void validateResponseBody(Response response, String expectedBody) {
        assertThat("Response body mismatch", response.getBody().asString(), equalTo(expectedBody));
    }

}
