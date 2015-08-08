package com.ube.salinlahifour.lessonActivities.CookingColors;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Image;
import com.kilobolt.framework.Input.TouchEvent;
import com.kilobolt.framework.Sound;
import com.ube.salinlahifour.Lesson;
import com.ube.salinlahifour.MapActivity;
import com.ube.salinlahifour.evaluationModule.Evaluation;
import com.ube.salinlahifour.lessonActivities.AbstractGameScreen;

public class GameScreen extends AbstractGameScreen {
	// Variable Setup
	// You would create game objects here.
	static String activityName = "Cooking";
	private Image bg, wrong, ready, feedboxChef, recipe;
	private Parts pDialog, pRecipe;
	private Image[] buttons_sprinkles;
	private Image[] buttons_bread;
	private Image[] buttons_frosting;
	private Image bread, frosting, sprinkles;
	private Cake cake;
	private ButtonSet cakeButtons;
	private int userID, isSubmit = 1;
	private Sound[] sounds;
	// Edit lives left to the question size
	private String[] questions;
	private String[] feedbacks;
	private Image backbtn, nobtn, yesbtn, bgBack;
	private Parts pNo, pYes, pBackg;
	private boolean isMistake = false;
	private boolean cor1, cor2, cor3;
	private boolean flag1, flag2, flag3;

	public GameScreen(Game game, String activityLevel, int userID, Context context, Lesson lesson, Evaluation eval) {
		// Super Parameters Game, ActivityName, ActivityLevel, UserID
		super(game, activityName, activityLevel, userID, context, lesson, eval);
		Log.d("Aldrin ExtendedFramework", "This should be after abstract Game");
		this.userID = userID;
		Log.d("Aldrin ExtendedFramework", "Gamescreen constructor...");
		questions = new String[3];
		feedbacks = new String[3];
		rounds = 1;

		flag1 = flag2 = flag3 = cor1 = cor2 = cor3 = false;

		lessonNumber = 3;
		for(int i = 0; i < feedbacks.length; i++) {
			feedbacks[i] = "";
		}
	}

	@Override
	protected void loadAssets() {
		// TODO Auto-generated method stub
		Log.d("Aldrin ExtendedFramework", "Loading Assets");
		eval.setLexiconDir("lexicon_cooking");

		transition = false;
		exit = false;

		buttons_bread = new Image[Assets.buttons.size()];
		buttons_frosting = new Image[Assets.buttons.size()];
		buttons_sprinkles = new Image[Assets.buttons.size()];
		sounds = new Sound[Assets.sound.size()];

		bg = Assets.bg;

		bread = Assets.nothingness;
		frosting = Assets.nothingness;
		sprinkles = Assets.nothingness;
		wrong = Assets.nothingness;
		ready = Assets.nothingness;
		feedboxChef = Assets.nothingness;
		recipe = Assets.envelope;

		pDialog = new Parts(160, 10);
		pRecipe = new Parts(160, 375);

		for(int i = 0; i < buttons_bread.length; i++) {
			buttons_bread[i] = Assets.buttons.get(i);
			buttons_frosting[i] = Assets.buttons.get(i);
			buttons_sprinkles[i] = Assets.buttons.get(i);
			sounds[i] = Assets.sound.get(i);
		}

		this.backbtn = Assets.backbtn;
		this.bgBack = Assets.bgBack;
		this.yesbtn = Assets.yesbtn;
		this.nobtn = Assets.nobtn;

		pYes = new Parts(220, 300);
		pNo = new Parts(400, 300);
		pBackg = new Parts(195, 100);
		eval.setAllowableMistakes(12);
	}
	
	private void initializeButtonSet() {
		cakeButtons=  new ButtonSet(4, lesson);
		
		cakeButtons.initializeCoords(70, 40, 290, 40, 510, 40);
		cakeButtons.calculateButtonPosition(0, buttons_bread[0].getWidth(), buttons_bread[0].getHeight(), 2, 3);
		cakeButtons.calculateButtonPosition(1, buttons_frosting[0].getWidth(), buttons_frosting[0].getHeight(), 2, 3);
		cakeButtons.calculateButtonPosition(2, buttons_sprinkles[0].getWidth(), buttons_sprinkles[0].getHeight(), 2, 3);

		cakeButtons.loadAnswer(activityLevel);

		cakeButtons.loadQuestions();
	}

	@Override
	protected void assetPositionEasy() {
		nItemsRemaining = totalItems = 3;
		initializeButtonSet();
		cake = new Cake();
		
		eval.setTotScore(nItemsRemaining*3);
	}

	@Override
	protected void assetPositionMedium() {
		initializeButtonSet();
		nItemsRemaining = totalItems = 4;
		cake = new Cake();

		cakeButtons.loadRandomColors(6);

		eval.setTotScore(nItemsRemaining*3);
	}

