package com.testautomation.TestRunner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions (
        features = "./features/"
        ,glue = {"com.testautomation.StepDef"}
        ,tags = {"@TajawalFirstScenario"},plugin = {"pretty", "html:target/cucumber/", "json:target/cucumber.json"},
        monochrome = true)

public class TestRunner extends AbstractTestNGCucumberTests{

	
}