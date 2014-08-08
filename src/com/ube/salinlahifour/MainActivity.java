package com.ube.salinlahifour;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		int SPLASH_TIME = 2 * 1000;// 3 seconds
		final Intent intent = new Intent(this, SelectUserActivity.class);
		
		   new Handler().postDelayed(new Runnable() {

		        public void run() {
		           startActivity(intent);
		           MainActivity.this.finish();
		        }    

		    }, SPLASH_TIME);
		   
	}
	
}
