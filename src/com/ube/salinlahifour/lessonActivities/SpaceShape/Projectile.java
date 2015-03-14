package com.ube.salinlahifour.lessonActivities.SpaceShape;

import android.util.Log;

public class Projectile {
	private int initX, initY;
	private int enX, enY, enW, enH;
	private int curX= 0, curY=0;
	private boolean isHit;
	
	public Projectile(){
		isHit = false;
	}
	public void loadAmmos(int initX, int initY){
		this.initX = initX;
		this.initY = initY;
		curX = initX;
		curY = initY;
		isHit = false;
	}
	public void lockTarget(int x, int y, int width, int height){
		enX = x;
		enY = y;
		enW = width;
		enH = height;
	}
	public void shoot(){
		int enemy = enY;
		Log.d("Projectile", "curY: " + curY + " enY: " + enY);
		while(curY >= enemy){
			Log.d("Projectile", "shoot");
			curY--;
			if(curY == enemy){
				isHit = true;
			}
		}
	}
	public void setCurX(int x){
		curX = x;
	}
	public void setCurY(int y){
		curY = y;
	}
	public int getCurX(){
		return curX;
	}
	public int getCurY(){
		return curY;
	}
	public boolean isHit(){
		return isHit;
	}
	public void setIsHit(boolean isHit){
		this.isHit = isHit;
	}
}
