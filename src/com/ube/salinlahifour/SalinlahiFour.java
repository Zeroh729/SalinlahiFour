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
	private static Typeface fontBpreplay;
	private static Typeface fontKgsecondchances;

	private static ArrayList<Item> lessonItems;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		SalinlahiFour.salinlahifour = this;

		if (context == null)
			context = this.getApplicationContext();

		fontBpreplay = Typeface.createFromAsset(context.getAssets(), "fonts/BPREPLAYBOLD.OTF");
		fontKgsecondchances = Typeface.createFromAsset(context.getAssets(), "fonts/KGSECONDCHANCESSOLID.TTF");
	}
	
	public static SalinlahiFour getSalinlahifour() {
		return salinlahifour;
	}

	public static void setSalinlahifour(SalinlahiFour salinlahifour) {
		SalinlahiFour.salinlahifour = salinlahifour;
	}
	
	public static ArrayList<Item> getLessonItems() {
		return lessonItems;
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
	
	public static Context getContext() {
		return context;
	}

	public static void setContext(Context context) {
		SalinlahiFour.context = context;
	}
}
