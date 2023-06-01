package petstoreswagger.pratice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class PetStoreTestL {

    private String baseUrl;
    private long petId;
    private String name;
    private String status;
    private String payloadFilePath;
    private String updatePayloadFilePath;
    private String imageFilePath;
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeClass
    public void loadProperties() throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src" + File.separator + "test" + File.separator + "java" + File.separator + "petstoreswagger" + File.separator + "finalproj" + File.separator + "test.properties");
        properties.load(fileInputStream);

        baseUrl = properties.getProperty("base.url");
        petId = Long.parseLong(properties.getProperty("pet.id"));
        name = properties.getProperty("name");
        status = properties.getProperty("status");
        payloadFilePath = properties.getProperty("payload.file.path");
        updatePayloadFilePath = properties.getProperty("update.payload.file.path");
        imageFilePath = properties.getProperty("image.file.path");

        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("test-output" + File.separator + "ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @AfterClass
    public void tearDownExtentReports() {
        extent.flush();
    }

    @Test
    public void addNewPetToStore() throws IOException {
        test = extent.createTest("Add New Pet to Store");
        String requestPayload = new String(Files.readAllBytes(Paths.get(payloadFilePath)));

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestPayload)
                .post(baseUrl);

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        System.out.println("Status Code: " + statusCode);
        System.out.println("Response Body: " + responseBody);

        assertThat("Add New Pet to Store - Status Code", statusCode, is(equalTo(200)));
        assertThat("Add New Pet to Store - Response Body", responseBody, is(notNullValue()));

        test.log(Status.INFO, "Status Code: " + statusCode);
        test.log(Status.INFO, "Response Body: " + responseBody);

        response.then().statusCode(200);
        test.pass("Add New Pet to Store - Test Passed");
    }

    @Test
    public void uploadImageWithPetId() {
        test = extent.createTest("Upload Image with Pet ID");
        String additionalMetadata = "Additional data to pass to the server";
        String filePath = "src" + File.separator + "test" + File.separator + "java" + File.separator + "petstoreswagger" + File.separator + "finalproj" + File.separator + "Cute_dog.jpg";

        Response response = RestAssured.given()
                .multiPart("petId", petId)
                .multiPart("additionalMetadata", additionalMetadata)
                .multiPart("file", new File(filePath))
                .post(baseUrl + "/" + petId + "/uploadImage");

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        System.out.println("Status Code: " + statusCode);
        System.out.println("Response Body: " + responseBody);

        assertThat("Upload Image with Pet ID - Status Code", statusCode, is(equalTo(200)));
        assertThat("Upload Image with Pet ID - Response Body", responseBody, is(notNullValue()));

        test.log(Status.INFO, "Status Code: " + statusCode);
        test.log(Status.INFO, "Response Body: " + responseBody);

        response.then().statusCode(200);
        test.pass("Upload Image with Pet ID - Test Passed");
    }

    @Test
    public void getPetById() {
        test = extent.createTest("Get Pet by ID");
        Response response = RestAssured.given()
                .pathParam("petId", petId)
                .get(baseUrl + "/{petId}");

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        System.out.println("Status Code: " + statusCode);
        System.out.println("Response Body: " + responseBody);

        assertThat("Get Pet by ID - Status Code", statusCode, is(equalTo(200)));
        assertThat("Get Pet by ID - Response Body", responseBody, is(notNullValue()));

        test.log(Status.INFO, "Status Code: " + statusCode);
        test.log(Status.INFO, "Response Body: " + responseBody);

        response.then().statusCode(200);
        test.pass("Get Pet by ID - Test Passed");
    }

    @Test
    public void updatePetInStoreWithFormData() throws IOException {
        test = extent.createTest("Update Pet in Store with Form Data");

        String filePath = "src" + File.separator + "test" + File.separator + "java" + File.separator + "petstoreswagger" + File.separator + "finalproj" + File.separator + "updatepayload.json";
        String requestPayload = new String(Files.readAllBytes(Paths.get(filePath)));

        Response response = RestAssured.given()
                .contentType(ContentType.URLENC)
                .body(requestPayload)
                .post(baseUrl + "/" + petId);

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        System.out.println("Status Code: " + statusCode);
        System.out.println("Response Body: " + responseBody);

        assertThat("Update Pet in Store with Form Data - Status Code", statusCode, is(equalTo(200)));
        assertThat("Update Pet in Store with Form Data - Response Body", responseBody, is(notNullValue()));

        test.log(Status.INFO, "Status Code: " + statusCode);
        test.log(Status.INFO, "Response Body: " + responseBody);

        response.then().statusCode(200);
        test.pass("Update Pet in Store with Form Data - Test Passed");
    }

    @Test
    public void deletePetById() {
        test = extent.createTest("Delete Pet by ID");
        Response response = RestAssured.given()
                .pathParam("petId", petId)
                .delete(baseUrl + "/{petId}");

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        System.out.println("Status Code: " + statusCode);
        System.out.println("Response Body: " + responseBody);

        assertThat("Delete Pet by ID - Status Code", statusCode, is(equalTo(200)));
        assertThat("Delete Pet by ID - Response Body", responseBody, is(notNullValue()));

        test.log(Status.INFO, "Status Code: " + statusCode);
        test.log(Status.INFO, "Response Body: " + responseBody);

        response.then().statusCode(200);
        test.pass("Delete Pet by ID - Test Passed");
    }
}
