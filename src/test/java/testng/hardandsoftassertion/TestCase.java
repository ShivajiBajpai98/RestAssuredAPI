package testng.hardandsoftassertion;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestCase {
    @Test
    public void test1() {
        SoftAssert softAssert = new SoftAssert();
        System.out.println("open browser");
        Assert.assertEquals(true, true);
        System.out.println("enter username");
        System.out.println("enter password");
        System.out.println("click on sign in button");
        Assert.assertEquals(true, true);
        System.out.println("Validate home page");
        softAssert.assertEquals(true, false);
        System.out.println("go to deal page");
        System.out.println("create deal");
        softAssert.assertEquals(true, false);
        System.out.println("go to contacts page");
        System.out.println("create contacts");
        softAssert.assertEquals(true, false);
        softAssert.assertAll();
    }

    @Test
    public void test2() {
        SoftAssert softAssert = new SoftAssert();
        System.out.println("logout");
        softAssert.assertEquals(true, false);
        softAssert.assertAll();
    }

    @AfterClass
    public void tearDown() {
        // Perform any necessary cleanup
    }
}
