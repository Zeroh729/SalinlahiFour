package com.ube.salinlahifour;

import java.util.ArrayList;

import android.widget.ImageView;

public class Scene {
	private ArrayList<Lesson> lessons;
	private ArrayList<ImageView> imagesSpots;
	private ImageView img_background;
	private int layoutID;
	
	public Scene(ImageView img_background, int layoutID){
		this.img_background = img_background;
		this.layoutID = layoutID;
		lessons = new ArrayList();
		imagesSpots = new ArrayList();
	}
	
	public void addLesson(Lesson lesson){
		lessons.add(lesson);
	}
	
	public int getLayoutID(){
		return layoutID;
	}
	
	public ArrayList<Lesson> getLessons(){
		return lessons;
	}
}
