package com.ube.salinlahifour;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class SelectUserActivity extends Activity {

	RadioButton rdo_male;
	RadioButton rdo_female;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_user);
		rdo_male = (RadioButton)findViewById(R.id.rdo_male);
		rdo_female = (RadioButton)findViewById(R.id.rdo_female);
	}
	
	public void registerUser(View view){
		Intent intent = new Intent(this, MapActivity.class);
		startActivity(intent);
	}
}
