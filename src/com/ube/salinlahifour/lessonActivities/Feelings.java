package com.ube.salinlahifour.lessonActivities;

import java.util.ArrayList;

import android.text.Html;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ube.salinlahifour.Item;
import com.ube.salinlahifour.R;
import com.ube.salinlahifour.enumTypes.LevelType;

public class Feelings extends AbstractLessonActivity implements OnClickListener, OnTouchListener{
	private final int EASY = 3;
	private final int MEDIUM = 5;
	private int totalQuestions;
	private TextView feedbackView;
	private ImageView questionView;
	private Button[] choices;
	private Button nextButton;

	public Feelings() {
		layoutID = R.layout.lessonactivity_feelings;
	}
	
	@Override
	protected void configureEasyLevel() {
		totalQuestions = EASY;
		
		//instantiate buttons as an array
		choices = new Button[totalQuestions];
		choices[0] = (Button) findViewById(R.id.feelings1);
		choices[1] = (Button) findViewById(R.id.feelings2);
		choices[2] = (Button) findViewById(R.id.feelings3);
	}

	@Override
	protected void configureMediumLevel() {
		totalQuestions = MEDIUM;
		
		//instantiate buttons as an array
		choices = new Button[totalQuestions];
		choices[0] = (Button) findViewById(R.id.feelings1);
		choices[1] = (Button) findViewById(R.id.feelings2);
		choices[2] = (Button) findViewById(R.id.feelings3);
		choices[3] = (Button) findViewById(R.id.feelings4);
		choices[4] = (Button) findViewById(R.id.feelings5);
	}

	@Override
	protected void configureHardLevel() {
		//Unsupported
	}

	@Override
	protected void initiateViews() {
		//assign UI Elements
		feedbackView = (TextView) findViewById(R.id.feelings_questionbox);
		questionView = (ImageView) findViewById(R.id.feelings_imageview);
		nextButton = (Button) findViewById(R.id.feelings_next);
		
		//add button listener to the view
		feedbackView.setOnClickListener(this);
		//Set total number of questions
		setCntQuestions(totalQuestions);
	}

	@Override
	protected void update() {
		//Set the choices
		int i = 0;
		
		if(!activityLevel.equals(LevelType.HARD)) {
			for(String s : loadSortedChoices(totalQuestions)) {
				Log.d("Dev", "Q: "+ s);
				choices[i].setText(s);
				++i;
			}
		} else {
			for(Item item : loadShuffledChoices(totalQuestions)) {
				choices[i].setText(item.getWord());
				++i;
			}
		}
		
		//Load the next question
		loadQuestion(itemno);
		questions.get(itemno).playEnglishSound();
		questionView.setImageResource(questions.get(itemno).getImageID());
		feedbackView.setText(Html.fromHtml(feedback));
	}

	@Override
	protected void ifAnswerIsCorrect() {
		toggleButtons(false);
		questions.get(itemno).playFilipinoSound();
		nextButton.setVisibility(Button.VISIBLE);
	}
	
	private void toggleButtons(boolean toggle) {
		int val;
		if(toggle) {
			val = Button.VISIBLE;
		} else {
			val = Button.INVISIBLE;
		}
		
		for(int i = 0; i < totalQuestions; i++) {
			choices[i].setVisibility(val);
		}
	}

	@Override
	protected void ifAnswerIsWrong() {
		ifAnswerIsCorrect();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		//Unsupported
		return false;
	}

	@Override
	public void onClick(View v) {
		//Get the chosen button
		switch(v.getId()) {
		case R.id.feelings1: 
			evaluate(choices[0].getText().toString());
			break;
		case R.id.feelings2: 
			evaluate(choices[1].getText().toString());
			break;
		case R.id.feelings3: 
			evaluate(choices[2].getText().toString());
			break;
		case R.id.feelings4:
			evaluate(choices[3].getText().toString());
			break;
		case R.id.feelings5:
			evaluate(choices[4].getText().toString());
			break;
		case R.id.feelings_next:
			nextButton.setVisibility(Button.INVISIBLE);
			update();
		}
		update();
	}
	
	

}
