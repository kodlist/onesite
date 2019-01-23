package com.onesite.stepdefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.onesite.hooks.TestBaseSetUp;
import com.onesite.managers.FileReaderManager;
import com.onesite.pages.ReadComputerPage;
import com.onesite.pages.core.CommonActions;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;

public class BackgroundSteps {
	
	 WebDriver driver;
	 private static final Logger LOGGER = LogManager.getLogger(BackgroundSteps.class);
	 	
	 @Given("^with application url I will navigate to the main search page$")
	 public void with_application_url_I_will_navigate_to_the_main_search_page() throws Throwable {	     
		 
		 LOGGER.info("--------- in backgroundsteps class ----------");
		 driver = TestBaseSetUp.getWebDriverInstance();
		 CommonActions.getCommonActionsInstance().navigateTo(driver,
				 FileReaderManager.getInstance().getConfigReaderInstance().getApplicationURL());
		 LOGGER.info("--------- application url opened..... ----------");
		 
	 }
	 
}
