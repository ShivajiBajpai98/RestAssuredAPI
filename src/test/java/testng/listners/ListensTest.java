package testng.listners;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

//@Listeners(CustmListeners.class)
public class ListensTest
{
    @Test
    void test1()
    {
        System.out.println("this is test 1");
        Assert.assertEquals("Shivaji","Shivaji");
    }

    @Test
    void test2()
    {
        System.out.println("this is test 2");
        Assert.assertEquals("Hyderabad","Hyderabad");
        //Assert.assertEquals("Hyderabad","Bengaluru");
    }

    @Test
    void test3()
    {
        System.out.println("this is test 3");
        throw new SkipException("This is skip exception");
    }
}
