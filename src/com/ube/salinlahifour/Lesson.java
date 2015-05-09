package com.ube.salinlahifour;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class Lesson implements Parcelable{
	private String name;
	private String activity;
	private String tutorial;
	private String narrative;
	private String iconDir;
	private String lexiconDir;
	private int lessonPreReq;
	private String description;
	private int image;
	private boolean locked;
	private int lessonNumber;
	private ArrayList<Item> items;

	public Lesson(){}
	
	public Lesson setValues(String name, String description, String className, int image, int lessonNumber){
		this.name = name;
		this.image = image;
		this.description = description;
		this.activity = "com.ube.salinlahifour.lessonActivities." + className;
		this.tutorial = "com.ube.salinlahifour.tutorials." + className;
		this.lessonNumber = lessonNumber;
		return this;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getActivity() {
		return activity;
	}

	public void setNarrative(String narrative) {
		this.narrative = narrative;
	}
	public String getNarrative() {
		return narrative;
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
	public String getIcon() {
		return this.iconDir;
	}

	public void setIcon(String icon) {
		this.iconDir = icon;
	}
	public String getLexicon(){
		return this.lexiconDir;
	}
	public void setLexicon(String path){
		lexiconDir = path;
	}
	public int getPreReq() {
		return this.lessonPreReq;
	}

	public void setPreReq(int lessonNum) {
		this.lessonPreReq = lessonNum;
	}
	public void setLocked(boolean locked){
		this.locked = locked;
	}
	
	public boolean getLocked(){
		return locked;
	}

	public int getLessonNumber() {
		return lessonNumber;
	}

	public void setLessonNumber(int lessonNumber) {
		this.lessonNumber = lessonNumber;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}	

	public Lesson(Parcel in) {
	    super();
	    readFromParcel(in);
	}
	
	public static final Parcelable.Creator<Lesson> CREATOR = new Parcelable.Creator<Lesson>() {
	    public Lesson createFromParcel(Parcel in) {
	        return new Lesson(in);
	    }
	
	    public Lesson[] newArray(int size) {
	
	        return new Lesson[size];
	    }
	
	};

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int arg1) {
//		parcel.writeStringArray(new String[] { this.name, this.activity,
//            this.tutorial, this.description, this.image+""});
		dest.writeString(name);
		dest.writeString(activity);
		dest.writeString(tutorial);
		dest.writeString(description);
		dest.writeString(image+"");
		dest.writeString(lessonNumber+"");
	}
	

	public void readFromParcel(Parcel in) {	
	    this.name = in.readString();
	    this.activity = in.readString();
	    this.tutorial = in.readString();
	    this.description = in.readString();
	    this.image = Integer.parseInt(in.readString());
	    this.lessonNumber = Integer.parseInt(in.readString());
	}

}
