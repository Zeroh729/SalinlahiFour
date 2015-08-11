package com.ube.salinlahifour.lessonActivities;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Intent;
import android.os.Bundle;
import com.ube.salinlahifour.debugclasses.Log;






import com.kilobolt.framework.Game;
import com.kilobolt.framework.Screen;
import com.kilobolt.framework.implementation.AndroidGame;
import com.ube.salinlahifour.SalinlahiFour;
import com.ube.salinlahifour.lessonActivities.CookingColors.LoadingScreen;

public class Cooking extends AbstractLAFramework  {

	 
	 @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}
	@Override
	 public Screen getInitScreen() {
		 Log.d("Debug Init", "Cooking");
		 
		 return new LoadingScreen(this, activityLevel, SalinlahiFour.getLoggedInUser().getId(), this, lesson, evaluation); 
	 }
	 @Override
	 public void onBackPressed() {
		 getCurrentScreen().backButton();
		
	 }
	/* public void showReportCard(){
		 super.showReportCard(this);
	 }*/
	 
	}
