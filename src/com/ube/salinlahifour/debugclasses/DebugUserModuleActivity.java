package com.ube.salinlahifour.debugclasses;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ube.salinlahifour.R;
import com.ube.salinlahifour.database.UserDetailOperations;
import com.ube.salinlahifour.model.UserDetail;

public class DebugUserModuleActivity extends Activity implements OnClickListener{
	private ArrayList<UserDetail> userDetails;
	private ListView listview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_debug_user_module);
		
		UserDetailOperations userdb = new UserDetailOperations(this);
		userdb.open();
		
		userDetails = userdb.getAllUserDetails();
		userdb.close();
		listview = (ListView) this.findViewById(R.id.listview);
		
		DebugUserModuleAdapter adapter = new DebugUserModuleAdapter(this, R.layout.debug_listlayout_usermodule, userDetails);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(DebugUserModuleActivity.this);
                alertDialog.setTitle("Action");
                alertDialog.setMessage("What do you want to do?");
                alertDialog.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "You clicked on Update",
                                        Toast.LENGTH_SHORT).show();
                    }
                });
                
                alertDialog.setNegativeButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                		SharedPreferences prefs = getSharedPreferences("appData", MODE_PRIVATE);
                		final int lastUserID = prefs.getInt("lastUserID", -1);
                		
                    	UserDetailOperations userDetailOperator = new UserDetailOperations(DebugUserModuleActivity.this);
                    	userDetailOperator.open();
                    	userDetailOperator.deleteUserDetail(userDetails.get(position), lastUserID);
                    	userDetailOperator.close();
                    	userDetails.remove(position);
                    	finish();
                    	startActivity(getIntent());
                    }
                });
 
                alertDialog.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alertDialog.show();
			}
			
		});
	}

	@Override
	public void onClick(View view) {
		Intent intent;
		switch(view.getId()){
		case R.id.btn_userrecord:
			intent = new Intent(this, DebugUserRecordActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_lessonprogress:
			intent = new Intent(this, DebugUserLessonProgressActivity.class);
			startActivity(intent);
			break;
		}
	}
}
