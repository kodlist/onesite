package com.onesite.stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.onesite.hooks.TestBaseSetUp;
import com.onesite.managers.PageObjectManager;
import com.onesite.pages.CreateComputerPage;
import com.onesite.pages.DeleteComputerPage;
import com.onesite.pages.ReadComputerPage;
import com.onesite.utilities.JSONUtil;

public class UpdateComputerPageSteps {
	
	WebDriver driver = TestBaseSetUp.getWebDriverInstance();;
	PageObjectManager pageObjectManager = PageObjectManager.getpageObjectManagerInstance(driver);
	ReadComputerPage readComputerPage = pageObjectManager.getReadComputerPage();
	CreateComputerPage createComputerPage = pageObjectManager.getCreateComputerPage() ;
	DeleteComputerPage deleteComputerPage = pageObjectManager.getDeleteComputerPage();
	JSONUtil jsonUtil = JSONUtil.getJSONUtilInstance();

}
