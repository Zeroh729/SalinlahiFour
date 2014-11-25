package com.ube.salinlahifour.tutorials;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ube.salinlahifour.Item;
import com.ube.salinlahifour.LessonItemLoader;
import com.ube.salinlahifour.SalinlahiFour;
import com.ube.salinlahifour.enumTypes.LevelType;

public abstract class AbstractTutorialActivity extends Activity {
	protected ArrayList<SoundPool> voiceovers;
	protected ArrayList<Item> items;
	protected ArrayList<String> description;
	protected String activityClass;
	protected String activityLevel;
	protected int layoutID;
	protected int UserID;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(layoutID);

		
		Bundle bundle = getIntent().getExtras();		
		UserID = bundle.getInt("UserID");
		
		activityClass = bundle.getString("activityClass");
		activityLevel = bundle.getString("activityLevel");
		
		Log.d(activityClass, "TEST ActivityName");
		items = LessonItemLoader.getLessonItems(this, activityClass, activityLevel);

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
	
	public void btn_play(View view){
		Log.d("BTN_PLAYCLICKED", "YES");
		goToActivity();
	}
	
	protected void goToActivity(){

		Intent intent = new Intent(activityClass);            	
		intent.putExtra("activityClass", activityClass);
		intent.putExtra("activityLevel", activityLevel);
		intent.putExtra("UserID", UserID);
		Log.d(activityClass, "TEST passing from ActivityName");

		startActivity(intent);
	}

	abstract protected void setEasyTutorial();
	abstract protected void setMediumTutorial();
	abstract protected void setHardTutorial();
	abstract protected void initiateViews();
}
