package com.guitartool.chordbase;

import com.guitartool.R;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;

public class RequestParser extends Activity {
	public static final String RESPONSE = "Response";
	private int extra1, extra2;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loading);
		extra1 = getIntent().getIntExtra("note", -1);
		extra2 = getIntent().getIntExtra("chord", -1);
		prepareResponse(extra1 , extra2);
		finish();
	}
	private void prepareResponse(int n, int ch) {
        String response = "failed";
		try {
			response = getXml(n,ch);
			Intent resultIntent = new Intent();
		    resultIntent.putExtra(RESPONSE, response);
		    setResult(RESULT_OK, resultIntent);
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }	
		
	private String getXml(int noteId, int chordId) throws XmlPullParserException, IOException{
	
    	
    	StringBuffer sb = new StringBuffer();
    	Resources res = this.getResources();
    	XmlResourceParser xpp = res.getXml(R.xml.updated_guitar_chords);
    	boolean note_check, chord_check, variant_check, found;
    	int eventType = xpp.getEventType();
    	
    	
    	found = false;

    	if (noteId == -1){
    		sb.append("-1 note error\n");
    	}
    	else if (chordId == -1){
    		sb.append("-1 chord error\n");
    	}
    	else{
	    	xpp.next();
	    	while (eventType != XmlPullParser.END_DOCUMENT){
	    		//definicja warunku wyszukiwania nuty
	    		note_check =(eventType == XmlPullParser.START_TAG)&&
						 (xpp.getName().equalsIgnoreCase("note"))&&
						 (Integer.parseInt(xpp.getIdAttribute()) == noteId);
		    	if(note_check){
		    		// dodaje nazwe nuty do ci¹gu wynikowego
		    		sb.append(xpp.getAttributeValue(1)+";");
		    		eventType = xpp.next();
		    		    			    		
			    	while(xpp.getDepth()>2){
			    		// zdefiniowany warunek wyszukiwania akordu
			    		chord_check =(eventType == XmlPullParser.START_TAG)&&
		    					 (xpp.getName().equalsIgnoreCase("chord"))&&
		    					 (Integer.parseInt(xpp.getIdAttribute()) == chordId);
			    		if(chord_check){
			    			//dodanie nazw chwytu do ci¹gu wynikowego
			    			sb.append(xpp.getAttributeValue(1)+";");
			    			eventType = xpp.next();
			    			while(xpp.getDepth() > 3){
			    				variant_check = (eventType == XmlPullParser.START_TAG) && (xpp.getName().equalsIgnoreCase("variant"));
			    				if(variant_check){
			    					//pozycja lewej rêki
			    					sb.append(xpp.getAttributeValue(1)+":");
			    					eventType = xpp.next();
			    					sb.append(getChord(xpp)+"#");
			    					found = true;
			    					}//end if variant check
			    				eventType = xpp.next();
			    				}//end while depth 3
			    		}//end chord check
			    		eventType = xpp.next();
			    	}//end while depth 2
		    	}//end note check
		    	eventType = xpp.next();
	    	}//end of loop
	    	if(!found){
	    		sb.append("Chord not found!");
	    	}
    	}//end of else
    	
    	
    	
    	return sb.toString();
    }//eof-function

    private String getChord(XmlPullParser xpp) throws XmlPullParserException, IOException{
    	StringBuffer sb = new StringBuffer();
    	for(int i=0; i<6;i++){
    		sb.append(xpp.getAttributeValue(0)+":"+xpp.getAttributeValue(1)+":");
    		xpp.next();
    		xpp.next();
    	}
    	return sb.toString();
    }
}
