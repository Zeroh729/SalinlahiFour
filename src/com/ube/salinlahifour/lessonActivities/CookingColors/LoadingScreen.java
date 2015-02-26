package com.ube.salinlahifour.lessonActivities.CookingColors;
import java.util.ArrayList;

import android.util.Log;

import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Image;
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
	        Assets.bg = g.newImage("cooking/bg.png", ImageFormat.RGB565);
	        Assets.bread = new ArrayList<Image>();
        	Assets.buttons = new ArrayList<Image>();
        	Assets.frosting = new ArrayList<Image>();
        	Assets.sprinkles = new ArrayList<Image>();
	        switch(activityLevel){
	        case "HARD":
	        	Assets.buttons.add( g.newImage("cooking/Hard/buttons/black.png", ImageFormat.RGB565)); 
	        	Assets.buttons.add(g.newImage("cooking/Hard/buttons/white.png", ImageFormat.RGB565));
	        	
	        	Assets.bread.add( g.newImage("cooking/Hard/bread/black.png", ImageFormat.RGB565)); 
	        	Assets.bread.add( g.newImage("cooking/Hard/bread/white.png", ImageFormat.RGB565)); 
	        	
	        	Assets.sprinkles.add(g.newImage("cooking/Hard/sprinkles/black.png", ImageFormat.RGB565)); 
	        	Assets.sprinkles.add( g.newImage("cooking/Hard/sprinkles/white.png", ImageFormat.RGB565)); 

	        	Assets.frosting.add( g.newImage("cooking/Hard/frosting/black.png", ImageFormat.RGB565)); 
	        	Assets.frosting.add( g.newImage("cooking/Hard/frosting/white.png", ImageFormat.RGB565)); 
	        	
	        case "MEDIUM": 
	        	
	        	Assets.buttons.add( g.newImage("cooking/Medium/buttons/brown.png", ImageFormat.RGB565)); 
	        	Assets.buttons.add( g.newImage("cooking/Medium/buttons/violet.png", ImageFormat.RGB565)); 
	        	
	        	Assets.bread.add( g.newImage("cooking/Medium/bread/brown.png", ImageFormat.RGB565)); 
	        	Assets.bread.add( g.newImage("cooking/Medium/bread/violet.png", ImageFormat.RGB565)); 
	        	
	        	Assets.sprinkles.add(g.newImage("cooking/Medium/sprinkles/brown.png", ImageFormat.RGB565)); 
	        	Assets.sprinkles.add( g.newImage("cooking/Medium/sprinkles/violet.png", ImageFormat.RGB565)); 

	        	Assets.frosting.add( g.newImage("cooking/Medium/frosting/brown.png", ImageFormat.RGB565)); 
	        	Assets.frosting.add(g.newImage("cooking/Medium/frosting/violet.png", ImageFormat.RGB565)); 
	        	
	        case "EASY":
	        	
	        	
	        	
	        	Assets.buttons.add(g.newImage("cooking/Easy/buttons/blue.png", ImageFormat.RGB565)); 
	        	Assets.buttons.add(g.newImage("cooking/Easy/buttons/green.png", ImageFormat.RGB565)); 
	        	Assets.buttons.add( g.newImage("cooking/Easy/buttons/red.png", ImageFormat.RGB565)); 
	        	Assets.buttons.add(g.newImage("cooking/Easy/buttons/yellow.png", ImageFormat.RGB565));
	        	
	        	Assets.bread.add(g.newImage("cooking/Easy/bread/blue.png", ImageFormat.RGB565)); 
	        	Assets.bread.add(g.newImage("cooking/Easy/bread/green.png", ImageFormat.RGB565)); 
	        	Assets.bread.add(g.newImage("cooking/Easy/bread/red.png", ImageFormat.RGB565)); 
	        	Assets.bread.add(g.newImage("cooking/Easy/bread/yellow.png", ImageFormat.RGB565));
	        	
	        	Assets.frosting.add(g.newImage("cooking/Easy/frosting/blue.png", ImageFormat.RGB565)); 
	        	Assets.frosting.add(g.newImage("cooking/Easy/frosting/green.png", ImageFormat.RGB565)); 
	        	Assets.frosting.add(g.newImage("cooking/Easy/frosting/red.png", ImageFormat.RGB565)); 
	        	Assets.frosting.add(g.newImage("cooking/Easy/frosting/yellow.png", ImageFormat.RGB565));
	        	
	        	Assets.sprinkles.add( g.newImage("cooking/Easy/sprinkles/blue.png", ImageFormat.RGB565)); 
	        	Assets.sprinkles.add(g.newImage("cooking/Easy/sprinkles/green.png", ImageFormat.RGB565)); 
	        	Assets.sprinkles.add(g.newImage("cooking/Easy/sprinkles/red.png", ImageFormat.RGB565)); 
	        	Assets.sprinkles.add(g.newImage("cooking/Easy/sprinkles/yellow.png", ImageFormat.RGB565)); 
	        	
	        	Assets.nothingness = g.newImage("cooking/nothingness.png", ImageFormat.RGB565);
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
