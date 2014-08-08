package com.ube.salinlahifour;

import android.app.Activity;
import android.util.Log;

public class Lesson {
	public String name;
	public String activity;
	public String tutorial;
	public int image;
	
	public Lesson(String name, String activity, String tutorial, int image){
		this.name = name;
		this.image = image;
		this.activity = "com.ube.salinlahifour.lessonActivities" + activity;
		this.tutorial = "com.ube.salinlahifour.tutorials" + tutorial;
	}
}
