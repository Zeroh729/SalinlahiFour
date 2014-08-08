package com.ube.salinlahifour;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MapActivity extends Activity implements OnClickListener{
	private ArrayList<Scene> scenes;
	private ImageButton[] imgBtns;
	private Scene scene;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		imgBtns = new ImageButton[5];
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
		imgBtns[0] = (ImageButton)findViewById(R.id.img_lesson1);
		imgBtns[1] = (ImageButton)findViewById(R.id.img_lesson2);
		imgBtns[2] = (ImageButton)findViewById(R.id.img_lesson3);
		imgBtns[3] = (ImageButton)findViewById(R.id.img_lesson4);
		imgBtns[4] = (ImageButton)findViewById(R.id.img_lesson5);
		
		for(int i = 0; i < scene.getLessons().size(); i++){
			imgBtns[i].setImageResource(scene.getLessons().get(i).image);
			imgBtns[i].setVisibility(View.VISIBLE);
			imgBtns[i].setOnClickListener(this);
		}
	}

	@Override
	public void onClick(View view) {
		Intent intent = null;
		switch(view.getId()){
		case R.id.img_lesson1:
			intent = new Intent(this,MainActivity.class);
			break;
		case R.id.img_lesson2:
			intent = new Intent(scene.getLessons().get(1).tutorial);
			break;
		case R.id.img_lesson3:
			intent = new Intent(scene.getLessons().get(2).tutorial);
			break;
		case R.id.img_lesson4:
			intent = new Intent(scene.getLessons().get(3).tutorial);
			break;
		case R.id.img_lesson5:
			intent = new Intent(scene.getLessons().get(4).tutorial);
			break;
		}
		if(intent != null)
			startActivity(intent);
	}
	
}