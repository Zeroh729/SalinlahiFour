package com.ube.salinlahifour;

//import android.R;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import org.jdom.*;
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
