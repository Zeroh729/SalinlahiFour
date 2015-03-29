package com.ube.salinlahifour.lessonActivities;

import iFeedback.iFeedback;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.jdom.Parent;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.ube.salinlahifour.Item;
import com.ube.salinlahifour.Lesson;
import com.ube.salinlahifour.MapActivity;
import com.ube.salinlahifour.R;
import com.ube.salinlahifour.ReportCard;
import com.ube.salinlahifour.SalinlahiFour;
import com.ube.salinlahifour.database.UserRecordOperations;
import com.ube.salinlahifour.enumTypes.LevelType;
import com.ube.salinlahifour.enumTypes.StatusType;
import com.ube.salinlahifour.evaluationModule.Evaluation;
import com.ube.salinlahifour.model.UserRecord;
import com.ube.salinlahifour.tools.DateTimeConverter;
import com.ube.salinlahifour.uibuilders.Button.BtnStatesDirector;
import com.ube.salinlahifour.uibuilders.Button.NoBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.PauseBtnStatesDirector;
import com.ube.salinlahifour.uibuilders.Button.YesBtnStatesBuilder;

public abstract class AbstractLessonActivity extends Activity {
	protected Context context;
	protected Lesson lesson;
	protected ArrayList<ImageView> backgrounds;
	protected ArrayList<Item> items;
	protected ArrayList<Item> questions;
	protected ArrayList<SoundPool> timeoutvoices;
	protected String activityName;
	protected LevelType activityLevel;
	protected int layoutID;
	protected int UserID;	
	protected int cnt_question;
	protected iFeedback NLG;
	protected ReportCard reportCard;
	protected Evaluation evaluation;
	protected GamePausePopup gamePause;
	private ImageButton pauseBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
//		setContentView(R.layout.activity_lesson);
		try{
			setContentView(layoutID);		
		}catch(Exception e){
			errorPopup("Layout ID not found", "Check if:\n"
					+ "1. XML layout for this activity exists.\n"
					+ "2. layoutID has been set in Constructor");
		}
		
		Bundle bundle = getIntent().getExtras();
		activityName = bundle.getString("activityName");
		activityLevel = LevelType.valueOf(bundle.getString("activityLevel"));
		Log.d("activityLevel:", activityLevel.toString());
		lesson = (Lesson) bundle.getParcelable("lesson");
		
		UserID = SalinlahiFour.getLoggedInUser().getId();
		Log.d(activityName, "TEST ActivityName in lesson act");

		items = ((SalinlahiFour)getApplication()).getLessonItems();
		initiateNarrationModule();

		evaluation =  new Evaluation(this, activityName, activityLevel.toString());
		cnt_question = 0;

