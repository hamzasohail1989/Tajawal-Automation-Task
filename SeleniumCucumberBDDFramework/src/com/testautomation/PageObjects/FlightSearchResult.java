/**
 * 
 */
package com.testautomation.PageObjects;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.opencsv.CSVWriter;
import com.testautomation.resource.SeleniumWaits;
import com.testautomation.resource.Timeout;
import com.testautomation.utility.CommonFunctions;
import com.testautomation.utility.PropertiesFileReader;

/**
 * @author HamzaSohail
 *
 */
public class FlightSearchResult{
	SeleniumWaits SeleniumWaits= new SeleniumWaits();
	CommonFunctions CommonFunctions=new CommonFunctions();
	PropertiesFileReader obj= PropertiesFileReader.getInstance(); 
	Properties properties=obj.getProperty();
	WebDriver driver=FlightSearchPage.getDriver();
	JavascriptExecutor jse = (JavascriptExecutor)driver;
	private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(FlightSearchResult.class);
	
	//Locators for the page Flight Search Result 
	By originCountry = By.xpath("//*[@id='root']/div[3]/div[1]/div[3]/div[1]/div[1]/div[2]");
	By destCountry = By.xpath("//*[@id='root']/div[3]/div[1]/div[3]/div[1]/div[2]/div[2]");
	By cabinClass = By.xpath("//*[@id='root']/div[3]/div[1]/div[3]/div[1]/div[6]/div[2]");
	By passengerCount = By.xpath("//*[@id='root']/div[3]/div[1]/div[3]/div[1]/div[5]/div[2]");
	By namesOfAirlineInList = By.xpath("//*[contains(@for,'0-')]");
	By flightButtons = By.xpath("//button[contains(@data-testid, '_SelectFlightButton')]");
	By flightPrices = By.xpath("//div[contains(@data-testid, '__PriceLabel')]");
	By emiratesCheckBox = By.xpath("//label[contains(@for,'0-EK')]");
	By emiratesCheckBox2 = By.xpath("//label[contains(@for,'1-EK')]");
	By firstAirlineCheckBox = By.xpath("//*[@id='root']/div[3]/div[1]/div[2]/div/div[7]/div[2]/div[2]/div[1]/label");
	By airlineListVisble = By.xpath("//div[contains(@data-testid, 'AirlineLabel')]");
	By sortButtonAscending = By.xpath("//a[contains(@data-testid,'FlightSearchResult__Sorting__Price—-LowestFirstSelected')]");
	By sortButtonDescending = By.xpath("//a[contains(@data-testid,'FlightSearchResult__Sorting__Price—-HighestFirstSelected')]");
	By firstAirlineCheckbox=By.xpath("//label[contains(@for,'1-')]");
	By departureDate=By.xpath("//div[contains(@data-testid, 'FlightSearchResults__DepartureDateLabel')]");
	By returnDate = By.xpath("//div[contains(@data-testid, 'FlightSearchResults__ArrivalDateLabel')]");
	By departureCarrier= By.xpath("//*[@id='root']/div[3]/div[1]/div[2]/div/div[7]/div[1]");
	By returnCarrier=By.xpath("//*[@id='root']/div[3]/div[1]/div[2]/div/div[7]/div[2]/div[3]");
	By firstFlightButton=By.xpath("//*[@id='root']/div[3]/div[1]/div[3]/div[3]/div/div/div/div/div/div/div/div[2]/div[2]/div/button");
	By secondFlightButton=By.xpath("//*[@id='root']/div[3]/div[1]/div[3]/div[3]/div/div/div[2]/div/div/div/div/div[2]/div/div/button");
	By messageDisplay=By.xpath("//*[@id='root']/div[3]/div[1]/div[1]/div/div[1]");
	
	
	//Variables to compare values
	String enteredOriginCountry = FlightSearchPage.originCountry;
	String enteredDestCountry = FlightSearchPage.destCountry;
	String enteredcabinClass = FlightSearchPage.cabinClass;
	int enteredPassengerCount=FlightSearchPage.passengerCount;
	public static String selectedPriceOfFlight;
	
