package com.ube.salinlahifour.lessonActivities;

import iFeedback.iFeedback;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
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
import com.ube.salinlahifour.enumTypes.LevelType;
import com.ube.salinlahifour.evaluationModule.Evaluation;
import com.ube.salinlahifour.howtoplay.HowToPlay;
import com.ube.salinlahifour.uibuilders.Button.BtnStatesDirector;
import com.ube.salinlahifour.uibuilders.Button.NoBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.PauseBtnStatesDirector;
import com.ube.salinlahifour.uibuilders.Button.YesBtnStatesBuilder;

/**
 * AbstractLessonActivity is a framework that provides the user the necessary methods and parameters to setup the basic elements of a SalinlahiFour game.
 *
 */
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

	/**
	 * Initializes the object before starting the Activity
	 * 
	 * @param savedInstanceState
	 *            contains the parameters of the last run of this activity.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Log.d("START", "START");

		// setContentView(R.layout.activity_lesson);
		try {
			setContentView(layoutID);
		} catch(Exception e) {
			errorPopup("Layout ID not found", "Check if:\n" + "1. XML layout for this activity exists.\n" + "2. layoutID has been set in Constructor");
		}

		UserID = SalinlahiFour.getLoggedInUser().getId();
		layout = (RelativeLayout) findViewById(R.id.parent_view);

		Bundle bundle = getIntent().getExtras();
		activityName = bundle.getString("activityName");
		activityLevel = LevelType.valueOf(bundle.getString("activityLevel"));
		Log.d("activityLevel:", activityLevel.toString());
		Log.d("activityName:", activityName.toString());

		lesson = SalinlahiFour.getLessonByClassName(activityName);
		Log.d("lessonName: ", lesson.getName());
		Log.d("image: ", lesson.getImage() + "");
		Log.d("lexiconthang: ", lesson.getLexicon());
		Log.d("item count: ", "" + lesson.getItems().size());

		evaluation = new Evaluation(this, lesson.getName(), activityLevel.toString());
		Log.d("Jim Parse On Moving", "Game's Lexicon: " + lesson.getLexicon());

		Intent intent = new Intent(this, HowToPlay.class);
		intent.putExtra("lessonName", lesson.getTheRealName());
		startActivity(intent);

		items = lesson.getItems();

		itemno = 0;
		initiateLevels();
		initiateViews();
		// initiateLevels();
		initiateGamePauseUI();
		getQuestions();
		initiateNarrationModule();
		evaluation.setLexiconSize(evaluation.generateLexiconSize(lesson));// Set the number of wrods in lexicon
		evaluation.setTotScore(questions.size()); // Set Total Score of game.
		Log.d("End of Feedback", "Lexicon Size: " + evaluation.generateLexiconSize(lesson));
		Log.d("End Of Feedback", "Total Score" + questions.size());
		initiateLives();// Initiate lives
		update(); // starts game by showing question and loading choices
		context = getBaseContext();
	}

	/**
	 * Initializes the interface of the questions and the lives bar on the upper right corner of the screen.
	 * 
	 */
	private void initiateLives() {
		// ImageView[] life_iv = new ImageView[evaluation.getAllowableMistakes()];
		// for(int i= 0; i<life_iv.length;i++){
		// life_iv[i] = new ImageView(this);
		// }
		life_tv = new TextView(this);
		questionNo_tv = new TextView(this);
		utilBar = new RelativeLayout(this);
		RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		p.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
		p.setMargins(10, 5, 10, 10);
		life_tv.setId(1);
		life_tv.setTextSize(25);
		life_tv.setTypeface(SalinlahiFour.getFontPlaytime());
		life_tv.setTextColor(Color.WHITE);
		// life_tv.setTextColor(Color.WHITE);
		// life_tv.setShadowLayer(5f, -1, 1, Color.BLACK);
		RelativeLayout.LayoutParams q = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		q.addRule(RelativeLayout.LEFT_OF, life_tv.getId());
		// q.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
		q.setMargins(20, 5, 10, 10);
		questionNo_tv.setTextSize(25);
		questionNo_tv.setTypeface(SalinlahiFour.getFontPlaytime());
		questionNo_tv.setTextColor(Color.WHITE);

		life_tv.setLayoutParams(p);
		life_tv.setText("Tries Left: " + evaluation.getMistakesRemaining() + "/" + evaluation.getAllowableMistakes());
		questionNo_tv.setLayoutParams(q);
		questionNo_tv.setText("Question No: " + (itemno + 1) + "/" + evaluation.getTotalScore());

		RelativeLayout.LayoutParams u = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		u.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		u.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

		utilBar.setLayoutParams(u);
		utilBar.setBackgroundResource(R.drawable.utilitybar);

		layout.addView(utilBar);
		layout.addView(life_tv);
		layout.addView(questionNo_tv);
		// layout.addView(life_tv);
		// layout.addView(questionNo_tv);
	}

	/**
	 * Initializes the interface and the parameters needed for the level.
	 * 
	 */
	private void initiateLevels() {
		if(activityLevel.equals(LevelType.EASY)) {
			configureEasyLevel();
		} else if(activityLevel.equals(LevelType.MEDIUM)) {
			configureMediumLevel();
		} else {
			configureHardLevel();
		}
	}

	/**
	 * Initializes the interface and the parameters when the level is paused.
	 * 
	 */
	private void initiateGamePauseUI() {
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

		((ViewGroup) getWindow().getDecorView().getRootView()).addView(pauseBtn);
	}

	/**
	 * Gets the current question being asked.
	 * 
	 * @return Returns an item object of the current question.
	 * 
	 */
	protected Item getQuestionItem() {
		return questions.get(itemno);
	}

	/**
	 * Gets the Pause Button.
	 * 
	 * @return Returns an ImageButton of the Pause Button.
	 * 
	 */
	protected ImageButton getPauseButton() {
		return pauseBtn;
	}

	/**
	 * Loads the question.
	 * 
	 * @param itemNo
	 *            Integer value representing the question number.
	 * 
	 */
	protected void loadQuestion(int itemNo) {
		Log.d("New Frame", "Loading Questions!");
		question = questions.get(itemNo).getQuestion();
	}

	/**
	 * Evaluates the user's answer to the current question.
	 * 
	 * @param answer
	 *            The string representation of the user's answer
	 * @return returns true if the answer is correct, false if the answer is wrong.
	 * 
	 */
	protected boolean evaluate(String answer) {
		if(evaluation.evaluateAnswer(questions.get(itemno).getWord(), answer, UserID)) {
			Log.d("New Frame", "Correct!");
			feedback = evaluation.getImmediateFeedback(questions.get(itemno).getID(), answer, lesson.getLessonNumber());
			if(isGameOver()) {
				// Log.d("Debug Family", "Aldrin: iFeedback says its finished (Delayed Feedback)");
				Log.d("New Frame", "GameOver!");
				evaluation.updateUserLessonProgress(lesson.getName(), activityLevel.toString(), UserID);
				showReportCard(this);
			} else {
				itemno++;
				ifAnswerIsCorrect();
			}
			life_tv.setText("Tries Left:" + evaluation.getMistakesRemaining() + "/" + evaluation.getAllowableMistakes());
			questionNo_tv.setText("Question No:" + (itemno + 1) + "/" + evaluation.getTotalScore());

			return true;
		} else {
			Log.d("New Frame", "Wrong!");
			feedback = evaluation.getImmediateFeedback(questions.get(itemno).getID(), answer, lesson.getLessonNumber());

			if(!evaluation.isAlive()) {
				// Log.d("Debug Family", "Aldrin: iFeedback says its finished (Delayed Feedback)");
				Log.d("New Frame", "GameOver!");
				evaluation.updateUserLessonProgress(lesson.getName(), activityLevel.toString(), UserID);
				showReportCard(this);
			} else {
				ifAnswerIsWrong();
			}
			life_tv.setText("Tries Left:" + evaluation.getMistakesRemaining() + "/" + evaluation.getAllowableMistakes());
			questionNo_tv.setText("Question No:" + (itemno + 1) + "/" + evaluation.getTotalScore());

			return false;
		}
	}

	/**
	 * Loads the choices in the same order that they were initialized.
	 * 
	 * @param nChoices
	 *            the number of choices needed.
	 * @return a string array of the choices.
	 * 
	 */
	protected String[] loadSortedChoices(int nChoices) {
		String[] choices = new String[nChoices];
		for(int i = 0; i < nChoices; i++) {
			choices[i] = lesson.getItems().get(i).getWord();
		}
		return choices;
	}

	/**
	 * Checks whether the game is over.
	 * 
	 * @return true if the game is over. Returns false if the game is still running.
	 * 
	 */
	protected boolean isGameOver() {
		Log.d("TEST0", "Lives left: " + evaluation.getMistakesRemaining());
		if(evaluation.isAlive() == true && itemno < questions.size() - 1) {
			Log.d("New Frame", "GameOver Check: false!");
			return false;
		} else {
			Log.d("New Frame", "GameOver Check: true!");
			return true;
		}
	}

	/**
	 * Loads the questions into the game.
	 * 
	 */
	protected void getQuestions() {
		questions = new ArrayList<Item>();
		Random rand = new Random();

		if(activityLevel == LevelType.HARD) {
			for(Item item : items) {
				questions.add(item);
			}
		} else if(activityLevel == LevelType.MEDIUM) {
			for(Item item : items) {
				if(!item.getDifficulty().equals("HARD")) {
					questions.add(item);
				}
			}
		} else if(activityLevel == LevelType.EASY) {
			for(Item item : items) {
				if(item.getDifficulty().equals("EASY")) {
					questions.add(item);
				}
			}
		}

		int ctr = questions.size() - cnt_question;
		ctr = ctr > 0 ? ctr : 0;
		Log.d("COUNTER", ctr + "");

		for(int i = 0; i < ctr; i++) {
			questions.remove(rand.nextInt(questions.size() - 1));
		}

		Collections.shuffle(questions);
	}

	/**
	 * Initializes the Feedback and the allowable mistakes of the game.
	 * 
	 */
	protected void initiateNarrationModule() {
		int passingScore = 0;
		Log.d("TESTINGLessonActivity", "Aldrin: Initiating iFeedback..");
		NLG = new iFeedback();
		Log.d("TESTINGLessonActivity", "Aldrin: Reading iFeedback properties");
		NLG.readProperties();
		evaluation.setLexiconDir(lesson.getLexicon());
		Log.d("End Of Feedback", "Total score: " + questions.size());
		if(questions.size() % 2 > 0) {
			Log.d("End Of Feedback", "Total score is Odd");
			passingScore = (int) (questions.size() * 0.75) + 1;
			Log.d("End Of Feedback", "Passing score: " + passingScore);
		} else {
			Log.d("End Of Feedback", "Total score is Even");
			passingScore = (int) (questions.size() * 0.75);
			Log.d("End Of Feedback", "Passing score: " + passingScore);
		}
		evaluation.setPassingGrade(passingScore);
		evaluation.setAllowableMistakes(4);
		Log.d("TESTINGLessonActivity", "Aldrin: iFeedback Initiated");
		Log.d("TESTINGLessonActivity", "Aldrin: iFeedback LOL");

	}

	/**
	 * Transfers the user to other activities once the game is over.
	 * 
	 * @param choice
	 *            an integer value to determine which activity to run next.
	 * 
	 */
	protected void end_report(int choice) {// THIS IS FOR TRANSFERRING TO OTHER ACTIVITIES

		switch (choice) {
			case 1:
				Intent intent = new Intent(this, MapActivity.class);
				startActivity(intent);
				break;
			case 2:
				Intent intent1 = new Intent(activityName);
				intent1.putExtra("activityClass", activityName);
				intent1.putExtra("activityLevel", activityLevel);
				startActivity(intent1);
				break;
		}

	}

	/**
	 * Shows the report card that displays the performance of the user after the game is over.
	 * 
	 * @param context
	 *            the context of the activity.
	 * 
	 */
	protected void showReportCard(Context context) {
		reportCard = new ReportCard(context, lesson, activityLevel, evaluation, evaluation.getEndofActivityFeedback(evaluation.getScore(), lesson.getLessonNumber(),evaluation.getStar()), activityName);
		reportCard.reveal();
	}

	abstract protected void configureEasyLevel();

	abstract protected void configureMediumLevel();

	abstract protected void configureHardLevel();

	abstract protected void initiateViews();

	abstract protected void update();

	abstract protected void ifAnswerIsCorrect();

	abstract protected void ifAnswerIsWrong();

	/**
	 * Displays an interface when the system encounters an error.
	 * 
	 * @param title
	 *            a string that represents the title of the error
	 * @param error
	 *            a string that details the error
	 * 
	 */
	private void errorPopup(String title, String error) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
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

	/**
	 * Sets the layoutID of the game.
	 * 
	 * @param layoutID
	 *            an integer value that represents the layoutID of the game
	 * 
	 */
	protected void setLayoutID(int layoutID) {
		this.layoutID = layoutID;
	}

	/**
	 * Sets the number of questions.
	 * 
	 * @param x
	 *            the number of questions
	 * 
	 */
	protected void setCntQuestions(int x) {
		cnt_question = x;
	}

	/**
	 * Gets the number of questions.
	 * 
	 * @return The number of questions.
	 * 
	 */
	protected int getCntQuestions() {
		return cnt_question;
	}

	/**
	 * Loads the choices in a random order
	 * 
	 * @param size
	 *            the number of choices to be loaded.
	 * @return an ArrayList of items with the choices loaded in random order.
	 * @see com.ube.salinlahifour.Item
	 * @see java.util.ArrayList
	 */
	protected ArrayList<Item> loadShuffledChoices(int size) {
		ArrayList<Item> tempitems = new ArrayList();

		tempitems.add(getQuestionItem());
		Log.d("TEST0", "Adding Selected Item: " + getQuestionItem().getWord());

		for(int i = 1; i < size; i++) {
			boolean isEligibleItem = false;
			int loops = 0;
			do {
				Item selectedItem = items.get(new Random().nextInt(items.size()));
				Log.d("TEST0", "Selected Item: " + selectedItem.getWord());
				if(activityLevel.equals(LevelType.EASY) && !tempitems.contains(selectedItem)) {
					if(selectedItem.getDifficulty().equals(LevelType.EASY.toString())) {
						Log.d("TEST0", "Selected Item: is easy and Eligible!");
						isEligibleItem = true;
					}
				} else if(activityLevel.equals(LevelType.MEDIUM) && !tempitems.contains(selectedItem)) {
					if(!selectedItem.getDifficulty().equals(LevelType.HARD.toString())) {
						Log.d("TEST0", "Selected Item: is medium and Eligible!");
						isEligibleItem = true;
					}
				} else if(!tempitems.contains(selectedItem)) {
					Log.d("TEST0", "Selected Item: is hard and Eligible!");
					isEligibleItem = true;
				}
				if(isEligibleItem) {
					Log.d("TEST0", "Adding Selected Item: " + selectedItem.getWord());
					if(!tempitems.contains(selectedItem))
						tempitems.add(selectedItem);
					Log.d("TEST0", "Temp Items size: " + tempitems.size());
					break;
				} else {
					loops++;
					if(loops >= items.size()) {
						tempitems.add(selectedItem);
						break;
					}
				}
			} while(!isEligibleItem);
		}
		Collections.shuffle(tempitems, new Random(System.nanoTime()));
		Log.d("TEST0", "Shuffled Items: " + tempitems.size());
		return (ArrayList<Item>) tempitems.clone();
	}

	/**
	 * Plays the background Music when the game is running.
	 * 
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		SalinlahiFour.getBgm().start();
	}

	/**
	 * Pauses the background Music when the game is paused.
	 * 
	 */
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		SalinlahiFour.getBgm().pause();
	}

	protected class GamePausePopup extends PopupWindow implements android.view.View.OnClickListener {
		private ImageButton btn_yes;
		private ImageButton btn_no;

		private View popupView;

		public GamePausePopup(Context context) {
			LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
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
			switch (v.getId()) {
				case R.id.btn_yes:
					startActivity(new Intent(context, MapActivity.class));
					break;
				case R.id.btn_no:
					dismiss();
					break;
			}
		}

	}

	/**
	 * Sets the Question number text in the user interface.
	 * 
	 */
	protected void setQuestionTVText(String text) {
		questionNo_tv.setText(text);
	}

	/**
	 * Sets the lives left text in the user Interface.
	 * 
	 */
	protected void setLifeTVText(String text) {
		life_tv.setText(text);
	}
}
