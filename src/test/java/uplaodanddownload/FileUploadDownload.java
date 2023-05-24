package uplaodanddownload;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUploadDownload {

    public static void main(String[] args) {
        // Start WireMock server
        WireMockServer wireMockServer = new WireMockServer();
        wireMockServer.start();

        // Configure WireMock to stub the upload and download endpoints
        WireMock.configureFor("localhost", wireMockServer.port());

        // Stub the file upload endpoint
        WireMock.stubFor(WireMock.post(WireMock.urlEqualTo("/upload"))
                .willReturn(WireMock.aResponse().withStatus(200).withBody("File uploaded successfully")));

        // Stub the file download endpoint
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/download"))
                .willReturn(WireMock.aResponse().withStatus(200).withBodyFile("file")));

        // Prepare the file to upload
        File fileToUpload = new File("src/test/java/uplaodanddownload/BlacklistHeader.txt");

        // Upload the file
        Response uploadResponse = uploadFile(fileToUpload, wireMockServer.port());

        // Print the upload response
        System.out.println("Upload response status code: " + uploadResponse.getStatusCode());
        System.out.println("Upload response body: " + uploadResponse.getBody().asString());

        // Download the file
        Response downloadResponse = downloadFile(wireMockServer.port());

        // Save the downloaded file
        File downloadedFile = new File("src/test/resources/downloadedFile.txt");
        try {
            InputStream inputStream = downloadResponse.getBody().asInputStream();
            FileOutputStream fos = new FileOutputStream(downloadedFile);
            IOUtils.copy(inputStream, fos);
            fos.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Print the download response
        System.out.println("Download response status code: " + downloadResponse.getStatusCode());
        System.out.println("Downloaded file saved to: " + downloadedFile.getAbsolutePath());

        // Stop WireMock server
        wireMockServer.stop();
    }

    private static Response uploadFile(File fileToUpload, int port) {
        RequestSpecification request = RestAssured.given()
                .multiPart(fileToUpload);

        return sendRequest(request, "http://localhost:" + port + "/upload", "POST");
    }

    private static Response downloadFile(int port) {
        RequestSpecification request = RestAssured.given();

        return sendRequest(request, "http://localhost:" + port + "/download", "GET");
    }

    private static Response sendRequest(RequestSpecification request, String url, String method) {
        return request
                .relaxedHTTPSValidation()
                .header("Accept", "BLACKLISTED")
                .log().all()
                .request(method, url);
    }
}
