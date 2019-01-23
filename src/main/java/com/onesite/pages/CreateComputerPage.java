package com.onesite.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.onesite.hooks.TestBaseSetUp;

public class CreateComputerPage {
	
	private WebDriver driver;
	private static final Logger LOGGER = LogManager.getLogger(CreateComputerPage.class);
	
	 /*-------- constructor ---------*/
	
	 public  CreateComputerPage(WebDriver driver) {
		 this.driver = driver;
		 PageFactory.initElements(driver, this);
	}
	
	 /*-------- page elements --------*/
	 
	 @FindBy(how = How.ID, using = "add")	 
	 private WebElement addNewComputerBtn;
	
	 @FindBy(how = How.ID, using = "name")	
	 private WebElement computerNameField;
	 
	 @FindBy(how = How.ID, using = "introduced")	
	 private WebElement computerIntroducedField;	 
	
	 @FindBy(how = How.ID, using = "discontinued")	
	 private WebElement computerDiscontinuedField;
	 
	 @FindBy(id="company")
	 private WebElement company_dropdown;	
	 
	 @FindBy(how=How.CSS ,using="input[type='submit']")
	 private WebElement createComputer_submitButton;
	 
	 /*--------- element methods ----------*/
	 
	 public void clickAddComputer(){
		 LOGGER.info("------ now in create computer page ------");
		 this.addNewComputerBtn.click();
	 }
	 
	 public void enterComputerName(String nameOfNewComputer){
		 this.computerNameField.sendKeys(nameOfNewComputer);
	 }
	 
	 public void enterIntroducedDate(String introDate){
		 this.computerIntroducedField.sendKeys(introDate);
	 }
	 
	 public void enterDiscontinuedDate(String disconDate){
		 this.computerDiscontinuedField.sendKeys(disconDate);
	 }
	 
	 public void clickCreateThisComputerBtn(){
		 createComputer_submitButton.submit();
	 }
	 private String nameOfCompanyThatWasChoosen;
	 
	 public String getNameOfTheCompanyThatWasChoosen(){
		 return nameOfCompanyThatWasChoosen;
	 }
	 public void setNameOfTheCompany(String nameOfCompanyAtIndex){
		 this.nameOfCompanyThatWasChoosen = nameOfCompanyAtIndex;
	 }
	 
	 /*-------- action methods and fields --------*/
	 
	 public void selectCompany(int index){  
	     Select statusDropdown = new Select(company_dropdown);
	     //statusDropdown.selectByVisibleText(companyToPick);
	     statusDropdown.selectByIndex(index);
	     String nameOfCompanyAtIndex = null ;
	     List<WebElement> listOfCompanies = statusDropdown.getOptions();	    
	     nameOfCompanyAtIndex = listOfCompanies.get(index).getText();
	     setNameOfTheCompany(nameOfCompanyAtIndex);
	     if(nameOfCompanyAtIndex == null){
	    	 throw new NoSuchElementException("company at index 2 is not selected....please check ");
	     }	     
	     LOGGER.info("------- company selected from drop down ------");
	 }
	 
	/* public void select_Company(String companyName) 
	 {		 
		 drpdwn_CompanyDropDownArrow.click();
		 
		 for(WebElement company : company_List)
		 {
			 if(company.getText().equals(companyName)) {
				 company.click(); 
			   }
		 }
	}*/
	 

}
