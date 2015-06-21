package com.ube.salinlahifour.lessonActivities;

import com.ube.salinlahifour.R;
import com.ube.salinlahifour.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Feelings extends AbstractLessonActivity implements OnClickListener{

	private TextView txtBox_Box;
	private Button[] btn_choices;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lessonactivity_feelings);
	}

	public Feelings()
	{
		layoutID = R.layout.lessonactivity_feelings;
	}
	
	@Override
	protected void configureEasyLevel() {
		// TODO Auto-generated method stub
		setCntQuestions(3);
	}

	@Override
	protected void configureMediumLevel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void configureHardLevel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initiateViews() {
		// TODO Auto-generated method stub
		txtBox_Box = (TextView) findViewById(R.id.txtBox_box);
		
		btn_choices = new Button[3];
		
		btn_choices[0] = (Button)findViewById(R.id.btn_choice1);
		btn_choices[1] = (Button)findViewById(R.id.btn_choice2);
		btn_choices[2] = (Button)findViewById(R.id.btn_choice3);
	}

	@Override
	protected void update() {
		// TODO Auto-generated method stub
		String[] userChoices = loadSortedChoices(getCntQuestions());
		
		for(int i = 0; i < btn_choices.length; i++)
		{
			btn_choices[i].setText(userChoices[i]);
		}
		txtBox_Box.setText(feedback + "\n" + getQuestionItem().getQuestion());
	}

	@Override
	protected void ifAnswerIsCorrect() {
		// TODO Auto-generated method stub
		update();
	}

	@Override
	protected void ifAnswerIsWrong() {
		// TODO Auto-generated method stub
		update();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
			case R.id.btn_choice1: evaluate(btn_choices[0].getText().toString()); break;
			case R.id.btn_choice2: evaluate(btn_choices[1].getText().toString()); break;
			case R.id.btn_choice3: evaluate(btn_choices[2].getText().toString()); break;
		}
	}
}
