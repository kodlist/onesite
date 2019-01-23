package com.onesite.pages.core;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonActions {
	
	WebElement element=null;
	String old_win = null;
	String lastWinHandle = null;
	private static CommonActions commonActions = null;
	
	public static CommonActions getCommonActionsInstance(){
		if(commonActions == null){
			return commonActions = new CommonActions();
		}else{
			return commonActions;
		}
		
		
	}
	public  void handleAlert(WebDriver driver, String decision){
		if(decision.equals("accept"))
			driver.switchTo().alert().accept();
		else
			driver.switchTo().alert().dismiss();
	}
	
	public  void navigateTo(WebDriver driver, String url){
		driver.get(url);
	}

	/** Method to quite webdriver instance */
	public  void closeDriver(WebDriver driver ){
		driver.close();
	}
	
	/** Method to resize browser
	 * @param width : int : Width for browser resize
	 * @param height : int : Height for browser resize
	 */
	public  void resizeBrowser(WebDriver driver, int width, int height){
		driver.manage().window().setSize(new Dimension(width,height));
	}
	
	/** Method to maximize browser	 */
	public  void maximizeBrowser(WebDriver driver)	{
		driver.manage().window().maximize();
	}
	
	/**Method to switch to new window */
    public  void switchToNewWindow(WebDriver driver)
    {
    	old_win = driver.getWindowHandle();
    	for(String winHandle : driver.getWindowHandles())
    		lastWinHandle = winHandle;
    	driver.switchTo().window(lastWinHandle);
    }
    
    /** Method to switch to old window */
    public  void switchToOldWindow(WebDriver driver)
    {
    	driver.switchTo().window(old_win);
    }
    
   
    public  void untilExpectedElementIsFound(WebDriver driver, Function<WebDriver, WebElement> waitCondition, Long timeoutInSeconds){
         WebDriverWait webDriverWait = new WebDriverWait(driver, timeoutInSeconds);    	
    	 try{
    		 webDriverWait.until(waitCondition);
    	 }catch (Exception e){
    		 System.out.println(e.getMessage());
    	 }          
    }
    
    public  List<WebElement> untilListOfElementsFound(WebDriver driver, Function<WebDriver, List<WebElement>> waitCondition, Long timeoutInSeconds){
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeoutInSeconds);    	
   	 try{
   		 return webDriverWait.until(waitCondition);
   	 }catch (Exception e){
   		throw new NoSuchElementException("driver unable to find the webelement and return list of webelements");
   	 }          
   }
   
    
   
    
    
}
