package com.ube.salinlahifour;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.View;

import com.ube.salinlahifour.enumTypes.LevelType;

/**
 * An Item is an object that contains all the needed information in the game. An Item contains the Filipino word along with
 * it's English translation. In addition, it contains the question and its answer and all the needed resources in one object.
 *
 */
public class Item implements Cloneable {
	private int ID;
	private int q_num;
	private String word = "";
	private String english = "";
	private String question = "";
	private String tutorial_note = "";
	private String word_hint = "";
	private String voiceEngPath = "";
	private String voiceFilPath = "";
	private String imagePath = "";
	private String difficulty = "";
	private int lessonNum;

	private int voiceFilID;
	private int voiceEngID;
	private String label;
	private LevelType level;
	private int imageID;
	private View view;

	/**
	 * Creates a blank instance of an Item
	 */
	public Item() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	public Item clone() {
		try {
			Item item = (Item) super.clone();
			return item;
		} catch(Exception e) {
			return null;
		}
	}

	/**
	 * @param q_num
	 *            The question number
	 * @param word
	 *            The filipino word
	 * @param english
	 *            the English word
	 * @param description
	 *            the description of the word
	 * @param imageID
	 *            the image ID associated with the item
	 * @param voiceFilID
	 *            the ID of the audio file of the Filipino voice
	 * @param voiceEngID
	 *            the ID of the audio file of the English voice
	 * @param level
	 *            the difficulty of the level
	 */
	public Item(int q_num, String word, String english, String description, int imageID, int voiceFilID, int voiceEngID, LevelType level) {
		this.q_num = q_num;
		this.word = word;
		this.english = english;
		this.label = description;
		this.voiceFilID = voiceFilID;
		this.voiceEngID = voiceEngID;
		this.level = level;
		this.imageID = imageID;
	}

	/**
	 * Sets the ID of the item
	 * 
	 * @param num
	 *            A number that represents the ID of the Item.
	 */
	public void setID(int num) {
		this.ID = num;
	}

	/**
	 * Gets the ID of the item
	 * 
	 * @return the ID of the Item
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Gets the Question number associated with the item.
	 * 
	 * @return The question number of the item.
	 */
	public int getQ_num() {
		return q_num;
	}

	/**
	 * Sets the Question number associated with the item.
	 * 
	 * @param num
	 *            The question number of the item.
	 */
	public void setQ_num(int num) {
		this.q_num = num;
	}

	/**
	 * Gets the question of the item
	 * 
	 * @return the question of assiciated with the item
	 */
	public String getQuestion() {
		return this.question;
	}

	/**
	 * Sets the question of the item.
	 * 
	 * @param question
	 *            A string that represents the question
	 */
	public void setQuestion(String question) {
		this.question = question;
		this.label = question;
	}

	/**
	 * Gets the details related to the question
	 * 
	 * @return The details of the question
	 */
	public String getNote() {
		return this.word_hint;
	}

	/**
	 * Gets the details related to the question
	 * 
	 * @return The details of the question
	 */
	public String getRealNote() {
		return this.word_hint;
	}

	/**
	 * Sets the details related to the question
	 * 
	 * @param note
	 *            A string representation of the note.
	 */
	public void setNote(String note) {
		tutorial_note = note;
	}

	/**
	 * Gets the hint related to the item
	 * 
	 * @return Returns a string representation of the hint.
	 */
	public String getHint() {
		return this.tutorial_note;
	}

	/**
	 * Gets the hint related to the item
	 * 
	 * @return Returns a string representation of the hint.
	 */
	public String getRealHint() {
		return this.word_hint;
	}

	/**
	 * Sets the hint related to the item
	 * 
	 * @param hint
	 *            A string representation of the hint.
	 */
	public void setHint(String hint) {
		word_hint = hint;
	}

	/**
	 * Gets the path of the audio file of the Filipino voice.
	 * 
	 * @return The path of the audio file.
	 */
	public String getVoiceFilPath() {
		return this.voiceFilPath;
	}

	/**
	 * Sets the path of the audio file of the Filipino voice.
	 * 
	 * @param context
	 *            the context of the activity
	 * @param path
	 *            the path of the audio file
	 */
	public void setVoiceFilPath(Context context, String path) {
		voiceFilPath = path;
		voiceFilID = SalinlahiFour.getContext().getResources().getIdentifier(path, "raw", SalinlahiFour.getContext().getPackageName());
		if(voiceFilID == 0 && !path.equals("")) {
			SalinlahiFour.errorPopup(context, "File not found:", "Add " + path + " sound file in raw resource folder.");
		}
	}

