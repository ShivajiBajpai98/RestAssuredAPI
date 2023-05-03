package testng.basics;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

class TestNGBaseSuiteLevel {

    @BeforeSuite
    public void setUp() {

        System.out.println("Initializing Everything");
    }

    @AfterSuite
    public void tearDown() {

        System.out.println("Exit");
    }

}
