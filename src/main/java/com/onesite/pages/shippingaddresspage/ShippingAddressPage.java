package com.onesite.pages.shippingaddresspage;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import com.onesite.testdata.pojo.CustomerInfo;

public class ShippingAddressPage  {
	
	 private WebDriver driver;
		
	 
	 /*-------- constructor ---------*/
	
	 public  ShippingAddressPage(WebDriver driver) {
		 this.driver = driver;
		 PageFactory.initElements(driver, this);
	}

	 /*-------- page elements --------*/
	 
	 @FindBy(how = How.ID, using = "firstname")
	 @CacheLookup
	 private WebElement firstName;
	 
	 //OR
	 
	 /*@FindBy(className=".input.firstname")
	 private WebElement firstname;*/
	 
	 @FindBy(id="lastName")
	 @CacheLookup
	 private WebElement lastName;
	 
	 @FindBy(id="address")
	 private WebElement address1;
	 
	 @FindBy(id="phone")
	 private WebElement phone;
	 
	 @FindBy(id="email")
	 private WebElement email;
	 
	 @FindBy(id="continueButton")
	 private WebElement continueButton;
	 	  
	 @FindBy(id="CountryDropDown")
	 private List<WebElement> countryDropDownOptions  ;
	 
	 @FindBy(how = How.CSS, using = "#billing_country_field .select2-arrow") 
	 private WebElement drpdwn_CountryDropDownArrow;
	 
	 @FindAll(@FindBy(how = How.CSS, using = "#select2-drop ul li"))
	 private List<WebElement> country_List; 
	 
	 @FindBy(id="CountryToSelect")
	 WebElement country_dropdown;	 
	
	 /*------- the below is for expected condition -------*/	 
	 String logo_locator = "gh-logo";
	 
	 /*---------- methods ----------*/
	 
	 public void enter_FirstName(String firstName){
		 this.firstName.sendKeys(firstName);		 
	 }
	 public void enter_LastName(String lastName){
		 this.lastName.sendKeys(lastName);
	 }
	 public void enter_Address1(String address){
		 this.address1.sendKeys(address);
	 }
	 public void enter_Phone(String phone){
		 this.phone.sendKeys(phone);
	 }
	 public void enter_Email(String email){
		 this.email.sendKeys(email);
	 }
	 public void enter_Country(String country){
		 selectCountry(country);
	 }
	 
	 
	 
	 
	 
	 /*public void selectCountry(int countryToPickUsingNumber)
	 {		 
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
										.withTimeout(Duration.ofSeconds(30))
										.pollingEvery(Duration.ofSeconds(5))
										.ignoring(NoSuchElementException.class);
		wait.until(new Function<WebDriver, WebElement>() {
				@Override
				public WebElement apply(WebDriver driver) {
					
					return null;
				}
		});
		 
		 
	 }*/
	 
	 public void selectCountry(String countryToPick){
	     Select statusDropdown = new Select(country_dropdown);
	     statusDropdown.selectByVisibleText(countryToPick);
	 }
	 
	 public void select_Country(String countryName) 
	 {		 
		 drpdwn_CountryDropDownArrow.click();
		 
		 for(WebElement country : country_List)
		 {
			 if(country.getText().equals(countryName)) {
				 country.click(); 
			   }
		 }
	}
	 
	 public void switchToShippingFrame()
	 {
		 this.WaitForLogo(By.id(logo_locator));
		 //then switch to frame
	 }
	 
	 private void WaitForLogo(final By logo_locator)
	 {
		 Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					.withTimeout(Duration.ofSeconds(30))
					.pollingEvery(Duration.ofSeconds(5))
					.ignoring(NoSuchElementException.class);
		 
		 wait.until(new Function<WebDriver, WebElement>() {
			 		@Override
			 		public WebElement apply(WebDriver driver) {
			 			return driver.findElement(logo_locator);
			 		}
		 });
	 }
	 
	 
	 public void waitForShippingFrame(){
		 
	 }
	
	 public void fillShippingInfo(CustomerInfo custInfo)
	 {
		 enter_FirstName(custInfo.firstName);
		 enter_LastName(custInfo.lastName);
		 enter_Address1(custInfo.address1);
		 enter_Phone(custInfo.phone);
		 enter_Email(custInfo.email);
		 enter_Country(custInfo.country);
		 	 
	 }
	 
	 public void clickContinueButton(){
		 continueButton.submit();
	 }

}
