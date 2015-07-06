package com.ube.salinlahifour.lessonActivities;

import android.widget.RelativeLayout;

import com.ube.salinlahifour.R;



public class Fiesta extends AbstractLessonActivity {
	
	public Fiesta(){
		layoutID = R.layout.lessonactivity_fiesta;
	}
	
	@Override
	protected void configureEasyLevel() {
	}

	@Override
	protected void configureMediumLevel() {
	}

	@Override
	protected void configureHardLevel() {
	}
	
	@Override
	protected void initiateViews() {
		// TODO Auto-generated method stub
		((RelativeLayout)findViewById(R.id.parent_view)).addView(getPauseButton());
	}

	@Override
	protected void update() {
		// TODO Auto-generated method stub
		
	}

	protected boolean checkAnswer(String answer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void ifAnswerIsCorrect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void ifAnswerIsWrong() {
		// TODO Auto-generated method stub
		
	}

}
