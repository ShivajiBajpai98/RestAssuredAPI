package testng.annotations;

import org.testng.annotations.*;

public class TestNGAnnotationLifeCycle2
{
    @BeforeClass
    void beforeClass()
    {
        System.out.println("This will execute before the class");
    }
    @AfterClass
    void afterClass()
    {
        System.out.println("This will execute After the class");
    }

    @BeforeMethod
    void beforeMethod(){
        System.out.println("This will execute before every method");
    }
    @AfterMethod
    void afterMethod(){
        System.out.println("This will execute after every method");
    }

    @Test
    void test3()
    {
        System.out.println("Test3");
    }
    @Test
    void test4(){
        System.out.println("Test4");
    }

    @BeforeSuite
    void beforeSuite()
    {
        System.out.println("It will execute before test suite");
    }
    @AfterSuite
    void AfterSuite()
    {
        System.out.println("It will execute After  test suite");
    }
}
