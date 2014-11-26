package com.ube.salinlahifour;

import java.sql.Date;

import com.ube.salinlahifour.database.UserDetailOperations;
import com.ube.salinlahifour.model.UserDetail;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class RegistrationActivity extends Activity {
	private RadioButton rdo_male;
	private RadioButton rdo_female;
	private EditText tf_name;
	
	private UserDetailOperations userDetailOperator;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		//rdo_male = (RadioButton)findViewById(R.id.rdo_male);
		//rdo_female = (RadioButton)findViewById(R.id.rdo_female);
		//tf_name = (EditText) findViewById(R.id.tf_name);
		
		userDetailOperator = new UserDetailOperations(this);
		userDetailOperator.open();	//DON'T FORGET
	}
	
	public void registerUser(View view){
		String name = tf_name.getText().toString();
		String gender;
		
	/*	switch(view.getId()){
			case R.id.rdo_male:
				gender = "male";
				break;
			case R.id.rdo_female:
				gender = "female";
				break;
			default:
				gender = "bisexual";
		}*/

		
		Intent intent = new Intent(this, MapActivity.class);
		startActivity(intent);

		//((SalinlahiFour)getApplication()).setUserID(user.getId());
		//((SalinlahiFour)getApplication()).setUserName(user.getName());
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
