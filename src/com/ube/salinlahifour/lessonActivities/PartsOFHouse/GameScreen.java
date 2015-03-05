package com.ube.salinlahifour.lessonActivities.PartsOFHouse;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Image;
import com.kilobolt.framework.Screen;
import com.kilobolt.framework.Input.TouchEvent;
import com.ube.salinlahifour.Lesson;
import com.ube.salinlahifour.SalinlahiFour;
import com.ube.salinlahifour.evaluationModule.*;
import com.ube.salinlahifour.lessonActivities.AbstractGameScreen;
import com.ube.salinlahifour.lessonActivities.House;
import com.ube.salinlahifour.database.UserDetailOperations;
import com.ube.salinlahifour.database.UserLessonProgressOperations;
import com.ube.salinlahifour.database.UserRecordOperations;

public class GameScreen extends AbstractGameScreen  {
	
	    // Variable Setup
	    // You would create game objects here.
	    static String activityName = "House";
	    //private int lessonNumber = 2;
	    String activityLevel;
	    private House main_control;
	    private Image bg, body, bodyholder, door, doorholder, gamebg,roof,roofholder,window,windowholder;
	    private Parts pRoof, pBody, pDoor,pWindow, pRoofH, pBodyH, pDoorH, pWindowH ;
	    private Image garage, garageholder, fence, fenceholder;
	    private Parts pGarage, pFence, pGarageH, pFenceH ;
	    private Image tooltip;
	    private Parts pTooltip;
	    int answer = 0, userID;
	    // Edit lives left to the question size
	    
	   // private String sFeedback = "",  sQuestion = "", sAnswer = "";
	    
	    public GameScreen(Game game, String activityLevel, int userID, Context context, Lesson lesson) {
	    	//Super Parameters Game, ActivityName, ActivityLevel, UserID
	        super(game, activityName, activityLevel, userID, context, lesson);
	        Log.d("Aldrin ExtendedFramework", "This should be after abstract Game");
	        this.userID = userID;
	        this.activityLevel = activityLevel;
	        lessonNumber = 2;
	        rounds = 1;
	        //main_control = new House();
	        Log.d("Aldrin ExtendedFramework", "Gamescreen constructor...");
	        
	    }
	    
		@Override
		protected void loadAssets() {
			// TODO Auto-generated method stub
	        Log.d("Aldrin ExtendedFramework", "Loading Assets");
//hello
	        livesLeft = 4;
				bg =  Assets.gamebg;
		        body =Assets.body_selected;
		        bodyholder = Assets.bodyholder;
		        door =Assets.door_selected;
		        doorholder = Assets.doorholder;
		        roof = Assets.roof_selected;
		        roofholder = Assets.roofholder;
		        window = Assets.window_selected;
		        windowholder = Assets.windowholder;
		        garage = Assets.garage_selected;
		        garageholder = Assets.garageholder;
		        fence = Assets.fence_selected;
		        fenceholder = Assets.fenceholder;
		        tooltip = Assets.nothingness;
		        
		        Log.d("Aldrin ExtendedFramework", "Loading Assets...Done");

		}

		@Override
		protected void assetPositionEasy() {
			// TODO Auto-generated method stub
	        Log.d("Aldrin ExtendedFramework", "Positioning Easy Assets");
			  	pRoof = new Parts(59,54);
		        pBody = new Parts(490,100);
		        pDoor = new Parts(498,350);
		        pWindow = new Parts(128,395);
		        
		        pRoofH = new Parts(225,160);
		        pBodyH = new Parts(255,230);
		        pDoorH = new Parts(281,258);
		        pWindowH = new Parts(370,256);
		        
		        pTooltip = new Parts(0,0);
		        Log.d("Aldrin ExtendedFramework", "Positioning Easy Assets...Done");
		}

