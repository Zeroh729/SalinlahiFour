package com.ube.salinlahifour.lessonActivities.SpaceShape;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Image;
import com.kilobolt.framework.Screen;
import com.kilobolt.framework.Graphics.ImageFormat;
import com.ube.salinlahifour.Item;
import com.ube.salinlahifour.Lesson;
import com.ube.salinlahifour.lessonActivities.SpaceShape.Assets;

public class LoadingScreen extends Screen {
	private String activityLevel;
	private int userID;
	private Context context;
	private Lesson lesson;
	private ArrayList<Item> items;
	 public LoadingScreen(Game game, String activityLevel, int userID, Context con, Lesson lesson, ArrayList<Item> items) {
	        super(game);
	        context = con;
	        this.activityLevel = activityLevel;
	        this.userID = userID;
	        this.lesson = lesson;
			Log.d("Lodaing Screen", "TEST ActivityLevel in lesson act: " + activityLevel);
			this.items = items;
	    }
	 @Override
	    public void update(float deltaTime) {
	        Graphics g = game.getGraphics();
	        //Assets.menu = g.newImage("menu.jpg", ImageFormat.RGB565);
	        //Assets.click = game.getAudio().createSound("explode.ogg");
	       // Assets.splashbg = g.newImage("splashbg.png", ImageFormat.RGB565);
	        Assets.enemyShapes = new ArrayList<Image>();
	        Assets.lives = new ArrayList<Image>();
	        Assets.bgBack = g.newImage("back/window.png", ImageFormat.RGB565);
	        Assets.backbtn = g.newImage("back/backbtn.png", ImageFormat.RGB565);
	        Assets.yesbtn = g.newImage("back/yesbtn.png", ImageFormat.RGB565);
	        Assets.nobtn = g.newImage("back/nobtn.png", ImageFormat.RGB565);
	        //Assets.menubg = g.newImage("menubg.png", ImageFormat.RGB565);
        	Assets.bg = g.newImage("shapes/bg.png", ImageFormat.RGB565);
        	Assets.nothingness = g.newImage("shapes/nothingness.png", ImageFormat.RGB565);
        	Assets.spaceship = g.newImage("shapes/spaceship.png", ImageFormat.RGB565);
        	Assets.wrong = g.newImage("shapes/wrong.png", ImageFormat.RGB565);
        	Assets.feedboxBoy = g.newImage("charbox/popoibox.png", ImageFormat.RGB565);
	        Assets.feedboxGirl = g.newImage("charbox/pepaybox.png", ImageFormat.RGB565);
	        Assets.nextBtn = g.newImage("buttons/boost.png", ImageFormat.RGB565);
        	Assets.nextBtn_pressed = g.newImage("buttons/boost-pressed.png", ImageFormat.RGB565);
        	Assets.tooltip = g.newImage("shapes/tooltip.png", ImageFormat.RGB565);
        	Assets.lives.add(g.newImage("shapes/lives/0.png", ImageFormat.RGB565));
        	Assets.lives.add(g.newImage("shapes/lives/1.png", ImageFormat.RGB565));
        	Assets.lives.add(g.newImage("shapes/lives/2.png", ImageFormat.RGB565));
        	Assets.lives.add(g.newImage("shapes/lives/3.png", ImageFormat.RGB565));
        	Assets.lives.add(g.newImage("shapes/lives/4.png", ImageFormat.RGB565));
        	//Assets.enemyShapes.add(0,g.newImage("shapes/Easy/em_circle.png", ImageFormat.RGB565));
        	switch(activityLevel){
        	case "EASY": Log.d("easy", "peasy");
	        	em_easy(g);
	        	break;
        	case "MEDIUM":	Log.d("med", "meadi");
	        	em_easy(g);
	        	em_med(g);
	        	break;
        	case "HARD":	Log.d("hard", "ard");
	        	em_easy(g);
	        	em_med(g);
	        	em_hard(g);
	        	break;
        	}
	       	switch(activityLevel){
	        case "HARD":
	        	Assets.arrow = g.newImage("shapes/Hard/arrow.png", ImageFormat.RGB565);
	        	Assets.cresent = g.newImage("shapes/Hard/cresent.png", ImageFormat.RGB565);
	        	Assets.heart = g.newImage("shapes/Hard/heart.png", ImageFormat.RGB565);
	        	
	        	Assets.arrowP = g.newImage("shapes/Hard/arrowP.png", ImageFormat.RGB565);
	        	Assets.cresentP = g.newImage("shapes/Hard/cresentP.png", ImageFormat.RGB565);
	        	Assets.heartP = g.newImage("shapes/Hard/heartP.png", ImageFormat.RGB565);
	        	
	        	Assets.arrow_error = g.newImage("shapes/Hard/arrow_error.png", ImageFormat.RGB565);
	        	Assets.cresent_error = g.newImage("shapes/Hard/cresent_error.png", ImageFormat.RGB565);
	        	Assets.heart_error = g.newImage("shapes/Hard/heart_error.png", ImageFormat.RGB565);
	        	
	        	Assets.tunod = game.getAudio().createSound("shapes/Sounds/shape_tunod.mp3");
	        	Assets.gasuklay = game.getAudio().createSound("shapes/Sounds/shape_gasuklay.mp3");
	        	Assets.puso = game.getAudio().createSound("shapes/Sounds/shape_puso.mp3");
	        	
	        case "MEDIUM": 
	        	Assets.cross = g.newImage("shapes/Medium/cross.png", ImageFormat.RGB565);
	        	Assets.diamond = g.newImage("shapes/Medium/diamond.png", ImageFormat.RGB565);
	        	Assets.rectangle = g.newImage("shapes/Medium/rectangle.png", ImageFormat.RGB565);
	       	        
	        	Assets.crossP = g.newImage("shapes/Medium/crossP.png", ImageFormat.RGB565);
	        	Assets.diamondP = g.newImage("shapes/Medium/diamondP.png", ImageFormat.RGB565);
	        	Assets.rectangleP = g.newImage("shapes/Medium/rectangleP.png", ImageFormat.RGB565);
	        	
	        	Assets.cross_error = g.newImage("shapes/Medium/cross_error.png", ImageFormat.RGB565);
	        	Assets.diamond_error = g.newImage("shapes/Medium/diamond_error.png", ImageFormat.RGB565);
	        	Assets.rectangle_error = g.newImage("shapes/Medium/rectangle_error.png", ImageFormat.RGB565);
	        	
	        	Assets.krus = game.getAudio().createSound("shapes/Sounds/shape_krus.mp3");
	        	Assets.diamante = game.getAudio().createSound("shapes/Sounds/shape_diamante.mp3");
	        	Assets.parihaba = game.getAudio().createSound("shapes/Sounds/shape_parihaba.mp3");
	        case "EASY":
	        	Assets.circle = g.newImage("shapes/Easy/circle.png", ImageFormat.RGB565);
	        	Assets.square = g.newImage("shapes/Easy/square.png", ImageFormat.RGB565);
	        	Assets.star = g.newImage("shapes/Easy/star.png", ImageFormat.RGB565);
	        	Assets.triangle = g.newImage("shapes/Easy/triangle.png", ImageFormat.RGB565);
	        	
	        	Assets.circleP = g.newImage("shapes/Easy/circleP.png", ImageFormat.RGB565);
	        	Assets.squareP = g.newImage("shapes/Easy/squareP.png", ImageFormat.RGB565);
	        	Assets.starP = g.newImage("shapes/Easy/starP.png", ImageFormat.RGB565);
	        	Assets.triangleP = g.newImage("shapes/Easy/triangleP.png", ImageFormat.RGB565);
	        	
	        	Assets.circle_error = g.newImage("shapes/Easy/circle_error.png", ImageFormat.RGB565);
	        	Assets.square_error  = g.newImage("shapes/Easy/square_error.png", ImageFormat.RGB565);
	        	Assets.star_error  = g.newImage("shapes/Easy/star_error.png", ImageFormat.RGB565);
	        	Assets.triangle_error  = g.newImage("shapes/Easy/triangle_error.png", ImageFormat.RGB565);
	        	
	        	Assets.bilog = game.getAudio().createSound("shapes/Sounds/shape_bilog.mp3");
	        	Assets.parisukat = game.getAudio().createSound("shapes/Sounds/shape_parisukat.mp3");
	        	Assets.bituin = game.getAudio().createSound("shapes/Sounds/shape_bituin.mp3");
	        	Assets.tatsulok = game.getAudio().createSound("shapes/Sounds/shape_tatsulok.mp3");
	        break;
	        }
	       	
	        game.setScreen(new GameScreen(game, activityLevel,userID, context,lesson, items));
	        

	    }
	 	public void em_easy(Graphics g){
	 		Assets.enemyShapes.add(g.newImage("shapes/Easy/em_circle.png", ImageFormat.RGB565));
	 		Assets.enemyShapes.add(g.newImage("shapes/Easy/em_circle.png", ImageFormat.RGB565));
        	Assets.enemyShapes.add(g.newImage("shapes/Easy/em_square.png", ImageFormat.RGB565));
        	Assets.enemyShapes.add(g.newImage("shapes/Easy/em_star.png", ImageFormat.RGB565));
        	Assets.enemyShapes.add(g.newImage("shapes/Easy/em_triangle.png", ImageFormat.RGB565));
	 	}
	 	public void em_med(Graphics g){
	 		Assets.enemyShapes.add(g.newImage("shapes/Medium/em_cross.png", ImageFormat.RGB565));
        	Assets.enemyShapes.add(g.newImage("shapes/Medium/em_diamond.png", ImageFormat.RGB565));
        	Assets.enemyShapes.add(g.newImage("shapes/Medium/em_rectangle.png", ImageFormat.RGB565));
	 	}
	 	public void em_hard(Graphics g){
	 		Assets.enemyShapes.add(g.newImage("shapes/Hard/em_arrow.png", ImageFormat.RGB565));
        	Assets.enemyShapes.add(g.newImage("shapes/Hard/em_cresent.png", ImageFormat.RGB565));
        	Assets.enemyShapes.add(g.newImage("shapes/Hard/em_heart.png", ImageFormat.RGB565));
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
