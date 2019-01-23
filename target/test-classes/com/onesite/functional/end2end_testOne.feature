Feature: Automated End2End Tests

Description: The purpose of this feature is to test End 2 End integration.

Background: User is Logged In
 Given with application url I will navigate to the main search page
 
@read_testSample  
Scenario: verify number computers in the list
 When my first test step I will get the number computers in the table
 
  
@read_test1  
Scenario Outline: verify number computers in the list
 When I will get the number of computers in the table
 #Then I will verify expected computers '<number_of_computers>' is equal to actual number of computers

Examples:
 |number_of_computers |
 |574						     	|


@createComputerScenario
Scenario Outline: create a computer and save it database
 When my first step I will click add new computer button in create page
 Then I will enter computer name "<computer_name>" in create page
 And I will enter introduced date "<intro_date>" in create page
 And I will enter discontinued date "<discontiue_date>" in create page
 And I will select company name at index 2 from the list of companies from create page
 And I will click create computer button in create page
 And I will save the new computer name with key "computerName" and value "<computer_name>" in json file using scenario name "createComputerScenario" 

Examples:
 |computer_name                   |  intro_date   | discontiue_date  | index_number  |
 |onesite_bkupx 						     	|  2019-01-23   | 2019-05-22       |       2       |


 
@readComputerScenario
Scenario Outline: search for the computer you created in the above scenario, expected computer name is created in above scenario
 When I will enter computer name from my previous scenario using key "computerName" and "<scenarioDataOutput>" file in read page
 Then I will click filter by name button in read page
 And I will get the number of computers in the table from read page
 And I will verify my expected computer name is equal to actual computer name in the filter list in read page

Examples:
 |computer_name 		  					| scenarioDataOutput     |
 |onesite_bkup						     	| createComputerScenario |
 
 
 
 
@deleteComputerScenario
Scenario Outline: search for the computer you created in the above scenario and delete computer.
 When I will enter computer name from my previous scenario using key "computerName" and "<scenarioDataOutput>" file in delete page
 Then I will click filter by name button in delete page
 And I will verify my expected computer name is equal to actual computer name in the filter list in delete page
 And I will click on the computer name that i like to delete
 And I will click delete this computer button
 And I will search for my computer that is just deleted in my previous step
 And I will verify my computer is deleted and expected text is "<expected_text>"

Examples:
 |computer_name 		  					| scenarioDataOutput     | expected_text  								  |
 |onesite_bkup						     	| createComputerScenario | Nothing to display               |