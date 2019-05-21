/**
 * 
 */
package com.testautomation.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.testautomation.resource.SeleniumWaits;
import com.testautomation.resource.Timeout;
import com.testautomation.utility.CommonFunctions;

/**
 * @author HamzaSohail
 *
 */
public class PassengerDetailPage {
	
	SeleniumWaits SeleniumWaits= new SeleniumWaits();
	CommonFunctions CommonFunctions=new CommonFunctions();
	WebDriver driver=FlightSearchPage.getDriver();
	String priceOfSelectedFlight = FlightSearchResult.selectedPriceOfFlight;
	private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(PassengerDetailPage.class);
	
	//Locators for the page of Passenger Details
	By totalPrice = By.xpath("//*[@id='root']/div[3]/div/div[2]/div/div[2]/div/div[1]/div[2]/div[6]/div/span[2]");
	By vatPrice = By.xpath("//*[@id='root']/div[3]/div/div[2]/div/div[2]/div/div[1]/div[2]/div[5]/div[2]/span[2]");
	By serviceFee = By.xpath("//*[@id='root']/div[3]/div/div[2]/div/div[2]/div/div[1]/div[2]/div[4]/div[2]/span[2]");
	By titleDropDown=By.xpath("//*[@id='travellerDetailsForm']/div[1]/div[2]/div[2]/div[2]/div[1]/div/div/div[1]/div/div/div");
	By dropDownMr=By.xpath("//*[@id='travellerDetailsForm']/div[1]/div[2]/div[2]/div[2]/div[1]/div/div/div[1]/div/ul/li[1]");
	By dropDownMs=By.xpath("//*[@id='travellerDetailsForm']/div[1]/div[2]/div[2]/div[2]/div[1]/div/div/div[1]/div/ul/li[2]");
	By dropDownMrs=By.xpath("//*[@id='travellerDetailsForm']/div[1]/div[2]/div[2]/div[2]/div[1]/div/div/div[1]/div/ul/li[3]");
	By firstName= By.name("travellers.0.firstName");
	By middleName= By.name("travellers.0.middleName");
	By lastName= By.name("travellers.0.lastName");
	By titleSecond= By.xpath("//*[@id='travellerDetailsForm']/div[1]/div[2]/div[3]/div[2]/div[1]/div/div/div[1]/div/div/div");
	By dropDownMrSecond= By.xpath("//*[@id='travellerDetailsForm']/div[1]/div[2]/div[3]/div[2]/div[1]/div/div/div[1]/div/ul/li[1]");
	By dropDownMsSecond= By.xpath("//*[@id='travellerDetailsForm']/div[1]/div[2]/div[3]/div[2]/div[1]/div/div/div[1]/div/ul/li[2]");
	By dropDownMrsSecond= By.xpath("//*[@id='travellerDetailsForm']/div[1]/div[2]/div[3]/div[2]/div[1]/div/div/div[1]/div/ul/li[3]");
	By firstNameSecond = By.name("travellers.1.firstName");
	By middleNameSecond= By.name("travellers.1.middleName");
	By lastNameSecond= By.name("travellers.1.lastName");
	By email= By.name("contact.email");
	By phoneNumber= By.name("contact.phoneNumber");
	By proceedButton = By.xpath("//button[contains(@data-testid, 'FlightPAX__ContinueToPaymentButton')]");
	By finalPageText=By.xpath("//*[@id='__next']/div[3]/div/div[2]/div/div[1]/form/div/div/div[1]");
	
