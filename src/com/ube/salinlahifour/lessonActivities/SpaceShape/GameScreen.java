package com.ube.salinlahifour.lessonActivities.SpaceShape;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
import com.ube.salinlahifour.lessonActivities.SpaceShape.Parts;
import com.ube.salinlahifour.lessonActivities.SpaceShape.Assets;
import com.ube.salinlahifour.database.UserDetailOperations;
import com.ube.salinlahifour.database.UserLessonProgressOperations;
import com.ube.salinlahifour.database.UserRecordOperations;


public class GameScreen extends AbstractGameScreen  {
	
	    // Variable Setup
	    // You would create game objects here.
	    static String activityName = "Shape";
	    
	    //String activityLevel;
	    private Image backbtn, nobtn, yesbtn, bgBack;
	    private Image bg, feedboxBoy, feedboxGirl,nextBtn, tooltip;
	    private Parts pDialog,p_nextBtn, pTooltip;
	    // Edit lives left to the question size
	    private Image spaceship, enemy, projectile, wrong;
	    private Parts pSpaceship, pEnemy,pWrong;
	    private Image lives;
		private Parts pLives;
	    private Image circle,square,star,triangle;
	    private Parts pCircle,pSquare,pStar,pTriangle;
	    public static Sound v_bilog,v_parisukat, v_bituin, v_tatsulok;
	    
	    private Image cross,diamond,rectangle;
	    private Parts pCross,pDiamond,pRectangle;
	    public static Sound v_krus,v_diamante, v_parihaba;
	    
	    private Image arrow,cresent,heart;
	    private Parts pArrow,pCresent,pHeart;
	    public static Sound v_tunod,v_gasuklay, v_puso;
	    private Parts pNo,pYes,pBackg;
	    private EnemyList enemies;
	    private Projectile ammo;
	    private boolean isEnemyExist = false;
	   int index = 0;
	    public GameScreen(Game game, String activityLevel, int userID, Context context, Lesson lesson, ArrayList<Item> items, Evaluation evals) {
	    	//Super Parameters Game, ActivityName, ActivityLevel, UserID
	        super(game, activityName, activityLevel, userID, context,lesson, evals);
	        Log.d("Aldrin ExtendedFramework", "This should be after abstract Game");
	        this.userID = userID;
	        this.activityLevel = activityLevel;
	        Log.d("Aldrin ExtendedFramework", "Gamescreen constructor...");
	        lessonNumber = 4;
	        super.items = items;
	    }

		@Override
		protected void loadAssets() {
			// TODO Auto-generated method stub
	        Log.d("Aldrin ExtendedFramework", "Loading Assets");
	        Log.d("Lives", "Starting Life: " + eval.getMistakesRemaining());
	        this.backbtn = Assets.backbtn;
	        this.bgBack = Assets.bgBack;
	        this.yesbtn = Assets.yesbtn;
	        this.nobtn = Assets.nobtn;
	        nextBtn = Assets.nextBtn;
	        p_nextBtn = new Parts(250,180);
	        pDialog = new Parts(85,15);
	        feedboxBoy = Assets.feedboxBoy;
        	feedboxGirl = Assets.feedboxGirl;
        	tooltip = Assets.tooltip;
	        enemies = new EnemyList(activityLevel);
	        enemies.loadEnemy();
	        bg = Assets.bg;
	        circle = Assets.circle;
	        square = Assets.square;
	        star = Assets.star;
	        triangle = Assets.triangle;
	        
	        cross = Assets.cross;
	        diamond = Assets.diamond;
	        rectangle = Assets.rectangle;
	        
	        arrow = Assets.arrow;
	        cresent = Assets.cresent;
	        heart = Assets.heart;
	        
	        enemy = Assets.nothingness;
	        spaceship = Assets.spaceship;
	        projectile = Assets.nothingness;
	        wrong = Assets.nothingness;
	        ammo = new Projectile();
	        
	        lives = Assets.lives.get(4);
	        v_bilog = Assets.bilog;
	        v_bituin = Assets.bituin;
	        v_parisukat = Assets.parisukat;
	        v_tatsulok = Assets.tatsulok;
	        v_krus = Assets.krus;
	        v_diamante = Assets.diamante;
	        v_parihaba = Assets.parihaba;
	        v_tunod = Assets.tunod;
	        v_gasuklay = Assets.gasuklay;
	        v_puso = Assets.puso;
	        
		    Log.d("Aldrin ExtendedFramework", "Loading Assets...Done");
		}

