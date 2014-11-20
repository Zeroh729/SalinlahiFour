package com.ube.salinlahifour.tutorials;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.ube.salinlahifour.R;

public class House extends AbstractTutorialActivity  {
	public House(){
		layoutID = R.layout.tutorial_house;
	}
	
	public void btn_play(View view){
		Log.d("BTN_PLAYCLICKED", "YES");
		Intent intent = new Intent(activityName);
		startActivity(intent);
	}

	@Override
	protected void initiateViews() {
		// TODO Auto-generated method stub
		
	}
}
