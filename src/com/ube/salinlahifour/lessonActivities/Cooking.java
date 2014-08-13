package com.ube.salinlahifour.lessonActivities;

import java.util.ArrayList;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ube.salinlahifour.Item;
import com.ube.salinlahifour.LevelType;
import com.ube.salinlahifour.R;



public class Cooking extends AbstractLessonActivity{
	private TextView tv_dialog;
	private int index;
	
	public Cooking(){
		layoutID = R.layout.lessonactivity_cooking;
	}
	
	@Override
	protected void initiateItems() {
		Log.d("TESTINGinitiateItems","intiating items");
		items = new ArrayList();
		items.add(new Item("Bilog" , "Circle", "Which one is Bilog", null, LevelType.EASY));
		items.add(new Item("Tatsulok" , "Triangle", "Which one is Tatsulok", null, LevelType.EASY));
		items.add(new Item("Parisukat" , "Square", "Which one is Parisukat", null, LevelType.EASY));
		items.add(new Item("Parihaba" , "Rectangle", "Which one is Parihaba", null, LevelType.EASY));
		items.add(new Item("Bituin" , "Star", "Which one is Bituin", null, LevelType.EASY));
	}

	@Override
	protected void run() {
		tv_dialog.setText(questions.get(index).getLabel());
	}

	@Override
	protected void checkAnswer(String answer) {
		Log.d("TESTINGLessonActivity", "checking answers");
		index++;
	}

	@Override
	protected void initiateViews() {
		index = 0;
		tv_dialog = (TextView)findViewById(R.id.tv_dialog);
	}

	@Override
	public void onClick(View view) {
		switch(view.getId()){
		case R.id.btn_choicea:
		case R.id.btn_choiceb:
		case R.id.btn_choicec:
			checkAnswer(((TextView)findViewById(view.getId())).getText().toString());
		}
		
	}

}