	@Override
	protected void assetPositionHard() {
		// TODO Auto-generated method stub
		nItemsRemaining = totalItems = 5;
		initializeButtonSet();

		cake = new Cake();

		cakeButtons.loadRandomColors(8);
		eval.setTotScore(nItemsRemaining*3);
	}

	@Override
	protected void updateRunningEasy(List<TouchEvent> touchEvents, float deltaTime) {
		// TODO Auto-generated method stub

		int len = touchEvents.size();
		for(int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);

			if(isSubmit == 1) {
				questions[0] = cakeButtons.createQuestions(activityLevel, 0);
				questions[1] = cakeButtons.createQuestions(activityLevel, 1);
				questions[2] = cakeButtons.createQuestions(activityLevel, 2);
				isSubmit = 0;
				recipe = Assets.instructions;
			}
			// ///////////////////////////////////////////
			if(event.type == TouchEvent.TOUCH_DOWN) {
				Log.d("Touched Down", "X: " + event.x + "Y: " + event.y);
				if(inBounds(event, 1, 1, this.backbtn.getWidth(), this.backbtn.getHeight())) {
					exit = true;
				}
				if(exit) {
					if(inBounds(event, pNo.getX(), pNo.getY(), this.nobtn.getWidth(), this.nobtn.getWidth())) {
						exit = false;
					} else if(inBounds(event, pYes.getX(), pYes.getY(), this.yesbtn.getWidth(), this.yesbtn.getWidth())) {
						Intent intent = new Intent(context, MapActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						context.startActivity(intent);
					} else if(inBounds(event, pNo.getX(), pNo.getY(), this.nobtn.getWidth(), this.nobtn.getWidth())) {
						exit = false;
					}
				}
				if(transition) {
					if(inBounds(event, pRecipe.getX(), pRecipe.getY(), recipe.getWidth(), recipe.getHeight())) {
						recipe = Assets.instructions;
						transition = cor1 = cor2 = cor3 = false;
					}
				} else {
					if(inBounds(event, cakeButtons.getInitBreadX(), cakeButtons.getInitBreadY(), buttons_bread[0].getWidth() * 2, buttons_bread[0].getHeight() * 2)) {// if
						// bread
						cake.move(50, 250);

						if(inBounds(event, cakeButtons.getBreadX(0), cakeButtons.getBreadY(0), buttons_bread[0].getWidth(), buttons_bread[0].getHeight())) {// if
							// blue
							Log.d("GameScreen", "Thats a breader and blue button");
							bread = Assets.bread.get(0);
							cake.addBead("Asul");
							sounds[0].play(0.85f);
							wrong = Assets.nothingness;
							buttons_bread[0] = Assets.buttons_pressed.get(0);
						} else if(inBounds(event, cakeButtons.getBreadX(1), cakeButtons.getBreadY(1), buttons_bread[1].getWidth(), buttons_bread[1].getHeight())) {// if
							// green
							Log.d("GameScreen", "Thats a breader and green button");
							bread = Assets.bread.get(1);
							cake.addBead("Berde");
							sounds[1].play(0.85f);
							wrong = Assets.nothingness;
							buttons_bread[1] = Assets.buttons_pressed.get(1);
						} else if(inBounds(event, cakeButtons.getBreadX(2), cakeButtons.getBreadY(2), buttons_bread[2].getWidth(), buttons_bread[2].getHeight())) {// if
							// red
							Log.d("GameScreen", "Thats a breader and red button");
							bread = Assets.bread.get(2);
							cake.addBead("Pula");
							sounds[2].play(0.85f);
							wrong = Assets.nothingness;
							buttons_bread[2] = Assets.buttons_pressed.get(2);
						} else if(inBounds(event, cakeButtons.getBreadX(3), cakeButtons.getBreadY(3), buttons_bread[3].getWidth(), buttons_bread[3].getHeight())) {// if
							// yellow
							Log.d("GameScreen", "Thats a breader and yellow button");
							bread = Assets.bread.get(3);
							cake.addBead("Dilaw");
							sounds[3].play(0.85f);
							wrong = Assets.nothingness;
							buttons_bread[3] = Assets.buttons_pressed.get(3);
						}
					} else if(inBounds(event, cakeButtons.getInitCreamX(), cakeButtons.getInitCreamY(), buttons_bread[0].getWidth() * 2, buttons_bread[0].getHeight() * 2)) {// if
						// frosting
						cake.move(270, 250);
						Log.d("GameScreen", "Thats a creamer");
						if(inBounds(event, cakeButtons.getCreamX(0), cakeButtons.getCreamY(0), buttons_frosting[0].getWidth(), buttons_frosting[0].getHeight())) {// if
							// blue
							Log.d("GameScreen", "Thats a creamer and blue button");
							frosting = Assets.frosting.get(0);
							cake.addCream("Asul");
							sounds[0].play(0.85f);
							wrong = Assets.nothingness;
							buttons_frosting[0] = Assets.buttons_pressed.get(0);
						} else if(inBounds(event, cakeButtons.getCreamX(1), cakeButtons.getCreamY(1), buttons_frosting[1].getWidth(), buttons_frosting[1].getHeight())) {// if
							// green
							Log.d("GameScreen", "Thats a creamer and green button");
							frosting = Assets.frosting.get(1);
							cake.addCream("Berde");
							sounds[1].play(0.85f);
							wrong = Assets.nothingness;
							buttons_frosting[1] = Assets.buttons_pressed.get(1);
						} else if(inBounds(event, cakeButtons.getCreamX(2), cakeButtons.getCreamY(2), buttons_frosting[2].getWidth(), buttons_frosting[2].getHeight())) {// if
							// red
							Log.d("GameScreen", "Thats a creamer and red button");
							frosting = Assets.frosting.get(2);
							cake.addCream("Pula");
							sounds[2].play(0.85f);
							wrong = Assets.nothingness;
							buttons_frosting[2] = Assets.buttons_pressed.get(2);
						} else if(inBounds(event, cakeButtons.getCreamX(3), cakeButtons.getCreamY(3), buttons_frosting[3].getWidth(), buttons_frosting[3].getHeight())) {// if
							// yellow
							Log.d("GameScreen", "Thats a creamer and yellow button");
							frosting = Assets.frosting.get(3);
							cake.addCream("Dilaw");
							sounds[3].play(0.85f);
							wrong = Assets.nothingness;
							buttons_frosting[3] = Assets.buttons_pressed.get(3);
						}
					} else if(inBounds(event, cakeButtons.getInitSprinkleX(), cakeButtons.getInitSprinkleY(), buttons_sprinkles[0].getWidth() * 2, buttons_sprinkles[0].getHeight() * 2)) {// if
						// sprikinle
						cake.move(485, 250);
						if(inBounds(event, cakeButtons.getSprinkleX(0), cakeButtons.getSprinkleY(0), buttons_sprinkles[0].getWidth(), buttons_sprinkles[0].getHeight())) {// if
							// blue
							sprinkles = Assets.sprinkles.get(0);
							cake.addSprinkles("Asul");
							Log.d("GameScreen", "Thats a sprinkle and Blue button");
							sounds[0].play(0.85f);
							wrong = Assets.nothingness;
							buttons_sprinkles[0] = Assets.buttons_pressed.get(0);
						} else if(inBounds(event, cakeButtons.getSprinkleX(1), cakeButtons.getSprinkleY(1), buttons_sprinkles[1].getWidth(), buttons_sprinkles[1].getHeight())) {// if
							// green
							sprinkles = Assets.sprinkles.get(1);
							cake.addSprinkles("Berde");
							wrong = Assets.nothingness;
							buttons_sprinkles[1] = Assets.buttons_pressed.get(1);
							Log.d("GameScreen", "Thats a sprinkle and Green button");
							sounds[1].play(0.85f);
						} else if(inBounds(event, cakeButtons.getSprinkleX(2), cakeButtons.getSprinkleY(2), buttons_sprinkles[2].getWidth(), buttons_sprinkles[2].getHeight())) {// if
							// red
							sprinkles = Assets.sprinkles.get(2);
							cake.addSprinkles("Pula");
							wrong = Assets.nothingness;
							Log.d("GameScreen", "Thats a sprinkle and Red button");
							sounds[2].play(0.85f);
							buttons_sprinkles[2] = Assets.buttons_pressed.get(2);
						} else if(inBounds(event, cakeButtons.getSprinkleX(3), cakeButtons.getSprinkleY(3), buttons_sprinkles[3].getWidth(), buttons_sprinkles[3].getHeight())) {// if
							// yellow
							sprinkles = Assets.sprinkles.get(3);
							cake.addSprinkles("Dilaw");
							wrong = Assets.nothingness;
							Log.d("GameScreen", "Thats a sprinkle and yellow button");
							sounds[3].play(0.85f);
							buttons_sprinkles[3] = Assets.buttons_pressed.get(3);
						}
					}
					if(cake.isBread() && cake.isCream() && cake.isSprinkled()) {
						ready = Assets.ready;
					}
					if(inBounds(event, cake.getX(), cake.getY(), bread.getWidth(), bread.getHeight())) {
						checkAnswer();
					}
				}
			}
			if(event.type == TouchEvent.TOUCH_UP) {
				if(inBounds(event, cakeButtons.getInitBreadX(), cakeButtons.getInitBreadY(), buttons_bread[0].getWidth() * 2, buttons_bread[0].getHeight() * 2)) {// if
					// bread
					if(inBounds(event, cakeButtons.getBreadX(0), cakeButtons.getBreadY(0), buttons_bread[0].getWidth(), buttons_bread[0].getHeight())) {// if
						// blue
						buttons_bread[0] = Assets.buttons.get(0);
					} else if(inBounds(event, cakeButtons.getBreadX(1), cakeButtons.getBreadY(1), buttons_bread[1].getWidth(), buttons_bread[1].getHeight())) {// if
						// blue
						buttons_bread[1] = Assets.buttons.get(1);
					} else if(inBounds(event, cakeButtons.getBreadX(2), cakeButtons.getBreadY(2), buttons_bread[2].getWidth(), buttons_bread[2].getHeight())) {// if
						// blue
						buttons_bread[2] = Assets.buttons.get(2);
					} else if(inBounds(event, cakeButtons.getBreadX(3), cakeButtons.getBreadY(3), buttons_bread[3].getWidth(), buttons_bread[3].getHeight())) {// if
						// blue
						buttons_bread[3] = Assets.buttons.get(3);
					}
				} else if(inBounds(event, cakeButtons.getInitCreamX(), cakeButtons.getInitCreamY(), buttons_frosting[0].getWidth() * 2, buttons_frosting[0].getHeight() * 2)) {// if
					// frostying
					if(inBounds(event, cakeButtons.getCreamX(0), cakeButtons.getCreamY(0), buttons_frosting[0].getWidth(), buttons_frosting[0].getHeight())) {// if
						// blue
						buttons_frosting[0] = Assets.buttons.get(0);
					} else if(inBounds(event, cakeButtons.getCreamX(1), cakeButtons.getCreamY(1), buttons_frosting[1].getWidth(), buttons_frosting[1].getHeight())) {// if
						// blue
						buttons_frosting[1] = Assets.buttons.get(1);
					} else if(inBounds(event, cakeButtons.getCreamX(2), cakeButtons.getCreamY(2), buttons_frosting[2].getWidth(), buttons_frosting[2].getHeight())) {// if
						// blue
						buttons_frosting[2] = Assets.buttons.get(2);
					} else if(inBounds(event, cakeButtons.getCreamX(3), cakeButtons.getCreamY(3), buttons_frosting[3].getWidth(), buttons_frosting[3].getHeight())) {// if
						// blue
						buttons_frosting[3] = Assets.buttons.get(3);
					}
				} else if(inBounds(event, cakeButtons.getInitSprinkleX(), cakeButtons.getInitSprinkleY(), buttons_sprinkles[0].getWidth() * 2, buttons_sprinkles[0].getHeight() * 2)) {// if
					// sprinkle
					if(inBounds(event, cakeButtons.getSprinkleX(0), cakeButtons.getSprinkleY(0), buttons_sprinkles[0].getWidth(), buttons_sprinkles[0].getHeight())) {// if
						// blue
						buttons_sprinkles[0] = Assets.buttons.get(0);

					} else if(inBounds(event, cakeButtons.getSprinkleX(1), cakeButtons.getSprinkleY(1), buttons_sprinkles[1].getWidth(), buttons_sprinkles[1].getHeight())) {// if
						// blue
						buttons_sprinkles[1] = Assets.buttons.get(1);

					} else if(inBounds(event, cakeButtons.getSprinkleX(2), cakeButtons.getSprinkleY(2), buttons_sprinkles[2].getWidth(), buttons_sprinkles[2].getHeight())) {// if
						// blue
						buttons_sprinkles[2] = Assets.buttons.get(2);

					} else if(inBounds(event, cakeButtons.getSprinkleX(3), cakeButtons.getSprinkleY(3), buttons_sprinkles[3].getWidth(), buttons_sprinkles[3].getHeight())) {// if
						// blue
						buttons_sprinkles[3] = Assets.buttons.get(3);
					}
				}
			}
		}
	}

