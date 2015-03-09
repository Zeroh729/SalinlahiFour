package com.ube.salinlahifour;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

public class GameOver extends Activity {
	public static Context gContext;
	protected void onCreate(Bundle savedInstanceState) {	
		super.onCreate(savedInstanceState);
		gContext = getBaseContext();
		}
	public static Context getContext() {
	    return gContext;
	}
}
