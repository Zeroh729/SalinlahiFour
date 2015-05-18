package com.ube.salinlahifour;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Html;
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

import com.qwerjk.better_text.MagicTextView;
import com.ube.salinlahifour.database.UserLessonProgressOperations;
import com.ube.salinlahifour.debugclasses.DebugUserModuleActivity;
import com.ube.salinlahifour.enumTypes.LevelType;
import com.ube.salinlahifour.enumTypes.StarType;
import com.ube.salinlahifour.model.UserLessonProgress;
import com.ube.salinlahifour.tutorials.Tutorial;
import com.ube.salinlahifour.uibuilders.Button.BtnStatesDirector;
import com.ube.salinlahifour.uibuilders.Button.EasyBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.HardBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.LogoutBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.MapNextBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.MapPrevBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.MediumBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.PopupcloseBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.ProgressBtnStatesBuilder;

public class MapActivity extends Activity implements OnClickListener{
	private Context context;

	private ArrayList<Scene> scenes;
	private ImageButton[] imgBtns;
	private TextView[] txtViews;
	private Scene scene;
	private ImageButton btn_progress;
	private ImageButton btn_logout;
	private ImageButton btn_account;
	private TextView tv_friendTalk;
	
	private PopupWindow popupWindow;
	private View popupView;
	private MagicTextView tv_title;
	private TextView tv_desc;
	private ImageButton btn_popupclose;
	private ImageButton btn_easy;
	private ImageButton btn_medium;
	private ImageButton btn_hard;
	private ImageButton btn_nxtscene;
	private ImageButton btn_prevscene;
	private ImageView img_popupmap;
	private RelativeLayout parentView;
	
	private int UserID;
	private int sceneIndex;
	private Intent intent = null;
	
