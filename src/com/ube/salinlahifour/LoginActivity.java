package com.ube.salinlahifour;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ube.adapters.LoginAdapter;
import com.ube.salinlahifour.database.UserDetailOperations;
import com.ube.salinlahifour.model.UserDetail;

public class LoginActivity extends Activity {
	private ArrayList<UserDetail> userDetails;
	private ListView listview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		SharedPreferences prefs = getSharedPreferences("lastUserID", MODE_PRIVATE);
		Editor editor = prefs.edit();
		editor.putInt("lastUserID", -1);
		editor.commit();
		
		UserDetailOperations userdb = new UserDetailOperations(this);
		userdb.open();
		
		userDetails = userdb.getAllUserDetails();
		listview = (ListView) this.findViewById(R.id.listview);
		
		LoginAdapter adapter = new LoginAdapter(this, R.layout.item_user, userDetails);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
				Intent intent = new Intent(getApplicationContext(), MapActivity.class);
				startActivity(intent);
				SharedPreferences prefs = getSharedPreferences("lastUserID", MODE_PRIVATE);
				Editor editor = prefs.edit();
				editor.putInt("lastUserID", userDetails.get(position).getId());
				editor.commit();
				((SalinlahiFour)getApplication()).setUserID(userDetails.get(position).getId());
				((SalinlahiFour)getApplication()).setUserName(userDetails.get(position).getName());
			}
			
		});
	}
}
