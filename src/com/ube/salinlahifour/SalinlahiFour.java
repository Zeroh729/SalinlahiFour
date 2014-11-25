package com.ube.salinlahifour;

import java.util.ArrayList;

import android.app.Application;

import com.ube.salinlahifour.model.UserDetail;

public class SalinlahiFour extends Application{
	private UserDetail loggedInUser;
	private ArrayList<Item> lessonItems;
	
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
}