		@Override
		protected void assetPositionEasy() {
			// TODO Auto-generated method stub
	        Log.d("Aldrin ExtendedFramework", "Positioning Easy Assets");
	        
	        pTooltip = new Parts(230-30,320-30);
	        pCircle = new Parts (120,415);
	        pSquare= new Parts (495,415);
	        pStar= new Parts (385,390);
	        pTriangle = new Parts (247,385);
	        
	        pSpaceship = new Parts(310,290);
	        pEnemy = new Parts(200,100);
	        nItemsRemaining = 10;
	        //eval.setTotScore(nItemsRemaining);
	        pWrong = new Parts(0,0);
	        ammo.loadAmmos(pSpaceship.getX(), pSpaceship.getY());
	        pLives = new Parts(130-30,480-30);
	      
	        pYes = new Parts(220,300);
	        pNo = new Parts(400,300);
	        pBackg = new Parts(195, 100);
	        Log.d("Aldrin ExtendedFramework", "Positioning Easy Assets...Done");
		}

		@Override
		protected void assetPositionMedium() {
			// TODO Auto-generated method stub
		   Log.d("GameScreen", "Positioning Medium"); 
			assetPositionEasy();
			  
	        pCross = new Parts(75 - 30, 340-30);
	        pDiamond = new Parts(75 - 30,260-30);
	        pRectangle = new Parts(5,185-30);
	        
	        nItemsRemaining  = 15;

	        //eval.setTotScore(nItemsRemaining);
 	       Log.d("GameScreen", "Positioning Medium...Done"); 
		}
		
		@Override
		protected void assetPositionHard() {
			// TODO Auto-generated method stub
			assetPositionMedium();
			
			pArrow = new Parts(570-30, 340-30);
	        pCresent = new Parts(570-30,260-30);
	        pHeart = new Parts(570-30, 180-30);
	        nItemsRemaining = 20;
	        //eval.setTotScore(nItemsRemaining);
		}
		private void spawnEnemy(int size){
			Random rand = new Random();
			isEnemyExist = true;
			index = rand.nextInt(size);
			if(index <size){
			this.resetButtonStates();
			//enemyInfo = enemies.getEnemy(index);
			if(index == 0){
				index = 1;
			}
			sQuestion = lesson.getItems().get(index-1).getQuestion();
			//sQuestion = items.get(index).getLabel();
			//cAnswer = enemies.getEnemy(index);
			cAnswer = lesson.getItems().get(index-1).getWord();
			Log.d("Enemies Size", "Size: " + Assets.enemyShapes.size());
			enemy = Assets.enemyShapes.get(index-1);
			Log.d("Enemy Index: ", "i: " + index + " enemy formation: " + enemies.getEnemy(index)  );
			}
		}
		
