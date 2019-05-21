/**
 * 
 */
package com.testautomation.listeners;


import java.io.IOException;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.testautomation.StepDef.FlightSearchStepDef;
import com.testautomation.utility.CommonFunctions;


/**
 * @author Sheikh Hamza Sohail
 *
 */
public class ExtentReportListener{
	public static ExtentHtmlReporter report = null;
	public static ExtentReports extent = null;
	public static ExtentTest test = null;
	private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ExtentReportListener.class);
	
	public static ExtentReports setUp() {
		String reportLocation = "./Reports/Extent_Report.html";
		report = new ExtentHtmlReporter(reportLocation);		
		report.config().setDocumentTitle("Tajawal Assignement Test Report");
		report.config().setReportName("Tajawal Assignement Test Report");
		report.config().setTheme(Theme.STANDARD);		
		log.info("Extent Report location initialized . . .");
		report.start();
		
		extent = new ExtentReports();
		extent.attachReporter(report);		
		extent.setSystemInfo("Application", "Tajawal.com");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		log.info("System Info. set in Extent Report");		
		return extent;
	}
	
	//Test Step Handle in case of Test case is passed or Failed
	public static void testStepHandle(String teststatus,WebDriver driver,ExtentTest extenttest,Throwable throwable) {
		if(teststatus=="FAIL"){
				
			extenttest.fail(MarkupHelper.createLabel("Test Case is Failed : ", ExtentColor.RED));			
			extenttest.error(throwable.fillInStackTrace());
			
			try {
				extenttest.addScreenCaptureFromPath(CommonFunctions.captureScreenShot(driver));
				} catch (IOException e) {
				e.printStackTrace();
				}
			
			if (driver != null) {
				driver.quit();
			}		
		
		else if(teststatus=="PASS"){			
			extenttest.pass(MarkupHelper.createLabel("Test Case is Passed : ", ExtentColor.GREEN));
		}
		}	
		}
	
	
	
}
