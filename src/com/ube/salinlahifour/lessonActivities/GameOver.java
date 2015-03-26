package com.ube.salinlahifour.lessonActivities;

import com.ube.salinlahifour.Lesson;
import com.ube.salinlahifour.R;
import com.ube.salinlahifour.ReportCard;
import com.ube.salinlahifour.SalinlahiFour;
import com.ube.salinlahifour.enumTypes.LevelType;
import com.ube.salinlahifour.evaluationModule.Evaluation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class GameOver extends Activity {
	private String ActName;
	private String ActLevel;
	private Lesson lesson;
	private Evaluation ev;
	private String feedback;
	private ReportCard reportCard;
	private int lessonNumber;
	private int userID;
	private  LevelType LTActLevel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_over);
		Intent beforeIntent = getIntent();
		ActName = beforeIntent.getStringExtra("ActivityName");
		ActLevel = beforeIntent.getStringExtra("ActivityLevel");
		feedback = beforeIntent.getStringExtra("EOAFeedback");
		userID = beforeIntent.getIntExtra("userID", 0);
		lessonNumber = beforeIntent.getIntExtra("LessonNum", 0);
		lesson = beforeIntent.getParcelableExtra("lesson");
		ev = new Evaluation(this, ActName, ActLevel);
		ev.setScore( beforeIntent.getIntExtra("e_score", 0));
		ev.setTotScore(beforeIntent.getIntExtra("e_total", 0));
		 ev.updateUserLessonProgress(lesson.getName(), ActLevel.toString(), SalinlahiFour.getLoggedInUser().getId());
		  LTActLevel = null;
			switch(ActLevel){
			case "EASY": LTActLevel = LevelType.EASY; break;
			case "MEDIUM": LTActLevel = LevelType.MEDIUM; break;
			case "HARD": LTActLevel = LevelType.HARD; break;
			}
		    
	}
	protected void onStart(){
		super.onStart();
		Log.d("Debug ReportCard", "On Game Over Screen" );
		Log.d("Debug ReportCard", "Name: " + ActName );
		Log.d("Debug ReportCard", "Level: " + ActLevel );
		Log.d("Debug ReportCard", "feedback: " + feedback );
		Log.d("Debug ReportCard", "user ID: " + userID );
		Log.d("Debug ReportCard", "Lesson Number: " + lessonNumber );
		Log.d("Debug ReportCard", "lesson: " + lesson.getName() );
		Log.d("Debug ReportCard", "Eval Score: " + ev.getScore() );
		Log.d("Debug ReportCard", "Eval Total Score: " + ev.getTotalScore() );
		Log.d("Debug ReportCard", "Info End" );
		
		reportCard = new ReportCard(this, lesson,LTActLevel, ev, feedback);
		new Handler().postDelayed(new Runnable() {
		    public void run() {
		    	
				//reportCard.setHeight(100);
				//reportCard.setWidth(100);
				//reportCard.setFocusable(true);
			    reportCard.reveal();
		    }
		}, 100);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
	    
	}


	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
	}
	
}
