package com.onesite.utilities;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.onesite.managers.FileReaderManager;

public class JSONUtil {
	
	private JSONObject jsonObj;
	private JSONArray jsonArray;	
	private JSONParser jsonParser;
	private static JSONUtil jsonUtil;
	
	public static JSONUtil getJSONUtilInstance(){
		if(jsonUtil == null){
			return new JSONUtil();
		}else{
			return jsonUtil;
		}
	}
	public static void setJSONUtilInstanceToNull(){
		jsonUtil = null;
	}
	
	public void saveToJSONFile(JSONObject jsonObj, String scenarioDataFolderPath, String nameOfjsonFile){
		this.jsonObj = jsonObj;
		try (FileWriter file = new FileWriter(scenarioDataFolderPath+File.separator+nameOfjsonFile+".json" )) {
            file.write(this.jsonObj.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	public JSONObject readJSONfile(JSONObject jsonObj, String scenarioDataFolderPath, String nameOfjsonFile){
		jsonParser = new JSONParser();
		this.jsonObj = jsonObj;
		Object object = null;
		try { object = jsonParser.parse(new FileReader(scenarioDataFolderPath+File.separator+nameOfjsonFile+".json") );
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return jsonObj = (JSONObject) object;			
	}
	
	
	/*
	 * method: getValueUsingKey
	 * work: it is recursive method to find required value
	 * 
	 */
	private boolean flag = false;
	private String jsonValue = null ;	
	public String getValueUsingKey(JSONObject jsonObj, String key){				
		JSONObject jsonObject = (JSONObject) jsonObj;
		for(Iterator iterator = jsonObj.keySet().iterator(); iterator.hasNext();)
		{
		    String key1 = (String) iterator.next();		    
		    if( !key1.equalsIgnoreCase(key)){		    	
		    	if(flag == true){  		break;    	}		    	
		    	getValueUsingKey((JSONObject)jsonObject.get(key1), key);		    	
		    }else if(key1.equalsIgnoreCase(key)){		    	
		    	jsonValue = (String)jsonObject.get(key1) ;		    	
		    	flag = true;
		    	break;
		    }		    
		}		
		return jsonValue;
	}
	
	
}
