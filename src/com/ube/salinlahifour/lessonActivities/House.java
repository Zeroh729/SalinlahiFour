package com.ube.salinlahifour.lessonActivities;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.content.Intent;
import com.ube.salinlahifour.debugclasses.Log;

import com.kilobolt.framework.Game;
import com.kilobolt.framework.Screen;
import com.kilobolt.framework.implementation.AndroidGame;
import com.ube.salinlahifour.SalinlahiFour;
import com.ube.salinlahifour.evaluationModule.Evaluation;
import com.ube.salinlahifour.lessonActivities.PartsOFHouse.LoadingScreen;

public class House extends AbstractLAFramework  {

	
	 
	 @Override
	 public Screen getInitScreen() {
		 return new LoadingScreen(this, activityLevel, SalinlahiFour.getLoggedInUser().getId(), this, lesson, items,evaluation); 
	 }
	 @Override
	 public void onBackPressed() {
		 getCurrentScreen().backButton();
	 }
	
}
