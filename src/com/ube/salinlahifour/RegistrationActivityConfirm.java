package com.ube.salinlahifour;

import java.sql.Date;

import com.ube.salinlahifour.database.UserDetailOperations;
import com.ube.salinlahifour.model.UserDetail;
import com.ube.salinlahifour.uibuilders.Button.BackBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.BtnStatesDirector;
import com.ube.salinlahifour.uibuilders.Button.OkBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.StartBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.YesBtnStatesBuilder;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import com.ube.salinlahifour.debugclasses.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class RegistrationActivityConfirm extends Activity {

	private UserDetailOperations userDetailOperator;
	private String name ;
	private String gender;
	private TextView tv_name;
	private ImageView iv_char;
	private ImageButton btn_start;
	private ImageButton btn_back;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration_confirmation);
		
		instantiateViews();

		tv_name = (TextView)findViewById(R.id.tv_name);
		iv_char = (ImageView)findViewById(R.id.iv_char);
		
		userDetailOperator = new UserDetailOperations(this);
		userDetailOperator.open();	//DON'T FORGET
		
		Intent beforeIntent = getIntent();
		Bundle bundle = beforeIntent.getExtras();
		name = bundle.getString("userName");
		gender = bundle.getString("userGender");
		
		tv_name.setTypeface(SalinlahiFour.getFontBpreplay());
		tv_name.setText("Let's go\n" + name + "!");

		switch(gender){
		case "male":
			iv_char.setImageResource(R.drawable.popoy);	
					break;
		case "female":
			iv_char.setImageResource(R.drawable.pepay);
						break;
				
		}
	}
	
	private void instantiateViews() {
		btn_start = (ImageButton)findViewById(R.id.btn_start);
		btn_back = (ImageButton)findViewById(R.id.btn_back);

		btn_start.setImageDrawable(BtnStatesDirector.getImageDrawable(new YesBtnStatesBuilder()));
		btn_back.setImageDrawable(BtnStatesDirector.getImageDrawable(new BackBtnStatesBuilder()));

		btn_start.setBackgroundDrawable(null);
		btn_back.setBackgroundDrawable(null);
	}

	public void registerUser(View view){
		UserDetail user = userDetailOperator.addUserDetail(name, gender);
		Intent intent = new Intent(this, MapActivity.class);
		startActivity(intent);
		
		SharedPreferences prefs = getSharedPreferences("appData", MODE_PRIVATE);
		Editor editor = prefs.edit();
		editor.putInt("lastUserID", user.getId());
		editor.putInt("firstTime", 1);
		editor.commit();
		
		((SalinlahiFour)getApplication()).setLoggedInUser(user);
	}
	
	public void back(View view){
		this.finish();
	}

	@Override
	protected void onPause() {
		userDetailOperator.close();
		SalinlahiFour.getBgm().pause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		userDetailOperator.open();
		SalinlahiFour.getBgm().start();
		super.onResume();
	} 
}
