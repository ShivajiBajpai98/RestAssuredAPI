package testng.paralleltesting;

import org.testng.annotations.Test;

public class ParallelTest {

    @Test(threadPoolSize = 2, invocationCount = 4)
    public void testMethod() {
        long threadId = Thread.currentThread().getId();
        System.out.println("Test method executing on thread " + threadId);
    }
    }
