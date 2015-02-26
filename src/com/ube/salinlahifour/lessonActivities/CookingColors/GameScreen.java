package com.ube.salinlahifour.lessonActivities.CookingColors;


import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Image;
import com.kilobolt.framework.Screen;
import com.kilobolt.framework.Input.TouchEvent;
import com.ube.salinlahifour.SalinlahiFour;
import com.ube.salinlahifour.evaluationModule.*;
import com.ube.salinlahifour.lessonActivities.AbstractGameScreen;
import com.ube.salinlahifour.lessonActivities.CookingColors.Assets;
import com.ube.salinlahifour.database.UserDetailOperations;
import com.ube.salinlahifour.database.UserLessonProgressOperations;
import com.ube.salinlahifour.database.UserRecordOperations;


public class GameScreen extends AbstractGameScreen  {
	
	    // Variable Setup
	    // You would create game objects here.
	    static String activityName = "Colors";
	    private int lessonNumber = 3;
	    //String activityLevel;
	    
	    private Image bg;
	    private Image[] buttons;
	    private Image bread, frosting, sprinkles;
	    //private Parts pBread, pFrosting, pSprinkles, pButtons;
	    private Cake cake;
	    private ButtonSet breaderButtons, creamerButtons, sprinklerButtons;
	    //private boolean isBread, isFrosted, isSprinkled;
	    int livesLeft = 0, answer = 0, userID, isSubmit = 0;
	    // Edit lives left to the question size
	    
	    
	    public GameScreen(Game game, String activityLevel, int userID) {
	    	//Super Parameters Game, ActivityName, ActivityLevel, UserID
	        super(game, activityName, activityLevel, userID);
	        Log.d("Aldrin ExtendedFramework", "This should be after abstract Game");
	        this.userID = userID;
	        this.activityLevel = activityLevel;
	        Log.d("Aldrin ExtendedFramework", "Gamescreen constructor...");
	        
	    }

		@Override
		protected void loadAssets() {
			// TODO Auto-generated method stub
	        Log.d("Aldrin ExtendedFramework", "Loading Assets");
	        buttons = new Image[Assets.buttons.size()];
	        
	        bg = Assets.bg;
	        bread = Assets.nothingness;
	        frosting = Assets.nothingness;
	        sprinkles = Assets.nothingness;
	        for (int i = 0; i< buttons.length; i++){
	        	buttons[i] = Assets.buttons.get(i);
	        }
	        
	        
		    Log.d("Aldrin ExtendedFramework", "Loading Assets...Done");
		}

		@Override
		protected void assetPositionEasy() {
			// TODO Auto-generated method stub
	        Log.d("Aldrin ExtendedFramework", "Positioning Easy Assets");
			
	        breaderButtons = new ButtonSet(4, 70,40);
	        Log.d("ButtonDebug", "Wdith: " + buttons[0].getWidth() + " Height: " + buttons[0].getHeight());
	        breaderButtons.calculateButtonPosition(buttons[0].getWidth(), buttons[0].getHeight(), 2, 3);
	        
	        creamerButtons = new ButtonSet(4, 290, 40);
	        creamerButtons.calculateButtonPosition(buttons[0].getWidth(), buttons[0].getHeight(), 2, 3);
	        
	        sprinklerButtons = new ButtonSet(4, 510, 40);
	        sprinklerButtons.calculateButtonPosition(buttons[0].getWidth(), buttons[0].getHeight(), 2, 3);
	        
	        livesLeft = 3;
	        
	        cake = new Cake();
	        
	        Log.d("Aldrin ExtendedFramework", "Positioning Easy Assets...Done");
		}

