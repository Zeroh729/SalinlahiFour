package com.ube.salinlahifour.howtoplay;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

import com.ube.salinlahifour.SalinlahiFour;
import com.ube.salinlahifour.narrativeStory.NarrativeStory;

public class HowToPlaySet implements Cloneable{
	public ArrayList<Integer> imageRes;
	public String lessonName;
	
	public HowToPlaySet(){
		imageRes = new ArrayList<Integer>();
	}
	
	public HowToPlaySet clone(){  
	    try{  
	    	HowToPlaySet story = (HowToPlaySet) super.clone();
	        return story;
	    }catch(Exception e){ 
	        return null; 
	    }
	}
	
	public void addScreenshot(Context context, String resIdName){
		int resID = SalinlahiFour.getContext().getResources().getIdentifier(resIdName, "drawable", SalinlahiFour.getContext().getPackageName());
		
		if(resID != -1){
			imageRes.add(resID);
		}else{
			SalinlahiFour.errorPopup(context, "No image file found",  "Add " + resIdName + "file to drawable resource folder");
		}
	}
}
