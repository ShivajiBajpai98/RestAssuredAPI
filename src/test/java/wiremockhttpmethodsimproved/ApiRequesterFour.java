package wiremockhttpmethodsimproved;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiRequesterFour {
    public Response makeGetRequest(String path) {
        Response response = given()
                .when()
                .get(path)
                .then()
                .extract().response();

        printResponse(response);
        return response;
    }

    public Response makePostRequest(String path, String requestBody) {
        Response response = given()
                .body(requestBody)
                .when()
                .post(path)
                .then()
                .extract().response();

        printResponse(response);
        return response;
    }

    public Response makePutRequest(String path, String requestBody) {
        Response response = given()
                .body(requestBody)
                .when()
                .put(path)
                .then()
                .extract().response();

        printResponse(response);
        return response;
    }

    public Response makePatchRequest(String path, String requestBody) {
        Response response = given()
                .body(requestBody)
                .when()
                .patch(path)
                .then()
                .extract().response();

        printResponse(response);
        return response;
    }

    public Response makeDeleteRequest(String path) {
        Response response = given()
                .when()
                .delete(path)
                .then()
                .extract().response();

        printResponse(response);
        return response;
    }

    private void printResponse(Response response) {
        System.out.println("Response:");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
        System.out.println();
    }
}
