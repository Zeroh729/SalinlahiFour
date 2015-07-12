package com.ube.salinlahifour.lessonActivities.PartsOFHouse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.content.Intent;

import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Image;
import com.kilobolt.framework.Input.TouchEvent;
import com.ube.salinlahifour.Lesson;
import com.ube.salinlahifour.MapActivity;
import com.ube.salinlahifour.SalinlahiFour;
import com.ube.salinlahifour.evaluationModule.*;
import com.ube.salinlahifour.lessonActivities.AbstractGameScreen;
import com.ube.salinlahifour.lessonActivities.PartsOFHouse.Parts;
import com.ube.salinlahifour.lessonActivities.PartsOFHouse.Assets;

public class GameScreen extends AbstractGameScreen {

	static final String activityName = "House";
	private Image backbtn;
	private Image bg, dialogbox, feedbox;
	private Parts pDialog, pNextBtn;
	private Parts pRoof, pBody, pDoor, pWindow;
	private Parts pGarage, pFence;
	private Parts pChimney, pStairs;
	private Parts pNo, pYes, pBackg;
	private String correctAnswer;
	private int answer = 0, userID;
	private ArrayList<Parts> parts;
	private StringBuilder lineOne, lineTwo, lineTri;

	/**
	 * Class Constructor
	 *
	 * @param game
	 *            the game engine.
	 * @param activityLevel
	 *            the difficulty of the level.
	 * @param userID
	 *            the user ID of the logged in user.
	 * @param context
	 *            context of the activity.
	 * @param lesson
	 *            contains the details related to the game.
	 * @param items
	 *            contains the items of the game.
	 * @param evals
	 *            the evaluation object
	 */
	public GameScreen(Game game, String activityLevel, int userID, Context context, Lesson lesson, Evaluation evals) {
		// Super Parameters Game, ActivityName, ActivityLevel, UserID
		super(game, activityName, activityLevel, userID, context, lesson, evals);
		this.userID = userID;
		lessonNumber = 2;
		rounds = 1;
		lineOne = new StringBuilder();
		lineTwo = new StringBuilder();
		lineTri = new StringBuilder();
	}

	/**
	 * Loads the assets of the game.
	 */
	@Override
	protected void loadAssets() {
		parts = new ArrayList<Parts>();

		backbtn = Assets.backbtn;
		bg = Assets.gamebg;
		dialogbox = Assets.dialogbox;
		exit = false;
		transition = false;

		if(SalinlahiFour.getLoggedInUser().getGender().equals("male")) {
			feedbox = Assets.feedboxBoy;
		} else {
			feedbox = Assets.feedboxGirl;
		}

		pRoof = new Parts(Assets.roof, Assets.roof_choice, Assets.roof_error, Assets.roof_selected, Assets.roofholder, Assets.bubong);
		pRoof.setWord("Bubong");
		pBody = new Parts(Assets.body, Assets.body_choice, Assets.body_error, Assets.body_selected, Assets.bodyholder, Assets.dingding);
		pBody.setWord("Dingding");
		pDoor = new Parts(Assets.door, Assets.door_choice, Assets.door_error, Assets.door_selected, Assets.doorholder, Assets.pinto);
		pDoor.setWord("Pinto");
		pWindow = new Parts(Assets.window, Assets.window_choice, Assets.window_error, Assets.window_selected, Assets.windowholder, Assets.bintana);
		pWindow.setWord("Bintana");

		parts.add(pRoof);
		parts.add(pBody);
		parts.add(pDoor);
		parts.add(pWindow);

		// If the game difficulty is medium or hard, load garage and fence assets.
		if(activityLevel.equals("MEDIUM") || activityLevel.equals("HARD")) {
			pGarage = new Parts(Assets.garage, Assets.garage_choice, Assets.garage_error, Assets.garage_selected, Assets.garageholder, Assets.garahe);
			pGarage.setWord("Garahe");
			pFence = new Parts(Assets.fence, Assets.fence_choice, Assets.fence_error, Assets.fence_selected, Assets.fenceholder, Assets.bakod);
			pFence.setWord("Bakod");

			parts.add(pGarage);
			parts.add(pFence);
		}
		// If the game difficulty is hard, load chimney and stair assets.
		if(activityLevel.equals("HARD")) {
			pChimney = new Parts(Assets.chimney, Assets.chimney_choice, Assets.chimney_error, Assets.chimney_selected, Assets.chimneyholder, Assets.chimnea);
			pChimney.setWord("Tsimenea");
			pStairs = new Parts(Assets.stairs, Assets.stairs_choice, Assets.stairs_error, Assets.stairs_selected, Assets.stairsholder, Assets.hagdan);
			pStairs.setWord("Hagdan");

			parts.add(pChimney);
			parts.add(pStairs);
		}

		pNextBtn = new Parts(Assets.nextBtn, 510, 60);
		pYes = new Parts(Assets.yesbtn, 220, 300);
		pNo = new Parts(Assets.nobtn, 400, 300);
		pBackg = new Parts(Assets.bgBack, 195, 100);
	}

