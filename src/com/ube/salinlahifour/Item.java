package com.ube.salinlahifour;

import android.media.SoundPool;

public class Item {
	private String word;
	private String english;
	private SoundPool voiceover;
	private String label;
	private LevelType level;
	
	public Item(String word, String english, String description, SoundPool voiceover, LevelType level){
		this.word = word;
		this.english = english;
		this.label = description;
		this.voiceover = voiceover;
		this.level = level;
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

	public SoundPool getVoiceover() {
		return voiceover;
	}

	public void setVoiceover(SoundPool voiceover) {
		this.voiceover = voiceover;
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
	
}
