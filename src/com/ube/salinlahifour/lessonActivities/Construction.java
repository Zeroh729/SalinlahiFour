package com.ube.salinlahifour.lessonActivities;

import java.util.ArrayList;
import java.util.Collections;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ube.salinlahifour.Item;
import com.ube.salinlahifour.R;
import com.ube.salinlahifour.SalinlahiFour;
import com.ube.salinlahifour.enumTypes.LevelType;

public class Construction extends AbstractLessonActivity implements OnClickListener {

	private static final int EASY = 3;
	private TextView tv_dialog;
	private Button[] choiceBtnArray;
	private Button[] answerBtnArray;
	private ArrayList<String> splittedWords;
	private Button nextButton;
	private Button checkAnswerButton;
	private ArrayList<Item> shuffledChoices;
	private String givenSentence;
	private Button testButton;
	private LinearLayout lin;
	public Construction() {
		layoutID = R.layout.lessonactivity_cons;
	}

	@Override
	protected void configureEasyLevel() {
		setCntQuestions(EASY);
	}

	@Override
	protected void configureMediumLevel() {
		setCntQuestions(EASY);
	}

	@Override
	protected void configureHardLevel() {
		setCntQuestions(EASY);
	}

	@Override
	protected void initiateViews() {
		tv_dialog = (TextView) findViewById(R.id.tv_dialog);
		nextButton = (Button) findViewById(R.id.btn_next);
		checkAnswerButton = (Button) findViewById(R.id.btn_checkAnswer);

		choiceBtnArray = new Button[4];
		choiceBtnArray[0] = (Button) findViewById(R.id.choice_1);
		choiceBtnArray[1] = (Button) findViewById(R.id.choice_2);
		choiceBtnArray[2] = (Button) findViewById(R.id.choice_3);
		choiceBtnArray[3] = (Button) findViewById(R.id.choice_4);
		
		answerBtnArray = new Button[3];
		answerBtnArray[0] = (Button) findViewById(R.id.answer_1);
		answerBtnArray[1] = (Button) findViewById(R.id.answer_2);
		answerBtnArray[2] = (Button) findViewById(R.id.answer_3);
		splittedWords = new ArrayList<String>();
		//testAddButtonProgramatically();
		
	}
	//TEST FUNCTION DO NOT USE
	private void testAddButtonProgramatically(){
		
		lin = (LinearLayout) findViewById(R.id.layout_answer);
		RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		p.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
		p.setMargins(10, 5, 10, 10);
		testButton = new Button(this);
		testButton.setId(1);
		testButton.setTextSize(25);
		testButton.setTypeface(SalinlahiFour.getFontAndy());
		testButton.setTextColor(Color.WHITE);
		testButton.setText("TEST");
		testButton.setLayoutParams(p);
		testButton.setOnClickListener(this);
		lin.addView(testButton);
	}
	@Override
	protected void update() {
		nextButton.setVisibility(View.INVISIBLE);
		checkAnswerButton.setVisibility(View.VISIBLE);
		answerBtnArray[2].setVisibility(View.GONE);
		clearAnswers();
		for(int x = 0; x < choiceBtnArray.length; x++){
			choiceBtnArray[x].setVisibility(View.GONE);
		}
		if(getQuestionItem().getWord().split(" ").length >= 3)
			answerBtnArray[2].setVisibility(View.VISIBLE);
		for(int x = 0; x<getQuestionItem().getWord().split(" ").length;x++){
			choiceBtnArray[x].setVisibility(View.VISIBLE);
		}
		loadChoiceButtons();

		givenSentence = getQuestionItem().getEnglish();
		tv_dialog.setText(givenSentence);
	}

	private void loadChoiceButtons(){
		splittedWords.clear();
		
		String[] words = getQuestionItem().getWord().split(" ");
		
		for(int i = 0; i < words.length; i++) {
			if(splittedWords.size() < choiceBtnArray.length) {
				String temp = words[i].replace("_", " ");
				splittedWords.add(temp);
			} else {
				break;
			}
		}
		
		Collections.shuffle(splittedWords);

		for(int i = 0; i < splittedWords.size(); i++) {
			choiceBtnArray[i].setText(splittedWords.get(i));
		}
	}

	@Override
	protected void ifAnswerIsCorrect() {
		tv_dialog.setText(feedback);
		
		checkAnswerButton.setVisibility(View.INVISIBLE);
		nextButton.setVisibility(View.VISIBLE);
	}

	@Override
	protected void ifAnswerIsWrong() {
		tv_dialog.setText(feedback + "\n" + question);
	}

	@Override
	public void onClick(View v) {
		int btnIndex = -1;
		int ansIndex = -1;
		//Log.d("Kaartehan", "View ID: "+ v.getId());
		//if(testButton.getId()== v.getId()){
		//	Log.d("Kaartehan", "If's worked!");
		//}
		switch (v.getId()) {
			case 1: Log.d("Kaartehan", "It worked!");break;
			case R.id.choice_1:
				btnIndex = 0;
				break;
			case R.id.choice_2:
				btnIndex = 1;
				break;
			case R.id.choice_3:
				btnIndex = 2;
				break;
			case R.id.choice_4:
				btnIndex = 3;
				break;
			case R.id.answer_1:
				ansIndex = 0;
				break;
			case R.id.answer_2:
				ansIndex = 1;
				break;
			case R.id.answer_3:
				ansIndex = 2;
				break;
			case R.id.btn_next:
				update();
				break;
			case R.id.btn_checkAnswer:
				String answer = formatAnswer();
				evaluate(answer);
				break;
		}

		if(btnIndex != -1){
			addToAnswers(choiceBtnArray[btnIndex].getText().toString());
		}

		if(ansIndex != -1){
			answerBtnArray[ansIndex].setText("");
		}
	}

	private void clearAnswers(){
		for(Button butt : answerBtnArray){
			butt.setText("");
		}
	}

	private String formatAnswer(){
		String answer = "";
		for(int i = 0; i < answerBtnArray.length; i++){
			if(answerBtnArray[i].getVisibility() == View.VISIBLE){
				if(i != 0){
					answer += " ";
				}
				String temp = ((String) answerBtnArray[i].getText()).replace(" ", "_");
				answer += temp;
			}
		}
		return answer;
	}

	private void addToAnswers(String answer){
		for(int i = 0; i < answerBtnArray.length; i++){
			if(answerBtnArray[i].getVisibility() == View.VISIBLE){
				if(answerBtnArray[i].getText().equals("")){
					answerBtnArray[i].setText(answer);
					break;
				}
			}
		}
	}

}