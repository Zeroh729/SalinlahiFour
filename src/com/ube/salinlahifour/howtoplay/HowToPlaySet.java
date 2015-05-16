package com.ube.salinlahifour.howtoplay;

import java.util.ArrayList;

import android.content.Context;

import com.ube.salinlahifour.SalinlahiFour;

public class HowToPlaySet {
	public ArrayList<Integer> imageRes;
	public String lessonName;
	
	public HowToPlaySet(){
		imageRes = new ArrayList<Integer>();
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
