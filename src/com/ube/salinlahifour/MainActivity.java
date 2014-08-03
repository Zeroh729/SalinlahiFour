package com.ube.salinlahifour;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final Intent intent = new Intent(this, SelectUserActivity.class);
		
		Thread timer = new Thread(){
			public void run(){
			try{
				sleep(5000);
			}catch(InterruptedException e){
				e.printStackTrace();
			}finally{
				Log.d("Starting Activity","SelectUserActivty");
				startActivity(intent);
			}
			}
		};
		
		timer.run();

	}
	
}