		@Override
		protected void updateRunningEasy(List<TouchEvent> touchEvents,float deltaTime) {
			// TODO Auto-generated method stub
			int len = touchEvents.size();
			if(isEnemyExist == false){
				spawnEnemy(4);
				ammo.setIsHit(false);
				ammo.lockTarget(pEnemy.getX(), pEnemy.getY(), enemy.getWidth(), enemy.getHeight());
			}
			if(ammo.isHit() == true){
				isEnemyExist = false;
				projectile = Assets.nothingness;
			}
			switch(eval.getMistakesRemaining()){
			case 4:lives = Assets.lives.get(4);;break;
			case 3:lives = Assets.lives.get(3);;break;
			case 2:lives = Assets.lives.get(2);;break;
			case 1:lives = Assets.lives.get(1);;break;
			case 0:lives = Assets.lives.get(0);;break;
			}
	        for (int i = 0; i < len; i++) {
	            TouchEvent event = touchEvents.get(i);
	            
	            /////////////////////////////////////////////
	            if (event.type == TouchEvent.TOUCH_DOWN) { //Happens When you press a specifi
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
	            	wrong = Assets.nothingness;
	            	if(transition){
	            		if(inBounds(event, p_nextBtn.getX() ,p_nextBtn.getY() , nextBtn.getWidth(), nextBtn.getHeight())){
	            		Log.d("Transition Debug", "Falseing in easy");
	   				  	transition = false;
	            		}
	            	}else{
	            	
	            	Log.d("Touched Down", "X: " + event.x + "Y: " + event.y );
	            	//ammo.shoot();
	            	if(inBounds(event, pCircle.getX() ,pCircle.getY() , circle.getWidth(), circle.getHeight())){
	            		Log.d("Pew", "Pewpew Circle");
	            		circle = Assets.circleP;
	            		v_bilog.play(0.85f);
	            	}else if(inBounds(event, pSquare.getX() ,pSquare.getY() , square.getWidth(), square.getHeight())){
	            		Log.d("Pew", "Pewpew Square");
	            		square = Assets.squareP;
	            		v_parisukat.play(0.85f);
	            	}else if(inBounds(event, pStar.getX() ,pStar.getY() , star.getWidth(), star.getHeight())){
	            		Log.d("Pew", "Pewpew Star");
	            		star = Assets.starP;
	            		v_bituin.play(0.85f);
	            	}else if(inBounds(event, pTriangle.getX() ,pTriangle.getY() , triangle.getWidth(), triangle.getHeight())){
	            		Log.d("Pew", "Pewpew Triangle");
	            		triangle = Assets.triangleP;
	            		v_tatsulok.play(0.85f);
	            	}
	            }
	            }
	
	            if (event.type == TouchEvent.TOUCH_UP) {
	            	userRecordOperator.open();
        			userLessonProgressOperator.open();
        			
	            	if(inBounds(event, pCircle.getX() ,pCircle.getY() , circle.getWidth(), circle.getHeight())){
	            		Log.d("Pew", "Pewpew Circle");
	            		circle = Assets.circle;
	            		//projectile = Assets.circleP;
	            		sAnswer = "Bilog";
	            		Log.d("Feedback debug", "Correct Answer: " + cAnswer);
	            		Log.d("Feedback debug", "User Answer: " + sAnswer);
	            		Log.d("Feedback debug", "Index: " + index);
	            		
	            		if(eval.evaluateAnswer(cAnswer, sAnswer, userID)){
	            			Log.d("Feedback debug", "Evaluation true");
	            			if(index == 0){
	            				index=1;
	            			}
	            			sFeedback =  eval.getImmediateFeedback(index, sAnswer, lessonNumber);
	            			isEnemyExist = false;
	            			nItemsRemaining--;
	            			rounds++;
	            			transition = true;
	            		}else{
	            			Log.d("Feedback debug", "Evaluation false");
	            			//wrong = Assets.wrong;
	            			//pWrong.move(pCircle.getX(), pCircle.getY());
	            			circle = Assets.circle_error;
	            			sFeedback =  eval.getImmediateFeedback(index, sAnswer, lessonNumber);
	            			transition = true;
	            		}
	            	}else if(inBounds(event, pSquare.getX() ,pSquare.getY() , square.getWidth(), square.getHeight())){
	            		Log.d("Pew", "Pewpew Square");
	            		square = Assets.square;
	            		sAnswer="Parisukat";
	            		Log.d("Feedback debug", "Correct Answer: " + cAnswer);
	            		Log.d("Feedback debug", "User Answer: " + sAnswer);
	            		Log.d("Feedback debug", "Index: " + index);
	            		if(eval.evaluateAnswer(cAnswer, sAnswer, userID)){
	            			Log.d("Feedback debug", "Evaluation true");
	            			sFeedback =  eval.getImmediateFeedback(index, sAnswer, lessonNumber);
	            			isEnemyExist = false;
	            			nItemsRemaining--;
	            			rounds++;
	            			transition = true;
	            		}else{
	            			Log.d("Feedback debug", "Evaluation false");
	            			sFeedback =  eval.getImmediateFeedback(index, sAnswer, lessonNumber);
	            			//wrong = Assets.wrong;
	            			//pWrong.move(pSquare.getX(), pSquare.getY());
	            			square = Assets.square_error;
	            			transition = true;
	            		}
	            	}else if(inBounds(event, pStar.getX() ,pStar.getY() , star.getWidth(), star.getHeight())){
	            		Log.d("Pew", "Pewpew Star");
	            		star = Assets.star;
	            		sAnswer = "Bituin";
	            		Log.d("Feedback debug", "Correct Answer: " + cAnswer);
	            		Log.d("Feedback debug", "User Answer: " + sAnswer);
	            		Log.d("Feedback debug", "Index: " + index);
	            		Log.d("Answered", cAnswer + " " +sAnswer);
	            		if(eval.evaluateAnswer(cAnswer, sAnswer, userID)){
	            			Log.d("Feedback debug", "Evaluation true");
	            			sFeedback =  eval.getImmediateFeedback(index, sAnswer, lessonNumber);
	            			isEnemyExist = false;
	            			nItemsRemaining--;
	            			rounds++;
	            			transition = true;
	            		}else{
	            			Log.d("Feedback debug", "Evaluation false");
	            			sFeedback =  eval.getImmediateFeedback(index, sAnswer, lessonNumber);
	            			//wrong = Assets.wrong;
	            			//pWrong.move(pStar.getX(), pStar.getY());
	            			star = Assets.star_error;
	            			transition = true;
	            		}
	            	}else if(inBounds(event, pTriangle.getX() ,pTriangle.getY() , triangle.getWidth(), triangle.getHeight())){
	            		Log.d("Pew", "Pewpew Triangle");
	            		triangle = Assets.triangle;
	            		sAnswer = "Tatsulok";
	            		Log.d("Feedback debug", "Correct Answer: " + cAnswer);
	            		Log.d("Feedback debug", "User Answer: " + sAnswer);
	            		Log.d("Feedback debug", "Index: " + index);
	            		if(eval.evaluateAnswer(cAnswer, sAnswer, userID)){
	            			Log.d("Feedback debug", "Evaluation true");
	            			sFeedback =  eval.getImmediateFeedback(index, sAnswer, lessonNumber);
	            			isEnemyExist = false;
	            			nItemsRemaining--;
	            			rounds++;
	            			transition = true;
	            		}else{
	            			Log.d("Feedback debug", "Evaluation false");
	            			sFeedback =  eval.getImmediateFeedback(index, sAnswer, lessonNumber);
	            			//wrong = Assets.wrong;
	            			//pWrong.move(pTriangle.getX(), pTriangle.getY());
	            			triangle = Assets.triangle_error;
	            			transition = true;
	            		}
	            	}
	            	userRecordOperator.close();
        			userLessonProgressOperator.close();
	            }
	            if (event.type == TouchEvent.TOUCH_DRAGGED) {
	            }
	        }
		}

