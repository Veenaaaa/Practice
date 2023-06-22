package genericUtilities;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenersImplementation implements ITestListener{
	
	ExtentReports report;
	ExtentTest test;

	public void onTestStart(ITestResult result) {
		String methodName=result.getMethod().getMethodName();
		System.out.println(methodName+" test execution started");
		test=report.createTest(methodName);
		test.log(Status.INFO, methodName+" test execution started");
		
	}

	public void onTestSuccess(ITestResult result) {
		String methodName=result.getMethod().getMethodName();
		System.out.println(methodName+ " test execution success");
		test.log(Status.PASS, methodName+" test execution success");
		
		
	}

	public void onTestFailure(ITestResult result) {
		String methodName=result.getMethod().getMethodName();
		System.out.println(methodName+" test execution failed");
		System.out.println(result.getThrowable());
		test.log(Status.FAIL, methodName+" fail");
		test.log(Status.WARNING, result.getThrowable());
		WebDriverUtility wUtil=new WebDriverUtility();
		try {
			wUtil.takeScreenshot(BaseClass.sDriver, methodName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		String methodName=result.getMethod().getMethodName();
		System.out.println(methodName+" test execution skipped");
		System.out.println(result.getThrowable());
		test.log(Status.FAIL, methodName+" skipped");
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	public void onStart(ITestContext context) {
		System.out.println("test execution started");
		ExtentSparkReporter html=new ExtentSparkReporter(".\\ExtentReports\\Report.html");
		html.config().setDocumentTitle("vTiger execution result");
		html.config().setReportName("execution report");
		html.config().setTheme(Theme.DARK);
		
		report=new ExtentReports();
		report.attachReporter(html);
		report.setSystemInfo("base browser", "chrome");
		report.setSystemInfo("base url", "http://testENV.com");
		report.setSystemInfo("Base platform", "windows-family");
		report.setSystemInfo("reporter Name", "Veena");
		
		
		
	}

	public void onFinish(ITestContext context) {
		System.out.println("test execution finished");
		report.flush();
		
	}
	

}