		@Override
		protected void assetPositionMedium() {
			// TODO Auto-generated method stub
			Log.d("GameScreen", "Positioning Medium"); 
			pRoof = new Parts(59,54);	
	        pBody = new Parts(490,100);
	        pDoor = new Parts(498,350);
	        pWindow = new Parts(128,395);
	        
        	pGarage = new Parts(60,54);
        	pFence = new Parts(130,340);
        	
        	pGarageH = new Parts(370,240);
        	pFenceH = new Parts(0,350);
        	
        	pRoofH = new Parts(155,160);
 	        pBodyH = new Parts(185,230);
 	        pDoorH = new Parts(211,258);
 	        pWindowH = new Parts(300,256);
 	       pTooltip = new Parts(0,0);
 	       Log.d("GameScreen", "Positioning Medium...Done"); 
		}

		@Override
		protected void assetPositionHard() {
			// TODO Auto-generated method stub
			
		}

		@Override
		protected void updateRunningEasy(List<TouchEvent> touchEvents,float deltaTime) {
			Log.d("Lives", Integer.toString(livesLeft));
			
			// TODO Auto-generated method stub
			int len = touchEvents.size();
	        for (int i = 0; i < len; i++) {
	            TouchEvent event = touchEvents.get(i);
	            if (event.type == TouchEvent.TOUCH_DOWN) {
	            	Log.d("Debug GameScreen", "Touch down");
	            	if(inBounds(event, pRoofH.getX() ,pRoofH.getY() , roofholder.getWidth(), roofholder.getHeight())){
	            		Log.d("Debug GameScreen", "Roof Holder: " + event.x + ", " + event.y);
	            		roofholder = Assets.roofholder_selected;
	            		bodyholder = Assets.bodyholder;
	            		doorholder = Assets.doorholder;
	            		windowholder = Assets.windowholder;		
	            		sQuestion = "Bubong";
	            		pTooltip.move(pRoofH.getX()-125, pRoofH.getY()-140);
	            		tooltip = Assets.tooltip_left;
	            		if(answer > 0){
	            			userRecordOperator.open();
	                		userLessonProgressOperator.open();
	            			if(eval.evaluateAnswer(sQuestion, sAnswer, userID)){
		            			Log.d("Debug GameScreen", "CORRECT THATS A Bubong");
		            			sFeedback =  eval.getImmediateFeedback(1, sAnswer, lessonNumber);
		            			livesLeft--;
		            			pRoof.move(pRoofH.getX() , pRoofH.getY());
		            			roof = Assets.roof;
		            			Log.d("Debug GameScreen EVENT","X: " +event.x + "Y: " + event.y);
		            			Log.d("Debug GameScreen pRoof","X: " +pRoof.getX() + "Y: " + pRoof.getY());	
		            			
		            		}else{
		            			Log.d("Wenks", "Mali");
		            			sFeedback =  eval.getImmediateFeedback(1, sAnswer, lessonNumber);
		            		}
	            			userRecordOperator.close();
	                		userLessonProgressOperator.close();
	            		}
	            	}	else if(inBounds(event, pWindowH.getX(), pWindowH.getY(), windowholder.getWidth(), windowholder.getHeight())){
	            		Log.d("Debug GameScreen", "Window Holder: " + event.x + ", " + event.y);
	            		//windowholder = Assets.bodyholder_selected;
	            		bodyholder = Assets.bodyholder;
	            		doorholder = Assets.doorholder;
	            		roofholder = Assets.roofholder;
	            		windowholder = Assets.windowholder_selected;
	            		sQuestion = "Bintana";
	            		pTooltip.move(pWindowH.getX()-125, pWindowH.getY()-140);
	            		tooltip = Assets.tooltip_left;
	            		if(answer > 0){
	            			userRecordOperator.open();
	                		userLessonProgressOperator.open();
	            			if(eval.evaluateAnswer(sQuestion, sAnswer, userID)){
		            			Log.d("Debug GameScreen", "CORRECT THATS A WINDOW");
		            			sFeedback =  eval.getImmediateFeedback(4, sAnswer, lessonNumber);
		            			livesLeft--;
		            			pWindow.move(pWindowH.getX() , pWindowH.getY());
		            			window = Assets.window;
		            		}else{
		            			sFeedback =  eval.getImmediateFeedback(4, sAnswer, lessonNumber);
		            		}
	            			userRecordOperator.close();
	                		userLessonProgressOperator.close();
	            		}
	            	}
	            	else if(inBounds(event, pDoorH.getX(), pDoorH.getY(), doorholder.getWidth(), doorholder.getHeight())){
	            		Log.d("Debug GameScreen", "DoorHolder: " + event.x + ", " + event.y);	
	            		doorholder = Assets.doorholder_selected;
	            		roofholder = Assets.roofholder;
	            		bodyholder = Assets.bodyholder;
	            		windowholder = Assets.windowholder;
	            		sQuestion = "Pinto";
	            		pTooltip.move(pDoorH.getX()-125, pDoorH.getY()-140);
	            		tooltip = Assets.tooltip_left;
	            		if(answer > 0){
	            			userRecordOperator.open();
	                		userLessonProgressOperator.open();
	            			if(eval.evaluateAnswer(sQuestion, sAnswer, userID)){
		            			Log.d("Debug GameScreen", "CORRECT THATS A door");
		            			sFeedback =  eval.getImmediateFeedback(3, sAnswer, lessonNumber);
		            			livesLeft--;
		            			pDoor.move(pDoorH.getX() , pDoorH.getY());
		            			door = Assets.door;
		            		}else{
		            			sFeedback =  eval.getImmediateFeedback(3, sAnswer, lessonNumber);
		            		}
	            			userRecordOperator.close();
	                		userLessonProgressOperator.close();
	            		}
	            	}
	            	
	            	else if(inBounds(event, pBodyH.getX(), pBodyH.getY(), bodyholder.getWidth(), bodyholder.getHeight())){
	            		Log.d("Debug GameScreen", "Body Holder: " + event.x + ", " + event.y);
	            		bodyholder = Assets.bodyholder_selected;
	            		doorholder = Assets.doorholder;
	            		roofholder = Assets.roofholder;
	            		windowholder = Assets.windowholder;
	            		sQuestion = "Dingding";
	            		pTooltip.move(pBodyH.getX()-125, pBodyH.getY()-140);
	            		tooltip = Assets.tooltip_left;

	            		if(answer > 0){
	            			userRecordOperator.open();
	                		userLessonProgressOperator.open();
	            			if(eval.evaluateAnswer(sQuestion, sAnswer, userID)){
		            			Log.d("Debug GameScreen", "CORRECT THATS A BODY");
		            			sFeedback =  eval.getImmediateFeedback(2, sAnswer, lessonNumber);
		            			livesLeft--;
		            			pBody.move(pBodyH.getX() , pBodyH.getY());
		            			body = Assets.body;
		            		}else{
		            			Log.d("Wenks", "Mali");
		            			sFeedback =  eval.getImmediateFeedback(2, sAnswer, lessonNumber);

		            		}
	            			userRecordOperator.close();
	                		userLessonProgressOperator.close();
	            		}
	            	} 
	            	
	            	
	            	if(inBounds(event, pRoof.getX(), pRoof.getY(), roof.getWidth()-25, roof.getHeight()-25)){
	            		Log.d("Debug GameScreen", "Click roof");
	            		answer = 1;
	            		sAnswer = "Bubong";
	            	}	else if(inBounds(event, pBody.getX(), pBody.getY(), body.getWidth(), body.getHeight())){
	            		Log.d("Debug GameScreen", "Click body");	
	            		answer = 2;
	            		sAnswer = "Dingding";
	            	}	else if(inBounds(event, pDoor.getX(), pDoor.getY(), door.getWidth(), door.getHeight())){
	            		Log.d("Debug GameScreen", "Click door");	
	            		answer = 3;
	            		sAnswer = "Pinto";
	            	}
	            	else if(inBounds(event, pWindow.getX(), pWindow.getY(), window.getWidth(), window.getHeight())){
	            		Log.d("Debug GameScreen", "Click window");	
	            		answer = 4;
	            		sAnswer = "Bintana";
	            	} else{
	            		answer = 0;
	            	}
	            }//End touchdown
	        }//end loop
		}