	@Override
	protected void updateRunningMedium(List<TouchEvent> touchEvents, float deltaTime) {
		// TODO Auto-generated method stub
		int len = touchEvents.size();
		for(int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if(isSubmit == 1) {
				questions[0] = cakeButtons.createQuestions(activityLevel, 0);
				questions[1] = cakeButtons.createQuestions(activityLevel, 1);
				questions[2] = cakeButtons.createQuestions(activityLevel, 2);
				isSubmit = 0;
				recipe = Assets.instructions;
			}

			// ///////////////////////////////////////////
			if(event.type == TouchEvent.TOUCH_DOWN) {
				if(inBounds(event, 1, 1, this.backbtn.getWidth(), this.backbtn.getHeight())) {
					exit = true;
				}
				if(exit) {
					if(inBounds(event, pNo.getX(), pNo.getY(), this.nobtn.getWidth(), this.nobtn.getWidth())) {
						Log.d("Exit Debug", "This should be once: NO");
						exit = false;
					} else if(inBounds(event, pYes.getX(), pYes.getY(), this.yesbtn.getWidth(), this.yesbtn.getWidth())) {
						Log.d("Exit Debug", "Quit");
						Intent intent = new Intent(context, MapActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						context.startActivity(intent);
					} else if(inBounds(event, pNo.getX(), pNo.getY(), this.nobtn.getWidth(), this.nobtn.getWidth())) {
						exit = false;
						Log.d("Exit Debug", "Continue");
					}
				}

				if(transition) {
					if(inBounds(event, pRecipe.getX(), pRecipe.getY(), recipe.getWidth(), recipe.getHeight())) {
						Log.d("Transition Debug", "Falseing in easy");
						recipe = Assets.instructions;
						transition = cor1 = cor2 = cor3 = false;
					}
				} else {
					wrong = Assets.nothingness;
					if(inBounds(event, cakeButtons.getInitBreadX(), cakeButtons.getInitBreadY(), buttons_bread[0].getWidth() * 2, buttons_bread[0].getHeight() * 2)) {// if
						// bread
						cake.move(50, 250);
						Log.d("GameScreen", "Thats a breader");
						for(int bread_i = 0; bread_i < 4; bread_i++) {
							if(inBounds(event, cakeButtons.getBreadX(bread_i), cakeButtons.getBreadY(bread_i), buttons_bread[bread_i].getWidth(), buttons_bread[bread_i].getHeight())) {// if
								// blue
								Log.d("GameScreen", "Thats a breader and" + cakeButtons.getAnswer(cakeButtons.getBreadColor(bread_i)));
								bread = Assets.bread.get(cakeButtons.getBreadColor(bread_i));// correct
								cake.addBead(cakeButtons.getAnswer(cakeButtons.getBreadColor(bread_i))); // correct
								sounds[cakeButtons.getBreadColor(bread_i)].play(0.85f);
								// buttons_bread[0] =
								// Assets.buttons_pressed.get(breaderButtons.getChosenColors(0));
								buttons_bread[cakeButtons.getBreadColor(bread_i)] = Assets.buttons_pressed.get(cakeButtons.getBreadColor(bread_i));
								Log.d("Buttons", "Breader Index 0 Chosen Color Index: " + cakeButtons.getBreadColor(bread_i));
							}
						}
					} else if(inBounds(event, cakeButtons.getInitCreamX(), cakeButtons.getInitCreamY(), buttons_frosting[0].getWidth() * 2, buttons_frosting[0].getHeight() * 2)) {// if
						// frosting
						cake.move(270, 250);
						Log.d("GameScreen", "Thats a creamerButtons");
						for(int cream_i = 0; cream_i < 4; cream_i++) {
							if(inBounds(event, cakeButtons.getCreamX(cream_i), cakeButtons.getCreamY(cream_i), buttons_frosting[cream_i].getWidth(), buttons_frosting[cream_i].getHeight())) {// if
								// blue
								frosting = Assets.frosting.get(cakeButtons.getCreamColor(cream_i));// correct
								cake.addCream(cakeButtons.getAnswer(cakeButtons.getCreamColor(cream_i))); // correct
								sounds[cakeButtons.getCreamColor(cream_i)].play(0.85f);
								// buttons_bread[0] =
								// Assets.buttons_pressed.get(breaderButtons.getChosenColors(0));
								buttons_frosting[cakeButtons.getCreamColor(cream_i)] = Assets.buttons_pressed.get(cakeButtons.getCreamColor(cream_i));
								Log.d("Buttons", "Creamer Index 0 Chosen Color Index: " + cakeButtons.getCreamColor(cream_i));
							}
						}
					} else if(inBounds(event, cakeButtons.getInitSprinkleX(), cakeButtons.getInitSprinkleY(), buttons_sprinkles[0].getWidth() * 2, buttons_sprinkles[0].getHeight() * 2)) {// if
						// bread
						cake.move(485, 250);
						Log.d("GameScreen", "Thats a sprinklerButtons");
						for(int sprinkle_i = 0; sprinkle_i < 4; sprinkle_i++) {
							if(inBounds(event, cakeButtons.getSprinkleX(sprinkle_i), cakeButtons.getSprinkleY(sprinkle_i), buttons_sprinkles[sprinkle_i].getWidth(), buttons_sprinkles[sprinkle_i].getHeight())) {// if
								// blue
								Log.d("GameScreen", "Thats a sprinklerButtons and" + cakeButtons.getAnswer(cakeButtons.getSprinkleColor(sprinkle_i)));
								sprinkles = Assets.sprinkles.get(cakeButtons.getSprinkleColor(sprinkle_i));// correct
								cake.addSprinkles(cakeButtons.getAnswer(cakeButtons.getSprinkleColor(sprinkle_i))); // correct
								sounds[cakeButtons.getSprinkleColor(sprinkle_i)].play(0.85f);
								// buttons_bread[0] =
								// Assets.buttons_pressed.get(sprinklerButtons.getChosenColors(0));
								buttons_sprinkles[cakeButtons.getSprinkleColor(sprinkle_i)] = Assets.buttons_pressed.get(cakeButtons.getSprinkleColor(sprinkle_i));
								Log.d("Buttons", "Sprinkler Index 0 Chosen Color Index: " + cakeButtons.getSprinkleColor(sprinkle_i));
							}
						}
					}
					if(cake.isBread() && cake.isCream() && cake.isSprinkled()) {
						ready = Assets.ready;
					}
					if(inBounds(event, cake.getX(), cake.getY(), bread.getWidth(), bread.getHeight())) {
						checkAnswer();
					}
				}
			}

			if(event.type == TouchEvent.TOUCH_UP) {
				if(inBounds(event, cakeButtons.getInitBreadX(), cakeButtons.getInitBreadY(), buttons_bread[0].getWidth() * 2, buttons_bread[0].getHeight() * 2)) {// if
					// bread
					for(int bread_i = 0; bread_i < 4; bread_i++) {
						if(inBounds(event, cakeButtons.getBreadX(bread_i), cakeButtons.getBreadY(bread_i), buttons_bread[bread_i].getWidth(), buttons_bread[bread_i].getHeight())) {// if
							// blue
							buttons_bread[cakeButtons.getBreadColor(bread_i)] = Assets.buttons.get(cakeButtons.getBreadColor(bread_i));
						}
					}
				} else if(inBounds(event, cakeButtons.getInitCreamX(), cakeButtons.getInitCreamY(), buttons_frosting[0].getWidth() * 2, buttons_frosting[0].getHeight() * 2)) {// if
					// frostying
					for(int cream_i = 0; cream_i < 4; cream_i++) {
						if(inBounds(event, cakeButtons.getCreamX(cream_i), cakeButtons.getCreamY(cream_i), buttons_frosting[cream_i].getWidth(), buttons_frosting[cream_i].getHeight())) {// if
							// blue
							buttons_frosting[cakeButtons.getCreamColor(cream_i)] = Assets.buttons.get(cakeButtons.getCreamColor(cream_i));
						}
					}
				} else if(inBounds(event, cakeButtons.getInitSprinkleX(), cakeButtons.getInitSprinkleY(), buttons_sprinkles[0].getWidth() * 2, buttons_sprinkles[0].getHeight() * 2)) {// if
					// sprinkle
					for(int sprinkle_i = 0; sprinkle_i < 4; sprinkle_i++) {
						if(inBounds(event, cakeButtons.getSprinkleX(sprinkle_i), cakeButtons.getSprinkleY(sprinkle_i), buttons_sprinkles[sprinkle_i].getWidth(), buttons_sprinkles[sprinkle_i].getHeight())) {// if
							// blue
							buttons_sprinkles[cakeButtons.getSprinkleColor(sprinkle_i)] = Assets.buttons.get(cakeButtons.getSprinkleColor(sprinkle_i));

						}
					}
				}
			}
		}
	}

	@Override
	protected void updateRunningHard(List<TouchEvent> touchEvents, float deltaTime) {
		// TODO Auto-generated method stub
		updateRunningMedium(touchEvents, deltaTime);
	}

	@Override
	protected void updatePaused(List<TouchEvent> touchEvents) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void updateGameOver(List<TouchEvent> touchEvents) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void nullify() {
		// TODO Auto-generated method stub
		paint = null;
		paint2 = null;
		bg = null;
		bread = null;
		frosting = null;
		sprinkles = null;

		System.gc();

	}

	@Override
	protected void painterEasy() {
		// TODO Auto-generated method stub
		Graphics g = game.getGraphics();

		g.drawImage(bg, 0, 0);
		g.drawImage(wrong, cake.getX(), cake.getY());
		g.drawImage(ready, cake.getX(), cake.getY()-15);
		g.drawImage(recipe, pRecipe.getX(), pRecipe.getY());

		if(cake.isBread()) {
			g.drawImage(bread, cake.getX(), cake.getY());
		}
		if(cake.isCream()) {
			g.drawImage(frosting, cake.getX(), cake.getY());
		}
		if(cake.isSprinkled()) {
			g.drawImage(sprinkles, cake.getX(), cake.getY());
		}

		for(int i = 0; i < 4; i++) {
			// Log.d("Painter", "I: " + i + " X: "+ breaderButtons.getX(i) +
			// " Y: " + breaderButtons.getY(i));
			g.drawImage(buttons_bread[i], cakeButtons.getBreadX(i), cakeButtons.getBreadY(i));
			g.drawImage(buttons_frosting[i], cakeButtons.getCreamX(i), cakeButtons.getCreamY(i));
			g.drawImage(buttons_sprinkles[i], cakeButtons.getSprinkleX(i), cakeButtons.getSprinkleY(i));
		}

	}

	protected void painterMedium() {
		// TODO Auto-generated method stub
		Graphics g = game.getGraphics();
		g.drawImage(bg, 0, 0);
		g.drawImage(ready, cake.getX(), cake.getY()-15);
		g.drawImage(wrong, cake.getX(), cake.getY());
		g.drawImage(recipe, pRecipe.getX(), pRecipe.getY());
		if(cake.isBread()) {
			g.drawImage(bread, cake.getX(), cake.getY());
		}
		if(cake.isCream()) {
			g.drawImage(frosting, cake.getX(), cake.getY());
		}
		if(cake.isSprinkled()) {
			g.drawImage(sprinkles, cake.getX(), cake.getY());
		}
		for(int i = 0; i < 4; i++) {
			// Log.d("Painter", "I: " + i + " X: "+ breaderButtons.getX(i) +
			// " Y: " + breaderButtons.getY(i));
			g.drawImage(buttons_bread[cakeButtons.getBreadColor(i)], cakeButtons.getBreadX(i), cakeButtons.getBreadY(i));
			g.drawImage(buttons_frosting[cakeButtons.getCreamColor(i)], cakeButtons.getCreamX(i), cakeButtons.getCreamY(i));
			g.drawImage(buttons_sprinkles[cakeButtons.getSprinkleColor(i)], cakeButtons.getSprinkleX(i), cakeButtons.getSprinkleY(i));
		}
	}

	protected void painterHard() {
		// TODO Auto-generated method stub
		Graphics g = game.getGraphics();
		painterMedium();
	}

	@Override
	protected void showTransition() {
		// TODO Auto-generated method stub
		Graphics g = game.getGraphics();
		if(super.transition) {
			g.drawARGB(155, 0, 0, 0);
			feedboxChef = Assets.feedboxChef;
			recipe = Assets.envelope;
			g.drawImage(this.feedboxChef, pDialog.getX(), pDialog.getY());
			g.drawImage(this.recipe, pRecipe.getX(), pRecipe.getY());
			// g.drawString(feedbacks[0], 350,155, paint4);
			// g.drawString(feedbacks[1], 350,175, paint4);
			// g.drawString(feedbacks[2], 350,195, paint4);

			String[] cuttedWord;

			if(isMistake) {
				if(!cor1) {
					String lineOne = "", lineTwo = "", lineTri = "", lineFor = "";
					// g.drawString("- ", 350,155, paint4);
					// g.drawString(feedbacks[0], 350,155, paint4);
					cuttedWord = sentenceCutter(feedbacks[0]);
					for(int s = 0; s < cuttedWord.length; s++) {
						if(s > 14) {
							lineFor += cuttedWord[s] + " ";
						} else if(s > 9) {
							lineTri += cuttedWord[s] + " ";
						} else if(s > 4) {
							lineTwo += cuttedWord[s] + " ";
						} else {
							lineOne += cuttedWord[s] + " ";
						}
					}
					g.drawString("Bread: " + lineOne, 360, 125, paint4);
					g.drawString(lineTwo, 360, 145, paint4);
					g.drawString(lineTri, 360, 165, paint4);
					g.drawString(lineFor, 360, 185, paint4);
				}
				if(!cor2) {
					String lineOne = "", lineTwo = "", lineTri = "", lineFor = "";
					// g.drawString(feedbacks[1], 350,195, paint4);
					// g.drawString("- ", 350,195, paint4);
					cuttedWord = sentenceCutter(feedbacks[1]);
					for(int s = 0; s < cuttedWord.length; s++) {
						if(s > 14) {
							lineFor += cuttedWord[s] + " ";
						} else if(s > 9) {
							lineTri += cuttedWord[s] + " ";
						} else if(s > 4) {
							lineTwo += cuttedWord[s] + " ";
						} else {
							lineOne += cuttedWord[s] + " ";
						}
					}
					g.drawString("Frosting: " + lineOne, 360, 205, paint4);
					g.drawString(lineTwo, 360, 225, paint4);
					g.drawString(lineTri, 360, 245, paint4);
					g.drawString(lineFor, 360, 265, paint4);
				}
				if(!cor3) {
					String lineOne = "", lineTwo = "", lineTri = "", lineFor = "";
					// g.drawString(feedbacks[2], 350,225, paint4);
					// g.drawString("- ", 350,225, paint4);
					cuttedWord = sentenceCutter(feedbacks[2]);
					for(int s = 0; s < cuttedWord.length; s++) {
						if(s > 14) {
							lineFor += cuttedWord[s] + " ";
						} else if(s > 8) {
							lineTri += cuttedWord[s] + " ";
						} else if(s > 3) {
							lineTwo += cuttedWord[s] + " ";
						} else {
							lineOne += cuttedWord[s] + " ";
						}
					}
					g.drawString("Sprinkles: " + lineOne, 360, 285, paint4);
					g.drawString(lineTwo, 360, 305, paint4);
					g.drawString(lineTri, 360, 325, paint4);
					g.drawString(lineFor, 360, 345, paint4);

				}
			} else {
				String lineOne = "", lineTwo = "";
				cuttedWord = sentenceCutter(feedbacks[0]);
				for(int s = 0; s < cuttedWord.length; s++) {
					if(s > 5) {
						lineTwo += cuttedWord[s] + " ";

					} else {
						lineOne += cuttedWord[s] + " ";

					}
				}
				// g.drawString(feedbacks[0], 360, 155, paint4);
				g.drawString(lineOne, 360, 155, paint4);
				g.drawString(lineTwo, 360, 175, paint4);
			}
		}
	}

	private void checkAnswer() {
		if(cake.isBread() && cake.isCream() && cake.isSprinkled()) {
			for(int s = 0; s < feedbacks.length; s++) {
				feedbacks[s] = "";
			}
			userRecordOperator.open();
			userLessonProgressOperator.open();

			if(cakeButtons.getQuestionColor(0).equals(cake.getAnswer(0))) {
				cor1 = true;
			}
			if(cakeButtons.getQuestionColor(1).equals(cake.getAnswer(1))) {
				cor2 = true;
			}
			if(cakeButtons.getQuestionColor(2).equals(cake.getAnswer(2))) {
				cor3 = true;
			}

			if(!(cor1 && flag1)) {
				eval.evaluateAnswer(cakeButtons.getQuestionColor(0), cake.getAnswer(0), userID);
				if(cor1) {
					flag1 = cor1;
				}
			}
			if(!(cor2 && flag2)) {
				eval.evaluateAnswer(cakeButtons.getQuestionColor(1), cake.getAnswer(1), userID);
				if(cor2) {
					flag2 = cor2;
				}
			}
			if(!(cor3 && flag3)) {
				eval.evaluateAnswer(cakeButtons.getQuestionColor(2), cake.getAnswer(2), userID);
				if(cor3) {
					flag3 = cor3;
				}
			}

			feedbacks[0] = eval.getImmediateFeedback(cakeButtons.getNumberColor(0) + 1, cake.getAnswer(0), lessonNumber);
			feedbacks[1] = eval.getImmediateFeedback(cakeButtons.getNumberColor(1) + 1, cake.getAnswer(1), lessonNumber);
			feedbacks[2] = eval.getImmediateFeedback(cakeButtons.getNumberColor(2) + 1, cake.getAnswer(2), lessonNumber);

			if(cor1 && cor2 && cor3) {
				isMistake = false;

				flag1 = false;
				flag2 = false;
				flag3 = false;
				isSubmit = 1;
				nItemsRemaining--;
				rounds++;

				bread = Assets.nothingness;
				frosting = Assets.nothingness;
				sprinkles = Assets.nothingness;
				cake.resetFlags();
			} else {
				isMistake = true;
				wrong = Assets.wrong;

				//cake.resetFlags();
			}

			transition = true;
			ready = Assets.nothingness;
			userRecordOperator.close();
			userLessonProgressOperator.close();
		}
	}

	private String[] sentenceCutter(String sentence) {
		String[] words;
		words = sentence.split(" ");

		return words;
	}

	@Override
	protected void drawReadyUI() {
		// TODO Auto-generated method stub
		Graphics g = game.getGraphics();
		feedboxChef = Assets.feedbox;
		g.drawARGB(200, 0, 0, 0);
		g.drawImage(this.feedboxChef, pDialog.getX(), pDialog.getY());
		g.drawImage(this.recipe, pRecipe.getX(), pRecipe.getY());
		g.drawString("Tap the envelope to get the new", 350, 95, paint4);
		g.drawString("recipe and create the cake!", 350, 115, paint4);
		g.drawString("Tap the cake when it's ready!", 350, 145, paint4);

	}

	@Override
	protected void showExit() {
		// TODO Auto-generated method stub
		Graphics g = game.getGraphics();
		if(exit) {
			g.drawARGB(200, 0, 0, 0);
			g.drawImage(bgBack, this.pBackg.getX(), pBackg.getY());
			g.drawImage(nobtn, this.pNo.getX(), pNo.getY());
			g.drawImage(yesbtn, pYes.getX(), pYes.getY());
		}
	}

	@Override
	protected void drawCustomUI() {
		// TODO Auto-generated method stub
		Graphics g = game.getGraphics();
		g.drawImage(backbtn, 1, 1);
		g.drawString(questions[0], 305, 425, paint2);// sQuestion
		g.drawString(questions[1], 305, 450, paint2);// sQuestion
		g.drawString(questions[2], 305, 475, paint2);// sQuestion
		showTransition();
		showExit();
	}

}