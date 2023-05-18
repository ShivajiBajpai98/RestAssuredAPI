package testng.listners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomListeners implements ITestListener {
    @Override
    public void onStart(ITestContext context) {
        System.out.println("Start test execution: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Finish test execution: " + context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Start test: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Skipped test: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Passed test: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Failed test: " + result.getName());
    }
}