	//This function asserts that the search result of flight is same as per the given input
	public void verifySearchResults() throws InterruptedException{
		log.info("Going to verify search results");
		
		SeleniumWaits.waitForPageLoadComplete(driver,Timeout.largeTimeOut);
		String loadingText = properties.getProperty("loading.text");
		SeleniumWaits.waitForTextToGetInvisiblble(driver,messageDisplay,loadingText);
		SeleniumWaits.visibilityOfElementLocated(driver,departureDate);
        
		String departueDateSearchResult=driver.findElement(departureDate).getText();
        String comparison=CommonFunctions.getDeapartureDateToCompare()+"/"+CommonFunctions.getMonthToCompare();
        String[]  departureDateArraying=departueDateSearchResult.split(", ");
        
        Assert.assertEquals(departureDateArraying[1],comparison,"Departure Dates are not as same as selected"); 
        
        String returnDateSearchResult=driver.findElement(returnDate).getText();
        String comparisonOfDates=CommonFunctions.getReturnDateToCompare()+"/"+CommonFunctions.getMonthToCompare();
        String[]  returnDateArraying=returnDateSearchResult.split(", ");
        
        Assert.assertEquals(returnDateArraying[1],comparisonOfDates,"Return Dates are not as same as selected"); 
        Assert.assertEquals(driver.findElement(originCountry).getText(),enteredOriginCountry,"Origin Country is not same as that Selected");  
 		Assert.assertEquals(driver.findElement(destCountry).getText(),enteredDestCountry,"Destination Country is not same as that Selected"); 
 		Assert.assertEquals(driver.findElement(cabinClass).getText(),enteredcabinClass,"Cabin Class is not same as that Selected");
 		Assert.assertEquals(driver.findElement(passengerCount).getText(),Integer.toString(enteredPassengerCount),"Passenger Count is not same as that Selected"); 
 		
 		log.info("All Assertions Passed, the flight is as per given input cretiaria");
	}
	
	
	//This Function selects the Airline Carrier
	public void filterCarrier() throws InterruptedException {		
		
		String nameofAirline = null;
		String browser = properties.getProperty("browser.name");
		
		List<WebElement> airlinesList = driver.findElements(namesOfAirlineInList);	
		boolean carrierFound = false;
		
		log.info("Going to filter Flight Carriers");
		
		if(browser.equals("Chrome")){
			WebElement element = driver.findElement(departureCarrier);  
			jse.executeScript("arguments[0].scrollIntoView(true);",element);
		}
		
		for (int i = 0; i < airlinesList.size(); i++) {
			String airlineList = airlinesList.get(i).getText();
			log.info("Airlines in List" + airlineList);
				if (airlineList.contains("EK: Emirates")){												

					log.info("Going to click emirates airline checkbox");
					
					if(browser.equals("Firefox")||browser.equals("Chrome")){
					
					 boolean clicked = false;
				        do{
				            try {
				            	driver.findElement(emiratesCheckBox).click();
				            } catch (WebDriverException e) {
				                continue;
				            } finally {
				                clicked = true;
				                carrierFound=true;	
				            }
				        } while (!clicked);
				
				        boolean clicked2 = false;
				        do{
				            try{
				            	if(browser.equals("Chrome")){
				            		jse.executeScript("window.scrollTo(0,0)");
				            		WebElement element = driver.findElement(returnCarrier);  
				            		jse.executeScript("arguments[0].scrollIntoView(true);",element);
				            		SeleniumWaits.visibilityOfElementLocated(driver,returnCarrier);
				            		SeleniumWaits.visibilityOfElementLocated(driver,emiratesCheckBox2);
				            	}
				            	driver.findElement(emiratesCheckBox2).click();
				            } catch (WebDriverException e) {
				                continue;
				            } finally {
				                clicked2 = true;
				                carrierFound=true;		
				            }
				        } while (!clicked2);
					}
						nameofAirline="Emirates";
						checklist(nameofAirline);
				}
				}
		
				//following condition will work if Emirates Flights are not Available
				if(carrierFound==false){
					WebElement test =driver.findElement(firstAirlineCheckBox);					
					nameofAirline= test.getText();
					String strArray[] = nameofAirline.split(": ");
					nameofAirline=strArray[1];
					
					log.info("Name of Carrier" + strArray[1]);
					log.info("name of Airline that to be selected" + nameofAirline);					
					log.info("Going to click other airline checkbox");
					
					if(browser.equals("Firefox")||browser.equals("Chrome")){
					
					boolean clicked = false;
				        do{
				            try {
				            	driver.findElement(firstAirlineCheckBox).click();
				            } catch (WebDriverException e) {
				                continue;
				            } finally {
				                clicked = true;
				                carrierFound=true;		
				            }
				        } while (!clicked);
				        boolean clicked2 = false;
				        do{
				            try {
				            	if(browser.equals("Chrome")){
				            		jse.executeScript("window.scrollTo(0,0)");
				            		WebElement element = driver.findElement(returnCarrier);  
				            		jse.executeScript("arguments[0].scrollIntoView(true);",element);
				            		SeleniumWaits.visibilityOfElementLocated(driver,returnCarrier);
				            		}
				            	driver.findElement(firstAirlineCheckbox).click();
				            } catch (WebDriverException e) {
				                continue;
				            } finally {
				                clicked2 = true;
				                carrierFound=true;		
				            }
				        } while (!clicked2);   
				        
					}
				}
				checklist(nameofAirline);
			}
	
	
	//Asserts if the list of airline is that of the selected Carrier	
	public void checklist(String airlineName) throws InterruptedException{
		jse.executeScript("window.scrollTo(0,0)");
		
		log.info("Verifying list of airlines");		
		SeleniumWaits.visibilityOfElementLocated(driver,airlineListVisble); 
		
		//Get List of all flights in listing page and assert that flight is Emirates
		List<WebElement> listingAirlines = driver.findElements(airlineListVisble);	
		log.info("Lis size of Airlines" + listingAirlines.size());
		
		for (int i = 0; i < listingAirlines.size(); i++) {
				log.info("Visible List of Airline"+listingAirlines.get(i).getText());
				
				String listingAirline = listingAirlines.get(i).getText();
				Assert.assertEquals(listingAirline,airlineName,"List does not contains only selected Carrier Flights");	
			}
		log.info("List contains only selected Carrier Flights");
		}	
	
