package com.ube.salinlahifour.debugclasses;

import java.util.ArrayList;

import android.R.layout;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ube.salinlahifour.R;
import com.ube.salinlahifour.database.UserLessonProgressOperations;
import com.ube.salinlahifour.model.UserLessonProgress;

public class DebugUserLessonProgressActivity extends Activity {
	private ListView listview;
	private ArrayList<UserLessonProgress> userlessons;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_debug_user_lesson_progress);	
		
		listview = (ListView) findViewById(R.id.listview);
		UserLessonProgressOperations userdb = new UserLessonProgressOperations(this);
		userdb.open();		
		
		userlessons = userdb.getAllUserLessonProgress();

		ArrayList<String> records = new ArrayList();
		records.add("ID UserID LessonName EasyStar MedStar HardStar");
		for(int i = 0; i < userlessons.size(); i++)
			records.add(userlessons.get(i).getId() + " " + userlessons.get(i).getUserID() + " " + userlessons.get(i).getLessonName()
					 + " " + userlessons.get(i).getEasyStar()  + " " + userlessons.get(i).getMediumStar()  + " " + userlessons.get(i).getHardStar());
		
		listview.setAdapter(new ArrayAdapter(this, layout.simple_list_item_1, records));
	}
}
