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

public class RegistrationActivityGender extends Activity {
	private View rdo_selected;
	
	private String name;
	private UserDetailOperations userDetailOperator;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration2);
		rdo_selected = (RadioButton)findViewById(R.id.rdo_males);
		userDetailOperator = new UserDetailOperations(this);
		userDetailOperator.open();	//DON'T FORGET
		Intent beforeIntent = getIntent();
		 name = beforeIntent.getStringExtra("userName");
		
	}
	
	public void onRadioButtonClicked(View view) {
		rdo_selected = view;
	}
	
	public void registerUser(View view){
		String gender;
		
		
		
		switch(rdo_selected.getId()){
			case R.id.rdo_males:
				gender = "male";
				break;
			case R.id.rdo_females:
				gender = "female";
				break;
			default:
				gender = "bisexual";
		}

		Intent intent = new Intent(this, RegistrationActivityConfirm.class);
		Bundle extras = new Bundle();
		extras.putString("userName", name);
		extras.putString("userGender", gender);
		intent.putExtras(extras);
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
