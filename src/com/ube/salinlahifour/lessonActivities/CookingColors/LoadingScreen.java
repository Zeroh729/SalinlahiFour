package com.ube.salinlahifour.lessonActivities.CookingColors;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Image;
import com.kilobolt.framework.Screen;
import com.kilobolt.framework.Graphics.ImageFormat;
import com.kilobolt.framework.Sound;
import com.ube.salinlahifour.Lesson;

public class LoadingScreen extends Screen {
	private String activityLevel;
	private int userID;
	private Context context;
	private Lesson lesson;
	 public LoadingScreen(Game game, String activityLevel, int userID, Context cont, Lesson lesson) {
	        super(game);
	        context = cont;
	        this.activityLevel = activityLevel;
	        this.userID = userID;
	        this.lesson = lesson;
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
	        Assets.nothingness = g.newImage("cooking/nothingness.png", ImageFormat.RGB565);
	        Assets.wrong = g.newImage("cooking/wrong.png", ImageFormat.RGB565);
	        Assets.ready = g.newImage("cooking/ready.png", ImageFormat.RGB565);
	        Assets.bread = new ArrayList<Image>();
        	Assets.buttons = new ArrayList<Image>();
        	Assets.buttons_pressed = new ArrayList<Image>();
        	Assets.frosting = new ArrayList<Image>();
        	Assets.sprinkles = new ArrayList<Image>();
        	Assets.sound = new ArrayList<Sound>();
        
	        switch(activityLevel){
	        case "HARD":
	        	loadEasy(g);
	        	loadMed(g);
	        	loadHard(g);
	        	break;
	        case "MEDIUM": 
	        	loadEasy(g);
	        	loadMed(g);
	        	break;
	        case "EASY":
	        	loadEasy(g);
	        	
	        break;
	        }
	        game.setScreen(new GameScreen(game, activityLevel,userID, context, lesson));


	    }
	 