		@Override
		protected void updateRunningMedium(List<TouchEvent> touchEvents,float deltaTime) {
			// TODO Auto-generated method stub
			updateRunningEasy(touchEvents, deltaTime);
			int len = touchEvents.size();
			if(isEnemyExist == false){
				spawnEnemy(7);
			}
	        for (int i = 0; i < len; i++) {
	            TouchEvent event = touchEvents.get(i);
	            
	            /////////////////////////////////////////////
	            if (event.type == TouchEvent.TOUCH_DOWN) {
	            	if(transition){
	            		if(inBounds(event, p_nextBtn.getX() ,p_nextBtn.getY() , nextBtn.getWidth(), nextBtn.getHeight())){
	            		Log.d("Transition Debug", "Falseing in easy");
	   				  	transition = false;
	            		}
	            	}else{
	            	Log.d("Touched Down", "X: " + event.x + "Y: " + event.y );
	            	wrong = Assets.nothingness;
	            	Log.d("Touched Down", "X: " + event.x + "Y: " + event.y );
	            	//ammo.shoot();
	            	if(inBounds(event, pCross.getX() ,pCross.getY() , cross.getWidth(), cross.getHeight())){
	            		Log.d("Pew", "Pewpew cross");
	            		cross = Assets.crossP;
	            		v_krus.play(0.85f);
	            	}else if(inBounds(event, pDiamond.getX() ,pDiamond.getY() , diamond.getWidth(), diamond.getHeight())){
	            		Log.d("Pew", "Pewpew diamond");
	            		diamond = Assets.diamondP;
	            		v_diamante.play(0.85f);
	            	}else if(inBounds(event, pRectangle.getX() ,pRectangle.getY() , rectangle.getWidth(), rectangle.getHeight())){
	            		Log.d("Pew", "Pewpew rectangle");
	            		rectangle = Assets.rectangleP;
	            		v_parihaba.play(0.85f);
	            	}
	            }
		         }
	            if (event.type == TouchEvent.TOUCH_UP) {
	            	userRecordOperator.open();
        			userLessonProgressOperator.open();
        			
	            	if(inBounds(event, pCross.getX() ,pCross.getY() , cross.getWidth(), cross.getHeight())){
	            		Log.d("Pew", "Pewpew cross");
	            		cross = Assets.cross;
	            		//projectile = Assets.circleP;
	            		sAnswer = "Krus";
	            		Log.d("Feedback debug", "Correct Answer: " + cAnswer);
	            		Log.d("Feedback debug", "User Answer: " + sAnswer);
	            		Log.d("Feedback debug", "Index: " + index);
	            		
	            		if(eval.evaluateAnswer(cAnswer, sAnswer, userID)){
	            			Log.d("Feedback debug", "Evaluation true");
	            			if(index == 0){
	            				index=1;
	            			}
	            			sFeedback =  eval.getImmediateFeedback(index, sAnswer, lessonNumber);
	            			isEnemyExist = false;
	            			nItemsRemaining--;
	            			rounds++;
	            			transition = true;
	            		}else{
	            			Log.d("Feedback debug", "Evaluation false");
	            			//wrong = Assets.wrong;
	            			//pWrong.move(pCircle.getX(), pCircle.getY());
	            			cross = Assets.cross_error;
	            			sFeedback =  eval.getImmediateFeedback(index, sAnswer, lessonNumber);
	            			transition = true;
	            		}
	            	}else if(inBounds(event, pDiamond.getX() ,pDiamond.getY() , diamond.getWidth(), diamond.getHeight())){
	            		Log.d("Pew", "Pewpew diamond");
	            		diamond = Assets.diamond;
	            		sAnswer="Diamante";
	            		Log.d("Feedback debug", "Correct Answer: " + cAnswer);
	            		Log.d("Feedback debug", "User Answer: " + sAnswer);
	            		Log.d("Feedback debug", "Index: " + index);
	            		if(eval.evaluateAnswer(cAnswer, sAnswer, userID)){
	            			Log.d("Feedback debug", "Evaluation true");
	            			sFeedback =  eval.getImmediateFeedback(index, sAnswer, lessonNumber);
	            			isEnemyExist = false;
	            			nItemsRemaining--;
	            			rounds++;
	            			transition = true;
	            		}else{
	            			Log.d("Feedback debug", "Evaluation false");
	            			sFeedback =  eval.getImmediateFeedback(index, sAnswer, lessonNumber);
	            			//wrong = Assets.wrong;
	            			//pWrong.move(pSquare.getX(), pSquare.getY());
	            			diamond = Assets.diamond_error;
	            			transition = true;
	            		}
	            	}else if(inBounds(event, pRectangle.getX() ,pRectangle.getY() , rectangle.getWidth(), rectangle.getHeight())){
	            		Log.d("Pew", "Pewpew rectangle");
	            		rectangle = Assets.rectangle;
	            		sAnswer = "Parihaba";
	            		Log.d("Feedback debug", "Correct Answer: " + cAnswer);
	            		Log.d("Feedback debug", "User Answer: " + sAnswer);
	            		Log.d("Feedback debug", "Index: " + index);
	            		Log.d("Answered", cAnswer + " " +sAnswer);
	            		if(eval.evaluateAnswer(cAnswer, sAnswer, userID)){
	            			Log.d("Feedback debug", "Evaluation true");
	            			sFeedback =  eval.getImmediateFeedback(index, sAnswer, lessonNumber);
	            			isEnemyExist = false;
	            			nItemsRemaining--;
	            			rounds++;
	            			transition = true;
	            		}else{
	            			Log.d("Feedback debug", "Evaluation false");
	            			sFeedback =  eval.getImmediateFeedback(index, sAnswer, lessonNumber);
	            		
	            			rectangle = Assets.rectangle_error;
	            			transition = true;
	            		}
	            	}
	            	userRecordOperator.close();
        			userLessonProgressOperator.close();
	            }
	          
	        }
		}

