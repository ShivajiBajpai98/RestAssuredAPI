package wiremockget;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WireMockHelper {

    public static void stubGetRequest(String url, int status, String contentType, String responseBody) {
        stubFor(get(urlEqualTo(url))
                .willReturn(aResponse()
                        .withStatus(status)
                        .withHeader("Content-Type", contentType)
                        .withBody(responseBody)));
    }

    public static void stubGetRequestFromFile(String url, int status, String contentType, String filePath)  {
        String responseBody = null;
        try {
            responseBody = readJsonFromFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stubGetRequest(url, status, contentType, responseBody);
    }

    public static void stubPostRequest(String url, int status, String contentType, String responseBody) {
        stubFor(post(urlEqualTo(url))
                .willReturn(aResponse()
                        .withStatus(status)
                        .withHeader("Content-Type", contentType)
                        .withBody(responseBody)));
    }

    public static void stubPostRequestFromFile(String url, int status, String contentType, String filePath) {
        String responseBody = null;
        try {
            responseBody = readJsonFromFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stubPostRequest(url, status, contentType, responseBody);
    }

    // Helper method to read JSON data from file
    private static String readJsonFromFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
    }
}
