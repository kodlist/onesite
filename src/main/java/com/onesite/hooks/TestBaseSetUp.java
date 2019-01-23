package com.onesite.hooks;

import org.openqa.selenium.WebDriver;

import com.onesite.managers.PageObjectManager;
import com.onesite.managers.WebDriverManager;
import com.onesite.pages.CreateComputerPage;
import com.onesite.pages.ReadComputerPage;
import com.onesite.utilities.JSONUtil;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class TestBaseSetUp {
	
	
	
	/*
	 ** NOTE: @before
	 **	Starting a webdriver
	 **	Setting up DB connections
	 **	Setting up test data
	 **	Setting up browser cookies
	 **	Navigating to certain page
	 **	or anything before the test
	 *
	 ** we use cucumber hooks to set pre-requisite for every test.
	 **
	 **
	 */
	private static WebDriver driver;
	private WebDriverManager webDriverManager;
		
	public WebDriver getDriver(WebDriverManager webDriverManager) {
		return driver = webDriverManager.getDriver();
	}
	
	public static WebDriver getWebDriverInstance() {
		return driver ;
	}
	public static void setWebDriverInstanceToNull() {
		 driver = null;
	}
	
	
	
	@Before(order=1)
    public void beforeScenario(){
        System.out.println("Start the browser and clear the cookies");
        webDriverManager = new WebDriverManager();
        driver = getDriver(webDriverManager);        
    } 
    @Before(order=0)
    public void beforeScenarioStart(){
        System.out.println("-----------------Start of Scenario-----------------");
    } 
 
 
    @After(order=0)
    public void afterScenarioFinish(){    	
    	
    	PageObjectManager pageObjectManager = PageObjectManager.getpageObjectManagerInstance(driver);    	
    	pageObjectManager.setReadComputerPageInstanceNull();
    	pageObjectManager.setCreateComputerPageInstanceNull();
    	pageObjectManager.setDeleteComputerPageInstanceToNull();
    	PageObjectManager.setPageObjectManagerInstanceToNull();
    	
    	TestBaseSetUp.setWebDriverInstanceToNull();
    	JSONUtil.setJSONUtilInstanceToNull();  
    	try {
			Thread.sleep(9000l);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
    	webDriverManager.closeDriver();    	
    	webDriverManager.quitDriver();
    	pageObjectManager = null;
        System.out.println("-----------------End of Scenario-----------------");
        
        
    } 
    @After(order=1)
    public void afterScenario(){
        System.out.println("Log out the user and close the browser");
    } 
  

}
