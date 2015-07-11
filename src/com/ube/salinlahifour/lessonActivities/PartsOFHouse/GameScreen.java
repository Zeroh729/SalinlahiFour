package com.ube.salinlahifour.lessonActivities.PartsOFHouse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Image;
import com.kilobolt.framework.Sound;
import com.kilobolt.framework.Input.TouchEvent;
import com.ube.salinlahifour.Item;
import com.ube.salinlahifour.Lesson;
import com.ube.salinlahifour.MapActivity;
import com.ube.salinlahifour.SalinlahiFour;
import com.ube.salinlahifour.evaluationModule.*;
import com.ube.salinlahifour.lessonActivities.AbstractGameScreen;
import com.ube.salinlahifour.lessonActivities.PartsOFHouse.Parts;
import com.ube.salinlahifour.lessonActivities.PartsOFHouse.Assets;

public class GameScreen extends AbstractGameScreen {

	static String activityName = "House";
	private Image backbtn, nobtn, yesbtn, bgBack;
	private Image bg, dialogbox, feedbox, nextBtn;
	private Parts pDialog, p_nextBtn;
	private Parts pRoof, pBody, pDoor, pWindow;
	private Parts pGarage, pFence;
	private Parts pChimney, pStairs;
	private Parts pNo, pYes, pBackg;
	public static Sound v_bakod, v_bintana, v_bubong, v_chimnea, v_dingding, v_garahe, v_hagdan, v_pinto;
	private String correctAnswer;
	private int answer = 0, userID;
	private ArrayList<Parts> parts;

	public GameScreen(Game game, String activityLevel, int userID, Context context, Lesson lesson, ArrayList<Item> items, Evaluation evals) {
		// Super Parameters Game, ActivityName, ActivityLevel, UserID
		super(game, activityName, activityLevel, userID, context, lesson, evals);
		this.userID = userID;
		lessonNumber = 2;
		rounds = 1;

		super.items = items;
	}

	@Override
	protected void loadAssets() {
		parts = new ArrayList<Parts>();

		this.backbtn = Assets.backbtn;
		this.bgBack = Assets.bgBack;
		this.yesbtn = Assets.yesbtn;
		this.nobtn = Assets.nobtn;
		exit = false;
		transition = false;

		if(SalinlahiFour.getLoggedInUser().getGender().equals("male")) {
			feedbox = Assets.feedboxBoy;
		} else {
			feedbox = Assets.feedboxGirl;
		}

		bg = Assets.gamebg;
		dialogbox = Assets.dialogbox;

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
		
		if(activityLevel.equals("MEDIUM") || activityLevel.equals("HARD")) {
			pGarage = new Parts(Assets.garage, Assets.garage_choice, Assets.garage_error, Assets.garage_selected, Assets.garageholder, Assets.garahe);
			pGarage.setWord("Garahe");
			pFence = new Parts(Assets.fence, Assets.fence_choice, Assets.fence_error, Assets.fence_selected, Assets.fenceholder, Assets.bakod);
			pFence.setWord("Bakod");
			
			parts.add(pGarage);
			parts.add(pFence);
		}
		if(activityLevel.equals("HARD")) {
			pChimney = new Parts(Assets.chimney, Assets.chimney_choice, Assets.chimney_error, Assets.chimney_selected, Assets.chimneyholder, Assets.chimnea);
			pChimney.setWord("Tsimenea");
			pStairs = new Parts(Assets.stairs, Assets.stairs_choice, Assets.stairs_error, Assets.stairs_selected, Assets.stairsholder, Assets.hagdan);
			pStairs.setWord("Hagdan");
			
			parts.add(pChimney);
			parts.add(pStairs);
		}
		
		nextBtn = Assets.nextBtn;
		p_nextBtn = new Parts(510, 60);
		pYes = new Parts(220, 300);
		pNo = new Parts(400, 300);
		pBackg = new Parts(195, 100);
	}

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

	@Override
	protected void updateRunningEasy(List<TouchEvent> touchEvents, float deltaTime) {
		updateGame(touchEvents);
	}

	@Override
	protected void updateRunningMedium(List<TouchEvent> touchEvents, float deltaTime) {
		updateGame(touchEvents);
	}

	@Override
	protected void updateRunningHard(List<TouchEvent> touchEvents, float deltaTime) {
		updateGame(touchEvents);
	}

