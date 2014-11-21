package com.ube.salinlahifour;

import java.io.IOException;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ube.salinlahifour.debugclasses.DebugUserModuleActivity;
import com.ube.salinlahifour.enumTypes.LevelType;

public class MapActivity extends Activity implements OnClickListener{
	private ArrayList<Scene> scenes;
	private ImageButton[] imgBtns;
	private TextView[] txtViews;
	private Scene scene;
	private Intent intent = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		imgBtns = new ImageButton[5];
		txtViews = new TextView[5];
		
		if(((SalinlahiFour)getApplication()).getLoggedInUser() == null){
    		Intent intent = new Intent();
    		intent.setClass(getApplicationContext(), RegistrationActivity.class);
    		startActivity(intent);
		}else{
	        Toast toast = Toast.makeText(getApplicationContext(), "Welcome " + ((SalinlahiFour)getApplication()).getLoggedInUser().getName() + "!!!", Toast.LENGTH_SHORT);
	        toast.show();
			
			Log.d("PasringXML","TestTestTest");
			try {
				parseXML();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			setLayout();
		}
	}
	
	public void parseXML() throws XmlPullParserException, IOException{
		scenes = new ArrayList();
		scene = makeNewScene();
		String lessonName = "";
		String activityName = "";
		String value = "";
		int lessonImgID = 0;
		
		XmlResourceParser parser = getResources().getXml(R.xml.lessonlist);
		
        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
         if(eventType == XmlPullParser.START_DOCUMENT) {
        	 
             Log.d("Start document", "TEST");
         } else if(eventType == XmlPullParser.END_DOCUMENT) {
        	 
        	 Log.d("End document", "TEST");
         } else if(eventType == XmlPullParser.START_TAG) {
        	 
        	 Log.d("Start tag "+parser.getName(), "TEST");
         } else if(eventType == XmlPullParser.END_TAG) {
        	 if(parser.getName().equals("Lesson")){
        		 scene.addLesson(new Lesson(lessonName, activityName, lessonImgID));
        		 lessonName = "";
        		 activityName = "";
        		 value = "";
        		 lessonImgID = 0;
        	 }else if(parser.getName().equals("Name")){
        		 lessonName = value;
        	 }else if(parser.getName().equals("Image")){
        		 lessonImgID = getResources().getIdentifier(value, "drawable", getPackageName());
        	 }else if(parser.getName().equals("ActivityName")){
        		 activityName = value;
        	 }
         } else if(eventType == XmlPullParser.TEXT) {
        	 value = parser.getText();
        	 Log.d("Text "+parser.getText(), "TEST");
         }
         eventType = parser.next();
        }
		 
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
		txtViews[0] = (TextView)findViewById(R.id.tv_lesson1);
		txtViews[1] = (TextView)findViewById(R.id.tv_lesson2);
		txtViews[2] = (TextView)findViewById(R.id.tv_lesson3);
		txtViews[3] = (TextView)findViewById(R.id.tv_lesson4);
		txtViews[4] = (TextView)findViewById(R.id.tv_lesson5);
		
		for(int i = 0; i < scene.getLessons().size(); i++){
			imgBtns[i].setImageResource(scene.getLessons().get(i).getImage());
			txtViews[i].setText(scene.getLessons().get(i).getName());
			imgBtns[i].setVisibility(View.VISIBLE);
			imgBtns[i].setOnClickListener(this);
		}
	}

	@Override
	public void onClick(View view) {
		int index = -1;
		switch(view.getId()){
			case R.id.img_lesson1:
				index = 0;
				break;
			case R.id.img_lesson2:
				index = 1;
				break;
			case R.id.img_lesson3:
				index = 2;
				break;
			case R.id.img_lesson4:
				index = 3;
				break;
			case R.id.img_lesson5:
				index = 4;
				break;
			case R.id.btn_usermodule:
				intent = new Intent(this, DebugUserModuleActivity.class);
				startActivity(intent);
				break;
			case R.id.btn_register:
				intent = new Intent(this, RegistrationActivity.class);
				startActivity(intent);
				break;
			case R.id.btn_logout:
				intent = new Intent(this, LoginActivity.class);
				startActivity(intent);
				break;
		}
		
		if(index != -1){
			selectLevelPopup(index);
		}
	}
	
	public void selectLevelPopup(final int index){
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(MapActivity.this);
        alertDialog.setTitle(scene.getLessons().get(index).getName());
        alertDialog.setMessage("Choose difficulty:");
	        alertDialog.setNegativeButton("EASY", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int which) {	
	            	try{
		            	intent = new Intent(scene.getLessons().get(index).getTutorial());
		    			intent.putExtra("activityClass", scene.getLessons().get(index).getActivity());
		    			intent.putExtra("activityLevel", LevelType.EASY.toString());
		    			startActivity(intent);
		            }catch(Exception e){
		            	errorPopup(e, index);
		            }
	            }
	        });
	        
	        alertDialog.setNeutralButton("MEDIUM", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int which) {		
	            	try{
		            	intent = new Intent(scene.getLessons().get(index).getTutorial());
		    			intent.putExtra("activityClass", scene.getLessons().get(index).getActivity());
		    			intent.putExtra("activityLevel", LevelType.MEDIUM.toString());
		    			startActivity(intent);
		            }catch(Exception e){
		            	errorPopup(e, index);
		            }
	            }
	        });
	
	        alertDialog.setPositiveButton("HARD", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int which) {	
	            	try{
		            	intent = new Intent(scene.getLessons().get(index).getTutorial());
		    			intent.putExtra("activityClass", scene.getLessons().get(index).getActivity());
		    			intent.putExtra("activityLevel", LevelType.HARD.toString());
		    			startActivity(intent);		            
	    			}catch(Exception e){
		            	errorPopup(e, index);
		            }
	            }
	        });
        

        alertDialog.show();
	}
	
	private void errorPopup(Exception e, int index){
		final AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder.setTitle("Exception");
		builder.setMessage(e.toString() + "\nCheck if:\n"
				+"1. " + scene.getLessons().get(index).getTutorial() + " exists\n"
				+"2. This <activity> has <intent-filter> tags in AndroidManifest.xml");
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		builder.setNeutralButton("I'll debug it right away!", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				System.exit(0);
			}
		});
		builder.show();
	}
}