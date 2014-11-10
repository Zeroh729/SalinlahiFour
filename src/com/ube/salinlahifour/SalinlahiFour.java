package com.ube.salinlahifour;

import android.app.Application;

public class SalinlahiFour extends Application{
	private int userID;
	private String userName;
	
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
