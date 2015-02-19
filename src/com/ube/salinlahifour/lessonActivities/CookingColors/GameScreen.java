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
	    String activityLevel;
	    
	    private Image bg, conveyor, breader,creamer, sprinkler,plate;
	    private Parts pConveyor,pBreader, pCreamer, pSprinkler,pPlate;
	    
	    int livesLeft = 5 , answer = 0, userID;
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
	        bg = Assets.gamebg;
	        conveyor = Assets.belt;
	        breader = Assets.breader;
	        creamer = Assets.creamer;
	        sprinkler = Assets.sprinkler;
	        plate = Assets.plate;
		    Log.d("Aldrin ExtendedFramework", "Loading Assets...Done");
		}

		@Override
		protected void assetPositionEasy() {
			// TODO Auto-generated method stub
	        Log.d("Aldrin ExtendedFramework", "Positioning Easy Assets");
			pConveyor = new Parts(0,160);
			pBreader = new Parts(50,0);
			pCreamer = new Parts(200,0);
			pSprinkler = new Parts(380,0);
			pPlate = new Parts(50, 200);
	        Log.d("Aldrin ExtendedFramework", "Positioning Easy Assets...Done");
		}

		@Override
		protected void assetPositionMedium() {
			// TODO Auto-generated method stub
		   Log.d("GameScreen", "Positioning Medium"); 
			
 	       Log.d("GameScreen", "Positioning Medium...Done"); 
		}
		
		@Override
		protected void assetPositionHard() {
			// TODO Auto-generated method stub
			
		}

		@Override
		protected void updateRunningEasy(List<TouchEvent> touchEvents,float deltaTime) {
			// TODO Auto-generated method stub
			int len = touchEvents.size();
	        for (int i = 0; i < len; i++) {
	            TouchEvent event = touchEvents.get(i);
	            
	            /////////////////////////////////////////////
	            if (event.type == TouchEvent.TOUCH_DOWN) { //Happens When you press a specifi
	            	if(inBounds(event, pBreader.getX() ,pBreader.getY() , breader.getWidth(), breader.getHeight())){
	            		pPlate.move(pBreader.getX(), pPlate.getY());
	            	}
	            	else if(inBounds(event, pCreamer.getX() ,pCreamer.getY() , creamer.getWidth(), creamer.getHeight())){
	            		pPlate.move(pCreamer.getX(), pPlate.getY());
	            	}
	            	else if(inBounds(event, pSprinkler.getX() ,pSprinkler.getY() , sprinkler.getWidth(), sprinkler.getHeight())){
	            		pPlate.move(pSprinkler.getX(), pPlate.getY());
	            	}
	            }
	            if (event.type == TouchEvent.TOUCH_UP) {
	            	
	            }
	            if (event.type == TouchEvent.TOUCH_DRAGGED) {
	            	
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
			int len = touchEvents.size();
	        for (int i = 0; i < len; i++) {
	            TouchEvent event = touchEvents.get(i);
	            
	            /////////////////////////////////////////////
	            if (event.type == TouchEvent.TOUCH_DOWN) {
	            }
	            if (event.type == TouchEvent.TOUCH_UP) {
	            	
	            }
	            if (event.type == TouchEvent.TOUCH_DRAGGED) {
	            	
	            }
	        }
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
		     conveyor = null;
		     breader = null;
		     creamer = null;
		     sprinkler = null;
		     plate = null;
		        System.gc();

		}

		@Override
		protected void painterEasy() {
			// TODO Auto-generated method stub
			 Graphics g = game.getGraphics();
			 
			 g.drawImage(bg, 0, 0);
			 g.drawImage(conveyor, pConveyor.getX(), pConveyor.getY());
			 g.drawImage(breader, pBreader.getX(), pBreader.getY());
			 g.drawImage(creamer, pCreamer.getX(), pCreamer.getY());
			 g.drawImage(sprinkler, pSprinkler.getX(), pSprinkler.getY());
			 g.drawImage(plate, pPlate.getX(), pPlate.getY());
			 
			 }
		protected void painterMedium() {
			// TODO Auto-generated method stub
			Graphics g = game.getGraphics();
			}
		protected void painterHard() {
			// TODO Auto-generated method stub
			 Graphics g = game.getGraphics();
			 
		}

		@Override
		protected void drawRunningUI() {
			// TODO Auto-generated method stub
			Graphics g = game.getGraphics();
	        g.drawString("sFeedback", 300, 400, paint2);//sFeedback 
	        g.drawString("sQuestion", 50, 400, paint2);//sQuestion
	        
	       
		}

	   
}