		@Override
		protected void updateRunningMedium(List<TouchEvent> touchEvents,
				float deltaTime) {
			// TODO Auto-generated method stub
			int len = touchEvents.size();
	        for (int i = 0; i < len; i++) {
	            TouchEvent event = touchEvents.get(i);
	            
	            if (event.type == TouchEvent.TOUCH_DOWN) {
            		if(inBounds(event, pGarageH.getX() ,pGarageH.getY() , garageholder.getWidth(), garageholder.getHeight())){
	            		Log.d("Debug GameScreen", "Garage Holder: " + event.x + ", " + event.y);
	            		garageholder = Assets.garageholder_selected;
	            		fenceholder = Assets.fenceholder;
	            		roofholder = Assets.roofholder;
	            		bodyholder = Assets.bodyholder;
	            		doorholder = Assets.doorholder;
	            		windowholder = Assets.windowholder;
	            		
	            		sQuestion = "Garahe";

	            		pTooltip.move(pGarageH.getX(), pGarageH.getY()-120);
	            		tooltip = Assets.tooltip_left;
	            	}else if(inBounds(event, pFenceH.getX(), pFenceH.getY(), fenceholder.getWidth(), fenceholder.getHeight())){
	            		Log.d("Debug GameScreen", "fence Holder: " + event.x + ", " + event.y);
	            		garageholder = Assets.garageholder;
	            		fenceholder = Assets.fenceholder_selected;
	            		bodyholder = Assets.bodyholder;
	            		doorholder = Assets.doorholder;
	            		roofholder = Assets.roofholder;
	            		windowholder = Assets.windowholder;
	            		sQuestion = "Bakod";

	            		pTooltip.move(pFenceH.getX(), pFenceH.getY()-120);
	            		tooltip = Assets.tooltip_left;
	            	}
            		Log.d("Debug GameScreen", "Touch down");
	            	if(inBounds(event, pRoofH.getX() ,pRoofH.getY() , roofholder.getWidth(), roofholder.getHeight())){
	            		Log.d("Debug GameScreen", "Roof Holder: " + event.x + ", " + event.y);
	            		roofholder = Assets.roofholder_selected;
	            		bodyholder = Assets.bodyholder;
	            		doorholder = Assets.doorholder;
	            		windowholder = Assets.windowholder;
	            		garageholder = Assets.garageholder;
	            		fenceholder = Assets.fenceholder;
	            		sQuestion = "Bubong";
	            		pTooltip.move(pRoofH.getX(), pRoofH.getY()-120);
	            		tooltip = Assets.tooltip_left;
	            	}
	            	else if(inBounds(event, pWindowH.getX(), pWindowH.getY(), windowholder.getWidth(), windowholder.getHeight())){
	            		Log.d("Debug GameScreen", "Window Holder: " + event.x + ", " + event.y);
	            		//windowholder = Assets.bodyholder_selected;
	            		bodyholder = Assets.bodyholder;
	            		doorholder = Assets.doorholder;
	            		roofholder = Assets.roofholder;
	            		windowholder = Assets.windowholder_selected;

	            		garageholder = Assets.garageholder;
	            		fenceholder = Assets.fenceholder;
	            		sQuestion = "Bintana";
	            		pTooltip.move(pWindowH.getX(), pWindowH.getY()-120);
	            		tooltip = Assets.tooltip_left;
	            	}
	            	else if(inBounds(event, pDoorH.getX(), pDoorH.getY(), doorholder.getWidth(), doorholder.getHeight())){
	            		Log.d("Debug GameScreen", "DoorHolder: " + event.x + ", " + event.y);	
	            		doorholder = Assets.doorholder_selected;
	            		roofholder = Assets.roofholder;
	            		bodyholder = Assets.bodyholder;
	            		windowholder = Assets.windowholder;
	            		garageholder = Assets.garageholder;
	            		fenceholder = Assets.fenceholder;
	            		sQuestion = "Pinto";
	            		pTooltip.move(pDoorH.getX(), pDoorH.getY()-120);
	            		tooltip = Assets.tooltip_left;
	            	}
	            	
	            	else if(inBounds(event, pBodyH.getX(), pBodyH.getY(), bodyholder.getWidth(), bodyholder.getHeight())){
	            		Log.d("Debug GameScreen", "Body Holder: " + event.x + ", " + event.y);
	            		bodyholder = Assets.bodyholder_selected;
	            		doorholder = Assets.doorholder;
	            		roofholder = Assets.roofholder;
	            		windowholder = Assets.windowholder;
	            		garageholder = Assets.garageholder;
	            		fenceholder = Assets.fenceholder;
	            		sQuestion = "Dingding";
	            		pTooltip.move(pBodyH.getX(), pBodyH.getY()-120);
	            		tooltip = Assets.tooltip_left;
	            	}
	            	
	            	else{
	            		Log.d("Debug GameScreen","NONE: " + event.x + ", " + event.y);
	            	}
            	}
	            if (event.type == TouchEvent.TOUCH_UP) {
	            	if(answer > 0){
	            	if(inBounds(event, pGarageH.getX() , pGarageH.getY() , garageholder.getWidth(), garageholder.getHeight())){
	            		Log.d("Debug GameScreen SIZE", "Garage Holder: " + garageholder.getWidth() + ", " +garageholder.getHeight());
	            		Log.d("gameScreen", "userID");
	            		if(eval.evaluateAnswer(sQuestion, sAnswer, userID)){
	            			Log.d("Debug GameScreen", "CORRECT THATS A Garahe");
	            			sFeedback =  eval.getImmediateFeedback(5, sAnswer, lessonNumber);
	            			livesLeft--;
	            			pGarage.move(pGarageH.getX() , pGarageH.getY());
	            			garage = Assets.garage;
	            			Log.d("Debug GameScreen EVENT","X: " +event.x + "Y: " + event.y);
	            			Log.d("Debug GameScreen pRoof","X: " +pGarage.getX() + "Y: " + pGarage.getY());
	            			
	            			
	            		}else{
	            			Log.d("Wenks", "Mali");
	            			sFeedback =  eval.getImmediateFeedback(5, sAnswer, lessonNumber);
	            		}
	            	}
	            	else if(inBounds(event, pFenceH.getX() , pFenceH.getY() , fenceholder.getWidth(), fenceholder.getHeight())){
	            		//Log.d("Debug GameScreen", "Roof Holder: " + event.x + ", " + event.y);
	            		if(eval.evaluateAnswer(sQuestion, sAnswer, userID)){
	            			Log.d("Debug GameScreen", "CORRECT THATS A FENCE");
	            			sFeedback =  eval.getImmediateFeedback(6, sAnswer, lessonNumber);
	            			livesLeft--;
	            			pFence.move(pFenceH.getX() , pFenceH.getY());
	            			fence = Assets.fence;
	            		}else{
	            			Log.d("Wenks", "Mali");
	            			sFeedback =  eval.getImmediateFeedback(6, sAnswer, lessonNumber);

	            		}
	            	}
	            	
	            	else if(inBounds(event, pRoofH.getX() , pRoofH.getY() , roofholder.getWidth(), roofholder.getHeight())){
		            		Log.d("Debug GameScreen SIZE", "Roof Holder: " + roofholder.getWidth() + ", " +roofholder.getHeight());
		            		if(eval.evaluateAnswer(sQuestion, sAnswer, userID)){
		            			Log.d("Debug GameScreen", "CORRECT THATS A Bubong");
		            			sFeedback =  eval.getImmediateFeedback(1, sAnswer, lessonNumber);
		            			livesLeft--;
		            			pRoof.move(pRoofH.getX() , pRoofH.getY());
		            			roof = Assets.roof;
		            			Log.d("Debug GameScreen EVENT","X: " +event.x + "Y: " + event.y);
		            			Log.d("Debug GameScreen pRoof","X: " +pRoof.getX() + "Y: " + pRoof.getY());
		            			
		            			
		            		}else{
		            			Log.d("Wenks", "Mali");
		            			sFeedback =  eval.getImmediateFeedback(1, sAnswer, lessonNumber);
		            		}
		            	}else if(inBounds(event,pDoorH.getX() , pDoorH.getY(), doorholder.getWidth(), doorholder.getHeight())){
		            		//Log.d("Debug GameScreen", "DoorHolder: " + event.x + ", " + event.y);	
		            		if(eval.evaluateAnswer(sQuestion, sAnswer, userID)){
		            			Log.d("Debug GameScreen", "CORRECT THATS A door");
		            			sFeedback =  eval.getImmediateFeedback(3, sAnswer, lessonNumber);
		            			livesLeft--;
		            			pDoor.move(pDoorH.getX() , pDoorH.getY());
		            			door = Assets.door;
		            		}else{
		            			sFeedback =  eval.getImmediateFeedback(3, sAnswer, lessonNumber);

		            		}
		            	}
		            	else if(inBounds(event, pWindowH.getX() , pWindowH.getY() , windowholder.getWidth(), windowholder.getHeight())){
		            		//Log.d("Debug GameScreen", "Roof Holder: " + event.x + ", " + event.y);
		            		Log.d("Debug GameScreen", "WINDOW UP");
		            		if(eval.evaluateAnswer(sQuestion, sAnswer, userID)){
		            			Log.d("Debug GameScreen", "CORRECT THATS A WINDOW");
		            			sFeedback =  eval.getImmediateFeedback(4, sAnswer, lessonNumber);
		            			livesLeft--;
		            			pWindow.move(pWindowH.getX() , pWindowH.getY());
		            			window = Assets.window;
		            		}else{
		            			sFeedback =  eval.getImmediateFeedback(4, sAnswer, lessonNumber);

		            		}
		            	}
		            	else if(inBounds(event, pBodyH.getX() , pBodyH.getY() , bodyholder.getWidth(), bodyholder.getHeight())){
		            		//Log.d("Debug GameScreen", "Roof Holder: " + event.x + ", " + event.y);

		            		if(eval.evaluateAnswer(sQuestion, sAnswer, userID)){

		            			Log.d("Debug GameScreen", "CORRECT THATS A BODY");
		            			sFeedback =  eval.getImmediateFeedback(2, sAnswer, lessonNumber);
		            			livesLeft--;
		            			pBody.move(pBodyH.getX() , pBodyH.getY());
		            			body = Assets.body;
		            		}else{
		            			Log.d("Wenks", "Mali");
		            			sFeedback =  eval.getImmediateFeedback(2, sAnswer, lessonNumber);

		            		}
		            	}

		            	answer = 0;
		            	}

	            }
	            if (event.type == TouchEvent.TOUCH_DRAGGED) {
	        		
	        		if(answer == 0){
	        		///
	            	 if(inBounds(event, pGarage.getX(), pGarage.getY(), garage.getWidth()-25, garage.getHeight()-25)){
		            		Log.d("Debug GameScreen", "DRAGS garage");
		            		answer = 5;
		            		sAnswer = "Garahe";
		            		//Log.d("Debug GameScreen", "X: "+event.x +"Y: "+event.y);
		            		//Log.d("Debug GameScreen: pRoof", "X: "+pRoof.getX() +"Y: "+pRoof.getY());
		            		//Log.d("Debug GameScreen", event.pointer);
		            		
		            	}
	            	 	else if(inBounds(event, pFence.getX(), pFence.getY(), fence.getWidth(), fence.getHeight())){
		            		//Log.d("Debug GameScreen", "body: " + event.x + ", " + event.y);
		            		Log.d("Debug GameScreen", "DRAGS fence");	
		            		answer = 6;
		            		sAnswer = "Bakod";
		            	}
	            	 if(inBounds(event, pRoof.getX(), pRoof.getY(), roof.getWidth()-25, roof.getHeight()-25)){
		            		Log.d("Debug GameScreen", "DRAGS roof");
		            		answer = 1;
		            		sAnswer = "Bubong";
		            		//Log.d("Debug GameScreen", "X: "+event.x +"Y: "+event.y);
		            		//Log.d("Debug GameScreen: pRoof", "X: "+pRoof.getX() +"Y: "+pRoof.getY());
		            		//Log.d("Debug GameScreen", event.pointer);
		            		
		            	}
	            	 
	            	 	else if(inBounds(event, pBody.getX(), pBody.getY(), body.getWidth(), body.getHeight())){
		            		//Log.d("Debug GameScreen", "body: " + event.x + ", " + event.y);
		            		Log.d("Debug GameScreen", "DRAGS body");	
		            		answer = 2;
		            		sAnswer = "Dingding";
		            	}
		            	
		            	else if(inBounds(event, pDoor.getX(), pDoor.getY(), door.getWidth(), door.getHeight())){
		            		//Log.d("Debug GameScreen", "door: " + event.x + ", " + event.y);	
		            		Log.d("Debug GameScreen", "DRAGS door");	
		            		answer = 3;
		            		sAnswer = "Pinto";
		            		
		            	}
		            	else if(inBounds(event, pWindow.getX(), pWindow.getY(), window.getWidth(), window.getHeight())){
		            		//Log.d("Debug GameScreen", "body: " + event.x + ", " + event.y);
		            		Log.d("Debug GameScreen", "DRAGS window");	
		            		answer = 4;
		            		sAnswer = "Bintana";
		            	}
		            	else{
		            		Log.d("Debug GameScreen: NONE", "X: "+event.x +"Y: "+event.y);
		            	}
	            	 ///
	            }
	            	 
	            }
	            
	            
	            
	        }
	        
		}

