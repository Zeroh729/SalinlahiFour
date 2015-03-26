package com.ube.salinlahifour;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.qwerjk.better_text.MagicTextView;
import com.ube.salinlahifour.database.UserLessonProgressOperations;
import com.ube.salinlahifour.debugclasses.DebugUserModuleActivity;
import com.ube.salinlahifour.enumTypes.LevelType;
import com.ube.salinlahifour.enumTypes.StarType;
import com.ube.salinlahifour.model.UserLessonProgress;
import com.ube.salinlahifour.uibuilders.Button.BtnNextArrowStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.BtnStatesDirector;
import com.ube.salinlahifour.uibuilders.Button.EasyBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.HardBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.LogoutBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.MapNextBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.MediumBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.OkBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.PopupcloseBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.ProgressBtnStatesBuilder;

public class MapActivity extends Activity implements OnClickListener{
	private ArrayList<Scene> scenes;
	private ImageButton[] imgBtns;
	private TextView[] txtViews;
	private Scene scene;
	private ImageButton btn_progress;
	private ImageButton btn_logout;
	private ImageButton btn_account;
	
	private PopupWindow popupWindow;
	private View popupView;
	private MagicTextView tv_title;
	private TextView tv_desc;
	private ImageButton btn_popupclose;
	private ImageButton btn_easy;
	private ImageButton btn_medium;
	private ImageButton btn_hard;
	private ImageButton btn_nxtscene;
	private ImageView img_popupmap;
	private RelativeLayout parentView;
	
