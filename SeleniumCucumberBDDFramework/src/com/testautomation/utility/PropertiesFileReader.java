package com.testautomation.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;




public class PropertiesFileReader {
	/**
	 * static Singleton instance.
	 */
	private static volatile PropertiesFileReader instance;

	/**
	 * Private constructor for singleton.
	 */
	private PropertiesFileReader() {
	}

	/**
	 * Return a singleton instance of PropertiesFileReader.
	 */
	public static PropertiesFileReader getInstance() {
		// Double lock for thread safety.
		if (instance == null) {
			synchronized (PropertiesFileReader.class) {
				if (instance == null) {
					instance = new PropertiesFileReader();
				}
			}
		}
		return instance;
	} 
	
	//Function to read properties file
	public Properties getProperty() 
	{
        Properties properties = new Properties();
        try {        	 
            properties.load(new FileInputStream("resources/test-configs.properties"));
            
        } catch (Exception e) {
            System.out.println("Exception: " + e);
       } 
         return properties;	
	}

}
