package com.ube.salinlahifour.lessonActivities.SpaceShape;


import java.util.List;
import java.util.Random;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Image;
import com.kilobolt.framework.Screen;
import com.kilobolt.framework.Sound;
import com.kilobolt.framework.Input.TouchEvent;
import com.ube.salinlahifour.Lesson;
import com.ube.salinlahifour.SalinlahiFour;
import com.ube.salinlahifour.evaluationModule.*;
import com.ube.salinlahifour.lessonActivities.AbstractGameScreen;
import com.ube.salinlahifour.lessonActivities.SpaceShape.Assets;
import com.ube.salinlahifour.database.UserDetailOperations;
import com.ube.salinlahifour.database.UserLessonProgressOperations;
import com.ube.salinlahifour.database.UserRecordOperations;


public class GameScreen extends AbstractGameScreen  {
	
	    // Variable Setup
	    // You would create game objects here.
	    static String activityName = "Shape";
	    private int lessonNumber = 4;
	    //String activityLevel;
	   
	    private Image bg;
	    // Edit lives left to the question size

		public static Sound v_bilog,v_parisukat, v_bituin, v_tatsulok;
	    private Image circle,square,star,triangle,easy_panel;
	    private Image spaceship, enemy, projectile, wrong;
	    private Parts pEasyPanel, pCircle,pSquare,pStar,pTriangle,pSpaceship, pEnemy,pWrong;
	    private EnemyList enemies;
	    private Projectile ammo;
	    private boolean isEnemyExist = false;
	   int index = 0;
	    public GameScreen(Game game, String activityLevel, int userID, Context context, Lesson lesson) {
	    	//Super Parameters Game, ActivityName, ActivityLevel, UserID
	        super(game, activityName, activityLevel, userID, context,lesson);
	        Log.d("Aldrin ExtendedFramework", "This should be after abstract Game");
	        this.userID = userID;
	        this.activityLevel = activityLevel;
	        Log.d("Aldrin ExtendedFramework", "Gamescreen constructor...");
	        
	    }

		@Override
		protected void loadAssets() {
			// TODO Auto-generated method stub
	        Log.d("Aldrin ExtendedFramework", "Loading Assets");
	        enemies = new EnemyList(activityLevel);
	        enemies.loadEnemy();
	        bg = Assets.bg;
	        circle = Assets.circle;
	        square = Assets.square;
	        star = Assets.star;
	        triangle = Assets.triangle;
	        easy_panel = Assets.easy_panel;
	        enemy = Assets.nothingness;
	        spaceship = Assets.spaceship;
	        projectile = Assets.nothingness;
	        wrong = Assets.nothingness;
	        ammo = new Projectile();
	        v_bilog = Assets.bilog;
	        v_bituin = Assets.bituin;
	        v_parisukat = Assets.parisukat;
	        v_tatsulok = Assets.tatsulok;
		    Log.d("Aldrin ExtendedFramework", "Loading Assets...Done");
		}

		@Override
		protected void assetPositionEasy() {
			// TODO Auto-generated method stub
	        Log.d("Aldrin ExtendedFramework", "Positioning Easy Assets");
			
	        pEasyPanel = new Parts(0,600);
	        pCircle = new Parts (120,415);
	        pSquare= new Parts (495,415);
	        pStar= new Parts (385,390);
	        pTriangle = new Parts (247,385);
	        pSpaceship = new Parts(310,235);
	        pEnemy = new Parts(300,45);
	        livesLeft = 4;
	        rounds = 10;
	        pWrong = new Parts(0,0);
	        ammo.loadAmmos(pSpaceship.getX(), pSpaceship.getY());
	        
	        Log.d("Aldrin ExtendedFramework", "Positioning Easy Assets...Done");
		}

		@Override
		protected void assetPositionMedium() {
			// TODO Auto-generated method stub
		   Log.d("GameScreen", "Positioning Medium"); 
			assetPositionEasy();
			livesLeft = 4;
	        rounds  = 15;
 	       Log.d("GameScreen", "Positioning Medium...Done"); 
		}
		