		@Override
		protected void assetPositionMedium() {
			// TODO Auto-generated method stub
		   Log.d("GameScreen", "Positioning Medium"); 
			
			breaderButtons = new ButtonSet(4, 70,40);
	        Log.d("ButtonDebug", "Wdith: " + buttons[0].getWidth() + " Height: " + buttons[0].getHeight());
	        breaderButtons.calculateButtonPosition(buttons[0].getWidth(), buttons[0].getHeight(), 2, 3);
	        
	        creamerButtons = new ButtonSet(4, 290, 40);
	        creamerButtons.calculateButtonPosition(buttons[0].getWidth(), buttons[0].getHeight(), 2, 3);
	        
	        sprinklerButtons = new ButtonSet(4, 510, 40);
	        sprinklerButtons.calculateButtonPosition(buttons[0].getWidth(), buttons[0].getHeight(), 2, 3);
	        livesLeft = 5;
	        cake = new Cake();
			
			breaderButtons.loadRandomColors(6);
			creamerButtons.loadRandomColors(6);
			sprinklerButtons.loadRandomColors(6);
			
			breaderButtons.loadAnswer(activityLevel);
			creamerButtons.loadAnswer(activityLevel);
			sprinklerButtons.loadAnswer(activityLevel);
			
 	       Log.d("GameScreen", "Positioning Medium...Done"); 
		}
		
		@Override
		protected void assetPositionHard() {
			// TODO Auto-generated method stub
			breaderButtons = new ButtonSet(4, 70,40);
	        Log.d("ButtonDebug", "Wdith: " + buttons[0].getWidth() + " Height: " + buttons[0].getHeight());
	        breaderButtons.calculateButtonPosition(buttons[0].getWidth(), buttons[0].getHeight(), 2, 3);
	        
	        creamerButtons = new ButtonSet(4, 290, 40);
	        creamerButtons.calculateButtonPosition(buttons[0].getWidth(), buttons[0].getHeight(), 2, 3);
	        
	        sprinklerButtons = new ButtonSet(4, 510, 40);
	        sprinklerButtons.calculateButtonPosition(buttons[0].getWidth(), buttons[0].getHeight(), 2, 3);
	        
	        cake = new Cake();
			
			breaderButtons.loadRandomColors(8);
			creamerButtons.loadRandomColors(8);
			sprinklerButtons.loadRandomColors(8);
			
			breaderButtons.loadAnswer(activityLevel);
			creamerButtons.loadAnswer(activityLevel);
			sprinklerButtons.loadAnswer(activityLevel);
		}

