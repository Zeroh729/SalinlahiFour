package com.ube.salinlahifour.lessonActivities;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;

import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Graphics.ImageFormat;
import com.kilobolt.framework.Input.TouchEvent;
import com.kilobolt.framework.Screen;
import com.ube.salinlahifour.Item;
import com.ube.salinlahifour.Lesson;
import com.ube.salinlahifour.ReportCard;
import com.ube.salinlahifour.SalinlahiFour;
import com.ube.salinlahifour.database.UserLessonProgressOperations;
import com.ube.salinlahifour.database.UserRecordOperations;
import com.ube.salinlahifour.evaluationModule.Evaluation;

public abstract class AbstractGameScreen extends Screen {
	enum GameState {
		Ready, Running, Paused, GameOver
	}

	GameState state = GameState.Ready;
	protected Evaluation eval;
	protected String activtityName;
	protected int lessonNumber;
	protected String activityLevel;
	protected Paint paint, paint2, paint3, paint4, paint5;
	protected String sFeedback = "", sQuestion = "", sAnswer = "", cAnswer = "";
	protected int userID;
	protected UserRecordOperations userRecordOperator = new UserRecordOperations(SalinlahiFour.getContext());
	protected UserLessonProgressOperations userLessonProgressOperator = new UserLessonProgressOperations(SalinlahiFour.getContext());
	protected int nItemsRemaining;
	protected int totalItems;
	protected int rounds;

	protected ReportCard reportCard;
	protected Context context;
	protected Lesson lesson;
	private boolean gameOverLock;
	protected ArrayList<Item> items;
	protected boolean transition, exit;

	public AbstractGameScreen(Game game, String activityName, String activityLevel, int userID, Context context, Lesson lesson, Evaluation evaluation) {

		super(game);
		eval = evaluation;
		Log.d("Aldrin ExtendedFramework", "Abstract game Screen loading");
		this.context = context;
		// TODO Auto-generated constructor stub
		this.userID = userID;
		this.activtityName = activityName;
		this.activityLevel = activityLevel;
		this.lesson = lesson;
		items = lesson.getItems();
		this.gameOverLock = false;
		this.context = context;
		this.loadAssets();

		Log.d("Abstract Game Screen", activityName + " " + activityLevel);
		Looper.prepare();
		eval.setAllowableMistakes(4);
		loadAssets();
		// Asset Positioning
		switch (activityLevel) {
		case "HARD":
			assetPositionHard();
			break;
		case "MEDIUM":
			assetPositionMedium();
			break;
		case "EASY":
			assetPositionEasy();
			break;
		}

		Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/ANDYB.TTF");

		// Defining a paint object
		Log.d("Abstract GamesScreen", "Initializing Paint Methods");
		paint = new Paint();
		paint.setTextSize(25);
		paint.setTypeface(tf);
		paint.setTextAlign(Paint.Align.LEFT);
		paint.setAntiAlias(true);
		paint.setColor(Color.BLACK);

		paint2 = new Paint();
		paint2.setTypeface(tf);
		paint2.setTextSize(20);
		paint2.setTextAlign(Paint.Align.LEFT);
		paint2.setAntiAlias(true);
		paint2.setColor(Color.BLACK);

		paint3 = new Paint();
		paint3.setTypeface(tf);
		paint3.setTextSize(15);
		paint3.setTextAlign(Paint.Align.LEFT);
		paint3.setAntiAlias(true);
		paint3.setColor(Color.BLACK);

		paint4 = new Paint();
		paint4.setTypeface(tf);
		paint4.setTextSize(25);
		paint4.setTextAlign(Paint.Align.CENTER);
		paint4.setAntiAlias(true);
		paint4.setColor(Color.BLACK);

		paint5 = new Paint();

		paint5.setTypeface(tf);
		paint5.setTextSize(25);
		paint5.setTextAlign(Paint.Align.RIGHT);
		paint5.setAntiAlias(true);
		paint5.setColor(Color.WHITE);

		Log.d("Abstract GamesScreen", "Initializing Paint Methods...done");
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

		// We have four separate update methods in this example.
		// Depending on the state of the game, we call different update methods.
		// Refer to Unit 3's code. We did a similar thing without separating the
		// update methods.

		if (state == GameState.Ready)
			updateReady(touchEvents);
		if (state == GameState.Running) {
			switch (activityLevel) {
			case "HARD":
				updateRunningHard(touchEvents, deltaTime);
				break;
			case "MEDIUM":
				updateRunningMedium(touchEvents, deltaTime);
				break;
			case "EASY":
				updateRunningEasy(touchEvents, deltaTime);
				break;
			}

			if (nItemsRemaining == 0 ||  eval.isAlive() == false) {
				state = GameState.GameOver;
				Looper.myLooper().quit();
				if (!gameOverLock) {
					Log.d("SEMAPHORE", gameOverLock + "");
					gameOverLock = true;
					eval.updateUserLessonProgress(lesson.getName(), activityLevel.toString(), userID);
					Intent intent = new Intent(context, GameOver.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					Bundle extras = new Bundle();

					Log.d("Debug ReportCard", "Activity Name: " + activtityName);
					Log.d("Debug ReportCard", "Activity Level: " + activityLevel);
					Log.d("Debug ReportCard", "Lesson Number: " + lessonNumber);
					Log.d("Debug ReportCard", "userID: " + userID);
					Log.d("Debug ReportCard", "Lesson: " + lesson.getName());
					Log.d("Debug ReportCard", "Eval Score: " + eval.getScore());
					Log.d("Debug ReportCard", "Eval Total Score: " + eval.getTotalScore());
					Log.d("Debug ReportCard", "Eval Lexicon Size: " + eval.getLexiconSize());
					Log.d("Debug ReportCard", "Eval Max Tries: " + eval.getAllowableMistakes());
					Log.d("Debug ReportCard", "Eval Remaining Tries: " + eval.getMistakesRemaining());
					Log.d("Debug ReportCard", "Throwing...");

					extras.putString("ActivityName", activtityName);
					extras.putString("ActivityLevel", activityLevel);
					extras.putInt("LessonNum", lessonNumber);
					extras.putInt("userID", userID);
					extras.putInt("e_score", eval.getScore());
					extras.putInt("e_total", eval.getTotalScore());
					extras.putInt("lex_size", eval.getLexiconSize());

					Log.d("LessonNum", lessonNumber + "");
					extras.putString("EOAFeedback", eval.getEndofActivityFeedback(eval.getScore(), lessonNumber));
					extras.putParcelable("lesson", lesson);

					intent.putExtras(extras);
					context.startActivity(intent);
					// final Handler handler2 = new Handler();
					// Runnable runnable2 = new Runnable() {
					// @Override
					// public void run() {
					/*
					 * eval.updateUserLessonProgress(lesson.getName(),
					 * activityLevel.toString(), userID); LevelType LTActLevel =
					 * null; switch(activityLevel){ case "EASY": LTActLevel =
					 * LevelType.EASY; break; case "MEDIUM": LTActLevel =
					 * LevelType.MEDIUM; break; case "HARD": LTActLevel =
					 * LevelType.HARD; break; } reportCard = new
					 * ReportCard(context, lesson,LTActLevel, eval,
					 * eval.getEndofActivityFeedback(eval.getScore(),
					 * lessonNumber)); reportCard.setHeight(100);
					 * reportCard.setWidth(100); reportCard.setFocusable(true);
					 * reportCard.reveal();
					 */
					// }
					//
					//
					// };
					// handler2.post(runnable2);
				}
			}
		}
		if (state == GameState.Paused)
			updatePaused(touchEvents);
		if (state == GameState.GameOver)
			updateGameOver(touchEvents);
	}

	public void updateReady(List<TouchEvent> touchEvents) {

		// This example starts with a "Ready" screen.
		// When the user touches the screen, the game begins.
		// state now becomes GameState.Running.
		// Now the updateRunning() method will be called!

		if (touchEvents.size() > 0)
			state = GameState.Running;
	}

	public boolean inBounds(TouchEvent event, int x, int y, int width, int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y && event.y < y + height - 1)
			return true;
		else
			return false;
	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		// showTransition();

		switch (activityLevel) {
		case "HARD":
			painterHard();
			break;
		case "MEDIUM":
			painterMedium();
			break;
		case "EASY":
			painterEasy();
			break;
		}
		if (state == GameState.Ready)
			drawReadyUI();
		if (state == GameState.Running)
			drawRunningUI();
		if (state == GameState.Paused)
			drawPausedUI();
		if (state == GameState.GameOver)
			drawGameOverUI();

	}

