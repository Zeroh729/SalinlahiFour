package com.ube.salinlahifour.lessonActivities;

import android.widget.RelativeLayout;

import com.ube.salinlahifour.R;



public class Fiesta extends AbstractLessonActivity {
	
	public Fiesta(){
		layoutID = R.layout.lessonactivity_fiesta;
	}
	
	@Override
	protected void initiateViews() {
		// TODO Auto-generated method stub
		((RelativeLayout)findViewById(R.id.parent_view)).addView(getPauseButton());
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean checkAnswer(String answer) {
		// TODO Auto-generated method stub
		return false;
	}

}