	/**
	 * Sets the parameters when the difficulty is Easy
	 */
	@Override
	protected void assetPositionEasy() {
		eval.setTotScore(4);
		nItemsRemaining = totalItems = 4;
		pDialog = new Parts(65, 40);
		pRoof.move(27, 185);
		pBody.move(480, 150);
		pDoor.move(490, 355);
		pWindow.move(120, 395);

		pRoof.setHolderCoord(222, 179);
		pBody.setHolderCoord(245, 240);
		pDoor.setHolderCoord(269, 265);
		pWindow.setHolderCoord(349, 266);
	}

	/**
	 * Sets the parameters when the difficulty is Medium
	 */
	@Override
	protected void assetPositionMedium() {
		eval.setTotScore(6);
		nItemsRemaining = totalItems = 6;
		pDialog = new Parts(65, 40);
		pRoof.move(230, 130);
		pBody.move(480, 150);
		pDoor.move(510, 270);
		pWindow.move(351, 445);
		pGarage.move(17, 232);
		pFence.move(34, 157);

		pGarage.setHolderCoord(355, 260);
		pFence.setHolderCoord(0, 385);
		pRoof.setHolderCoord(165, 190);
		pBody.setHolderCoord(190, 250);
		pDoor.setHolderCoord(215, 270);
		pWindow.setHolderCoord(290, 273);
	}

	/**
	 * Sets the parameters when the difficulty is Hard
	 */
	@Override
	protected void assetPositionHard() {
		nItemsRemaining = totalItems = 8;
		eval.setTotScore(8);
		pDialog = new Parts(65, 40);
		pRoof.move(383, 425);
		pBody.move(480, 150);
		pDoor.move(498, 270);
		pWindow.move(310, 445);
		pGarage.move(17, 232);
		pFence.move(34, 157);
		pChimney.move(390, 125);
		pStairs.move(590, 280);

		pChimney.setHolderCoord(270, 143);
		pStairs.setHolderCoord(205, 350);
		pGarage.setHolderCoord(345, 265);
		pFence.setHolderCoord(0, 385);
		pRoof.setHolderCoord(165, 170);
		pBody.setHolderCoord(190, 230);
		pDoor.setHolderCoord(213, 250);
		pWindow.setHolderCoord(285, 250);
	}

	/**
	 * Update the game environment for (Easy Difficulty)
	 * 
	 * @param touchEvents
	 *            the list of actions.
	 * @param deltaTime
	 *            the time elapsed
	 */
	@Override
	protected void updateRunningEasy(List<TouchEvent> touchEvents, float deltaTime) {
		updateGame(touchEvents);
	}

	/**
	 * Update the game environment for (Medium Difficulty)
	 * 
	 * @param touchEvents
	 *            the list of actions.
	 * @param deltaTime
	 *            the time elapsed
	 */
	@Override
	protected void updateRunningMedium(List<TouchEvent> touchEvents, float deltaTime) {
		updateGame(touchEvents);
	}

	/**
	 * Update the game environment for (Hard Difficulty)
	 * 
	 * @param touchEvents
	 *            the list of actions.
	 * @param deltaTime
	 *            the time elapsed
	 */
	@Override
	protected void updateRunningHard(List<TouchEvent> touchEvents, float deltaTime) {
		updateGame(touchEvents);
	}

