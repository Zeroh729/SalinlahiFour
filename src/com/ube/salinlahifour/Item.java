package com.ube.salinlahifour;

import com.ube.salinlahifour.enumTypes.LevelType;

import android.media.MediaPlayer;
import android.media.SoundPool;
import android.view.View;

public class Item {
	private String word;
	private String english;
	private int voiceFilID;
	private int voiceEngID;
	private String label;
	private LevelType level;
	private int imageID;
	private int q_num;
	private View view;
	
	public Item(int q_num,String word, String english, String description, int imageID, int voiceFilID, int voiceEngID, LevelType level){
		this.q_num = q_num;
		this.word = word;
		this.english = english;
		this.label = description;
		this.voiceFilID = voiceFilID;
		this.voiceEngID = voiceEngID;
		this.level = level;
		this.imageID = imageID;
	}
	public int getQ_num(){
		return q_num;
	}

	public int getImageID() {
		return imageID;
	}

	public void setImageID(int imageID) {
		this.imageID = imageID;
	}
	

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getEnglish() {
		return english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

	public int getVoiceFilID() {
		return voiceFilID;
	}
	
	public int getVoiceEngID() {
		return voiceEngID;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String description) {
		this.label = description;
	}

	public LevelType getLevel() {
		return level;
	}

	public void setLevel(LevelType level) {
		this.level = level;
	}
	
	public void playFilipinoSound(){
		if(voiceFilID != 0){
			MediaPlayer mPlayer = MediaPlayer.create(SalinlahiFour.getContext(), voiceFilID);
			mPlayer.setVolume(1, 1);
			mPlayer.start();
		}
	}
	
	public void playEnglishSound(){
		if(voiceEngID != 0){
			MediaPlayer mPlayer = MediaPlayer.create(SalinlahiFour.getContext(), voiceEngID);
			mPlayer.setVolume(1, 1);
			mPlayer.start();
		}
	}
}