		@Override
		protected void updateRunningHard(List<TouchEvent> touchEvents, float deltaTime) {
			// TODO Auto-generated method stub
			updateRunningMedium(touchEvents, deltaTime);
						int len = touchEvents.size();
						if(isEnemyExist == false){
							spawnEnemy(10);
						}
				        for (int i = 0; i < len; i++) {
				            TouchEvent event = touchEvents.get(i);
				            /////////////////////////////////////////////
				            if (event.type == TouchEvent.TOUCH_DOWN) {
				            	if(transition){
				            		if(inBounds(event, p_nextBtn.getX() ,p_nextBtn.getY() , nextBtn.getWidth(), nextBtn.getHeight())){
				            		Log.d("Transition Debug", "Falseing in easy");
				   				  	transition = false;
				            		}
				            	}else{
				            	Log.d("Touched Down", "X: " + event.x + "Y: " + event.y );
				            	if(inBounds(event, pArrow.getX() ,pArrow.getY() , arrow.getWidth(), arrow.getHeight())){
				            		Log.d("Pew", "Pewpew cross");
				            		arrow = Assets.arrowP;
				            		v_tunod.play(0.85f);
				            	}else if(inBounds(event, pCresent.getX() ,pCresent.getY() , cresent.getWidth(), cresent.getHeight())){
				            		Log.d("Pew", "Pewpew cresent");
				            		cresent = Assets.cresentP;
				            		v_gasuklay.play(0.85f);
				            	}else if(inBounds(event, pHeart.getX() ,pHeart.getY() , heart.getWidth(), heart.getHeight())){
				            		Log.d("Pew", "Pewpew heart");
				            		heart = Assets.heartP;
				            		v_puso.play(0.85f);
				            	}
				            }
					         }
				            if (event.type == TouchEvent.TOUCH_UP) {
				            	userRecordOperator.open();
			        			userLessonProgressOperator.open();
			        			
				            	if(inBounds(event, pArrow.getX() ,pArrow.getY() , arrow.getWidth(), arrow.getHeight())){
				            		Log.d("Pew", "Pewpew arrow");
				            		arrow = Assets.arrow;
				            		//projectile = Assets.circleP;
				            		sAnswer = "Tunod";
				            		Log.d("Feedback debug", "Correct Answer: " + cAnswer);
				            		Log.d("Feedback debug", "User Answer: " + sAnswer);
				            		Log.d("Feedback debug", "Index: " + index);
				            		
				            		if(eval.evaluateAnswer(cAnswer, sAnswer, userID)){
				            			Log.d("Feedback debug", "Evaluation true");
				            			if(index == 0){
				            				index=1;
				            			}
				            			sFeedback =  eval.getImmediateFeedback(index, sAnswer, lessonNumber);
				            			isEnemyExist = false;
				            			nItemsRemaining--;
				            			rounds++;
				            			transition = true;
				            		}else{
				            			Log.d("Feedback debug", "Evaluation false");
				            			//wrong = Assets.wrong;
				            			//pWrong.move(pCircle.getX(), pCircle.getY());
				            			arrow = Assets.arrow_error;
				            			sFeedback =  eval.getImmediateFeedback(index, sAnswer, lessonNumber);
				            			transition = true;
				            		}
				            	}else if(inBounds(event, pCresent.getX() ,pCresent.getY() , cresent.getWidth(), cresent.getHeight())){
				            		Log.d("Pew", "Pewpew cresent");
				            		cresent = Assets.cresent;
				            		sAnswer="Gasuklay";
				            		Log.d("Feedback debug", "Correct Answer: " + cAnswer);
				            		Log.d("Feedback debug", "User Answer: " + sAnswer);
				            		Log.d("Feedback debug", "Index: " + index);
				            		if(eval.evaluateAnswer(cAnswer, sAnswer, userID)){
				            			Log.d("Feedback debug", "Evaluation true");
				            			sFeedback =  eval.getImmediateFeedback(index, sAnswer, lessonNumber);
				            			isEnemyExist = false;
				            			nItemsRemaining--;
				            			rounds++;
				            			transition = true;
				            		}else{
				            			Log.d("Feedback debug", "Evaluation false");
				            			sFeedback =  eval.getImmediateFeedback(index, sAnswer, lessonNumber);
				            			//wrong = Assets.wrong;
				            			//pWrong.move(pSquare.getX(), pSquare.getY());
				            			cresent = Assets.cresent_error;
				            			transition = true;
				            		}
				            	}else if(inBounds(event, pHeart.getX() ,pHeart.getY() , heart.getWidth(), heart.getHeight())){
				            		Log.d("Pew", "Pewpew heart");
				            		heart = Assets.heart;
				            		sAnswer = "Puso";
				            		Log.d("Feedback debug", "Correct Answer: " + cAnswer);
				            		Log.d("Feedback debug", "User Answer: " + sAnswer);
				            		Log.d("Feedback debug", "Index: " + index);
				            		Log.d("Answered", cAnswer + " " +sAnswer);
				            		if(eval.evaluateAnswer(cAnswer, sAnswer, userID)){
				            			Log.d("Feedback debug", "Evaluation true");
				            			sFeedback =  eval.getImmediateFeedback(index, sAnswer, lessonNumber);
				            			isEnemyExist = false;
				            			transition = true;
				            		}else{
				            			Log.d("Feedback debug", "Evaluation false");
				            			sFeedback =  eval.getImmediateFeedback(index, sAnswer, lessonNumber);
				            			heart = Assets.heart_error;
				            			transition = true;
				            		}
				            	}
				            	userRecordOperator.close();
			        			userLessonProgressOperator.close();
				            }
				           
				        }
			}

