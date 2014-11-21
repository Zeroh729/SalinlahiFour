package com.ube.salinlahifour.tutorials;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ube.salinlahifour.Item;
import com.ube.salinlahifour.LessonItemLoader;
import com.ube.salinlahifour.tutorials.*;
import com.ube.salinlahifour.enumTypes.LevelType;

public abstract class AbstractTutorialActivity extends Activity {
	protected ArrayList<SoundPool> voiceovers;
	protected ArrayList<Item> items;
	protected ArrayList<String> description;
	protected String activityClass;
	protected String activityName;
	protected String activityLevel;
	protected int layoutID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try{
			setContentView(layoutID);
		}catch(Exception e){
			errorPopup("Layout ID not found", "Check if:\n"
					+ "1. XML layout for this activity exists.\n"
					+ "2. layoutID has been set in Constructor");
		}
		Bundle bundle = getIntent().getExtras();
		activityClass = bundle.getString("activityClass");
		activityLevel = bundle.getString("activityLevel");
		
		activityName = activityClass.replace("com.ube.salinlahifour.lessonActivities.", "");
		
		Log.d(activityClass, "TEST ActivityName");
		items = LessonItemLoader.getLessonItems(this, activityClass, activityLevel);
		
		if(items == null){
			errorPopup("Lesson Items Insufficient", LessonItemLoader.getError());
		}
		else{
			Log.d("items size: " + items.size(),"TESTESTEST");
			initiateViews();
			
			if(activityLevel.equals(LevelType.EASY.toString())){
				setEasyTutorial();
			}else if(activityLevel.equals(LevelType.MEDIUM.toString())){
				setMediumTutorial();
			}else if(activityLevel.equals(LevelType.HARD.toString())){
				setHardTutorial();
			}
		}
	}
	
	public void btn_play(View view){
		Log.d("BTN_PLAYCLICKED", "YES");
		goToActivity();
	}
	
	protected void goToActivity(){
		try{
			Intent intent = new Intent(activityClass);            	
			intent.putExtra("activityName", activityName);
			intent.putExtra("activityLevel", activityLevel);
			Log.d(activityClass, "TEST passing from ActivityName");
			startActivity(intent);
		}catch(Exception e){
			errorPopup("Activity Error", "Check if:\n"
					+ "1. " + activityClass + " exists.\n"
					+ "2. This <activity> has <intent-filter> tags in AndroidManifest.xml");
		}
	}
	
	private void errorPopup(String title, String error){
		final AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder.setTitle(title);
		builder.setMessage(error);
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		builder.setPositiveButton("I'll debug it right away!", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				System.exit(0);
			}
			});
		builder.show();
	}

	abstract protected void setEasyTutorial();
	abstract protected void setMediumTutorial();
	abstract protected void setHardTutorial();
	abstract protected void initiateViews();
}
