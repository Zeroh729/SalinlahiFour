package com.ube.salinlahifour.debugclasses;

import java.text.ParseException;
import java.util.ArrayList;

import android.R.layout;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ube.salinlahifour.R;
import com.ube.salinlahifour.SalinlahiFour;
import com.ube.salinlahifour.database.UserDetailOperations;
import com.ube.salinlahifour.database.UserRecordOperations;
import com.ube.salinlahifour.enumTypes.StatusType;
import com.ube.salinlahifour.model.UserRecord;

public class DebugUserRecordActivity extends Activity implements OnClickListener{
	private ListView listview;
	private static ArrayList<UserRecord> userrecords;
	
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
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
				UserRecordOperations userdb = new UserRecordOperations(DebugUserRecordActivity.this);
				userdb.open();
				userdb.deleteUserRecord(userrecords.get(position-1));
				userdb.close();
				finish();
	        	startActivity(getIntent());
			}
		});
	}

	@Override
	public void onClick(View v) {
		UserRecordOperations userdb;
		long id = ((SalinlahiFour)getApplication()).getLoggedInUser().getId();
		switch(v.getId())
		{
		case R.id.btn_populate:
			userdb = new UserRecordOperations(this);
			userdb.open();
			userdb.addUserRecord(id, "Debug", "answer1", StatusType.CORRECT.toString(), "2014-11-21");
			userdb.addUserRecord(id, "Debug", "answer2", StatusType.CORRECT.toString(), "2014-11-11");
			userdb.addUserRecord(id, "Debug", "answer3", StatusType.CORRECT.toString(), "2014-10-21");
			userdb.addUserRecord(id, "Debug", "answer4", StatusType.CORRECT.toString(), "2014-09-21");
			userdb.addUserRecord(id, "Debug", "answer5", StatusType.CORRECT.toString(), "2014-12-21");
			userdb.addUserRecord(id, "Debug", "answer6", StatusType.INCORRECT.toString(), "2014-11-07");
			userdb.addUserRecord(id, "Debug", "answer7", StatusType.CORRECT.toString(), "2014-10-22");
			userdb.addUserRecord(id, "Debug", "answer8", StatusType.INCORRECT.toString(), "2014-10-23");
			userdb.addUserRecord(id, "Debug", "answerid", StatusType.CORRECT.toString(), "2014-11-21");
			userdb.addUserRecord(id, "Debug", "answer10", StatusType.CORRECT.toString(), "2013-11-21");
			userdb.addUserRecord(id, "Debug", "answer11", StatusType.INCORRECT.toString(), "2014-11-21");
			userdb.close();
			finish();
        	startActivity(getIntent());
			break;
		case R.id.btn_extract:
			userdb = new UserRecordOperations(this);
			userdb.open();
			try {
				userrecords = userdb.getRecentUserRecordsFromUserId(id);
				userdb.close();
	            Toast toast = Toast.makeText(getApplicationContext(), "User Records count: " + userrecords.size(), Toast.LENGTH_LONG);
	            toast.show();
				ArrayList<String> records = new ArrayList();
				records.add("ID UserID LessonName CorrectAnswer Status DateCreated");
				for(int i = 0; i < userrecords.size(); i++)
					records.add(userrecords.get(i).getId() + " " + userrecords.get(i).getUserID()  + " " +  userrecords.get(i).getLessonName()
							 + " " +  userrecords.get(i).getCorrectAnswer()  + " " +  userrecords.get(i).getStatus()  + " " +  userrecords.get(i).getDateCreated());
				listview.setAdapter(new ArrayAdapter(this, layout.simple_list_item_1, records));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			break;
		case R.id.btn_refresh:
			finish();
        	startActivity(getIntent());
			break;
		}
	}
}
