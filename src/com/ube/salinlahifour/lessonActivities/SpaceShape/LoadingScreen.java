package com.ube.salinlahifour.lessonActivities.SpaceShape;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Image;
import com.kilobolt.framework.Screen;
import com.kilobolt.framework.Graphics.ImageFormat;
import com.ube.salinlahifour.Lesson;

public class LoadingScreen extends Screen {
	private String activityLevel;
	private int userID;
	private Context context;
	private Lesson lesson;
	 public LoadingScreen(Game game, String activityLevel, int userID, Context con, Lesson lesson) {
	        super(game);
	        context = con;
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
	        Assets.enemyShapes = new ArrayList<Image>();
	        //Assets.menubg = g.newImage("menubg.png", ImageFormat.RGB565);
        	Assets.bg = g.newImage("shapes/bg.png", ImageFormat.RGB565);
        	Assets.nothingness = g.newImage("shapes/nothingness.png", ImageFormat.RGB565);
        	Assets.spaceship = g.newImage("shapes/spaceship.png", ImageFormat.RGB565);
        	Assets.wrong = g.newImage("shapes/wrong.png", ImageFormat.RGB565);
        	
        	Assets.enemyShapes.add(0,g.newImage("shapes/Easy/em_circle.png", ImageFormat.RGB565));
	       	switch(activityLevel){
	        case "HARD":
	       	        	
	        case "MEDIUM": 
	        	
	       	        	
	        case "EASY":
	        	Assets.circle = g.newImage("shapes/Easy/circle.png", ImageFormat.RGB565);
	        	Assets.square = g.newImage("shapes/Easy/square.png", ImageFormat.RGB565);
	        	Assets.star = g.newImage("shapes/Easy/star.png", ImageFormat.RGB565);
	        	Assets.triangle = g.newImage("shapes/Easy/triangle.png", ImageFormat.RGB565);
	        	Assets.circleP = g.newImage("shapes/Easy/circleP.png", ImageFormat.RGB565);
	        	Assets.squareP = g.newImage("shapes/Easy/squareP.png", ImageFormat.RGB565);
	        	Assets.starP = g.newImage("shapes/Easy/starP.png", ImageFormat.RGB565);
	        	Assets.triangleP = g.newImage("shapes/Easy/triangleP.png", ImageFormat.RGB565);
	        	Assets.easy_panel = g.newImage("shapes/Easy/panel.png", ImageFormat.RGB565);
	        	Assets.enemyShapes.add(g.newImage("shapes/Easy/em_circle.png", ImageFormat.RGB565));
	        	Assets.enemyShapes.add(g.newImage("shapes/Easy/em_square.png", ImageFormat.RGB565));
	        	Assets.enemyShapes.add(g.newImage("shapes/Easy/em_star.png", ImageFormat.RGB565));
	        	Assets.enemyShapes.add(g.newImage("shapes/Easy/em_triangle.png", ImageFormat.RGB565));
	        	Assets.bilog = game.getAudio().createSound("shapes/Sounds/shape_bilog.mp3");
	        	Assets.parisukat = game.getAudio().createSound("shapes/Sounds/shape_parisukat.mp3");
	        	Assets.bituin = game.getAudio().createSound("shapes/Sounds/shape_bituin.mp3");
	        	Assets.tatsulok = game.getAudio().createSound("shapes/Sounds/shape_tatsulok.mp3");
	        break;
	        }
	        game.setScreen(new GameScreen(game, activityLevel,userID, context,lesson));


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
