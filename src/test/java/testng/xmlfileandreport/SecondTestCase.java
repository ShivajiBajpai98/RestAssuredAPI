package testng.xmlfileandreport;

import org.testng.annotations.Test;

public class SecondTestCase
{
    @Test(priority = 1)
    void setup()
    {
        System.out.println("Opening browser");
    }

    @Test(priority = 3)
    void searchCustomer()
    {
        System.out.println("This is search customer test");
       // Assert.assertEquals(1,2);
    }

    @Test(priority = 2)
    void addCustomer()
    {
        System.out.println("This is add customer test");
    }
    @Test(priority = 4)
    void tearDown()
    {
        System.out.println("Closing browser");
    }
}
