package com.ube.salinlahifour.lessonActivities;

import java.util.ArrayList;

import android.app.Activity;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.ube.salinlahifour.Item;
import com.ube.salinlahifour.R;

import iFeedback.iFeedback;

public abstract class AbstractLessonActivity extends Activity {
	protected ArrayList<ImageView> backgrounds;
	protected ArrayList<Item> items;
	protected ArrayList<Item> questions;
	protected ArrayList<SoundPool> timeoutvoices;
	protected int layoutID;
	protected iFeedback NLG;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_lesson);
		setContentView(layoutID);
		initiateNarrationModule();
		initiateViews();
		initiateItems();
		getQuestions();
		run();
	}
	
	protected void getQuestions(){
		
		Log.d("TESTINGLessonActivity", "Aldrin: getting Questions");
		questions = new ArrayList<Item>();
		questions.add(items.get(0));
		questions.add(items.get(1));
		questions.add(items.get(2));
		questions.add(items.get(3));
		questions.add(items.get(4));
	}
	
	protected void initiateNarrationModule(){
		Log.d("TESTINGLessonActivity", "Aldrin: Initiating iFeedback..");
		NLG = new iFeedback();
		Log.d("TESTINGLessonActivity", "Aldrin: Reading iFeedback properties");
		NLG.readProperties();
		Log.d("TESTINGLessonActivity", "Aldrin: iFeedback Initiated");
		Log.d("TESTINGLessonActivity", "Aldrin: iFeedback LOL");

	}
	
	protected void showReportCard(){
	}

	abstract protected void initiateViews();
	abstract protected void initiateItems();
	abstract protected void run();
	abstract protected void checkAnswer(String answer);
}
