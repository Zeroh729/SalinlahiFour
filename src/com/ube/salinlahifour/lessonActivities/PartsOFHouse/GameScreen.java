package com.ube.salinlahifour.lessonActivities.PartsOFHouse;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Looper;
import android.util.Log;

import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Image;
import com.kilobolt.framework.Screen;
import com.kilobolt.framework.Sound;
import com.kilobolt.framework.Input.TouchEvent;
import com.ube.salinlahifour.Item;
import com.ube.salinlahifour.Lesson;
import com.ube.salinlahifour.MapActivity;
import com.ube.salinlahifour.SalinlahiFour;
import com.ube.salinlahifour.evaluationModule.*;
import com.ube.salinlahifour.lessonActivities.AbstractGameScreen;
import com.ube.salinlahifour.lessonActivities.House;
import com.ube.salinlahifour.lessonActivities.PartsOFHouse.Parts;
import com.ube.salinlahifour.lessonActivities.PartsOFHouse.Assets;
import com.ube.salinlahifour.database.UserDetailOperations;
import com.ube.salinlahifour.database.UserLessonProgressOperations;
import com.ube.salinlahifour.database.UserRecordOperations;

public class GameScreen extends AbstractGameScreen  {
	
	    // Variable Setup
	    // You would create game objects here.
	    static String activityName = "House";
	    //private int lessonNumber = 2;
	    String activityLevel;
	    private Image backbtn, nobtn, yesbtn, bgBack;
	    private Image bg, dialogbox, feedboxBoy, feedboxGirl,nextBtn;
	    private Parts pDialog,p_nextBtn;
	    private Image body,door,roof,window;
	    private Image bodyholder,doorholder,roofholder,windowholder;
	    private Parts pRoof, pBody, pDoor,pWindow;
	    private Parts pRoofH, pBodyH, pDoorH, pWindowH ;
	    
	    private Image garage, garageholder, fence, fenceholder;
	    private Parts pGarage, pFence, pGarageH, pFenceH ;
	    
	    private Image chimney, chimneyholder, stairs, stairsholder;
	    private Parts pChimney, pStairs, pChimneyH, pStairsH ;
	    private Parts pNo,pYes,pBackg;
		public static Sound v_bakod, v_bintana, v_bubong, v_chimnea, v_dingding, v_garahe, v_hagdan, v_pinto;
		private String correctAnswer;
	    int answer = 0, userID;
	    // Edit lives left to the question size
	    
	   // private String sFeedback = "",  sQuestion = "", sAnswer = "";
	    public GameScreen(Game game, String activityLevel, int userID, Context context, Lesson lesson, ArrayList<Item> items, Evaluation evals) {
	    	//Super Parameters Game, ActivityName, ActivityLevel, UserID
	        super(game, activityName, activityLevel, userID, context, lesson, evals);
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
	        this.backbtn = Assets.backbtn;
	        this.bgBack = Assets.bgBack;
	        this.yesbtn = Assets.yesbtn;
	        this.nobtn = Assets.nobtn;
	        	exit = false;
	        	transition = false;
	        	feedboxBoy = Assets.feedboxBoy;
	        	feedboxGirl = Assets.feedboxGirl;
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
		        garage = Assets.garage_choice;
		        garageholder = Assets.garageholder;
		        fence = Assets.fence_choice;
		        fenceholder = Assets.fenceholder;
		        chimney = Assets.chimney_choice;
		        chimneyholder = Assets.chimneyholder;
		        stairs = Assets.stairs_choice;
		        stairsholder = Assets.stairsholder;
		        v_bakod = Assets.bakod;
		        v_bintana = Assets.bintana;
		        v_bubong = Assets.bubong;
		        v_chimnea = Assets.chimnea;
		        v_dingding = Assets.dingding;
		        v_garahe = Assets.garahe;
		        v_hagdan = Assets.hagdan;
		        v_pinto = Assets.pinto;
		        nextBtn = Assets.nextBtn;
		        p_nextBtn = new Parts(510,60);
		        pYes = new Parts(220,300);
		        pNo = new Parts(400,300);
		        pBackg = new Parts(195, 100);
		        
		     
		        Log.d("Aldrin ExtendedFramework", "Loading Assets...Done");

		}

