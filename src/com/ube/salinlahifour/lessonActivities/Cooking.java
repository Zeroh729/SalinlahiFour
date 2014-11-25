package com.ube.salinlahifour.lessonActivities;

import java.util.ArrayList;
import java.util.Random;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ube.salinlahifour.R;
import com.ube.salinlahifour.evaluationModule.Evaluation;

public class Cooking extends AbstractLessonActivity implements OnClickListener{
	private TextView tv_dialog;
	private TextView tv_feedback;
	private Evaluation evaluator = new Evaluation();
	private ImageButton[] choices;
	private int itemno;
	
	public Cooking(){
		layoutID = R.layout.lessonactivity_cooking;
	}

	@Override
	protected void run() {
		setChoices();
		tv_dialog.setText(questions.get(itemno).getLabel());
	}

	@Override
	protected void checkAnswer(String answer) {
		Log.d("TESTINGLessonActivity", "checking answers");
		if(evaluator.evaluateAnswer(questions.get(itemno).getWord(), answer)){
			//NLG Part - Correct
			tv_feedback.setText(evaluator.getImmediateFeedback(questions.get(itemno), itemno, answer));
			
			if(itemno < questions.size()-1){
				itemno++;
				run();
			}
			else{
				tv_feedback.setText(evaluator.getEndofActivityFeedback());
			}
		}else{
			//NLG Part - Wrong
			tv_feedback.setText(evaluator.getImmediateFeedback(questions.get(itemno), itemno, answer));
		}
	}
	
	private void setChoices(){
		int answerIndex = new Random().nextInt(choices.length);
		ArrayList<Integer> taken = new ArrayList();
		taken.add(items.indexOf(questions.get(itemno)));
		for(int i = 0; i < choices.length; i++){
			if(i == answerIndex){
				choices[i].setImageResource(questions.get(itemno).getImageID());
				choices[i].setTag(questions.get(itemno).getWord());
			}
			else{
				int rand;
				do{
					rand = new Random().nextInt(items.size());
				}while(taken.contains(rand));
					taken.add(rand);
					choices[i].setImageResource(items.get(rand).getImageID());
					choices[i].setTag(items.get(rand).getWord());
			}
		}

	}

	@Override
	protected void initiateViews() {
		itemno = 0;
		tv_dialog = (TextView)findViewById(R.id.tv_dialog);
		tv_feedback = (TextView) findViewById(R.id.tv_feedback);
		choices = new ImageButton[3];
		choices[0] = (ImageButton) findViewById(R.id.btn_choicea);
		choices[1] = (ImageButton) findViewById(R.id.btn_choiceb);
		choices[2] = (ImageButton) findViewById(R.id.btn_choicec);
	}

	@Override
	public void onClick(View view) {
		Log.d("OnClickListened", "Click!");
		tv_feedback.setText("in onclick");
		switch(view.getId()){
		case R.id.btn_choicea:
		case R.id.btn_choiceb:
		case R.id.btn_choicec:
			checkAnswer(((ImageButton)findViewById(view.getId())).getTag() + "");
			break;
			default: 
				tv_feedback.setText("error in onclick");
		}
		
	}

}