	//Verifies the price of selected flight from cart
	public void verifyPriceOfFlight() throws InterruptedException{
		
		log.info("Going to verify the price of Flight");
		SeleniumWaits.waitForPageLoadComplete(driver,Timeout.largeTimeOut);
		SeleniumWaits.visibilityOfElementLocated(driver,serviceFee);
		SeleniumWaits.visibilityOfElementLocated(driver,totalPrice);
		
		String serviceCharges = driver.findElement(serviceFee).getText();
		String vatFee = driver.findElement(vatPrice).getText();
		String totalSum = driver.findElement(totalPrice).getText();
		 
		log.info("serviceCharges " + serviceCharges + " vatFee " + vatFee + " totalSum " + totalSum);
		 
		double selectedPrice=Double.parseDouble(priceOfSelectedFlight.replace(",", ""));
		double charges= Double.parseDouble(vatFee) + Double.parseDouble(serviceCharges);
		
		log.info("Total Charges " + charges);
		
		double totalPrice=Double.parseDouble(totalSum.replace(",", ""));
		
		log.info("Total Price Charged " + totalPrice);
		double effectivePrice=totalPrice-charges;
		Assert.assertEquals(effectivePrice,selectedPrice, "Price is Not Matching the Selected Price");
		
		log.info("Price of Flight Verified");
	}
	
	//Enter Title
	public void selectTitle(String title)
	{	
		log.info("Selecting Title");
		driver.findElement(titleDropDown).click();
		if(title.equals("Mr.")){
			driver.findElement(dropDownMr).click();
			}
		if(title.equals("Ms.")){
			driver.findElement(dropDownMs).click();
			}
		if(title.equals("Mrs.")){
			driver.findElement(dropDownMrs).click();
			}
		log.info("Title Selected " + title);
	}
	
	//Enter First Name
	public void setFirstName(String name)
	{
		log.info("Setting First Name" + name);
		driver.findElement(firstName).clear();
		driver.findElement(firstName).sendKeys(name);
	}

	//Enter Middle name
	public void setMiddleName(String name)
	{
		log.info("Setting Middle Name" + name);
		driver.findElement(middleName).clear();
		driver.findElement(middleName).sendKeys(name);
	}
	
	//Enter Last Name
	public void setLastName(String name)
	{
		log.info("Setting Last Name" + name);
		driver.findElement(lastName).clear();
		driver.findElement(lastName).sendKeys(name);
	}
	
	//Enter Title
	public void selectTitlesecondPassenger(String title)
	{	
		log.info("Selecting Title");
		driver.findElement(titleSecond).click();
		if(title.equals("Ms.")){
		driver.findElement(dropDownMsSecond).click();
		}
		if(title.equals("Mr.")){
			driver.findElement(dropDownMrSecond).click();
			}
		if(title.equals("Mrs.")){
			driver.findElement(dropDownMrsSecond).click();
			}
		log.info("Title Selected " + title);
	}
	
	//Enter First Name
	public void setFirstNamesecondPassenger(String name)
	{
		log.info("Setting First Name" + name);
		driver.findElement(firstNameSecond).clear();
		driver.findElement(firstNameSecond).sendKeys(name);
	}
	
	//Enter Middle Name
	public void setMiddleNamesecondPassenger(String name)
	{
		log.info("Setting Middle Name" + name);
		driver.findElement(middleNameSecond).clear();
		driver.findElement(middleNameSecond).sendKeys(name);
	}
	
	//Enter Last Name
	public void setLastNamesecondPassenger(String name)
	{
		log.info("Setting Last Name" + name);
		driver.findElement(lastNameSecond).clear();
		driver.findElement(lastNameSecond).sendKeys(name);
	}
	
	//Enter Email 
	public void email(String emailAddress)
	{
		log.info("Setting email : " + emailAddress);
		driver.findElement(email).clear();
		driver.findElement(email).sendKeys(emailAddress);
	}
	
	//Enter Phone Number
	public void conatctNumber(String number)
	{
		log.info("Setting Number : " + number);
		driver.findElement(phoneNumber).clear();
		driver.findElement(phoneNumber).sendKeys(number);
	}
	
	//Click Process Button
	public void clickProceedButton(){
		driver.findElement(proceedButton).click();
		SeleniumWaits.visibilityOfElementLocated(driver,finalPageText);

	}
	
	public void finalPageCheck(){
		
		log.info("Verifying that the last page is displayed");
		SeleniumWaits.visibilityOfElementLocated(driver,finalPageText);
		log.info("Last page displayed, now ending the test run");
	}
	// constructor
	public PassengerDetailPage(WebDriver driver)
	{
		FlightSearchPage.setDriver(driver);
		PageFactory.initElements(driver, this);
	}
}
