package com.onesite;

import java.io.File;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

import com.cucumber.listener.Reporter;
import com.onesite.managers.FileReaderManager;
import com.onesite.stepdefinitions.CreateComputerPageSteps;

//plugin = { "com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"}
//plugin = {"pretty", "html:target/cucumber", "json:target/cucumber.json"},

  @RunWith(Cucumber.class)
  @CucumberOptions( features = {"src/test/resources"},		  			
		            plugin = { "com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"},
		  		    monochrome = true,
  					glue={"com.onesite.stepdefinitions", "com.onesite.hooks"},
  					tags={"@createComputerScenario,@readComputerScenario, @updateComputerScenario, @deleteComputerScenario" } )
  public class TestRunner {
	  private static final Logger LOGGER = LogManager.getLogger(TestRunner.class);
	  
	  @AfterClass
	  public static void writeExtentReport() {
		  Reporter.loadXMLConfig(new File(FileReaderManager.getInstance().getConfigReaderInstance().getReportConfigPath()));
		  Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
		  Reporter.setSystemInfo("Zone Info", ZonedDateTime.now(ZoneId.systemDefault()).getZone().toString());
		  Reporter.setSystemInfo("Machine OS", System.getProperty("os.name"));		  
		  Reporter.setSystemInfo("Test URL", FileReaderManager.getInstance().getConfigReaderInstance().getApplicationURL());
		  Reporter.setSystemInfo("Browser Type", FileReaderManager.getInstance().getConfigReaderInstance().getBrowserType().name());
		  
		  LOGGER.info("----- Generating html report with test results, and you should see them in cucumber-reports under target directory.... ------ ");
		  
	  }

  }
