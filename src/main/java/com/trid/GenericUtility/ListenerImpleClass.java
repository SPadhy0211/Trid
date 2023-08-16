package com.trid.GenericUtility;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.io.Files;

public class ListenerImpleClass implements ITestListener {
	ExtentReports report;
	ExtentTest test;
	
	@Override
	public void onTestStart(ITestResult result) {
		//execution starts from here
		String methodName = result.getMethod().getMethodName();
		test = report.createTest(methodName);
		Reporter.log(methodName+"---> execution started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		test.log(Status.PASS, methodName+"-->passed");
		Reporter.log(methodName+"-->Testscript passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
	    String methodName = result.getMethod().getMethodName();
		Reporter.log(methodName + "Execution faild");
		TakesScreenshot ts = (TakesScreenshot)BaseClass.driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(".\\Screenshot\\"+methodName+"_"+new JavaUtility().getSystemDateInFormat()+".png");
		try
		{
			Files.copy(src, dest);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	    test.addScreenCaptureFromPath(dest.getAbsolutePath());
		test.log(Status.FAIL, methodName+"--> failed");
		test.log(Status.FAIL, result.getThrowable());
		Reporter.log(methodName+"---> Testscript failed");
		
		
//		try {
//			String methodName = WebDriverUtilities.getScreenShot(BaseClass.driver, result.getMethod().getMethodName());
//			test.addScreenCaptureFromPath(methodName+new JavaUtility().getSystemDateInFormat());
//		}
//		catch (Throwable e) {
//			e.printStackTrace();
//		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		test.log(Status.SKIP, methodName+"-->failed" );
		test.log(null, result.getThrowable());
		Reporter.log(methodName+"-->Testscript skipped");
	}

	@Override
	public void onStart(ITestContext context) {
		//create html report
	    ExtentSparkReporter htmlReport = new ExtentSparkReporter("./ExtentReport/report.html");
	    htmlReport.config().setDocumentTitle("Trid");
	    htmlReport.config().setTheme(Theme.DARK);
	    htmlReport.config().setReportName("Sales");
	    
	    report = new ExtentReports();
	    report.attachReporter(htmlReport);
	    report.setSystemInfo("OS", "Window");
	    report.setSystemInfo("Base-Browser", "Chrome");
	    report.setSystemInfo("Base-url", " http://rmgtestingserver/domain/Sales_And_Inventory_System/pages/login.php");
	    report.setSystemInfo("Reporter Name", "Sasmita");
	}

	@Override
	public void onFinish(ITestContext context) {
		//consolidate the report
		report.flush();
	}
	
	

}
