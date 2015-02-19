package com.ube.salinlahifour.lessonActivities.CookingColors;
import android.util.Log;

import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Screen;
import com.kilobolt.framework.Graphics.ImageFormat;

public class LoadingScreen extends Screen {
	private String activityLevel;
	private int userID;
	 public LoadingScreen(Game game, String activityLevel, int userID) {
	        super(game);
	        this.activityLevel = activityLevel;
	        this.userID = userID;
			Log.d("Lodaing Screen", "TEST ActivityLevel in lesson act: " + activityLevel);

	    }
	 @Override
	    public void update(float deltaTime) {
	        Graphics g = game.getGraphics();
	        //Assets.menu = g.newImage("menu.jpg", ImageFormat.RGB565);
	        //Assets.click = game.getAudio().createSound("explode.ogg");
	       // Assets.splashbg = g.newImage("splashbg.png", ImageFormat.RGB565);
	        
	        //Assets.menubg = g.newImage("menubg.png", ImageFormat.RGB565);
	        switch(activityLevel){
	        case "HARD":
		 	       
	        	
	        case "MEDIUM": 
	        
	        	break;
	        case "EASY":
	        	Assets.gamebg = g.newImage("cooking/Easy/bg.png", ImageFormat.RGB565);
	        	Assets.breader = g.newImage("cooking/Easy/breader.png", ImageFormat.RGB565);
	        	Assets.belt = g.newImage("cooking/Easy/conveyor.png", ImageFormat.RGB565);
	        	Assets.creamer = g.newImage("cooking/Easy/creamer.png", ImageFormat.RGB565);
	        	Assets.sprinkler = g.newImage("cooking/Easy/sprinkler.png", ImageFormat.RGB565);
	        	Assets.plate = g.newImage("cooking/Easy/plates.png", ImageFormat.RGB565);
	        break;
	        }
	        game.setScreen(new GameScreen(game, activityLevel,userID));


	    }


	    @Override
	    public void paint(float deltaTime) {


	    }


	    @Override
	    public void pause() {


	    }


	    @Override
	    public void resume() {


	    }


	    @Override
	    public void dispose() {


	    }


	    @Override
	    public void backButton() {


	    }
}
