package enhanchedboundaryvalueanalysis;

import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BaseTest {
    protected Properties prop;
    protected RequestSpecification request;
    protected String filename;

    @BeforeTest
    public void loadProperties() {
        prop = new Properties();
        FileInputStream fis = null;
        try {
            String filePath = "src" + File.separator + "test" + File.separator + "resources" + File.separator + "config.properties";
            fis = new FileInputStream(filePath);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            prop.load(fis);
        } catch (IOException e)
        {
            e.printStackTrace();
        }



    }







    @BeforeClass
    public void setup() {
        RestAssured.baseURI = prop.getProperty("baseURI");
        request = RestAssured.given().header("Content-Type", "application/json");
    }

    @AfterTest
    public void tearDown() {
        RestAssured.reset();
    }
}





