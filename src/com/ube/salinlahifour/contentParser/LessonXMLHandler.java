package com.ube.salinlahifour.contentParser;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import android.content.Context;

import com.ube.salinlahifour.Lesson;
import com.ube.salinlahifour.SalinlahiFour;


public class LessonXMLHandler extends DefaultHandler {
	private List<Lesson> lessons;
    private String tempVal;
    private Lesson tempLesson;

    private Context context;
    
    public LessonXMLHandler(Context context){
    	lessons = new ArrayList<Lesson>();
    	this.context = context;
    }
    
    public LessonXMLHandler(){
    	lessons = new ArrayList<Lesson>();
    }
    public List<Lesson> getLessons() {
        return lessons;
    }
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {
        // reset
        tempVal = "";
        if (qName.equalsIgnoreCase("lesson_num")) {
            // create a new instance of employee
        	tempLesson = new Lesson();
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
            lessons.add(tempLesson);
        } else if (qName.equalsIgnoreCase("entry")) { //ID
        	tempLesson.setLessonNumber(Integer.parseInt(tempVal));
        } else if (qName.equalsIgnoreCase("Lesson_Name")) {
        	tempLesson.setName(tempVal);
        } else if (qName.equalsIgnoreCase("Lesson_PreReq")) {
        	tempLesson.setPreReq(Integer.parseInt(tempVal));
        } else if (qName.equalsIgnoreCase("Summary")) {
        	tempLesson.setDescription(tempVal);
        } else if (qName.equalsIgnoreCase("lexicon_name")) {
        	tempLesson.setLexicon(tempVal);
        }else if (qName.equalsIgnoreCase("activity_name")) {
        	tempLesson.setActivity(tempVal);
        }else if (qName.equalsIgnoreCase("tutorial_background")) {
        	tempLesson.setTutBackground(context, tempVal);
        }else if (qName.equalsIgnoreCase("icon_image")) {
        	int resID = SalinlahiFour.getContext().getResources().getIdentifier(tempVal, "drawable", SalinlahiFour.getContext().getPackageName());
        	tempLesson.setImage(resID);
        }
        
    }

}

