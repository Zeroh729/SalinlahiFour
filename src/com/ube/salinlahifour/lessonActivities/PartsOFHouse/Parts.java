package com.ube.salinlahifour.lessonActivities.PartsOFHouse;

import com.kilobolt.framework.Image;
import com.kilobolt.framework.Sound;

public class Parts {
	private int x, y, holderX, holderY;
	private boolean isPlaced;
	private Image placed, choice, error, selected, holder;
	private Sound sound;
	private Image state;
	private String word;

	public Parts(int x, int y) {
		this.x = x;
		this.y = y;

		isPlaced = false;
	}

	public Parts(Image placed, Image choice, Image error, Image selected, Image holder, Sound sound) {
		this.placed = placed;
		this.choice = choice;
		this.error = error;
		this.selected = selected;
		this.sound = sound;
		this.holder = holder;

		state = choice;
	}

	public Parts(Image state, int x, int y) {
		this.state = state;
		this.x = x;
		this.y = y;
	}

	public void setHolderCoord(int x, int y) {
		holderX = x;
		holderY = y;
	}

	public void setAssets(Image placed, Image choice, Image error, Image selected, Sound sound) {
		this.placed = placed;
		this.choice = choice;
		this.error = error;
		this.selected = selected;
		this.sound = sound;

		state = choice;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public boolean isPlaced() {
		return isPlaced;
	}

	public void toErrorImage() {
		state = error;
	}

	public void toSelectedImage() {
		state = selected;
	}

	public void toChoiceImage() {
		state = choice;
	}

	public void setPlaced(boolean isPlaced) {
		this.isPlaced = isPlaced;

		if(isPlaced) {
			x = holderX;
			y = holderY;

			state = placed;
		}
	}

	public void move(int newX, int newY) {
		x = newX;
		y = newY;
	}

	public Image getHolder() {
		return holder;
	}

	public Image getImage() {
		return state;
	}

	public int getWidth() {
		return state.getWidth();
	}

	public int getHeight() {
		return state.getHeight();
	}

	public int getHolderWidth() {
		return holder.getWidth();
	}

	public int getHolderHeight() {
		return holder.getHeight();
	}

	public String getWord() {
		return word;
	}

	public Sound getSound() {
		return sound;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getHolderX() {
		return holderX;
	}

	public int getHolderY() {
		return holderY;
	}

}
