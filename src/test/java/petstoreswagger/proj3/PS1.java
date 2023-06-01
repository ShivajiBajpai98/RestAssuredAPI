package petstoreswagger.proj3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static org.hamcrest.Matchers.equalTo;


public class PS1 {
    private RequestSpecification request;
    private String baseUri;
    private Properties properties;
    private static final Logger logger = LogManager.getLogger(PS1.class);
    private ExtentReports extent;
    private ExtentTest extentTest;

    @BeforeClass
    public void setup() {
        // Load the properties file
        properties = new Properties();
        String configFilePath = "src" + File.separator + "test" + File.separator + "java" + File.separator + "petstoreswagger" + File.separator + "proj3" + File.separator + "configuring1.properties";
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(configFilePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Read the baseUri from the properties file
        baseUri = properties.getProperty("baseUri");

        // Set the base URI for the API
        RestAssured.baseURI = baseUri;

        // Create the request specification
        request = RestAssured.given();

        // Initialize ExtentReports and specify the output file location
        extent = new ExtentReports();
        extent.attachReporter(new ExtentHtmlReporter("test-output/ExtentReport.html"));
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        // Start a new test in the ExtentReport
        extentTest = extent.createTest(method.getName(), method.getName());
    }

    @AfterMethod
    public void afterMethod() {
        // End the test in the ExtentReport
        extent.flush();
    }

    // Scenario 1: Pet Post - upload image, find pet by id (get), Get - find pet by status
    @Test(priority = 1)
    public void scenario1() {
        extentTest.log(Status.INFO, "Executing Scenario 1");

        addPetToStore();
        findPetById();
        findPetByStatus();
    }

    // Scenario 2: Add a pet to the store - find pet by id - find pet by status - delete pet
    @Test(priority = 2)
    public void scenario2() {
        extentTest.log(Status.INFO, "Executing Scenario 2");

        addPetToStore();
        findPetById();
        findPetByStatus();
        deletePet();
    }

    // Scenario 3: Add a pet upload images - update an existing pet - delete pet
    @Test(priority = 3)
    public void scenario3() {
        extentTest.log(Status.INFO, "Executing Scenario 3");

        addPetToStore();
        uploadFile();
        updatePet();
        deletePet();
    }

    public void addPetToStore() {
        // Read the JSON request body from a file
        String requestBodyPath = "src" + File.separator + "test" + File.separator + "java" + File.separator + "petstoreswagger" + File.separator + "proj3" + File.separator + "requestPayload.json";
        String requestBody = null;
        try {
            requestBody = new String(Files.readAllBytes(Paths.get(requestBodyPath)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Send the POST request to add a pet
        Response response = request
                .contentType("application/json")
                .body(requestBody)
                .post("/pet");

        // Assert the response status code and log the result
        response.then().statusCode(200);
        logger.info("Add pet response: " + response.asString());

        // Add a log entry to the ExtentReport
        extentTest.log(Status.INFO, "Add pet response: " + response.asString());
    }

    public void findPetById() {
        // Send the GET request to find the pet by ID
        Response response = request.get("/pet/{petId}", 1);

        // Assert the response status code, response body, and log the result
        response.then()
                .statusCode(200)
                .body("name", equalTo("doggie"))
                .body("status", equalTo("available"));
        logger.info("Find pet by ID response: " + response.asString());

        // Add a log entry to the ExtentReport
        extentTest.log(Status.INFO, "Find pet by ID response: " + response.asString());
    }

    public void findPetByStatus() {
        // Send the GET request to find pets by status
        Response response = request.get("/pet/findByStatus?status=available");

        // Assert the response status code, response body, and log the result
        response.then()
                .statusCode(200)
                .body("status[0]", Matchers.equalTo("available"));
        logger.info("Find pet by status response: " + response.asString());

        // Add a log entry to the ExtentReport
        extentTest.log(Status.INFO, "Find pet by status response: " + response.asString());
    }

    public void deletePet() {
        // Send the DELETE request to delete the pet
        Response response = request.delete("/pet/{petId}", 1);

        // Assert the response status code and log the result
        response.then().statusCode(200);
        logger.info("Delete pet response: " + response.asString());

        // Add a log entry to the ExtentReport
        extentTest.log(Status.INFO, "Delete pet response: " + response.asString());
    }

    public void uploadFile() {
        // Specify the file path of the image to upload
        String filePath = "src" + File.separator + "test" + File.separator + "java" + File.separator + "petstoreswagger" + File.separator + "proj3" + File.separator + "dog.jpg";

        // Send the POST request to upload the image
        Response response = request
                .multiPart("file", new File(filePath))
                .post("/pet/{petId}/uploadImage", 1);

        // Assert the response status code and log the result
        response.then().statusCode(200);
        logger.info("Upload image response: " + response.asString());

        // Add a log entry to the ExtentReport
        extentTest.log(Status.INFO, "Upload image response: " + response.asString());
    }

    public void updatePet() {
        // Read the JSON request body from a file
        String requestBodyPath = "src" + File.separator + "test" + File.separator + "java" + File.separator + "petstoreswagger" + File.separator + "proj3" + File.separator + "updatePayload.json";
        String requestBody = null;
        try {
            requestBody = new String(Files.readAllBytes(Paths.get(requestBodyPath)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Send the PUT request to update the pet
        Response response = request
                .contentType("application/json")
                .body(requestBody)
                .put("/pet");

        // Assert the response status code and log the result
        response.then().statusCode(200);
        logger.info("Update pet response: " + response.asString());

        // Add a log entry to the ExtentReport
        extentTest.log(Status.INFO, "Update pet response: " + response.asString());
    }
}
