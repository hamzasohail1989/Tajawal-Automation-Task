/**
 * 
 */
package com.testautomation.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.testautomation.utility.CommonFunctions;
import com.testautomation.resource.SeleniumWaits;

/**
 * @author HamzaSohail
 *
 */
public class FlightSearchPage {
	
	private static WebDriver driver;
	public static String cabinClass;
	public static String originCountry;
	public static String destCountry;
	public static int passengerCount;
	public static String randomDateDeparture;
	public static String randomDateReturn;
	private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(FlightSearchPage.class);
	
	//Following are the locators for the page Flight Search/Hone Page
	By roundTripButton = By.xpath("//div[contains(@data-testid, 'FlightSearchBox__RoundTripButton')]");
	By originField = By.cssSelector(".wt4csw-5 > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > input:nth-child(2)");
	By destinationField = By.cssSelector(".wt4csw-6 > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > input:nth-child(2)");															
	By dropDownSelectionOrigin = By.cssSelector("li.AutoComplete__ListItem:nth-child(2) > div:nth-child(1)");
	By dropDownSelectionDestination = By.cssSelector("li.AutoComplete__ListItem:nth-child(2) > div:nth-child(1)");	
	By datePicker= By.cssSelector("div.sc-5uo3xu-2:nth-child(1)");		
	By numberofPaasengerDropDown = By.cssSelector(".FlightSearchBox__PaxCounterToggle");
	By passengerCurrentcounter=By.cssSelector("div.sc-1o8lb20-5:nth-child(1) > div:nth-child(2) > div:nth-child(2)");
	By passengerCountIncreaseButton = By.cssSelector("div.sc-1o8lb20-5:nth-child(1) > div:nth-child(2) > div:nth-child(3)");
	By passengerCountDecreaseButton = By.cssSelector("div.sc-1o8lb20-5:nth-child(1) > div:nth-child(2) > div:nth-child(1)");
	By searchButton = By.cssSelector(".col-lg-4");
	By cabinClassValue = By.cssSelector(".FlightSearchBox__CabinToggle");
	By cabinClassToggleButton = By.cssSelector(".FlightSearchBox__CabinToggle");
	By economyClass = By.cssSelector("a.sc-1sn5k4t-3:nth-child(1)");
	private By datePickerHelper(String randomDate) {
        return By.xpath("//span[contains(@data-testid, 'FlightSearchCalendar__"+randomDate+"')]");
    }
	
	CommonFunctions CommonFunctions=new CommonFunctions();
	SeleniumWaits SeleniumWaits= new SeleniumWaits();
	
	//Finds the location of Element Origin Field & Enters the Randomly Selected Country & saves the value for further comparison
	public void enterOriginDetails(String inputOriginCountry) throws InterruptedException
	{
		//verifying if the Flight Search page is Opened
		log.info("Verifying the correct page os displayed");
		verifyFlightSearchPage(driver,searchButton);
		
		//verifying and clicking Round Trip Button
		log.info("verifying and clicking Round Trip Button");
		verifyRoundTripSelection(driver,roundTripButton);
		
		log.info("Going to find the location of Element Origin Field & Enters the Randomly Selected Country" + inputOriginCountry);
		getDriver().findElement(originField).clear();
		getDriver().findElement(originField).sendKeys(inputOriginCountry);
		SeleniumWaits.waitTillTextMatches(driver,dropDownSelectionOrigin,inputOriginCountry);
		driver.findElement(dropDownSelectionOrigin).click();
		originCountry=inputOriginCountry;
		log.info("Entered the location of Element Origin Field & Enters the Randomly Selected Country" + inputOriginCountry);
	}
	
	//Finds the location of Element Destination Field & Enters the Randomly Selected Country & saves the value for further comparison
	public void enterDestinationDetails(String inputDestCountry) throws InterruptedException
	{
		log.info("Going to find the location of Element Destination Field & Enters the Randomly Selected Country" + inputDestCountry);
		getDriver().findElement(destinationField).clear();
		getDriver().findElement(destinationField).sendKeys(inputDestCountry);
		SeleniumWaits.waitTillTextMatches(driver,dropDownSelectionDestination,inputDestCountry);
		getDriver().findElement(dropDownSelectionDestination).click();
		destCountry=inputDestCountry;
		log.info("Entered the location of Element Destination Field & Enters the Randomly Selected Country" + inputDestCountry);
	}
	
	//Selects Departure Date
	public void selectDepartureDate()
	{	
		log.info("Going to Select Departure Date");
		getDriver().findElement(datePicker).click();
		CommonFunctions.randomDates();
		getDriver().findElement(datePickerHelper(randomDateDeparture)).click();		
		log.info("Departure Date Selected");
	}

	//Selects Arrival Date
	public void selectReturnDate()
	{
		log.info("Going to Select Arrival Date");
		getDriver().findElement(datePickerHelper(randomDateReturn)).click();	
		log.info("Arrival Date Selected");
	}
	
	//Function takes # of passengers as input & sets the count by increasing/decreasing accordingly & save count for further comparison
	public void setPassengerCount(int requiredCount)
	{
		getDriver().findElement(numberofPaasengerDropDown).click();	
		WebElement counter=getDriver().findElement(passengerCurrentcounter);
		Integer currentCount = Integer.valueOf(counter.getText());		
		
		log.info("Going to set passenger count as :" + requiredCount);
		
		while(currentCount != requiredCount){
		if(currentCount < requiredCount)
		{
			getDriver().findElement(passengerCountIncreaseButton).click();	
			currentCount++;
		}
		if(currentCount > requiredCount)
		{
			getDriver().findElement(passengerCountDecreaseButton).click();
			currentCount--;
		}
		}
		getDriver().findElement(numberofPaasengerDropDown).click();
		passengerCount=requiredCount;
		
		log.info("Passenger count is set as :" + requiredCount);
	}
	
	//Function sets value of Cabin Class
	public void setCabinClass(String cabin)
	{
		log.info("Going to set cabin class as :" + cabin);
		
		String valueCabinClass=getDriver().findElement(cabinClassValue).getText();	
		
		if(valueCabinClass!=cabin){
			getDriver().findElement(cabinClassToggleButton).click();	
			getDriver().findElement(economyClass).click();	
			cabinClass=cabin;
		}
		log.info("Cabin Class is set as :" + cabin);
	}
	
	//Function finds location of Search Button & Clicks it
	public void clickSearchButton()
	{
		getDriver().findElement(searchButton).click();		
	}
	// Driver get function
	public static WebDriver getDriver() {
		return driver;
	}
	
	// Driver set function
	public static void setDriver(WebDriver driver) {
		FlightSearchPage.driver = driver;
	}
	//constructor
	public FlightSearchPage(WebDriver driver)
	{
		FlightSearchPage.setDriver(driver);
		PageFactory.initElements(driver, this);
	}	
	
	public boolean verifyRoundTripSelection(WebDriver driver, By element){
		try{	
			 SeleniumWaits.visibilityOfElementLocated(driver, element);
			 driver.findElement(element).click();
			 return true;
			}catch (Exception e) {
				return false;
			}
		
	}
	
	//funcation to verify that Flight Search Page is opened
	public boolean verifyFlightSearchPage(WebDriver driver, By element){
		try{	
		 SeleniumWaits.visibilityOfElementLocated(driver, element);
		 return true;
		}catch (Exception e) {
			return false;
		}
	   
		
		
	}
	
	
}
