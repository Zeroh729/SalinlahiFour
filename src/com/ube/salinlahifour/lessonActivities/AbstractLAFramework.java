package com.ube.salinlahifour.lessonActivities;



import android.content.Context;
import android.os.Bundle;
import com.kilobolt.framework.Screen;
import com.kilobolt.framework.implementation.AndroidGame;
import com.ube.salinlahifour.Lesson;
import com.ube.salinlahifour.SalinlahiFour;
import com.ube.salinlahifour.evaluationModule.Evaluation;

public abstract class AbstractLAFramework extends AndroidGame {
	protected Lesson lesson;
	protected String activityName;
	protected String activityLevel;
	protected int UserID;
	protected Evaluation evaluation;
	public static Context mContext;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		Bundle bundle = getIntent().getExtras();
		activityName = bundle.getString("activityName");
		lesson = SalinlahiFour.getLessonByClassName(activityName);
		activityLevel = bundle.getString("activityLevel");
		UserID = SalinlahiFour.getLoggedInUser().getId();
		evaluation = new Evaluation(this, lesson.getName(), activityLevel.toString());
		mContext = getBaseContext();

		evaluation.setLexiconDir(lesson.getLexicon());
		evaluation.setLexiconSize(evaluation.generateLexiconSize(lesson));
		
		super.onCreate(savedInstanceState);
	}

	public static Context getContext() {
		return mContext;
	}

	@Override
	public Screen getInitScreen() {
		return null;
	}

}