	public void updateGame(List<TouchEvent> touchEvents) {
		boolean partSelected = false;
		sQuestion = lesson.getItems().get(rounds - 1).getQuestion();

		correctAnswer = lesson.getItems().get(rounds - 1).getWord();
		for(TouchEvent event : touchEvents) {
			if(event.type == TouchEvent.TOUCH_DOWN) {
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
					if(inBounds(event, p_nextBtn.getX(), p_nextBtn.getY(), nextBtn.getWidth(), nextBtn.getHeight())) {
						transition = false;
					}
				}
			} else {
				for(Iterator<Parts> iterator = parts.iterator(); iterator.hasNext();) {
					Parts part = iterator.next();
					
					if(answer > 0) {
						if(inBounds(event, part.getHolderX(), part.getHolderY(), part.getHolderWidth(), part.getHolderHeight())) {
							// If Correct
							if(sAnswer.equals(part.getWord()) && correctAnswer.equals(part.getWord())) {
								sFeedback = eval.getImmediateFeedback(lesson.getItems().get(rounds - 1).getID(), sAnswer, lessonNumber);
								nItemsRemaining--;
								rounds++;

								part.setPlaced(true);
								iterator.remove();
								transition = true;
							} else {
								part.toChoiceImage();
								sAnswer = "";

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
		// Call garbage collector to clean up memory.
		System.gc();
	}

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

	protected void painterMedium() {
		Graphics g = game.getGraphics();
		painterEasy();

		g.drawImage(pGarage.getHolder(), pGarage.getHolderX(), pGarage.getHolderY());
		g.drawImage(pFence.getHolder(), pFence.getHolderX(), pFence.getHolderY());
		
		g.drawImage(pFence.getImage(), pFence.getX(), pFence.getY());
		g.drawImage(pGarage.getImage(), pGarage.getX(), pGarage.getY());
	}

	protected void painterHard() {
		Graphics g = game.getGraphics();
		painterMedium();
		g.drawImage(pStairs.getHolder(), pStairs.getHolderX(), pStairs.getHolderY());
		g.drawImage(pChimney.getHolder(), pChimney.getHolderX(), pChimney.getHolderY());

		g.drawImage(pStairs.getImage(), pStairs.getX(), pStairs.getY());
		g.drawImage(pChimney.getImage(), pChimney.getX(), pChimney.getY());
	}

	@Override
	protected void showTransition() {
		// TODO Auto-generated method stub
		Graphics g = game.getGraphics();

		if(super.transition) {
			g.drawARGB(200, 0, 0, 0);
			g.drawImage(feedbox, this.pDialog.getX(), pDialog.getY());

			String lineOne = "", lineTwo = "", lineTri = "";
			String[] cuttedWord = sentenceCutter(sFeedback);
			for(int s = 0; s < cuttedWord.length; s++) {
				if(s > 11) {
					lineTri += cuttedWord[s] + " ";
				} else if(s > 6) {
					lineTwo += cuttedWord[s] + " ";
				} else {
					lineOne += cuttedWord[s] + " ";
				}
			}

			g.drawString(lineOne, 150, 80, paint);
			g.drawString(lineTwo, 150, 100, paint);
			g.drawString(lineTri, 150, 120, paint);

			answer = 0;

			g.drawImage(nextBtn, p_nextBtn.getX(), p_nextBtn.getY());
		}
	}

	private void evaluateAnswer() {
		userRecordOperator.open();
		userLessonProgressOperator.open();
		eval.evaluateAnswer(correctAnswer, sAnswer, userID);
		userRecordOperator.close();
		userLessonProgressOperator.close();
	}

	private String[] sentenceCutter(String sentence) {
		String[] words;
		words = sentence.split("\\s");

		return words;
	}

	@Override
	protected void drawReadyUI() {
		// TODO Auto-generated method stub
		Graphics g = game.getGraphics();
		g.drawARGB(200, 0, 0, 0);
		g.drawImage(feedbox, this.pDialog.getX(), pDialog.getY());

		g.drawString("Tap the pieces asked and place it", 322, 63, paint4);
		g.drawString("on its proper position", 322, 78, paint4);
		g.drawImage(nextBtn, p_nextBtn.getX(), p_nextBtn.getY());
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
		g.drawString(sQuestion, 97, 90, paint);
		showTransition();
		showExit();
	}

}
