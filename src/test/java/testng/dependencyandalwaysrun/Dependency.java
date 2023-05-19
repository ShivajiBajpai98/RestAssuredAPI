package testng.dependencyandalwaysrun;

import org.testng.annotations.Test;

public class Dependency {
    @Test
    void startCar() {
        System.out.println("Car started");
        // Add your car start logic here
    }

    @Test(dependsOnMethods = {"startCar"})
    void driveCar() {
        System.out.println("Car driving");
        // Add your car driving logic here
    }

    @Test(dependsOnMethods = {"driveCar"})
    void stopCar() {
        System.out.println("Car stopped");
        // Add your car stop logic here
    }

    @Test(dependsOnMethods = {"driveCar", "stopCar"}, alwaysRun = true)
    void parkCar() {
        System.out.println("Car parked");
        // Add your car park logic here
    }
}
