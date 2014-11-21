package com.ube.salinlahifour;

//import android.R;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.ube.salinlahifour.database.DatabaseHandler;
import com.ube.salinlahifour.database.UserDetailOperations;
import com.ube.salinlahifour.model.UserDetail;
import com.ube.salinlahifour.tools.DateTimeConverter;

public class MainActivity extends Activity {
	private UserDetailOperations userDetailOperator;		
	private int SPLASH_TIME = 2 * 1000;// 3 seconds
//	private final Intent registationActivity = new Intent(this, RegistrationActivity.class);
//	private final Intent mapActivity = new Intent(this, MapActivity.class);

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SharedPreferences prefs = getSharedPreferences("appData", MODE_PRIVATE);
		final int lastUserID = prefs.getInt("lastUserID", -1);
		final int firstTime = prefs.getInt("firstTime", -1);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		DatabaseHandler dbhandler = new DatabaseHandler(this);

		userDetailOperator = new UserDetailOperations(this);
		
		   new Handler().postDelayed(new Runnable() {

		        public void run() {
		        	if(firstTime == -1){	//If app is launched for the first time ever, go to Register Screen
		        		Intent intent = new Intent();
		        		intent.setClass(getApplicationContext(), RegistrationActivity.class);
		        		startActivity(intent);	
		        	}
		        	else if(lastUserID == -1){ //If there is no last logged in user, go to Login Screen
			        	Intent intent = new Intent();
			        	intent.setClass(getApplicationContext(), LoginActivity.class);
			        	startActivity(intent);			
		        	}
		        	else{						//If there is a last logged in user, go to Map Screen
		        		userDetailOperator.open();
		        		UserDetail user = userDetailOperator.getUserDetail(lastUserID);
		        		userDetailOperator.close();
		        		Intent intent = new Intent();
		        		if(user != null){
			        		((SalinlahiFour)getApplication()).setLoggedInUser(user);
			        		intent.setClass(getApplicationContext(), MapActivity.class);
		        		}else
			        		intent.setClass(getApplicationContext(), RegistrationActivity.class);
		        		startActivity(intent);
		        		}
//		            MainActivity.this.finish();
		        }    

		    }, SPLASH_TIME);
		   
	}
	
}
