package com.onesite;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


  @RunWith(Cucumber.class)
  @CucumberOptions( features = {"src/test/java/com/onesite/functional"},
  					glue={"com.onesite.stepdefinitions", "com.onesite.hooks"},
  					tags={"@createComputerScenario,@readComputerScenario, @updateComputerScenario, @deleteComputerScenario" } )
  public class TestRunner {

  }
