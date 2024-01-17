package ListenersPackage;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomTestListeners implements ITestListener{

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println(result.getName() + " Test Started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println(result.getName() + " Test Success");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println(result.getName() + " Test skiped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println(result.getName() + " Test fail with success %");
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println(context.getName() + "Started");
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println(context.getName() + "Finished");
	}

}
