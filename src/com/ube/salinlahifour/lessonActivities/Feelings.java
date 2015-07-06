package com.ube.salinlahifour.lessonActivities;

import java.util.ArrayList;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ube.salinlahifour.Item;
import com.ube.salinlahifour.R;
import com.ube.salinlahifour.enumTypes.LevelType;

public class Feelings extends AbstractLessonActivity implements OnClickListener {

	private static final int EASY = 3;
	private static final int MEDIUM = 5;
	private ImageView imageView;
	private TextView textView;
	private Button[] buttonArray;
	private Button nextButton;
	private ArrayList<Item> shuffledChoices;

	//CONSTRUCTOR
	public Feelings() {
		layoutID = R.layout.lessonactivity_feelings;
	}

	@Override
	//SETS THE PARAMETERS OF THE EASY LEVEL OF THE GAME
	protected void configureEasyLevel() {
		// SET THE NUMBER OF QUESTIONS TO 3 (EASY)
		setCntQuestions(EASY);
	}

	@Override
	//SETS THE PARAMETERS OF THE MEDIUM LEVEL OF THE GAME
	protected void configureMediumLevel() {
		// SET THE NUMBER OF QUESTIONS TO 5 (MEDIUM)
		setCntQuestions(MEDIUM);
	}

	@Override
	//SETS THE PARAMETERS OF THE HARD LEVEL OF THE GAME
	protected void configureHardLevel() {
		// SET THE NUMBER OF QUESTIONS TO 5 (SAME AS MEDIUM)
		configureMediumLevel();
	}

	@Override
	//INSTANTIATES ALL THE UI ELEMENTS OF THE GAME
	protected void initiateViews() {
		// INSTANTIATE TEXT AND IMAGE VIEW
		textView = (TextView) findViewById(R.id.questionView);
		imageView = (ImageView) findViewById(R.id.questionImageView);

		// INSTANTIATE BUTTONS
		buttonArray = new Button[5];
		buttonArray[0] = (Button) findViewById(R.id.answerButton1);
		buttonArray[1] = (Button) findViewById(R.id.answerButton2);
		buttonArray[2] = (Button) findViewById(R.id.answerButton3);
		buttonArray[3] = (Button) findViewById(R.id.answerButton4);
		buttonArray[4] = (Button) findViewById(R.id.answerButton5);
		
		if(activityLevel == LevelType.EASY) {
			buttonArray[3].setVisibility(View.GONE);
			buttonArray[4].setVisibility(View.GONE);
		}

		nextButton = (Button) findViewById(R.id.nextButton);
	}

	@Override
	//UPDATES THE GAME
	protected void update() {
		nextButton.setVisibility(View.INVISIBLE);
		toggleButtonArrayVisibility(View.VISIBLE);

		// IF LEVEL IS HARD SHUFFLE CHOICES
		if (activityLevel == LevelType.HARD) {
			shuffledChoices = loadShuffledChoices(MEDIUM);

			for (int i = 0; i < shuffledChoices.size(); i++) {
				buttonArray[i].setText(shuffledChoices.get(i).getWord());
			}
		} else { //ELSE LOAD CHOICES IN ORDER
			String[] choices = loadSortedChoices(getCntQuestions());

			for (int i = 0; i < choices.length; i++) {
				buttonArray[i].setText(choices[i]);
			}
		}

		//GET THE NEXT QUESTION
		loadQuestion(itemno);

		//SET THE TEXT TO THE QUESTION
		textView.setText(question);
		//SET THE IMAGE TO THE QUESTION
		imageView.setImageResource(getQuestionItem().getImageID());
	}

	@Override
	//GENERATES A CORRECT FEEDBACK WHEN THE ANSWER IS CORRECT AND
	//HIDES THE ANSWER BUTTONS AND SHOWS THE NEXT BUTTON
	protected void ifAnswerIsCorrect() {
		textView.setText(feedback);
		
		nextButton.setVisibility(View.VISIBLE);
		toggleButtonArrayVisibility(View.INVISIBLE);
	}

	@Override
	//GENERATES A WRONG ANSWEER FEEDBACK WHEN THE ANSWER IS WRONG
	protected void ifAnswerIsWrong() {
		textView.setText(feedback + "\n" + question);
	}

	@Override
	//ACTION LISTENER WHEN A BUTTON IS PRESSED
	public void onClick(View v) {
		int index = -1;
		
		//GET THE BUTTON THAT WAS PRESSED
		switch (v.getId()) {
			case R.id.answerButton1:
				index = 0;
				break;
			case R.id.answerButton2:
				index = 1;
				break;
			case R.id.answerButton3:
				index = 2;
				break;
			case R.id.answerButton4:
				index = 3;
				break;
			case R.id.answerButton5:
				index = 4;
				break;
			case R.id.nextButton:
				update();
				break;
			case R.id.questionImageView:
				getQuestionItem().playEnglishSound();
				break;
		}
		
		//IF THE BUTTON WAS AN ANSWER, EVALUATE ANSWER AND PLAY THE FILIPINO SOUND
		if(index != -1) {
			evaluate(buttonArray[index].getText().toString());
			
			if(activityLevel == LevelType.HARD) {
				shuffledChoices.get(index).playFilipinoSound();
			} else {
				items.get(index).playFilipinoSound();
			}
		}
	}
	
	//TOGGLES THE VISIBILITY OF THE ANSWERS
	private void toggleButtonArrayVisibility(int visibility) {
		for (int i = 0; i < cnt_question; i++) {
			buttonArray[i].setVisibility(visibility);
		}
	}
}
