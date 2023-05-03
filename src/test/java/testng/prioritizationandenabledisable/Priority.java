package testng.prioritizationandenabledisable;

import org.testng.annotations.Test;

public class Priority
{
    @Test(priority = 4)
    void testOne()
    {
        System.out.println("This is test1");
    }
    @Test(priority = 2)
    void testTwo()
    {
        System.out.println("This is test2");
    }
    @Test(priority = 3)
    void testThree()
    {
        System.out.println("This is test3");

    }
    @Test(priority = 1,enabled = false)
    void testFour()
    {
        System.out.println("This is test4");
    }
}

