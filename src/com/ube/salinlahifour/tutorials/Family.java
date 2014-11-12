package com.ube.salinlahifour.tutorials;

import android.content.Intent;
import android.util.Log;


import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.ube.salinlahifour.R;


public class Family extends AbstractTutorialActivity {
	public Family(){
		layoutID = R.layout.tutorial_family;
	}
	
	public void btn_play(View view){
		Log.d("BTN_PLAYCLICKED", "YES");
		Intent intent = new Intent(activityName);
		startActivity(intent);
	}
}