		@Override
		protected void updateRunningEasy(List<TouchEvent> touchEvents,float deltaTime) {
			// TODO Auto-generated method stub
			int len = touchEvents.size();
	        for (int i = 0; i < len; i++) {
	            TouchEvent event = touchEvents.get(i);
	            
	            /////////////////////////////////////////////
	            if (event.type == TouchEvent.TOUCH_DOWN) { //Happens When you press a specifi
	            	Log.d("Touched Down", "X: " + event.x + "Y: " + event.y );
	            	if(inBounds(event, breaderButtons.getInitX() ,breaderButtons.getInitY() , buttons[0].getWidth() * 2, buttons[0].getHeight() * 2)){//if bread
	            		cake.move(50, 250);
	            		Log.d("GameScreen", "Thats a breader");
	            		if(inBounds(event, breaderButtons.getX(0) ,breaderButtons.getY(0) , buttons[0].getWidth(), buttons[0].getHeight())){//if blue
	            			Log.d("GameScreen", "Thats a breader and blue button");	
	            			bread = Assets.bread.get(0);
	            			cake.addBead("Asul");
	            		}else if(inBounds(event, breaderButtons.getX(1) ,breaderButtons.getY(1) , buttons[1].getWidth(), buttons[1].getHeight())){//if green
	            			Log.d("GameScreen", "Thats a breader and green button");	
	            			bread = Assets.bread.get(1);
	            			cake.addBead("Berde");
	            		}else if(inBounds(event, breaderButtons.getX(2) ,breaderButtons.getY(2) , buttons[2].getWidth(), buttons[2].getHeight())){//if red
	            			Log.d("GameScreen", "Thats a breader and red button");	
	            			bread = Assets.bread.get(2);
	            			cake.addBead("Pula");
	            		}else if(inBounds(event, breaderButtons.getX(3) ,breaderButtons.getY(3) , buttons[3].getWidth(), buttons[3].getHeight())){//if yellow
	            			Log.d("GameScreen", "Thats a breader and yellow button");	
	            			bread = Assets.bread.get(3);
	            			cake.addBead("Dilaw");
	            		}
	            	}else if(inBounds(event, creamerButtons.getInitX() ,creamerButtons.getInitY() , buttons[0].getWidth() * 2, buttons[0].getHeight() * 2)){//if frosting
	            		cake.move(270, 250);
	            		Log.d("GameScreen", "Thats a creamer");
	            		if(inBounds(event, creamerButtons.getX(0) ,creamerButtons.getY(0) , buttons[0].getWidth(), buttons[0].getHeight())){//if blue
	            			Log.d("GameScreen", "Thats a creamer and blue button");	
	            			frosting = Assets.frosting.get(0);
	            			cake.addCream("Asul");
	            		}else if(inBounds(event, creamerButtons.getX(1) ,creamerButtons.getY(1) , buttons[1].getWidth(), buttons[1].getHeight())){//if green
	            			Log.d("GameScreen", "Thats a creamer and green button");	
	            			frosting = Assets.frosting.get(1);
	            			cake.addCream("Berde");
	            		}else if(inBounds(event, creamerButtons.getX(2) ,creamerButtons.getY(2) , buttons[2].getWidth(), buttons[2].getHeight())){//if red
	            			Log.d("GameScreen", "Thats a creamer and red button");	
	            			frosting = Assets.frosting.get(2);
	            			cake.addCream("Pula");
	            		}else if(inBounds(event, creamerButtons.getX(3) ,creamerButtons.getY(3) , buttons[3].getWidth(), buttons[3].getHeight())){//if yellow
	            			Log.d("GameScreen", "Thats a creamer and yellow button");	
	            			frosting = Assets.frosting.get(3);
	            			cake.addCream("Dilaw");
	            		}
	            	}else if(inBounds(event, sprinklerButtons.getInitX() ,sprinklerButtons.getInitY() , buttons[0].getWidth() * 2, buttons[0].getHeight() * 2)){//if sprikinle
	            		cake.move(485, 250);
	            		if(inBounds(event, sprinklerButtons.getX(0) ,sprinklerButtons.getY(0) , buttons[0].getWidth(), buttons[0].getHeight())){//if blue
	            			sprinkles = Assets.sprinkles.get(0);
	            			cake.addSprinkles("Asul");
	            			Log.d("GameScreen", "Thats a sprinkle and Blue button");
	            		}else if(inBounds(event, sprinklerButtons.getX(1) ,sprinklerButtons.getY(1) , buttons[1].getWidth(), buttons[1].getHeight())){//if green
	            			sprinkles = Assets.sprinkles.get(1);
	            			cake.addSprinkles("Berde");
	            			Log.d("GameScreen", "Thats a sprinkle and Green button");
	            		}else if(inBounds(event, sprinklerButtons.getX(2) ,sprinklerButtons.getY(2) , buttons[2].getWidth(), buttons[2].getHeight())){//if red
	            			sprinkles = Assets.sprinkles.get(2);
	            			cake.addSprinkles("Pula");
	            			Log.d("GameScreen", "Thats a sprinkle and Red button");
	            		}else if(inBounds(event, sprinklerButtons.getX(3) ,sprinklerButtons.getY(3) , buttons[3].getWidth(), buttons[3].getHeight())){//if yellow
	            			sprinkles = Assets.sprinkles.get(3);
	            			cake.addSprinkles("Dilaw");
	            			Log.d("GameScreen", "Thats a sprinkle and yellow button");
	            		}
	            	}
	            	
	            	if(inBounds(event, cake.getX() , cake.getX(), bread.getWidth(), bread.getHeight())){
	            		if(cake.isBread() && cake.isCream() && cake.isSprinkled()){
	            			//eval.evaluateAnswer(CorrectAnswer, UserAnswer, userRecordOperator, UserID)
	            			String feedback1, feedback2, feedback3;
	            		}
	            	}
	            	
	
	            }
	            if (event.type == TouchEvent.TOUCH_UP) {
	            	
	            }
	            if (event.type == TouchEvent.TOUCH_DRAGGED) {
	            	if(inBounds(event, cake.getX() ,cake.getY() , bread.getWidth(), bread.getHeight())){
	            		isSubmit = 1;
	            	}
	            }
	        }
		}

