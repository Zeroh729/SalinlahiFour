package com.ube.salinlahifour;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.util.Log;

import com.ube.salinlahifour.howtoplay.HowToPlaySet;
import com.ube.salinlahifour.model.UserDetail;
import com.ube.salinlahifour.narrativeDialog.Character;
import com.ube.salinlahifour.narrativeStory.NarrativeStory;

public class SalinlahiFour extends Application{
	private static SalinlahiFour salinlahifour;
	private static Context context;
	private static UserDetail loggedInUser;
	private static Typeface fontBpreplay;
	private static Typeface fontKgsecondchances;
	private static Typeface fontPlaytime;
	private static Typeface fontPlaytimeoblique;
	private static Typeface fontKgtangledupin;
	private static Typeface fontAndy;

	private static MediaPlayer bgm;

	private static ArrayList<Item> lessonItems;	//Di ko alam ano ginagawa nito, wag nalang naten idelete =))
	
	private static ArrayList<Lesson> lessonsList;
	private static ArrayList<Character> charactersList;
	private static HashMap<String, NarrativeStory> storiesList;
	private static HashMap<String, HowToPlaySet> tutorialsList;
	
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		SalinlahiFour.salinlahifour = this;

		if (context == null)
			context = this.getApplicationContext();

		fontBpreplay = Typeface.createFromAsset(context.getAssets(), "fonts/BPREPLAYBOLD.OTF");
		fontKgsecondchances = Typeface.createFromAsset(context.getAssets(), "fonts/KGSECONDCHANCESSOLID.TTF");
		fontPlaytime = Typeface.createFromAsset(context.getAssets(), "fonts/PLAYTIME.TTF");
		fontPlaytimeoblique = Typeface.createFromAsset(context.getAssets(), "fonts/PLAYTIMEOBLIQUE.TTF");
		fontKgtangledupin = Typeface.createFromAsset(context.getAssets(), "fonts/KGTANGLEDUPINYOU.TTF");
		fontAndy = Typeface.createFromAsset(context.getAssets(), "fonts/ANDYB.TTF");
		bgm = MediaPlayer.create(this, R.raw.bgm_map);
//		bgm.setLooping(true);
		bgm.setVolume(0, 0);
//		bgm.setVolume(0.4f, 0.4f);
		
		lessonsList = new ArrayList<Lesson>();
		charactersList = new ArrayList<Character>();
		storiesList = new HashMap<String, NarrativeStory>();
		tutorialsList = new HashMap<String, HowToPlaySet>();
		
		}
	
	public static SalinlahiFour getSalinlahifour() {
		return salinlahifour;
	}

	public static void setSalinlahifour(SalinlahiFour salinlahifour) {
		SalinlahiFour.salinlahifour = salinlahifour;
	}

	public static void setLessonItems(ArrayList<Item> items) {
		SalinlahiFour.lessonItems = items;
	}
	
	public static UserDetail getLoggedInUser() {
		return loggedInUser;
	}

	public static void setLoggedInUser(UserDetail loggedInUser) {
		SalinlahiFour.loggedInUser = loggedInUser;
	}


	public static Typeface getFontBpreplay() {
		return fontBpreplay;
	}

	public static Typeface getFontKgsecondchances() {
		return fontKgsecondchances;
	}
	
	public static Typeface getFontPlaytime() {
		return fontPlaytime;
	}
	
	public static Typeface getFontKgtangledupinyou() {
		return fontKgtangledupin;
	}
	
	public static Typeface getFontPlaytimeOblique() {
		return fontPlaytimeoblique;
	}
	
	public static Typeface getFontAndy() {
		return fontAndy;
	}
	
	public static Context getContext() {
		return context;
	}

	public static void setContext(Context context) {
		SalinlahiFour.context = context;
	}
	
	public static MediaPlayer getBgm(){
		return bgm;
	}

	public static Lesson getLesson(String name){
		for(Lesson lesson : lessonsList){
			Log.d("XDXD", "Comparing " + name + " & " + lesson.getTheRealName());
			if(name.equals(lesson.getTheRealName())){
				return lesson;
			}
		}
		return null;
	}
	
	public static Lesson getLessonByClassName(String name){
		for(Lesson lesson : lessonsList){
			Log.d("TEST0", "Checking lesson : " + lesson.getTheRealName() + " & " + name);
			if(name.equals(lesson.getTheRealName())){
				return lesson;
			}
		}
		return null;
	}
	
	public static Lesson getLesson(int qentry){
		for(Lesson lesson : lessonsList){
			if(lesson.getLessonNumber() == qentry){
				return lesson;
			}
		}
		return null;
	}
	
	public static ArrayList<Lesson> getLessonsList() {
		return lessonsList;
	}

	public static void setLessonsList(ArrayList<Lesson> lessonsList) {
		SalinlahiFour.lessonsList = lessonsList;
	}

	public static ArrayList<Item> getLessonItems(String lessonname, String activityLevel){
		Lesson lesson = getLesson(lessonname);
		ArrayList<Item> item = new ArrayList();
		for(Item val : lesson.getItems()){
			if(val.getDifficulty().equalsIgnoreCase(activityLevel)){
				item.add(val);
			}
		}
		return item;
	}
	
	public static NarrativeStory getStory(String lessonname) {
		Log.d("TEST0", "Narrative Story: Getting Story.. Lessonname got:" + lessonname);
		for(String  story : storiesList.keySet()){
			Log.d("TEST0", "Narrative Story: Searching through " + story);
		}
		Log.d("TEST0", "Narrative Story: Retrieving.." + storiesList.get(lessonname).getName());
		return storiesList.get(lessonname);
	}

	public static void addStoriesList(String lessonname, NarrativeStory story) {
		storiesList.put(lessonname, story);
	}

	public static ArrayList<Character> getCharactersList() {
		return charactersList;
	}

	public static void setCharactersList(ArrayList<Character> charactersList) {
		SalinlahiFour.charactersList = charactersList;
	}

	public static HowToPlaySet getTutorial(String lessonname) {
		for(String key : tutorialsList.keySet()){
			Log.d("TEST0", "Looking for Tutorial: comparing..." + key + " & " + lessonname);
		}
		
		return tutorialsList.get(lessonname);
	}

	public static void addTutorialsList(String lessonname, HowToPlaySet tutorial) {
		tutorialsList.put(lessonname, tutorial);
	}
	
	public static Character getCharacter(String name){
		Log.d("TEST0", "Getting States : " + name);
		for(Character character : charactersList){
			Log.d("TEST0", "comparing... " + name + " & " + character.getMainName());
			if(character.getMainName().equalsIgnoreCase(name)){
				Log.d("TEST0", "states count... " + character.getStates().size());
				return character;
			}
		}
		return null;
	}
	
	public static void errorPopup(Context context, String title, String error){
		final AlertDialog.Builder builder=new AlertDialog.Builder(context);
		builder.setTitle(title);
		builder.setMessage(error);
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		builder.setPositiveButton("I'll debug it right away!", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				System.exit(0);
			}
			});
		builder.show();
	}

}
