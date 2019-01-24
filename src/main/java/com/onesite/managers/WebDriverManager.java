package com.onesite.managers;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;




import com.onesite.core.BrowserType;
import com.onesite.hooks.TestBaseSetUp;


public class WebDriverManager {
	
	 private WebDriver driver;
	 private static BrowserType browserType;	
	 private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
	 private static final String FIREFOX_DRIVER_PROPERTY = "webdriver.gecko.driver";
	 private static final String IE_DRIVER_PROPERTY = "webdriver.ie.driver";
	 private static final Logger LOGGER = LogManager.getLogger(WebDriverManager.class);
	 
	 public WebDriverManager() {
		 browserType = FileReaderManager.getInstance().getConfigReaderInstance().getBrowserType();	
	 }
	 
	 public WebDriver getDriver() {		 
		 if(driver == null){ 
			 driver = createDriver(browserType);
			 return driver;
		 }else{
			 return driver;
		 }	 
	 }
	 
	 private WebDriver createDriver(BrowserType browserType) {		 
		 switch(browserType) {
		 	case CHROME:
		 		driver = setChromeDriver(); 
		 		LOGGER.info("-------- setting chrome driver -------");
		 		break;
		 	case FIREFOX:
		 		driver = setFirefoxDriver();
		 		LOGGER.info("-------- setting firefox driver -------");
		 		break;
		 	case INTERNETEXPLORER:
		 		driver = setInternetExplorerDriver();
		 		LOGGER.info("-------- setting IE driver -------");
		 		break;
		 }	  
	    return driver;
	 }
	 
	 private  WebDriver setChromeDriver() {
			System.out.println("Launching google chrome with new profile..");			
			// path for chrome driver 
			System.setProperty(CHROME_DRIVER_PROPERTY, FileReaderManager.getInstance().getConfigReaderInstance().getDriverPath());
	        // Create object of HashMap Class
			Map<String, Object> prefs = new HashMap<String, Object>();
			
	        // Set the notification setting it will override the default setting, meaning disable chrome notifications
			prefs.put("profile.default_content_setting_values.notifications", 2);
			prefs.put("profile.default_content_settings.cookies", 2);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--disable-infobars");			
			
			driver = new ChromeDriver(options);		
		    driver.manage().window().maximize();
		    driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReaderInstance().getImplicitWait(), TimeUnit.SECONDS);
			//driver.navigate().to(appURL);
			return driver;
	}
	 

	 private  WebDriver setFirefoxDriver() {
			System.out.println("Launching Firefox browser..");
			// path for firefox driver 
			System.setProperty(FIREFOX_DRIVER_PROPERTY, FileReaderManager.getInstance().getConfigReaderInstance().getDriverPath());
			
			FirefoxOptions options = new FirefoxOptions();
			options.setCapability("marionette",true);
			options.setAcceptInsecureCerts(true);
			options.setCapability("disable-restore-session-state", true);
			
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("network.cookie.cookieBehavior",2);
			options.setProfile(profile);			
			
			driver = new FirefoxDriver(options);
			driver.manage().window().maximize();
			//driver.navigate().to(appURL);
			return driver;
	}
	
	 /*
	  * I haven't configured these tests for IE browser.
	  * So the below method is no use.
	  */
	 private  WebDriver setInternetExplorerDriver() {
		System.out.println("Launching InternetExplorer browser..");
		driver = new InternetExplorerDriver();
		//driver.manage().window().maximize();
		//driver.navigate().to(appURL);
		return driver;
	}
	
	 /*
	  * I haven't configured these tests for IE edge browser.
	  * So the below method is no use.
	  */
	 private  WebDriver setInternetExplorerEdgeDriver() {
		System.out.println("Launching InternetExplorer browser..");
		driver = new EdgeDriver();
		//driver.manage().window().maximize();
		//driver.navigate().to(appURL);
		return driver;
	}
	 
	

	 /*
	  * This method is useful for tests to run using selenium grid with remote machines configured.
	  * I haven't implemented, so the below method is no use.
	  */
	 private WebDriver createRemoteDriver() {
	 	throw new RuntimeException("RemoteWebDriver is not yet implemented");
	 }
	 
	
	/*
	 * close() is a webdriver command which closes the browser window which is currently in focus.
	 * During the automation process, if there are more than one browser window opened, then the close() command will close
	 * only the current browser window which is having focus at that time. The remaining browser windows will not be closed.	
	 * 
	 */		
	 public void closeDriver() {
		 LOGGER.info("-------- closing webdriver -------");
		driver.close();		
	 }
	
	 /*
	  * quit() is a webdriver command which calls the driver.dispose method, 
	  * which in turn closes all the browser windows and terminates the WebDriver session.
	  * If we do not use quit() at the end of program, the WebDriver session will not be 
	  * closed properly and the files will not be cleared off memory. This may result in memory leak errors.  
	  * 
	  */
	 public void quitDriver(){
		 LOGGER.info("-------- quiting webdriver -------");
		driver.quit();
	 }
    
	
}
