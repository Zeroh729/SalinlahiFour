package com.ube.salinlahifour.lessonActivities;

import java.util.ArrayList;
import java.util.Random;

import com.ube.salinlahifour.R;
import com.ube.salinlahifour.SalinlahiFour;

import android.text.Html;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Greetings extends AbstractLessonActivity {

	private TextView tv_question;
	private Button[] choices;

	private String question;
	private int itemno;
	private String feedback = " ";
	public Greetings(){
		Log.d("Debug Family","Aldrin: Entered Greetings Class");
		layoutID = R.layout.lessonactivity_greetings;

		
	}
	@Override
	protected void initiateViews() {
		// TODO Auto-generated method stub
		itemno = 0;
		tv_question = (TextView) findViewById(R.id.tv_question);
		tv_question.setTypeface(SalinlahiFour.getFontAndy());
		tv_question.setText(" ");
		tv_question.setOnClickListener((OnClickListener) this);
		choices = new Button[3];
		setCntQuestions(3);
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		Log.d("Debug Family","Aldrin: Running");
		setChoices();
		question = SalinlahiFour.getLessonsList().get(0).getItems().get(itemno).getQuestion();
//		questions.get(itemno).playFilipinoSound();
		tv_question.setText(Html.fromHtml(question));
//		timer.start();
		Log.d("Debug Family","Aldrin: Running Done");
		
	}

	@Override
	protected boolean checkAnswer(String answer) {
		// TODO Auto-generated method stub
		return false;
	}
	private void setChoices(){
		choices[0].setText(SalinlahiFour.getLessonsList().get(0).getItems().get(0).getWord());
		choices[1].setText(SalinlahiFour.getLessonsList().get(0).getItems().get(0).getWord());
		choices[2].setText(SalinlahiFour.getLessonsList().get(0).getItems().get(0).getWord());
		choices[3].setText(SalinlahiFour.getLessonsList().get(0).getItems().get(0).getWord());
	}
}

