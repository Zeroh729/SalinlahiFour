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

	private String question;
	private int itemno;
	private String feedback = " ";
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
		itemno = 0;
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
	protected void run() {
		// TODO Auto-generated method stub
		Log.d("Greetings","Aldrin: Running");
		setChoices();
		//question = SalinlahiFour.getLessonsList().get(lesson.getLessonNumber()).getItems().get(itemno).getQuestion();
		question = lesson.getItems().get(itemno).getQuestion();
		Log.d("Greetings", itemno+". Question: "+ question );
		Log.d("Greetings", itemno+". Feedback: "+ feedback );
		tv_question.setText(Html.fromHtml(feedback+question) );
		Log.d("Debug Family","Aldrin: Running Done");
		
	}

	@Override
	protected boolean checkAnswer(String answer) {
		// TODO Auto-generated method stub
		Log.d("Greetings", itemno+". answered a question: "+ answer );
		if(evaluation.evaluateAnswer(lesson.getItems().get(itemno).getWord(), answer, UserID)){
			feedback = evaluation.getImmediateFeedback(lesson.getItems().get(itemno).getQ_num(), answer, lesson.getLessonNumber());
			//tv_question.setText( Html.fromHtml(question));
			if(itemno < questions.size()-1 && evaluation.isAlive() == true){
				itemno++;
			}else{
				Log.d("Debug Family", "Aldrin: iFeedback says its finished (Delayed Feedback)");
				feedback = evaluation.getEndofActivityFeedback(evaluation.getScore(), lesson.getLessonNumber());
				tv_question.setText(feedback);
				evaluation.updateUserLessonProgress(lesson.getName(), activityLevel.toString(), UserID);
				//feedback = NLG.GenerateDelayedFeedback(score, LessonNum);
				//feedback = "feedback placeholder";
//				timer.cancel();
				showReportCard(this);
			}		
		}else{
			//tv_question.setText( Html.fromHtml(question));]
			if(evaluation.isAlive() == false){
				Log.d("Debug Family", "Aldrin: iFeedback says its finished (Delayed Feedback)");
				feedback = evaluation.getEndofActivityFeedback(evaluation.getScore(), lesson.getLessonNumber());
				tv_question.setText(feedback);
				evaluation.updateUserLessonProgress(lesson.getName(), activityLevel.toString(), UserID);
				//feedback = NLG.GenerateDelayedFeedback(score, LessonNum);
				//feedback = "feedback placeholder";
//				timer.cancel();
				showReportCard(this);
			}else{
				feedback = evaluation.getImmediateFeedback(lesson.getItems().get(itemno).getQ_num(), answer, lesson.getLessonNumber());
				
			}
		}
		run();
		return false;
	}
	private void setChoices(){
		choices[0].setText(lesson.getItems().get(0).getWord());
		choices[1].setText(lesson.getItems().get(1).getWord());
		choices[2].setText(lesson.getItems().get(2).getWord());
		//choices[3].setText(lesson.getItems().get(3).getWord());
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

