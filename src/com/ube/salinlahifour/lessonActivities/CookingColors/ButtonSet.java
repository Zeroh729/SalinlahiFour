package com.ube.salinlahifour.lessonActivities.CookingColors;

import java.util.ArrayList;
import java.util.Random;

import com.ube.salinlahifour.Lesson;

public class ButtonSet {

	private int size;
	private int[] creamColors, creamX, creamY;
	private int[] breadColors, breadX, breadY;
	private int[] sprinkleColors, sprinkleX, sprinkleY;
	private int[] qColor;
	private ArrayList<String> fColors;
	private ArrayList<String> fQuestions;
	private Random rand;
	private int sizeHard;
	private int sizeMed;
	private int sizeEasy;
	private int pseudoSeed1, pseudoSeed2, pseudoSeed3, pseudoSeed4;
	private Lesson lesson;

	public ButtonSet(int size, Lesson lesson) {
		pseudoSeed1 = pseudoSeed2 = pseudoSeed3 = pseudoSeed4 = 3;
		this.size = size;
		creamX = new int[size];
		creamY = new int[size];

		breadX = new int[size];
		breadY = new int[size];

		sprinkleX = new int[size];
		sprinkleY = new int[size];

		qColor = new int[3];

		this.lesson = lesson;

		rand = new Random();
		for(int i = 0; i < lesson.getItems().size(); i++) {
			switch (lesson.getItems().get(i).getDifficulty()) {
				case "HARD":
					sizeHard++;
					break;
				case "MEDIUM":
					sizeMed++;
					break;
				case "EASY":
					sizeEasy++;
					break;
			}
		}
	}

	public void initializeCoords(int breadX, int breadY, int creamX, int creamY, int sprinkleX, int sprinkleY) {
		this.creamX[0] = creamX;
		this.creamY[0] = creamY;

		this.breadX[0] = breadX;
		this.breadY[0] = breadY;

		this.sprinkleX[0] = sprinkleX;
		this.sprinkleY[0] = sprinkleY;

	}

	public void calculateButtonPosition(int buttonType, int buttonWidth, int buttonHeight, int column, int margin) {
		int[] X, Y;

		switch (buttonType) {
			case 0:
				X = breadX;
				Y = breadY;
				break;
			case 1:
				X = creamX;
				Y = creamY;
				break;
			case 2:
				X = sprinkleX;
				Y = sprinkleY;
				break;
			default:
				X = Y = null;
		}
		int x = X[0], y = Y[0], index = 0;

		for(int i = 0; i < size / column; i++) {
			for(int c = 0; c < column; c++) {
				if(index == 0) {
					x += buttonWidth + margin;
				} else {
					X[index] = x;
					Y[index] = y;

					x += buttonWidth;
				}
				if(c == column - 1) {
					x = X[0]; // initial location
					y += buttonHeight + margin;
				}
				index++;
			}
		}
	}

	public int getBreadX(int quadrant) {
		return breadX[quadrant];
	}

	public int getBreadY(int quadrant) {
		return breadY[quadrant];
	}

	public int getCreamX(int quadrant) {
		return creamX[quadrant];
	}

	public int getCreamY(int quadrant) {
		return creamY[quadrant];
	}

	public int getSprinkleX(int quadrant) {
		return sprinkleX[quadrant];
	}

	public int getSprinkleY(int quadrant) {
		return sprinkleY[quadrant];
	}

	public int getInitBreadX() {
		return breadX[0];
	}

	public int getInitBreadY() {
		return breadY[0];
	}

	public int getInitCreamX() {
		return creamX[0];
	}

	public int getInitCreamY() {
		return creamY[0];
	}

	public int getInitSprinkleX() {
		return sprinkleX[0];
	}

	public int getInitSprinkleY() {
		return sprinkleY[0];
	}

