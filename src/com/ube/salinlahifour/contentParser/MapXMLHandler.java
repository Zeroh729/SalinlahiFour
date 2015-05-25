package com.ube.salinlahifour.contentParser;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.content.Context;
import android.util.Log;

import com.ube.salinlahifour.SalinlahiFour;
import com.ube.salinlahifour.howtoplay.HowToPlaySet;
import com.ube.salinlahifour.narrativeDialog.Character;

public class MapXMLHandler extends DefaultHandler{
	private ArrayList<Integer> resourceId;
    private String tempVal;
    private String errorTitle = "Parse error: list_mappages.xml";
    private Context context;

    public MapXMLHandler(Context context){
    	resourceId = new ArrayList<Integer>();
        this.context = context;
    }
    public ArrayList<Integer> getMapPages() {
        return resourceId;
    }
    
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("mappage")) {
            String name = attributes.getValue("layout");
            if(name!=null){
            	int tempResID = SalinlahiFour.getContext().getResources().getIdentifier(name, "layout",SalinlahiFour.getContext().getPackageName());
            	if(tempResID == 0){
                    SalinlahiFour.errorPopup(context, errorTitle, "Add " + name + ".xml in layout folder.");	
            	}else{
            		resourceId.add(tempResID);
            	}
            }else{
                SalinlahiFour.errorPopup(context, errorTitle, "A mappage tag has no 'layout' attribute");
            }
        }
    }

    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if(qName.equalsIgnoreCase("resources")){
        	if(((SalinlahiFour.getLessonsList().size()-1)/5) >= resourceId.size()){
        	    SalinlahiFour.errorPopup(context, errorTitle, "Warning: Insufficient Map Pages to accomodate " + SalinlahiFour.getLessonsList().size() + "lessons (5 Lessons in a Map Page).\nDefault layout will be set.");	
        	}
        }
    }
}
