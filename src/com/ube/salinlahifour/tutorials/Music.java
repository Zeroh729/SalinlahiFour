package com.ube.salinlahifour.tutorials;

import com.ube.salinlahifour.R;
import com.ube.salinlahifour.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Music extends AbstractTutorialActivity {
	public Music(){
		layoutID = R.layout.tutorial_music;
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
