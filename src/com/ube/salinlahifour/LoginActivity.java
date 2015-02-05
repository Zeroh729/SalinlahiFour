package com.ube.salinlahifour;

import java.security.acl.LastOwnerException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qwerjk.better_text.MagicTextView;
import com.ube.salinlahifour.database.UserDetailOperations;
import com.ube.salinlahifour.database.UserLessonProgressOperations;
import com.ube.salinlahifour.debugclasses.DebugUserModuleActivity;
import com.ube.salinlahifour.model.UserDetail;
import com.ube.salinlahifour.model.UserLessonProgress;
import com.ube.salinlahifour.uibuilders.Button.BtnStatesDirector;
import com.ube.salinlahifour.uibuilders.Button.DeleteBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.LoginBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.NoBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.PopupcloseBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.RegisterBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.YesBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.RadioButton.FemaleRadioBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.RadioButton.MaleRadioBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.RadioButton.RadioBtnStatesDirector;

public class LoginActivity extends Activity implements OnClickListener, OnCheckedChangeListener{
	private ArrayList<UserDetail> userDetails;
	private UserDetailOperations userDetailOperator;
	private RelativeLayout layout_popup;
	private TextView tv_userdetails;
	private RadioButton[] rdoBtns;
	private RadioButton rdo_char1;
	private RadioButton rdo_char2;
	private RadioButton rdo_char3;
	private RadioButton rdo_char4;
	private ImageButton btn_register;
	private ImageButton btn_login;
	private ImageButton btn_delete;
	private ImageButton btn_yes;
	private ImageButton btn_no;
	private ImageButton btn_popupclose;
	private int selected;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		instantiateViews();
		
		SharedPreferences prefs = getSharedPreferences("appData", MODE_PRIVATE);
		Editor editor = prefs.edit();
		editor.putInt("lastUserID", -1);
		editor.commit();
		
		userDetailOperator = new UserDetailOperations(this);
		userDetailOperator.open();
		
		userDetails = userDetailOperator.getAllUserDetails();
		userDetailOperator.close();
		
		populateRadioButtons();
		