	/**
	 * Update the game environment when the GameState is Running.
	 * 
	 * @param touchEvents
	 *            the list of actions.
	 */
	public void updateGame(List<TouchEvent> touchEvents) {
		// Flag to determine if a House Part is selected.
		boolean partSelected = false;

		// Get the current question and its answer
		sQuestion = items.get(rounds - 1).getQuestion();
		correctAnswer = items.get(rounds - 1).getWord();

		// Traverse all touchEvents
		for(TouchEvent event : touchEvents) {
			// If the event is TOUCH_DOWN (Tap)
			if(event.type == TouchEvent.TOUCH_DOWN) {
				// If Back Button is tapped
				if(inBounds(event, 1, 1, this.backbtn.getWidth(), this.backbtn.getHeight())) {
					pause();
				}
				if(transition) {
					// If next button is pressed transition back to the game.
					if(inBounds(event, pNextBtn.getX(), pNextBtn.getY(), pNextBtn.getWidth(), pNextBtn.getHeight())) {
						transition = false;
					}
				} else {
					// Iterate parts of the house.
					for(Iterator<Parts> iterator = parts.iterator(); iterator.hasNext();) {
						Parts part = iterator.next();

						// If there is a part selected
						if(answer > 0) {
							// If the place holder is selected
							if(inBounds(event, part.getHolderX(), part.getHolderY(), part.getHolderWidth(), part.getHolderHeight())) {
								// Check if the Selected part and where it will be placed is the same and if it is the correct answer.
								if(sAnswer.equals(part.getWord()) && correctAnswer.equals(part.getWord())) {
									// Get feedback
									sFeedback = eval.getImmediateFeedback(lesson.getItems().get(rounds - 1).getID(), sAnswer, lessonNumber);
									nItemsRemaining--;
									rounds++;

									//Place the part to its corresponding place in the house
									part.setPlaced(true);
									//Remove the part from the list (Since its already completed and it won't be needed anymore.)
									iterator.remove();
									transition = true;
								} else {
									part.toChoiceImage();
									sAnswer = "";

									// Get Feedback
									if(answer != lesson.getItems().get(rounds - 1).getID()) {
										sFeedback = eval.getImmediateFeedback(lesson.getItems().get(rounds - 1).getID(), sAnswer, lessonNumber);
									} else {
										sFeedback = "Sorry, that is the wrong position.";
									}
								}
								transition = true;
								evaluateAnswer();
								continue;
							}
						}
						// If the Part of the house is selected, play sound and change image to "selected"
						if(inBounds(event, part.getX(), part.getY(), part.getWidth(), part.getHeight())) {
							answer = 1;
							sAnswer = part.getWord();
							part.getSound().play(0.85f);
							part.toSelectedImage();
							partSelected = true;
							continue;
						} else {
							part.toChoiceImage();
						}
					}
					if(!partSelected) {
						answer = 0;
					}
				}
			}
		}
	}

