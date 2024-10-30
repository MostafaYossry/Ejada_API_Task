#Author: mos.yossry.cufe@gmail.com
#Task for Ejada Company
# API task

Feature: API methods


  Scenario: Check the Books API
    Given The site is up and running (Check status API)
	  
	  When I need to check the list of all books
    Then I get list of books in response
	  
	  When I need to check single book with ID "1"
	  Then I get one book in response with ID "1"
	  
	  When I Submit an order
	  Then I get the created value is "true"
	  And I save the created ID
	  
	  When I need to check all my orders
	  Then I found one order with the saved ID
 	  And I need to view this order with the saved ID 
	  
	  When I need to update the order with the saved ID
	  Then I need to check the order details after updating
	  
	  When I need to delete this order
	  Then I can not find this order again
	  
	  