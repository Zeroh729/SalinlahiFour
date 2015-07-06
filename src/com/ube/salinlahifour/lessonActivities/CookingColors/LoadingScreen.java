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
import com.ube.salinlahifour.Item;
import com.ube.salinlahifour.Lesson;
import com.ube.salinlahifour.evaluationModule.Evaluation;
import com.ube.salinlahifour.lessonActivities.CookingColors.Assets;

public class LoadingScreen extends Screen {
	private String activityLevel;
	private int userID;
	private Context context;
	private Lesson lesson;
	private Item[] items;
	private int size;
	private Evaluation eval;

	public LoadingScreen(Game game, String activityLevel, int userID, Context cont, Lesson lesson, Evaluation eval) {
		super(game);
		context = cont;
		this.activityLevel = activityLevel;
		this.userID = userID;
		this.lesson = lesson;
		this.eval = eval;

		Log.d("Lodaing Screen", "TEST ActivityLevel in lesson act: " + activityLevel + eval.getLexiconSize());

	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		// Assets.menu = g.newImage("menu.jpg", ImageFormat.RGB565);
		// Assets.click = game.getAudio().createSound("explode.ogg");
		// Assets.splashbg = g.newImage("splashbg.png", ImageFormat.RGB565);

		// Assets.menubg = g.newImage("menubg.png", ImageFormat.RGB565);
		Assets.bgBack = g.newImage("back/window.png", ImageFormat.RGB565);
		Assets.backbtn = g.newImage("back/backbtn.png", ImageFormat.RGB565);
		Assets.yesbtn = g.newImage("back/yesbtn.png", ImageFormat.RGB565);
		Assets.nobtn = g.newImage("back/nobtn.png", ImageFormat.RGB565);
		Assets.bg = g.newImage("cooking/bg.png", ImageFormat.RGB565);
		Assets.nothingness = g.newImage("cooking/nothingness.png", ImageFormat.RGB565);
		Assets.wrong = g.newImage("cooking/wrong.png", ImageFormat.RGB565);
		Assets.ready = g.newImage("cooking/ready.png", ImageFormat.RGB565);
		Assets.envelope = g.newImage("cooking/envelope.png", ImageFormat.RGB565);
		Assets.instructions = g.newImage("cooking/instructions.png", ImageFormat.RGB565);
		Assets.feedboxChef = g.newImage("charbox/chefbubble2.png", ImageFormat.RGB565);
		Assets.feedbox = g.newImage("charbox/chefbubble1.png", ImageFormat.RGB565);
		Assets.bread = new ArrayList<Image>();
		Assets.buttons = new ArrayList<Image>();
		Assets.buttons_pressed = new ArrayList<Image>();
		Assets.frosting = new ArrayList<Image>();
		Assets.sprinkles = new ArrayList<Image>();
		Assets.sound = new ArrayList<Sound>();
		Assets.nextBtn = g.newImage("buttons/btn.png", ImageFormat.RGB565);
		Assets.nextBtn_pressed = g.newImage("buttons/btn-pressed.png", ImageFormat.RGB565);
		for (int i = 0; i < lesson.getItems().size(); i++) {
			switch (lesson.getItems().get(i).getDifficulty()) {
			case "HARD":
				size++;
				break;
			case "MEDIUM":
				size++;
				break;
			case "EASY":
				size++;
				break;
			}
		}
		Log.d("New Gamescreen", "Easy Size: " + size);
		for (int e = 0; e < size; e++) {
			Assets.buttons.add(g.newImage("cooking/buttons/" + lesson.getItems().get(e).getImagePath() + ".png", ImageFormat.RGB565));
			Assets.buttons_pressed.add(g.newImage("cooking/buttons/" + lesson.getItems().get(e).getImagePath() + "P.png", ImageFormat.RGB565));
			Assets.bread.add(g.newImage("cooking/bread/" + lesson.getItems().get(e).getImagePath() + ".png", ImageFormat.RGB565));
			Assets.frosting.add(g.newImage("cooking/frosting/" + lesson.getItems().get(e).getImagePath() + ".png", ImageFormat.RGB565));
			Assets.sprinkles.add(g.newImage("cooking/sprinkles/" + lesson.getItems().get(e).getImagePath() + ".png", ImageFormat.RGB565));
			Assets.sound.add(game.getAudio().createSound("cooking/Sounds/" + lesson.getItems().get(e).getVoiceFilPath() + ".mp3"));
		}

		game.setScreen(new GameScreen(game, activityLevel, userID, context, lesson, eval));

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
