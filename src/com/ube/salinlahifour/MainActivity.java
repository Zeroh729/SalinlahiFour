package com.ube.salinlahifour;

//import android.R;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.ube.salinlahifour.database.DatabaseHandler;
import com.ube.salinlahifour.database.UserDetailOperations;
import com.ube.salinlahifour.model.UserDetail;

public class MainActivity extends Activity {
	private UserDetailOperations userDetailOperator;		
	private int SPLASH_TIME = 2 * 1000;// 3 seconds
//	private final Intent registationActivity = new Intent(this, RegistrationActivity.class);
//	private final Intent mapActivity = new Intent(this, MapActivity.class);

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SharedPreferences prefs = getSharedPreferences("lastUserID", MODE_PRIVATE);
		final int lastUserID = prefs.getInt("lastUserID", -1);
//		final int lastUserID = 0; 
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		DatabaseHandler dbhandler = new DatabaseHandler(this);

		userDetailOperator = new UserDetailOperations(this);
		userDetailOperator.open();
		
		   new Handler().postDelayed(new Runnable() {

		        public void run() {
		        	if(lastUserID == -1){
		        		Intent intent = new Intent();
		        		intent.setClass(getApplicationContext(), RegistrationActivity.class);
		        		startActivity(intent);
		        	}
		        		//startActivity(registationActivity);
		        	else{
		        		UserDetail user = userDetailOperator.getUserDetail(lastUserID);
		        		((SalinlahiFour)getApplication()).setUserID(user.getId());
		        		((SalinlahiFour)getApplication()).setUserName(user.getName());
		        		
		        		Intent intent = new Intent();
		        		intent.setClass(getApplicationContext(), MapActivity.class);
		        		startActivity(intent);
		        		
	                    Toast toast = Toast.makeText(getApplicationContext(), "Welcome " + user.getName() + "!!!", Toast.LENGTH_SHORT);
	                    toast.show();
		        		}
//		            MainActivity.this.finish();
		        }    

		    }, SPLASH_TIME);
		   
	}
	
}
