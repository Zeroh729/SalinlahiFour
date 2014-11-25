package com.ube.salinlahifour;

import java.util.ArrayList;

import android.app.Application;

public class SalinlahiFour extends Application{
	private int userID;
	private String userName;
	private ArrayList<Item> lessonItems;
	
	public ArrayList<Item> getLessonItems() {
		return lessonItems;
	}

	public void setLessonItems(ArrayList<Item> items) {
		this.lessonItems = items;
	}

	public int getUserID(){
		return userID;
	}
	
	public void setUserID(int id){
		userID = id;
	}
	
	public String getUserName(){
		return userName;
	}
	public void setUserName(String name){
		userName = name;
	}
}
