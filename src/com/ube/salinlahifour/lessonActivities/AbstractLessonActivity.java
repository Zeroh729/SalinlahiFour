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

public abstract class AbstractLessonActivity extends Activity {
	protected ArrayList<ImageView> backgrounds;
	protected ArrayList<Item> items;
	protected ArrayList<Item> questions;
	protected ArrayList<SoundPool> timeoutvoices;
	protected int layoutID;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_lesson);
		setContentView(layoutID);

		initiateViews();
		initiateItems();
		getQuestions();
		run();
	}
	
	protected void getQuestions(){

		Log.d("TESTINGLessonActivity", "getting Questions");
		questions = new ArrayList<Item>();
		questions.add(items.get(0));
		questions.add(items.get(1));
	}
	
	protected void showReportCard(){
	}

	abstract protected void initiateViews();
	abstract protected void initiateItems();
	abstract protected void run();
	abstract protected void checkAnswer(String answer);
}
