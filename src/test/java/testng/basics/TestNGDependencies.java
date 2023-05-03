package testng.basics;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNGDependencies extends TestNGBaseSuiteLevel {


    @Test(priority = 1, groups = {"functional", "smoke"})
    public void doUserRegistration() {

        System.out.println("Executing User Registration test");
        Assert.fail("User not registered successfully");

    }

    @Test(priority = 2, dependsOnMethods = "doUserRegistration", groups = {"functional", "smoke"})
    public void doLogin() {

        System.out.println("Executing login test");

    }

    @Test(priority = 3, alwaysRun = true, groups = {"functional", "smoke"})
    public void thirdTest() {

        System.out.println("Executing Third Test");
    }

    @Test(priority = 4, groups = "Atmecs")
    public void fourthTest() {

        System.out.println("Executing Fourth Test");
    }

}
