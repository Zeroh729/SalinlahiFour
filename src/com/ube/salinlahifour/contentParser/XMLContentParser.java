package com.ube.salinlahifour.contentParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.content.Context;

import com.ube.salinlahifour.Item;
import com.ube.salinlahifour.Lesson;
import com.ube.salinlahifour.SalinlahiFour;
import com.ube.salinlahifour.howtoplay.HowToPlay;
import com.ube.salinlahifour.howtoplay.HowToPlaySet;
import com.ube.salinlahifour.narrativeDialog.Character;
import com.ube.salinlahifour.narrativeStory.NarrativeStory;

public class XMLContentParser {
	
	public static List<Lesson> parseLesson(Context context, InputStream is) {
        List<Lesson> lessons = null;
        try {
            XMLReader xmlReader = SAXParserFactory.newInstance().newSAXParser()
                    .getXMLReader();
            LessonXMLHandler saxHandler = new LessonXMLHandler(context);
            xmlReader.setContentHandler(saxHandler);
            xmlReader.parse(new InputSource(is));
            lessons = saxHandler.getLessons();
 
        } catch (Exception ex) {
            SalinlahiFour.errorPopup(context, "XML", "SAXXMLParser: parseLesson() failed: " + ex.getMessage());
        }
 
        return lessons;
    }
	public static List<Item> parseItem(Context context, InputStream is) {
        List<Item> items = null;
        try {
            XMLReader xmlReader = SAXParserFactory.newInstance().newSAXParser()
                    .getXMLReader();
            ItemXMLHandler saxHandler = new ItemXMLHandler(context);
            xmlReader.setContentHandler(saxHandler);
            xmlReader.parse(new InputSource(is));
            items = saxHandler.getItems();
 
        } catch (Exception ex) {
            SalinlahiFour.errorPopup(context, "XML", "SAXXMLParser: parseItem() failed: " + ex.getMessage());
        }
 
        return items;
    }
	public static ArrayList<HowToPlaySet> parseHowToPlay(Context context, InputStream is) {
		ArrayList<HowToPlaySet> howToPlay = null;
        try {
            XMLReader xmlReader = SAXParserFactory.newInstance().newSAXParser()
                    .getXMLReader();
            HowtoplayXMLHandler saxHandler = new HowtoplayXMLHandler(context);
            xmlReader.setContentHandler(saxHandler);
            xmlReader.parse(new InputSource(is));
            howToPlay = saxHandler.getHowToPlays();
 
        } catch (Exception ex) {
            SalinlahiFour.errorPopup(context, "XML", "SAXXMLParser: parseHowtoplay() failed: " + ex.getMessage());
        }
 
        return howToPlay;
    }
	public static ArrayList<Character> parseCharacter(Context context, InputStream is) {
		ArrayList<Character> characters = null;
        try {
            XMLReader xmlReader = SAXParserFactory.newInstance().newSAXParser()
                    .getXMLReader();
            CharacterXMLHandler saxHandler = new CharacterXMLHandler(context);
            xmlReader.setContentHandler(saxHandler);
            xmlReader.parse(new InputSource(is));
            characters = saxHandler.getCharacters();
 
        } catch (Exception ex) {
            SalinlahiFour.errorPopup(context, "XML", "SAXXMLParser: parseCharacter() failed: " + ex.getMessage());
        }
 
        return characters;
    }
	public static ArrayList<NarrativeStory> parseNarrativeStory(Context context, InputStream is) {
		ArrayList<NarrativeStory> stories = null;
        try {
            XMLReader xmlReader = SAXParserFactory.newInstance().newSAXParser()
                    .getXMLReader();
            NarrativeStoryXMLHandler saxHandler = new NarrativeStoryXMLHandler(context);
            xmlReader.setContentHandler(saxHandler);
            xmlReader.parse(new InputSource(is));
            stories = saxHandler.getNarrativeStories();
 
        } catch (Exception ex) {
            SalinlahiFour.errorPopup(context, "XML", "SAXXMLParser: parseNarrativeStory() failed: " + ex.getMessage());
        }
 
        return stories;
    }
}