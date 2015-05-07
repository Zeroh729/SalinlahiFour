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

import android.util.Log;

import com.ube.salinlahifour.R;
import com.ube.salinlahifour.SalinlahiFour;

public class NarrativeStory {
	private static ArrayList<Character> characters;

	public NarrativeStory(){
		Log.d("TEST0", "Narrative Story");
		try {
			characterParseXML();
		} catch (XmlPullParserException | IOException e) {
			Log.d("TEST0", "Character Parse XML ERROR");
			e.printStackTrace();
		}
	}
	
	public void characterParseXML() throws XmlPullParserException, IOException{
		characters = new ArrayList<Character>();
		
		Log.d("TEST0", "Character Parse XML");
		
		 InputStream in = SalinlahiFour.getContext().getResources().openRawResource(R.raw.narrativestory_character);//(R.xml.lesson);
		 try{
		    FileOutputStream out = new FileOutputStream("/sdcard/lesson.xml");
		    byte[] buff = new byte[4096];
		    int read = 0;
		       while ((read = in.read(buff)) > 0) {
		          out.write(buff, 0, read);
		         in.close();
		         out.close();
		    }
				 Log.d("TEST", "Success");
		 }catch(Exception e){
			 Log.d("TEST", "Failed: " + e.getMessage());
		 }

		 SAXParserFactory factory = SAXParserFactory.newInstance();
		 try {
			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(new InputSource(new FileInputStream("/sdcard/lesson.xml")), new Handler());
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}
	
	class Line{
		String name;
		ArrayList<String> items = new ArrayList();
		ArrayList<String> itemName = new ArrayList();
	}

	class Handler extends DefaultHandler{
		Line tempStory;
		boolean isItem;
	 
		public void startElement(String uri, String localName,String qName, 
	                Attributes attributes) throws SAXException {
			
			isItem = false;
	 
			if (qName.equalsIgnoreCase("CHARACTER")) {
				tempStory = new Line();
				tempStory.name = attributes.getValue("name");
				Log.d("TEST0", "Start tag <Character> name:" + tempStory.name);
			}
	 
			else if (qName.equalsIgnoreCase("ITEM")) {
				tempStory.items.add(attributes.getValue("name"));
				isItem = true;
				Log.d("TEST0", "Start tag <Item> name:" + tempStory.name);
			}
	 
		}
	 
		public void endElement(String uri, String localName,
			String qName) throws SAXException {
		}
	 
		public void characters(char ch[], int start, int length) throws SAXException {
	 
			if(isItem)	{
				tempStory.itemName.add(new String(ch, start, length));
				Log.d("TEST0", "Text: " + new String(ch, start, length));
			}
		}
	 
	};
	
}
