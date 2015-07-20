package com.ube.salinlahifour.lessonActivities;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.ube.salinlahifour.R;
import com.ube.salinlahifour.enumTypes.LevelType;

public class Solarsystem extends AbstractLessonActivity implements OnClickListener {
	
	private TextView textview;
	private Button[] buttons;
	private Button nxtbtn;
	private ImageView imgVw;
	
	public Solarsystem(){

		layoutID = R.layout.lessonactivity_feelings;
	}

	@Override
	protected void configureEasyLevel() {
		setCntQuestions(3);

	}

	@Override
	protected void configureMediumLevel() {
	//	setCntQuestions(5);

	}

	@Override
	protected void configureHardLevel() {
	//	setCntQuestions(5);

	}

	@Override
	protected void initiateViews() {
		textview = (TextView)findViewById(R.id.tv_questionbox);
		
		buttons = new Button[5];
		buttons[0] = (Button)findViewById(R.id.answerButton1);
		buttons[1] = (Button)findViewById(R.id.answerButton2);
		buttons[2] = (Button)findViewById(R.id.answerButton3);
		buttons[3] = (Button)findViewById(R.id.answerButton4);
		buttons[4] = (Button)findViewById(R.id.answerButton5);
		
		nxtbtn = (Button)findViewById(R.id.btn_next);
		
		if(activityLevel == LevelType.EASY){
			buttons[3].setVisibility(View.GONE);
			buttons[4].setVisibility(View.GONE);
		}
		
		imgVw = (ImageView)findViewById(R.id.questionImageView);
		
		for(int i = 0; i < buttons.length; i++){
			buttons[i].setOnClickListener(this);
		}
		imgVw.setOnClickListener(this);
		nxtbtn.setOnClickListener(this);

	}

	@Override
	protected void update() {
		// TODO Auto-generated method stub

		String[] choices = loadSortedChoices(getCntQuestions());
		
		for(int i = 0; i < choices.length; i++){
			buttons[i].setText(choices[i]);
		}
		loadQuestion(itemno);

		textview.setText(question);
		imgVw.setImageResource(getQuestionItem().getImageID());

	}

	@Override
	protected void ifAnswerIsCorrect() {
		// TODO Auto-generated method stub

		textview.setText(feedback);
		nxtbtn.setVisibility(View.VISIBLE);

	}

	@Override
	protected void ifAnswerIsWrong() {
		// TODO Auto-generated method stub

		textview.setText(feedback +"\n" + question);

	}

	@Override
	public void onClick(View v) {

		switch(v.getId()){
		case R.id.answerButton1: evaluate(buttons[0].getText().toString());
			items.get(0).playFilipinoSound();
			break;
		case R.id.answerButton2: evaluate(buttons[1].getText().toString()); items.get(1).playFilipinoSound();
			break;
		case R.id.answerButton3: evaluate(buttons[2].getText().toString()); items.get(2).playFilipinoSound();
			break;
		case R.id.answerButton4: evaluate(buttons[3].getText().toString()); items.get(3).playFilipinoSound();
			break;
		case R.id.answerButton5: evaluate(buttons[4].getText().toString()); items.get(4).playFilipinoSound();
			break;
		
		case R.id.nextButton: update(); break;
		case R.id.questionImageView: getQuestionItem().playEnglishSound();	}		
	}

}