		private void resetButtonStates(){
			circle = Assets.circle;
			square = Assets.square;
			star = Assets.star;
			triangle = Assets.triangle;
			cross = Assets.cross;
			diamond = Assets.diamond;
			rectangle= Assets.rectangle;
			 arrow= Assets.arrow;
			cresent = Assets.cresent;
			heart = Assets.heart;
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
		    
		        System.gc();

		}

		@Override
		protected void painterEasy() {
			// TODO Auto-generated method stub
			 Graphics g = game.getGraphics();
			 
			 g.drawImage(bg, 0, 0);
			
			 g.drawImage(spaceship, pSpaceship.getX(), pSpaceship.getY());
			 g.drawImage(circle, pCircle.getX(), pCircle.getY());
			 g.drawImage(square, pSquare.getX(), pSquare.getY());
			 g.drawImage(star, pStar.getX(), pStar.getY());
			 g.drawImage(triangle, pTriangle.getX(), pTriangle.getY());
			 g.drawImage(enemy, pEnemy.getX(), pEnemy.getY());
			 g.drawImage(projectile, ammo.getCurX(), ammo.getCurY());
			 g.drawImage(wrong, pWrong.getX(), pWrong.getY());
			 g.drawImage(feedboxBoy, this.pDialog.getX(), pDialog.getY());
			 g.drawImage(lives, pLives.getX(), pLives.getY());
		
			 }
		protected void painterMedium() {
			// TODO Auto-generated method stub
			Graphics g = game.getGraphics();
				painterEasy();
				g.drawImage(cross, pCross.getX(), pCross.getY());
				g.drawImage(diamond, pDiamond.getX(), pDiamond.getY());
				g.drawImage(rectangle, pRectangle.getX(), pRectangle.getY());
				
			}
		protected void painterHard() {
			// TODO Auto-generated method stub
			 Graphics g = game.getGraphics();
			 painterEasy();
			 painterMedium();
			 g.drawImage(arrow, pArrow.getX(), pArrow.getY());
			 g.drawImage(cresent, pCresent.getX(), pCresent.getY());
			 g.drawImage(heart, pHeart.getX(), pHeart.getY());
			}