		@Override
		protected void assetPositionEasy() {
			// TODO Auto-generated method stub
	        Log.d("Aldrin ExtendedFramework", "Positioning Easy Assets");
	        	nItemsRemaining = 4;
	        	pDialog = new Parts(65,40);
	        	pRoof = new Parts(27,185);	
		        pBody = new Parts(480,150);
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
			nItemsRemaining = 6;
			pDialog = new Parts(65,40);
			pRoof = new Parts(230,130);	
	        pBody = new Parts(480,150);
	        pDoor = new Parts(510,270);
	        pWindow = new Parts(351,445);
	        
        	pGarage = new Parts(17,232);
        	pFence = new Parts(34,157);
        	
        	pGarageH = new Parts(355,260);
        	pFenceH = new Parts(0,385);
        	
        	pRoofH = new Parts(165,190);
 	        pBodyH = new Parts(190,250);
 	        pDoorH = new Parts(215,270);
 	        pWindowH = new Parts(290,273);
 	       Log.d("GameScreen", "Positioning Medium...Done"); 
		}

		@Override
		protected void assetPositionHard() {
			// TODO Auto-generated method stub
			Log.d("GameScreen", "Positioning Hard"); 
			nItemsRemaining = 8;
			pDialog = new Parts(65,40);
			pRoof = new Parts(383,425);	
	        pBody = new Parts(480,150);
	        pDoor = new Parts(498,270);
	        pWindow = new Parts(351,445);
	        
        	pGarage = new Parts(17,232);
        	pFence = new Parts(34,157);
        	
        	pChimney = new Parts(390,125);
        	pStairs = new Parts(590,265);
        	
        	pChimneyH = new Parts(270,143);
        	pStairsH = new Parts(205,330);
        	
        	pGarageH = new Parts(345,265);
        	pFenceH = new Parts(0,385);
        	
        	pRoofH = new Parts(165,170);
 	        pBodyH = new Parts(190,230);
 	        pDoorH = new Parts(213,250);
 	        pWindowH = new Parts(285,250);
 	       Log.d("GameScreen", "Positioning Hard...Done"); 
		}

