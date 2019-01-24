package com.onesite.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class UpdateComputerPage {
	
	private WebDriver driver;	
	private static final Logger LOGGER = LogManager.getLogger(UpdateComputerPage.class);
	 
	 /*-------- constructor ---------*/	
	 public  UpdateComputerPage(WebDriver driver) {
		 this.driver = driver;
		 PageFactory.initElements(driver, this);
	}

	 /*-------- page elements --------*/	
	 
	 @FindBy(how = How.ID, using = "name")	
	 private WebElement computerNameField;
	 
	 @FindBy(how = How.ID, using = "introduced")	 
	 private WebElement computerIntroducedField;	 
	
	 @FindBy(how = How.ID, using = "discontinued")	
	 private WebElement computerDiscontinuedField;
	 
	 @FindBy(how = How.XPATH, using = "//input[@id='discontinued']")	 
	 private WebElement computerDiscontinuedFieldToClear;
	 
	 @FindBy(id="company")
	 private WebElement company_dropdown;	
	 
	 @FindBy(how=How.CSS ,using="input[type='submit']")
	 private WebElement saveComputer_submitButton;	
	 
	 @FindBy(how = How.ID, using = "searchbox")	 
	 private WebElement computerSearchBox;
	 
	 @FindBy(how = How.ID, using = "searchsubmit")	
	 private WebElement searchSubmit;
	 
	 @FindBy(how = How.XPATH,  using = "//*[@id='main']/table/tbody")	 
	 private WebElement tableBody;
	 
	 @FindBy(how=How.CSS ,using="input[class='btn danger']")
	 private WebElement deleteComputer_submitButton;
	 
	 
	 @FindBy(how = How.XPATH,  using = "//*[@id='main']/div[2]/em")	 
	 private WebElement nothingToDisplay_fromComputerList;
	 
    /*--------- element methods ----------*/
	 
	 
	 public void searchComputer(String computerNameToSearch){
		 this.computerSearchBox.sendKeys(computerNameToSearch);		 
	 }
	 
	 public void clickSearchButton(){
		 this.searchSubmit.submit();
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
	 
	 public void clickSaveThisComputerBtn(){
		 saveComputer_submitButton.submit();
	 }
	 
	 
	 
	 private String nameOfComputerToSearch = null;
	 private String nameOfComputerToUpdate = null;
	 
	 /*------- action methods and fields -------*/
	 
	 public String getNameOfComputerToUpdate(){
		 return nameOfComputerToUpdate;
	 }
	 public void setNameOfComputerToUpdate(String nameOfComputer){
		 nameOfComputerToUpdate = nameOfComputer;
	 }
	 
	 public String getNameOfComputerToSearch(){
		 return nameOfComputerToSearch;
	 }
	 public void setNameOfComputerToSearch(String nameOfComputer){
		 nameOfComputerToSearch = nameOfComputer;
	 }
	 
	 private WebElement element_locator_row_ComputerToDelete;
	 private WebElement element_locator_link_forComputerName;
	 
	 public void setComputerNameWebElement(WebElement element_Locator){		
		 element_locator_row_ComputerToDelete = element_Locator;
	 }
	 public WebElement getComputerNameWebElement(){
		 return element_locator_row_ComputerToDelete;
	 }
	 
	 public void setComputerNameLinkWebElement(WebElement element_Locator){
		 element_locator_link_forComputerName = element_Locator;
	 }
	 public  WebElement getComputerNameLinkWebElement(){
		 return element_locator_link_forComputerName;
	 }
	 public String getDiscontinuedDate(){
		return this.computerDiscontinuedFieldToClear.getAttribute("value");
	 }
	 public void setDiscontinuedDateFieldEmpty(){
		 computerDiscontinuedField.clear();
	 }
	 
	 public void searchForComputerInTheExistingTable(){
		 LOGGER.info("------- trying to search for computer that I need --------");
		 boolean flag = false;
		 List<WebElement> rowsInTableBody = tableBody.findElements(By.tagName("tr"));
		 for(WebElement webElement : rowsInTableBody){			 
			 if(webElement.findElement(By.tagName("td")).getText().equalsIgnoreCase(getNameOfComputerToSearch())){
				 setNameOfComputerToUpdate(webElement.findElement(By.tagName("td")).getText());				
				 setComputerNameWebElement(webElement.findElement(By.tagName("td")));				 
				 flag = true;
			  }
		 }
		 Assert.assertTrue("the computer name "+ getNameOfComputerToUpdate()+ " should be same as the one in the list",
		 			flag);		 
	 }
	 
	 public void clickTheComputerNameLinkToUpdate(String nameOfComputer_link){		
		 setComputerNameLinkWebElement(getComputerNameWebElement().findElement(By.linkText(nameOfComputer_link)));
		 getComputerNameLinkWebElement().click();
		 LOGGER.info("------- clicked on computer link that I need --------");
	 }	 
	 
	 public void searchForUpdatedDiscontinuedDateAndValidate(String dateToSearch){
		 LOGGER.info("------- trying to see whether the discontinued date is updated --------");
		 boolean flag = false;
		 List<WebElement> rowsInTableBody = tableBody.findElements(By.tagName("tr"));
		 for(WebElement webElement : rowsInTableBody){			 
			 if(webElement.findElement(By.tagName("td")).getText().equalsIgnoreCase(getNameOfComputerToSearch())){
				 setNameOfComputerToUpdate(webElement.findElement(By.tagName("td")).getText());				
				 setComputerNameWebElement(webElement.findElement(By.tagName("td")));
				 List<WebElement> listOrColumnsInTheRow = webElement.findElements(By.tagName("td"));				 
				 for(WebElement webElementTd : listOrColumnsInTheRow){ 					
					 if(!(webElementTd.getText().equalsIgnoreCase(dateToSearch))){						 
						 flag = true;
					 }
				 }				 
			  }
		 }
		 Assert.assertTrue("the computer should be updated with new date "+ dateToSearch+ " and it should be showing same in the UI.",
		 			flag);
		 LOGGER.info("------- the updated discontinued date is updated with no issues... --------");
	 }
	 
	 public void ourOldDiscontinuedDateIsNotEqualToNewUpdateDate(String dateToSearch){
		 searchForUpdatedDiscontinuedDateAndValidate(dateToSearch);
	 }
	 
	 
}
