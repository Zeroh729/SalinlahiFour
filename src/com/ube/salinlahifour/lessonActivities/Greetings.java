package com.ube.salinlahifour.lessonActivities;

import java.util.ArrayList;
import java.util.Random;

import com.ube.salinlahifour.R;
import com.ube.salinlahifour.SalinlahiFour;

import android.text.Html;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Greetings extends AbstractLessonActivity implements OnClickListener, OnTouchListener {

	private TextView tv_question;
	private Button[] choices;

	
	public Greetings(){
		super();
		Log.d("Greetings","Aldrin: Entered Greetings Class");
		layoutID = R.layout.lessonactivity_greetings;
		
		
	}
	@Override
	protected void initiateViews() {
		Log.d("Greetings", "LessonNumber: " + lesson.getLessonNumber());
		Log.d("Greetings", "Lesson: " + lesson.getName());
		Log.d("Greetings", "Sample: " + lesson.getItems().get(0).getWord());
		
		// TODO Auto-generated method stub
		
		tv_question = (TextView) findViewById(R.id.tv_questionbox);
		tv_question.setTypeface(SalinlahiFour.getFontAndy());
		tv_question.setText(" ");
		
		tv_question.setOnClickListener((OnClickListener) this);
		choices = new Button[3];
		choices[0] = (Button) findViewById(R.id.tv_choice1);
		choices[1] = (Button) findViewById(R.id.tv_choice2);
		choices[2] = (Button) findViewById(R.id.tv_choice3);
		setCntQuestions(3);
	}

	@Override
	protected void update() {//this is called update because it updates the screen by changing the question and the choices(if the user made it dynamic)
		// TODO Auto-generated method stub
		Log.d("Greetings","Aldrin: Updating");
		setChoices();
		loadQuestion(itemno);
		tv_question.setText(Html.fromHtml(feedback+question) );
		Log.d("Debug Family","Aldrin: Running Done");
		
	}

	@Override
	protected boolean checkAnswer(String answer) {
		// TODO Auto-generated method stub
		if(evaluate(answer)){//if correct function evaluate will check if the answer is correct and load the feedback in string feedback
			itemno++; //developer can insert effects here 
		}else{//if wrong the function evaluate will load a feedback
			//if wrong insert effects here
		}
		update();//updates the state of the game
		return true;
	}
	private void setChoices(){
		String[] sChoices = loadSortedChoices(3);//this function loads the choices from the lexicon.xml using the order of index, the number 3 is the size of the items that it will load or the number of buttons in a game
		for(int i=0; i<choices.length;i++){
			choices[i].setText(sChoices[i]);//Assign the loaded Choices to a button array "choices"
		}
	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.tv_choice1:	checkAnswer(choices[0].getText().toString());	break;
		case R.id.tv_choice2:	checkAnswer(choices[1].getText().toString());	break;	
		case R.id.tv_choice3:	checkAnswer(choices[2].getText().toString());	break;
		}
	}
}

