package com.ube.salinlahifour;

import android.app.Activity;
import android.util.Log;

public class Lesson {	private String name;
	private String activity;
	private String tutorial;
	private int image;
	
	public Lesson(String name, String className, int image){
		this.name = name;
		this.image = image;
		this.activity = "com.ube.salinlahifour.lessonActivities." + className;
		this.tutorial = "com.ube.salinlahifour.tutorials." + className;
	}
		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getTutorial() {
		return tutorial;
	}

	public void setTutorial(String tutorial) {
		this.tutorial = tutorial;
	}

	public int getImage() {
		return image;
	}

	public void setImage(int image) {
		this.image = image;
	}
}
