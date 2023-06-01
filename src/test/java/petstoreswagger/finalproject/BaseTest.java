package petstoreswagger.finalproject;

import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class BaseTest {

    protected String baseUrl;
    protected long petId;
    protected String name;
    protected String status;
    protected String payloadFilePath;
    protected String updatePayloadFilePath;
    protected String imageFilePath;
    protected ExtentReports extent;
    protected ExtentTest test;

    @BeforeClass
    public void setUp() throws IOException {
        loadProperties();

        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("test-output" + File.separator + "ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @AfterClass
    public void tearDownExtentReports() {
        extent.flush();
    }

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
    }

    public void validateResponse(Response response, int expectedStatusCode) {
        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        System.out.println("Status Code: " + statusCode);
        System.out.println("Response Body: " + responseBody);

        assertThat("Status Code", statusCode, is(equalTo(expectedStatusCode)));
        assertThat("Response Body", responseBody, is(notNullValue()));
    }

    public void logInfo(String message) {
        test.log(Status.INFO, message);
    }
}
