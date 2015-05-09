package com.ube.salinlahifour.contentParser;

import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.util.Log;

import com.ube.salinlahifour.Item;
import com.ube.salinlahifour.Lesson;

public class XMLContentParser {
	
	public static List<Lesson> parseLesson(InputStream is) {
        List<Lesson> lessons = null;
        try {
            // create a XMLReader from SAXParser
            XMLReader xmlReader = SAXParserFactory.newInstance().newSAXParser()
                    .getXMLReader();
            // create a SAXXMLHandler
            LessonXMLHandler saxHandler = new LessonXMLHandler();
            // store handler in XMLReader
            xmlReader.setContentHandler(saxHandler);
            // the process starts
            xmlReader.parse(new InputSource(is));
            // get the `Employee list`
            lessons = saxHandler.getLessons();
 
        } catch (Exception ex) {
            Log.d("XML", "SAXXMLParser: parseLesson() failed: " + ex);
            ex.printStackTrace();
        }
 
        // return Employee list
        return lessons;
    }
	public static List<Item> parseItem(InputStream is) {
        List<Item> items = null;
        try {
            // create a XMLReader from SAXParser
            XMLReader xmlReader = SAXParserFactory.newInstance().newSAXParser()
                    .getXMLReader();
            // create a SAXXMLHandler
            ItemXMLHandler saxHandler = new ItemXMLHandler();
            // store handler in XMLReader
            xmlReader.setContentHandler(saxHandler);
            // the process starts
            xmlReader.parse(new InputSource(is));
            // get the `Employee list`
            items = saxHandler.getItems();
 
        } catch (Exception ex) {
            Log.d("XML", "SAXXMLParser: parseItem() failed" + ex);
            ex.printStackTrace();
        }
 
        // return Employee list
        return items;
    }
}
