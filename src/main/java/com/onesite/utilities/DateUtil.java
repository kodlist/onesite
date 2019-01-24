package com.onesite.utilities;


import java.time.DayOfWeek;
import java.time.LocalDate;

import java.time.Month;
import java.time.Year;

import java.time.format.DateTimeFormatter;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class DateUtil {
	private static final Logger LOGGER = LogManager.getLogger(DateUtil.class);
	private static DateUtil dateUtil;
	private Month month;
	private Year year;
	private DayOfWeek day;
	
	public static DateUtil getDateUtilInstance(){
		if(dateUtil == null ){
			return dateUtil =  new DateUtil();}
		else{ return dateUtil;				
			}
	}
	
	public Month getMonthFromParsingDate(String dateToParse){
		LocalDate date = LocalDate.parse(dateToParse,	DateTimeFormatter.ISO_DATE);
		return date.getMonth();		
	}
	public int getYearFromParsingDate(String dateToParse){
		LocalDate date = LocalDate.parse(dateToParse,	DateTimeFormatter.ISO_DATE);		
		return date.getYear();
	}
	public int getDayFromParsingDate(String dateToParse){
		LocalDate date = LocalDate.parse(dateToParse,	DateTimeFormatter.ISO_DATE);		
		return date.getDayOfMonth();
	}
	
	public static void setDateUtilInstanceToNull(){
		dateUtil = null;
	}
	
	private String todaysDate ;
	public void setTodaysDate(String todaysDT){		
		this.todaysDate = todaysDT;
	}
	public String getTodaysDate(){
		return todaysDate;
	}
	
	public String getCustomDateByAddingGivenYears(int plusYearsToAdd){
		LOGGER.info("-------- creating new custom date by adding years '"+ plusYearsToAdd +"' -------- ");
		String date = dateUtil.getTodaysDate() ;
		int year = dateUtil.getYearFromParsingDate(date);
		Month month = dateUtil.getMonthFromParsingDate(date);
		int day = dateUtil.getDayFromParsingDate(date);		
		plusYearsToAdd = plusYearsToAdd + year ;
		String newDiscontinuedDate = "";
		String newMonth = "";
		if(month.getValue()<10){			
			newMonth = 0 + String.valueOf(1);
			newDiscontinuedDate = String.valueOf(plusYearsToAdd)+"-"+newMonth+"-"+String.valueOf(day);
		}else{
			newDiscontinuedDate = String.valueOf(plusYearsToAdd)+"-"+month.getValue()+"-"+String.valueOf(day);
		}
		LOGGER.info("-------- created new custom date '"+ newDiscontinuedDate  +"' by adding years '"+ plusYearsToAdd +"' -------- ");
		return newDiscontinuedDate;
	}		
		

}
