package com.ube.salinlahifour.lessonActivities.PartsOFHouse;
import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Image;
import com.kilobolt.framework.Screen;
import com.kilobolt.framework.Input.TouchEvent;
public class GameScreen extends Screen  {
	 enum GameState {
	        Ready, Running, Paused, GameOver
	    }

	    GameState state = GameState.Ready;

	    // Variable Setup
	    // You would create game objects here.
	    private Image bg, body, bodyholder, door, doorholder, gamebg,roof,roofholder;
	    private Parts pRoof, pBody, pDoor ;
	    int livesLeft = 3 , answer = 0;
	    // Edit lives left to the question size
	    Paint paint, paint2;
	    private String sFeedback = "",  sQuestion = "";

	    public GameScreen(Game game) {
	        super(game);

	        // Initialize game objects here
	        bg =  Assets.gamebg;
	        body =Assets.body;
	        bodyholder = Assets.bodyholder;
	        door =Assets.door;
	        doorholder = Assets.doorholder;
	        roof = Assets.roof;
	        roofholder = Assets.roofholder;
	        pRoof = new Parts(500,100);
	        pBody = new Parts(700,200);
	        pDoor = new Parts(700,250);
	        
	        // Defining a paint object
	        paint = new Paint();
	        paint.setTextSize(30);
	        paint.setTextAlign(Paint.Align.CENTER);
	        paint.setAntiAlias(true);
	        paint.setColor(Color.WHITE);
	        
	        paint2 = new Paint();
			paint2.setTextSize(30);
			paint2.setTextAlign(Paint.Align.CENTER);
			paint2.setAntiAlias(true);
			paint2.setColor(Color.BLUE);
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
	        if (state == GameState.Running)
	            updateRunning(touchEvents, deltaTime);
	        if (state == GameState.Paused)
	            updatePaused(touchEvents);
	        if (state == GameState.GameOver)
	            updateGameOver(touchEvents);
	    }

	    private void updateReady(List<TouchEvent> touchEvents) {
	        
	        // This example starts with a "Ready" screen.
	        // When the user touches the screen, the game begins. 
	        // state now becomes GameState.Running.
	        // Now the updateRunning() method will be called!
	        
	        if (touchEvents.size() > 0)
	            state = GameState.Running;
	    }

	    private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
	        
	        //This is identical to the update() method from our Unit 2/3 game.
	        
	        
	        // 1. All touch input is handled here:
	        int len = touchEvents.size();
	        for (int i = 0; i < len; i++) {
	            TouchEvent event = touchEvents.get(i);
	            
	            if (event.type == TouchEvent.TOUCH_DOWN) {
	          
	            	Log.d("Debug GameScreen", "Touch down");
	            	if(inBounds(event, 200 , 100 , roofholder.getWidth(), roofholder.getHeight())){
	            		Log.d("Debug GameScreen", "Roof Holder: " + event.x + ", " + event.y);
	            		roofholder = Assets.roofholder_selected;
	            		bodyholder = Assets.bodyholder;
	            		doorholder = Assets.doorholder;
	            		sQuestion = "Bubong";
	            	}
	            	
	            	else if(inBounds(event, 200, 200, bodyholder.getWidth(), bodyholder.getHeight())){
	            		Log.d("Debug GameScreen", "Body Holder: " + event.x + ", " + event.y);
	            		bodyholder = Assets.bodyholder_selected;
	            		doorholder = Assets.doorholder;
	            		roofholder = Assets.roofholder;
	            		sQuestion = "Dingding";
	            	}
	            	else if(inBounds(event, 200, 250, doorholder.getWidth(), doorholder.getHeight())){
	            		Log.d("Debug GameScreen", "DoorHolder: " + event.x + ", " + event.y);	
	            		doorholder = Assets.doorholder_selected;
	            		roofholder = Assets.roofholder;
	            		bodyholder = Assets.bodyholder;
	            		sQuestion = "Pinto";
	            	}
	            	
	            	else{
	            		Log.d("Debug GameScreen","NONE: " + event.x + ", " + event.y);
	            	}

	            }

	            if (event.type == TouchEvent.TOUCH_UP) {
	            	
	            	if(inBounds(event, 200 , 100 , roofholder.getWidth(), roofholder.getHeight())){
	            		//Log.d("Debug GameScreen", "Roof Holder: " + event.x + ", " + event.y);
	            		if(answer == 1){
	            			Log.d("Debug GameScreen", "CORRECT THATS A Bubong");
	            			sFeedback = "Correct Thats A roof";
	            			livesLeft--;
	            			pRoof.move(200, 100);
	            			Log.d("Debug GameScreen EVENT","X: " +event.x + "Y: " + event.y);
	            			Log.d("Debug GameScreen pRoof","X: " +pRoof.getX() + "Y: " + pRoof.getY());
	            			this.paint(1);
	            		}else if(answer == 0){
	            			Log.d("Debug GameScreen", "X");
	            		}else{
	            			sFeedback = "Maling sagot";
	            			Log.d("Debug GameScreen", "Wrong... answer is roof");
	            		}
	            		
	            	}
	            	if(inBounds(event, 200 , 200 , bodyholder.getWidth(), bodyholder.getHeight())){
	            		//Log.d("Debug GameScreen", "Roof Holder: " + event.x + ", " + event.y);
	            		if(answer == 2){
	            			Log.d("Debug GameScreen", "CORRECT THATS A BODY");
	            			sFeedback = "Correct Thats A Dingding";
	            			livesLeft--;
	            			pBody.move(200, 200);
	            		}else if(answer == 0){
	            			Log.d("Debug GameScreen", "X");
	            		}else{
	            			Log.d("Debug GameScreen", "Wrong... answer is BODY");
	            			sFeedback = "Mali";
	            		}
	            	}
	            	else if(inBounds(event, 200, 250, doorholder.getWidth(), doorholder.getHeight())){
	            		//Log.d("Debug GameScreen", "DoorHolder: " + event.x + ", " + event.y);	
	            		if(answer == 3){
	            			Log.d("Debug GameScreen", "CORRECT THATS A door");
	            			sFeedback = "Correct Thats A Pinto";
	            			livesLeft--;
	            			pDoor.move(200, 250);
	            		}else if(answer == 0){
	            			Log.d("Debug GameScreen", "X");
	            		}else{
	            			Log.d("Debug GameScreen", "Wrong... answer is door");
	            			sFeedback = "Ay Mali!";
	            		}
	            	}
	            	answer = 0;
	            }

	            if (event.type == TouchEvent.TOUCH_DRAGGED) {

	            	 if(inBounds(event, 500, 100, roof.getWidth(), roof.getHeight())){
		            		Log.d("Debug GameScreen", "DRAGS roof");
		            		answer = 1;
		            		//Log.d("Debug GameScreen", "X: "+event.x +"Y: "+event.y);
		            		//Log.d("Debug GameScreen: pRoof", "X: "+pRoof.getX() +"Y: "+pRoof.getY());
		            		//Log.d("Debug GameScreen", event.pointer);
		            		
		            	}

		            	else if(inBounds(event, 700, 200, body.getWidth(), body.getHeight())){
		            		//Log.d("Debug GameScreen", "body: " + event.x + ", " + event.y);
		            		Log.d("Debug GameScreen", "DRAGS body");	
		            		answer = 2;
		            	}
		            	else if(inBounds(event, 700, 250, door.getWidth(), door.getHeight())){
		            		//Log.d("Debug GameScreen", "door: " + event.x + ", " + event.y);	
		            		Log.d("Debug GameScreen", "DRAGS door");	
		            		answer = 3;
		            	}else{
		            		Log.d("Debug GameScreen: NONE", "X: "+event.x +"Y: "+event.y);
		            	}
	            	 
	            }
	            
	        }
	        
