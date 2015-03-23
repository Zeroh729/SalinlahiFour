package com.ube.salinlahifour.lessonActivities;


import android.util.Log;


import com.kilobolt.framework.Game;
import com.kilobolt.framework.Screen;
import com.kilobolt.framework.implementation.AndroidGame;
import com.ube.salinlahifour.lessonActivities.SpaceShape.LoadingScreen;

public class Shape extends AbstractLAFramework {

	 @Override
	 public Screen getInitScreen() {
		 Log.d("Debug Init", "Space");
		 return new LoadingScreen(this, activityLevel, UserID, this, lesson, items); 
	 }
	 @Override
	 public void onBackPressed() {
		 getCurrentScreen().backButton();
	 }

}
