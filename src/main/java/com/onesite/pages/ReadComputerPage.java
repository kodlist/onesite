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


public class ReadComputerPage {	
	
	 private WebDriver driver;	
	 private static final Logger LOGGER = LogManager.getLogger(ReadComputerPage.class);	
	 
	 /*-------- constructor ---------*/	
	 public  ReadComputerPage(WebDriver driver) {
		 this.driver = driver;
		 PageFactory.initElements(driver, this);
	}

	 /*-------- page elements --------*/	 
	 @FindBy(how = How.ID, using = "searchbox")	 
	 private WebElement computerSearchBox;
	 
	 @FindBy(how = How.ID, using = "searchsubmit")	 
	 private WebElement searchSubmit;
	 
	 @FindBy(how = How.CLASS_NAME, using = "computers zebra-striped")	 
	 private WebElement tableWithListOfComputers;	
	
	 @FindBy(how = How.XPATH,  using = "//*[@id='pagination']/ul/li[3]/a")	 
	 private WebElement nextBtnOrLink;
	
	 @FindBy(how = How.XPATH,  using = "//*[@id='pagination']/ul/li[1]/a")	 
	 private WebElement previousBtnOrLink;
	 
	 @FindBy(how = How.ID, using = "add")	 
	 private WebElement addNewComputerBtn;
	 
	 @FindBy(how = How.LINK_TEXT, using = "Computer name")	 
	 private WebElement header_computerName;	 
	
	 @FindBy(how = How.XPATH,  using = "//*[@id='main']/table/thead")	 
	 private WebElement tableHeader;	 
	
	 @FindBy(how = How.XPATH,  using = "//*[@id='main']/table/thead/tr")	 
	 private WebElement rowElementInHeader;	 
	
	 @FindBy(how = How.XPATH,  using = "//*[@id='main']/table/tbody")	 
	 private WebElement tableBody;
	 
	 //
	 @FindBy(how = How.XPATH,  using = "//*[@id='pagination']/ul/li[2]/a")	 
	 private WebElement numberOfcomputerDisplayLink;
	 
	 /*--------- element methods ----------*/
	 
	 public void searchComputer(String computerNameToSearch){
		 LOGGER.info("------- in main page or read page and driver to search for a computer ------- ");
		 this.computerSearchBox.sendKeys(computerNameToSearch);		 
	 }
	 
	 public void clickSearchButton(){
		 this.searchSubmit.submit();
	 }
	 
	 public void clickNextButton(){
		 this.nextBtnOrLink.click();
	 }
	 
	 public void clickPreviousButton(){
		 this.previousBtnOrLink.click();
	 }
	 
	 public void clickAddNewComputerButton(){
		 this.addNewComputerBtn.click();
	 }	 
	 
	 private long numberOfComputers = 0;
	 private String nameOfComputerToSearch = null;
	 
	 public long getNumberOfComputers(){
		 return numberOfComputers;
	 }
	 private void setNumberOfComputers(long computers){
		 numberOfComputers = computers;
	 }
	 public void numberOfComputersFound(){
		 String link = numberOfcomputerDisplayLink.getText();
		 String s[] = link.split("of");		
		 //numberOfComputers = Long.parseLong(s[1].trim());
		 setNumberOfComputers(Long.parseLong(s[1].trim()));
	 }
	 
	 public String getNameOfComputerToSearch(){
		 return nameOfComputerToSearch;
	 }
	 public void setNameOfComputerToSearch(String nameOfComputer){
		 nameOfComputerToSearch = nameOfComputer;
	 }
	
	 public void searchForComputerInTheExistingTable(long numberOfComputersInTableList){		 
		 long noOfComputers = numberOfComputersInTableList;
		 boolean flag = false;
		 List<WebElement> rowsInTableBody = tableBody.findElements(By.tagName("tr"));
		 for(WebElement webElement : rowsInTableBody){			 
			 if(webElement.findElement(By.tagName("td")).getText().equalsIgnoreCase(getNameOfComputerToSearch())){
				 flag = true;
			  }
		 }
		 Assert.assertTrue("the computer name "+ getNameOfComputerToSearch()+ " should be same as the one in the list",
		 			flag);
		 LOGGER.info("-------- computer found --------- ");
	 }
	 public void getNumberOfRowsWithComputers(){
		 List<WebElement> rowsInTableBody = tableBody.findElements(By.tagName("tr"));		 
	 }
	 
	
	 
	 
	 /* method: getNumberOfRowsWithComputers:
	  * 
	  * this method provides number of rows exist in the page, each row filled with one computer name.
	  * and, we have customized the finding element condition for this particular page object.
	  * 
	  * note: tableBody.findElements(By.tagName("tr")); works with no issues. just that I want to use waitcondition.
	  * you can remove waitcondition or comment the lines. and uncomment  List<WebElement> rowsInTableBody = tableBody.findElements(By.tagName("tr"));
	  * 
	  */
	/* public void getNumberOfRowsWithComputers(){
		 System.out.println("=========line 86 ========= in ReadComputerPage =========");
		 
		 //List<WebElement> rowsInTableBody = tableBody.findElements(By.tagName("tr"));
		 //System.out.println(rowsInTableBody.size());
		 Function<WebDriver, List<WebElement>> waitCondition = new Function<WebDriver, List<WebElement>>() {		
			 											@Override
			 											public List<WebElement> apply(WebDriver driver) {	
			 												return driver.findElements(By.tagName("tr"));
			 											}
		 												};		
		 List<WebElement> rowsInTableBody = CommonActions.getCommonActionsInstance().untilListOfElementsFound(driver, waitCondition, configFileReader.getImplicitWait());
		 System.out.println(rowsInTableBody.size());
	 }*/
	 
	 
}
