@TajawalFirstScenario
Feature: Search Flight	
	Scenario Outline: Flight Search possitive scenario
	Given Open browser and enter url
	When Enter Origin Country
	Then Enter Destination Country
	Then Select Departure Dates
	Then Select Return Date
	 #Dynamic Number of passenger Selection can be made by changing this value, current is 2
	Then Select Number of Passenger "2"
	Then Select class "Economy"
	Then Click Search Button
	Then Verify search results
	Then I filter carrier and verify all flights are for selected carrier
	Then Select Random Flight
	Then Verify flight price from cart
	Then Enter Details of First Passenger "<title>" "<fisrtName>" "<middleName>" "<lastName>" "<tileSecondPassenger>" "<firstnameSecondPassenger>" "<middlenameSecondPassenger>" "<lastenameSecondPassenger>" "<email>" "<contactNumber>"
	Then Verify last Page Load 

Examples:
|title |fisrtName|middleName|lastName|tileSecondPassenger|firstnameSecondPassenger	|middlenameSecondPassenger |lastenameSecondPassenger| email				|contactNumber|
| Mr.  | Hamza 	 |Sheikh    |Sohail  |	Ms.			 |Sheikh					|Muhammad				   |Ramdan					|myemail@hotmail.com| 80788865	  |

	 