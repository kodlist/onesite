Feature: Automated End2End Tests

Description: The purpose of this feature is to test CRUD operations.
             We first create computer and save, then read the new computer, next update the computer and finally delete computer from system.
             This complete flow is End-2-End regression (CRUD) test from UI. 
             This scenario mainly focus on postive test cases with 
             primary objective being - CREATE, READ, UPDATE, DELETE

Background: user should navigate to main search page
 Given with application url I will navigate to the main search page


@createComputerScenario
Scenario Outline: create a computer and save it in database

 When my first step I will click add new computer button in create page
 Then I will enter computer name "<computer_name>" in create page 
 And I will enter new introduced date in create page 
 And I will enter new discontinued date using plus 10 years in create page
 And I will select company name at index 2 from the list of companies from create page
 And I will click create computer button in create page
 And I will save the new computer name with key "computerName" and new name as value in json file using scenario name

Examples:
 |computer_name     |
 |onesite				   	| 


 
@readComputerScenario
Scenario Outline: search for the computer you created in the above scenario, expected computer name is created in above scenario

 When I will enter computer name from my previous scenario using key "computerName" and "<scenarioDataOutput>" file in read page
 Then I will click filter by name button in read page
 And I will get the number of computers in the table from read page
 And I will verify my expected computer name is equal to actual computer name in the filter list in read page

# createComputerScenario is hard coded because of cucumber limition.As you can see createComputerScenario is the tag name of first scenario.
Examples:
 	| scenarioDataOutput     |
 	| createComputerScenario |
 
 
 
@updateComputerScenario
Scenario Outline: search for the computer you created in the above scenario and update that computer

 When I will enter computer name from my previous scenario using key "computerName" and "<scenarioDataOutput>" file in edit page
 Then I will click filter by name button in edit page
 And I will verify my expected computer name is equal to actual computer name in the filter list in edit page
 And I will click on the computer name that i like to edit
 And I will get the current discontinued date to edit
 And I will clear the existing discontinued date
 And I will enter discontinued date by adding 10 plus years in edit page
 And I will save the updated information
 And I will search for updated computer with name
 And I will click filter by name button in edit page
 And I will verify my expected computer name is equal to actual computer name in the filter list in edit page
 And I will verify the new updated discontinued date is not equal to old discontinued date 
 
 Examples:
 | scenarioDataOutput      |
 | createComputerScenario  | 
 
  
 
@deleteComputerScenario
Scenario Outline: search for the computer you created in the above scenario and delete computer

 When I will enter computer name from my previous scenario using key "computerName" and "<scenarioDataOutput>" file in delete page
 Then I will click filter by name button in delete page
 And I will verify my expected computer name is equal to actual computer name in the filter list in delete page
 And I will click on the computer name that i like to delete
 And I will click delete this computer button
 And I will search for my computer that is just deleted in my previous step
 And I will verify my computer is deleted and expected text is "<expected_text>"

Examples:
 | scenarioDataOutput     | expected_text  					 |
 | createComputerScenario | Nothing to display       |
 
 
 
 