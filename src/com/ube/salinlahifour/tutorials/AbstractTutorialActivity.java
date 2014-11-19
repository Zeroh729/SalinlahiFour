package com.ube.salinlahifour.tutorials;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.ube.salinlahifour.R;

public abstract class AbstractTutorialActivity extends Activity {
	
	protected ArrayList<ImageView> screenshots;
	protected ArrayList<SoundPool> voiceovers;
	protected ArrayList<String> description;
	protected String activityName;
	protected int layoutID;
	protected int UserID;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(layoutID);
		Bundle bundle = getIntent().getExtras();
		activityName = bundle.getString("activityClass");
		UserID = bundle.getInt("UserID");
		Log.d("activityNameFounded:", activityName);
	}
	
	public void btn_play(View view){
		Log.d("BTN_PLAYCLICKED", "YES");
		goToActivity();
	}
	
	protected void goToActivity(){
		Intent intent = new Intent(activityName);
		intent.putExtra("UserID", UserID);
		startActivity(intent);
	}
	
}
