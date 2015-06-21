package com.ube.salinlahifour.lessonActivities;


import android.content.Intent;
import android.util.Log;





import com.kilobolt.framework.Game;
import com.kilobolt.framework.Screen;
import com.kilobolt.framework.implementation.AndroidGame;
import com.ube.salinlahifour.SalinlahiFour;
import com.ube.salinlahifour.lessonActivities.SpaceShape.LoadingScreen;

public class Shape extends AbstractLAFramework {

	 @Override
	 public Screen getInitScreen() {
		 Log.d("Debug Init", "Space");

		 return new LoadingScreen(this, activityLevel, SalinlahiFour.getLoggedInUser().getId(), this, lesson, items, evaluation); 
	 }
	 @Override
	 public void onBackPressed() {
		 getCurrentScreen().backButton();
	 }

}
