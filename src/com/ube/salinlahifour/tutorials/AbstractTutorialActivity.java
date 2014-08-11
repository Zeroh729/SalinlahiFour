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
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_abstract_tutorial);
		Bundle bundle = getIntent().getExtras();
		activityName = bundle.getString("activityClass");
		
		Log.d("activityNameFounded:", activityName);
	}
	
	public void btn_play(View view){
		Log.d("BTN_PLAYCLICKED", "YES");
		Intent intent = new Intent(activityName);
		startActivity(intent);
	}
	
}
