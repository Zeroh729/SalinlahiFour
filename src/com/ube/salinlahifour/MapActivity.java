package com.ube.salinlahifour;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

public class MapActivity extends Activity {
	private ArrayList<Scene> scenes;
	private Scene scene;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_map);

		Log.d("PasringXML","TestTestTest");
		parseXML();
		setLayout();
	}
	
	public void parseXML(){
		scenes = new ArrayList();
		scene = makeNewScene();
		scene.addLesson(new Lesson("Cooking","CookingActivity","CookingTut",R.drawable.placeholder_lesson));
	}
	
	private Scene makeNewScene(){
		switch(scenes.size()){
		case 0:
			scenes.add(new Scene(null, R.layout.scene_layout_1));
			break;
		}
		return scenes.get(scenes.size()-1);
	}
	
	private void setLayout(){
		setContentView(scene.getLayoutID());
		ImageButton imgbtn1 = (ImageButton)findViewById(R.id.img_lesson1);
		ImageButton imgbtn2 = (ImageButton)findViewById(R.id.img_lesson2);
		ImageButton imgbtn3 = (ImageButton)findViewById(R.id.img_lesson3);
		ImageButton imgbtn4 = (ImageButton)findViewById(R.id.img_lesson4);
		ImageButton imgbtn5 = (ImageButton)findViewById(R.id.img_lesson5);
		
		switch(scene.getLessons().size()){
		case 5:
			imgbtn5.setImageResource(scene.getLessons().get(4).image);
		case 4:
			imgbtn4.setImageResource(scene.getLessons().get(3).image);
		case 3:
			imgbtn3.setImageResource(scene.getLessons().get(2).image);
		case 2:
			imgbtn2.setImageResource(scene.getLessons().get(1).image);
		case 1:
			imgbtn1.setImageResource(scene.getLessons().get(0).image);
		}
		
	}
	
}