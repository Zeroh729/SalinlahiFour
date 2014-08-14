package com.ube.salinlahifour.lessonActivities;

import java.util.ArrayList;
import java.util.Random;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ube.salinlahifour.Item;
import com.ube.salinlahifour.LevelType;
import com.ube.salinlahifour.R;



public class Cooking extends AbstractLessonActivity implements OnClickListener{
	private TextView tv_dialog;
	private TextView tv_feedback;
	private ImageButton[] choices;
	private int index;
	
	public Cooking(){
		layoutID = R.layout.lessonactivity_cooking;
	}
	
	@Override
	protected void initiateItems() {
		tv_feedback.setText("");	//delete
		Log.d("TESTINGinitiateItems","intiating items");
		items = new ArrayList();
		items.add(new Item("Tatsulok" , "Triangle", "Which one is Tatsulok", R.drawable.cooking_triangle, null, LevelType.EASY));
		items.add(new Item("Bilog" , "Circle", "Which one is Bilog", R.drawable.cooking_circle, null, LevelType.EASY));
		items.add(new Item("Parisukat" , "Square", "Which one is Parisukat", R.drawable.cooking_square, null, LevelType.EASY));
		items.add(new Item("Parihaba" , "Rectangle", "Which one is Parihaba", R.drawable.cooking_rectangle, null, LevelType.EASY));
		items.add(new Item("Bituin" , "Star", "Which one is Bituin", R.drawable.cooking_star, null, LevelType.EASY));
	}

	@Override
	protected void run() {
		setChoices();
		tv_dialog.setText(questions.get(index).getLabel());
	}

	@Override
	protected void checkAnswer(String answer) {
		Log.d("TESTINGLessonActivity", "checking answers");
		if(questions.get(index).getWord().equals(answer)){
			//NLG Part - Correct
			tv_feedback.setText("Magaling!" + answer + " is " + questions.get(index).getEnglish());
			
			if(index < questions.size()-1){
				index++;
				run();
			}
			else{
				tv_feedback.setText("Nakakatuwa! You finished the game! You learned \"Bilog\"! Play again to practice more on \"Parisukat\"");
			}
		}else{
			//NLG Part - Wrong
			tv_feedback.setText("Oops. That's " + answer + ", Try Again!");
		}
	}
	
	private void setChoices(){
		int answerIndex = new Random().nextInt(choices.length);
		ArrayList<Integer> taken = new ArrayList();
		taken.add(index);
		for(int i = 0; i < choices.length; i++){
			if(i == answerIndex){
				choices[i].setImageResource(questions.get(index).getImageID());
				choices[i].setTag(questions.get(index).getWord());
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
		index = 0;
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