		@Override
		protected void updateRunningHard(List<TouchEvent> touchEvents,
				float deltaTime) {
			// TODO Auto-generated method stub
			int len = touchEvents.size();
	        for (int i = 0; i < len; i++) {
	            TouchEvent event = touchEvents.get(i);
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
		        bg =  null;
		        body =null;
		        bodyholder = null;
		        door =null;
		        doorholder = null;
		        roof = null;
		        roofholder = null;
		        windowholder = null;
		        window = null;
		        garage = null;
		        garageholder = null;
		        fence = null;
		        fenceholder = null;
		        // Call garbage collector to clean up memory.
		        System.gc();

		}

		@Override
		protected void painterEasy() {
			// TODO Auto-generated method stub
			 Graphics g = game.getGraphics();
			 
			 	g.drawImage(bg, 0, 0);
		        g.drawImage(roofholder, pRoofH.getX(), pRoofH.getY());
		        g.drawImage(bodyholder, pBodyH.getX(), pBodyH.getY());
		        g.drawImage(doorholder, pDoorH.getX(), pDoorH.getY());
		        g.drawImage(windowholder, pWindowH.getX(), pWindowH.getY());
		        //g.drawImage(body, 1, 1);
		        g.drawImage(roof, pRoof.getX(), pRoof.getY());
		        g.drawImage(body, pBody.getX(), pBody.getY());
		        g.drawImage(door, pDoor.getX(), pDoor.getY());
		        g.drawImage(window, pWindow.getX(), pWindow.getY());

		        g.drawImage(tooltip, pTooltip.getX(), pTooltip.getY());
		}
		protected void painterMedium() {
			// TODO Auto-generated method stub
			Graphics g = game.getGraphics();
			
		        g.drawImage(bg, 0, 0);
		        g.drawImage(garageholder, pGarageH.getX(), pGarageH.getY());
		        g.drawImage(fenceholder, pFenceH.getX(), pFenceH.getY());
		        g.drawImage(roofholder, pRoofH.getX(), pRoofH.getY());
		        g.drawImage(bodyholder, pBodyH.getX(), pBodyH.getY());
		        g.drawImage(doorholder, pDoorH.getX(), pDoorH.getY());
		        g.drawImage(windowholder, pWindowH.getX(), pWindowH.getY());
		        //g.drawImage(body, 1, 1);
		        g.drawImage(roof, pRoof.getX(), pRoof.getY());
		        g.drawImage(body, pBody.getX(), pBody.getY());
		        g.drawImage(door, pDoor.getX(), pDoor.getY());
		        g.drawImage(window, pWindow.getX(), pWindow.getY());
		        g.drawImage(fence, pFence.getX(), pFence.getY());
		        g.drawImage(garage, pGarage.getX(), pGarage.getY());
		        g.drawImage(tooltip, pTooltip.getX(), pTooltip.getY());
		}
		protected void painterHard() {
			// TODO Auto-generated method stub
			 Graphics g = game.getGraphics();
			 
		}

		@Override
		protected void drawRunningUI() {
			// TODO Auto-generated method stub
			  Graphics g = game.getGraphics();
		        g.drawString(sFeedback, 400, 500, paint2);
		        g.drawString(sQuestion, pTooltip.getX()+100, pTooltip.getY()+50, paint2);
		}

	   
}
