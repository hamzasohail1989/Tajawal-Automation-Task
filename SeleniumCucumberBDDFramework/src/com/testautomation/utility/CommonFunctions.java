/**
 * 
 */
package com.testautomation.utility;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testautomation.PageObjects.FlightSearchPage;
import com.testautomation.StepDef.FlightSearchStepDef;
import com.testautomation.resource.Timeout;

/**
 * @author HamzaSohail
 *
 */
public class CommonFunctions {
	
	PropertiesFileReader obj = PropertiesFileReader.getInstance();
	private static String monthToCompare;
	private static String returnDateToCompare;
	private static String deapartureDateToCompare;
	private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CommonFunctions.class);
	
	//Function to get Random Element from Array
	public String ramdomElementFromArray(String str) {

		String strArray[] = str.split(",");

		System.out.println("String converted to String array");

		// print elements of String array
		for (int i = 0; i < strArray.length; i++) {
			log.info(strArray[i]);
		}

		int randomArrayIndex = randomNumber(strArray.length);
		return strArray[randomArrayIndex];

	}

	//Function to get Random Number
	public int randomNumber(int MaxLimit) {
		Random rand = new Random();
		int randNum = rand.nextInt(MaxLimit);
		return randNum;
	}

	//Function to get random Origin from the list provided in .properties file
	public String getRandomOrigin() {
		Properties properties = obj.getProperty();
		String originCountries = properties.getProperty("originCountries");
		return ramdomElementFromArray(originCountries);
	}
	
	//Function to get random Destination from the list provided in .properties file
	public String getRandomDestination(){
		Properties properties = obj.getProperty();
		String destCountries = properties.getProperty("destCountries");
		return ramdomElementFromArray(destCountries);
	}
	
	//Format Number
	public String formatNumber(String string){
		
		string=string.replace(',','\0');
		return string;
		
	}
	
	
	//Function takes and saves ScreenShot
	public static String captureScreenShot(WebDriver driver) throws IOException {
			TakesScreenshot screen = (TakesScreenshot) driver;
			File src = screen.getScreenshotAs(OutputType.FILE);
			String dest = "./Reports/ScreenShots/" + getcurrentdateandtime() + ".jpeg";
			File target = new File(dest);
			FileUtils.copyFile(src, target);
			return dest;
		}
		
	//Function to return current time and date
	public static String getcurrentdateandtime() {
			String str = null;
			try {
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SSS");
				Date date = new Date();
				str = dateFormat.format(date);
				str = str.replace(" ", "").replaceAll("/", "").replaceAll(":", "");
			} catch (Exception e) {
			}
			return str;
		}
		
	//Function that Returns random Dates of Departure and  Date of Arrival	(Month selected is the next from the Current Month)
	public void randomDates(){
			Calendar now = Calendar.getInstance();
			int Year=now.get(Calendar.YEAR);
			String month="";
			//Month selected is the next from the Current Month
			int mymonth = now.get(Calendar.MONTH) + 2;
			if(mymonth<10){
				month="0"+mymonth;
			}
			else{
				month=Integer.toString(mymonth);
			}
					    
			String date="";
			// Selects Random date from number0-22, for departure date 
			int dateSelection = randomNumber(23);

		   //If the random date is 0 then add 2 to make date 02
		    if(dateSelection==0){
		    	dateSelection=dateSelection+2;
		    	date=Integer.toString(dateSelection);
		    	date="0"+date;
		    	
		    }
		    if(dateSelection<10&&dateSelection!=0){
		    	date="0"+dateSelection;	
		    	log.info("After appending date is " + date);
		    }
		    if(dateSelection>=10){		    	
		    	date=Integer.toString(dateSelection);
		    }
		     setDeapartureDateToCompare(date);
			 log.info("Selected Date is : " + date);
			 FlightSearchPage.randomDateDeparture = ""+Year+"-"+month+"-"+date+"";
			 
			 String returndate="";
			
			 // return date is increment by 1 if less then 8 and by 2 if greater or equal to 8.
			 if(dateSelection<8){
			 int retrunDate=dateSelection+1;
			 returndate="0"+retrunDate;
			 }
			 else if(dateSelection>=8){
				 returndate=Integer.toString((dateSelection+2));
			 }
			 log.info("Return date is " + returndate);
			 
			 setMonthToCompare(month);
			 setReturnDateToCompare(returndate);
			 
			 FlightSearchPage.randomDateReturn = ""+Year+"-"+month+"-"+returndate+"";
			  				
		}

	
	//function to get Return Date
	public static String getReturnDateToCompare() {
		return returnDateToCompare;
	}
	
	//function to set Return Date
	public static void setReturnDateToCompare(String returnDateToCompare) {
		CommonFunctions.returnDateToCompare = returnDateToCompare;
	}
	
	//function to get Month 
	public static String getMonthToCompare() {
		return monthToCompare;
	}
	
	//function to set Month 
	public static void setMonthToCompare(String monthToCompare) {
		CommonFunctions.monthToCompare = monthToCompare;
	}
	
	//function to set Departure Date
	public static String getDeapartureDateToCompare() {
		return deapartureDateToCompare;
	}

	//function to set Departure Date
	public static void setDeapartureDateToCompare(String deapartureDateToCompare) {
		CommonFunctions.deapartureDateToCompare = deapartureDateToCompare;
	}

}