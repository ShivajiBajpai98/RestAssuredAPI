package petstoreswagger.finalproject;

import org.testng.TestNG;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TestNGRunner {

    @Test
    public void runScenarios() {
        TestNG testng = new TestNG();
        List<String> suites = new ArrayList<>();
        String filePath = "src" + File.separator + "test" + File.separator + "java" + File.separator + "petstoreswagger" + File.separator + "finalproj" + File.separator + "testng.xml";
        suites.add(filePath);
        testng.setTestSuites(suites);
        testng.run();
    }

    public static void main(String[] args) {
        TestNGRunner runner = new TestNGRunner();
        runner.runScenarios();
    }
}
