package com.ube.salinlahifour.lessonActivities.PartsOFHouse;

public class Parts {
	private int x,y,moveX,moveY;
	private boolean isPlaced;
	
	public Parts(int x, int y){
		this.x = x;
		this.y = y;
		
		isPlaced = false;
	}
	
	public boolean isPlaced() {
		return isPlaced;
	}

	public void setPlaced(boolean isPlaced) {
		this.isPlaced = isPlaced;
	}

	
	
	public void move(int newX, int newY){
		//moveX = newX;
		//moveY = newY;
		 x = newX;
         y = newY;
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	
	
}
