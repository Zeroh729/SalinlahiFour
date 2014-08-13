package com.ube.salinlahifour.lessonActivities;

import java.util.ArrayList;

import android.util.Log;
import android.widget.TextView;

import com.ube.salinlahifour.Item;
import com.ube.salinlahifour.LevelType;
import com.ube.salinlahifour.R;



public class CookingActivity extends AbstractLessonActivity{
	
	public CookingActivity(){
		layoutID = R.layout.lessonactivity_cooking;
	}
	
	@Override
	protected void initiateItems() {
		Log.d("TESTINGinitiateItems","intiating items");
		items = new ArrayList();
		items.add(new Item("Bilog" , "Circle", "Which on is Bilog", null, LevelType.EASY));
		items.add(new Item("Tatsulok" , "Triangle", "Which on is Tatsulok", null, LevelType.EASY));
		items.add(new Item("Parisukat" , "Square", "Which on is Parisukat", null, LevelType.EASY));
		items.add(new Item("Parihaba" , "Rectangle", "Which on is Parihaba", null, LevelType.EASY));
		items.add(new Item("Bituin" , "Star", "Which on is Bituin", null, LevelType.EASY));
	}

	@Override
	protected void run() {
		
	}

	@Override
	protected void checkAnswer() {

		Log.d("TESTINGLessonActivity", "checking answers");
	}

}
