package com.ube.salinlahifour;

import java.sql.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import database.UserDetailOperations;

public class RegistrationActivity extends Activity {
	private RadioButton rdo_male;
	private RadioButton rdo_female;
	private EditText tf_name;
	
	private UserDetailOperations userDetailOperator;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		rdo_male = (RadioButton)findViewById(R.id.rdo_male);
		rdo_female = (RadioButton)findViewById(R.id.rdo_female);
		tf_name = (EditText) findViewById(R.id.tf_name);
		
		userDetailOperator = new UserDetailOperations(this);
		userDetailOperator.open();
	}
	
	public void registerUser(View view){
		String name = tf_name.getText().toString();
		String gender;
		String dateCreated = new Date(System.currentTimeMillis()).toString();
		
		switch(view.getId()){
			case R.id.rdo_male:
				gender = "male";
				break;
			case R.id.rdo_female:
				gender = "female";
				break;
			default:
				gender = "bisexual";
		}

		userDetailOperator.addUserDetail(name, gender, dateCreated);
		
		Intent intent = new Intent(this, MapActivity.class);
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