		String[] cuttedWord;
		@Override
		protected void showTransition() {
			// TODO Auto-generated method stub
			Graphics g = game.getGraphics();
			if(super.transition){
				Log.d("Transition Debug", "Enters: Knock Knock");
				
			 g.drawARGB(155, 0, 0, 0);
			 g.drawImage(feedboxBoy, this.pDialog.getX(), pDialog.getY());
			 g.drawImage(nextBtn, p_nextBtn.getX(), p_nextBtn.getY());
			  String lineOne = "", lineTwo ="";
			  cuttedWord = sentenceCutter(sFeedback);
			  for(int s = 0; s<cuttedWord.length;s++){
					if(s>8){
						lineTwo += cuttedWord[s] + " ";
					}else{
						lineOne += cuttedWord[s]+ " ";
					}
				}
			  g.drawString(lineOne, 350,63, paint4);
			  g.drawString(lineTwo, 350,83, paint4);
			 //g.drawString(sFeedback, 322, 63, paint4);
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
			 g.drawString("Tap 'BOOST' to face the next alien", 322, 63, paint4);
			 g.drawString("Tap the correct button to deafeat it", 322, 78, paint4);
			 g.drawImage(nextBtn, p_nextBtn.getX(), p_nextBtn.getY());
			 g.drawImage(lives, pLives.getX(), pLives.getY());
			 g.drawImage(tooltip, pTooltip.getX(), pTooltip.getY());
			 g.drawString("Aliens will hit us if we miss", 336, 318, paint4);
			 g.drawString("Don't let the lights fade out", 336, 338, paint4);
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
			// g.drawString("sFeedback", 300, 400, paint2);//sFeedback 
			// g.drawString(sFeedback, 545, 40, paint3);//sQuestion
			g.drawString(sQuestion, 350, 63, paint4);//sQuestion
			showTransition();
			showExit();
			
		}

	   
}