	private void loadColors(int[] array, ArrayList<Integer> colorList, ArrayList<Integer> diffColors) {
		ArrayList<Integer> tempColors = (ArrayList<Integer>) colorList.clone();
		ArrayList<Integer> tempDColors = (ArrayList<Integer>) diffColors.clone();

		for(int i = 0; i < 4; i++) {
			if(rand.nextInt(2) == 1 && !tempDColors.isEmpty()) {
				array[i] = tempDColors.remove(rand.nextInt(tempDColors.size()));
			} else {
				array[i] = tempColors.remove(rand.nextInt(tempColors.size()));
			}
		}
	}

	public void loadRandomColors(int nColors) {
		ArrayList<Integer> colorList = new ArrayList<Integer>();

		creamColors = new int[4];
		breadColors = new int[4];
		sprinkleColors = new int[4];

		ArrayList<Integer> diffColors = new ArrayList<Integer>();
		for(int i = 0; i < nColors; i++) {
			if(i >= nColors - 2) {
				diffColors.add(i);
			} else {
				colorList.add(i);
			}
		}

		loadColors(breadColors, colorList, diffColors);
		loadColors(creamColors, colorList, diffColors);
		loadColors(sprinkleColors, colorList, diffColors);
	}

	public int getBreadColor(int i) {
		return breadColors[i];
	}

	public int getCreamColor(int i) {
		return creamColors[i];
	}

	public int getSprinkleColor(int i) {
		return sprinkleColors[i];
	}

	public void loadAnswer(String ActivityLevel) {
		fColors = new ArrayList<String>();
		switch (ActivityLevel) {
			case "HARD":
				for(int i = 0; i < sizeHard + sizeMed + sizeEasy; i++) {
					fColors.add(lesson.getItems().get(i).getQuestion());
				}
				break;
			case "MEDIUM":
				for(int i = 0; i < sizeMed + sizeEasy; i++) {
					fColors.add(lesson.getItems().get(i).getQuestion());
				}
				break;
			case "EASY":
				for(int i = 0; i < sizeEasy; i++) {
					fColors.add(lesson.getItems().get(i).getQuestion());
				}
				break;
		}
	}

	public String getAnswer(int colorIndex) {
		return fColors.get(colorIndex);
	}

	public void loadQuestions() {
		fQuestions = new ArrayList<String>();
		fQuestions.add("Bread");
		fQuestions.add("Frosting");
		fQuestions.add("Sprinkles");
	}

	private int getChosenColor(int[] colorList, int index) {
		if(pseudoSeed1 > index) {
			pseudoSeed1--;
			pseudoSeed2++;
			pseudoSeed3++;
			pseudoSeed4++;
			return colorList[0];
		} else if(pseudoSeed1 + pseudoSeed2 > index) {
			pseudoSeed1++;
			pseudoSeed2--;
			pseudoSeed3++;
			pseudoSeed4++;
			return colorList[1];
		} else if(pseudoSeed1 + pseudoSeed2 + pseudoSeed3 > index) {
			pseudoSeed1++;
			pseudoSeed2++;
			pseudoSeed3--;
			pseudoSeed4++;
			return colorList[2];
		} else {
			pseudoSeed1++;
			pseudoSeed2++;
			pseudoSeed3++;
			pseudoSeed4--;
			return colorList[3];
		}
	}

	public String createQuestions(String actlevel, int nQuestion) {
		int randColor;

		switch (actlevel) {
			case "EASY":
				randColor = rand.nextInt(fColors.size());
				qColor[nQuestion] = randColor;
				break;
			case "MEDIUM":
			case "HARD":
				randColor = rand.nextInt(pseudoSeed1 + pseudoSeed2 + pseudoSeed3 + pseudoSeed4);
				if(nQuestion == 0) {
					qColor[nQuestion] = getChosenColor(breadColors, randColor);
				} else if(nQuestion == 1) {
					qColor[nQuestion] = getChosenColor(creamColors, randColor);
				} else {
					qColor[nQuestion] = getChosenColor(sprinkleColors, randColor);
				}
				break;
		}
		return fColors.get(qColor[nQuestion]) + " na " + fQuestions.get(nQuestion);
	}

	public String getQuestionColor(int index) {
		return fColors.get(qColor[index]);
	}

	public int getNumberColor(int index) {
		return qColor[index];
	}
}
