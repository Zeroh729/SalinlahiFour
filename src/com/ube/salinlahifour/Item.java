package com.ube.salinlahifour;

import com.ube.salinlahifour.enumTypes.LevelType;

import android.media.MediaPlayer;
import android.media.SoundPool;
import android.view.View;

public class Item {
	private int ID;
	private int q_num;
	private String word;
	private String english;
	private String question;
	private String tutorial_note;
	private String word_hint;
	private String voiceEngPath;
	private String voiceFilPath;
	private String imagePath;
	private String difficulty;
	private int lessonNum;
	
	private int voiceFilID;
	private int voiceEngID;
	private String label;
	private LevelType level;
	private int imageID;
	private View view;
	
	public Item(){
		
	}
	
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
	public void setID(int num){
		this.ID = num;
	}
	public int getID(){
		return ID;
	}
	public int getQ_num(){
		return q_num;
	}
	public void setQ_num(int num){
		this.q_num = num;
	}
	public String getQuestion(){
		return this.question;
	}
	public void setQuestion(String question){
		this.question = question;
	}
	public String getNote(){
		return this.tutorial_note;
	}
	public void setNote(String note){
		tutorial_note = note;
	}
	public String getHint(){
		return this.word_hint;
	}
	public void setHint(String hint){
		word_hint = hint;
	}
	public String getVoiceFilPath() {
		return this.voiceFilPath;
	}
	public void setVoiceFilPath(String path){
		voiceFilPath = path;
	}
	public String getVoiceEngPath() {
		return this.voiceEngPath;
	}
	public void setVoiceEngPath(String path){
		voiceEngPath = path;
	}
	public String getImagePath() {
		return this.imagePath;
	}
	public void setImagePath(String path){
		imagePath = path;
	}
	public String getDifficulty() {
		return this.difficulty;
	}
	public void setDifficulty(String level){
		difficulty = level;
	}
	public int getLessonNum() {
		return this.lessonNum;
	}
	public void setLessonNum(int id){
		lessonNum = id;
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