	/**
	 * Gets the path of the audio file of the English voice.
	 * 
	 * @return The path of the audio file.
	 */
	public String getVoiceEngPath() {
		return this.voiceEngPath;
	}

	/**
	 * Sets the path of the audio file of the English voice.
	 * 
	 * @param context
	 *            the context of the activity
	 * @param path
	 *            the path of the audio file
	 */
	public void setVoiceEngPath(Context context, String path) {
		voiceEngPath = path;
		voiceEngID = SalinlahiFour.getContext().getResources().getIdentifier(path, "raw", SalinlahiFour.getContext().getPackageName());
		if(voiceEngID == 0 && !path.equals("")) {
			SalinlahiFour.errorPopup(context, "File not found:", "Add " + path + " sound file in raw resource folder.");
		}
	}

	/**
	 * Gets the path of the image file related to the item.
	 * 
	 * @return The path of the image file.
	 */
	public String getImagePath() {
		return this.imagePath;
	}

	/**
	 * Sets the path of the image file related to the item
	 * 
	 * @param context
	 *            the context of the activity
	 * @param path
	 *            the path of the image file
	 */
	public void setImagePath(Context context, String path) {
		imagePath = path;
		int resID = SalinlahiFour.getContext().getResources().getIdentifier(imagePath, "drawable", SalinlahiFour.getContext().getPackageName());

		if(resID == 0 && !path.equals("")) {
			SalinlahiFour.errorPopup(context, "Parse error: ", "Add image '" + path + "' to the drawable resource folder.");
		} else {
			imageID = resID;
		}

	}

	/**
	 * Gets the difficulty of the item
	 * 
	 * @return The difficulty of the item.
	 */
	public String getDifficulty() {
		return this.difficulty;
	}

	/**
	 * Sets the difficulty of the item
	 * 
	 * @param level
	 *            The difficulty of the item.
	 */
	public void setDifficulty(String level) {
		difficulty = level;
	}

	/**
	 * Gets the lesson number of the item
	 * 
	 * @return the lesson number
	 */
	public int getLessonNum() {
		return this.lessonNum;
	}

	/**
	 * Sets the lesson number of the item
	 * 
	 * @param id
	 *            the lesson number
	 */
	public void setLessonNum(int id) {
		lessonNum = id;
	}

	/**
	 * Gets the image ID of the item
	 * 
	 * @return the Image ID
	 */
	public int getImageID() {
		return imageID;
	}

	/**
	 * Gets the Filipino word
	 * 
	 * @return the word/label
	 */
	public String getWord() {
		return word;
	}

	/**
	 * Sets the Filipino word
	 * 
	 * @param word
	 *            The word to be assiciated
	 */
	public void setWord(String word) {
		this.word = word;
	}

	/**
	 * Gets the English translation of the word
	 * 
	 * @return the english word
	 */
	public String getEnglish() {
		return english;
	}

	/**
	 * Sets the English translation of the word.
	 * 
	 * @param english
	 *            the English translation
	 */
	public void setEnglish(String english) {
		this.english = english;
	}

	/**
	 * Gets the ID of the Filipino audio file
	 * 
	 * @return the Voice ID
	 */
	public int getVoiceFilID() {
		return voiceFilID;
	}

	/**
	 * Gets the ID of the English audio file
	 * 
	 * @return the Voice ID
	 */
	public int getVoiceEngID() {
		return voiceEngID;
	}

	/**
	 * Gets the label/description of the item.
	 * 
	 * @return the label of the item
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Sets the label of the item.
	 * 
	 * @param description
	 *            the label.
	 */
	public void setLabel(String description) {
		this.label = description;
	}

	/**
	 * Gets the difficulty of the item
	 * 
	 * @return the difficulty of the item.
	 */
	public String getLevel() {
		return difficulty;
	}

	/**
	 * Sets the level type/Difficulty of the item
	 * 
	 * @param level
	 *            The difficulty of the item
	 */
	public void setLevel(LevelType level) {
		this.level = level;
	}

	/**
	 * Plays the Filipino audio file
	 */
	public void playFilipinoSound() {
		if(voiceFilID != 0) {
			MediaPlayer mPlayer = MediaPlayer.create(SalinlahiFour.getContext(), voiceFilID);
			mPlayer.setVolume(1, 1);
			mPlayer.start();
		}
	}

	/**
	 * Plays the English audio file
	 */
	public void playEnglishSound() {
		if(voiceEngID != 0) {
			MediaPlayer mPlayer = MediaPlayer.create(SalinlahiFour.getContext(), voiceEngID);
			mPlayer.setVolume(1, 1);
			mPlayer.start();
		}
	}
}
