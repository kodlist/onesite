package com.onesite.stepdefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;

import com.onesite.hooks.TestBaseSetUp;
import com.onesite.managers.FileReaderManager;
import com.onesite.managers.PageObjectManager;
import com.onesite.pages.CreateComputerPage;
import com.onesite.pages.DeleteComputerPage;
import com.onesite.pages.ReadComputerPage;
import com.onesite.utilities.JSONUtil;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DeleteComputerPageSteps {
	
	WebDriver driver = TestBaseSetUp.getWebDriverInstance();
	private static final Logger LOGGER = LogManager.getLogger(DeleteComputerPageSteps.class);
	PageObjectManager pageObjectManager = PageObjectManager.getpageObjectManagerInstance(driver);
	ReadComputerPage readComputerPage = pageObjectManager.getReadComputerPage();
	CreateComputerPage createComputerPage = pageObjectManager.getCreateComputerPage() ;
	DeleteComputerPage deleteComputerPage = pageObjectManager.getDeleteComputerPage();
	JSONUtil jsonUtil = JSONUtil.getJSONUtilInstance();
	
	
	@When("^I will enter computer name from my previous scenario using key \"([^\"]*)\" and \"([^\"]*)\" file in delete page$")
	public void enter_computer_name_in_search_box_searchForComputer_from_deletepage(String key, String scenarioDataOutputFile){
		LOGGER.info("-------- get computer name from json file that was saved from earlier scenario -------- ");
		String scenarioDataFolderPath = FileReaderManager.getInstance().getConfigReaderInstance().getScenarioDataFolderPath();					
		JSONObject jsonObj = new JSONObject();
		jsonObj = jsonUtil.readJSONfile(jsonObj, scenarioDataFolderPath, scenarioDataOutputFile);
		String nameOfComputer = jsonUtil.getValueUsingKey(jsonObj, key);
		deleteComputerPage.setNameOfComputerToSearch(nameOfComputer);
		searchForComputer_from_deletepage(deleteComputerPage.getNameOfComputerToSearch());
	}

	@When("^my first step I will enter computer name \"([^\"]*)\" in search box from delete page$")
	public void searchForComputer_from_deletepage(String computerNameToSearch){
		LOGGER.info("-------- enter computer name in search box ---------");
		deleteComputerPage.searchComputer(computerNameToSearch);				
	}
	
	@Then("^I will click filter by name button in delete page$")
	public void i_will_click_search_button_from_deletepage(){
		deleteComputerPage.clickSearchButton();			
	}
	
	@Then("^I will verify my expected computer name is equal to actual computer name in the filter list in delete page$")
	public void verify_my_expected_computer_name_from_filter_list_from_deletepage(){		
		deleteComputerPage.searchForComputerInTheExistingTable();
	}
	
	@Then("^I will click on the computer name that i like to delete$")
	public void click_on_the_computer_name_from_deletepage(){
		deleteComputerPage.clickTheComputerNameLinkToDelete(deleteComputerPage.getNameOfComputerToDelete());
	}
	
	@Then("^I will click delete this computer button$")
	public void click_delete_this_computer_button_from_deletepage(){
		deleteComputerPage.clickDeleteThisComputerBtn();
	}
	
	@Then("^I will search for my computer that is just deleted in my previous step$")
	public void enter_computer_name_in_search_box_in_deletepage(){		
		readComputerPage.searchComputer(deleteComputerPage.getNameOfComputerToDelete());
		readComputerPage.clickSearchButton();
	}

	@Then("^I will verify my computer is deleted and expected text is \"([^\"]*)\"$")
	public void verify_my_expected_computer_is_deleted_successfully(String expected_String){
		deleteComputerPage.assertThatComputerIsDeleted(expected_String);
		LOGGER.info("-------- it seems computer deleted successfully ---------");
	}
	
}
