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

public class DeleteComputerPage {
	
	private WebDriver driver;	
	
	 
	 /*-------- constructor ---------*/	
	 public  DeleteComputerPage(WebDriver driver) {
		 this.driver = driver;
		 PageFactory.initElements(driver, this);
	}

	 /*-------- page elements --------*/	 
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
	 public void clickDeleteThisComputerBtn(){
		 deleteComputer_submitButton.submit();
	 }
	 public String getNothingToDisplayText(){
		 return nothingToDisplay_fromComputerList.getText();
	 }
	 private long numberOfComputers = 0;
	 
	 public long getNumberOfComputers(){
		 return numberOfComputers;
	 }
	 private void setNumberOfComputers(long computers){
		 numberOfComputers = computers;
	 }
	 
	 private String nameOfComputerToSearch = null;
	 private String nameOfComputerToDelete = null;
	 public String getNameOfComputerToSearch(){
		 return nameOfComputerToSearch;
	 }
	 public void setNameOfComputerToSearch(String nameOfComputer){
		 nameOfComputerToSearch = nameOfComputer;
	 }
	 public String getNameOfComputerToDelete(){
		 return nameOfComputerToDelete;
	 }
	 public void setNameOfComputerToDelete(String nameOfComputer){
		 nameOfComputerToDelete = nameOfComputer;
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
				 setNameOfComputerToDelete(webElement.findElement(By.tagName("td")).getText());				
				 setComputerNameWebElement(webElement.findElement(By.tagName("td")));				 
				 flag = true;
			  }
		 }
		 Assert.assertTrue("the computer name "+ getNameOfComputerToDelete()+ " should be same as the one in the list",
		 			flag);		 
	 }
	 
	 public void clickTheComputerNameLinkToDelete(String nameOfComputer_link){		 
		 setComputerNameLinkWebElement(getComputerNameWebElement().findElement(By.linkText(nameOfComputer_link)));
		 getComputerNameLinkWebElement().click();
	 }
	 
	 private boolean flag_For_NothingToDisplay = false;
	 public void assertThatComputerIsDeleted(String expectedText){
		 String text_nothingToDisplay = getNothingToDisplayText();
		 if(text_nothingToDisplay.equalsIgnoreCase(expectedText)){
			 flag_For_NothingToDisplay = true ;
		 }
		 Assert.assertTrue("No computer found with selected name, hence "+ getNothingToDisplayText()+ " .",	flag_For_NothingToDisplay);
	 }
	 

}
