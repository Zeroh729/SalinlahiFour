package com.ube.salinlahifour.contentParser;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.ube.salinlahifour.Lesson;
import com.ube.salinlahifour.SalinlahiFour;

import android.content.Context;


public class CFeedbackXMLHandler extends DefaultHandler {
	//private List<Item> items;
    private String tempVal;
    //private Item tempItem;

    private Context context;
    public CFeedbackXMLHandler(Context context){
    	
    	this.context = context;
    }
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {
        // reset
        tempVal = "";
        if (qName.equalsIgnoreCase("NextDifficulty")) {
            // create a new instance of employee
        	//tempLesson = new Lesson();
        }
        if(qName.equalsIgnoreCase("NextLesson")){
        	
        }
    }
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        tempVal = new String(ch, start, length);
    }
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if (qName.equalsIgnoreCase("lesson_num")) {
            // add it to the list
            //lessons.add(tempLesson);
        } else if (qName.equalsIgnoreCase("entry")) { //ID
        	//tempLesson.setLessonNumber(Integer.parseInt(tempVal));
        } 
        
    }
}
