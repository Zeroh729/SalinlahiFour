package com.ube.salinlahifour;

import java.util.ArrayList;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class Lesson implements Parcelable{
	private String name;
	private String className;
	private String activity;
	private String lexiconDir;
	private int lessonPreReq;
	private String description;
	private int image;
	private boolean locked;
	private int lessonNumber;
	private ArrayList<Item> items;
	private int tutBackground;

	public Lesson(){}
	
	public Lesson setValues(String name, String description, String className, int image, int lessonNumber){
		this.name = name;
		this.image = image;
		this.description = description;
		this.activity = "com.ube.salinlahifour.lessonActivities." + className;
		this.lessonNumber = lessonNumber;
		tutBackground = 0;
		return this;
	}
	
	public void setTutBackground(Context context, String resName){
		int resID = 0;
		
		Log.d("TEST0", "Lesson Tutorial Background: " + resName);
		
		resID = SalinlahiFour.getContext().getResources().getIdentifier(resName, "drawable", SalinlahiFour.getContext().getPackageName());
	
		if(resID != 0){
			tutBackground = resID;
			Log.d("TEST0", "Lesson Tutorial Background: " + resName + " FOUND!");
		}else{
			SalinlahiFour.errorPopup(context, "File not found:", "Add " + resName + " image file in a drawable resource folder.");
		}
		
	}
	
	public int getTutBackground(){
		return tutBackground;
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
	
	public void setActivity(String activity) {
		className = activity;
		this.activity = "com.ube.salinlahifour.lessonActivities." + activity;
	}


	public String getTheRealName(){
		return className;
	}

	public int getImage() {
		return image;
	}

	public void setImage(int image) {
		this.image = image;
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
		dest.writeString(description);
		dest.writeString(image+"");
		dest.writeString(lessonNumber+"");
	}
	

	public void readFromParcel(Parcel in) {	
	    this.name = in.readString();
	    this.activity = in.readString();
	    this.description = in.readString();
	    this.image = Integer.parseInt(in.readString());
	    this.lessonNumber = Integer.parseInt(in.readString());
	}

}
