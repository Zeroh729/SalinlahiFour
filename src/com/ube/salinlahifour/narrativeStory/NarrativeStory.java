package com.ube.salinlahifour.narrativeStory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import com.ube.salinlahifour.debugclasses.Log;
import android.view.View;
import android.view.ViewGroup;

import com.ube.salinlahifour.R;
import com.ube.salinlahifour.SalinlahiFour;
import com.ube.salinlahifour.narrativeDialog.Character;

public class NarrativeStory implements Cloneable{
	private Context context;
	private String name;
	private ArrayList<Character> characters;
	private ArrayList<Action> actions;
	private int background;
	
	private static ArrayList<Character> charactersFromLesson;
	
	public NarrativeStory(){
		Log.d("TEST0", "Narrative Story");
		characters = new ArrayList();
		actions = new ArrayList();
		charactersFromLesson = new ArrayList<>();
	}
	
	public NarrativeStory clone(){  
	    try{  
	    	NarrativeStory story = (NarrativeStory) super.clone();
	    	Log.d("TEST0", "PARSED STORIES: returning clone: " + story.getName());
	        return story;
	    }catch(Exception e){ 
	    	Log.d("TEST0", "PARSED STORIES: returning clone: ERROR");
	        return null; 
	    }
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name2) {
		name = name2;
	}

	public void setContext(Context context){
		this.context = context;
	}

	public ArrayList<Character> getCharacters() {
		return characters;
	}

	public void setCharacters(ArrayList<Character> character) {
		characters = character;
	}

	public ArrayList<Action> getActions() {
		return actions;
	}

	public void addActions(Action action) {
		actions.add(action);
	}	

	public int getBackground() {
		return background;
	}


	public void setBackground(int bg) {
		background = bg;
	}
	
	class Line{
		String name;
		ArrayList<String> items = new ArrayList();
		ArrayList<String> itemName = new ArrayList();
	}
	
}
