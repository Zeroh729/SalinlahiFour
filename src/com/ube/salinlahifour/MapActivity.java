package com.ube.salinlahifour;

import java.io.IOException;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.ube.salinlahifour.database.UserDetailOperations;
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
		//setContentView(R.layout.activity_map);

		Log.d("PasringXML","TestTestTest");
		try {
			parseXML();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setLayout();
		
		
		 final ImageButton btnOpenPopup = (ImageButton)findViewById(R.id.account_btn);
	        btnOpenPopup.setOnClickListener(new ImageButton.OnClickListener(){

	   @Override
	   public void onClick(View view) {
	    LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);  
	    View popupView = layoutInflater.inflate(R.layout.map_widgets, null);  
	             final PopupWindow popupWindow = new PopupWindow(popupView, LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);  
	             //popupWindow.dismiss();
	             popupWindow.setOutsideTouchable(true);
	             popupWindow.setBackgroundDrawable(new BitmapDrawable());
	             ImageButton reg_btn = (ImageButton)popupView.findViewById(R.id.btn_register_widget);
	             reg_btn.setOnClickListener(new ImageButton.OnClickListener(){
	        	     @Override
	        	     public void onClick(View v) {
	        	      // TODO Auto-generated method stub
	        	    	 navigateWidget(1);
	        	      popupWindow.dismiss();
	        	     }});
	             ImageButton out_btn = (ImageButton)popupView.findViewById(R.id.btn_logout_widget);
	             out_btn.setOnClickListener(new ImageButton.OnClickListener(){

	        	     @Override
	        	     public void onClick(View v) {
	        	      // TODO Auto-generated method stub
	        	    	 navigateWidget(2);
	        	      popupWindow.dismiss();
	        	     }});
	        
	             popupWindow.showAsDropDown(btnOpenPopup, 50, -30);
	         
	   }});
		
		
	}
	

	public void navigateWidget(int choice){
		switch(choice){
		case 1: Log.d("debug", "register intent go!");
				intent = new Intent(this, RegistrationActivity.class);
				startActivity(intent); 
				break;
		case 2: intent = new Intent(this, LoginActivity.class);
				startActivity(intent);
				break;
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
            	intent = new Intent(scene.getLessons().get(index).getTutorial());
    			intent.putExtra("activityClass", scene.getLessons().get(index).getActivity());
    			intent.putExtra("activityLevel", LevelType.EASY.toString());
    			startActivity(intent);
            }
        });
        
        alertDialog.setNeutralButton("MEDIUM", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {		
            	intent = new Intent(scene.getLessons().get(index).getTutorial());
    			intent.putExtra("activityClass", scene.getLessons().get(index).getActivity());
    			intent.putExtra("activityLevel", LevelType.MEDIUM.toString());
    			startActivity(intent);
            }
        });

        alertDialog.setPositiveButton("HARD", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {	
            	intent = new Intent(scene.getLessons().get(index).getTutorial());
    			intent.putExtra("activityClass", scene.getLessons().get(index).getActivity());
    			intent.putExtra("activityLevel", LevelType.HARD.toString());
    			startActivity(intent);
            }
        });

        alertDialog.show();
	}
}