package com.ube.salinlahifour.lessonActivities.PartsOFHouse;
import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Screen;
import com.kilobolt.framework.Graphics.ImageFormat;

public class LoadingScreen extends Screen {
	 public LoadingScreen(Game game) {
	        super(game);
	    }
	 @Override
	    public void update(float deltaTime) {
	        Graphics g = game.getGraphics();
	        //Assets.menu = g.newImage("menu.jpg", ImageFormat.RGB565);
	        //Assets.click = game.getAudio().createSound("explode.ogg");
	        Assets.splashbg = g.newImage("splashbg.png", ImageFormat.RGB565);
	        
	        Assets.menubg = g.newImage("menubg.png", ImageFormat.RGB565);
	        
	        Assets.body = g.newImage("body.png", ImageFormat.RGB565);
	        Assets.bodyholder = g.newImage("bodyholder.png", ImageFormat.RGB565);
	        Assets.door = g.newImage("door.png", ImageFormat.RGB565);
	        Assets.doorholder = g.newImage("doorholder.png", ImageFormat.RGB565);
	        Assets.gamebg = g.newImage("gamebg.png", ImageFormat.RGB565);
	        Assets.roof = g.newImage("roof.png", ImageFormat.RGB565);
	        Assets.roofholder = g.newImage("roofholder.png", ImageFormat.RGB565);
	        
	        Assets.bodyholder_selected = g.newImage("bodyholder_selected.png", ImageFormat.RGB565);
	        Assets.roofholder_selected = g.newImage("roofholder_selected.png", ImageFormat.RGB565);
	        Assets.doorholder_selected = g.newImage("doorholder_selected.png", ImageFormat.RGB565);
	        
	        
	        
	        game.setScreen(new GameScreen(game));


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