	/**
	 * Update the game environment when the GameState is Paused.
	 * 
	 * @param touchEvents
	 *            the list of actions.
	 */
	@Override
	protected void updatePaused(List<TouchEvent> touchEvents) {
		// Traverse all touchEvents
		for(TouchEvent event : touchEvents) {
			// If the event is TOUCH_DOWN (Tap)
			if(event.type == TouchEvent.TOUCH_DOWN) {
				// If the No Button is tapped, return to game
				if(inBounds(event, pNo.getX(), pNo.getY(), pNo.getWidth(), pNo.getWidth())) {
					returnToGame();
				} else if(inBounds(event, pYes.getX(), pYes.getY(), pYes.getWidth(), pYes.getWidth())) {
					// If the yes button is tapped, exit game.
					Intent intent = new Intent(context, MapActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					context.startActivity(intent);
				}
			}
		}
	}

	@Override
	protected void updateGameOver(List<TouchEvent> touchEvents) {
		// Not Supported
	}

	/**
	 * Garbage Collecting.
	 * 
	 */
	@Override
	protected void nullify() {
		paint = null;
		paint2 = null;
		bg = null;
		System.gc();
	}

	/**
	 * Draws Assets (Easy Difficulty).
	 * 
	 */
	@Override
	protected void painterEasy() {
		Graphics g = game.getGraphics();

		g.drawImage(bg, 0, 0);
		g.drawImage(dialogbox, pDialog.getX(), pDialog.getY());

		g.drawImage(pDoor.getHolder(), pDoor.getHolderX(), pDoor.getHolderY());
		g.drawImage(pWindow.getHolder(), pWindow.getHolderX(), pWindow.getHolderY());
		g.drawImage(pRoof.getHolder(), pRoof.getHolderX(), pRoof.getHolderY());
		g.drawImage(pBody.getHolder(), pBody.getHolderX(), pBody.getHolderY());

		g.drawImage(pRoof.getImage(), pRoof.getX(), pRoof.getY());
		g.drawImage(pBody.getImage(), pBody.getX(), pBody.getY());
		g.drawImage(pDoor.getImage(), pDoor.getX(), pDoor.getY());
		g.drawImage(pWindow.getImage(), pWindow.getX(), pWindow.getY());
	}

	/**
	 * Draws Assets (Medium Difficulty).
	 * 
	 */
	protected void painterMedium() {
		Graphics g = game.getGraphics();
		painterEasy();

		g.drawImage(pGarage.getHolder(), pGarage.getHolderX(), pGarage.getHolderY());
		g.drawImage(pFence.getHolder(), pFence.getHolderX(), pFence.getHolderY());

		g.drawImage(pFence.getImage(), pFence.getX(), pFence.getY());
		g.drawImage(pGarage.getImage(), pGarage.getX(), pGarage.getY());
	}

	/**
	 * Draws Assets (Hard Difficulty).
	 * 
	 */
	protected void painterHard() {
		Graphics g = game.getGraphics();
		painterMedium();
		g.drawImage(pStairs.getHolder(), pStairs.getHolderX(), pStairs.getHolderY());
		g.drawImage(pChimney.getHolder(), pChimney.getHolderX(), pChimney.getHolderY());

		g.drawImage(pStairs.getImage(), pStairs.getX(), pStairs.getY());
		g.drawImage(pChimney.getImage(), pChimney.getX(), pChimney.getY());
	}

	/**
	 * Draws the Transition Screen.
	 * 
	 */
	@Override
	protected void showTransition() {
		Graphics g = game.getGraphics();

		if(super.transition) {
			g.drawARGB(200, 0, 0, 0);
			g.drawImage(feedbox, this.pDialog.getX(), pDialog.getY());

			String[] cuttedWord = sentenceCutter(sFeedback);
			for(int s = 0; s < cuttedWord.length; s++) {
				if(s > 11) {
					lineTri.append(cuttedWord[s] + " ");
				} else if(s > 6) {
					lineTwo.append(cuttedWord[s] + " ");
				} else {
					lineOne.append(cuttedWord[s] + " ");
				}
			}

			g.drawString(lineOne.toString(), 150, 80, paint);
			g.drawString(lineTwo.toString(), 150, 100, paint);
			g.drawString(lineTri.toString(), 150, 120, paint);

			lineOne.delete(0, lineOne.length());
			lineTwo.delete(0, lineTwo.length());
			lineTri.delete(0, lineTri.length());
			answer = 0;

			g.drawImage(pNextBtn.getImage(), pNextBtn.getX(), pNextBtn.getY());
		}
	}

	/**
	 * Evaluate the user answer.
	 * 
	 */
	private void evaluateAnswer() {
		userRecordOperator.open();
		userLessonProgressOperator.open();
		eval.evaluateAnswer(correctAnswer, sAnswer, userID);
		userRecordOperator.close();
		userLessonProgressOperator.close();
	}

	/**
	 * Splits a string by space characters.
	 * 
	 */
	private String[] sentenceCutter(String sentence) {
		String[] words;
		words = sentence.split("\\s");

		return words;
	}

	/**
	 * Draws the interface when the game is ready to be played.
	 * 
	 */
	@Override
	protected void drawReadyUI() {
		Graphics g = game.getGraphics();
		g.drawARGB(200, 0, 0, 0);
		g.drawImage(feedbox, this.pDialog.getX(), pDialog.getY());

		g.drawString("Tap the pieces asked and place it", 322, 63, paint4);
		g.drawString("on its proper position", 322, 78, paint4);
		g.drawImage(pNextBtn.getImage(), pNextBtn.getX(), pNextBtn.getY());
	}

	/**
	 * Draws the exit menu.
	 * 
	 */
	protected void drawPausedUI() {
		Graphics g = game.getGraphics();
		g.drawARGB(200, 0, 0, 0);
		g.drawImage(pBackg.getImage(), this.pBackg.getX(), pBackg.getY());
		g.drawImage(pNo.getImage(), this.pNo.getX(), pNo.getY());
		g.drawImage(pYes.getImage(), pYes.getX(), pYes.getY());
	}

	@Override
	protected void showExit() {
		// Not Supported
	}

	/**
	 * Draws the question interface.
	 * 
	 */
	@Override
	protected void drawCustomUI() {
		Graphics g = game.getGraphics();
		g.drawImage(backbtn, 1, 1);
		g.drawString(sQuestion, 97, 90, paint);
		showTransition();
	}
}
