package com.ube.salinlahifour;

//import android.R;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.xml.sax.InputSource;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.ube.salinlahifour.database.DatabaseHandler;
import com.ube.salinlahifour.database.UserDetailOperations;
import com.ube.salinlahifour.howtoplay.HowToPlay;
import com.ube.salinlahifour.howtoplay.HowToPlaySet;
import com.ube.salinlahifour.model.UserDetail;
import com.ube.salinlahifour.narrativeStory.NarrativeStory;
import com.ube.salinlahifour.tutorials.Tutorial;
import com.ube.salinlahifour.contentParser.*;
import com.ube.salinlahifour.narrativeDialog.Character;

public class MainActivity extends Activity {
	private UserDetailOperations userDetailOperator;		
	private int SPLASH_TIME = 10 * 1000;// 3 seconds //Now 4 secs
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
			File lexicon_cooking = new File ("/sdcard/lexicon_cooking.xml");
			File lexicon_family = new File ("/sdcard/lexicon_family.xml");
			File lexicon_house = new File ("/sdcard/lexicon_house.xml");
			File lexicon_shape = new File ("/sdcard/lexicon_shape.xml");
			File templatecatalogue = new File ("/sdcard/templatecatalogue.xml");
		try {
			properties.createNewFile();
			config.createNewFile();
			dhistory.createNewFile();
			lessonlibrary.createNewFile();
			lexicon.createNewFile();
			lexicon_cooking.createNewFile();
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
		 in = getResources().openRawResource(R.raw.lexicon_cooking);
		 try{
		    FileOutputStream out = new FileOutputStream("/sdcard/lexicon_cooking.xml");
		    byte[] buff = new byte[4096];
		    int read = 0;
		       while ((read = in.read(buff)) > 0) {
		          out.write(buff, 0, read);
		         in.close();
		         out.close();
		    }
		 }catch(Exception e){
		 }
		 in = getResources().openRawResource(R.raw.lexicon_family);
		 try{
		    FileOutputStream out = new FileOutputStream("/sdcard/lexicon_family.xml");
		    byte[] buff = new byte[4096];
		    int read = 0;
		       while ((read = in.read(buff)) > 0) {
		          out.write(buff, 0, read);
		         in.close();
		         out.close();
		    }
		 }catch(Exception e){
		 }
		 in = getResources().openRawResource(R.raw.lexicon_house);
		 try{
		    FileOutputStream out = new FileOutputStream("/sdcard/lexicon_house.xml");
		    byte[] buff = new byte[4096];
		    int read = 0;
		       while ((read = in.read(buff)) > 0) {
		          out.write(buff, 0, read);
		         in.close();
		         out.close();
		    }
		 }catch(Exception e){
		 }
		 in = getResources().openRawResource(R.raw.lexicon_shape);
		 try{
		    FileOutputStream out = new FileOutputStream("/sdcard/lexicon_shape.xml");
		    byte[] buff = new byte[4096];
		    int read = 0;
		       while ((read = in.read(buff)) > 0) {
		          out.write(buff, 0, read);
		         in.close();
		         out.close();
		    }
		 }catch(Exception e){
		 }
//		 SalinlahiFour.getContext().getResources().getIdentifier(xmlfilename, "raw", SalinlahiFour.getContext().getPackageName());
		 in = getResources().openRawResource(R.raw.lexicon_animals);
		 try{
		    FileOutputStream out = new FileOutputStream("/sdcard/lexicon_animals.xml");
		    byte[] buff = new byte[4096];
		    int read = 0;
		       while ((read = in.read(buff)) > 0) {
		          out.write(buff, 0, read);
		         in.close();
		         out.close();
		    }
		 }catch(Exception e){
		 }
		 in = getResources().openRawResource(R.raw.lexicon_greet);
		 try{
		    FileOutputStream out = new FileOutputStream("/sdcard/lexicon_greet.xml");
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

		 parseLessons();
		 parseLexicon();
		 parseCharacters();
		 parseStories();
		 parseTutorials();
		 
//		   new Handler().postDelayed(new Runnable() {
//
//		        public void run() {
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
//		        }    
//
//		    }, SPLASH_TIME);
		   
	}

	
	private void parseLexicon(){
		Log.d("Jim Parse On", "Item Parsing Starts");
		//R.raw.lexicon_animals;
		//R.raw.lexicon_cooking
		//R.raw.lexicon_family
		//R.raw.lexicon_house
		//R.raw.lexicon_shape
		Log.d("LAST", "Index: "+ SalinlahiFour.getLessonsList().size() );
		for(int i = 0; i < SalinlahiFour.getLessonsList().size(); i++){
			ArrayList<Item> items = new ArrayList();
			//parse lexicon.xml or watver
			//Log.d("Lexicon Parsing", "In " + i);
			try {
				items = (ArrayList<Item>) XMLContentParser.parseItem(this, new FileInputStream("/sdcard/"+ SalinlahiFour.getLessonsList().get(i).getLexicon()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				Log.d("Jim Parse On", "Caught like a fly:" + e);
				e.printStackTrace();
				
			}
			SalinlahiFour.getLesson((i+1)).setItems(items);
			

		}
		Log.d("Jim Parse On", "Item Parsing Ends");
		Log.d("Jim Parse On", "Sample Item: " + SalinlahiFour.getLessonsList().get(0).getItems().get(0).getWord());
		
		
	}
	
	
	private void parseLessons(){
		ArrayList<Lesson> lessons = new ArrayList();
		Log.d("Jim Parse On", "Lesson Starts");
		//parse raw/lessonlist.xml	(kung puede irename, gawin list_lesson.xml.... para lang consistent (arte))
		lessons = (ArrayList<Lesson>) XMLContentParser.parseLesson(this, this.getResources().openRawResource(R.raw.lessonlibrary));
		for(int x= 0; x<lessons.size(); x++){
		Log.d("Jim Parse On", "Sample Output: "+ x +" " + lessons.get(x).getName());
		}
		Log.d("Jim Parse On", "Lesson Ends");
		SalinlahiFour.setLessonsList(lessons);
	}
	
	private void parseCharacters(){
		ArrayList<Character> characters = new ArrayList();
		
		characters = XMLContentParser.parseCharacter(this, this.getResources().openRawResource(R.raw.list_character));
		
		//parse raw/list_characters.xml
		
		SalinlahiFour.setCharactersList(characters);
		for(int i = 0 ; i < SalinlahiFour.getCharactersList().size(); i++){
			for(String state : SalinlahiFour.getCharactersList().get(i).getStates().keySet()){
				Log.d("TEST0", "Parsed Characters: " + SalinlahiFour.getCharactersList().get(i).getName() + " State: " + state);
			}
		}
	}
	
	private void parseStories(){
		ArrayList<NarrativeStory> stories = new ArrayList();
		
		stories = XMLContentParser.parseNarrativeStory(this, this.getResources().openRawResource(R.raw.list_stories));
		//parse list_stories.xml
		
		Log.d("TEST0", "Stories parsed: " + stories.size());
		
		for(int i = 0; i < stories.size(); i++){
			SalinlahiFour.addStoriesList(stories.get(i).getName(), stories.get(i));
			Log.d("TEST0", "Story name: " + stories.get(i).getName());
		}
		
		
	}
	
	private void parseTutorials(){
		ArrayList<HowToPlaySet> tutorials = new ArrayList();
		
		tutorials = XMLContentParser.parseHowToPlay(this, this.getResources().openRawResource(R.raw.list_howtoplay));
		
		//parse list_tutorial.xml

		for(int i = 0; i < tutorials.size(); i++){
			SalinlahiFour.addTutorialsList(tutorials.get(i).lessonName, tutorials.get(i));
		}
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
