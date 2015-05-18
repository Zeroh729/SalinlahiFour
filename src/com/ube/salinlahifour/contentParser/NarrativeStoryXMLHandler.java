package com.ube.salinlahifour.contentParser;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.content.Context;
import android.util.Log;

import com.ube.salinlahifour.SalinlahiFour;
import com.ube.salinlahifour.narrativeDialog.Character;
import com.ube.salinlahifour.narrativeStory.Action;
import com.ube.salinlahifour.narrativeStory.AnimationList;
import com.ube.salinlahifour.narrativeStory.NarrativeStory;
import com.ube.salinlahifour.narrativeStory.ScriptLine;

public class NarrativeStoryXMLHandler extends DefaultHandler{
	private final int CHARACTERS_MAX_SIZE = 4;
	private ArrayList<NarrativeStory> stories;
    private NarrativeStory tempStory;
    private ArrayList<Character> characters;
    private Action tempAction;
    private String errorTitle = "Parse error: list_stories.xml";
    private Context context;
    
    public NarrativeStoryXMLHandler(Context context){
    	stories = new ArrayList<NarrativeStory>();
    	this.context = context;
    	Log.d("TEST0", "Created NarrativeStory");
    	characters = new ArrayList<>();
    	stories = new ArrayList<>();
    }
    
    public ArrayList<NarrativeStory> getNarrativeStories() {
        return stories;
    }
    
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {
    	
//    	for(int i = 0; i <stories.size(); i++){
//    		Log.d("TEST0", "Narrative Stories names: " + i + " : " + stories.get(i).getName());
//    	}

    	Log.d("TEST0", "Narrative Story: START tag");
        if (qName.equalsIgnoreCase("Lesson")) {
        	stories.add(new NarrativeStory());
        	tempStory = stories.get(stories.size()-1);
        	characters = new ArrayList<Character>();
        	String name = attributes.getValue("name");
        	Log.d("TEST0", "Narrative Story: lesson tag");
        	if(name!=null){
        		tempStory.setName(name);
            	Log.d("TEST0", "Narrative Story: name element: " + name);
        	}else{
        		SalinlahiFour.errorPopup(context, errorTitle, "A <story> tag has no 'name' attribute!");
        	}
        }else if(qName.equalsIgnoreCase("Scene")){
        	tempAction = new Action(context);
        	Log.d("TEST0", "Narrative Story: scene element");
        }else if(qName.equalsIgnoreCase("Background")){
        	String src = attributes.getValue("src");
        	if(src!=null){
        		tempAction.setBackgroundResID(src);
            	Log.d("TEST0", "Narrative Story: background element: " + src);
        	}else{
        		SalinlahiFour.errorPopup(context, errorTitle, "A <backgroud> tag has no 'src' attribute!");
        	}
        }else if(qName.equalsIgnoreCase("Character")){
        	Character character = new Character(context);
        	String name = attributes.getValue("name");
        	String expression = attributes.getValue("expression");
        	String animate = attributes.getValue("animate");
        	String say = attributes.getValue("say");

        	Log.d("TEST0", "Narrative Story: character element");
        	Log.d("TEST0", "Narrative Story: character name is " + name);
        	
        	if(name!=null){
            	Log.d("TEST0", "Narrative Story: character element name: " + name);
        		character.setName(name);
        	}else{
            	Log.d("TEST0", "Narrative Story: character element name null");
        		SalinlahiFour.errorPopup(context, errorTitle, "A <character> tag has no 'name' attribute!");
        	}
        	if(expression!=null){
        		if(!character.setExpressionFromCharacter(name, expression)){
        			SalinlahiFour.errorPopup(context, errorTitle, "Character '" + name + "' has no such expression '" + expression + "'. \nPlease check list_characters.xml");
        		}
        	}
        	if(animate!=null){
        		if(animationExists(animate)){
        			character.setMovement(animate);
                	Log.d("TEST0", "Narrative Story: character element animate: " + animate);
        		}else{
        			SalinlahiFour.errorPopup(context, errorTitle, "No such animation '" + animate +"' found in Character '" + name + "'");
        		}
        	}
        	if(say!=null){
            	Log.d("TEST0", "Narrative Story: Saying...");
        		ScriptLine scriptLine = new ScriptLine(context, tempStory.getName(), say, tempAction.getScriptCnt());

            	Log.d("TEST0", "Narrative Story: Setting Script Line to character");
        		character.setScriptLine(scriptLine);
        	}else{
        		ScriptLine scriptLine = new ScriptLine(context, tempStory.getName(), "", tempAction.getScriptCnt());
        		character.setScriptLine(scriptLine);
        	}
        	
        	Log.d("TEST0", "Narrative Story: Finally, now checking if characters already contain " + character.getRawName());
        	
        	if(!characterExists(character)){
            	Log.d("TEST0", "Narrative Story: Nope! Doesn't contain it yet");
            	
        		characters.add(character);
            	Log.d("TEST0", "Narrative Story: Added new character: " + name);
        		if(characters.size() > CHARACTERS_MAX_SIZE){
                	Log.d("TEST0", "Narrative Story: Characters size exceeeds MAX SIZE! Size now:" + characters.size());
                	
        			SalinlahiFour.errorPopup(context, errorTitle, "Number of characters in story '" + tempStory.getName() + "' exceeds the number of max characters (" + CHARACTERS_MAX_SIZE +")");
        		}
        	}
        	
        	Log.d("TEST0", "Narrative Story: Adding character to this scene");
    		
        	tempAction.addCharacter(character);
        }
    }
    

    public void endElement(String uri, String localName, String qName)
            throws SAXException {
    	Log.d("TEST0", "Narrative Story: END TAG");
    	if(qName.equalsIgnoreCase("Scene")){
        	tempStory.addActions(tempAction.clone());
        	Log.d("TEST0", "Narrative Story: Scene End tag");
    	}
    	else if (qName.equalsIgnoreCase("Lesson")) {
    		tempStory.setCharacters((ArrayList<Character>) characters.clone());
            stories.add(tempStory.clone());
    		Log.d("TEST0", "Narrative Story: Action size: " + stories.get(stories.size()-1).getActions().size());
        	Log.d("TEST0", "Narrative Story: Lesson End tag");
        }
    }
    
    private boolean characterExists(Character character){
    	for(Character tempChar : characters){
    		if(tempChar.getRawName().equals(character.getRawName()))
    			return true;
    	}
    	return false;
    }
    
    private boolean animationExists(String animation){
    	Log.d("TEST0", "Check if Animation exists: " + animation);
    	for(AnimationList anim : AnimationList.values()){
        	Log.d("TEST0", "checking... " + anim.toString());
    		if(animation.equalsIgnoreCase(anim.toString())){
            	Log.d("TEST0", "Animation exists");
    			return true;
    		}
    	}
    	Log.d("TEST0", "Animation does not exist!");
    	return false;
    }
}
