package com.ube.salinlahifour;

import android.media.SoundPool;

public class Item {
	private String word;
	private String english;
	private SoundPool voiceover;
	private String description;
	private LevelType level;
	
	public Item(String word, String english, String description, SoundPool voiceover, LevelType level){
		this.word = word;
		this.english = english;
		this.description = description;
		this.voiceover = voiceover;
		this.level = level;
	}
}
