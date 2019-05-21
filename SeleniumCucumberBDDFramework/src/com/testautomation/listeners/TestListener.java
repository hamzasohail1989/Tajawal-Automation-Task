/**
 * 
 */
package com.testautomation.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.testautomation.PageObjects.FlightSearchPage;

/**
 * @author HamzaSohail
 *
 */
public class TestListener  extends ExtentReportListener implements ITestListener{

	private static ExtentReports extent;
	private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ExtentReportListener.class);
	
	//Action on Test Start
	public void onTestStart(ITestResult result) {
		log.info("Starting Test");
	}

	//Action on Test Success
	public void onTestSuccess(ITestResult result) {
		log.info("Passed");
	}

	//Action on Test Failure
	public void onTestFailure(ITestResult result) {
		log.info("Test Fail");
	}

	//Action on Test Skipped
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		log.info("Skipped");
	}

	//
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	//Action on Start of Execution
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		log.info("Starting test");
		extent = setUp();
	}

	//Action on Finish Execution
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		log.info("Ending");
		extent.flush();
		FlightSearchPage.getDriver().quit();
	}

}
