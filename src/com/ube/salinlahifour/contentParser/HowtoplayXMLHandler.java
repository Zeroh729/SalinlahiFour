package com.ube.salinlahifour.contentParser;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.content.Context;

import com.ube.salinlahifour.SalinlahiFour;
import com.ube.salinlahifour.howtoplay.HowToPlay;
import com.ube.salinlahifour.howtoplay.HowToPlaySet;

public class HowtoplayXMLHandler extends DefaultHandler{
	private ArrayList<HowToPlaySet> howtoplays;
    private HowToPlaySet tempStory;
    private String errorTitle = "Parse error: list_howtoplay.xml";
    private Context context;
    private String name = "";
    
    public HowtoplayXMLHandler(Context context){
    	howtoplays = new ArrayList<HowToPlaySet>();
    	this.context = context;
    }
    public ArrayList<HowToPlaySet> getHowToPlays() {
        return howtoplays;
    }
    

    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {
    	if (qName.equalsIgnoreCase("Lesson")) {
    		tempStory = new HowToPlaySet();
    		String name = attributes.getValue("name");
    		if(name!=null){
    			
    		}else{
    			SalinlahiFour.errorPopup(context, errorTitle, "A story tag has no 'name' attribute");
    		}
    	}    	
    	if (qName.equalsIgnoreCase("Screenshot")) {
    		String value = attributes.getValue("src");
    		if(value!=null){
    			tempStory.addScreenshot(context, value);
    		}else{
    			SalinlahiFour.errorPopup(context, errorTitle, "A screenshot tag has no 'src' attribute in the lesson '" + tempStory.lessonName + "'");
    		}
    	}
    }
    

    public void endElement(String uri, String localName, String qName)
            throws SAXException {
    	if(qName.equalsIgnoreCase("Lesson")){
    		howtoplays.add(tempStory);
    	}
    }
}
