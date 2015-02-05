package com.ube.salinlahifour.lessonActivities;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.util.Log;

import com.kilobolt.framework.Screen;
import com.kilobolt.framework.implementation.AndroidGame;
import com.ube.salinlahifour.lessonActivities.PartsOFHouse.LoadingScreen;

public class House extends AbstractLAFramework  {

	 @Override
	    public Screen getInitScreen() {
	        return new LoadingScreen(this); 
	    }
	 @Override
	 public void onBackPressed() {
	 getCurrentScreen().backButton();
	 }
	@Override
	protected void initiateViews() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void run() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void checkAnswer(String answer) {
		// TODO Auto-generated method stub
		
	}
}