		switch(userDetails.size()){
			case 0: btn_login.setEnabled(false);
					break;
			case 4: btn_register.setVisibility(View.GONE);
					rdo_char4.setVisibility(View.VISIBLE); 
			case 3: rdo_char3.setVisibility(View.VISIBLE);
			case 2: rdo_char2.setVisibility(View.VISIBLE);
			case 1: rdo_char1.setVisibility(View.VISIBLE);

		}
		
	}

	private void populateRadioButtons() {
		rdoBtns = new RadioButton[]{rdo_char1, rdo_char2, rdo_char3, rdo_char4};
		for(int i = 0; i < userDetails.size(); i++){
			if(userDetails.get(i).getGender().equals("male")){
				rdoBtns[i].setBackgroundDrawable(RadioBtnStatesDirector.getImageDrawable(new MaleRadioBtnStatesBuilder()));
			}else{
				rdoBtns[i].setBackgroundDrawable(RadioBtnStatesDirector.getImageDrawable(new FemaleRadioBtnStatesBuilder()));
			}
		}
		
		selected = 0;
		UserDetail user = SalinlahiFour.getLoggedInUser();
		if(user != null){
			for(int i = 0; i < userDetails.size(); i++){
				if(userDetails.get(i).getId() == user.getId()){
					selected = i;
					break;
				}
			}
		}
		rdoBtns[selected].setChecked(true);
	}

	private void instantiateViews() {
		tv_userdetails = (TextView)findViewById(R.id.tv_userdetails);
		rdo_char1 = (RadioButton)findViewById(R.id.rdo_char1);
		rdo_char2 = (RadioButton)findViewById(R.id.rdo_char2);
		rdo_char3 = (RadioButton)findViewById(R.id.rdo_char3);
		rdo_char4 = (RadioButton)findViewById(R.id.rdo_char4);
		btn_yes = (ImageButton)findViewById(R.id.btn_yes);
		btn_no = (ImageButton)findViewById(R.id.btn_no);
		btn_popupclose = (ImageButton)findViewById(R.id.btn_popupclose);
		layout_popup = (RelativeLayout)findViewById(R.id.layout_popup);
		
		MagicTextView tv_title = (MagicTextView)findViewById(R.id.tv_title);
		for(int i = 0; i < 30; i++)
			tv_title.addOuterShadow(5, 0, 0, 0xFF164366);
		tv_title.setTypeface(SalinlahiFour.getFontKgsecondchances());
		
		TextView tv_subtitle = (TextView)findViewById(R.id.tv_subtitle);
		tv_subtitle.setTypeface(SalinlahiFour.getFontBpreplay());
		
		RadioGroup radiog_character = (RadioGroup)findViewById(R.id.radiog_character);
		radiog_character.setOnCheckedChangeListener(this);
		
		tv_userdetails.setTypeface(SalinlahiFour.getFontBpreplay());

		btn_register = (ImageButton)findViewById(R.id.btn_register);
		btn_login = (ImageButton)findViewById(R.id.btn_login);
		btn_delete = (ImageButton)findViewById(R.id.btn_delete);
		
		btn_register.setImageDrawable(BtnStatesDirector.getImageDrawable(new RegisterBtnStatesBuilder()));
		btn_login.setImageDrawable(BtnStatesDirector.getImageDrawable(new LoginBtnStatesBuilder()));
		btn_delete.setImageDrawable(BtnStatesDirector.getImageDrawable(new DeleteBtnStatesBuilder()));
		btn_yes.setImageDrawable(BtnStatesDirector.getImageDrawable(new YesBtnStatesBuilder()));
		btn_no.setImageDrawable(BtnStatesDirector.getImageDrawable(new NoBtnStatesBuilder()));
		btn_popupclose.setImageDrawable(BtnStatesDirector.getImageDrawable(new PopupcloseBtnStatesBuilder()));
		}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch(v.getId()){
			case R.id.view_blackoverlay:
				break;
			case R.id.btn_popupclose:
			case R.id.btn_no:
				layout_popup.setVisibility(View.GONE);
				break;
			case R.id.btn_yes:
            	userDetailOperator.open();
            	userDetailOperator.deleteUserDetail(userDetails.get(selected));
            	userDetailOperator.close();
            	userDetails.remove(selected);
            	
            	finish();
            	startActivity(getIntent());
				break;
			case R.id.btn_register:
				intent = new Intent();
	    		intent.setClass(getApplicationContext(), RegistrationActivityName.class);
	    		startActivity(intent);
	    		break;
			case R.id.btn_delete:
				layout_popup.setVisibility(View.VISIBLE);
	    		break;
			case R.id.btn_login:
				int selected = 0;
				for(int i = 0; i < userDetails.size(); i++){
					if(rdoBtns[i].isChecked()){
						selected = i;
						break;
					}
				}
				userDetailOperator.open();
				UserDetail user = userDetailOperator.getAllUserDetails().get(selected);
				userDetailOperator.close();
				
				SharedPreferences prefs = getSharedPreferences("appData", MODE_PRIVATE);
				Editor editor = prefs.edit();
				editor.putInt("lastUserID", user.getId());
				editor.commit();
				
				SalinlahiFour.setLoggedInUser(user);
				
				intent = new Intent(this, MapActivity.class);
				startActivity(intent);
				break;

		}
		
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch(checkedId){
		case R.id.rdo_char1:
			selected = 0;
			break;
		case R.id.rdo_char2:
			selected = 1;
			break;
		case R.id.rdo_char3:
			selected = 2;
			break;
		case R.id.rdo_char4:
			selected = 3;
			break;
		}
		UserDetail user = userDetails.get(selected);
		UserLessonProgressOperations progressOperator = new UserLessonProgressOperations(this);
		progressOperator.open();
		int goldStars = progressOperator.getGoldStarsCount(user.getId());
		progressOperator.close();
		tv_userdetails.setText("Name: " + user.getName() + "\nGold Stars: " + 0);
	}
}
