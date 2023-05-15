package wiremockbasicsimproved;

import com.github.tomakehurst.wiremock.client.WireMock;

public class ApiStubberThree

{

    public void stubGetApiResponse(String path, int statusCode, String responseBody) {
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo(path))
                .willReturn(WireMock.aResponse()
                        .withStatus(statusCode)
                        .withHeader("Content-Type", "application/json")
                        .withBody(responseBody)));
    }

    public void stubPostApiResponse(String path, int statusCode, String responseBody) {
        WireMock.stubFor(WireMock.post(WireMock.urlEqualTo(path))
                .willReturn(WireMock.aResponse()
                        .withStatus(statusCode)
                        .withHeader("Content-Type", "application/json")
                        .withBody(responseBody)));
    }

    public void stubPutApiResponse(String path, int statusCode, String responseBody) {
        WireMock.stubFor(WireMock.put(WireMock.urlEqualTo(path))
                .willReturn(WireMock.aResponse()
                        .withStatus(statusCode)
                        .withHeader("Content-Type", "application/json")
                        .withBody(responseBody)));
    }

    public void stubPatchApiResponse(String path, int statusCode, String responseBody) {
        WireMock.stubFor(WireMock.patch(WireMock.urlEqualTo(path))
                .willReturn(WireMock.aResponse()
                        .withStatus(statusCode)
                        .withHeader("Content-Type", "application/json")
                        .withBody(responseBody)));
    }

    public void stubDeleteApiResponse(String path, int statusCode, String responseBody) {
        WireMock.stubFor(WireMock.delete(WireMock.urlEqualTo(path))
                .willReturn(WireMock.aResponse()
                        .withStatus(statusCode)
                        .withHeader("Content-Type", "application/json")
                        .withBody(responseBody)));
    }
}
