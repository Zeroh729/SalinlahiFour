package com.ube.salinlahifour.narrativeStory;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.RelativeLayout;

import com.ube.salinlahifour.R;
import com.ube.salinlahifour.SalinlahiFour;
import com.ube.salinlahifour.narrativeDialog.Character;

public class StoryPlayer {
	private NarrativeStory story;
	private int page = 0;
	private int totalPages;
	private Context context;
	private RelativeLayout parentView;
	private String lessonName;
	private int voiceID = 1;
	private ArrayList<Character> characters;
	
	public StoryPlayer(String lessonName, Context context){
		story = SalinlahiFour.getStory(lessonName);
		this.lessonName = lessonName;
		totalPages = story.getActions().size();
		this.context = context;
		parentView = (RelativeLayout)((Activity)context).findViewById(R.id.parent_view);
		Log.d("TEST0", "Total Pages: " + totalPages);
	}
	
	public void nextPage(){
		if(page++ >= totalPages-1){
			((Activity)context).finish();
		}else
			updateScene();
	}
	
	public void updateScene(){
		Log.d("TEST0", "Story Player : stories size: " + story.getActions().size());
		for(int i = 0; i < story.getActions().get(page).getCharacters().size(); i++){ //how many characters moved in that line
			executeEvent(i);
		}
		
		int resID = story.getActions().get(page).getBackgroundResID();
		if(resID != 0){
			parentView.setBackgroundResource(resID);
		}
	}
	
	public void setCharacter(ArrayList<Character> character){
		this.characters = character;
	}
	
	public void executeEvent(int actionIndex){
		Action event = story.getActions().get(page);	
		
		if(story.getActions().get(page).getCharacters().get(actionIndex) != null){
			Character character = story.getActions().get(page).getCharacters().get(actionIndex);
			Character movingcharacter = null;
			
			Log.d("TEST0", "Story Player: Character count... " + characters.size());
			
			for(int i = 0; i < characters.size(); i++){
				Log.d("TEST0", "Story Player: comparing... " + characters.get(i).getMainName() + " & " + character.getMainName());
				if(characters.get(i).getMainName().equals(character.getMainName())){
					movingcharacter = characters.get(i);
					break;
				}
			}
			Log.d("TEST0", "Story Player: Moving character is " + movingcharacter.getRawName());
			if(!character.getExpression().equals("")){
				movingcharacter.setExpression(character.getExpression());
			}
			
			if(!character.getMovement().equals("")){
				if(character.getMovement().equalsIgnoreCase(SalinlahiFour.getContext().getResources().getString(R.string.action_enterFromAbove))){
					movingcharacter.entranceFromHeaven();
				}else if(character.getMovement().equalsIgnoreCase(SalinlahiFour.getContext().getResources().getString(R.string.action_enterFromBelow))){
					movingcharacter.entranceFromHell();
				}else if(character.getMovement().equalsIgnoreCase(SalinlahiFour.getContext().getResources().getString(R.string.action_enterFromLeft))){
					movingcharacter.entranceFromLeft();
				}else if(character.getMovement().equalsIgnoreCase(SalinlahiFour.getContext().getResources().getString(R.string.action_enterFromRight))){
					movingcharacter.entranceFromRight();
				}else if(character.getMovement().equalsIgnoreCase(SalinlahiFour.getContext().getResources().getString(R.string.action_exitFromAbove))){
					movingcharacter.exitToHeaven();
				}else if(character.getMovement().equalsIgnoreCase(SalinlahiFour.getContext().getResources().getString(R.string.action_exitFromBelow))){
					movingcharacter.exitToHell();
				}else if(character.getMovement().equalsIgnoreCase(SalinlahiFour.getContext().getResources().getString(R.string.action_exitFromLeft))){
					movingcharacter.exitToLeft();
				}else if(character.getMovement().equalsIgnoreCase(SalinlahiFour.getContext().getResources().getString(R.string.action_exitFromRight))){
					movingcharacter.exitToRight();
				}else if(character.getMovement().equalsIgnoreCase(SalinlahiFour.getContext().getResources().getString(R.string.action_emphasize))){
					movingcharacter.emphasize();
				}else if(character.getMovement().equalsIgnoreCase(SalinlahiFour.getContext().getResources().getString(R.string.action_jump))){
					movingcharacter.jump();
				}else if(character.getMovement().equalsIgnoreCase(SalinlahiFour.getContext().getResources().getString(R.string.action_panic))){
					movingcharacter.panic();
				}else if(character.getMovement().equalsIgnoreCase(SalinlahiFour.getContext().getResources().getString(R.string.action_rumble))){
					movingcharacter.rumble();
				}else if(character.getMovement().equalsIgnoreCase(SalinlahiFour.getContext().getResources().getString(R.string.action_shake))){
					movingcharacter.shake();
				}else if(character.getMovement().equalsIgnoreCase(SalinlahiFour.getContext().getResources().getString(R.string.action_surprise))){
					movingcharacter.surprise();
				}
			}
			
			if(!character.getSay().getLine().equals("")){
				movingcharacter.say(character.getSay(lessonName, voiceID), character.getPhrase());
				voiceID++;
			}
		}

	}
	
	public NarrativeStory getStory(){
		return story;
	}
}
