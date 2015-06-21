package com.ube.salinlahifour.contentParser;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.content.Context;

import android.util.Log;

import com.ube.salinlahifour.Item;
import com.ube.salinlahifour.Lesson;

public class ItemXMLHandler extends DefaultHandler {
	private List<Item> items;
    private String tempVal;
    private Item tempItem;

    private Context context;
    
    public ItemXMLHandler(Context context){
    	items = new ArrayList<Item>();
    	this.context = context;

    }
    public List<Item> getItems() {
        return items;
    }
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {
        // reset
        tempVal = "";
        if (qName.equalsIgnoreCase("Word_Entry")) {
            // create a new instance of employee
        	tempItem = new Item();
        }  
    }
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        tempVal = new String(ch, start, length);
    	//Log.d("Jim Parse On", "Inside ItemXMLHandler Value is actually: " + tempVal);
    }
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if (qName.equalsIgnoreCase("Word_Entry")) {
            // add it to the list
            items.add(tempItem.clone());
        	//Log.d("Jim Parse On", "ITEM PARSED successful storing: " + items.get(items.size()-1).getWord());
        } else if (qName.equalsIgnoreCase("q_num")) { //ID
        	tempItem.setID(Integer.parseInt(tempVal));
        	
        } else if (qName.equalsIgnoreCase("word")) {
        	//Log.d("Jim Parse On", "Inside ItemXMLHandler tempval of word: " + tempVal);
        	tempItem.setWord(tempVal);
        	//Log.d("Jim Parse On", "Inside ItemXMLHandler stored word: " + tempItem.getWord());
        } else if (qName.equalsIgnoreCase("question")) {
        	tempItem.setQuestion(tempVal);
        	//Log.d("Jim Parse On", "Inside ItemXMLHandler stored question: " + tempItem.getQuestion());
        } else if (qName.equalsIgnoreCase("eng_word")) {
        	tempItem.setEnglish(tempVal);
        	//Log.d("Jim Parse On", "Inside ItemXMLHandler stored english: " + tempItem.getEnglish());
        } else if (qName.equalsIgnoreCase("notes")) {
        	tempItem.setNote(tempVal);
        	//Log.d("Jim Parse On", "Inside ItemXMLHandler stored notes: " + tempItem.getNote());
        } else if (qName.equalsIgnoreCase("hint")) {
        	tempItem.setHint(tempVal);
        	//Log.d("Jim Parse On", "Inside ItemXMLHandler stored hints: " + tempItem.getHint());
        } else if (qName.equalsIgnoreCase("lesson")) {
        	tempItem.setLessonNum(Integer.parseInt(tempVal));
        } else if (qName.equalsIgnoreCase("image_path")) {
        	tempItem.setImagePath(context, tempVal);
        } else if (qName.equalsIgnoreCase("eng_sound")) {
        	tempItem.setVoiceEngPath(context, tempVal);
        } else if (qName.equalsIgnoreCase("fil_sound")) {
        	tempItem.setVoiceFilPath(context, tempVal);
        } else if (qName.equalsIgnoreCase("difficulty")) {
        	tempItem.setDifficulty(tempVal);
        } 
        
    }

}