		@Override
		protected void updateRunningMedium(List<TouchEvent> touchEvents,float deltaTime) {
			// TODO Auto-generated method stub
			int len = touchEvents.size();
	        for (int i = 0; i < len; i++) {
	            TouchEvent event = touchEvents.get(i);
	            
	            /////////////////////////////////////////////
	            if (event.type == TouchEvent.TOUCH_DOWN) {
	            	if(inBounds(event, breaderButtons.getInitX() ,breaderButtons.getInitY() , buttons[0].getWidth() * 2, buttons[0].getHeight() * 2)){//if bread
	            		cake.move(50, 250);
	            		Log.d("GameScreen", "Thats a breader");
	            		if(inBounds(event, breaderButtons.getX(0) ,breaderButtons.getY(0) , buttons[0].getWidth(), buttons[0].getHeight())){//if blue
	            			Log.d("GameScreen", "Thats a breader and" + breaderButtons.getAnswer(breaderButtons.getChosenColors(0)));	
	            			bread = Assets.bread.get(breaderButtons.getChosenColors(0));
	            			cake.addBead(breaderButtons.getAnswer(0));
	            		}else if(inBounds(event, breaderButtons.getX(1) ,breaderButtons.getY(1) , buttons[1].getWidth(), buttons[1].getHeight())){//if green
	            			Log.d("GameScreen", "Thats a breader and"  + breaderButtons.getAnswer(breaderButtons.getChosenColors(1)));	
	            			bread = Assets.bread.get(breaderButtons.getChosenColors(1));
	            			cake.addBead(breaderButtons.getAnswer(1));
	            		}else if(inBounds(event, breaderButtons.getX(2) ,breaderButtons.getY(2) , buttons[2].getWidth(), buttons[2].getHeight())){//if red
	            			Log.d("GameScreen", "Thats a breader and"  + breaderButtons.getAnswer(breaderButtons.getChosenColors(2)));	
	            			bread = Assets.bread.get(breaderButtons.getChosenColors(2));
	            			cake.addBead(breaderButtons.getAnswer(2));
	            		}else if(inBounds(event, breaderButtons.getX(3) ,breaderButtons.getY(3) , buttons[3].getWidth(), buttons[3].getHeight())){//if yellow
	            			Log.d("GameScreen", "Thats a breader and"  + breaderButtons.getAnswer(breaderButtons.getChosenColors(3)));	
	            			bread = Assets.bread.get(breaderButtons.getChosenColors(3));
	            			cake.addBead(breaderButtons.getAnswer(3));
	            		}
	            	}else if(inBounds(event, creamerButtons.getInitX() ,creamerButtons.getInitY() , buttons[0].getWidth() * 2, buttons[0].getHeight() * 2)){//if bread
	            		cake.move(270, 250);
	            		Log.d("GameScreen", "Thats a creamerButtons");
	            		if(inBounds(event, creamerButtons.getX(0) ,creamerButtons.getY(0) , buttons[0].getWidth(), buttons[0].getHeight())){//if blue
	            			Log.d("GameScreen", "Thats a creamerButtons and" + creamerButtons.getAnswer(creamerButtons.getChosenColors(0)));	
	            			frosting = Assets.frosting.get(creamerButtons.getChosenColors(0));
	            			cake.addCream(creamerButtons.getAnswer(0));
	            		}else if(inBounds(event, creamerButtons.getX(1) ,creamerButtons.getY(1) , buttons[1].getWidth(), buttons[1].getHeight())){//if green
	            			Log.d("GameScreen", "Thats a creamerButtons and"  + creamerButtons.getAnswer(creamerButtons.getChosenColors(1)));	
	            			frosting = Assets.frosting.get(creamerButtons.getChosenColors(1));
	            			cake.addCream(creamerButtons.getAnswer(1));
	            		}else if(inBounds(event, creamerButtons.getX(2) ,creamerButtons.getY(2) , buttons[2].getWidth(), buttons[2].getHeight())){//if red
	            			Log.d("GameScreen", "Thats a creamerButtons and"  + creamerButtons.getAnswer(creamerButtons.getChosenColors(2)));	
	            			frosting = Assets.frosting.get(creamerButtons.getChosenColors(2));
	            			cake.addCream(creamerButtons.getAnswer(2));
	            		}else if(inBounds(event, creamerButtons.getX(3) ,creamerButtons.getY(3) , buttons[3].getWidth(), buttons[3].getHeight())){//if yellow
	            			Log.d("GameScreen", "Thats a creamerButtons and"  + creamerButtons.getAnswer(creamerButtons.getChosenColors(3)));	
	            			frosting = Assets.frosting.get(creamerButtons.getChosenColors(3));
	            			cake.addCream(creamerButtons.getAnswer(3));
	            		}
	            	}else if(inBounds(event, sprinklerButtons.getInitX() ,sprinklerButtons.getInitY() , buttons[0].getWidth() * 2, buttons[0].getHeight() * 2)){//if bread
	            		cake.move(485, 250);
	            		Log.d("GameScreen", "Thats a sprinklerButtons");
	            		if(inBounds(event, sprinklerButtons.getX(0) ,sprinklerButtons.getY(0) , buttons[0].getWidth(), buttons[0].getHeight())){//if blue
	            			Log.d("GameScreen", "Thats a sprinklerButtons and" + sprinklerButtons.getAnswer(sprinklerButtons.getChosenColors(0)));	
	            			sprinkles = Assets.sprinkles.get(sprinklerButtons.getChosenColors(0));
	            			cake.addSprinkles(sprinklerButtons.getAnswer(0));
	            		}else if(inBounds(event, sprinklerButtons.getX(1) ,sprinklerButtons.getY(1) , buttons[1].getWidth(), buttons[1].getHeight())){//if green
	            			Log.d("GameScreen", "Thats a sprinklerButtons and"  + sprinklerButtons.getAnswer(sprinklerButtons.getChosenColors(1)));	
	            			sprinkles = Assets.sprinkles.get(sprinklerButtons.getChosenColors(1));
	            			cake.addSprinkles(sprinklerButtons.getAnswer(1));
	            		}else if(inBounds(event, sprinklerButtons.getX(2) ,sprinklerButtons.getY(2) , buttons[2].getWidth(), buttons[2].getHeight())){//if red
	            			Log.d("GameScreen", "Thats a sprinklerButtons and"  + sprinklerButtons.getAnswer(sprinklerButtons.getChosenColors(2)));	
	            			sprinkles = Assets.sprinkles.get(sprinklerButtons.getChosenColors(2));
	            			cake.addSprinkles(sprinklerButtons.getAnswer(2));
	            		}else if(inBounds(event, sprinklerButtons.getX(3) ,sprinklerButtons.getY(3) , buttons[3].getWidth(), buttons[3].getHeight())){//if yellow
	            			Log.d("GameScreen", "Thats a sprinklerButtons and"  + sprinklerButtons.getAnswer(sprinklerButtons.getChosenColors(3)));	
	            			sprinkles = Assets.sprinkles.get(sprinklerButtons.getChosenColors(3));
	            			cake.addSprinkles(sprinklerButtons.getAnswer(3));
	            		}
	            	}
	            }
	            if (event.type == TouchEvent.TOUCH_UP) {
	            	
	            }
	            if (event.type == TouchEvent.TOUCH_DRAGGED) {
	            	
	            }
	        }
		}

