package com.ube.salinlahifour.debugclasses;

import java.util.ArrayList;

import android.R.layout;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ube.salinlahifour.R;
import com.ube.salinlahifour.database.UserRecordOperations;
import com.ube.salinlahifour.model.UserRecord;

public class DebugUserRecordActivity extends Activity {
	private ListView listview;
	private ArrayList<UserRecord> userrecords;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_debug_user_record);
		
		listview = (ListView) findViewById(R.id.listview);
		UserRecordOperations userdb = new UserRecordOperations(this);
		userdb.open();		
		
		userrecords = userdb.getAllUserRecords();
		
		ArrayList<String> records = new ArrayList();
		records.add("ID UserID LessonName CorrectAnswer Status DateCreated");
		for(int i = 0; i < userrecords.size(); i++)
			records.add(userrecords.get(i).getId() + " " + userrecords.get(i).getUserID()  + " " +  userrecords.get(i).getLessonName()
					 + " " +  userrecords.get(i).getCorrectAnswer()  + " " +  userrecords.get(i).getStatus()  + " " +  userrecords.get(i).getDateCreated());
		
		listview.setAdapter(new ArrayAdapter(this, layout.simple_list_item_1, records));
	}
}
