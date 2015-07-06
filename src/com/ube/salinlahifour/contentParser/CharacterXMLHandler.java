package com.ube.salinlahifour.contentParser;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.content.Context;

import com.ube.salinlahifour.SalinlahiFour;
import com.ube.salinlahifour.narrativeDialog.Character;

public class CharacterXMLHandler extends DefaultHandler{
	private ArrayList<Character> characters;
    private Character tempCharacter;
    private boolean hasName;
    private boolean hasDefault;
    private String errorTitle = "Parse error: list_character.xml";
    private Context context;
    
    
    public CharacterXMLHandler(Context context){
    	characters = new ArrayList<Character>();
    	this.context = context;
    }
    public ArrayList<Character> getCharacters() {
        return characters;
    }
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("Character")) {
        	tempCharacter = new Character(SalinlahiFour.getContext());
        	hasName = false;
        	hasDefault = false;
        	
        	for(int i = 0; i < attributes.getLength(); i++){
        		String value = attributes.getValue(i);
        		if(attributes.getLocalName(i).equalsIgnoreCase("name")){
        			tempCharacter.setName(value);
        			hasName = true;
        		}else if(attributes.getLocalName(i).equalsIgnoreCase("default")){
        			tempCharacter.addExpression("default", value);
        			hasDefault = true;
        		}else{
        			tempCharacter.addExpression(attributes.getLocalName(i), value);
        		}
        	}
        	if(!hasName){
        		SalinlahiFour.errorPopup(context, errorTitle, "A character tag has no attribute 'name'");
        	}else if(!hasDefault){
        		SalinlahiFour.errorPopup(context, errorTitle, "Character '" + tempCharacter.getName() + "' has no attribute 'default'");
        	}else{
        		characters.add(tempCharacter);
        	}
        }
    }
}
