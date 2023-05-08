package testng.hardandsoftassertion;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestCase {

    //SoftAssert softAssert = new SoftAssert();
    @Test
    public void test1()
    {
        SoftAssert softAssert1 = new SoftAssert();
        System.out.println("open browser");
        Assert.assertEquals(true,true);
        System.out.println("enter username");
        System.out.println("enter password");
        System.out.println("click on sign in button");
        Assert.assertEquals(true,true);
        System.out.println("Validate home page");
       // Assert.assertEquals(true,true);
        softAssert1.assertEquals(true,false);
        System.out.println("go to deal page");
        System.out.println("create deal");
        softAssert1.assertEquals(true,false);

        System.out.println("go to contacts page");
        System.out.println("create contacts");
        softAssert1.assertEquals(true,false);

        softAssert1.assertAll();

    }
    @Test
    public void test2()
    {
        SoftAssert softAssert2= new SoftAssert();
        System.out.println("logout");
        softAssert2.assertEquals(true,false);
        softAssert2.assertAll();
    }

    @AfterClass
    public void tearDown()
    {
           // softAssert.assertAll();
    }
}
