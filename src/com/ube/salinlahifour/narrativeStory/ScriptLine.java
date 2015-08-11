package com.ube.salinlahifour.narrativeStory;

import java.util.ArrayList;

import android.content.Context;
import com.ube.salinlahifour.debugclasses.Log;

import com.ube.salinlahifour.SalinlahiFour;

public class ScriptLine{
	private String lessonname;
	private String line;
	private Context context;
	private String soundFile = "";
	
	public ScriptLine(Context context, String lessonname, String line){
    	Log.d("TEST0", "Script Line: New Script Line");
		this.line = line;
		this.context = context;

		int index;
		String tempLine = line;
		int indexStart = 0;
		
		do{

			index = tempLine.indexOf(')', indexStart);
			

	    	Log.d("TEST0", "Script Line: ')' - " + index);
			
			if(index > 0){
		    	Log.d("TEST0", "Script Line: index: " + index + " tempLine: " + tempLine);
				int index2 = tempLine.indexOf('(', indexStart);
				int index3 = tempLine.indexOf('/', indexStart);

		    	Log.d("TEST0", "Script Line: '(' - " + index2);
		    	Log.d("TEST0", "Script Line: '/' - " + index3);
				
				
				if(index2 < index3 && index3 < index && index2 != -1 && index3 != -1){

			    	Log.d("TEST0", "Script Line: Has Tagalog/English tag in " + index2 + " " + index3 + " " + index);

					String tagalog = "<i>"+line.substring(index2+1, index3) + " </i>";
					String english = "<font color=#8C8C8C>("+line.substring(index3+1, index)+")</font>";

			    	Log.d("TEST0", "Script Line: Tagalog: " + tagalog);
			    	Log.d("TEST0", "Script Line: English: " + english);
			    	
					String newLine = "";

					newLine = tempLine.substring(0, index2);
			    	Log.d("TEST0", "Script Line: newLine: " + newLine);
					newLine += tagalog + english;
			    	Log.d("TEST0", "Script Line: newLine: " + newLine);
					

					
					if(tempLine.length() >= index+1){

						newLine += tempLine.substring(index+1);
						
						tempLine = newLine;
				    	Log.d("TEST0", "Script Line: Temp line: " + tempLine);
					
						indexStart = tempLine.indexOf("</font>", indexStart)+5;
					}else{
						index = -1;
					}
				}else{
//					if(index !=-1){
//						indexStart = index;
//					}else{
						indexStart++;
//					}
				}
			}
		}while(index != -1);


    	Log.d("TEST0", "Script Line: Done initialization: " + tempLine);
		
		this.line = tempLine;
	}
	
	public void setSoundFile(String filename){
		soundFile = filename;
	}

	public String getSoundFile() {
		return soundFile;
	}
	
	public String getLine(){
		String tempLine = line;
		if(tempLine.contains("(username)")){
	    	Log.d("TEST0", "Script Line: contains (username)");
			tempLine = tempLine.replace("(username)", SalinlahiFour.getLoggedInUser().getName());
	    	Log.d("TEST0", "Script Line: New line is now: " + tempLine);
		}
		if(tempLine.contains("(maincharacter)")){
	    	Log.d("TEST0", "Script Line: contains (maincharacter)");
	    	tempLine = tempLine.replace("(maincharacter)", SalinlahiFour.getLoggedInUser().getGender().equals("female") ? "Pepay" : "Popoi");

	    	Log.d("TEST0", "Script Line: New line is now: " + tempLine);
		}
		
		return tempLine;
	}
	
	public void setContext(Context context){
		this.context = context;
	}
	

}