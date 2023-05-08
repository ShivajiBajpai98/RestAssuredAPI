package testng.listners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class CustmListeners implements ITestListener
{
    @Override
    public void onStart(ITestContext context) {
        System.out.println("start test excecution"+context.getName());
    }



    @Override
    public void onFinish(ITestContext context) {
        System.out.println("finish test execution"+context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("start test"+result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("skiped test"+result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Passed Test"+result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println(" test failed"+result.getName());
    }
}
