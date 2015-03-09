package com.ube.salinlahifour.lessonActivities.PartsOFHouse;
import android.content.Context;
import android.util.Log;

import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Screen;
import com.kilobolt.framework.Graphics.ImageFormat;
import com.ube.salinlahifour.Lesson;

public class LoadingScreen extends Screen {
	private String activityLevel;
	private int userID;
	private Context context;
	private Lesson lesson;
	 public LoadingScreen(Game game, String activityLevel, int userID, Context context, Lesson lesson) {
	        super(game);
	        this.activityLevel = activityLevel;
	        this.userID = userID;
			Log.d("Lodaing Screen", "TEST ActivityLevel in lesson act: " + activityLevel);
			this.context = context;
			this.lesson = lesson;
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
	        	Log.d("Loading Screen", "Medium loading");
	        	Assets.gamebg = g.newImage("house/Medium/gamebg.png", ImageFormat.RGB565);
	        	
	        	Assets.roof = g.newImage("house/Medium/roof.png", ImageFormat.RGB565);
	   	        Assets.roofholder = g.newImage("house/Medium/roofholder.png", ImageFormat.RGB565);
		        Assets.roofholder_selected = g.newImage("house/Medium/roofholder_selected.png", ImageFormat.RGB565);
		        Assets.roof_selected = g.newImage("house/Medium/roof_selected.png", ImageFormat.RGB565);
	        	
		        Assets.body = g.newImage("house/Easy/body.png", ImageFormat.RGB565);
		        Assets.bodyholder = g.newImage("house/Easy/bodyholder.png", ImageFormat.RGB565);
		        Assets.bodyholder_selected = g.newImage("house/Easy/bodyholder_selected.png", ImageFormat.RGB565);
		        Assets.body_selected = g.newImage("house/Easy/body_selected.png", ImageFormat.RGB565);
		        
		        Assets.door = g.newImage("house/Easy/door.png", ImageFormat.RGB565);
		        Assets.doorholder = g.newImage("house/Easy/doorholder.png", ImageFormat.RGB565);
		        Assets.doorholder_selected = g.newImage("house/Easy/doorholder_selected.png", ImageFormat.RGB565);
		        Assets.door_selected = g.newImage("house/Easy/door_selected.png", ImageFormat.RGB565);
		        
		        Assets.window = g.newImage("house/Easy/window.png", ImageFormat.RGB565);
		        Assets.windowholder = g.newImage("house/Easy/windowholder.png", ImageFormat.RGB565);
		        Assets.windowholder_selected = g.newImage("house/Easy/windowholder_selected.png", ImageFormat.RGB565);
		        Assets.window_selected = g.newImage("house/Easy/window_selected.png", ImageFormat.RGB565);
	        	Log.d("Loading Screen", "Loading Garage " + activityLevel);  
	        	Assets.garage = g.newImage("house/Medium/garage.png", ImageFormat.RGB565);
	        	Assets.garageholder = g.newImage("house/Medium/garageholder.png", ImageFormat.RGB565);
	        	Assets.garage_selected = g.newImage("house/Medium/garage_selected.png", ImageFormat.RGB565);
	        	Assets.garageholder_selected = g.newImage("house/Medium/garageholder_selected.png", ImageFormat.RGB565);
	        	Log.d("Loading Screen", "Loading Garage...Done " + activityLevel); 
	        	Log.d("Loading Screen", "Loading Fence " + activityLevel); 
	        	Assets.fence = g.newImage("house/Medium/fence.png", ImageFormat.RGB565);
	        	Assets.fenceholder = g.newImage("house/Medium/fenceholder.png", ImageFormat.RGB565);
	        	Assets.fence_selected = g.newImage("house/Medium/fence_selected.png", ImageFormat.RGB565);
	        	Assets.fenceholder_selected = g.newImage("house/Medium/fenceholder_selected.png", ImageFormat.RGB565);
	        	Log.d("Loading Screen", "Loading Fence...Done " + activityLevel); 
	        	Log.d("Loading Screen", "Medium loaded: " + activityLevel);
	        
	        	break;
	        case "EASY":
	        	Log.d("Loading Screen", "Easy loading");
	        Assets.body = g.newImage("house/Easy/body.png", ImageFormat.RGB565);
	        Assets.bodyholder = g.newImage("house/Easy/bodyholder.png", ImageFormat.RGB565);
	        Assets.door = g.newImage("house/Easy/door.png", ImageFormat.RGB565);
	        Assets.doorholder = g.newImage("house/Easy/doorholder.png", ImageFormat.RGB565);
	        Assets.gamebg = g.newImage("house/Easy/gamebg.png", ImageFormat.RGB565);
	        Assets.roof = g.newImage("house/Easy/roof.png", ImageFormat.RGB565);
	        Assets.roofholder = g.newImage("house/Easy/roofholder.png", ImageFormat.RGB565);
	        Assets.window = g.newImage("house/Easy/window.png", ImageFormat.RGB565);
	        Assets.windowholder = g.newImage("house/Easy/windowholder.png", ImageFormat.RGB565);
	        
	        Assets.bodyholder_selected = g.newImage("house/Easy/bodyholder_selected.png", ImageFormat.RGB565);
	        Assets.roofholder_selected = g.newImage("house/Easy/roofholder_selected.png", ImageFormat.RGB565);
	        Assets.doorholder_selected = g.newImage("house/Easy/doorholder_selected.png", ImageFormat.RGB565);
	        Assets.windowholder_selected = g.newImage("house/Easy/windowholder_selected.png", ImageFormat.RGB565);
	        
	        Assets.body_selected = g.newImage("house/Easy/body_selected.png", ImageFormat.RGB565);
	        Assets.roof_selected = g.newImage("house/Easy/roof_selected.png", ImageFormat.RGB565);
	        Assets.door_selected = g.newImage("house/Easy/door_selected.png", ImageFormat.RGB565);
	        Assets.window_selected = g.newImage("house/Easy/window_selected.png", ImageFormat.RGB565);
	        Log.d("Loading Screen", "Easy loaded");
	        	
	       
	        break;
	        }
	        Assets.tooltip_left = g.newImage("house/tooltip_left.png", ImageFormat.RGB565);
	        Assets.tooltip_right =  g.newImage("house/tooltip_right.png", ImageFormat.RGB565);
	        Assets.nothingness = g.newImage("house/nothingness.png", ImageFormat.RGB565);
	        game.setScreen(new GameScreen(game, activityLevel,userID,context,lesson ));


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
