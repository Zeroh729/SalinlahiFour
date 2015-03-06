package com.ube.salinlahifour;

//import android.R;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.ube.salinlahifour.database.DatabaseHandler;
import com.ube.salinlahifour.database.UserDetailOperations;
import com.ube.salinlahifour.model.UserDetail;

public class MainActivity extends Activity {
	private UserDetailOperations userDetailOperator;		
	private int SPLASH_TIME = 1 * 1000;// 3 seconds //Now 4 secs
//	private final Intent registationActivity = new Intent(this, RegistrationActivity.class);
//	private final Intent mapActivity = new Intent(this, MapActivity.class);

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SalinlahiFour.getBgm().start();
		
		
		//Load File from res or assets
		//InputStream ins = getResources().openRawResource( getResources().getIdentifier("raw/properties", "raw", getPackageName()));
		File properties = new File("/sdcard/properties.txt");
		if(!properties.exists()){
			File config = new File ("/sdcard/config.txt");
			File dhistory = new File ("/sdcard/dhistory.txt");
			File lessonlibrary = new File ("/sdcard/lessonlibrary.xml");
			File lexicon = new File ("/sdcard/lexicon.xml");
			File templatecatalogue = new File ("/sdcard/templatecatalogue.xml");
		try {
			properties.createNewFile();
			config.createNewFile();
			dhistory.createNewFile();
			lessonlibrary.createNewFile();
			lexicon.createNewFile();
			templatecatalogue.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}
		//Copy file from res/assets to file from SDCARD
		 InputStream in = getResources().openRawResource(R.raw.properties);
		 try{
		    FileOutputStream out = new FileOutputStream("/sdcard/properties.txt");
		    byte[] buff = new byte[1024];
		    int read = 0;

		    
		       while ((read = in.read(buff)) > 0) {
		          out.write(buff, 0, read);
		    
		 
		         in.close();

		         out.close();
		    }
		 }catch(Exception e){
			e.printStackTrace();
		 }
		 in = getResources().openRawResource(R.raw.config);
		 try{
		    FileOutputStream out = new FileOutputStream("/sdcard/config.txt");
		    byte[] buff = new byte[4096];
		    int read = 0;
		       while ((read = in.read(buff)) > 0) {
		          out.write(buff, 0, read);
		         in.close();
		         out.close();
		    }
		 }catch(Exception e){
		 }
		 in = getResources().openRawResource(R.raw.dhistory);
		 try{
		    FileOutputStream out = new FileOutputStream("/sdcard/dhistory.txt");
		    byte[] buff = new byte[4096];
		    int read = 0;
		       while ((read = in.read(buff)) > 0) {
		          out.write(buff, 0, read);
		         in.close();
		         out.close();
		    }
		 }catch(Exception e){
		 }
		 in = getResources().openRawResource(R.raw.lessonlibrary);
		 try{
		    FileOutputStream out = new FileOutputStream("/sdcard/lessonlibrary.xml");
		    byte[] buff = new byte[4096];
		    int read = 0;
		    while ((read = in.read(buff)) > 0) {
		          out.write(buff, 0, read);
		         in.close();
		         out.close();
		         
		    }
		 }catch(Exception e){
		 }
		 in = getResources().openRawResource(R.raw.lexicon);
		 try{
		    FileOutputStream out = new FileOutputStream("/sdcard/lexicon.xml");
		    byte[] buff = new byte[4096];
		    int read = 0;
		       while ((read = in.read(buff)) > 0) {
		          out.write(buff, 0, read);
		         in.close();
		         out.close();
		    }
		 }catch(Exception e){
		 }
		 in = getResources().openRawResource(R.raw.templatecatalogue);
		 try{
		    FileOutputStream out = new FileOutputStream("/sdcard/templatecatalogue.xml");
		    byte[] buff = new byte[4096];
		    int read = 0;
		       while ((read = in.read(buff)) > 0) {
		          out.write(buff, 0, read);
		          
		         in.close();
		      
		         out.flush();
		         out.close();
		    }
		 }catch(Exception e){
		 }
		 
		SharedPreferences prefs = getSharedPreferences("appData", MODE_PRIVATE);
		final int lastUserID = prefs.getInt("lastUserID", -1);
		final int firstTime = prefs.getInt("firstTime", -1);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		DatabaseHandler dbhandler = new DatabaseHandler(this);

		userDetailOperator = new UserDetailOperations(this);
		
		 ImageView cloud1 = (ImageView) findViewById(R.id.Splash_Cloud1);
		 ImageView cloud2 = (ImageView) findViewById(R.id.Splash_Cloud2);
		 //ImageView logo = (ImageView) findViewById(R.id.Splash_Logo);
		 TranslateAnimation animation = new TranslateAnimation(0.0f, 100.0f,0.0f, 0.0f);
		 TranslateAnimation animation2 = new TranslateAnimation(0.0f, 100.0f,0.0f, 0.0f);
		// TranslateAnimation animation3 = new TranslateAnimation(0.0f, 0.0f,0.0f, -400.0f);
		 animation.setDuration(SPLASH_TIME);
		 
		 animation.setRepeatCount(1);
		 //animation.setRepeatMode(2);
		 //animation.setFillAfter(true);
		 animation2.setDuration(SPLASH_TIME+3000);
		 animation2.setRepeatCount(1);
		 //animation2.setRepeatMode(2);
		// animation2.setFillAfter(true);
		 //animation3.setDuration(SPLASH_TIME);
		// animation3.setRepeatCount(1);
		 //animation3.setRepeatMode(2);
		 //animation3.setFillAfter(true);
		 cloud1.startAnimation(animation);
		 cloud2.startAnimation(animation2);
		 //logo.startAnimation(animation3);
		   new Handler().postDelayed(new Runnable() {

		        public void run() {
		        	if(firstTime == -1){	//If app is launched for the first time ever, go to Register Screen
		        		Intent intent = new Intent();
		        		intent.setClass(getApplicationContext(), RegistrationActivityName.class);
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
		        		
		        		intent.putExtra("UserID", user.getId());		        		
		        		if(lastUserID != -1){
			        		((SalinlahiFour)getApplication()).setLoggedInUser(user);
			        		intent.setClass(getApplicationContext(), MapActivity.class);
		        		}else
			        		intent.setClass(getApplicationContext(), RegistrationActivityName.class);
		        			startActivity(intent);
		        		}
//		            MainActivity.this.finish();
		        }    

		    }, SPLASH_TIME);
		   
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		SalinlahiFour.getBgm().start();
	}


	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		SalinlahiFour.getBgm().pause();
	}

	
}