	/*
	 * protected void drawReadyUI() { Graphics g = game.getGraphics();
	 * 
	 * g.drawARGB(155, 0, 0, 0); //g.drawString("Tap to Start.", 400, 240,
	 * paint); //showTransition(); }
	 */
	protected abstract void drawCustomUI();

	protected void drawRunningUI() {
		drawCustomUI();
		Graphics g = game.getGraphics();
		g.drawImage(g.newImage("charbox/utilitybar.png", ImageFormat.RGB565), 375, 0);
		g.drawString("Tries Left:" + eval.getMistakesRemaining() + "/" + eval.getAllowableMistakes() + " " + "Question No:" + (rounds) + "/" + totalItems, 670, 25, paint5);
		
		// g.drawString(sQuestion, 430, 50, paint2);
	}

	protected void drawPausedUI() {
		Graphics g = game.getGraphics();
		// Darken the entire screen so you can display the Paused screen.
		// g.drawARGB(155, 0, 0, 0);

	}

	protected void drawGameOverUI() {
		Graphics g = game.getGraphics();
		String endFeedback = "";
		// Image bg = g.newImage("house/Medium/roof.png", ImageFormat.RGB565);
		// g.drawImage(bg, 0, 0);

	}

	/*
	 * protected void transitionTouchEvent(TouchEvent touchEvents){
	 * if(transition){ if (inBounds(touchEvents, 0, 0,
	 * game.getGraphics().getWidth(), game.getGraphics().getHeight())){
	 * Log.d("Transition Debug", "Falseing"); transition = false; } } }
	 */

	abstract protected void loadAssets();

	abstract protected void assetPositionEasy();

	abstract protected void assetPositionMedium();

	abstract protected void assetPositionHard();

	abstract protected void updateRunningEasy(List<TouchEvent> touchEvents, float deltaTime);

	abstract protected void updateRunningMedium(List<TouchEvent> touchEvents, float deltaTime);

	abstract protected void updateRunningHard(List<TouchEvent> touchEvents, float deltaTime);

	abstract protected void updatePaused(List<TouchEvent> touchEvents);

	abstract protected void updateGameOver(List<TouchEvent> touchEvents);

	abstract protected void painterEasy();

	abstract protected void painterMedium();

	abstract protected void painterHard();

	abstract protected void showTransition();

	abstract protected void drawReadyUI();

	abstract protected void showExit();

	abstract protected void nullify();

	@Override
	public void pause() {
		if (state == GameState.Running)
			state = GameState.Paused;

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

	@Override
	public void backButton() {
		pause();
	}
}
