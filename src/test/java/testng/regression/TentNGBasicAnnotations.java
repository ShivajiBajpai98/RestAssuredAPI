package testng.regression;


import org.testng.annotations.*;


public class TentNGBasicAnnotations  {

    @BeforeTest
    public void createDBConn() {

        System.out.println("Creating DataBase Connection");
    }

    @AfterTest
    public void closeDBConn() {

        System.out.println("Closing DataBase Connection ");
    }

    @BeforeMethod
    public void launchBrowser() {

        System.out.println("Launching browser");
    }


    @AfterMethod
    public void closeBrowser() {

        System.out.println("Closing the browser");
    }

    @Test(priority = 1, groups = "functional")
    public void doUserReg() {


        System.out.println("Executing User Registration  Test");

    }

    @Test(priority = 2, groups = "functional")
    public void doLogin() {

        System.out.println("Executing Login Test");

    }


}