package com.onesite.stepdefinitions;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Month;

import gherkin.formatter.model.Scenario;

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
import com.onesite.utilities.DateUtil;
import com.onesite.utilities.JSONUtil;
import com.onesite.utilities.RandomNumberUtil;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;



public class CreateComputerPageSteps {
	private static final Logger LOGGER = LogManager.getLogger(CreateComputerPageSteps.class);	
	WebDriver driver = TestBaseSetUp.getWebDriverInstance();	
	PageObjectManager pageObjectManager = PageObjectManager.getpageObjectManagerInstance(driver);
	ReadComputerPage readComputerPage = pageObjectManager.getReadComputerPage();
	CreateComputerPage createComputerPage = pageObjectManager.getCreateComputerPage() ;
	DeleteComputerPage deleteComputerPage = pageObjectManager.getDeleteComputerPage();
	JSONUtil jsonUtil = JSONUtil.getJSONUtilInstance();
	DateUtil dateUtil = DateUtil.getDateUtilInstance();
	RandomNumberUtil randomNumberGen = RandomNumberUtil.getRandomNumberUtil();
	private String newComputerName;
	
	@When("^my first step I will click add new computer button in create page$")
	public void i_will_click_add_new_computer_button_from_createPage() throws Throwable {		
		LOGGER.info("------------ click add new computer button -------------");
		createComputerPage.clickAddComputer();		
	}	
	public String getNewComputerName(){
		return newComputerName;
	}
	public void setNewComputerName(String computerName){
		this.newComputerName = computerName;
	}
	@Then("^I will enter computer name \"([^\"]*)\" in create page$")
	public void enterNewComputerName_from_createPage(String computerName){
		LOGGER.info("------- trying to create new computer name -------");
		int randomNum = randomNumberGen.getRandomNumber();
		computerName = computerName+randomNum;
		setNewComputerName(computerName);
		createComputerPage.enterComputerName(getNewComputerName());
		LOGGER.info("------- created new computer name '"+getNewComputerName() +" -------");
	}
	
	/*@Then("^I will enter introduced date \"([^\"]*)\" in create page$")
	public void enter_IntroDate_from_createPage(String introDate){
		createComputerPage.enterIntroducedDate(introDate);
	}*/
	
	@Then("^I will enter new introduced date in create page$")
	public void enter_IntroDate_from_createPage(){
		dateUtil.setTodaysDate(LocalDate.now().toString());
		createComputerPage.enterIntroducedDate(dateUtil.getTodaysDate());
		LOGGER.info("------- enter new introduced date ------- ");
	}
	
	
	/*@Then("^I will enter new discontinued date \"([^\"]*)\" in create page$")
	public void enter_DiscontinuedDate_from_createPage(String disConDate){
		createComputerPage.enterDiscontinuedDate(disConDate);
	}*/
	
	@Then("^I will enter new discontinued date using plus (\\d+) years in create page$")
	public void enter_new_DiscontinuedDate_from_createPage(int plusYearsToAdd){		
		String newDiscontinuedDate = dateUtil.getCustomDateByAddingGivenYears(plusYearsToAdd);		
		createComputerPage.enterDiscontinuedDate(newDiscontinuedDate);
		LOGGER.info("------- enter new discontinued date ------- ");
	}
	
	@Then("^I will select company name at index (\\d+) from the list of companies from create page$")
	public void selectCompanyName_from_createPage(int index){
		createComputerPage.selectCompany(index);
	}
	
	@Then("^I will click create computer button in create page$")
	public void i_will_click_create_computer_button_from_createPage()  {	   
		createComputerPage.clickCreateThisComputerBtn();
		LOGGER.info("------- clicked create submit button -------- ");
	}

	@Then("^I will save the new computer name with key \"([^\"]*)\" and value \"([^\"]*)\" in json file using scenario name$")
	public void save_the_new_computer_name_to_json_file_from_createPage(String key_computerName, String value_computerName){
		String scenarioDataFolderPath = FileReaderManager.getInstance().getConfigReaderInstance().getScenarioDataFolderPath();		
		JSONObject jsonObj = new JSONObject();		
		JSONObject jsonObj2 = new JSONObject();
		jsonObj2.put(key_computerName, value_computerName);
		jsonObj.put(TestBaseSetUp.getScenarioName(), jsonObj2);		
		jsonUtil.saveToJSONFile(jsonObj, scenarioDataFolderPath, TestBaseSetUp.getScenarioName());		
	}
	
	@Then("^I will save the new computer name with key \"([^\"]*)\" and new name as value in json file using scenario name$")
	public void save_the_new_computer_name_in_json_file_from_createpage(String key_computerName){
		String scenarioDataFolderPath = FileReaderManager.getInstance().getConfigReaderInstance().getScenarioDataFolderPath();		
		JSONObject jsonObj = new JSONObject();		
		JSONObject jsonObj2 = new JSONObject();
		jsonObj2.put(key_computerName, getNewComputerName());
		jsonObj.put(TestBaseSetUp.getScenarioName(), jsonObj2);		
		jsonUtil.saveToJSONFile(jsonObj, scenarioDataFolderPath, TestBaseSetUp.getScenarioName());
		LOGGER.info("------- saving new computer name : "+getNewComputerName() + " in json file for next scenario --------");
		LOGGER.info("------ json file should be in scenariodata directory ------");
	}
	
	/*@Then("^I will save the new computer name with key \"([^\"]*)\" and value \"([^\"]*)\" in json file using scenario name \"([^\"]*)\"$")
	public void save_the_new_computer_name_to_json_file_from_createPage(String key_computerName, String value_computerName, String scenarioName){
		String scenarioDataFolderPath = FileReaderManager.getInstance().getConfigReaderInstance().getScenarioDataFolderPath();
		
		JSONObject jsonObj = new JSONObject();		
		JSONObject jsonObj2 = new JSONObject();
		jsonObj2.put(key_computerName, value_computerName);
		jsonObj.put(scenarioName, jsonObj2);		
		jsonUtil.saveToJSONFile(jsonObj, scenarioDataFolderPath, scenarioName);
		
	}*/
	
}
