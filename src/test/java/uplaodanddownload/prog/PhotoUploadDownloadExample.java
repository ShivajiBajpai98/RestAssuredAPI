package uplaodanddownload.prog;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class PhotoUploadDownloadExample {

    private static final String UPLOAD_PATH = "src/test/java/prog/uploadanddownload/restassured.png";
    private static final String DOWNLOAD_PATH = "src/test/java/prog/uploadanddownload/download";
    private static final String EXPECTED_PHOTO_ID = "12345";

    @BeforeSuite
    public void setUp() {
        startWireMockServer();
    }

    @AfterSuite
    public void tearDown() {
        stopWireMockServer();
    }

    @Test
    public void testUploadPhoto() {
        // Configure WireMock response for photo upload
        WireMock.stubFor(WireMock.post(WireMock.urlPathEqualTo("/api/photos"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"id\": \"" + EXPECTED_PHOTO_ID + "\"}")));

        String uploadedPhotoId = uploadPhoto(UPLOAD_PATH);
        assertEquals(uploadedPhotoId, EXPECTED_PHOTO_ID);
    }

    @Test(dependsOnMethods = "testUploadPhoto")
    public void testDownloadPhoto() {
        // Configure WireMock response for photo download
        WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo("/api/photos/" + EXPECTED_PHOTO_ID))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "image/jpeg")
                        .withBodyFile("src/test/resources/downloaded_photo.jpg")));

        downloadPhoto(EXPECTED_PHOTO_ID, DOWNLOAD_PATH);
        File downloadedPhoto = new File(DOWNLOAD_PATH);
        assertTrue(downloadedPhoto.exists());
    }

    private static void startWireMockServer() {
        WireMockConfiguration config = wireMockConfig().port(8081);
        configureFor("localhost", 8081);
        WireMockServer wireMockServer = new WireMockServer(config);
        wireMockServer.start();
    }

    private static void stopWireMockServer() {
        WireMockServer wireMockServer = new WireMockServer();
        wireMockServer.stop();
    }

    private static String uploadPhoto(String filePath) {
        File photo = new File(filePath);

        Response response = RestAssured.given()
                .multiPart(photo)
                .post("http://localhost:8081/api/photos");

        return response.jsonPath().getString("id");
    }

    private static void downloadPhoto(String photoId, String savePath) {
        Response response = RestAssured.get("http://localhost:8081/api/photos/" + photoId);
        try (InputStream photoStream = response.asInputStream()) {
            Files.copy(photoStream, Path.of(savePath), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
