package com.onesite.stepdefinitions;

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


public class CreateComputerPageSteps {
		
	WebDriver driver = TestBaseSetUp.getWebDriverInstance();;
	PageObjectManager pageObjectManager = PageObjectManager.getpageObjectManagerInstance(driver);
	ReadComputerPage readComputerPage = pageObjectManager.getReadComputerPage();
	CreateComputerPage createComputerPage = pageObjectManager.getCreateComputerPage() ;
	DeleteComputerPage deleteComputerPage = pageObjectManager.getDeleteComputerPage();
	JSONUtil jsonUtil = JSONUtil.getJSONUtilInstance();
	
	 
	@When("^my first step I will click add new computer button in create page$")
	public void i_will_click_add_new_computer_button_from_createPage() throws Throwable {		 
		createComputerPage.clickAddComputer();
	}
	
	
	@Then("^I will enter computer name \"([^\"]*)\" in create page$")
	public void enterNewComputerName_from_createPage(String computerName){
		createComputerPage.enterComputerName(computerName);
	}
	
	@Then("^I will enter introduced date \"([^\"]*)\" in create page$")
	public void enterIntroDate_from_createPage(String introDate){
		createComputerPage.enterIntroducedDate(introDate);
	}

	
	@Then("^I will enter discontinued date \"([^\"]*)\" in create page$")
	public void enterDiscontinuedDate_from_createPage(String disConDate){
		createComputerPage.enterDiscontinuedDate(disConDate);
	}
	
	@Then("^I will select company name at index (\\d+) from the list of companies from create page$")
	public void selectCompanyName_from_createPage(int index){
		createComputerPage.selectCompany(index);
	}
	
	@Then("^I will click create computer button in create page$")
	public void i_will_click_create_computer_button_from_createPage() throws Throwable {	   
		createComputerPage.clickCreateThisComputerBtn();
	}

	
	@Then("^I will save the new computer name with key \"([^\"]*)\" and value \"([^\"]*)\" in json file using scenario name \"([^\"]*)\"$")
	public void save_the_new_computer_name_to_json_file_from_createPage(String key_computerName, String value_computerName, String scenarioName){
		String scenarioDataFolderPath = FileReaderManager.getInstance().getConfigReaderInstance().getScenarioDataFolderPath();
					
		JSONObject jsonObj = new JSONObject();		
		JSONObject jsonObj2 = new JSONObject();
		jsonObj2.put(key_computerName, value_computerName);
		jsonObj.put(scenarioName, jsonObj2);		
		jsonUtil.saveToJSONFile(jsonObj, scenarioDataFolderPath, scenarioName);
		
	}
	
}
