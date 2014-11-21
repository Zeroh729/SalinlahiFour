package com.ube.salinlahifour;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.ube.adapters.LoginAdapter;
import com.ube.salinlahifour.database.UserDetailOperations;
import com.ube.salinlahifour.model.UserDetail;

public class LoginActivity extends Activity implements OnClickListener, AdapterView.OnItemClickListener{
	private ArrayList<UserDetail> userDetails;
	private ListView listview;
	private UserDetailOperations userDetailOperator;
	private Button registerButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		registerButton = (Button)findViewById(R.id.btn_register);
		
		SharedPreferences prefs = getSharedPreferences("appData", MODE_PRIVATE);
		Editor editor = prefs.edit();
		editor.putInt("lastUserID", -1);
		editor.commit();
		
		UserDetailOperations userdb = new UserDetailOperations(this);
		userdb.open();
		
		userDetails = userdb.getAllUserDetails();
		userdb.close();
		
		if(userDetails.size() == 0){
    		Intent intent = new Intent();
    		intent.setClass(getApplicationContext(), RegistrationActivity.class);
    		startActivity(intent);
		}
		
		LoginAdapter adapter = new LoginAdapter(this, R.layout.item_user, userDetails);
		userDetailOperator = new UserDetailOperations(this);
		
		listview = (ListView) this.findViewById(R.id.listview);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.btn_register:
        		Intent intent = new Intent();
        		intent.setClass(getApplicationContext(), RegistrationActivity.class);
        		startActivity(intent);
			break;
		}
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
		SharedPreferences prefs = getSharedPreferences("appData", MODE_PRIVATE);
		Editor editor = prefs.edit();
		editor.putInt("lastUserID", userDetails.get(position).getId());
		editor.commit();
		
		userDetailOperator.open();
		UserDetail user = userDetailOperator.getUserDetail(userDetails.get(position).getId());
		userDetailOperator.close();
		((SalinlahiFour)getApplication()).setLoggedInUser(user);

		Intent intent = new Intent(getApplicationContext(), MapActivity.class);
		startActivity(intent);
	}
}
