@TajawalSecondScenario
Feature: Search Flight	
	Scenario: Flight Search possitive scenario
	Given Open browser and enter url
	When Enter Origin Country
	Then Enter Destination Country
	Then Select Departure Dates
	Then Select Return Date
	 #Dynamic Number of passenger Selection can be made by changing this value, current is 2
	Then Select Number of Passenger "2"
	Then Select class "Economy"
	Then Click Search Button
	Then Sort Flight by Ascending Order & Check if the first price is lowest
	Then Save all Prices in a CSV File
	
