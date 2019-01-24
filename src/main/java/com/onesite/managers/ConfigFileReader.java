package com.onesite.managers;

import java.util.Properties;
import java.util.stream.Stream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;

import com.onesite.core.BrowserType;


public class ConfigFileReader {
	
	private Properties properties;
	private static String driverPath;
	private static String scenarioDataFolderPath;
	private static String browserName;
	private static String applicationURL;
	private static Long implicitWait;
	private final static String propertyFile = "configuration.properties";
	private final static String configFileLocation = System.getProperty("file.separator")+"src"+System.getProperty("file.separator")+"test"+System.getProperty("file.separator")+"resources"; 
	
	public ConfigFileReader(){
		BufferedReader configFileBufferReader;
		try {
			configFileBufferReader = new BufferedReader(new FileReader(getConfigurationPropertyFilePath()));
			properties = new Properties();
			try {
				properties.load(configFileBufferReader);
				configFileBufferReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("configuration.properties file not found: " + propertyFile);
		}			
	}
	
	public String getConfigurationPropertyFilePath(){
		Path dir = Paths.get(System.getProperty("user.dir")+configFileLocation);
		
		 try (Stream<Path> paths = Files.find(dir, Integer.MAX_VALUE, (path,attrs) -> attrs.isRegularFile() && path.toString().endsWith(propertyFile)))
	     {  return paths.iterator().next().toAbsolutePath().toString();
	     }catch(IOException IO) {
	    	 throw new RuntimeException("configuration.properties file not found: " + propertyFile);	         
	     }		 
		
	}
	
	
	public String getDriverPath()
	{
		driverPath = properties.getProperty("driverPath");
		if(driverPath!= null){ 
			return System.getProperty("user.dir")+driverPath; 
		}else{
			throw new RuntimeException("driver path not specified in the configuration properties file."); 
		}
	}
	public String getScenarioDataFolderPath(){
		scenarioDataFolderPath = properties.getProperty("scenarioDataFolderPath");
		if(scenarioDataFolderPath!= null){ 
			return System.getProperty("user.dir")+scenarioDataFolderPath; 
		}else{
			throw new RuntimeException("scenarioDataFolderPath path not specified in the configuration properties file."); 
		}
		
	}
	
	public String getApplicationURL()
	{
		applicationURL = properties.getProperty("qa_url");
		if(applicationURL != null){
			return applicationURL;
		}else{
			throw new RuntimeException("application url not specified in the configuration properties file.");
		}
	}
	
	
	
	public long getImplicitWait()
	{
		implicitWait = Long.parseLong(properties.getProperty("implicitlyWait"));
		if(implicitWait != null){
			return implicitWait;
		}else{
			throw new RuntimeException("implicitWait duration is not specified in the configuration properties file.");
		}
		
	}
	
	public BrowserType getBrowserType(){
		browserName = properties.getProperty("browser");
		
		if(browserName == null || browserName.equalsIgnoreCase("chrome")){
			return BrowserType.CHROME;
		}else if(browserName == null || browserName.equalsIgnoreCase("firefox")){
			return BrowserType.FIREFOX;
		}else if(browserName == null || browserName.equalsIgnoreCase("internetexplorer")){
			return BrowserType.INTERNETEXPLORER;
		}else{
			throw new RuntimeException("browser name or type is not specified in the configuration properties file.");
		}
		
	}
	
	public String getReportConfigPath(){
		 String reportConfigPath = System.getProperty("user.dir")+properties.getProperty("reportConfigPath");
		 if(reportConfigPath!= null) return reportConfigPath;
		 else throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath"); 
		}
	
	

}
