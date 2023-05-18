package testng.listners;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(CustomListeners.class)
public class ListenersTest {
    @Test
    void test1() {
        System.out.println("This is test 1");
        Assert.assertEquals("Shivaji", "Shivaji");
    }

    @Test
    void test2() {
        System.out.println("This is test 2");
        Assert.assertEquals("Hyderabad", "Hyderabad");
        //Assert.assertEquals("Hyderabad","Bengaluru");
    }

    @Test
    void test3() {
        System.out.println("This is test 3");
        throw new SkipException("This is a skip exception");
    }
}
