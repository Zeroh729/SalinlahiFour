package com.ube.salinlahifour.tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ube.salinlahifour.debugclasses.Log;

public class DateTimeConverter {
	
	public static String getCurrentDateTime(){
		String currDateTime = "";
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		currDateTime = dateFormat.format(date);
		
		return currDateTime;
	}

	public static Date convertStringToDateTime(String datetime) throws ParseException{
		return new SimpleDateFormat("yyyy-MM-dd").parse(datetime);
	}
	
	public static int getCurrentDay() {
		int day = 0;
		
		DateFormat dateFormat = new SimpleDateFormat("dd");
		Date date = new Date();
		day = Integer.parseInt(dateFormat.format(date));
		return day;
	}	
	
	public static String getDateLastMonth() {
		String day = "";
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		for(int i = 0; i < 30; i++)
			date =  new Date(date.getTime() - 24 * 3600 * 1000 ); 
		day = dateFormat.format(date).toString();
		
		return day;
	}
}