		@Override
		protected void updateRunningEasy(List<TouchEvent> touchEvents,float deltaTime) {
			//Log.d("Lives", Integer.toString(nItemsRemaining));
			
			// TODO Auto-generated method stub
			sQuestion = lesson.getItems().get(rounds-1).getQuestion();
			
			sQuestion = sQuestion.split(" ")[0];
			correctAnswer = lesson.getItems().get(rounds-1).getWord();
			//Log.d("rounds", rounds + "");
			int len = touchEvents.size();
	        for (int i = 0; i < len; i++) {
	            TouchEvent event = touchEvents.get(i);
	            
	            if (event.type == TouchEvent.TOUCH_DOWN) {
	            	
	            	Log.d("Touched Down", "X: " + event.x + "Y: " + event.y );
	            	
	            	if(inBounds(event, 1 ,1 ,this.backbtn.getWidth(), this.backbtn.getHeight())){
	            		exit = true;
	            	}
		            if(exit){
		            	if(inBounds(event, pNo.getX() ,pNo.getY() ,this.nobtn.getWidth(), this.nobtn.getWidth())){
		            		Log.d("Exit Debug", "This should be once: NO");
		            		exit = false;
		            	}else if (inBounds(event, pYes.getX() ,pYes.getY() ,this.yesbtn.getWidth(), this.yesbtn.getWidth())){
		            		Log.d("Exit Debug", "Quit");
		            		Looper.myLooper().quit();
		            		Intent intent = new Intent(context,MapActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
		            		context.startActivity(intent); 
			            }else if(inBounds(event, pNo.getX() ,pNo.getY() ,this.nobtn.getWidth(), this.nobtn.getWidth())){
			            	exit = false;
			            	Log.d("Exit Debug", "Continue");
			            }
		            }
	            	if(transition){
	            		if(inBounds(event, p_nextBtn.getX() ,p_nextBtn.getY() , nextBtn.getWidth(), nextBtn.getHeight())){
	            		Log.d("Transition Debug", "Falseing in easy");
	   				  	transition = false;
	            		}
	            	}else{
	            	if(!pRoof.isPlaced()){
	    				roof = Assets.roof_choice;
	    				Log.d("Select", "Choice Roof");
	    			}else{
	    				roof = Assets.roof;
	    			}
	    			if(!pBody.isPlaced()){
	    				body = Assets.body_choice;
	    				Log.d("Select", "Choice Body");
	    			}else{
	    				body = Assets.body;
	    			}
	    			if(!pDoor.isPlaced()){
	    				door = Assets.door_choice;
	    				Log.d("Select", "Choice Door");
	    			}else{
	    				door = Assets.door;
	    			}
	    			if(!pWindow.isPlaced()){
	    				window = Assets.window_choice;
	    				Log.d("Select", "Choice Window");
	    			}else{
	    				window = Assets.window;
	    			}
	            
	            	if(inBounds(event, pRoofH.getX() ,pRoofH.getY() , roofholder.getWidth(), roofholder.getHeight())){
	            		Log.d("Debug GameScreen", "Roof Holder: " + event.x + ", " + event.y);	
	            		
	            		if(answer > 0 && !pRoof.isPlaced()){
	            			userRecordOperator.open();
	                		userLessonProgressOperator.open();
	                		Log.d("Debug Error", "answer: " + answer + "sAnswer: " + sAnswer );
	                		Log.d("Debug Error", "Correct Answer: "+ correctAnswer );
	            			if(eval.evaluateAnswer(correctAnswer, sAnswer, userID) && sAnswer == "Bubong" ){
	            				roof = Assets.roof;
		            			Log.d("Debug Error", "CORRECT THATS A Bubong");
		            			Log.d("Debug Feedback", "getID" + lesson.getItems().get(rounds-1).getID());
		            			sFeedback =  eval.getImmediateFeedback(lesson.getItems().get(rounds-1).getID(), sAnswer, lessonNumber);
		            			nItemsRemaining--;
		            			rounds++;
		            			pRoof.move(pRoofH.getX() , pRoofH.getY());
		            			pRoof.setPlaced(true);
		            			Log.d("Debug GameScreen EVENT","X: " +event.x + "Y: " + event.y);
		            			Log.d("Debug GameScreen pRoof","X: " +pRoof.getX() + "Y: " + pRoof.getY());	
		            			transition = true;
		            			
		            		}else{
		            			if(answer == lesson.getItems().get(rounds-1).getID()){
		            				sFeedback =  eval.getImmediateFeedback(1, sAnswer, lessonNumber);
		            			}else{
		            				sFeedback = "Sorry That is not what i'm asking";
		            			}
		            			Log.d("Debug Error", "Mali");
		            			transition = true;
		            			changeToError(sAnswer);
		            			
		            		}
	            			userRecordOperator.close();
	                		userLessonProgressOperator.close();
	            		}
	            	}	else if(inBounds(event, pWindowH.getX(), pWindowH.getY(), windowholder.getWidth(), windowholder.getHeight())){
	            		Log.d("Debug GameScreen", "Window Holder: " + event.x + ", " + event.y);
	            		//windowholder = Assets.bodyholder_selected;
	            		if(answer > 0 && !pWindow.isPlaced()){
	            			userRecordOperator.open();
	                		userLessonProgressOperator.open();
	                		Log.d("Debug Error", "answer: " + answer + "sAnswer: " + sAnswer );
	            			if(eval.evaluateAnswer(correctAnswer, sAnswer, userID) && sAnswer == "Bintana"){
	            				window = Assets.window;
		            			Log.d("Debug Error", "CORRECT THATS A WINDOW");
		            			Log.d("Debug Feedback", "getID" + lesson.getItems().get(rounds-1).getID());
		            			sFeedback =  eval.getImmediateFeedback(lesson.getItems().get(rounds-1).getID(), sAnswer, lessonNumber);
		            			nItemsRemaining--;
		            			rounds++;
		            			pWindow.move(pWindowH.getX() , pWindowH.getY());
		            			pWindow.setPlaced(true);
		            			transition = true;
		            		}else{
		            			if(answer == lesson.getItems().get(rounds-1).getID()){
		            				sFeedback =  eval.getImmediateFeedback(4, sAnswer, lessonNumber);
		            			}else{
		            				sFeedback = "Sorry That is not what i'm asking";
		            			}
		            			transition = true;
		            			changeToError(sAnswer);
		            		}
	            			userRecordOperator.close();
	                		userLessonProgressOperator.close();
	            		}
	            	}
	            	else if(inBounds(event, pDoorH.getX(), pDoorH.getY(), doorholder.getWidth(), doorholder.getHeight())){
	            		Log.d("Debug GameScreen", "DoorHolder: " + event.x + ", " + event.y);
	            		
	            		if(answer > 0 && !pDoor.isPlaced()){
	            			userRecordOperator.open();
	                		userLessonProgressOperator.open();
	                		Log.d("Debug Error", "answer: " + answer + "sAnswer: " + sAnswer );
	            			if(eval.evaluateAnswer(correctAnswer, sAnswer, userID)  && sAnswer == "Pinto"){
	            				door = Assets.door;
		            			Log.d("Debug GameScreen", "CORRECT THATS A door");
		            			Log.d("Debug Feedback", "getID" + lesson.getItems().get(rounds-1).getID());
		            			sFeedback =  eval.getImmediateFeedback(items.get(rounds-1).getID(), sAnswer, lessonNumber);
		            			nItemsRemaining--;
		            			rounds++;
		            			pDoor.move(pDoorH.getX() , pDoorH.getY());
		            			pDoor.setPlaced(true);
		            			transition = true;
		            		}else{
		            			if(answer == lesson.getItems().get(rounds-1).getID()){
		            				sFeedback =  eval.getImmediateFeedback(3, sAnswer, lessonNumber);
		            			}else{
		            				sFeedback = "Sorry That is not what i'm asking";
		            			}
		            			Log.d("Debug Error", "Mali");
		            			transition = true;
		            			changeToError(sAnswer);
		            		}
	            			userRecordOperator.close();
	                		userLessonProgressOperator.close();
	            		}
	            	}
	            	
	            	else if(inBounds(event, pBodyH.getX(), pBodyH.getY(), bodyholder.getWidth(), bodyholder.getHeight())){
	            		Log.d("Debug GameScreen", "Body Holder: " + event.x + ", " + event.y);
	            		
	            		
	            		if(answer > 0 && !pBody.isPlaced()){
	            			userRecordOperator.open();
	                		userLessonProgressOperator.open();
	                		Log.d("Debug Error", "answer: " + answer + "sAnswer: " + sAnswer );
	            			if(eval.evaluateAnswer(correctAnswer, sAnswer, userID) && sAnswer == "Dingding"){
	            				body = Assets.body;
		            			Log.d("Debug GameScreen", "CORRECT THATS A BODY");
		            			Log.d("Debug Feedback", "getID" + lesson.getItems().get(rounds-1).getID());
		            			sFeedback =  eval.getImmediateFeedback(lesson.getItems().get(rounds-1).getID(), sAnswer, lessonNumber);
		            			nItemsRemaining--;
		            			rounds++;
		            			pBody.move(pBodyH.getX() , pBodyH.getY());
		            			pBody.setPlaced(true);
		            			transition = true;
		            			
		            		}else{
		            			if(answer == lesson.getItems().get(rounds-1).getID()){
		            				sFeedback =  eval.getImmediateFeedback(2, sAnswer, lessonNumber);
		            			}else{
		            				sFeedback = "Sorry That is not what i'm asking";
		            			}
		            			Log.d("Debug Error", "Mali");
		            			transition = true;
		            			changeToError(sAnswer);
		            		}
	            			userRecordOperator.close();
	                		userLessonProgressOperator.close();
	            		}
	            	} 
	            	
	            	
	            	if(inBounds(event, pRoof.getX(), pRoof.getY(), roof.getWidth(), roof.getHeight())){
	            		Log.d("Debug GameScreen", "Click roof");
	            		if(pRoof.isPlaced() == false){
	            			answer = 1;
	            			sAnswer = "Bubong";
	            			v_bubong.play(0.85f);
	            			roof = Assets.roof_selected;
	            			Log.d("Select", "Selected Roof");
	            		}else{
	            			answer = 0;
	            		}
	            	}	else if(inBounds(event, pBody.getX(), pBody.getY(), body.getWidth(), body.getHeight())){
	            		Log.d("Debug GameScreen", "Click body");
	            		if(pBody.isPlaced() == false){
	            			answer = 2;
	            			sAnswer = "Dingding";
	            			v_dingding.play(0.85f);
	            			body = Assets.body_selected;
	            			Log.d("Select", "Selected Body");
	            		}else{
	            			answer = 0;
	            		}
	            	}	else if(inBounds(event, pDoor.getX(), pDoor.getY(), door.getWidth(), door.getHeight())){
	            		Log.d("Debug GameScreen", "Click door");
	            		if(pDoor.isPlaced() == false){
	            			answer = 3;
	            			sAnswer = "Pinto";
	            			v_pinto.play(0.85f);
	            			door = Assets.door_selected;
	            			Log.d("Select", "Selected Door");
	            		}else{
	            			answer = 0;
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
	            		}else{
	            			answer = 0;
	            		}
	            	}else{
	            		if(activityLevel=="EASY"){
	            			answer = 0;
	            		}
	            	}
	            }//else
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
	            	if(transition){
	            		if(inBounds(event, p_nextBtn.getX() ,p_nextBtn.getY() , nextBtn.getWidth(), nextBtn.getHeight())){
	            			Log.d("Transition Debug", "Falseing in medium");
	   				  		transition = false;
	            		}
	            	}else{
	            	Log.d("Debug Medium", "Question: " + sQuestion);
	            	Log.d("Debug Medium", "Correct Answer: " + correctAnswer);
	            	Log.d("Touched Down", "X: " + event.x + "Y: " + event.y );
	            	
	            	if(!pFence.isPlaced()){
	    				fence = Assets.fence_choice;
	    				Log.d("Select", "Choice Fence");
	    			}else{
	    				fence = Assets.fence;
	    			}
	    			if(!pGarage.isPlaced()){
	    				garage = Assets.garage_choice;
	    				Log.d("Select", "Choice Garage");
	    			}else{
	    				garage = Assets.garage;
	    			}
	    			
            		if(inBounds(event, pGarageH.getX() ,pGarageH.getY() , garageholder.getWidth(), garageholder.getHeight())){
	            		Log.d("Debug GameScreen", "Garage Holder: " + event.x + ", " + event.y);
	            		Log.d("Debug Medium", "Answer Int: " + answer);
	            		Log.d("Debug Medium", "Answer: " + sAnswer);
	            		if(answer > 0 && !pGarage.isPlaced()){
	            			Log.d("Debug Medium", "There is an answer >0");
	            			userRecordOperator.open();
	                		userLessonProgressOperator.open();
	            			if(eval.evaluateAnswer(correctAnswer, sAnswer, userID) && sAnswer == "Garahe"){
		            			Log.d("Debug GameScreen", "CORRECT THATS A GARAGE");
		            			Log.d("Debug Feedback", "getID" + lesson.getItems().get(rounds-1).getID());
		            			garage = Assets.garage;
		            			sFeedback =  eval.getImmediateFeedback(lesson.getItems().get(rounds-1).getID(), sAnswer, lessonNumber);
		            			nItemsRemaining--;
		            			rounds++;
		            			pGarage.move(pGarageH.getX() , pGarageH.getY());
		            			pGarage.setPlaced(true);
		            			transition = true;
		            		}else{
		            			if(answer == lesson.getItems().get(rounds-1).getID()){
		            				sFeedback =  eval.getImmediateFeedback(5, sAnswer, lessonNumber);
		            			}else{
		            				sFeedback = "Sorry That is not what i'm asking";
		            			}
		            			Log.d("Debug Error", "Mali");
		            			transition = true;
		            			changeToError(sAnswer);
		            			
		            		}
	            			userRecordOperator.close();
	                		userLessonProgressOperator.close();
	            		}
	            	}else if(inBounds(event, pFenceH.getX(), pFenceH.getY(), fenceholder.getWidth(), fenceholder.getHeight())){
	            		Log.d("Debug GameScreen", "fence Holder: " + event.x + ", " + event.y);

	            		if(answer > 0 && !pFence.isPlaced()){
	            			userRecordOperator.open();
	                		userLessonProgressOperator.open();
	            			if(eval.evaluateAnswer(correctAnswer, sAnswer, userID) && sAnswer == "Bakod"){
		            			Log.d("Debug GameScreen", "CORRECT THATS A GARAGE");
		            			Log.d("Debug Feedback", "getID" + lesson.getItems().get(rounds-1).getID());
		            			fence = Assets.fence;
		            			sFeedback =  eval.getImmediateFeedback(lesson.getItems().get(rounds-1).getID(), sAnswer, lessonNumber);
		            			nItemsRemaining--;
		            			rounds++;
		            			pFence.move(pFenceH.getX() , pFenceH.getY());
		            			pFence.setPlaced(true);
		            			transition = true;
		            		}else{
		            			if(answer == lesson.getItems().get(rounds-1).getID()){
		            				sFeedback =  eval.getImmediateFeedback(6, sAnswer, lessonNumber);
		            			}else{
		            				sFeedback = "Sorry That is not what i'm asking";
		            			}
		            			Log.d("Debug Error", "Mali");
		            			transition = true;
		            			changeToError(sAnswer);
		            			
		            		}
	            			userRecordOperator.close();
	                		userLessonProgressOperator.close();
	            		}
	            	}
	            	else{
	            		Log.d("Debug GameScreen","NONE: " + event.x + ", " + event.y);
	            	}
            		
            		
            		
            	 if(inBounds(event, pGarage.getX(), pGarage.getY(), garage.getWidth(), garage.getHeight())){
            		if(pGarage.isPlaced() == false){
            			Log.d("Debug GameScreen", "DRAGS garage");
            			answer = 5;
            			sAnswer = "Garahe";
            			garage = Assets.garage_selected;
            			v_garahe.play(0.85f);
            		}else{
            			answer = 0;
            		}
            	}
        	 	else if(inBounds(event, pFence.getX(), pFence.getY(), fence.getWidth(), fence.getHeight())){
            		
            		if(pFence.isPlaced() == false){
            			Log.d("Debug GameScreen", "DRAGS fence");
            			answer = 6;
            			sAnswer = "Bakod";
            			fence = Assets.fence_selected;
            			v_bakod.play(0.85f);
            		}else{
            			answer = 0;
            		}
            	}else{
            		if(activityLevel=="MEDIUM"){
            			answer = 0;
            		}
            	}
	            
	            
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
	            if (event.type == TouchEvent.TOUCH_DOWN) {
	            	//super.transitionTouchEvent(event);
	            	if(transition){
	            		if(inBounds(event, p_nextBtn.getX() ,p_nextBtn.getY() , nextBtn.getWidth(), nextBtn.getHeight())){
	            			Log.d("Transition Debug", "Falseing in medium");
	   				  		transition = false;
	            		}
	            	}
	            	Log.d("Touched Down", "X: " + event.x + "Y: " + event.y );
	            	if(!pChimney.isPlaced()){
	    				chimney = Assets.chimney_choice;
	    				Log.d("Select", "Choice chimney");
	    			}else{
	    				chimney = Assets.chimney;
	    			}
	    			if(!pStairs.isPlaced()){
	    				stairs = Assets.stairs_choice;
	    				Log.d("Select", "Choice stairs");
	    			}else{
	    				stairs = Assets.stairs;
	    			}
	    			
	    			if(inBounds(event, pChimneyH.getX() ,pChimneyH.getY() , chimneyholder.getWidth(), chimneyholder.getHeight())){
	    				Log.d("Debug GameScreen", "chimney Holder: " + event.x + ", " + event.y);
	    				if(answer > 0 && !pChimney.isPlaced()){
	            			userRecordOperator.open();
	                		userLessonProgressOperator.open();
	                		Log.d("Debug Error", "answer: " + answer + "sAnswer: " + sAnswer );
	            			if(eval.evaluateAnswer(correctAnswer, sAnswer, userID) && sAnswer == "Tsimenea" ){
	            				chimney = Assets.chimney;
		            			Log.d("Debug Error", "CORRECT THATS A Bubong");
		            			Log.d("Debug Feedback", "getID" + lesson.getItems().get(rounds-1).getID());
		            			sFeedback =  eval.getImmediateFeedback(lesson.getItems().get(rounds-1).getID(), sAnswer, lessonNumber);
		            			nItemsRemaining--;
		            			rounds++;
		            			pChimney.move(pChimneyH.getX() , pChimneyH.getY());
		            			pChimney.setPlaced(true);
		            			Log.d("Debug GameScreen EVENT","X: " +event.x + "Y: " + event.y);
		            			Log.d("Debug GameScreen pChimney","X: " +pChimney.getX() + "Y: " + pChimney.getY());	
		            			transition = true;
		            		}else{
		            			if(answer == lesson.getItems().get(rounds-1).getID()){
		            				sFeedback =  eval.getImmediateFeedback(7, sAnswer, lessonNumber);
		            			}else{
		            				sFeedback = "Sorry That is not what i'm asking";
		            			}
		            			Log.d("Debug Error", "Mali");
		            			transition = true;
		            			changeToError(sAnswer);
		            		}
	            			userRecordOperator.close();
	                		userLessonProgressOperator.close();
	            		}//If there is answer
	    			}//Chimney Click
	    			else if(inBounds(event, pStairsH.getX() ,pStairsH.getY() , stairsholder.getWidth(), stairsholder.getHeight())){
	    				Log.d("Debug GameScreen", "stairs Holder: " + event.x + ", " + event.y);
	    				if(answer > 0 && !pStairs.isPlaced()){
	            			userRecordOperator.open();
	                		userLessonProgressOperator.open();
	                		Log.d("Debug Error", "answer: " + answer + "sAnswer: " + sAnswer );
	            			if(eval.evaluateAnswer(correctAnswer, sAnswer, userID) && sAnswer == "Hagdan" ){
	            				stairs = Assets.stairs;
		            			Log.d("Debug Error", "CORRECT THATS A Bubong");
		            			Log.d("Debug Feedback", "getID" + lesson.getItems().get(rounds-1).getID());
		            			sFeedback =  eval.getImmediateFeedback(lesson.getItems().get(rounds-1).getID(), sAnswer, lessonNumber);
		            			nItemsRemaining--;
		            			rounds++;
		            			pStairs.move(pStairsH.getX() , pStairsH.getY());
		            			pStairs.setPlaced(true);
		            			Log.d("Debug GameScreen EVENT","X: " +event.x + "Y: " + event.y);
		            			Log.d("Debug GameScreen pChimney","X: " +pStairs.getX() + "Y: " + pStairs.getY());	
		            			transition = true;
		            		}else{
		            			if(answer == lesson.getItems().get(rounds-1).getID()){
		            				sFeedback =  eval.getImmediateFeedback(8, sAnswer, lessonNumber);
		            			}else{
		            				sFeedback = "Sorry That is not what i'm asking";
		            			}
		            			Log.d("Debug Error", "Mali");
		            			transition = true;
		            			changeToError(sAnswer);
		            		}
	            			userRecordOperator.close();
	                		userLessonProgressOperator.close();
	            		}//If there is answer
	    			}//Chimney Click
	    			
	    			if(inBounds(event, pChimney.getX(), pChimney.getY(), chimney.getWidth(), chimney.getHeight())){
	            		Log.d("Debug GameScreen", "Click Chimney");
	            		if(pChimney.isPlaced() == false){
	            			answer = 7;
	            			sAnswer = "Tsimenea";
	            			v_chimnea.play(0.85f);
	            			chimney = Assets.chimney_selected;
	            			Log.d("Select", "Selected chimney");
	            		}else{
	            			answer = 0;
	            		}
	            	}else if(inBounds(event, pStairs.getX(), pStairs.getY(), stairs.getWidth(), stairs.getHeight())){
	            		Log.d("Debug GameScreen", "Click stairs");
	            		if(pStairs.isPlaced() == false){
	            			answer = 8;
	            			sAnswer = "Hagdan";
	            			v_hagdan.play(0.85f);
	            			stairs = Assets.stairs_selected;
	            			Log.d("Select", "Selected stairs");
	            		}else{
	            			answer = 0;
	            		}
	            	}else{
	            		if(activityLevel=="HARD"){
	            			answer = 0;
	            		}
	            	}	
	    			
	    			
	            }//Touch Down
	        }//Loop
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
			 showTransition();
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
		        g.drawImage(roofholder, pRoofH.getX(), pRoofH.getY());
		        g.drawImage(bodyholder, pBodyH.getX(), pBodyH.getY());
		        g.drawImage(doorholder, pDoorH.getX(), pDoorH.getY());
		        g.drawImage(windowholder, pWindowH.getX(), pWindowH.getY());
		        g.drawImage(fenceholder, pFenceH.getX(), pFenceH.getY());
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
			 painterMedium();
			 g.drawImage(stairsholder, pStairsH.getX(), pStairsH.getY());
		     g.drawImage(chimneyholder, pChimneyH.getX(), pChimneyH.getY());
			
			 g.drawImage(stairs, pStairs.getX(), pStairs.getY());
		     g.drawImage(chimney, pChimney.getX(), pChimney.getY());
		}

		
		String[] cuttedWord;
		@Override
		protected void showTransition() {
			// TODO Auto-generated method stub
			Graphics g = game.getGraphics();
			
			if(super.transition){
				Log.d("Transition Debug", "Enters: Knock Knock");
				  g.drawARGB(200, 0, 0, 0);
				  g.drawImage(feedboxBoy, this.pDialog.getX(), pDialog.getY());
				  
				  String lineOne = "", lineTwo ="";
				  cuttedWord = sentenceCutter(sFeedback);
				  for(int s = 0; s<cuttedWord.length;s++){
						if(s>8){
							lineTwo += cuttedWord[s] + " ";
						}else{
							lineOne += cuttedWord[s]+ " ";
						}
					}
				  g.drawString(lineOne, 165,63, paint);
				  g.drawString(lineTwo, 165,83, paint);
				  //g.drawString(sFeedback, 165, 63, paint);
				  g.drawImage(nextBtn, p_nextBtn.getX(), p_nextBtn.getY());
				  switch(answer){
				  case 1: g.drawImage(roof, pRoof.getX(), pRoof.getY());break;
				  case 2: g.drawImage(body, pBody.getX(), pBody.getY());break;
				  case 3: g.drawImage(door, pDoor.getX(), pDoor.getY());break;
				  case 4: g.drawImage(window, pWindow.getX(), pWindow.getY());break;
				  case 5: g.drawImage(garage, pGarage.getX(), pGarage.getY());break;
				  case 6: g.drawImage(fence, pFence.getX(), pFence.getY());break;
				  case 7: g.drawImage(chimney, pChimney.getX(), pChimney.getY());break;
				  case 8: g.drawImage(stairs, pStairs.getX(), pStairs.getY());break;
				  
				  }
			}
		}
		private String[] sentenceCutter(String sentence){
			  String[] words;
			  words = sentence.split(" ");
			  
			  return words;
		}

		@Override
		protected void drawReadyUI() {
			// TODO Auto-generated method stub
			 Graphics g = game.getGraphics();
			 g.drawARGB(200, 0, 0, 0);
			 g.drawImage(feedboxBoy, this.pDialog.getX(), pDialog.getY());
			 g.drawString("Tap the pieces asked and place it", 322, 63, paint4);
			 g.drawString("on its proper position", 322, 78, paint4);
			 g.drawImage(nextBtn, p_nextBtn.getX(), p_nextBtn.getY());
		}

		@Override
		protected void showExit() {
			// TODO Auto-generated method stub
			Graphics g = game.getGraphics();
			if(exit){
			g.drawARGB(200, 0, 0, 0);
			 g.drawImage(bgBack, this.pBackg.getX(), pBackg.getY());
			 g.drawImage(nobtn, this.pNo.getX(), pNo.getY());
			 g.drawImage(yesbtn, pYes.getX(), pYes.getY());
			}
		}

		@Override
		protected void drawCustomUI() {
			// TODO Auto-generated method stub
			  Graphics g = game.getGraphics();
			  g.drawImage(backbtn, 1, 1);
		        g.drawString(sQuestion, 97,90, paint);
		        showTransition();
		        showExit();
		}

	   
}