	        // 2. Check miscellaneous events like death:
	        
	        if (livesLeft == 0) {
	            state = GameState.GameOver;
	        }
	        
	        
	        // 3. Call individual update() methods here.
	        // This is where all the game updates happen.
	        // For example, robot.update();
	        
	    }

	    private void updatePaused(List<TouchEvent> touchEvents) {
	        int len = touchEvents.size();
	        for (int i = 0; i < len; i++) {
	            TouchEvent event = touchEvents.get(i);
	            if (event.type == TouchEvent.TOUCH_UP) {

	            }
	        }
	    }

	    private void updateGameOver(List<TouchEvent> touchEvents) {
	        int len = touchEvents.size();
	        for (int i = 0; i < len; i++) {
	            TouchEvent event = touchEvents.get(i);
	            if (event.type == TouchEvent.TOUCH_UP) {
	                if (event.x > 300 && event.x < 980 && event.y > 100
	                        && event.y < 500) {
	                    nullify();
	                    //game.setScreen(new MainMenuScreen(game));
	                    return;
	                }
	            }
	        }

	    }
	    private boolean inBounds(TouchEvent event, int x, int y, int width,
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

	        // First draw the game elements.
    		//Log.d("Debug GameScreen", "Paint Loops");	

	        // Example:
	        // g.drawImage(Assets.background, 0, 0);
	        // g.drawImage(Assets.character, characterX, characterY);
	        g.drawImage(bg, 0, 0);
	        g.drawImage(roofholder, 200, 100);
	        g.drawImage(bodyholder, 200, 200);
	        g.drawImage(doorholder, 200, 250);
	        
	        //g.drawImage(body, 1, 1);
	        g.drawImage(roof, pRoof.getX(), pRoof.getY());
	        g.drawImage(body, pBody.getX(), pBody.getY());
	        g.drawImage(door, pDoor.getX(), pDoor.getY());
	        
	       // g.drawImage(roof, 1, 1);
	        
	        // Secondly, draw the UI above the game elements.
	        if (state == GameState.Ready)
	            drawReadyUI();
	        if (state == GameState.Running)
	            drawRunningUI();
	        if (state == GameState.Paused)
	            drawPausedUI();
	        if (state == GameState.GameOver)
	            drawGameOverUI();

	    }

	    private void nullify() {

	        // Set all variables to null. You will be recreating them in the
	        // constructor.
	        paint = null;
	        bg =  null;
	        body =null;
	        bodyholder = null;
	        door =null;
	        doorholder = null;
	        roof = null;
	        roofholder = null;
	        // Call garbage collector to clean up memory.
	        System.gc();
	    }

	    private void drawReadyUI() {
	        Graphics g = game.getGraphics();

	        g.drawARGB(155, 0, 0, 0);
	        g.drawString("Tap to Start.", 400, 240, paint);

	    }

	    private void drawRunningUI() {
	        Graphics g = game.getGraphics();
	        g.drawString(sFeedback, 400, 500, paint2);
	        g.drawString(sQuestion, 430, 50, paint2);
	    }

	    private void drawPausedUI() {
	        Graphics g = game.getGraphics();
	        // Darken the entire screen so you can display the Paused screen.
	        //g.drawARGB(155, 0, 0, 0);

	    }

	    private void drawGameOverUI() {
	        Graphics g = game.getGraphics();
	        //g.drawRect(0, 0, 1281, 801, Color.BLACK);
	        //g.drawString("GAME OVER.", 640, 300, paint2);

	    }

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
