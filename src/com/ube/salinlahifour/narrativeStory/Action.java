package com.ube.salinlahifour.narrativeStory;

import java.util.ArrayList;

import android.content.Context;

import com.ube.salinlahifour.SalinlahiFour;
import com.ube.salinlahifour.narrativeDialog.Character;

public class Action  implements Cloneable{
	private Context context;
	private ArrayList<Character> characters;
	private int scriptId = 1;
	private int backgroundResID = 0;
	
	public Action(Context context){
		this.context = context;
		characters = new ArrayList<Character>();
	}

	public Action clone(){  
	    try{  
	        return (Action) super.clone();  
	    }catch(Exception e){ 
	        return null; 
	    }
	}
	
	public Character getCharacter(Character character){
		int index = characters.indexOf(character);
		if(index >= 0){
			return characters.get(index);
		}
		else
			return null;
	}
	
	public void setContext(Context context){
    	this.context = context;
	}
	
	public ArrayList<Character> getCharacters() {
		return characters;
	}
	
	public void addCharacter(Character character) {
		this.characters.add(character);
	}

	public int getBackgroundResID() {
		return backgroundResID;
	}

	public void setBackgroundResID(String backgroundSrc) {
		int resID = SalinlahiFour.getContext().getResources().getIdentifier(backgroundSrc, "drawable", SalinlahiFour.getContext().getPackageName());
		if(resID != -1){
			backgroundResID = resID;
		}else{
			SalinlahiFour.errorPopup(context, "Resource ID not found", "Background image: " + backgroundSrc + " does not exist in drawable.");
		}
	}
	
	public int getScriptCnt(){
		return scriptId++;
	}
	
}
