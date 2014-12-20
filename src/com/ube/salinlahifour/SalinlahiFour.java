package com.ube.salinlahifour;

import java.util.ArrayList;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;

import com.ube.salinlahifour.model.UserDetail;

public class SalinlahiFour extends Application{
	private static SalinlahiFour salinlahifour;
	private static Context context;
	private static UserDetail loggedInUser;
	private static Typeface font_bpreplay;
	private static ArrayList<Item> lessonItems;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		SalinlahiFour.salinlahifour = this;

		if (context == null)
			context = this.getApplicationContext();
	
		font_bpreplay = Typeface.createFromAsset(context.getAssets(), "fonts/BPREPLAYBOLD.OTF");
	}
	
	public static SalinlahiFour getSalinlahifour() {
		return salinlahifour;
	}

	public static void setSalinlahifour(SalinlahiFour salinlahifour) {
		SalinlahiFour.salinlahifour = salinlahifour;
	}
	
	public ArrayList<Item> getLessonItems() {
		return lessonItems;
	}

	public void setLessonItems(ArrayList<Item> items) {
		this.lessonItems = items;
	}
	public UserDetail getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(UserDetail loggedInUser) {
		this.loggedInUser = loggedInUser;
	}


	public static Typeface getFontbpreplay() {
		return font_bpreplay;
	}

	public static void setFontbpreplay(Typeface font_bpreplay) {
		SalinlahiFour.font_bpreplay = font_bpreplay;
	}
	
	public static Context getContext() {
		return context;
	}

	public static void setContext(Context context) {
		SalinlahiFour.context = context;
	}

	public static Typeface getFont_bpreplay() {
		return font_bpreplay;
	}
}