	 	private void loadEasy(Graphics g){

        	Assets.buttons.add(g.newImage("cooking/Easy/buttons/blue.png", ImageFormat.RGB565)); 
        	Assets.buttons.add(g.newImage("cooking/Easy/buttons/green.png", ImageFormat.RGB565)); 
        	Assets.buttons.add(g.newImage("cooking/Easy/buttons/red.png", ImageFormat.RGB565)); 
        	Assets.buttons.add(g.newImage("cooking/Easy/buttons/yellow.png", ImageFormat.RGB565));
        	
        	Assets.buttons_pressed.add(g.newImage("cooking/Easy/buttons/blueP.png", ImageFormat.RGB565)); 
        	Assets.buttons_pressed.add(g.newImage("cooking/Easy/buttons/greenP.png", ImageFormat.RGB565)); 
        	Assets.buttons_pressed.add(g.newImage("cooking/Easy/buttons/redP.png", ImageFormat.RGB565)); 
        	Assets.buttons_pressed.add(g.newImage("cooking/Easy/buttons/yellowP.png", ImageFormat.RGB565));
        	
        	
        	Assets.bread.add(g.newImage("cooking/Easy/bread/blue.png", ImageFormat.RGB565)); 
        	Assets.bread.add(g.newImage("cooking/Easy/bread/green.png", ImageFormat.RGB565)); 
        	Assets.bread.add(g.newImage("cooking/Easy/bread/red.png", ImageFormat.RGB565)); 
        	Assets.bread.add(g.newImage("cooking/Easy/bread/yellow.png", ImageFormat.RGB565));
        	
        	Assets.frosting.add(g.newImage("cooking/Easy/frosting/blue.png", ImageFormat.RGB565)); 
        	Assets.frosting.add(g.newImage("cooking/Easy/frosting/green.png", ImageFormat.RGB565)); 
        	Assets.frosting.add(g.newImage("cooking/Easy/frosting/red.png", ImageFormat.RGB565)); 
        	Assets.frosting.add(g.newImage("cooking/Easy/frosting/yellow.png", ImageFormat.RGB565));
        	
        	Assets.sprinkles.add(g.newImage("cooking/Easy/sprinkles/blue.png", ImageFormat.RGB565)); 
        	Assets.sprinkles.add(g.newImage("cooking/Easy/sprinkles/green.png", ImageFormat.RGB565)); 
        	Assets.sprinkles.add(g.newImage("cooking/Easy/sprinkles/red.png", ImageFormat.RGB565)); 
        	Assets.sprinkles.add(g.newImage("cooking/Easy/sprinkles/yellow.png", ImageFormat.RGB565)); 
        	
        	//Assets.click = game.getAudio().createSound("explode.ogg");
        	Assets.sound.add(game.getAudio().createSound("cooking/Sounds/color_asul.mp3"));
        	Assets.sound.add(game.getAudio().createSound("cooking/Sounds/color_berde.mp3"));
        	Assets.sound.add(game.getAudio().createSound("cooking/Sounds/color_pula.mp3"));
        	Assets.sound.add(game.getAudio().createSound("cooking/Sounds/color_dilao.mp3"));
        	
	 	}
	 	private void loadMed(Graphics g){
	 		Assets.buttons.add(g.newImage("cooking/Medium/buttons/brown.png", ImageFormat.RGB565)); 
        	Assets.buttons.add(g.newImage("cooking/Medium/buttons/violet.png", ImageFormat.RGB565)); 
        	
        	Assets.buttons_pressed.add(g.newImage("cooking/Medium/buttons/brownP.png", ImageFormat.RGB565)); 
        	Assets.buttons_pressed.add(g.newImage("cooking/Medium/buttons/violetP.png", ImageFormat.RGB565)); 
        	
        	Assets.bread.add(g.newImage("cooking/Medium/bread/brown.png", ImageFormat.RGB565)); 
        	Assets.bread.add(g.newImage("cooking/Medium/bread/violet.png", ImageFormat.RGB565)); 
        	
        	Assets.sprinkles.add(g.newImage("cooking/Medium/sprinkles/brown.png", ImageFormat.RGB565)); 
        	Assets.sprinkles.add(g.newImage("cooking/Medium/sprinkles/violet.png", ImageFormat.RGB565)); 

        	Assets.frosting.add(g.newImage("cooking/Medium/frosting/brown.png", ImageFormat.RGB565)); 
        	Assets.frosting.add(g.newImage("cooking/Medium/frosting/violet.png", ImageFormat.RGB565)); 
        	
        	Assets.sound.add(game.getAudio().createSound("cooking/Sounds/color_kayumanggi.mp3"));
        	Assets.sound.add(game.getAudio().createSound("cooking/Sounds/color_lila.mp3"));
	 	}
	 	private void loadHard(Graphics g){
	 		Assets.buttons.add(g.newImage("cooking/Hard/buttons/black.png", ImageFormat.RGB565)); 
        	Assets.buttons.add(g.newImage("cooking/Hard/buttons/white.png", ImageFormat.RGB565));
        	
        	Assets.buttons_pressed.add( g.newImage("cooking/Hard/buttons/blackP.png", ImageFormat.RGB565)); 
        	Assets.buttons_pressed.add(g.newImage("cooking/Hard/buttons/whiteP.png", ImageFormat.RGB565));
        	
        	Assets.bread.add(g.newImage("cooking/Hard/bread/black.png", ImageFormat.RGB565)); 
        	Assets.bread.add(g.newImage("cooking/Hard/bread/white.png", ImageFormat.RGB565)); 
        	
        	Assets.sprinkles.add(g.newImage("cooking/Hard/sprinkles/black.png", ImageFormat.RGB565)); 
        	Assets.sprinkles.add(g.newImage("cooking/Hard/sprinkles/white.png", ImageFormat.RGB565)); 

        	Assets.frosting.add( g.newImage("cooking/Hard/frosting/black.png", ImageFormat.RGB565)); 
        	Assets.frosting.add( g.newImage("cooking/Hard/frosting/white.png", ImageFormat.RGB565)); 
        	
        	Assets.sound.add(game.getAudio().createSound("cooking/Sounds/color_itim.mp3"));
        	Assets.sound.add(game.getAudio().createSound("cooking/Sounds/color_puti.mp3"));
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
