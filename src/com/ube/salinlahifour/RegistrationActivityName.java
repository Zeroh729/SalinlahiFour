package com.ube.salinlahifour;

import java.sql.Date;

import com.ube.salinlahifour.database.UserDetailOperations;
import com.ube.salinlahifour.model.UserDetail;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class RegistrationActivityName extends Activity {
	private EditText tf_name;
	
	private UserDetailOperations userDetailOperator;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		tf_name = (EditText) findViewById(R.id.tf_name);
		
		userDetailOperator = new UserDetailOperations(this);
		userDetailOperator.open();	//DON'T FORGET
	}
	
	public void registerUser(View view){
		String name = tf_name.getText().toString();
		Intent intent = new Intent(this, RegistrationActivityGender.class);
		intent.putExtra("userName", name);
		startActivity(intent);
	}
	@Override
	protected void onPause() {
		userDetailOperator.close();
		super.onPause();
	}

	@Override
	protected void onResume() {
		userDetailOperator.open();
		super.onResume();
	} 
}