		initiateGamePauseUI();
		initiateViews();
		getQuestions();
		run();
	}
	
	private void initiateGamePauseUI(){
		gamePause = new GamePausePopup(this);
		
		pauseBtn = new ImageButton(this);		
		LayoutParams p = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		p.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		p.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		
		pauseBtn.setLayoutParams(p);
		pauseBtn.setBackgroundDrawable(BtnStatesDirector.getImageDrawable(new PauseBtnStatesDirector()));
		pauseBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d("CLICKING POPUP", "TEST");
				gamePause = new GamePausePopup(context);
				gamePause.showAsDropDown(gamePause.popupView);
			}
		});
	}
	
	protected ImageButton getPauseButton(){
		return pauseBtn;
	}
	
	protected void getQuestions(){
		questions = new ArrayList<Item>();
		Log.d("TESTINGLessonActivity", "Aldrin: getting Questions");
		String lastDate = DateTimeConverter.getDateLastMonth();
		UserRecordOperations userdb = new UserRecordOperations(this);
		userdb.open();		
		ArrayList<UserRecord> records;

		try {
			records = userdb.getRecentUserRecordsFromUserId(SalinlahiFour.getLoggedInUser().getId(), activityName);
			int cnt_itemLevel = 0;
			
			ArrayList<Item> items = SalinlahiFour.getLessonItems();
	//		ArrayList<String> itemNames = new ArrayList();
	//		ArrayList<Integer> itemScores = new ArrayList();
			HashMap<String, Integer> itemKeys = new HashMap();
			
			for(int i = 0; i < items.size(); i++){
	//			itemNames.add(items.get(i).getWord());
	//			itemScores.add(0);
				if(!items.get(i).getLevel().equals(activityLevel)){
					itemKeys.put(items.get(i).getWord(), 0);
				}else{
					cnt_itemLevel++;
					questions.add(items.get(i));
				}
			}
			
            Log.d("records size: " + records.size(), "TEST");
            Log.d("itemKeys size: " + itemKeys.size(), "TEST");
					
			for(int i = 0; i < records.size(); i++){
	//			int index = itemNames.indexOf(records.get(i).getCorrectAnswer());
				if(itemKeys.containsKey(records.get(i).getCorrectAnswer())){
					int value = itemKeys.get(records.get(i).getCorrectAnswer());
					if(records.get(i).getStatus().equals(StatusType.CORRECT.toString())){
						value += 1;
					}else{
						value -= 1;
					}
					itemKeys.put(records.get(i).getCorrectAnswer(), value);
				}
			}		
			
			Map<String, Integer> sortedItemKeys = sortByComparator(itemKeys);
			
			int i = 0;
			for(String key : sortedItemKeys.keySet()){
				if(cnt_question == 0 || ((cnt_question - cnt_itemLevel) > i)){
					ArrayList<Item> lessonItems = SalinlahiFour.getLessonItems();
					for(int j = 0; j < lessonItems.size(); j++)
						if(lessonItems.get(j).getWord().equals(key)){
	//						if(lessonItems.get(j).getLevel().equals(activityLevel))
								questions.add(lessonItems.get(j));
	//						else if(cnt_question > 0 && cnt_question < j){
	//							
	//						}
							break;
						}
				}else{
					break;
				}
				i++;
			}
			Collections.shuffle(questions,  new Random(System.nanoTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	private static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap) {
		 
		// Convert Map to List
		List<Map.Entry<String, Integer>> list = 
			new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());
 
		// Sort list with comparator, to compare the Map values
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1,
                                           Map.Entry<String, Integer> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});
 
		// Convert sorted map back to a Map
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext();) {
			Map.Entry<String, Integer> entry = it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}
	
	protected void initiateNarrationModule(){
		Log.d("TESTINGLessonActivity", "Aldrin: Initiating iFeedback..");
		NLG = new iFeedback();
		Log.d("TESTINGLessonActivity", "Aldrin: Reading iFeedback properties");
		NLG.readProperties();
		Log.d("TESTINGLessonActivity", "Aldrin: iFeedback Initiated");
		Log.d("TESTINGLessonActivity", "Aldrin: iFeedback LOL");

	}
	protected void end_report(int choice){//THIS IS FOR TRANSFERRING TO OTHER ACTIVITIES
	    	
	    	switch(choice){
	    	case 1:
	    		Intent intent = new Intent(this, MapActivity.class);
			startActivity(intent); break;
	    	case 2:
	    		Intent intent1 = new Intent(activityName);
	    		intent1.putExtra("activityClass", activityName);
	    		intent1.putExtra("activityLevel", activityLevel);
				startActivity(intent1);break;
	    	}
	    	
	}
	
	protected void showReportCard(Context context){
		reportCard = new ReportCard(context, lesson, activityLevel, evaluation, evaluation.getEndofActivityFeedback(evaluation.getScore(), lesson.getLessonNumber()));
		reportCard.reveal();
	}

	
	abstract protected void initiateViews();
	abstract protected void run();
	abstract protected boolean checkAnswer(String answer);
	
	private void errorPopup(String title, String error){
		final AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder.setTitle(title);
		builder.setMessage(error);
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		builder.setPositiveButton("I'll debug it right away!", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				System.exit(0);
			}
			});
		builder.show();
	}
	
	protected void setCntQuestions(int x){
		cnt_question = x;
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
	
	protected class GamePausePopup extends PopupWindow implements android.view.View.OnClickListener{
		private Context context;
		private ImageButton btn_yes;
		private ImageButton btn_no;

		private View popupView;
		
		public GamePausePopup(Context context){
			super(context);
			this.context = context;
			LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);  
		    popupView = layoutInflater.inflate(R.layout.gamepause, null);        
		    setOutsideTouchable(false);
	        setBackgroundDrawable(new BitmapDrawable());
		    
	        setContentView(popupView);
		    setHeight(LayoutParams.MATCH_PARENT);
		    setWidth(LayoutParams.MATCH_PARENT);

		    btn_yes = (ImageButton) popupView.findViewById(R.id.btn_yes);
		    btn_no = (ImageButton) popupView.findViewById(R.id.btn_no);
		    
		    btn_yes.setImageDrawable(BtnStatesDirector.getImageDrawable(new YesBtnStatesBuilder()));
		    btn_no.setImageDrawable(BtnStatesDirector.getImageDrawable(new NoBtnStatesBuilder()));
		    
		    btn_yes.setOnClickListener(this);
		    btn_no.setOnClickListener(this);
		}

		@Override
		public void onClick(View v) {
				switch(v.getId()){
					case R.id.btn_yes:
						startActivity(new Intent(context, MapActivity.class));
						break;
					case R.id.btn_no:
						dismiss();
						break;
				}
		}
		
	}
}
