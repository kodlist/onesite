package com.onesite.pages;

import java.util.List;

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
	
	 
	 /*-------- constructor ---------*/	
	 public  UpdateComputerPage(WebDriver driver) {
		 this.driver = driver;
		 PageFactory.initElements(driver, this);
	}

	 /*-------- page elements --------*/	
	 
	 @FindBy(how = How.ID, using = "name")
	 @CacheLookup
	 private WebElement computerNameField;
	 
	 @FindBy(how = How.ID, using = "introduced")
	 @CacheLookup
	 private WebElement computerIntroducedField;	 
	
	 @FindBy(how = How.ID, using = "discontinued")
	 @CacheLookup
	 private WebElement computerDiscontinuedField;
	 
	 @FindBy(id="company")
	 private WebElement company_dropdown;	
	 
	 @FindBy(how=How.CSS ,using="input[type='submit']")
	 private WebElement saveComputer_submitButton;
	 
	
	 
	 @FindBy(how = How.ID, using = "searchbox")
	 @CacheLookup
	 private WebElement computerSearchBox;
	 
	 @FindBy(how = How.ID, using = "searchsubmit")
	 @CacheLookup
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
	 
	 /*------- action methods -------*/
	 
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
	 
	 public void searchForComputerInTheExistingTable(long numberOfComputersInTableList){		 
		 long noOfComputers = numberOfComputersInTableList;
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
	 }
	 
	 
}
