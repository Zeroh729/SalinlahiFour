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
	private ImageView iv_character;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirmation);
		tv_name = (TextView)findViewById(R.id.tv_name);
		userDetailOperator = new UserDetailOperations(this);
		userDetailOperator.open();	//DON'T FORGET
		iv_character = (ImageView)findViewById(R.id.iv_character);
		Intent beforeIntent =getIntent();
		Bundle bundle = beforeIntent.getExtras();
		name = bundle.getString("userName");
		gender = bundle.getString("userGender");
		
		tv_name.setText(name);

		switch(gender){
		case "male":iv_character.setImageResource(R.drawable.confirm_boy);	
					break;
		case "female":iv_character.setImageResource(R.drawable.confirm_girl);
						break;
				
		}
		
		
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

		//((SalinlahiFour)getApplication()).setUserID(user.getId());
		//((SalinlahiFour)getApplication()).setUserName(user.getName());
		((SalinlahiFour)getApplication()).setLoggedInUser(user);
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
