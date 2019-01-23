package com.onesite.pages.itempage;

import org.junit.Assert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ItemPage {
	
	private WebDriver driver;
	
	public ItemPage(WebDriver driver) {
		 this.driver = driver;
		 PageFactory.initElements(driver, this);
	}
	
	 @FindBy(id="buyButton")
	 private WebElement buyButton;
	 
	 @FindBy(id="actualItemPrice")
	 private WebElement actualItemPrice;
	 
	 public void clickBuyButton(){
		 buyButton.click();
	 }
	 
	 public double getActualItemPrice(){
		 return Double.parseDouble( actualItemPrice.getText());
	 }
	 
	 /*-------- validate methods ---------*/
	 
	 public void validatePrice(int expectedItemPrice){
		 Assert.assertEquals(expectedItemPrice, getActualItemPrice(), 0.01);		 
	 }
	 

}
