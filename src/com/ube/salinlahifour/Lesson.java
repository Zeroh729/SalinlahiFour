package com.ube.salinlahifour;

import android.app.Activity;
import android.util.Log;

public class Lesson {
	public String name;
	public String activity;
	public String tutorial;
	public int image;
	
	public Lesson(String name, String className, int image){
		this.name = name;
		this.image = image;
		this.activity = "com.ube.salinlahifour.lessonActivities." + className;
		this.tutorial = "com.ube.salinlahifour.tutorials." + className;
	}
}
