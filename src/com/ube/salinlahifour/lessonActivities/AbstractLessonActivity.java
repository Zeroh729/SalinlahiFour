package com.ube.salinlahifour.lessonActivities;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.ube.salinlahifour.Item;
import com.ube.salinlahifour.MapActivity;
import com.ube.salinlahifour.SalinlahiFour;

import iFeedback.iFeedback;

public abstract class AbstractLessonActivity extends Activity {
	protected ArrayList<ImageView> backgrounds;
	protected ArrayList<Item> items;
	protected ArrayList<Item> questions;
	protected ArrayList<SoundPool> timeoutvoices;
	protected String activityClass;
	protected String activityLevel;
	protected int layoutID;
	protected iFeedback NLG;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_lesson);
		setContentView(layoutID);
		
		Bundle bundle = getIntent().getExtras();
		activityClass = bundle.getString("activityClass");
		activityLevel = bundle.getString("activityLevel");
		Log.d(activityClass, "TEST ActivityName in lesson act");

		items = ((SalinlahiFour)getApplication()).getLessonItems();
		initiateNarrationModule();

		initiateViews();
		getQuestions();
		run();
	}
	
	protected void getQuestions(){
		Log.d("TESTINGLessonActivity", "Aldrin: getting Questions");
		questions = new ArrayList<Item>();
		switch(activityLevel){
		
		case "HARD":
		questions.add(items.get(0));
		questions.add(items.get(1));
		questions.add(items.get(2));
		questions.add(items.get(3));
		questions.add(items.get(4));
		questions.add(items.get(5));
		questions.add(items.get(6));
		questions.add(items.get(7));
		questions.add(items.get(8));
		break;
		case "MEDIUM":
			questions.add(items.get(0));
			questions.add(items.get(1));
			questions.add(items.get(2));
			questions.add(items.get(3));
			questions.add(items.get(4));
			questions.add(items.get(5));
			questions.add(items.get(6));
		break;
		case "EASY":
			questions.add(items.get(0));
			questions.add(items.get(1));
			questions.add(items.get(2));
			questions.add(items.get(3));
		break;
		}
	}
	
	protected void initiateNarrationModule(){
		Log.d("TESTINGLessonActivity", "Aldrin: Initiating iFeedback..");
		NLG = new iFeedback();
		Log.d("TESTINGLessonActivity", "Aldrin: Reading iFeedback properties");
		NLG.readProperties();
		Log.d("TESTINGLessonActivity", "Aldrin: iFeedback Initiated");
		Log.d("TESTINGLessonActivity", "Aldrin: iFeedback LOL");

	}
	protected void end_report(int choice){//THIS IS FOR TRANSFERRING TO OTHER ACTIVITIES
	    	
	    	switch(choice){
	    	case 1:
	    		Intent intent = new Intent(this, MapActivity.class);
			startActivity(intent); break;
	    	case 2:
	    		Intent intent1 = new Intent(activityClass);
	    		intent1.putExtra("activityClass", activityClass);
	    		intent1.putExtra("activityLevel", activityLevel);
				startActivity(intent1);break;
	    	}
	    	
	    }
	protected void showReportCard(){
	}

	abstract protected void initiateViews();
	abstract protected void run();
	abstract protected void checkAnswer(String answer);
}
