package testng.annotations;

import org.testng.annotations.*;

public class TestCaseTwo {
    @BeforeSuite
    void beforeSuite() {
        System.out.println("This will execute before the test suite");
    }

    @AfterSuite
    void afterSuite() {
        System.out.println("This will execute after the test suite");
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
    void test3() {
        System.out.println("Test3");
    }

    @Test
    void test4() {
        System.out.println("Test4");
    }
}
