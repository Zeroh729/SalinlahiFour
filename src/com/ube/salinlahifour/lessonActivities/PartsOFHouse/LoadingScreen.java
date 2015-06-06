package com.ube.salinlahifour.lessonActivities.PartsOFHouse;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Screen;
import com.kilobolt.framework.Graphics.ImageFormat;
import com.ube.salinlahifour.Item;
import com.ube.salinlahifour.Lesson;
import com.ube.salinlahifour.evaluationModule.Evaluation;
import com.ube.salinlahifour.lessonActivities.PartsOFHouse.Assets;

public class LoadingScreen extends Screen {
	private String activityLevel;
	private int userID;
	private Context context;
	private Lesson lesson;
	private ArrayList items;
	private Evaluation evals;
	 public LoadingScreen(Game game, String activityLevel, int userID, Context context, Lesson lesson, ArrayList<Item> items, Evaluation eval) {
	        super(game);
	        this.activityLevel = activityLevel;
	        this.userID = userID;
			Log.d("Lodaing Screen", "TEST ActivityLevel in lesson act: " + activityLevel);
			this.context = context;
			this.lesson = lesson;
			this.items = items;
			evals = eval;
	    }
	 @Override
	    public void update(float deltaTime) {
	        Graphics g = game.getGraphics();
	        //Assets.menu = g.newImage("menu.jpg", ImageFormat.RGB565);
	        //Assets.click = game.getAudio().createSound("explode.ogg");
	       // Assets.splashbg = g.newImage("splashbg.png", ImageFormat.RGB565);
	        Assets.wrong = g.newImage("house/wrong.png", ImageFormat.RGB565);
	        Assets.dialogbox = g.newImage("house/dialogbox.png", ImageFormat.RGB565);
	        Assets.bgBack = g.newImage("back/window.png", ImageFormat.RGB565);
	        Assets.backbtn = g.newImage("back/backbtn.png", ImageFormat.RGB565);
	        Assets.yesbtn = g.newImage("back/yesbtn.png", ImageFormat.RGB565);
	        Assets.nobtn = g.newImage("back/nobtn.png", ImageFormat.RGB565);
	        //Assets.menubg = g.newImage("menubg.png", ImageFormat.RGB565);
	        switch(activityLevel){
	        case "HARD":
	        	Assets.gamebg = g.newImage("house/Hard/bg.png", ImageFormat.RGB565);
	        	Assets.roof = g.newImage("house/Hard/"+lesson.getItems().get(0).getImagePath()+".png", ImageFormat.RGB565);
	   	        Assets.roofholder = g.newImage("house/Hard/"+lesson.getItems().get(0).getImagePath()+"holder.png", ImageFormat.RGB565);
	   	        Assets.roof_selected = g.newImage("house/Hard/"+lesson.getItems().get(0).getImagePath()+"_selected.png", ImageFormat.RGB565);
	   	        Assets.roof_error =  g.newImage("house/Hard/"+lesson.getItems().get(0).getImagePath()+"_error.png", ImageFormat.RGB565);
	   	        Assets.roof_choice = g.newImage("house/Hard/"+lesson.getItems().get(0).getImagePath()+"_choice.png", ImageFormat.RGB565);
	   	        
		        Assets.body = g.newImage("house/Hard/"+lesson.getItems().get(1).getImagePath()+".png", ImageFormat.RGB565);
		        Assets.bodyholder = g.newImage("house/Hard/"+lesson.getItems().get(1).getImagePath()+"holder.png", ImageFormat.RGB565);
		        Assets.body_selected = g.newImage("house/Hard/"+lesson.getItems().get(1).getImagePath()+"_selected.png", ImageFormat.RGB565);
		        Assets.body_error =  g.newImage("house/Hard/"+lesson.getItems().get(1).getImagePath()+"_error.png", ImageFormat.RGB565);
	   	        Assets.body_choice = g.newImage("house/Hard/"+lesson.getItems().get(1).getImagePath()+"_choice.png", ImageFormat.RGB565);
	   	        
		        Assets.door = g.newImage("house/Hard/"+lesson.getItems().get(2).getImagePath()+".png", ImageFormat.RGB565);
		        Assets.doorholder = g.newImage("house/Hard/"+lesson.getItems().get(2).getImagePath()+"holder.png", ImageFormat.RGB565);
		        Assets.door_selected = g.newImage("house/Hard/"+lesson.getItems().get(2).getImagePath()+"_selected.png", ImageFormat.RGB565);
		        Assets.door_error =  g.newImage("house/Hard/"+lesson.getItems().get(2).getImagePath()+"_error.png", ImageFormat.RGB565);
	   	        Assets.door_choice = g.newImage("house/Hard/"+lesson.getItems().get(2).getImagePath()+"_choice.png", ImageFormat.RGB565);
	   	        
		        Assets.window = g.newImage("house/Hard/"+lesson.getItems().get(3).getImagePath()+".png", ImageFormat.RGB565);
		        Assets.windowholder = g.newImage("house/Hard/"+lesson.getItems().get(3).getImagePath()+"holder.png", ImageFormat.RGB565);
		        Assets.window_selected = g.newImage("house/Hard/"+lesson.getItems().get(3).getImagePath()+"_selected.png", ImageFormat.RGB565);
		        Assets.window_error =  g.newImage("house/Hard/"+lesson.getItems().get(3).getImagePath()+"_error.png", ImageFormat.RGB565);
	   	        Assets.window_choice = g.newImage("house/Hard/"+lesson.getItems().get(3).getImagePath()+"_choice.png", ImageFormat.RGB565);
	   	        
	        	Assets.garage = g.newImage("house/Hard/garage.png", ImageFormat.RGB565);
	        	Assets.garageholder = g.newImage("house/Hard/garageholder.png", ImageFormat.RGB565);
	        	Assets.garage_selected = g.newImage("house/Hard/garage_selected.png", ImageFormat.RGB565);
	        	Assets.garage_error =  g.newImage("house/Hard/garage_error.png", ImageFormat.RGB565);
	   	        Assets.garage_choice = g.newImage("house/Hard/garage_choice.png", ImageFormat.RGB565); 
	   	        
	        	Assets.fence = g.newImage("house/Hard/fence.png", ImageFormat.RGB565);
	        	Assets.fenceholder = g.newImage("house/Hard/fenceholder.png", ImageFormat.RGB565);
	        	Assets.fence_selected = g.newImage("house/Hard/fence_selected.png", ImageFormat.RGB565);
	        	Assets.fence_error =  g.newImage("house/Hard/fence_error.png", ImageFormat.RGB565);
	   	        Assets.fence_choice = g.newImage("house/Hard/fence_choice.png", ImageFormat.RGB565);   
	   	        
	   	        Assets.chimney = g.newImage("house/Hard/chimney.png", ImageFormat.RGB565);
	        	Assets.chimneyholder = g.newImage("house/Hard/chimneyholder.png", ImageFormat.RGB565);
	        	Assets.chimney_selected = g.newImage("house/Hard/chimney_selected.png", ImageFormat.RGB565);
	        	Assets.chimney_error =  g.newImage("house/Hard/chimney_error.png", ImageFormat.RGB565);
	   	        Assets.chimney_choice = g.newImage("house/Hard/chimney_choice.png", ImageFormat.RGB565);  
	   	        
	   	        Assets.stairs = g.newImage("house/Hard/stairs.png", ImageFormat.RGB565);
	        	Assets.stairsholder = g.newImage("house/Hard/stairsholder.png", ImageFormat.RGB565);
	        	Assets.stairs_selected = g.newImage("house/Hard/stairs_selected.png", ImageFormat.RGB565);
	        	Assets.stairs_error =  g.newImage("house/Hard/stairs_error.png", ImageFormat.RGB565);
	   	        Assets.stairs_choice = g.newImage("house/Hard/stairs_choice.png", ImageFormat.RGB565);  
		        //Assets.sound.add(game.getAudio().createSound("cooking/Sounds/color_puti.mp3"));
	        	
	        	Assets.dingding = game.getAudio().createSound("house/Sounds/house_dingding.mp3");
	        	Assets.bintana = game.getAudio().createSound("house/Sounds/house_bintana.mp3");
	        	Assets.bubong = game.getAudio().createSound("house/Sounds/house_bubong.mp3");
	        	Assets.pinto = game.getAudio().createSound("house/Sounds/house_pinto.mp3");
	        	
	        	Assets.garahe = game.getAudio().createSound("house/Sounds/house_garahe.mp3");
	        	Assets.bakod = game.getAudio().createSound("house/Sounds/house_bakod.mp3");

	        	Assets.hagdan = game.getAudio().createSound("house/Sounds/house_hagdan.mp3");
	        	Assets.chimnea = game.getAudio().createSound("house/Sounds/house_chimney.mp3");
	        	//Assets.hardin = game.getAudio().createSound("house/Sounds/house_hardin.mp3"); 
	        	break;
	        case "MEDIUM": 
	        	Log.d("Loading Screen", "Medium loading");
	        	Assets.gamebg = g.newImage("house/Medium/gamebg.png", ImageFormat.RGB565);
	        	
	        	Assets.roof = g.newImage("house/Medium/roof.png", ImageFormat.RGB565);
	   	        Assets.roofholder = g.newImage("house/Medium/roofholder.png", ImageFormat.RGB565);
	   	        Assets.roof_selected = g.newImage("house/Medium/roof_selected.png", ImageFormat.RGB565);
	   	        Assets.roof_error =  g.newImage("house/Medium/roof_error.png", ImageFormat.RGB565);
	   	        Assets.roof_choice = g.newImage("house/Medium/roof_choice.png", ImageFormat.RGB565);
	   	        
		        Assets.body = g.newImage("house/Medium/body.png", ImageFormat.RGB565);
		        Assets.bodyholder = g.newImage("house/Medium/bodyholder.png", ImageFormat.RGB565);
		        Assets.body_selected = g.newImage("house/Medium/body_selected.png", ImageFormat.RGB565);
		        Assets.body_error =  g.newImage("house/Medium/body_error.png", ImageFormat.RGB565);
	   	        Assets.body_choice = g.newImage("house/Medium/body_choice.png", ImageFormat.RGB565);
	   	        
		        Assets.door = g.newImage("house/Medium/door.png", ImageFormat.RGB565);
		        Assets.doorholder = g.newImage("house/Medium/doorholder.png", ImageFormat.RGB565);
		        Assets.door_selected = g.newImage("house/Medium/door_selected.png", ImageFormat.RGB565);
		        Assets.door_error =  g.newImage("house/Medium/door_error.png", ImageFormat.RGB565);
	   	        Assets.door_choice = g.newImage("house/Medium/door_choice.png", ImageFormat.RGB565);
	   	        
		        Assets.window = g.newImage("house/Medium/window.png", ImageFormat.RGB565);
		        Assets.windowholder = g.newImage("house/Medium/windowholder.png", ImageFormat.RGB565);
		        Assets.window_selected = g.newImage("house/Medium/window_selected.png", ImageFormat.RGB565);
		        Assets.window_error =  g.newImage("house/Medium/window_error.png", ImageFormat.RGB565);
	   	        Assets.window_choice = g.newImage("house/Medium/window_choice.png", ImageFormat.RGB565);
	   	        
	        	Assets.garage = g.newImage("house/Medium/garage.png", ImageFormat.RGB565);
	        	Assets.garageholder = g.newImage("house/Medium/garageholder.png", ImageFormat.RGB565);
	        	Assets.garage_selected = g.newImage("house/Medium/garage_selected.png", ImageFormat.RGB565);
	        	Assets.garage_error =  g.newImage("house/Medium/garage_error.png", ImageFormat.RGB565);
	   	        Assets.garage_choice = g.newImage("house/Medium/garage_choice.png", ImageFormat.RGB565);       
	        	
	        	Assets.fence = g.newImage("house/Medium/fence.png", ImageFormat.RGB565);
	        	Assets.fenceholder = g.newImage("house/Medium/fenceholder.png", ImageFormat.RGB565);
	        	Assets.fence_selected = g.newImage("house/Medium/fence_selected.png", ImageFormat.RGB565);
	        	Assets.fence_error =  g.newImage("house/Medium/fence_error.png", ImageFormat.RGB565);
	   	        Assets.fence_choice = g.newImage("house/Medium/fence_choice.png", ImageFormat.RGB565);       
	        	
	        	Log.d("Loading Screen", "Medium loaded: " + activityLevel);
		        //Assets.sound.add(game.getAudio().createSound("cooking/Sounds/color_puti.mp3"));
	        	
	        	Assets.dingding = game.getAudio().createSound("house/Sounds/house_dingding.mp3");
	        	Assets.bintana = game.getAudio().createSound("house/Sounds/house_bintana.mp3");
	        	Assets.bubong = game.getAudio().createSound("house/Sounds/house_bubong.mp3");
	        	Assets.pinto = game.getAudio().createSound("house/Sounds/house_pinto.mp3");
	        	
	        	Assets.garahe = game.getAudio().createSound("house/Sounds/house_garahe.mp3");
	        	Assets.bakod = game.getAudio().createSound("house/Sounds/house_bakod.mp3");
	        	
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
	        
	        Assets.body_choice = g.newImage("house/Easy/body_choice.png", ImageFormat.RGB565);
	        Assets.roof_choice = g.newImage("house/Easy/roof_choice.png", ImageFormat.RGB565);
	        Assets.door_choice = g.newImage("house/Easy/door_choice.png", ImageFormat.RGB565);
	        Assets.window_choice = g.newImage("house/Easy/window_choice.png", ImageFormat.RGB565);
	        
	        Assets.body_error =  g.newImage("house/Easy/body_error.png", ImageFormat.RGB565);
	        Assets.roof_error =  g.newImage("house/Easy/roof_error.png", ImageFormat.RGB565);
	        Assets.door_error =  g.newImage("house/Easy/door_error.png", ImageFormat.RGB565);
	        Assets.window_error =  g.newImage("house/Easy/window_error.png", ImageFormat.RGB565);
	        
	        Assets.body_selected = g.newImage("house/Easy/body_selected.png", ImageFormat.RGB565);
	        Assets.roof_selected = g.newImage("house/Easy/roof_selected.png", ImageFormat.RGB565);
	        Assets.door_selected = g.newImage("house/Easy/door_selected.png", ImageFormat.RGB565);
	        Assets.window_selected = g.newImage("house/Easy/window_selected.png", ImageFormat.RGB565);
	        
	        Assets.dingding = game.getAudio().createSound("house/Sounds/house_dingding.mp3");
        	Assets.bintana = game.getAudio().createSound("house/Sounds/house_bintana.mp3");
        	Assets.bubong = game.getAudio().createSound("house/Sounds/house_bubong.mp3");
        	Assets.pinto = game.getAudio().createSound("house/Sounds/house_pinto.mp3");
        	
	        
	        Log.d("Loading Screen", "Easy loaded");
	        	
	       
	        break;
	        }
	        Assets.feedboxBoy = g.newImage("charbox/pepaybox.png", ImageFormat.RGB565);
	        Assets.feedboxGirl = g.newImage("charbox/popoibox.png", ImageFormat.RGB565);
	        Assets.nothingness = g.newImage("house/nothingness.png", ImageFormat.RGB565);
	        Assets.nextBtn = g.newImage("buttons/btn.png", ImageFormat.RGB565);
        	Assets.nextBtn_pressed = g.newImage("buttons/btn-pressed.png", ImageFormat.RGB565);
	        game.setScreen(new GameScreen(game, activityLevel,userID,context,lesson, items, evals ));


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
