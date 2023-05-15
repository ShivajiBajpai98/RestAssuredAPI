package wiremockbasicsimproved;

import io.restassured.RestAssured;

public class RestAssuredConfigTwo {
    private WireMockServerManagerOne serverManager;

    public RestAssuredConfigTwo(WireMockServerManagerOne serverManager) {
        this.serverManager = serverManager;
    }

    public void configureRestAssured() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = serverManager.getServerPort();
    }
}
