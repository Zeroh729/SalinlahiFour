package com.ube.salinlahifour.lessonActivities;

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

import iFeedback.iFeedback;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.kilobolt.framework.Screen;
import com.kilobolt.framework.implementation.AndroidGame;
import com.ube.salinlahifour.Item;
import com.ube.salinlahifour.Lesson;
import com.ube.salinlahifour.MapActivity;
import com.ube.salinlahifour.ReportCard;
import com.ube.salinlahifour.SalinlahiFour;
import com.ube.salinlahifour.database.UserRecordOperations;
import com.ube.salinlahifour.enumTypes.LevelType;
import com.ube.salinlahifour.enumTypes.StatusType;
import com.ube.salinlahifour.evaluationModule.Evaluation;
import com.ube.salinlahifour.model.UserRecord;
import com.ube.salinlahifour.tools.DateTimeConverter;

import iFeedback.iFeedback;


public abstract class AbstractLAFramework extends AndroidGame {
	protected Lesson lesson;
	protected ArrayList<ImageView> backgrounds;
	protected ArrayList<Item> items;
	protected ArrayList<Item> questions;
	protected ArrayList<SoundPool> timeoutvoices;
	protected String activityName;
	protected String activityLevel;
	protected int layoutID;
	protected int UserID;	
	protected int cnt_question;
	protected iFeedback NLG;
	protected ReportCard reportCard;
	protected Evaluation evaluation;
	public static Context mContext;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		Bundle bundle = getIntent().getExtras();
		activityName = bundle.getString("activityName");
		lesson = SalinlahiFour.getLessonByClassName(activityName);
		activityLevel = bundle.getString("activityLevel");
		UserID = SalinlahiFour.getLoggedInUser().getId();
		evaluation =  new Evaluation(this, activityName, activityLevel.toString());
		Log.d(activityName, "TEST ActivityName in lesson act");
		Log.d(activityLevel, "TEST ActivityLevel in lesson act: " + activityLevel);
		//items = lesson.getItems();
		mContext = getBaseContext();
		getQuestions();
		initiateNarrationModule();
		evaluation.setLexiconSize(evaluation.generateLexiconSize(lesson));//Set total score in evaluation module
		Log.d("Clarity", "Lexicon Size: "+ evaluation.generateLexiconSize(lesson) );
		Log.d("Clarity", "Question Size: " + questions.size());
		evaluation.setTotScore(questions.size());
		super.onCreate(savedInstanceState);
	}
	public static Context getContext() {
	    return mContext;
	}
	@Override
	public Screen getInitScreen() {
		// TODO Auto-generated method stub
		return null;
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
			
			ArrayList<Item> items = SalinlahiFour.getLessonItems(lesson.getTheRealName(), activityLevel);
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
					ArrayList<Item> lessonItems =  SalinlahiFour.getLessonItems(lesson.getTheRealName(), activityLevel);
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
		int passingScore = 0;
		Log.d("TESTINGLessonActivity", "Aldrin: Initiating iFeedback..");
		NLG = new iFeedback();
		Log.d("TESTINGLessonActivity", "Aldrin: Reading iFeedback properties");
		NLG.readProperties();
		evaluation.setLexiconDir(lesson.getLexicon());
		Log.d("Feedback", "Total score: " + questions.size());
		if(questions.size() % 2 > 0){
			Log.d("Feedback", "Total score is Odd");
			passingScore = (int) (questions.size()*0.5)+1;
			Log.d("Feedback", "Passing score: " +  passingScore);
		}else{
			Log.d("Feedback", "Total score is Even");
			passingScore = (int) (questions.size()*0.5);
			Log.d("Feedback", "Passing score: " +  passingScore);
		}
		evaluation.setPassingGrade(passingScore);
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
	protected void showReportCard(Context context, String activityLevel, Evaluation eval, int score, int lessonnumber){
		LevelType LTActLevel = null;
		switch(activityLevel){
		case "EASY": LTActLevel = LevelType.EASY; break;
		case "MEDIUM": LTActLevel = LevelType.MEDIUM; break;
		case "HARD": LTActLevel = LevelType.HARD; break;
		}
		reportCard = new ReportCard(context, lesson, LTActLevel, eval, evaluation.getEndofActivityFeedback(score, lessonnumber),activityName);
		reportCard.reveal();
	}

	protected void setCntQuestions(int x){
		cnt_question = x;
	}
	
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
}

	

