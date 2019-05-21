/**
 * 
 */
package com.testautomation.resource;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author HamzaSohail
 *
 */
public class SeleniumWaits {
	//function to wait until text matches the string given
		public boolean waitTillTextMatches(WebDriver driver, By element,String Value){
			
			WebDriverWait wait = new WebDriverWait(driver, Timeout.mediumTimeOut);
			try{
			assertTrue(wait.until(ExpectedConditions.textToBePresentInElementLocated(element, Value)));
			return true;
			 }catch (Exception e) {
					return false;
				}
		
		}
		
		
		//function waits for the element text to be equall to any specific string
		public boolean waitForElement(WebDriver driver,By element,String value,int specifiedTimeout) {
			   WebDriverWait wait = new WebDriverWait(driver,    specifiedTimeout);
			   try{
				   ExpectedCondition<Boolean> IsCorrect = arg0 -> driver.findElement(element).getText().equals(value);
			   wait.until(IsCorrect);
			   waitForPageLoadComplete(driver, specifiedTimeout);
			   return true;
			   }catch (Exception e) {
					return false;
				}
			   
			}
		
		
		//function waits fot the element to be visible
		public boolean visibilityOfElementLocated(WebDriver driver,By element){
				WebDriverWait wait = new WebDriverWait(driver,Timeout.largeTimeOut);
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(element));
					return true;
				} catch (Exception e) {
					return false;
				}
				
		}
	    
		//function waits for an element to be Invisible
		public boolean waitForTextToGetInvisiblble(WebDriver driver,By element,String text){
			
			WebElement elementToCheck = driver.findElement(element);
			WebDriverWait wait = new WebDriverWait(driver,Timeout.largeTimeOut);
			try{
			wait.until(ExpectedConditions.not(
			    ExpectedConditions.textToBePresentInElement(elementToCheck,text)));
			return true;
			}catch (Exception e) {
				return false;
			}
			
		}
		
		
		//Function that waits for page to load
		public void waitForPageLoadComplete(WebDriver driver, int specifiedTimeout){
			 ExpectedCondition<Boolean> pageLoadCondition = new
		                ExpectedCondition<Boolean>() {
		                    public Boolean apply(WebDriver driver) {
		                    	
		                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
		                    }
		                };
		        WebDriverWait wait = new WebDriverWait(driver, specifiedTimeout);
		       
		        wait.until(pageLoadCondition);
		        
		}
}
