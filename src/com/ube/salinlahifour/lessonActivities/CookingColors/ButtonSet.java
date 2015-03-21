package com.ube.salinlahifour.lessonActivities.CookingColors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import android.util.Log;

public class ButtonSet {
	
	private int margin = 5;
	private int size;
	private int[] X, Y;
	private int width, height; 
	private int[] chosenColors;
	private ArrayList<String> fColors;
	private int qColor;
	private ArrayList<String> fQuestions;
	
	private Random rand;
	public ButtonSet(int size, int initX,int initY){
		Log.d("ButtonSet", "Initializing ButtonSet");
		Log.d("ButtonSet", "Size: " + size +" initX: " + initX + " initY: " + initY );
		this.size = size;
		X = new int[size];
		Y = new int[size];
		X[0] = initX;
		Y[0] = initY;
		Log.d("ButtonSet", "Initializing ButtonSet...Done");
		rand = new Random();
		
	}
	
	public void calculateButtonPosition(int buttonWidth, int buttonHeight, int column, int margin){
		int x = X[0],y = Y[0], index = 0;
		Log.d("ButtonSet", "Calculating Button Position");
		Log.d("ButtonSet", "Size: " + size);
		Log.d("ButtonSet", "Width: " + buttonWidth + " Height: " + buttonHeight + " Column: " + column );
		for(int i = 0; i<size/column; i++){
			//Log.d("ButtonSet", "i: " + i);
			for(int c = 0; c < column; c++){
				Log.d("ButtonSet", "i: " + i + " c: " + c);
				Log.d("ButtonSet", "Loading Index:  " + index);
				if(index == 0){
					Log.d("ButtonSet", "its zero");
					Log.d("ButtonSet", "Placing index: "+ index + " (X,Y) "+ X[index] + "," + Y[index]);
					x += buttonWidth+margin;
					Log.d("ButtonSet", "its zero: " + x);
				}else{
					Log.d("ButtonSet", "Not Zero");
					X[index] = x; 
					Y[index] = y;
					Log.d("ButtonSet", "Placing index: "+ index + " (X,Y) "+ X[index] + "," + Y[index]);
					x += buttonWidth;
					Log.d("ButtonSet", "Index: " + index +" X[]: " + X[index] );
					Log.d("ButtonSet", "Index: " + index +" Y[]: " + Y[index] );
					Log.d("ButtonSet", "Temp x: " + x );
				}
				if(c == column-1){
					Log.d("ButtonSet", "Dulo na ng column");
					x = X[0]; //initial location
					y += buttonHeight+margin;
					Log.d("ButtonSet", "x: " + x + " y: " + y);
				}
				index++;
			}
		}
		Log.d("ButtonSet", "Calculating Button Position...Done");
	}
	
	public int getX(int quadrant){
		return X[quadrant];
	}
	public int getY(int quadrant){
		return Y[quadrant];
	}
	public int getInitX(){
		return X[0];
	}
	public int getInitY(){
		return Y[0];
	}
	public void loadRandomColors(int nColors){
		
		chosenColors = new int[nColors];
		ArrayList<Integer> tempColors = new ArrayList<Integer>();
		for(int i= 0; i<nColors; i++){
			//chosenColors[i] = i;
			tempColors.add(i);
		}
		
		
		
		Collections.shuffle(tempColors);
		Log.d("Randomizer", tempColors.toString());
		
		for(int i=0; i<nColors;i++){
			chosenColors[i] = tempColors.get(i);
		}
		//chosenColors = tempColors.toArray();
		Log.d("Randomizer", Arrays.toString(chosenColors));
		/*int temp = 0;
		int i = 0;
		while(i<nColors){
			
			temp = rand.nextInt(nColors-1);
			Log.d("Cake: Random", "Temp int: " + temp);

			if(checkRandomDuplicate(temp) == true){
			chosenColors[i] = temp;
			Log.d("Cake: Random", "Random int: " + chosenColors[i]);
			i++;
			}
		}*/
	}
	
	public int getChosenColors(int index){
		return chosenColors[index];
	}
	public void loadAnswer(String ActivityLevel){
		fColors = new ArrayList<String>();
		switch(ActivityLevel){
		case "HARD": 
			easyAns();
			medAns();
			hardAns();
		case "MEDIUM":
			easyAns();
			medAns();
		case "EASY":
			easyAns();
		}
	}
	
	private void easyAns(){
		fColors.add("Asul");
		fColors.add("Berde");
		fColors.add("Pula");
		fColors.add("Dilaw");
	}
	private void medAns(){
		fColors.add("Kayumanggi");
		fColors.add("Lila");
	}
	private void hardAns(){
		fColors.add("Itim");
		fColors.add("Puti");
	}
	public String getAnswer(int colorIndex){
		return fColors.get(colorIndex);
	}
	public void loadQuestions(){
		fQuestions = new ArrayList<String>();
		fQuestions.add("Cake");
		fQuestions.add("Frosting");
		fQuestions.add("Sprinkles");
	}
	public String createQuestions(String actlevel, int nQuestion ){
		int randColor;
		switch(actlevel){
		case "EASY":
		randColor =  rand.nextInt(fColors.size());
		qColor = randColor;
		break;
		case "MEDIUM":
		case "HARD":
			randColor =  rand.nextInt(3);
			qColor = this.getChosenColors(randColor);
			Log.d("Random Question Debug", "qColor: "+ qColor + " randColor: "+ randColor);
			break;

		}
		return fColors.get(qColor) + " na "+ fQuestions.get(nQuestion);
	}
	public String getQuestionColor(){
		return fColors.get(qColor);
	}
	public int getNumberColor(){
		return qColor;
	}
	
}
