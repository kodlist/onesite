package com.onesite.managers;

public class FileReaderManager {
	
	private static FileReaderManager fileReaderManager;
	private static ConfigFileReader configFileReader;

	private FileReaderManager() {
	}

	public static FileReaderManager getInstance( ) {
		 if(fileReaderManager == null){
			 return new FileReaderManager();
		 }else{
	         return fileReaderManager;
	     }
	}

	public ConfigFileReader getConfigReaderInstance() {
		 return (configFileReader == null) ? new ConfigFileReader() : configFileReader;
	}

}
