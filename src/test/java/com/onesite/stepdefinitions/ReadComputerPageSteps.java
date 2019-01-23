package com.onesite.stepdefinitions;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;

import com.onesite.hooks.TestBaseSetUp;
import com.onesite.managers.FileReaderManager;
import com.onesite.managers.PageObjectManager;
import com.onesite.managers.WebDriverManager;
import com.onesite.pages.CreateComputerPage;
import com.onesite.pages.DeleteComputerPage;
import com.onesite.pages.ReadComputerPage;
import com.onesite.utilities.JSONUtil;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ReadComputerPageSteps {
	
	WebDriver driver = TestBaseSetUp.getWebDriverInstance();
	private static final Logger LOGGER = LogManager.getLogger(ReadComputerPageSteps.class);
	PageObjectManager pageObjectManager = PageObjectManager.getpageObjectManagerInstance(driver);
	ReadComputerPage readComputerPage = pageObjectManager.getReadComputerPage();
	CreateComputerPage createComputerPage = pageObjectManager.getCreateComputerPage() ;
	DeleteComputerPage deleteComputerPage = pageObjectManager.getDeleteComputerPage();
	JSONUtil jsonUtil = JSONUtil.getJSONUtilInstance();;
	
	
	 
	@When("^I will get the number of computers in the table from read page$")
	public void I_will_get_the_number_of_computers_in_the_table_from_readPage() throws Throwable {
	    readComputerPage.numberOfComputersFound();
	    readComputerPage.getNumberOfComputers();
    }

	@When("^I will enter computer name from my previous scenario using key \"([^\"]*)\" and \"([^\"]*)\" file in read page$")
	public void enter_computer_name_in_search_box_from_readPage(String key, String scenarioDataOutputFile){
		LOGGER.info("-------- get computer name from json file that was saved from earlier scenario --------");
		String scenarioDataFolderPath = FileReaderManager.getInstance().getConfigReaderInstance().getScenarioDataFolderPath();					
		JSONObject jsonObj = new JSONObject();
		jsonObj = jsonUtil.readJSONfile(jsonObj, scenarioDataFolderPath, scenarioDataOutputFile);
		String nameOfComputer = jsonUtil.getValueUsingKey(jsonObj, key);
		readComputerPage.setNameOfComputerToSearch(nameOfComputer);
		LOGGER.info("-------- name of the computer to search : '"+readComputerPage.getNameOfComputerToSearch() +"' was set.....");
		searchForComputer_from_readPage(readComputerPage.getNameOfComputerToSearch());
		
	}
	
	@When("^my first step I will enter computer name \"([^\"]*)\" in search box in read page$")
	public void searchForComputer_from_readPage(String computerNameToSearch){
		LOGGER.info("-------- enter computer name in search box ---------");
		readComputerPage.searchComputer(computerNameToSearch);				
	}
	
	@Then("^I will click filter by name button in read page$")
	public void i_will_click_search_button_from_readPage(){
		readComputerPage.clickSearchButton();
		LOGGER.info("-------- searching started ---------");
	}
		
	@Then("^I will verify my expected computer name is equal to actual computer name in the filter list in read page$")
	public void verify_my_expected_computer_name_from_filter_list_from_readPage(){
		String nameOfComputer = readComputerPage.getNameOfComputerToSearch();
		readComputerPage.searchForComputerInTheExistingTable(readComputerPage.getNumberOfComputers());
	}
	
	
}