		@Override
		protected void assetPositionHard() {
			// TODO Auto-generated method stub
			assetPositionEasy();
			assetPositionMedium();
			livesLeft = 4;
	        rounds = 20;
		}
		private void spawnEnemy(int size){
			Random rand = new Random();
			isEnemyExist = true;
			index = rand.nextInt(size);
			//enemyInfo = enemies.getEnemy(index);
			sQuestion = enemies.getEnemyQuestion(index);
			cAnswer = enemies.getEnemy(index);
			enemy = Assets.enemyShapes.get(index);
			Log.d("Enemy Index: ", "i: " + index + " enemy formation: " + enemies.getEnemy(index)  );
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
	        for (int i = 0; i < len; i++) {
	            TouchEvent event = touchEvents.get(i);
	            
	            /////////////////////////////////////////////
	            if (event.type == TouchEvent.TOUCH_DOWN) { //Happens When you press a specifi
	            	wrong = Assets.nothingness;
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
	            			rounds--;
	            		}else{
	            			Log.d("Feedback debug", "Evaluation false");
	            			wrong = Assets.wrong;
	            			pWrong.move(pCircle.getX(), pCircle.getY());
	            			sFeedback =  eval.getImmediateFeedback(index, sAnswer, lessonNumber);
	            			livesLeft--;
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
	            			rounds--;
	            		}else{
	            			Log.d("Feedback debug", "Evaluation false");
	            			sFeedback =  eval.getImmediateFeedback(index, sAnswer, lessonNumber);
	            			wrong = Assets.wrong;
	            			pWrong.move(pSquare.getX(), pSquare.getY());
	            			livesLeft--;
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
	            			rounds--;
	            		}else{
	            			Log.d("Feedback debug", "Evaluation false");
	            			sFeedback =  eval.getImmediateFeedback(index, sAnswer, lessonNumber);
	            			wrong = Assets.wrong;
	            			pWrong.move(pStar.getX(), pStar.getY());
	            			livesLeft--;
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
	            			rounds--;
	            		}else{
	            			Log.d("Feedback debug", "Evaluation false");
	            			sFeedback =  eval.getImmediateFeedback(index, sAnswer, lessonNumber);
	            			wrong = Assets.wrong;
	            			pWrong.move(pTriangle.getX(), pTriangle.getY());
	            			livesLeft--;
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
			int len = touchEvents.size();
			if(isEnemyExist == false){
				spawnEnemy(7);
			}
	        for (int i = 0; i < len; i++) {
	            TouchEvent event = touchEvents.get(i);
	            
	            /////////////////////////////////////////////
	            if (event.type == TouchEvent.TOUCH_DOWN) {
	            	Log.d("Touched Down", "X: " + event.x + "Y: " + event.y );
	            	if(inBounds(event, pCircle.getX() ,pCircle.getY() , circle.getWidth(), circle.getHeight())){
	            		
	            	}else if(inBounds(event, pSquare.getX() ,pSquare.getY() , square.getWidth(), square.getHeight())){
	            		
	            	}else if(inBounds(event, pStar.getX() ,pStar.getY() , star.getWidth(), star.getHeight())){
	            		
	            	}else if(inBounds(event, pTriangle.getX() ,pTriangle.getY() , triangle.getWidth(), triangle.getHeight())){
	            		
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
						int len = touchEvents.size();
						if(isEnemyExist == false){
							spawnEnemy(7);
						}
				        for (int i = 0; i < len; i++) {
				            TouchEvent event = touchEvents.get(i);
				            
				            /////////////////////////////////////////////
				            if (event.type == TouchEvent.TOUCH_DOWN) {
				            	Log.d("Touched Down", "X: " + event.x + "Y: " + event.y );
				            	if(inBounds(event, pCircle.getX() ,pCircle.getY() , circle.getWidth(), circle.getHeight())){
				            		
				            	}else if(inBounds(event, pSquare.getX() ,pSquare.getY() , square.getWidth(), square.getHeight())){
				            		
				            	}else if(inBounds(event, pStar.getX() ,pStar.getY() , star.getWidth(), star.getHeight())){
				            		
				            	}else if(inBounds(event, pTriangle.getX() ,pTriangle.getY() , triangle.getWidth(), triangle.getHeight())){
				            		
				            	}
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
		    
		        System.gc();

		}

		@Override
		protected void painterEasy() {
			// TODO Auto-generated method stub
			 Graphics g = game.getGraphics();
			 
			 g.drawImage(bg, 0, 0);
			 g.drawImage(spaceship, pSpaceship.getX(), pSpaceship.getY());
			 g.drawImage(easy_panel, pEasyPanel.getX(), pEasyPanel.getY());
			 g.drawImage(circle, pCircle.getX(), pCircle.getY());
			 g.drawImage(square, pSquare.getX(), pSquare.getY());
			 g.drawImage(star, pStar.getX(), pStar.getY());
			 g.drawImage(triangle, pTriangle.getX(), pTriangle.getY());
			 g.drawImage(enemy, pEnemy.getX(), pEnemy.getY());
			 g.drawImage(projectile, ammo.getCurX(), ammo.getCurY());
			 g.drawImage(wrong, pWrong.getX(), pWrong.getY());
			 }
		protected void painterMedium() {
			// TODO Auto-generated method stub
			Graphics g = game.getGraphics();
				painterEasy();
			}
		protected void painterHard() {
			// TODO Auto-generated method stub
			 Graphics g = game.getGraphics();
			 painterEasy();
			 painterMedium();
			}

		@Override
		protected void drawRunningUI() {
			// TODO Auto-generated method stub
			Graphics g = game.getGraphics();
	       // g.drawString("sFeedback", 300, 400, paint2);//sFeedback 
	        g.drawString(sFeedback, 545, 40, paint3);//sQuestion
	        g.drawString(sQuestion, 545, 60, paint3);//sQuestion
	        
	       
		}

	   
}