package testng.basics;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestNGAssertion extends TestNGBaseSuiteLevel {
    @Test(groups = "smoke")
    public void validate() {

        System.out.println("Beginning");
        String expected_title = "Yahoo.com";
        String actual_title = "Gmail.com";


        // Hard assertions

        //System.out.println("Validating Title");
        //Assert.assertEquals(expected_title, actual_title); // Validating title

        // System.out.println("Validating Image");
        // Assert.assertEquals(true, false); // Validating image

        //System.out.println("Validating response Status");
        // Assert.assertEquals(400, 200); // Validating image


        // Soft Assertions

        SoftAssert softAssert = new SoftAssert();


        System.out.println("Validating title");
        softAssert.assertEquals(actual_title, expected_title);


        System.out.println("Validating image");
        softAssert.assertEquals(true, false, "image not present");


        System.out.println("validate response code");
        softAssert.assertEquals(400, 200, "Response code is not matching");


        System.out.println("Ending");
        softAssert.assertAll();


    }
}
