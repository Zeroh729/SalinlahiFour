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
import com.ube.salinlahifour.evaluationModule.Evaluation;
import com.ube.salinlahifour.lessonActivities.SpaceShape.Assets;

public class LoadingScreen extends Screen {
	private String activityLevel;
	private int userID;
	private Context context;
	private Lesson lesson;
	private Evaluation evals;
	private int size;

	public LoadingScreen(Game game, String activityLevel, int userID, Context con, Lesson lesson, Evaluation eval) {
		super(game);
		context = con;
		this.activityLevel = activityLevel;
		this.userID = userID;
		this.lesson = lesson;
		evals = eval;
		Log.d("Lodaing Screen", "TEST ActivityLevel in lesson act: " + activityLevel);
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		Assets.enemyShapes = new ArrayList<Image>();
		Assets.lives = new ArrayList<Image>();
		Assets.bgBack = g.newImage("back/window.png", ImageFormat.RGB565);
		Assets.backbtn = g.newImage("back/backbtn.png", ImageFormat.RGB565);
		Assets.yesbtn = g.newImage("back/yesbtn.png", ImageFormat.RGB565);
		Assets.nobtn = g.newImage("back/nobtn.png", ImageFormat.RGB565);
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
		for(int i = 0; i < lesson.getItems().size(); i++) {
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
		for(int e = 0; e < size; e++) {
			Assets.enemyShapes.add(g.newImage("shapes/em_" + lesson.getItems().get(e).getImagePath() + ".png", ImageFormat.RGB565));
		}
		switch (activityLevel) {
			case "HARD":
				Assets.arrow = g.newImage("shapes/" + lesson.getItems().get(7).getImagePath() + ".png", ImageFormat.RGB565);
				Assets.cresent = g.newImage("shapes/" + lesson.getItems().get(8).getImagePath() + ".png", ImageFormat.RGB565);
				Assets.heart = g.newImage("shapes/" + lesson.getItems().get(9).getImagePath() + ".png", ImageFormat.RGB565);

				Assets.arrowP = g.newImage("shapes/" + lesson.getItems().get(7).getImagePath() + "P.png", ImageFormat.RGB565);
				Assets.cresentP = g.newImage("shapes/" + lesson.getItems().get(8).getImagePath() + "P.png", ImageFormat.RGB565);
				Assets.heartP = g.newImage("shapes/" + lesson.getItems().get(9).getImagePath() + "P.png", ImageFormat.RGB565);

				Assets.arrow_error = g.newImage("shapes/" + lesson.getItems().get(7).getImagePath() + "_error.png", ImageFormat.RGB565);
				Assets.cresent_error = g.newImage("shapes/" + lesson.getItems().get(8).getImagePath() + "_error.png", ImageFormat.RGB565);
				Assets.heart_error = g.newImage("shapes/" + lesson.getItems().get(9).getImagePath() + "_error.png", ImageFormat.RGB565);

				Assets.tunod = game.getAudio().createSound("shapes/Sounds/" + lesson.getItems().get(7).getVoiceFilPath() + ".mp3");
				Assets.gasuklay = game.getAudio().createSound("shapes/Sounds/" + lesson.getItems().get(8).getVoiceFilPath() + ".mp3");
				Assets.puso = game.getAudio().createSound("shapes/Sounds/" + lesson.getItems().get(9).getVoiceFilPath() + ".mp3");

			case "MEDIUM":
				Assets.cross = g.newImage("shapes/" + lesson.getItems().get(4).getImagePath() + ".png", ImageFormat.RGB565);
				Assets.diamond = g.newImage("shapes/" + lesson.getItems().get(5).getImagePath() + ".png", ImageFormat.RGB565);
				Assets.rectangle = g.newImage("shapes/" + lesson.getItems().get(6).getImagePath() + ".png", ImageFormat.RGB565);

				Assets.crossP = g.newImage("shapes/" + lesson.getItems().get(4).getImagePath() + "P.png", ImageFormat.RGB565);
				Assets.diamondP = g.newImage("shapes/" + lesson.getItems().get(5).getImagePath() + "P.png", ImageFormat.RGB565);
				Assets.rectangleP = g.newImage("shapes/" + lesson.getItems().get(6).getImagePath() + "P.png", ImageFormat.RGB565);

				Assets.cross_error = g.newImage("shapes/" + lesson.getItems().get(4).getImagePath() + "_error.png", ImageFormat.RGB565);
				Assets.diamond_error = g.newImage("shapes/" + lesson.getItems().get(5).getImagePath() + "_error.png", ImageFormat.RGB565);
				Assets.rectangle_error = g.newImage("shapes/" + lesson.getItems().get(6).getImagePath() + "_error.png", ImageFormat.RGB565);

				Assets.krus = game.getAudio().createSound("shapes/Sounds/" + lesson.getItems().get(4).getVoiceFilPath() + ".mp3");
				Assets.diamante = game.getAudio().createSound("shapes/Sounds/" + lesson.getItems().get(5).getVoiceFilPath() + ".mp3");
				Assets.parihaba = game.getAudio().createSound("shapes/Sounds/" + lesson.getItems().get(6).getVoiceFilPath() + ".mp3");
			case "EASY":
				Assets.circle = g.newImage("shapes/" + lesson.getItems().get(0).getImagePath() + ".png", ImageFormat.RGB565);
				Assets.square = g.newImage("shapes/" + lesson.getItems().get(1).getImagePath() + ".png", ImageFormat.RGB565);
				Assets.star = g.newImage("shapes/" + lesson.getItems().get(2).getImagePath() + ".png", ImageFormat.RGB565);
				Assets.triangle = g.newImage("shapes/" + lesson.getItems().get(3).getImagePath() + ".png", ImageFormat.RGB565);

				Assets.circleP = g.newImage("shapes/" + lesson.getItems().get(0).getImagePath() + "P.png", ImageFormat.RGB565);
				Assets.squareP = g.newImage("shapes/" + lesson.getItems().get(1).getImagePath() + "P.png", ImageFormat.RGB565);
				Assets.starP = g.newImage("shapes/" + lesson.getItems().get(2).getImagePath() + "P.png", ImageFormat.RGB565);
				Assets.triangleP = g.newImage("shapes/" + lesson.getItems().get(3).getImagePath() + "P.png", ImageFormat.RGB565);

				Assets.circle_error = g.newImage("shapes/" + lesson.getItems().get(0).getImagePath() + "_error.png", ImageFormat.RGB565);
				Assets.square_error = g.newImage("shapes/" + lesson.getItems().get(1).getImagePath() + "_error.png", ImageFormat.RGB565);
				Assets.star_error = g.newImage("shapes/" + lesson.getItems().get(2).getImagePath() + "_error.png", ImageFormat.RGB565);
				Assets.triangle_error = g.newImage("shapes/" + lesson.getItems().get(3).getImagePath() + "_error.png", ImageFormat.RGB565);

				Assets.bilog = game.getAudio().createSound("shapes/Sounds/" + lesson.getItems().get(0).getVoiceFilPath() + ".mp3");
				Assets.parisukat = game.getAudio().createSound("shapes/Sounds/" + lesson.getItems().get(1).getVoiceFilPath() + ".mp3");
				Assets.bituin = game.getAudio().createSound("shapes/Sounds/" + lesson.getItems().get(2).getVoiceFilPath() + ".mp3");
				Assets.tatsulok = game.getAudio().createSound("shapes/Sounds/" + lesson.getItems().get(3).getVoiceFilPath() + ".mp3");
				break;
		}

		game.setScreen(new GameScreen(game, activityLevel, userID, context, lesson, evals));

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
