package testng.annotations;

import org.testng.annotations.*;

public class TC1
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
    void test1()
    {
        System.out.println("Test1");
    }
    @Test
    void test2(){
        System.out.println("Test2");
    }

    @BeforeTest
    void beforeTest()
    {
        System.out.println("This will execute before the test");
    }

    @AfterTest
    void afterTest()
    {
        System.out.println("This will execute after the test");
    }
}