	private String[] greetings;
	private int greetingIndex = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		
		Log.d("ONCREATING", "TESTTESTTESTEST");
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    UserID = extras.getInt("UserID");
		}else{
			UserID = SalinlahiFour.getLoggedInUser().getId();
		}
		
		imgBtns = new ImageButton[5];
		txtViews = new TextView[5];
		greetings = new String[]{"<i>Magandang Araw</i> " + SalinlahiFour.getLoggedInUser().getName()+"!<br><font color=#8C8C8C>Good day " + SalinlahiFour.getLoggedInUser().getName() + "!</font>",
				"<i>Kamusta na?</i><br><font color=#8C8C8C>How are you?</font>",
				"Haha! That tickles!",
				"I want to eat already!",
				"Tara!<br><font color=#8C8C8C>Let's go!</font>"};

		//setContentView(R.layout.activity_map);
		
		if(SalinlahiFour.getLoggedInUser() == null){
    		Intent intent = new Intent();
    		intent.setClass(getApplicationContext(), RegistrationActivityName.class);
    		startActivity(intent);
		}else{
	        Toast toast = Toast.makeText(getApplicationContext(), "Welcome " + ((SalinlahiFour)getApplication()).getLoggedInUser().getName() + "!!!", Toast.LENGTH_SHORT);
	        toast.show();
			
	        

//			try {
//				Log.d("parseXML()", "TESTTESTTESTEST");
				parseXML();
//				Log.d("settingLevelStatuses", "TESTTESTTESTEST");
				setLevelStatuses();
//				
//				
//			} catch (XmlPullParserException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
			
			sceneIndex = 0;
			scene = scenes.get(sceneIndex);
			setLayout();			
		}
		
	}
	
	private void setMapMenuButtons(){
		btn_account = new ImageButton(this);
		btn_logout = new ImageButton(this);
		btn_progress = new ImageButton(this);
		btn_nxtscene = new ImageButton(this);
		btn_prevscene = new ImageButton(this);
		tv_friendTalk = new TextView(this);

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
		btn_account.setId(2);
		

		LayoutParams p_friendtalk = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
		ViewGroup.LayoutParams.WRAP_CONTENT);
		
		p_friendtalk.addRule(RelativeLayout.RIGHT_OF, btn_account.getId());
		p_friendtalk.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		p_friendtalk.setMargins(10, 10, 0, 0);
		
		
		tv_friendTalk.setLayoutParams(p_friendtalk);
		tv_friendTalk.setBackgroundResource(R.drawable.dialog);
		tv_friendTalk.setTextColor(Color.argb(255, 37, 37, 37));
		tv_friendTalk.setPadding(20, 20, 20, 20);
		tv_friendTalk.setTextSize(19);
	    
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
		
		LayoutParams p_prevscene = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
		ViewGroup.LayoutParams.WRAP_CONTENT);
			
		p_prevscene.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		p_prevscene.addRule(RelativeLayout.CENTER_VERTICAL);
		
		btn_prevscene.setLayoutParams(p_prevscene);
		btn_prevscene.setBackgroundDrawable(null);
		btn_prevscene.setOnClickListener(this);		
		
		tv_friendTalk.setText(Html.fromHtml(greetings[greetingIndex]));

		parentView.addView(btn_account);
		parentView.addView(btn_logout);
		parentView.addView(btn_progress);
		parentView.addView(btn_nxtscene);
		parentView.addView(btn_prevscene);
		parentView.addView(tv_friendTalk);
	}
	
	private int getLessonCount(){
		int total = 0;
		for(int i = 0; i < scenes.size(); i++){
			for(int j = 0; j < scenes.get(i).getLessons().size(); j++){
				total++;
			}
		}
		return total;
	}

	private void setLevelStatuses() {
		UserLessonProgressOperations userdb = new UserLessonProgressOperations(this);
		userdb.open();
		Log.d("scene count: " + scenes.size(), "FINAL CHECKING");
		for(int k = 0; k < scenes.size(); k++){
			for(int i = 0; i < scenes.get(k).getLessons().size(); i++){
					UserLessonProgress progress = userdb.getUserLessonProgress(UserID, scenes.get(k).getLessons().get(i).getName());
					if(progress != null){
						scenes.get(k).getLessons().get(i).setLocked(false);
						Log.d("THERE", "FINAL CHECK");
						if(progress.getHardStar() != null){
							Log.d("THEREEEE", "FINAL CHECK");
							if(!progress.getHardStar().equals(StarType.BRONZE.toString())){
								Log.d("ALMOST THEREEEE", "FINAL CHECK");
								if((i+1) < scenes.get(k).getLessons().size()){
									scenes.get(k).getLessons().get(i+1).setLocked(false);
									Log.d("UNLOCKING: " + scenes.get(k).getLessons().get(i+1).getName(), "FINAL CHECK");
								}
							}
						}

//					if(!scene.getLessons().get(i).getLocked()){
//						scene.getLessons().get(i).setLocked(true);
////						scene.getLessons().get(i).setLocked(false);
//					}
				}

				Log.d("Lesson no. : " + i + " ->" + scenes.get(k).getLessons().get(i).getLocked(),"FINAL CHECKING");
				
				//scene.getLessons().get(i).setLocked(false);
				if(k > 0 && i == 0){
					UserLessonProgress prevCheck = userdb.getUserLessonProgress(UserID, scenes.get(k-1).getLessons().get(4).getName());
					try{
						if(!prevCheck.getHardStar().equals(StarType.BRONZE.toString())){
							scenes.get(k).getLessons().get(i).setLocked(false);
						}
					}catch(NullPointerException e){}
				}
			}
			
		}
		

		try{
			scenes.get(0).getLessons().get(0).setLocked(false);
		}catch(Exception e){
			Toast.makeText(this, "Can't unlock", Toast.LENGTH_SHORT).show();
		}
		
		userdb.close();
	}


	private void instantiateViews() {
		btn_logout.setImageDrawable(BtnStatesDirector.getImageDrawable(new LogoutBtnStatesBuilder()));
		btn_progress.setImageDrawable(BtnStatesDirector.getImageDrawable(new ProgressBtnStatesBuilder()));		
		btn_nxtscene.setImageDrawable(BtnStatesDirector.getImageDrawable(new MapNextBtnStatesBuilder()));
		btn_prevscene.setImageDrawable(BtnStatesDirector.getImageDrawable(new MapPrevBtnStatesBuilder()));
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
		
	
	public void parseXML(){
		scenes = new ArrayList();
		scene = makeNewScene();
		
		for(int i = 0; i < SalinlahiFour.getLessonsList().size(); i++){
			Lesson lesson = SalinlahiFour.getLessonsList().get(i);
			lesson.setLocked(true);
			scene.addLesson(lesson);
			if(scene.getLessons().size() > 5){
   			 scene = makeNewScene();
			}
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

		btn_prevscene.setVisibility(View.VISIBLE);
		btn_nxtscene.setVisibility(View.VISIBLE);
		Log.d("sceneIndex: " + sceneIndex, "TEST");
		if(sceneIndex == 0){
			btn_prevscene.setVisibility(View.INVISIBLE);
		}
		if(sceneIndex >= scenes.size()-1){
			btn_nxtscene.setVisibility(View.INVISIBLE);
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
			if((greetingIndex+1) >= greetings.length){
				greetingIndex = 0;
			}else{
				greetingIndex++;
			}
			tv_friendTalk.setText(Html.fromHtml(greetings[greetingIndex]));
			
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
			sceneIndex++;
			scene = scenes.get(sceneIndex);
			setLayout();
		}else if(view == btn_prevscene){
			sceneIndex--;
			scene = scenes.get(sceneIndex);
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
					        	    	intent = new Intent(context, Tutorial.class);
					         			intent.putExtra("lessonName", scene.getLessons().get(fIndex).getName());
					         			intent.putExtra("activityLevel", LevelType.EASY.toString());

					         			startActivity(intent);
					        	     }});
					             ImageButton medium = (ImageButton)popupView.findViewById(R.id.btn_med);
					             medium.setOnClickListener(new ImageButton.OnClickListener(){
	
					        	     @Override
					        	     public void onClick(View v) {
					        	    	intent = new Intent(context, Tutorial.class);
					         			intent.putExtra("lessonName", scene.getLessons().get(fIndex).getName());
					         			intent.putExtra("activityLevel", LevelType.MEDIUM.toString());
					         			startActivity(intent);
					        	     }});
					             ImageButton hard = (ImageButton)popupView.findViewById(R.id.btn_hard);
					             hard.setOnClickListener(new ImageButton.OnClickListener(){
	
	
					        	     @Override
					        	     public void onClick(View v) {
					        	    	intent = new Intent(context, Tutorial.class);
					         			intent.putExtra("lessonName", scene.getLessons().get(fIndex).getName());
					         			intent.putExtra("activityLevel", LevelType.HARD.toString());
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
						            	 		btn_medium.setEnabled(false);
						            	 		btn_hard.setEnabled(false);
						            	 		break;
						            	 	default:
						            	 		lesson.setEasyStar(null);
						            	 		btn_medium.setEnabled(false);
						            	 		btn_hard.setEnabled(false);
						            	 }
						             }else{
//					            	 	((ImageView)popupView.findViewById(R.id.star1)).setImageResource(R.drawable.lvlselect_null);
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
//					            	 	((ImageView)popupView.findViewById(R.id.star2)).setImageResource(R.drawable.lvlselect_null);
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
//					            	 	((ImageView)popupView.findViewById(R.id.star3)).setImageResource(R.drawable.lvlselect_null);
						             }
					             
						             if(!btn_medium.isEnabled()){
						            	 ((ImageView)popupView.findViewById(R.id.star2)).setImageResource(R.drawable.lvlselect_null);
						             }
						             if(!btn_hard.isEnabled()){
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
				+"1. " + scene.getLessons().get(index).getActivity() + " exists\n"
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

		Log.d("onResume","MapActivity TEST");

	}


	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		
		Log.d("onRestart","MapActivity TEST");

    	finish();
    	Intent intent = new Intent(this, MapActivity.class);
    	startActivity(intent);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		SalinlahiFour.getBgm().pause();
	}
}