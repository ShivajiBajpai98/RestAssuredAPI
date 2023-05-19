package testng.annotations;

import org.testng.annotations.*;

public class TestCaseOne {

    @BeforeSuite
    void beforeSuite() {
        System.out.println("This will execute before the test suite");
    }

    @AfterSuite
    void afterSuite() {
        System.out.println("This will execute after the test suite");
    }

    @BeforeTest
    void beforeTest() {
        System.out.println("This will execute before the test");
    }

    @AfterTest
    void afterTest() {
        System.out.println("This will execute after the test");
    }

    @BeforeClass
    void beforeClass() {
        System.out.println("This will execute before the class");
    }

    @AfterClass
    void afterClass() {
        System.out.println("This will execute after the class");
    }

    @BeforeMethod
    void beforeMethod() {
        System.out.println("This will execute before every method");
    }

    @AfterMethod
    void afterMethod() {
        System.out.println("This will execute after every method");
    }

    @Test
    void test1() {
        System.out.println("Test1");
    }

    @Test
    void test2() {
        System.out.println("Test2");
    }
}
