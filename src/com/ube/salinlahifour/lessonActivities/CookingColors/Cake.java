package com.ube.salinlahifour.lessonActivities.CookingColors;

import java.util.Random;

import android.util.Log;

public class Cake {
	private boolean isBread , isSprinkled, isCream;
	private String[] answer;
	private int X, Y;
	public Cake(){
		answer = new String[3];
		isBread = false;
		isSprinkled = false;
		isCream = false;
		
	}
	
	public void move(int newX, int newY){
		 X = newX;
         Y = newY;
	}
	public void addBead(String bread){
		answer[0] = bread;
		isBread = true;
	}
	public void addCream(String cream){
		answer[1] = cream;
		isCream = true;
	}
	public void addSprinkles(String sprinkles){
		answer[2] = sprinkles;
		isSprinkled = true;
	}
	
	public boolean isBread() {
		return isBread;
	}

	public boolean isSprinkled() {
		return isSprinkled;
	}

	public boolean isCream() {
		return isCream;
	}

	public int getX() {
		return X;
	}

	public int getY() {
		return Y;
	}
	public String getAnswer(int index){
		return answer[index];
	}
	
	
	
	
}