	private int UserID;
	private Intent intent = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    UserID = extras.getInt("UserID");
		}
		
		imgBtns = new ImageButton[5];
		txtViews = new TextView[5];

		//setContentView(R.layout.activity_map);
		
		if(((SalinlahiFour)getApplication()).getLoggedInUser() == null){
    		Intent intent = new Intent();
    		intent.setClass(getApplicationContext(), RegistrationActivityName.class);
    		startActivity(intent);
		}else{
	        Toast toast = Toast.makeText(getApplicationContext(), "Welcome " + ((SalinlahiFour)getApplication()).getLoggedInUser().getName() + "!!!", Toast.LENGTH_SHORT);
	        toast.show();
			
			try {
				parseXML();
				setLevelStatuses();
				
				
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			

			scene = scenes.get(0);
			setLayout();			
		}
	}
	
	private void setMapMenuButtons(){
		btn_account = new ImageButton(this);
		btn_logout = new ImageButton(this);
		btn_progress = new ImageButton(this);
		btn_nxtscene = new ImageButton(this);

		LayoutParams p_account = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
		ViewGroup.LayoutParams.WRAP_CONTENT);
			
		p_account.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		p_account.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		p_account.setMargins(10, 10, 0, 0);
		
		btn_account.setScaleX(0.8f);
		btn_account.setScaleY(0.8f);
		btn_account.setLayoutParams(p_account);
		btn_account.setBackgroundDrawable(null);
		btn_account.setOnClickListener(this);
	    
		LayoutParams p_logout = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
		ViewGroup.LayoutParams.WRAP_CONTENT);
			
		p_logout.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		p_logout.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		p_logout.setMargins(0, 18, 10, 0);

		btn_logout.setLayoutParams(p_logout);
		btn_logout.setBackgroundDrawable(null);
		btn_logout.setOnClickListener(this);
		btn_logout.setId(1);
		
		LayoutParams p_progress = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
		ViewGroup.LayoutParams.WRAP_CONTENT);
			
		p_progress.addRule(RelativeLayout.LEFT_OF, btn_logout.getId());
		p_progress.addRule(RelativeLayout.ALIGN_TOP, btn_logout.getId());
		p_progress.addRule(RelativeLayout.ALIGN_BASELINE, btn_logout.getId());

		btn_progress.setLayoutParams(p_progress);
		btn_progress.setBackgroundDrawable(null);
		btn_progress.setOnClickListener(this);
		
		LayoutParams p_nxtscene = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
		ViewGroup.LayoutParams.WRAP_CONTENT);
			
		p_nxtscene.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		p_nxtscene.addRule(RelativeLayout.CENTER_VERTICAL);
		
		btn_nxtscene.setLayoutParams(p_nxtscene);
		btn_nxtscene.setBackgroundDrawable(null);
		btn_nxtscene.setOnClickListener(this);

		parentView.addView(btn_account);
		parentView.addView(btn_logout);
		parentView.addView(btn_progress);
		parentView.addView(btn_nxtscene);
	}

	private void setLevelStatuses() {
		UserLessonProgressOperations userdb = new UserLessonProgressOperations(this);
		userdb.open();
		for(Scene scene : scenes){
			for(int i = 0; i < scene.getLessons().size(); i++){
				if(scene.getLessons().get(i).getLocked()){
					UserLessonProgress progress = userdb.getUserLessonProgress(UserID, scene.getLessons().get(i).getName());
					if(progress != null){
						scene.getLessons().get(i).setLocked(false);
						if(progress.getHardStar() != null){
							if(progress.getHardStar().equals(StarType.SILVER.toString()) || progress.getHardStar().equals(StarType.GOLD.toString()))
								if((i+1) < scene.getLessons().size())
								scene.getLessons().get(i+1).setLocked(false);
						}
					}else{
						//scene.getLessons().get(i).setLocked(true);
						scene.getLessons().get(i).setLocked(false);
					}
					try{
						scene.getLessons().get(0).setLocked(false);
					}catch(Exception e){
						
					}
				}
//				scene.getLessons().get(i).setLocked(false);
			}
		}
		userdb.close();
	}


	private void instantiateViews() {
		btn_logout.setImageDrawable(BtnStatesDirector.getImageDrawable(new LogoutBtnStatesBuilder()));
		btn_progress.setImageDrawable(BtnStatesDirector.getImageDrawable(new ProgressBtnStatesBuilder()));		
		btn_nxtscene.setImageDrawable(BtnStatesDirector.getImageDrawable(new MapNextBtnStatesBuilder()));
	}
	
	private void errorPopup(String title, String error){
		final AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder.setTitle(title);
		builder.setMessage(error);
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		builder.setPositiveButton("I'll debug it right away!", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				System.exit(0);
				
			}
			});
		builder.show();
	}

	public void instantiatePopupView(){
		LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);  
	    popupView = layoutInflater.inflate(R.layout.activity_difficulty, null);
	    
		popupWindow = new PopupWindow(popupView, LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);  
        popupWindow.setOutsideTouchable(false);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        
	    
		btn_easy = (ImageButton)popupView.findViewById(R.id.btn_easy);
		btn_medium = (ImageButton)popupView.findViewById(R.id.btn_med);
		btn_hard = (ImageButton)popupView.findViewById(R.id.btn_hard);
		btn_popupclose = (ImageButton)popupView.findViewById(R.id.btn_popupclose);
		img_popupmap = (ImageView)popupView.findViewById(R.id.popup_mapimg);

	    tv_desc = (TextView)popupView.findViewById(R.id.tv_description);
	    tv_desc.setTypeface(SalinlahiFour.getFontBpreplay());
	    tv_title = (MagicTextView)popupView.findViewById(R.id.tv_title);
		for(int i = 0; i < 30; i++)
			tv_title.addOuterShadow(5, 0, 0, 0xFF164366);
		tv_title.setTypeface(SalinlahiFour.getFontKgsecondchances());

		btn_popupclose.setImageDrawable(BtnStatesDirector.getImageDrawable(new PopupcloseBtnStatesBuilder()));
		btn_easy.setImageDrawable(BtnStatesDirector.getImageDrawable(new EasyBtnStatesBuilder()));
		btn_medium.setImageDrawable(BtnStatesDirector.getImageDrawable(new MediumBtnStatesBuilder()));
		btn_hard.setImageDrawable(BtnStatesDirector.getImageDrawable(new HardBtnStatesBuilder()));
        
	}
	

	public void navigateWidget(int choice){
		switch(choice){
		case 1: Log.d("debug", "register intent go!");
				intent = new Intent(this, RegistrationActivityName.class);
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
		String lessonDesc = "";
		String activityName = "";
		String value = "";
		int lessonImgID = 0;
		int lessonNumber = 0;
		
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
        		 lessonNumber++;
        		 Lesson lesson = new Lesson();
        		 lesson.setLocked(true);
        		 scene.addLesson(lesson.setValues(lessonName, lessonDesc, activityName, lessonImgID, lessonNumber));
        		 lessonName = "";
        		 lessonDesc = "";
        		 activityName = "";
        		 value = "";
        		 lessonImgID = 0;
        		 if(scene.getLessons().size() >= 5){
        			 scene = makeNewScene();
        		 }
        	 }else if(parser.getName().equals("Name")){
        		 lessonName = value;
        	 }else if(parser.getName().equals("Image")){
        		 lessonImgID = getResources().getIdentifier(value, "drawable", getPackageName());
        	 }else if(parser.getName().equals("ActivityName")){
        		 activityName = value;
        	 }else if(parser.getName().equals("Description")){
        		 lessonDesc = value;
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
		case 1:
			scenes.add(new Scene(null, R.layout.scene_layout_2));
			break;
		default:
			scenes.add(new Scene(null, R.layout.scene_layout_1));
			break;
		}
		return scenes.get(scenes.size()-1);
	}
	
	private void setLayout(){
		setContentView(scene.getLayoutID());

		try{
			imgBtns[0] = (ImageButton)findViewById(R.id.img_lesson1);
			imgBtns[1] = (ImageButton)findViewById(R.id.img_lesson2);
			imgBtns[2] = (ImageButton)findViewById(R.id.img_lesson3);
			imgBtns[3] = (ImageButton)findViewById(R.id.img_lesson4);
			imgBtns[4] = (ImageButton)findViewById(R.id.img_lesson5);

			for(ImageButton img : imgBtns)
				img.setImageDrawable(null);
			
		}catch(NullPointerException e){
			errorPopup("View not found", e.getMessage() + "\nCheck if each scene contains views with ids img_lesson1, img_lesson2, img_lesson3, img_lesson4, and img_lesson5.");
		}
		
		parentView = (RelativeLayout)findViewById(R.id.parent_view);
		float scale = getResources().getDisplayMetrics().density;
		int paddingDp = (int) (7*scale + 0.5f);
		
		Log.d("Scene:" + scenes.indexOf(scene) + " Lesson size:" + scene.getLessons().size(), "TEST");
		
		for(int i = 0; i < scene.getLessons().size(); i++){
			imgBtns[i].setVisibility(View.VISIBLE);
			if(!scene.getLessons().get(i).getLocked()){
				imgBtns[i].setImageResource(scene.getLessons().get(i).getImage());
				imgBtns[i].setTag(i);
				imgBtns[i].setOnClickListener(this);
				
				LayoutParams p = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
				        ViewGroup.LayoutParams.WRAP_CONTENT);
	
				p.addRule(RelativeLayout.BELOW, imgBtns[i].getId());
				p.addRule(RelativeLayout.ALIGN_LEFT, imgBtns[i].getId());
				p.addRule(RelativeLayout.ALIGN_RIGHT, imgBtns[i].getId());
				
				txtViews[i] = new TextView(this);
				txtViews[i].setText(scene.getLessons().get(i).getName());
				txtViews[i].setTextAppearance(getApplicationContext(), R.style.mapLabel);
				txtViews[i].setTypeface(SalinlahiFour.getFontPlaytime());
				txtViews[i].setGravity(Gravity.CENTER_HORIZONTAL);
			
				txtViews[i].setLayoutParams(p);
				txtViews[i].setBackgroundResource(R.drawable.map_labels);
				
				parentView.addView(txtViews[i]);
			}else{
				imgBtns[i].setImageResource(R.drawable.lesson0_icon);
			}
		}
		setMapMenuButtons();
		instantiateViews();

		if(SalinlahiFour.getLoggedInUser().getGender().equals("female")){
			btn_account.setBackgroundResource(R.drawable.map_hud_pepay_talking);
		}else{
			btn_account.setBackgroundResource(R.drawable.map_hud_popoi_talking);
		}
	}

	@Override
	public void onClick(View view) {
		int index = -1;
		
//		SoundPoolManager.CreateInstance();
//		List<Integer> sounds = new ArrayList<Integer>();
//		sounds.add(R.raw.family_ate);
//		SoundPoolManager.getInstance().setSounds(sounds);
//		try {
//		    SoundPoolManager.getInstance().InitializeSoundPool(this, new ISoundPoolLoaded() {
//		        @Override
//		        public void onSuccess() {
//		        	SoundPoolManager.getInstance().playSound(R.raw.family_ate);
//		        }
//		    });
//		} catch (Exception e) {
//		    e.printStackTrace();
//		}
//		
//		
		if(view == btn_account){
			
<<<<<<< HEAD
				    final Lesson lessonDetails = scene.getLessons().get(Integer.parseInt(((ImageButton)view).getTag().toString()));				
				    tv_title.setText(lessonDetails.getName());
				    tv_desc.setText(lessonDetails.getDescription());
				    img_popupmap.setImageResource(lessonDetails.getImage());
				    
				    Log.d("Pressed a lesson", "TEST");
				    
				             ImageButton easy = (ImageButton)popupView.findViewById(R.id.btn_easy);
				             easy.setOnClickListener(new ImageButton.OnClickListener(){
				        	     @Override
				        	     public void onClick(View v) {
				        	    	intent = new Intent(scene.getLessons().get(fIndex).getTutorial());
				         			intent.putExtra("activityClass", scene.getLessons().get(fIndex).getActivity());
				         			intent.putExtra("activityLevel", LevelType.EASY.toString());
				         			Bundle bundle = new Bundle();
				         			bundle.putParcelable("lesson", lessonDetails);
				         			intent.putExtras(bundle);
				         			startActivity(intent);
				        	     }});
				             ImageButton medium = (ImageButton)popupView.findViewById(R.id.btn_med);
				             medium.setOnClickListener(new ImageButton.OnClickListener(){

				        	     @Override
				        	     public void onClick(View v) {
				        	    	 intent = new Intent(scene.getLessons().get(fIndex).getTutorial());
				         			intent.putExtra("activityClass", scene.getLessons().get(fIndex).getActivity());
				         			intent.putExtra("activityLevel", LevelType.MEDIUM.toString());
				         			Bundle bundle = new Bundle();
				         			bundle.putParcelable("lesson", lessonDetails);
				         			intent.putExtras(bundle);
				         			startActivity(intent);
				        	     }});
				             ImageButton hard = (ImageButton)popupView.findViewById(R.id.btn_hard);
				             hard.setOnClickListener(new ImageButton.OnClickListener(){


				        	     @Override
				        	     public void onClick(View v) {
				        	    	 intent = new Intent(scene.getLessons().get(fIndex).getTutorial());
				         			intent.putExtra("activityClass", scene.getLessons().get(fIndex).getActivity());
				         			intent.putExtra("activityLevel", LevelType.HARD.toString());
				         			Bundle bundle = new Bundle();
				         			bundle.putParcelable("lesson", lessonDetails);
				         			intent.putExtras(bundle);
				         			startActivity(intent);
				        	     }});
				             
				             UserLessonProgressOperations userdb = new UserLessonProgressOperations(this);
				             userdb.open();
				             UserLessonProgress lesson = userdb.getUserLessonProgress(SalinlahiFour.getLoggedInUser().getId(), lessonDetails.getName());
				             
				             if(lesson == null){
				            	 lesson = new UserLessonProgress();
				            	 lesson.setEasyStar(null);
				             }
					             if(lesson.getEasyStar() != null){
					            	 switch(lesson.getEasyStar()){
					            	 	case "GOLD":
					            	 		((ImageView)popupView.findViewById(R.id.star1)).setImageResource(R.drawable.lvlselect_gold);
					            	 		break;
					            	 	case "SILVER":
					            	 		((ImageView)popupView.findViewById(R.id.star1)).setImageResource(R.drawable.lvlselect_silver);
					            	 		break;
					            	 	case "BRONZE":
					            	 		((ImageView)popupView.findViewById(R.id.star1)).setImageResource(R.drawable.lvlselect_bronze);
					            	 		btn_medium.setEnabled(true);
					            	 		btn_hard.setEnabled(true);
					            	 		break;
					            	 	default:
					            	 		lesson.setEasyStar(null);
					            	 		btn_medium.setEnabled(true);
					            	 		btn_hard.setEnabled(true);
					            	 }
					             }else{
				            	 	((ImageView)popupView.findViewById(R.id.star1)).setImageResource(R.drawable.lvlselect_null);
			            	 		btn_medium.setEnabled(true);
			            	 		btn_hard.setEnabled(true);
					             }
					             if(lesson.getMediumStar() != null){
					            	 switch(lesson.getMediumStar()){
					            	 	case "GOLD":
					            	 		((ImageView)popupView.findViewById(R.id.star2)).setImageResource(R.drawable.lvlselect_gold);
					            	 		break;
					            	 	case "SILVER":
					            	 		((ImageView)popupView.findViewById(R.id.star2)).setImageResource(R.drawable.lvlselect_silver);
					            	 		break;
					            	 	case "BRONZE":
					            	 		((ImageView)popupView.findViewById(R.id.star2)).setImageResource(R.drawable.lvlselect_bronze);
					            	 		btn_hard.setEnabled(true);
					            	 		break;
					            	 	default:
					            	 		lesson.setMediumStar(null);
					            	 }
					             }else{
				            	 	((ImageView)popupView.findViewById(R.id.star2)).setImageResource(R.drawable.lvlselect_null);
			            	 		btn_hard.setEnabled(true);
					             }
					             if(lesson.getHardStar() != null){
					            	 switch(lesson.getHardStar()){
					            	 	case "GOLD":
					            	 		((ImageView)popupView.findViewById(R.id.star3)).setImageResource(R.drawable.lvlselect_gold);
					            	 		break;
					            	 	case "SILVER":
					            	 		((ImageView)popupView.findViewById(R.id.star3)).setImageResource(R.drawable.lvlselect_silver);
					            	 		break;
					            	 	case "BRONZE":
					            	 		((ImageView)popupView.findViewById(R.id.star3)).setImageResource(R.drawable.lvlselect_bronze);
					            	 		break;
					            	 	default:
					            	 		lesson.setHardStar(null);
					            	 }
					             }else{
				            	 	((ImageView)popupView.findViewById(R.id.star3)).setImageResource(R.drawable.lvlselect_null);
=======
		}else if(view == btn_logout){
			intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
		}else if(view == btn_progress){
			int numLesson = 0;
			for(Scene scene : scenes){
				numLesson += scene.getLessons().size();
			}
			intent = new Intent(this, ProgressTreeActivity.class);
			Bundle bundle = new Bundle();
			ArrayList<Lesson> lessons = new ArrayList();
			for(Scene scene : scenes)
				for(Lesson lesson : scene.getLessons())
					lessons.add(lesson);
			bundle.putParcelableArrayList("lessons", lessons);
			intent.putExtras(bundle);
			startActivity(intent);
		}else if(view == btn_nxtscene){
			scene = scenes.get(1);
			setLayout();
		}else {
			
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
					intent = new Intent(this, RegistrationActivityName.class);
					startActivity(intent);
					break;
				case R.id.btn_popupclose:
					popupWindow.dismiss();
					break;
			}
			
			
			final int fIndex = index;
			
			if(index != -1){ //If lesson is pressed, continue
	//		//	selectLevelPopup(index);
	//			imgBtns[index].setOnClickListener(new ImageButton.OnClickListener(){
	//				
	//				   @Override
	//				   public void onClick(View view) {
	
						instantiatePopupView();
				
					    final Lesson lessonDetails = scene.getLessons().get(Integer.parseInt(((ImageButton)view).getTag().toString()));				
					    tv_title.setText(lessonDetails.getName());
					    tv_desc.setText(lessonDetails.getDescription());
					    img_popupmap.setImageResource(lessonDetails.getImage());
					    
					    Log.d("Pressed a lesson", "TEST");
					    
					             ImageButton easy = (ImageButton)popupView.findViewById(R.id.btn_easy);
					             easy.setOnClickListener(new ImageButton.OnClickListener(){
					        	     @Override
					        	     public void onClick(View v) {
					        	    	intent = new Intent(scene.getLessons().get(fIndex).getTutorial());
					         			intent.putExtra("activityClass", scene.getLessons().get(fIndex).getActivity());
					         			intent.putExtra("activityLevel", LevelType.EASY.toString());
					         			Bundle bundle = new Bundle();
					         			bundle.putParcelable("lesson", lessonDetails);
					         			intent.putExtras(bundle);
					         			startActivity(intent);
					        	     }});
					             ImageButton medium = (ImageButton)popupView.findViewById(R.id.btn_med);
					             medium.setOnClickListener(new ImageButton.OnClickListener(){
	
					        	     @Override
					        	     public void onClick(View v) {
					        	    	 intent = new Intent(scene.getLessons().get(fIndex).getTutorial());
					         			intent.putExtra("activityClass", scene.getLessons().get(fIndex).getActivity());
					         			intent.putExtra("activityLevel", LevelType.MEDIUM.toString());
					         			Bundle bundle = new Bundle();
					         			bundle.putParcelable("lesson", lessonDetails);
					         			intent.putExtras(bundle);
					         			startActivity(intent);
					        	     }});
					             ImageButton hard = (ImageButton)popupView.findViewById(R.id.btn_hard);
					             hard.setOnClickListener(new ImageButton.OnClickListener(){
	
	
					        	     @Override
					        	     public void onClick(View v) {
					        	    	 intent = new Intent(scene.getLessons().get(fIndex).getTutorial());
					         			intent.putExtra("activityClass", scene.getLessons().get(fIndex).getActivity());
					         			intent.putExtra("activityLevel", LevelType.HARD.toString());
					         			Bundle bundle = new Bundle();
					         			bundle.putParcelable("lesson", lessonDetails);
					         			intent.putExtras(bundle);
					         			startActivity(intent);
					        	     }});
					             
					             UserLessonProgressOperations userdb = new UserLessonProgressOperations(this);
					             userdb.open();
					             UserLessonProgress lesson = userdb.getUserLessonProgress(SalinlahiFour.getLoggedInUser().getId(), lessonDetails.getName());
					             
					             if(lesson == null){
					            	 lesson = new UserLessonProgress();
					            	 lesson.setEasyStar(null);
>>>>>>> Updated Assets & Map Fixes
					             }
						             if(lesson.getEasyStar() != null){
						            	 switch(lesson.getEasyStar()){
						            	 	case "GOLD":
						            	 		((ImageView)popupView.findViewById(R.id.star1)).setImageResource(R.drawable.lvlselect_gold);
						            	 		break;
						            	 	case "SILVER":
						            	 		((ImageView)popupView.findViewById(R.id.star1)).setImageResource(R.drawable.lvlselect_silver);
						            	 		break;
						            	 	case "BRONZE":
						            	 		((ImageView)popupView.findViewById(R.id.star1)).setImageResource(R.drawable.lvlselect_bronze);
						            	 		btn_medium.setEnabled(false);
						            	 		btn_hard.setEnabled(false);
						            	 		break;
						            	 	default:
						            	 		lesson.setEasyStar(null);
						            	 		btn_medium.setEnabled(false);
						            	 		btn_hard.setEnabled(false);
						            	 }
						             }else{
					            	 	((ImageView)popupView.findViewById(R.id.star1)).setImageResource(R.drawable.lvlselect_null);
				            	 		btn_medium.setEnabled(false);
				            	 		btn_hard.setEnabled(false);
						             }
						             if(lesson.getMediumStar() != null){
						            	 switch(lesson.getMediumStar()){
						            	 	case "GOLD":
						            	 		((ImageView)popupView.findViewById(R.id.star2)).setImageResource(R.drawable.lvlselect_gold);
						            	 		break;
						            	 	case "SILVER":
						            	 		((ImageView)popupView.findViewById(R.id.star2)).setImageResource(R.drawable.lvlselect_silver);
						            	 		break;
						            	 	case "BRONZE":
						            	 		((ImageView)popupView.findViewById(R.id.star2)).setImageResource(R.drawable.lvlselect_bronze);
						            	 		btn_hard.setEnabled(false);
						            	 		break;
						            	 	default:
						            	 		lesson.setMediumStar(null);
						            	 }
						             }else{
					            	 	((ImageView)popupView.findViewById(R.id.star2)).setImageResource(R.drawable.lvlselect_null);
				            	 		btn_hard.setEnabled(false);
						             }
						             if(lesson.getHardStar() != null){
						            	 switch(lesson.getHardStar()){
						            	 	case "GOLD":
						            	 		((ImageView)popupView.findViewById(R.id.star3)).setImageResource(R.drawable.lvlselect_gold);
						            	 		break;
						            	 	case "SILVER":
						            	 		((ImageView)popupView.findViewById(R.id.star3)).setImageResource(R.drawable.lvlselect_silver);
						            	 		break;
						            	 	case "BRONZE":
						            	 		((ImageView)popupView.findViewById(R.id.star3)).setImageResource(R.drawable.lvlselect_bronze);
						            	 		break;
						            	 	default:
						            	 		lesson.setHardStar(null);
						            	 }
						             }else{
					            	 	((ImageView)popupView.findViewById(R.id.star3)).setImageResource(R.drawable.lvlselect_null);
						             }
					             
					             userdb.close();
					            popupWindow.showAsDropDown(popupView);
				}	
			}
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


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		SalinlahiFour.getBgm().start();
	}


	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		SalinlahiFour.getBgm().pause();
	}
}