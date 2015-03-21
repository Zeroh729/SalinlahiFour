package com.ube.salinlahifour.lessonActivities;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Input.TouchEvent;
import com.kilobolt.framework.Screen;
import com.ube.salinlahifour.Item;
import com.ube.salinlahifour.Lesson;
import com.ube.salinlahifour.ReportCard;
import com.ube.salinlahifour.SalinlahiFour;
import com.ube.salinlahifour.database.UserLessonProgressOperations;
import com.ube.salinlahifour.database.UserRecordOperations;
import com.ube.salinlahifour.enumTypes.LevelType;
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
	  protected Paint paint, paint2, paint3;
	  protected String sFeedback = "",  sQuestion = "", sAnswer = "", cAnswer = "";
	  protected int userID;
	  protected UserRecordOperations userRecordOperator = new UserRecordOperations(SalinlahiFour.getContext());
	  protected UserLessonProgressOperations userLessonProgressOperator = new UserLessonProgressOperations(SalinlahiFour.getContext());
	  protected int livesLeft;
	  protected int rounds;
	  protected ReportCard reportCard;
	  private Context context;
	  protected Lesson lesson;
	  private boolean gameOverLock;
	  protected ArrayList<Item> items;
	public AbstractGameScreen(Game game, String activityName,String activityLevel ,int userID, Context context, Lesson lesson) {

		super(game);
        Log.d("Aldrin ExtendedFramework", "Abstract game Screen loading");
        this.context = context;
		// TODO Auto-generated constructor stub
		 eval = new Evaluation(SalinlahiFour.getContext(), activtityName, activityLevel);
		 this.userID = userID;
		 this.activtityName = activityName;
	     this.activityLevel = activityLevel;
	     this.lesson = lesson;
	     this.gameOverLock = false;
		 Log.d("Abstract Game Screen", activityName + " " + activityLevel);
		 Looper.prepare();
		 
	     	loadAssets();
		 //Asset Positioning
		 	switch(activityLevel){
		 	case "HARD":
		 		assetPositionHard();break;
		 	case "MEDIUM":
		 		assetPositionMedium();break;
		 	case "EASY":
		 		assetPositionEasy();break;
		 	}
	        // Defining a paint object
		 	Log.d("Abstract GamesScreen", "Initializing Paint Methods");
	        paint = new Paint();
	        paint.setTextSize(20);
	        paint.setTextAlign(Paint.Align.LEFT);
	        paint.setAntiAlias(true);
	        paint.setColor(Color.BLACK);
	        
	        paint2 = new Paint();
			paint2.setTextSize(13);
			paint2.setTextAlign(Paint.Align.LEFT);
			paint2.setAntiAlias(true);
			paint2.setColor(Color.BLUE);

			paint3 = new Paint();
			paint3.setTextSize(10);
			paint3.setTextAlign(Paint.Align.LEFT);
			paint3.setAntiAlias(true);
			paint3.setColor(Color.BLUE);
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
	        if (state == GameState.Running){
	        	switch(activityLevel){
	        	case "HARD":
	        		updateRunningHard(touchEvents, deltaTime);break;
	        	case "MEDIUM":
	        		updateRunningMedium(touchEvents, deltaTime);break;
	        	case "EASY":
	        		updateRunningEasy(touchEvents, deltaTime);break;
	        	}
	        	  if (livesLeft == 0 || rounds == 0) {
	  	            state = GameState.GameOver;
	  	            Looper.myLooper().quit();
	  		        if(!gameOverLock){
	  		        	Log.d("SEMAPHORE", gameOverLock + "");
	  					gameOverLock = true;
	  					
	  					Intent intent = new Intent(context,GameOver.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
	  					Bundle extras = new Bundle();
	  					
	  					extras.putString("ActivityName",activtityName);
	  					extras.putString("ActivityLevel", activityLevel);
	  					extras.putInt("LessonNum", lessonNumber);
	  					extras.putInt("userID", userID);
	  					Log.d("LessonNum", lessonNumber + "");
	  					extras.putString("EOAFeedback", eval.getEndofActivityFeedback(eval.getScore(), lessonNumber));
	  					extras.putParcelable("lesson", lesson);
	  					
	  					intent.putExtras(extras);
	  					context.startActivity(intent); 
//	  				    final Handler handler2 = new Handler();
//	  				  Runnable runnable2 = new Runnable() {
//	  				     @Override
//	  				      public void run() {
	  				/*    	 eval.updateUserLessonProgress(lesson.getName(), activityLevel.toString(), userID);
		  			        LevelType LTActLevel = null;
		  					switch(activityLevel){
		  					case "EASY": LTActLevel = LevelType.EASY; break;
		  					case "MEDIUM": LTActLevel = LevelType.MEDIUM; break;
		  					case "HARD": LTActLevel = LevelType.HARD; break;
		  					}
	  	  			        reportCard = new ReportCard(context, lesson,LTActLevel, eval, eval.getEndofActivityFeedback(eval.getScore(), lessonNumber));
		  					reportCard.setHeight(100);
		  					reportCard.setWidth(100);
		  					reportCard.setFocusable(true);
	  	  			        reportCard.reveal();*/
//	  				      }
//	  				      
//	  				      
//	  				  };
//	  				  handler2.post(runnable2);
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
	  
	  public boolean inBounds(TouchEvent event, int x, int y, int width,
				int height) {
			if (event.x > x && event.x < x + width - 1 && event.y > y
					&& event.y < y + height - 1)
				return true;
			else
				return false;
		}
	  @Override
	    public void paint(float deltaTime) {
	        Graphics g = game.getGraphics();
	        switch(activityLevel){
	        case "HARD":painterHard();break;
	        case "MEDIUM":painterMedium();break;
	        case "EASY":painterEasy();break;
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
	  protected void drawReadyUI() {
	        Graphics g = game.getGraphics();

	        g.drawARGB(155, 0, 0, 0);
	        g.drawString("Tap to Start.", 400, 240, paint);

	    }

	 /* protected void drawRunningUI() {
	        Graphics g = game.getGraphics();
	        g.drawString(sFeedback, 400, 500, paint2);
	        g.drawString(sQuestion, 430, 50, paint2);
	    }*/

	  protected void drawPausedUI() {
	        Graphics g = game.getGraphics();
	        // Darken the entire screen so you can display the Paused screen.
	        //g.drawARGB(155, 0, 0, 0);

	    }

	  protected void drawGameOverUI() {
	        Graphics g = game.getGraphics();
	        String endFeedback = "";
	        //Image bg = g.newImage("house/Medium/roof.png", ImageFormat.RGB565);
	        //g.drawImage(bg, 0, 0);
	        
	        
	        
	    }
		abstract protected void loadAssets();
		abstract protected void assetPositionEasy();
		abstract protected void assetPositionMedium();
		abstract protected void assetPositionHard();
		abstract protected void updateRunningEasy(List<TouchEvent> touchEvents, float deltaTime);
		abstract protected void updateRunningMedium(List<TouchEvent> touchEvents, float deltaTime);
		abstract protected void updateRunningHard(List<TouchEvent> touchEvents, float deltaTime);
		abstract protected void updatePaused(List<TouchEvent> touchEvents);
		abstract protected void updateGameOver(List<TouchEvent> touchEvents);
		abstract protected void drawRunningUI();
		abstract protected void painterEasy();
		abstract protected void painterMedium();
		abstract protected void painterHard();
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
