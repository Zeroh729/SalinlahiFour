package com.ube.salinlahifour.tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeConverter {
	
	public static String getCurrentDateTime(){
		String currDateTime = "";
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Date date = new Date();
		currDateTime = dateFormat.format(date);
		
		return currDateTime;
	}

	public static Date convertStringToDateTime(String datetime) throws ParseException{
		return new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(datetime);
	}
	
	public static int getCurrentDay() {
		int day = 0;
		
		DateFormat dateFormat = new SimpleDateFormat("dd");
		Date date = new Date();
		day = Integer.parseInt(dateFormat.format(date));
		return day;
	}
	

}