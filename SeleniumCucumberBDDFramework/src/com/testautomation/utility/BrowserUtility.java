/**
 * 
 */
package com.testautomation.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * @author HamzaSohail
 *
 */
public class BrowserUtility {

	
	//Opens Browser Based on Browser Name and Operating System that is set in properties file
	public static WebDriver OpenBrowser(WebDriver driver,String browserName,String url, String OS) throws InterruptedException
	{
		
		if(browserName.equals("Chrome"))
		{
			if(OS.equals("mac")){
				System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver");
			}
			if(OS.equals("windows")){
				System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
			}
			driver=new ChromeDriver();
			//driver.manage().window().maximize();
			driver.get(url);
			return driver;
			
		}else if(browserName.equals("Firefox"))
		{
			if(OS.equals("mac")){
				System.setProperty("geckodriver", "./Driver/geckodriver");
			}
			if(OS.equals("windows")){
				System.setProperty("geckodriver", "./Driver/geckodriver.exe");
			}
			driver=new FirefoxDriver();
			driver.manage().window().maximize(); 
			driver.get(url);
			return driver;	
		}
			return null;			
	}
}