	//This function selects random flight from the list of flights available
		public void selectRandomFlight() throws InterruptedException{
			
			log.info("Going to select Flight Number");
			
			List<WebElement> buttons = driver.findElements(flightButtons);
			int randomFlight = CommonFunctions.randomNumber(2);
			jse.executeScript("window.scrollTo(0,0)");
			log.info("Going to select Flight Number: "+ randomFlight);
			if(buttons.size()==1||randomFlight==0){
				selectedPriceOfFlight=selectedFlightPrice(0);
				log.info("Going to Select Flight # 1");
				SeleniumWaits.visibilityOfElementLocated(driver,firstFlightButton);
				driver.findElement(firstFlightButton).click();
			}
			if(buttons.size()>1&&randomFlight==1){	
				selectedPriceOfFlight=selectedFlightPrice(1);
				log.info("Going to select Flight # 2");
				SeleniumWaits.visibilityOfElementLocated(driver,secondFlightButton);
				driver.findElement(secondFlightButton).click();
			}
							
	}
		
	//This function gets list of all flights and then saves price of the flight that is randomly selected
		public String selectedFlightPrice(int index){
			List<WebElement> price = driver.findElements(flightPrices);
			
			log.info("Selecting flight price at index" + index);
			
			String selectedPrice = price.get(index).getText();
			selectedPriceOfFlight=selectedPrice;
			log.info("The Price of Selected Flight is" + selectedPriceOfFlight);
			return selectedPrice;
		}
		
	//Sort Flight by Ascending Order & Check if the first flight price is lowest
	public void sortbyAscending() throws InterruptedException{
		
		SeleniumWaits.waitForPageLoadComplete(driver,Timeout.largeTimeOut);
		String loadingText = properties.getProperty("loading.text");
		SeleniumWaits.waitForTextToGetInvisiblble(driver,messageDisplay,loadingText);
		SeleniumWaits.visibilityOfElementLocated(driver,sortButtonAscending);
	
		
		log.info("Verifying Sorting");
		
		WebElement isSorted = driver.findElement(sortButtonAscending);
		
		if(isSorted!=null){
			log.info("Flight is sorted by price");
		}
		else{
			driver.findElement(sortButtonDescending).click();
		}
		
		String priceofFirstFlight=selectedFlightPrice(1);
		int lowestPrice=Integer.parseInt(priceofFirstFlight.replace(",", ""));
		log.info("Price of First Fight " + priceofFirstFlight);
		
				
		List<WebElement> priceList = driver.findElements(flightPrices);
		for (int i = 1; i < priceList.size(); i++) {

			if (Integer.parseInt(priceList.get(i).getText().replace(",", "")) < lowestPrice) {
				lowestPrice = Integer.parseInt(priceList.get(i).getText());
				log.info("Price is not lowest, the lowest price is" + lowestPrice);
				
			}
			else{
				log.info("Price is lowest");
			}
		}
		
	}
	
	//Saves all price listing in the file of csv format
	public void open_file_and_write() throws IOException{
		 String csv = "./Reports/CSVFiles/data"+ CommonFunctions.getcurrentdateandtime() +".csv";
		 List<WebElement> priceList = driver.findElements(flightPrices);
			for (int i = 0; i < priceList.size() ; i++) {
			String price=priceList.get(i).getText();	
		    CSVWriter writer = new CSVWriter(new FileWriter(csv, true));
		    String [] record = price.split(" ");
		    writer.writeNext(record);		        
		    writer.close();
			}
			log.info("END of data writing in CSV File");

	}
	
	//Constructor
	public FlightSearchResult(WebDriver driver)
	{
		FlightSearchPage.getDriver();
		PageFactory.initElements(driver, this);
	}
	
	}
	
	
	