		@Override
		protected void updateRunningHard(List<TouchEvent> touchEvents, float deltaTime) {
			// TODO Auto-generated method stub
			updateRunningMedium(touchEvents, deltaTime);
		}

		@Override
		protected void updatePaused(List<TouchEvent> touchEvents) {
			// TODO Auto-generated method stub
			
		}

		@Override
		protected void updateGameOver(List<TouchEvent> touchEvents) {
			// TODO Auto-generated method stub
			
		}

		@Override
		protected void nullify() {
			// TODO Auto-generated method stub
			 paint = null;
			 paint2 = null;
		     bg = null;
		     bread = null;
		     frosting = null;
		     sprinkles = null;
		    
		        System.gc();

		}

		@Override
		protected void painterEasy() {
			// TODO Auto-generated method stub
			 Graphics g = game.getGraphics();
			 
			 g.drawImage(bg, 0, 0);
			 if(cake.isBread()){
				 g.drawImage(bread, cake.getX(), cake.getY());
			 }
			 if(cake.isCream()){
				 g.drawImage(frosting, cake.getX(), cake.getY());
			 }
			 if(cake.isSprinkled()){
				 g.drawImage(sprinkles, cake.getX(), cake.getY());
			 }
			 for(int i = 0; i<buttons.length;i++){
				// Log.d("Painter", "I: " + i + " X: "+ breaderButtons.getX(i) + " Y: " + breaderButtons.getY(i));
				 g.drawImage(buttons[i], breaderButtons.getX(i), breaderButtons.getY(i));
				 g.drawImage(buttons[i], creamerButtons.getX(i), creamerButtons.getY(i));
				 g.drawImage(buttons[i], sprinklerButtons.getX(i), sprinklerButtons.getY(i));
			 }
			 }
		protected void painterMedium() {
			// TODO Auto-generated method stub
			Graphics g = game.getGraphics();
			g.drawImage(bg, 0, 0);
			 if(cake.isBread()){
				 g.drawImage(bread, cake.getX(), cake.getY());
			 }
			 if(cake.isCream()){
				 g.drawImage(frosting, cake.getX(), cake.getY());
			 }
			 if(cake.isSprinkled()){
				 g.drawImage(sprinkles, cake.getX(), cake.getY());
			 }
			 for(int i = 0; i<4;i++){
					// Log.d("Painter", "I: " + i + " X: "+ breaderButtons.getX(i) + " Y: " + breaderButtons.getY(i));
					 g.drawImage(buttons[breaderButtons.getChosenColors(i)], breaderButtons.getX(i), breaderButtons.getY(i));
					 g.drawImage(buttons[creamerButtons.getChosenColors(i)], creamerButtons.getX(i), creamerButtons.getY(i));
					 g.drawImage(buttons[sprinklerButtons.getChosenColors(i)], sprinklerButtons.getX(i), sprinklerButtons.getY(i));
				 }
			
			}
		protected void painterHard() {
			// TODO Auto-generated method stub
			 Graphics g = game.getGraphics();
			 painterMedium(); 
		}

		@Override
		protected void drawRunningUI() {
			// TODO Auto-generated method stub
			Graphics g = game.getGraphics();
	        g.drawString("sFeedback", 300, 400, paint2);//sFeedback 
	        g.drawString("sQuestion", 50, 400, paint2);//sQuestion
	        
	       
		}

	   
}