package com.ube.salinlahifour.lessonActivities;

import com.kilobolt.framework.Screen;
import com.ube.salinlahifour.SalinlahiFour;
import com.ube.salinlahifour.lessonActivities.CookingColors.LoadingScreen;

public class Cooking extends AbstractLAFramework {

	@Override
	public Screen getInitScreen() {
		return new LoadingScreen(this, activityLevel, SalinlahiFour.getLoggedInUser().getId(), this, lesson, evaluation);
	}

	@Override
	public void onBackPressed() {
		getCurrentScreen().backButton();
	}
}