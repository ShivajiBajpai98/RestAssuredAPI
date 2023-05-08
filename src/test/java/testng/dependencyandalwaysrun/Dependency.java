package testng.dependencyandalwaysrun;

import org.testng.annotations.Test;

public class Dependency
{
    @Test
    void startCar()
    {
        System.out.println("car started");
      // Assert.fail();
    }

    @Test(dependsOnMethods = {"startCar"})
    void driveCar()
    {
        System.out.println("car driving");
    }

    @Test(dependsOnMethods = {"driveCar"})
    void stopCar()
    {

        System.out.println("car stopped");
    }

    @Test(dependsOnMethods = {"driveCar","stopCar"},alwaysRun = true)
    void parkCar()
    {

        System.out.println("car parked");
    }
}
