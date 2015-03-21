package com.ube.salinlahifour.lessonActivities.PartsOFHouse;
import java.util.ArrayList;
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
import com.kilobolt.framework.Sound;
import com.kilobolt.framework.Input.TouchEvent;
import com.ube.salinlahifour.Item;
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
	    private Image bg, dialogbox;
	    private Parts pDialog;
	    private Image body,door,roof,window;
	    private Image bodyholder,doorholder,roofholder,windowholder;
	    private Parts pRoof, pBody, pDoor,pWindow;
	    private Parts pRoofH, pBodyH, pDoorH, pWindowH ;
	    
	    private Image garage, garageholder, fence, fenceholder;
	    private Parts pGarage, pFence, pGarageH, pFenceH ;
	    
		public static Sound v_bakod, v_bintana, v_bubong, v_chimnea, v_dingding, v_garahe, v_hagdan, v_pinto;
		private String correctAnswer;
	    int answer = 0, userID;
	    // Edit lives left to the question size
	    
	   // private String sFeedback = "",  sQuestion = "", sAnswer = "";
	    
	    public GameScreen(Game game, String activityLevel, int userID, Context context, Lesson lesson, ArrayList<Item> items) {
	    	//Super Parameters Game, ActivityName, ActivityLevel, UserID
	        super(game, activityName, activityLevel, userID, context, lesson);
	        Log.d("Aldrin ExtendedFramework", "This should be after abstract Game");
	        this.userID = userID;
	        this.activityLevel = activityLevel;
	        lessonNumber = 2;
	        rounds = 1;
	        
	        super.items = items;
	        //main_control = new House();
	        Log.d("Aldrin ExtendedFramework", "Gamescreen constructor...");
	        Log.d("Lesson Class Debug", "Activity: " + lesson.getActivity());
	        Log.d("Lesson Class Debug", "Description: " + lesson.getDescription());
	        Log.d("Lesson Class Debug", "LessonNumber: " + lesson.getLessonNumber());
	        Log.d("Lesson Class Debug", "getName: " + lesson.getName());
	    }
	    
		@Override
		protected void loadAssets() {
			// TODO Auto-generated method stub
	        Log.d("Aldrin ExtendedFramework", "Loading Assets");
//hello		
	        
	        	eval.setLexiconDir("lexicon_house.xml");
	        	livesLeft = 4;
				bg =  Assets.gamebg;
				dialogbox = Assets.dialogbox;
		        body =Assets.body_choice;
		        bodyholder = Assets.bodyholder;
		        door =Assets.door_choice;
		        doorholder = Assets.doorholder;
		        roof = Assets.roof_choice;
		        roofholder = Assets.roofholder;
		        window = Assets.window_choice;
		        windowholder = Assets.windowholder;
		        garage = Assets.garage_selected;
		        garageholder = Assets.garageholder;
		        fence = Assets.fence_selected;
		        fenceholder = Assets.fenceholder;
		        v_bakod = Assets.bakod;
		        v_bintana = Assets.bintana;
		        v_bubong = Assets.bubong;
		        //v_chimnea = Assets.chimnea;
		        v_dingding = Assets.dingding;
		        v_garahe = Assets.garahe;
		        //v_hagdan = Assets.hagdan;
		        v_pinto = Assets.pinto;
		        Log.d("Aldrin ExtendedFramework", "Loading Assets...Done");

		}

		@Override
		protected void assetPositionEasy() {
			// TODO Auto-generated method stub
	        Log.d("Aldrin ExtendedFramework", "Positioning Easy Assets");
	        	
	        	pDialog = new Parts(65,15);
	        	pRoof = new Parts(27,185);	
		        pBody = new Parts(480,110);
		        pDoor = new Parts(490,355);
		        pWindow = new Parts(120,395);
		        
		        pRoofH = new Parts(222,179);
		        pBodyH = new Parts(245,240);
		        pDoorH = new Parts(269,265);
		        pWindowH = new Parts(349,266);
		        
		        Log.d("Aldrin ExtendedFramework", "Positioning Easy Assets...Done");
		}

		@Override
		protected void assetPositionMedium() {
			// TODO Auto-generated method stub
			Log.d("GameScreen", "Positioning Medium"); 
			pDialog = new Parts(65,15);
			pRoof = new Parts(59,65);	
	        pBody = new Parts(480,110);
	        pDoor = new Parts(498,350);
	        pWindow = new Parts(351,445);
	        
        	pGarage = new Parts(17,232);
        	pFence = new Parts(34,449);
        	
        	pGarageH = new Parts(370,240);
        	pFenceH = new Parts(0,350);
        	
        	pRoofH = new Parts(155,160);
 	        pBodyH = new Parts(185,240);
 	        pDoorH = new Parts(211,258);
 	        pWindowH = new Parts(300,256);
 	       Log.d("GameScreen", "Positioning Medium...Done"); 
		}

		@Override
		protected void assetPositionHard() {
			// TODO Auto-generated method stub
			
		}

		@Override
		protected void updateRunningEasy(List<TouchEvent> touchEvents,float deltaTime) {
			//Log.d("Lives", Integer.toString(livesLeft));
			
			// TODO Auto-generated method stub
			sQuestion = items.get(rounds-1).getLabel();
			correctAnswer = items.get(rounds-1).getWord();
			//Log.d("rounds", rounds + "");
			int len = touchEvents.size();
	        for (int i = 0; i < len; i++) {
	            TouchEvent event = touchEvents.get(i);
	            if (event.type == TouchEvent.TOUCH_DOWN) {
	            	Log.d("Touched Down", "X: " + event.x + "Y: " + event.y );
	            	if(!pRoof.isPlaced()){
	    				roof = Assets.roof_choice;
	    				Log.d("Select", "Choice Roof");
	    			}
	    			if(!pBody.isPlaced()){
	    				body = Assets.body_choice;
	    				Log.d("Select", "Choice Body");
	    			}
	    			if(!pDoor.isPlaced()){
	    				door = Assets.door_choice;
	    				Log.d("Select", "Choice Door");
	    			}
	    			if(!pWindow.isPlaced()){
	    				window = Assets.window_choice;
	    				Log.d("Select", "Choice Window");
	    			}
	            
	            	if(inBounds(event, pRoofH.getX() ,pRoofH.getY() , roofholder.getWidth(), roofholder.getHeight())){
	            		Log.d("Debug GameScreen", "Roof Holder: " + event.x + ", " + event.y);	
	            		
	            		if(answer > 0){
	            			
	            			userRecordOperator.open();
	                		userLessonProgressOperator.open();
	            			if(eval.evaluateAnswer(correctAnswer, sAnswer, userID) && sAnswer == "Bubong" ){
	            				roof = Assets.roof;
		            			Log.d("Debug GameScreen", "CORRECT THATS A Bubong");
		            			sFeedback =  eval.getImmediateFeedback(rounds, sAnswer, lessonNumber);
		            			livesLeft--;
		            			rounds++;
		            			pRoof.move(pRoofH.getX() , pRoofH.getY());
		            			pRoof.setPlaced(true);
		            			Log.d("Debug GameScreen EVENT","X: " +event.x + "Y: " + event.y);
		            			Log.d("Debug GameScreen pRoof","X: " +pRoof.getX() + "Y: " + pRoof.getY());	
		            			
		            		}else{
		            			Log.d("Wenks", "Mali");
		            			sFeedback =  eval.getImmediateFeedback(1, sAnswer, lessonNumber);
		            			changeToError(sAnswer);
		            		}
	            			userRecordOperator.close();
	                		userLessonProgressOperator.close();
	            		}
	            	}	else if(inBounds(event, pWindowH.getX(), pWindowH.getY(), windowholder.getWidth(), windowholder.getHeight())){
	            		Log.d("Debug GameScreen", "Window Holder: " + event.x + ", " + event.y);
	            		//windowholder = Assets.bodyholder_selected;
	            		if(answer > 0){
	            			userRecordOperator.open();
	                		userLessonProgressOperator.open();
	            			if(eval.evaluateAnswer(correctAnswer, sAnswer, userID) && sAnswer == "Bintana"){
	            				window = Assets.window;
		            			Log.d("Debug GameScreen", "CORRECT THATS A WINDOW");
		            			sFeedback =  eval.getImmediateFeedback(rounds, sAnswer, lessonNumber);
		            			livesLeft--;
		            			rounds++;
		            			pWindow.move(pWindowH.getX() , pWindowH.getY());
		            			pWindow.setPlaced(true);
		            		}else{
		            			Log.d("Wenks", "Mali");
		            			changeToError(sAnswer);
		            			sFeedback =  eval.getImmediateFeedback(4, sAnswer, lessonNumber);
		            		}
	            			userRecordOperator.close();
	                		userLessonProgressOperator.close();
	            		}
	            	}
	            	else if(inBounds(event, pDoorH.getX(), pDoorH.getY(), doorholder.getWidth(), doorholder.getHeight())){
	            		Log.d("Debug GameScreen", "DoorHolder: " + event.x + ", " + event.y);
	            		
	            		if(answer > 0){
	            			userRecordOperator.open();
	                		userLessonProgressOperator.open();
	            			if(eval.evaluateAnswer(correctAnswer, sAnswer, userID)  && sAnswer == "Pinto"){
	            				door = Assets.door;
		            			Log.d("Debug GameScreen", "CORRECT THATS A door");
		            			sFeedback =  eval.getImmediateFeedback(rounds, sAnswer, lessonNumber);
		            			livesLeft--;
		            			rounds++;
		            			pDoor.move(pDoorH.getX() , pDoorH.getY());
		            			pDoor.setPlaced(true);
		            		}else{
		            			
		            			Log.d("Wenks", "Mali");
		            			changeToError(sAnswer);
		            			sFeedback =  eval.getImmediateFeedback(3, sAnswer, lessonNumber);
		            		}
	            			userRecordOperator.close();
	                		userLessonProgressOperator.close();
	            		}
	            	}
	            	
	            	else if(inBounds(event, pBodyH.getX(), pBodyH.getY(), bodyholder.getWidth(), bodyholder.getHeight())){
	            		Log.d("Debug GameScreen", "Body Holder: " + event.x + ", " + event.y);
	            		
	            		
	            		if(answer > 0){
	            			userRecordOperator.open();
	                		userLessonProgressOperator.open();
	            			if(eval.evaluateAnswer(correctAnswer, sAnswer, userID) && sAnswer == "Dingding"){
	            				body = Assets.body;
		            			Log.d("Debug GameScreen", "CORRECT THATS A BODY");
		            			sFeedback =  eval.getImmediateFeedback(rounds, sAnswer, lessonNumber);
		            			livesLeft--;
		            			rounds++;
		            			pBody.move(pBodyH.getX() , pBodyH.getY());
		            			pBody.setPlaced(true);
		            		}else{
		            			Log.d("Wenks", "Mali");
		            			changeToError(sAnswer);
		            			sFeedback =  eval.getImmediateFeedback(2, sAnswer, lessonNumber);

		            		}
	            			userRecordOperator.close();
	                		userLessonProgressOperator.close();
	            		}
	            	} 
	            	
	            	
	            	if(inBounds(event, pRoof.getX(), pRoof.getY(), roof.getWidth()-25, roof.getHeight()-25)){
	            		Log.d("Debug GameScreen", "Click roof");
	            		if(pRoof.isPlaced() == false){
	            			answer = 1;
	            			sAnswer = "Bubong";
	            			v_bubong.play(0.85f);
	            			roof = Assets.roof_selected;
	            			Log.d("Select", "Selected Roof");
	            		}
	            	}	else if(inBounds(event, pBody.getX(), pBody.getY(), body.getWidth(), body.getHeight())){
	            		Log.d("Debug GameScreen", "Click body");
	            		if(pBody.isPlaced() == false){
	            			answer = 2;
	            			sAnswer = "Dingding";
	            			v_dingding.play(0.85f);
	            			body = Assets.body_selected;
	            			Log.d("Select", "Selected Body");
	            		}
	            	}	else if(inBounds(event, pDoor.getX(), pDoor.getY(), door.getWidth(), door.getHeight())){
	            		Log.d("Debug GameScreen", "Click door");
	            		if(pDoor.isPlaced() == false){
	            			answer = 3;
	            			sAnswer = "Pinto";
	            			v_pinto.play(0.85f);
	            			door = Assets.door_selected;
	            			Log.d("Select", "Selected Door");
	            		}
	            	}
	            	else if(inBounds(event, pWindow.getX(), pWindow.getY(), window.getWidth(), window.getHeight())){
	            		Log.d("Debug GameScreen", "Click window");	
	            		if(pWindow.isPlaced() == false){
	            			answer = 4;
	            			sAnswer = "Bintana";
	            			v_bintana.play(0.85f);
	            			window = Assets.window_selected;
	            			Log.d("Select", "Selected Window");
	            		}
	            	}
	            }//End touchdown
	        }//end loop
		}

		@Override
		protected void updateRunningMedium(List<TouchEvent> touchEvents,float deltaTime) {
			// TODO Auto-generated method stub
			updateRunningEasy(touchEvents, deltaTime);
			int len = touchEvents.size();
	        for (int i = 0; i < len; i++) {
	            TouchEvent event = touchEvents.get(i);
	            if (event.type == TouchEvent.TOUCH_DOWN) {
	            	Log.d("Debug Medium", "Question: " + sQuestion);
	            	Log.d("Debug Medium", "Correct Answer: " + correctAnswer);
	            	Log.d("Touched Down", "X: " + event.x + "Y: " + event.y );
	            	
	            	if(!pFence.isPlaced()){
	    				fence = Assets.fence_choice;
	    				Log.d("Select", "Choice Fence");
	    			}
	    			if(!pGarage.isPlaced()){
	    				garage = Assets.garage_choice;
	    				Log.d("Select", "Choice Garage");
	    			}
	    			
            		if(inBounds(event, pGarageH.getX() ,pGarageH.getY() , garageholder.getWidth(), garageholder.getHeight())){
	            		Log.d("Debug GameScreen", "Garage Holder: " + event.x + ", " + event.y);
	            		Log.d("Debug Medium", "Answer Int: " + answer);
	            		Log.d("Debug Medium", "Answer: " + sAnswer);
	            		if(answer > 0){
	            			Log.d("Debug Medium", "There is an answer >0");
	            			userRecordOperator.open();
	                		userLessonProgressOperator.open();
	            			if(eval.evaluateAnswer(correctAnswer, sAnswer, userID) && sAnswer == "Garahe"){
		            			Log.d("Debug GameScreen", "CORRECT THATS A GARAGE");
		            			garage = Assets.garage;
		            			sFeedback =  eval.getImmediateFeedback(rounds, sAnswer, lessonNumber);
		            			livesLeft--;
		            			rounds++;
		            			pGarage.move(pGarageH.getX() , pGarageH.getY());
		            			pGarage.setPlaced(true);
		            		}else{
		            			Log.d("Wenks", "Mali");
		            			Log.d("Debug Medium", "Mali");
		            			sFeedback =  eval.getImmediateFeedback(5, sAnswer, lessonNumber);
		            			changeToError(sAnswer);
		            		}
	            			userRecordOperator.close();
	                		userLessonProgressOperator.close();
	            		}
	            	}else if(inBounds(event, pFenceH.getX(), pFenceH.getY(), fenceholder.getWidth(), fenceholder.getHeight())){
	            		Log.d("Debug GameScreen", "fence Holder: " + event.x + ", " + event.y);

	            		if(answer > 0){
	            			userRecordOperator.open();
	                		userLessonProgressOperator.open();
	            			if(eval.evaluateAnswer(correctAnswer, sAnswer, userID) && sAnswer == "Bakod"){
		            			Log.d("Debug GameScreen", "CORRECT THATS A GARAGE");
		            			fence = Assets.fence;
		            			sFeedback =  eval.getImmediateFeedback(rounds, sAnswer, lessonNumber);
		            			livesLeft--;
		            			rounds++;
		            			pFence.move(pFenceH.getX() , pFenceH.getY());
		            			pFence.setPlaced(true);
		            			
		            		}else{
		            			Log.d("Wenks", "Mali");
		            			sFeedback =  eval.getImmediateFeedback(6, sAnswer, lessonNumber);
		            			changeToError(sAnswer);
		            		}
	            			userRecordOperator.close();
	                		userLessonProgressOperator.close();
	            		}
	            	}
	            	else{
	            		Log.d("Debug GameScreen","NONE: " + event.x + ", " + event.y);
	            	}
            		
            		
            		
            	 if(inBounds(event, pGarage.getX(), pGarage.getY(), garage.getWidth()-25, garage.getHeight()-25)){
            		Log.d("Debug GameScreen", "DRAGS garage");
            		answer = 5;
            		sAnswer = "Garahe";
            		garage = Assets.garage_selected;
            		v_garahe.play(0.85f);
            	}
        	 	else if(inBounds(event, pFence.getX(), pFence.getY(), fence.getWidth(), fence.getHeight())){
            		Log.d("Debug GameScreen", "DRAGS fence");	
            		answer = 6;
            		sAnswer = "Bakod";
            		fence = Assets.fence_selected;
            		v_bakod.play(0.85f);
            	}
	            
	            
	        }//Touch down
	        }//event for loop
		}

		@Override
		protected void updateRunningHard(List<TouchEvent> touchEvents,
				float deltaTime) {
			// TODO Auto-generated method stub
			updateRunningMedium(touchEvents, deltaTime);
			int len = touchEvents.size();
	        for (int i = 0; i < len; i++) {
	            TouchEvent event = touchEvents.get(i);
	        }
		}
		private void changeToError(String answer){
			switch(answer){
			case "Bubong":roof = Assets.roof_error; break;
			case "Dingding":body = Assets.body_error; break;
			case "Bintana":window = Assets.window_error; break;
			case "Pinto":door = Assets.door_error; break;
			case "Garahe":garage = Assets.garage_error; break;
			case "Bakod":fence = Assets.fence_error; break;
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
			 	g.drawImage(dialogbox, pDialog.getX(), pDialog.getY());
		        g.drawImage(roofholder, pRoofH.getX(), pRoofH.getY());
		        g.drawImage(bodyholder, pBodyH.getX(), pBodyH.getY());
		      
		        //g.drawImage(body, 1, 1);
		        g.drawImage(roof, pRoof.getX(), pRoof.getY());
		        g.drawImage(body, pBody.getX(), pBody.getY());
		        
		        g.drawImage(doorholder, pDoorH.getX(), pDoorH.getY());
		        g.drawImage(windowholder, pWindowH.getX(), pWindowH.getY());
		        g.drawImage(door, pDoor.getX(), pDoor.getY());
		        g.drawImage(window, pWindow.getX(), pWindow.getY());

		}
		protected void painterMedium() {
			// TODO Auto-generated method stub
			Graphics g = game.getGraphics();
			
		        g.drawImage(bg, 0, 0);
		    	g.drawImage(dialogbox, pDialog.getX(), pDialog.getY());
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
		}
		protected void painterHard() {
			// TODO Auto-generated method stub
			 Graphics g = game.getGraphics();
			 
		}

		@Override
		protected void drawRunningUI() {
			// TODO Auto-generated method stub
			  Graphics g = game.getGraphics();
		        g.drawString(sFeedback, 97, 43, paint2);
		        g.drawString(sQuestion, 97, 63, paint);
		}

	   
}
