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
import java.util.logging.Level;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

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
import com.ube.salinlahifour.howtoplay.HowToPlay;
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
	protected int cnt_question = 0;
	protected iFeedback NLG;
	protected ReportCard reportCard;
	protected Evaluation evaluation;
	protected GamePausePopup gamePause;
	private ImageButton pauseBtn;

	protected int itemno;
	protected String question;
	protected String feedback = " ";
	protected RelativeLayout layout;
	private TextView life_tv;
	private TextView questionNo_tv;
	private RelativeLayout utilBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Log.d("START", "START");

		//		setContentView(R.layout.activity_lesson);
		try{
			setContentView(layoutID);		
		}catch(Exception e){
			errorPopup("Layout ID not found", "Check if:\n"
					+ "1. XML layout for this activity exists.\n"
					+ "2. layoutID has been set in Constructor");
		}

		UserID = SalinlahiFour.getLoggedInUser().getId();
		layout = (RelativeLayout) findViewById(R.id.parent_view);

		Bundle bundle = getIntent().getExtras();
		activityName = bundle.getString("activityName");
		activityLevel = LevelType.valueOf(bundle.getString("activityLevel"));
		Log.d("activityLevel:", activityLevel.toString());
		Log.d("activityName:", activityName.toString());

		//lesson = SalinlahiFour.getLesson(activityName);
		lesson = SalinlahiFour.getLessonByClassName(activityName);
		Log.d("lessonName: ", lesson.getName());
		Log.d("image: ", lesson.getImage() + "");
		Log.d("lexiconthang: ", lesson.getLexicon());
		Log.d("item count: ", "" + lesson.getItems().size());

		evaluation =  new Evaluation(this, lesson.getName(), activityLevel.toString());
		Log.d("Jim Parse On Moving", "Game's Lexicon: " + lesson.getLexicon());

		//Log.d("Feedback Test", "Feedback: "+ evaluation.getImmediateFeedback(1, "Nanay", 1));
		Intent intent = new Intent(this, HowToPlay.class);
		intent.putExtra("lessonName", lesson.getTheRealName());
		startActivity(intent);

		items = lesson.getItems();

		itemno = 0;
		initiateLevels();
		initiateViews();
		//initiateLevels();
		initiateGamePauseUI();
		getQuestions();
		initiateNarrationModule();
		evaluation.setLexiconSize(evaluation.generateLexiconSize(lesson));//Set the number of wrods in lexicon
		evaluation.setTotScore(questions.size()); //Set Total Score of game.
		Log.d("End of Feedback", "Lexicon Size: " + evaluation.generateLexiconSize(lesson) );
		Log.d("End Of Feedback", "Total Score" + questions.size());
		initiateLives();//Initiate lives
		update(); //starts game by showing question and loading choices
		context = getBaseContext();
	}

	private void initiateLives(){
		//ImageView[] life_iv = new ImageView[evaluation.getAllowableMistakes()];
		//for(int i= 0; i<life_iv.length;i++){
		//	life_iv[i] = new ImageView(this);
		//}
		life_tv = new TextView(this);
		questionNo_tv = new TextView(this);
		utilBar = new RelativeLayout(this);
		RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		p.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,  RelativeLayout.TRUE);
		p.setMargins(10, 5, 10, 10);
		life_tv.setId(1);
		life_tv.setTextSize(25);
		life_tv.setTypeface(SalinlahiFour.getFontAndy());
		life_tv.setTextColor(Color.WHITE);
		//life_tv.setTextColor(Color.WHITE);
		//life_tv.setShadowLayer(5f, -1, 1, Color.BLACK);
		RelativeLayout.LayoutParams q = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		q.addRule(RelativeLayout.LEFT_OF,  life_tv.getId());
		//q.addRule(RelativeLayout.ALIGN_PARENT_TOP,  RelativeLayout.TRUE);
		q.setMargins(20, 5, 10, 10);
		questionNo_tv.setTextSize(25);
		questionNo_tv.setTypeface(SalinlahiFour.getFontAndy());
		questionNo_tv.setTextColor(Color.WHITE);

		life_tv.setLayoutParams(p);
		life_tv.setText("Tries Left: "+evaluation.getMistakesRemaining() + "/" + evaluation.getAllowableMistakes());
		questionNo_tv.setLayoutParams(q);
		questionNo_tv.setText("Question No: "+ (itemno+1) + "/" + evaluation.getTotalScore());

		RelativeLayout.LayoutParams u = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		u.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		u.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

		utilBar.setLayoutParams(u);
		utilBar.setBackgroundResource(R.drawable.utilitybar);

		layout.addView(utilBar);
		layout.addView(life_tv);
		layout.addView(questionNo_tv);
		//layout.addView(life_tv);
		//layout.addView(questionNo_tv);
	}

	private void initiateLevels(){
		if(activityLevel.equals(LevelType.EASY)){
			configureEasyLevel();
		}else if(activityLevel.equals(LevelType.MEDIUM)){
			configureMediumLevel();
		}else{
			configureHardLevel();
		}
	}

	private void initiateGamePauseUI(){
		gamePause = new GamePausePopup(this);

		pauseBtn = new ImageButton(this);		
		LayoutParams p = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
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
		
		((ViewGroup)getWindow().getDecorView().getRootView()).addView(pauseBtn);
	}

	protected Item getQuestionItem(){
		return questions.get(itemno);
	}

	protected ImageButton getPauseButton(){
		return pauseBtn;
	}
	protected void loadQuestion(int itemNo){
		Log.d("New Frame", "Loading Questions!");
		question = questions.get(itemNo).getQuestion();
	}
	protected boolean evaluate(String answer){
		if(evaluation.evaluateAnswer(questions.get(itemno).getWord(), answer, UserID)){
			Log.d("New Frame", "Correct!");
			feedback = evaluation.getImmediateFeedback(questions.get(itemno).getID(), answer, lesson.getLessonNumber());
			if(isGameOver()){
				//Log.d("Debug Family", "Aldrin: iFeedback says its finished (Delayed Feedback)");
				Log.d("New Frame", "GameOver!");
				evaluation.updateUserLessonProgress(lesson.getName(), activityLevel.toString(), UserID);
				showReportCard(this);
			}else {
				itemno++;
				ifAnswerIsCorrect();
			}
			life_tv.setText("Tries Left:"+evaluation.getMistakesRemaining() + "/" + evaluation.getAllowableMistakes());
			questionNo_tv.setText("Question No:"+ (itemno+1) + "/" + evaluation.getTotalScore());

			return true;
		}
		else{
			Log.d("New Frame", "Wrong!");
			feedback = evaluation.getImmediateFeedback(questions.get(itemno).getID(), answer, lesson.getLessonNumber());

			if(isGameOver()){
				//Log.d("Debug Family", "Aldrin: iFeedback says its finished (Delayed Feedback)");
				Log.d("New Frame", "GameOver!");
				evaluation.updateUserLessonProgress(lesson.getName(), activityLevel.toString(), UserID);
				showReportCard(this);
			}else{
				ifAnswerIsWrong();
			}
			life_tv.setText("Tries Left:"+evaluation.getMistakesRemaining() + "/" + evaluation.getAllowableMistakes());
			questionNo_tv.setText("Question No:"+ (itemno+1) + "/" + evaluation.getTotalScore());

			return false;
		}
	}
	protected String[] loadSortedChoices(int nChoices){
		String[] choices = new String[nChoices];
		for(int i = 0; i<nChoices; i++){
			choices[i] = lesson.getItems().get(i).getWord();
		}
		return choices;
	}
	protected boolean isGameOver(){
		Log.d("TEST0", "Lives left: " + evaluation.getMistakesRemaining());
		if(evaluation.isAlive() == true && itemno < questions.size() - 1){
			Log.d("New Frame", "GameOver Check: false!");
			return false;
		}else{
			Log.d("New Frame", "GameOver Check: true!");
			return true;
		}	
	}
	protected void getQuestions(){
		questions = new ArrayList<Item>();
		Log.d("TESTINGLessonActivity", "Aldrin: getting Questions");
		UserRecordOperations userdb = new UserRecordOperations(this);
		userdb.open();		
		ArrayList<UserRecord> records;

		try {
			records = userdb.getRecentUserRecordsFromUserId(SalinlahiFour.getLoggedInUser().getId(), activityName);
			int cnt_itemLevel = 0;

			ArrayList<Item> items = (ArrayList<Item>) lesson.getItems().clone();
			HashMap<String, Integer> itemKeys = new HashMap();

			for(int i = 0; i < items.size(); i++){
				if(!items.get(i).getLevel().equals(activityLevel.toString())){
					itemKeys.put(items.get(i).getWord(), 0);
				}else{
					cnt_itemLevel++;
					questions.add(items.get(i));
				}
			}

			Log.d("records size: " + records.size(), "TEST");
			Log.d("itemKeys size: " + itemKeys.size(), "TEST");

			for(int i = 0; i < records.size(); i++){
				//int index = itemNames.indexOf(records.get(i).getCorrectAnswer());
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
			ArrayList<Item> lessonItems = lesson.getItems();

			for(String key : sortedItemKeys.keySet()) {
				if((cnt_question - cnt_itemLevel) > i) {
					Log.d("TEST0", "questions part 5 lessonItems size: " + lessonItems.size());
					for(int j = 0; j < lessonItems.size(); j++)
						if(lessonItems.get(j).getWord().equals(key)){
							//if(lessonItems.get(j).getLevel().equals(activityLevel))
							questions.add(lessonItems.get(j));
							//else if(cnt_question > 0 && cnt_question < j){			
							//}
							break;
						}
				} else {
					break;
				}
				i++;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Log.d("TEST0", "questions size: " + questions.size());
		Collections.shuffle(questions,  new Random(System.nanoTime()));

		for (Item item : questions) {
			Log.d("QUESTIONS LOADED: ", item.getQuestion() + " " + item.getDifficulty());
		}
	}

	private static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap) {

		// Convert Map to List
		List<Map.Entry<String, Integer>> list = 
				new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

		// Sort list with comparator, to compare the Map values
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
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
		Log.d("End Of Feedback", "Total score: " + questions.size());
		if(questions.size() % 2 > 0){
			Log.d("End Of Feedback", "Total score is Odd");
			passingScore = (int) (questions.size()*0.5)+1;
			Log.d("End Of Feedback", "Passing score: " +  passingScore);
		}else{
			Log.d("End Of Feedback", "Total score is Even");
			passingScore = (int) (questions.size()*0.5);
			Log.d("End Of Feedback", "Passing score: " +  passingScore);
		}
		evaluation.setPassingGrade(passingScore);
		evaluation.setAllowableMistakes(4);
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
		reportCard = new ReportCard(context, lesson, activityLevel, evaluation, evaluation.getEndofActivityFeedback(evaluation.getScore(), lesson.getLessonNumber()),activityName);
		reportCard.reveal();
	}

	abstract protected void configureEasyLevel();
	abstract protected void configureMediumLevel();
	abstract protected void configureHardLevel();
	abstract protected void initiateViews();
	abstract protected void update();
	abstract protected void ifAnswerIsCorrect();
	abstract protected void ifAnswerIsWrong();

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

	protected void setLayoutID(int x){
		layoutID = x;
	}

	protected void setCntQuestions(int x){
		cnt_question = x;
	}

	protected int getCntQuestions(){
		return cnt_question;
	}

	protected ArrayList<Item> loadShuffledChoices(int size){
		ArrayList<Item> tempitems = new ArrayList();

		tempitems.add(getQuestionItem());
		Log.d("TEST0", "Adding Selected Item: " + getQuestionItem().getWord());

		for(int i = 1; i < size; i++){
			boolean isEligibleItem = false;
			int loops = 0;
			do{
				Item selectedItem = items.get(new Random().nextInt(items.size()));
				Log.d("TEST0", "Selected Item: " + selectedItem.getWord());
				if(activityLevel.equals(LevelType.EASY) && !tempitems.contains(selectedItem)){
					if(selectedItem.getDifficulty().equals(LevelType.EASY.toString())){
						Log.d("TEST0", "Selected Item: is easy and Eligible!");
						isEligibleItem = true;
					}
				}else if(activityLevel.equals(LevelType.MEDIUM) && !tempitems.contains(selectedItem)){
					if(!selectedItem.getDifficulty().equals(LevelType.HARD.toString())){
						Log.d("TEST0", "Selected Item: is medium and Eligible!");
						isEligibleItem = true;
					}
				}else if(!tempitems.contains(selectedItem)){
					Log.d("TEST0", "Selected Item: is hard and Eligible!");
					isEligibleItem = true;
				}
				if(isEligibleItem){
					Log.d("TEST0", "Adding Selected Item: " + selectedItem.getWord());
					if(!tempitems.contains(selectedItem))
						tempitems.add(selectedItem);
					Log.d("TEST0", "Temp Items size: " + tempitems.size());
					break;
				}else{
					loops++;
					if(loops >= items.size()){
						tempitems.add(selectedItem);
						break;
					}
				}
			}while(!isEligibleItem);
		}
		Collections.shuffle(tempitems,new Random(System.nanoTime()));
		Log.d("TEST0", "Shuffled Items: " + tempitems.size());
		return (ArrayList<Item>) tempitems.clone();
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
		private ImageButton btn_yes;
		private ImageButton btn_no;

		private View popupView;

		public GamePausePopup(Context context){
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

	protected void setQuestionTVText(String text) {
		questionNo_tv.setText(text);
	}

	protected void setLifeTVText(String text) {
		life_tv.setText(text);
	}
}
