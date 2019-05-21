package com.testautomation.StepDef;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.testautomation.PageObjects.FlightSearchPage;
import com.testautomation.PageObjects.FlightSearchResult;
import com.testautomation.PageObjects.PassengerDetailPage;
import com.testautomation.listeners.ExtentReportListener;
import com.testautomation.utility.BrowserUtility;
import com.testautomation.utility.CommonFunctions;
import com.testautomation.utility.PropertiesFileReader;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class FlightSearchStepDef extends ExtentReportListener{
	
	public WebDriver driver;
	PropertiesFileReader obj= PropertiesFileReader.getInstance(); 
	CommonFunctions CommonFunctions = new CommonFunctions();
	ExtentTest logInfo=null;
	private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(FlightSearchStepDef.class);
	
	
	//Step definition for ""Open browser and enter url"
	@Given("^Open browser and enter url$")
	public void open_browser_and_enter_url() throws Throwable 
	{
		
		try {
			test = extent.createTest(Feature.class, "Flight Searching");							
			test=test.createNode(Scenario.class, "Search, Select Flight & Enter Details");						
			logInfo=test.createNode(new GherkinKeyword("Given"), "open_browser_and_enter_url");

			Properties properties=obj.getProperty(); 		
			driver=BrowserUtility.OpenBrowser(driver, properties.getProperty("browser.name"), properties.getProperty("browser.baseURL"), properties.getProperty("OS"));

			logInfo.pass("Opened browser and entered url");
			logInfo.addScreenCaptureFromPath(CommonFunctions.captureScreenShot(driver));			

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}		   

	}
	
	//Step definition for "Enter Origin Country"
	@When("^Enter Origin Country$")
	public void enter_origin_search_creteria() throws IOException 
	{
	try {
		logInfo=test.createNode(new GherkinKeyword("When"), "enter_origin_search_creterias");
		
		new FlightSearchPage(driver).enterOriginDetails(CommonFunctions.getRandomOrigin());   
		
		logInfo.pass("Entered Origin Country");
		logInfo.addScreenCaptureFromPath(CommonFunctions.captureScreenShot(driver));
		
	}catch (AssertionError | Exception e) {
		testStepHandle("FAIL",driver,logInfo,e);			
	}		   
	}

	//Step definition for "Enter Random Destination Country"
	@Then("^Enter Destination Country$")
	public void enter_destination_search_creteria() throws InterruptedException
	{					
		try {
		logInfo=test.createNode(new GherkinKeyword("Then"), "enter_destination_search_creteria");
		
		new FlightSearchPage(driver).enterDestinationDetails(CommonFunctions.getRandomDestination());  
		
		logInfo.pass("Entered Destination Country");
		logInfo.addScreenCaptureFromPath(CommonFunctions.captureScreenShot(driver));
		
		}catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}		   
	}
	
	//Step definition for "Select Departure Date"
	@Then("^Select Departure Dates$")
	public void enter_departure_date()
	{	
		try {
			logInfo=test.createNode(new GherkinKeyword("Then"), "enter_departure_date");
			
			new FlightSearchPage(driver).selectDepartureDate(); 
			
			logInfo.pass("Entered Departure Date Successfully");
			logInfo.addScreenCaptureFromPath(CommonFunctions.captureScreenShot(driver));
		
		}catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}		
	}
	
	//Step definition for "Select Return Date"
	@Then("^Select Return Date$")
	public void enter_return_date()
	{	
		try {
			logInfo=test.createNode(new GherkinKeyword("Then"), "enter_return_date");
			
			new FlightSearchPage(driver).selectReturnDate();
			
			logInfo.pass("Entered Departure Date Successfully");
			logInfo.addScreenCaptureFromPath(CommonFunctions.captureScreenShot(driver));
		
		}catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}		
	}
	
	//Step definition for "Select Number of Passenger"
	@Then("^Select Number of Passenger \"(.*)\"$")
	public void select_passenger_count(int counter)
	{	
		try {
			logInfo=test.createNode(new GherkinKeyword("Then"), "select_passenger_count");
			
			new FlightSearchPage(driver).setPassengerCount(counter);  
		
			logInfo.pass("Passsenger Count Updated Successfully");
			logInfo.addScreenCaptureFromPath(CommonFunctions.captureScreenShot(driver));
	
	}catch (AssertionError | Exception e) {
		testStepHandle("FAIL",driver,logInfo,e);			
	}		
	}
	
	//Step definition for "Select cabin Class"
	@Then("^Select class \"(.*)\"$")
	public void select_class(String cabinClass)
	{	
		try {
			logInfo=test.createNode(new GherkinKeyword("Then"), "select_class");
			
			new FlightSearchPage(driver).setCabinClass(cabinClass); 
			
			logInfo.pass("Cabin Class Selected Successfully");
			logInfo.addScreenCaptureFromPath(CommonFunctions.captureScreenShot(driver));
			
	}catch (AssertionError | Exception e) {
		testStepHandle("FAIL",driver,logInfo,e);			
	}		
	}
	
	//Step definition for "Click Search Button"
	@Then("^Click Search Button$")
	public void click_search_button()
	{
		try {
			logInfo=test.createNode(new GherkinKeyword("Then"), "click_search_button");
			
			new FlightSearchPage(driver).clickSearchButton();   
			
			logInfo.pass("Search Button Clicked Successfully");
			logInfo.addScreenCaptureFromPath(CommonFunctions.captureScreenShot(driver));
		
		}catch (AssertionError | Exception e) {
				testStepHandle("FAIL",driver,logInfo,e);			
	}		
	}

	//Step definition for "Verify search results on Result Page"
	@Then("^Verify search results$")
	public void verify_search_results() throws InterruptedException
	{
		try {
			logInfo=test.createNode(new GherkinKeyword("Then"), "verify_search_results");
			
			new FlightSearchResult(driver).verifySearchResults();
			
			logInfo.pass("Search Results Verified Successfully");
			logInfo.addScreenCaptureFromPath(CommonFunctions.captureScreenShot(driver));
		
		}catch (AssertionError | Exception e) {
		testStepHandle("FAIL",driver,logInfo,e);			
	}		
	}

	//Step definition for "filter carrier and verify all flights are for selected carrier"
	@Then("^I filter carrier and verify all flights are for selected carrier$")
	public void filter_carrier() throws InterruptedException
	{
		try {
			logInfo=test.createNode(new GherkinKeyword("Then"), "filter_carrier");
			
			new FlightSearchResult(driver).filterCarrier();
			
			logInfo.pass("Filtered Flight Carrier Successfully");
			logInfo.addScreenCaptureFromPath(CommonFunctions.captureScreenShot(driver));
	
		}catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}		
	}
	
	//Step definition for "Select Random Flight from list of flights"
	@Then("Select Random Flight")
	public void select_random_flight() throws InterruptedException 
	{
		try {
			logInfo=test.createNode(new GherkinKeyword("Then"), "select_random_flight");
			
			new FlightSearchResult(driver).selectRandomFlight();
			
			logInfo.pass("Random Flight Selected");
			logInfo.addScreenCaptureFromPath(CommonFunctions.captureScreenShot(driver));
			
		}catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}		
	}
	
	//Step definition for "Verify flight price from cart"
	@Then("Verify flight price from cart")
	public void verify_flight_price_from_cart() throws InterruptedException
	{	
		try {
			logInfo=test.createNode(new GherkinKeyword("Then"), "verify_flight_price_from_cart");
			
			new PassengerDetailPage(driver).verifyPriceOfFlight();
			
			logInfo.pass("Cart Price Verified Successfully");
			logInfo.addScreenCaptureFromPath(CommonFunctions.captureScreenShot(driver));
		}catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}		
	}

	//Step definition for "Enter Details of First Passenger"
	@Then("Enter Details of First Passenger {string} {string} {string} {string} {string} {string} {string} {string} {string} {string}")
	public void enter_Details_of_First_Passenger(String title, String firstName, String middleName, String lastname, String titlesecondPassenger, String firstnameSecondPassenger, String middlenameSecondPassenger, String lastenameSecondPassenger, String email, String contactNumber) {
		try {
			
		logInfo=test.createNode(new GherkinKeyword("Then"), "enter_Details_of_First_Passenger");		
		new PassengerDetailPage(driver).selectTitle(title);
		new PassengerDetailPage(driver).setFirstName(firstName);
		new PassengerDetailPage(driver).setMiddleName(middleName);
		new PassengerDetailPage(driver).setLastName(lastname);
		new PassengerDetailPage(driver).selectTitlesecondPassenger(titlesecondPassenger);
		new PassengerDetailPage(driver).setFirstNamesecondPassenger(firstnameSecondPassenger);
		new PassengerDetailPage(driver).setMiddleNamesecondPassenger(middlenameSecondPassenger);
		new PassengerDetailPage(driver).setLastNamesecondPassenger(lastenameSecondPassenger);
		new PassengerDetailPage(driver).email(email);
		new PassengerDetailPage(driver).conatctNumber(contactNumber);
		new PassengerDetailPage(driver).clickProceedButton();
		
		logInfo.pass("Passengers Details Entered Successfully");
		logInfo.addScreenCaptureFromPath(CommonFunctions.captureScreenShot(driver));
	}catch (AssertionError | Exception e) {
		testStepHandle("FAIL",driver,logInfo,e);			
		}		
	}
	
	//Step definition for "Sort Flight by Ascending Order & Check if the first price is lowest"
	@Then("Sort Flight by Ascending Order & Check if the first price is lowest")
	public void sort_Flight_by_Ascending_Order() throws InterruptedException {
		try {
			
			logInfo=test.createNode(new GherkinKeyword("Then"), "sort_Flight_by_Ascending_Order");
			
			new FlightSearchResult(driver).sortbyAscending();
			
			logInfo.pass("Sorted Flight by Ascending Order and Verified that first Flight Price is lowest");
			logInfo.addScreenCaptureFromPath(CommonFunctions.captureScreenShot(driver));
			
	}catch (AssertionError | Exception e) {
		testStepHandle("FAIL",driver,logInfo,e);			
		}		
	}

	//Step definition for "Save all Prices in a CSV File"
	@Then("Save all Prices in a CSV File")
	public void save_all_Prices_in_a_CSV_File() throws IOException {
		try {
			
			logInfo=test.createNode(new GherkinKeyword("Then"), "save_all_Prices_in_a_CSV_File");

			new FlightSearchResult(driver).open_file_and_write();
		
			logInfo.pass("All Price Listing Saved in File");
			logInfo.addScreenCaptureFromPath(CommonFunctions.captureScreenShot(driver));
		
		}catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
			}		
		}
	@Then("Verify last Page Load")
	public void verify_last_Page_Load() {
		try {
			
			logInfo=test.createNode(new GherkinKeyword("Then"), "verify_last_Page_Load");
			
			new PassengerDetailPage(driver).finalPageCheck();
			
			logInfo.pass("Final Page is Loaded");
			logInfo.addScreenCaptureFromPath(CommonFunctions.captureScreenShot(driver));
	
	}catch (AssertionError | Exception e) {
		testStepHandle("FAIL",driver,logInfo,e);			
		}		
	}
	
}