package com.ube.salinlahifour;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.ube.salinlahifour.database.UserDetailOperations;
import com.ube.salinlahifour.uibuilders.Button.AbstractBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.BtnStatesDirector;
import com.ube.salinlahifour.uibuilders.Button.OkBtnStatesBuilder;

public class RegistrationActivityName extends Activity {
	private EditText tf_name;
	private ImageButton btn_ok;
	private UserDetailOperations userDetailOperator;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration_name);
		
		instantiateViews();
		
		
		userDetailOperator = new UserDetailOperations(this);
		userDetailOperator.open();	//DON'T FORGET
	}
	
	private void instantiateViews() {
		tf_name = (EditText) findViewById(R.id.tf_name);
		tf_name.setTypeface(SalinlahiFour.getFontBpreplay());
		
		btn_ok = (ImageButton)findViewById(R.id.btn_ok);
		btn_ok.setImageDrawable(BtnStatesDirector.getImageDrawable(new OkBtnStatesBuilder()));
		btn_ok.setBackgroundDrawable(null);
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
