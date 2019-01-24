package com.onesite.hooks;

import org.openqa.selenium.WebDriver;

import com.onesite.managers.PageObjectManager;
import com.onesite.managers.WebDriverManager;
import com.onesite.utilities.DateUtil;
import com.onesite.utilities.JSONUtil;
import com.onesite.utilities.RandomNumberUtil;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
 * we use cucumber hooks to set pre-requisite for every test
 * 
 */
public class TestBaseSetUp {	
	private static final Logger LOGGER = LogManager.getLogger(TestBaseSetUp.class);
	private static WebDriver driver;
	private WebDriverManager webDriverManager;
	private Scenario scenario ;
	private static String scenarioName;	
	
	public WebDriver getDriver(WebDriverManager webDriverManager) {
		return driver = webDriverManager.getDriver();
	}
	
	public static WebDriver getWebDriverInstance() {
		return driver ;
	}
	public static void setWebDriverInstanceToNull() {
		 driver = null;
	}	
	public void setScenarioName(String scenarioName){
		this.scenarioName = scenarioName;
	}
	public static String getScenarioName(){
		return scenarioName;
	}
		
	
	@Before(order=1)
    public void beforeScenario(){        
        LOGGER.info("Start the browser and clear the cookies");
        webDriverManager = new WebDriverManager();
        driver = getDriver(webDriverManager);        
    } 
	
    @Before(order=0)
    public void beforeScenarioStart( Scenario scenario ){    	
    	this.scenario = scenario;
    	scenario.getSourceTagNames().stream().forEach(c-> { String scenarioName = c.substring(1) ; setScenarioName(scenarioName) ;});
    	LOGGER.info("-------------------------------------");
    	LOGGER.info("-------------------------------------");
    	LOGGER.info("--------- Start of Scenario ----------");
        LOGGER.info("-------- scenario name : "+ getScenarioName() +" ---------");
    } 
   
 
    @After(order=0)
    public void afterScenarioFinish(){    	
    	
    	PageObjectManager pageObjectManager = PageObjectManager.getpageObjectManagerInstance(driver);    	
    	pageObjectManager.setReadComputerPageInstanceNull();
    	pageObjectManager.setCreateComputerPageInstanceNull();
    	pageObjectManager.setDeleteComputerPageInstanceToNull();
    	PageObjectManager.setPageObjectManagerInstanceToNull();
    	scenarioName = null;
    	TestBaseSetUp.setWebDriverInstanceToNull();
    	JSONUtil.setJSONUtilInstanceToNull();
    	DateUtil.setDateUtilInstanceToNull();
    	RandomNumberUtil.setRandomNumberUtilInstanceToNull();
    	try {
			Thread.sleep(7000l);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
    	webDriverManager.closeDriver();    	
    	webDriverManager.quitDriver();
    	pageObjectManager = null; 
    	LOGGER.info("......................................");
        LOGGER.info("-----------End of Scenario-----------");
        LOGGER.info("......................................");
        LOGGER.info("......................................");
    } 
    
    @After(order=1)
    public void afterScenario(){
    	LOGGER.info("-----------All sessions - including page objects, utilities, page manager, webdriver closed ----------");
    	LOGGER.info("-------------------------------------");
    	LOGGER.info("-------------------------------------");
    } 
  

}
