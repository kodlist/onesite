package com.onesite.stepdefinitions;

import java.time.LocalDate;

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
import com.onesite.pages.UpdateComputerPage;
import com.onesite.utilities.DateUtil;
import com.onesite.utilities.JSONUtil;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class UpdateComputerPageSteps {
	
	WebDriver driver = TestBaseSetUp.getWebDriverInstance();
	private static final Logger LOGGER = LogManager.getLogger(UpdateComputerPageSteps.class);
	PageObjectManager pageObjectManager = PageObjectManager.getpageObjectManagerInstance(driver);
	ReadComputerPage readComputerPage = pageObjectManager.getReadComputerPage();
	CreateComputerPage createComputerPage = pageObjectManager.getCreateComputerPage() ;
	DeleteComputerPage deleteComputerPage = pageObjectManager.getDeleteComputerPage();
	UpdateComputerPage updateComputerPage = pageObjectManager.getUpdateComputerPage();
	JSONUtil jsonUtil = JSONUtil.getJSONUtilInstance();
	DateUtil dateUtil = DateUtil.getDateUtilInstance();
	
	
	@When("^I will enter computer name from my previous scenario using key \"([^\"]*)\" and \"([^\"]*)\" file in edit page$")
	public void enter_computer_name_in_search_box_searchForComputer_from_editpage(String key, String scenarioDataOutputFile){
		LOGGER.info("-------- get computer name from json file that was saved from earlier scenario -------- ");
		String scenarioDataFolderPath = FileReaderManager.getInstance().getConfigReaderInstance().getScenarioDataFolderPath();					
		JSONObject jsonObj = new JSONObject();
		jsonObj = jsonUtil.readJSONfile(jsonObj, scenarioDataFolderPath, scenarioDataOutputFile);
		String nameOfComputer = jsonUtil.getValueUsingKey(jsonObj, key);
		updateComputerPage.setNameOfComputerToSearch(nameOfComputer);
		searchForComputer_from_editpage(updateComputerPage.getNameOfComputerToSearch());
	}

	@When("^my first step I will enter computer name \"([^\"]*)\" in search box from edit page$")
	public void searchForComputer_from_editpage(String computerNameToSearch){
		LOGGER.info("-------- enter computer name in search box ---------");
		updateComputerPage.searchComputer(computerNameToSearch);				
	}
	
	@Then("^I will click filter by name button in edit page$")
	public void i_will_click_search_button_from_editpage(){
		updateComputerPage.clickSearchButton();	
	}
	
	@Then("^I will verify my expected computer name is equal to actual computer name in the filter list in edit page$")
	public void verify_my_expected_computer_name_from_filter_list_from_editpage(){		
		updateComputerPage.searchForComputerInTheExistingTable();
	}
	
	@Then("^I will click on the computer name that i like to edit$")
	public void click_on_the_computer_name_from_editpage(){
		 LOGGER.info("------- trying to click computer link that I need --------");
		 updateComputerPage.clickTheComputerNameLinkToUpdate(updateComputerPage.getNameOfComputerToUpdate());
	}	
	
	@Then("^I will get the current discontinued date to edit$")
	public void get_current_discontinued_date_field(){		
		String currentDiscontinueDate = updateComputerPage.getDiscontinuedDate();
		dateUtil.setTodaysDate(currentDiscontinueDate);
		 LOGGER.info("------- current discontinued date '"+ currentDiscontinueDate + "'--------");
	}
	
	@Then("^I will clear the existing discontinued date$")
	public void clear_the_discontinued_date_field(){
		updateComputerPage.setDiscontinuedDateFieldEmpty();
		LOGGER.info("------- cleared discontinued date field -------- ");
	}
	
	/*@Then("^I will enter discontinued date \"([^\"]*)\" in edit page$")
	public void enterDiscontinuedDate_from_editPage(String disConDate){
		updateComputerPage.enterDiscontinuedDate(disConDate);
	}*/
	
	@Then("^I will enter discontinued date by adding (\\d+) plus years in edit page$")
	public void enterDiscontinuedDate_from_editPage(int editNumberOfYear){
		String newDiscontinuedDate = dateUtil.getCustomDateByAddingGivenYears(editNumberOfYear);		
		updateComputerPage.enterDiscontinuedDate(newDiscontinuedDate);	
		LOGGER.info("-------- entering new discontinued date date by adding years = '" + editNumberOfYear + "' -------- ");
	}
	@Then("^I will save the updated information$")
	public void i_will_click_save_computer_button_from_editPage() {	   
		updateComputerPage.clickSaveThisComputerBtn();
		LOGGER.info("--------- click save button ---------");
	}
	
	@Then("^I will search for updated computer with name$")
	public void i_will_search_for_updated_computer() {
		updateComputerPage.searchComputer(updateComputerPage.getNameOfComputerToSearch());
		LOGGER.info("------- now searching for updated computer and verifying the updated discontinue date -------");
	}
	
	
	@Then("^I will verify the new updated discontinued date is not equal to old discontinued date$")
	public void verify_the_new_updated_date_is_same_as_expected_new_date_in_ui(){
		String oldDisContinuedDT = dateUtil.getTodaysDate();		
		updateComputerPage.ourOldDiscontinuedDateIsNotEqualToNewUpdateDate(oldDisContinuedDT);
		LOGGER.info("-------- successfull, date is updated as expected.... --------");
	}
	
	
}
