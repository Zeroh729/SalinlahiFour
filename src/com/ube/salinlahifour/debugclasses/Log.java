package com.ube.salinlahifour.debugclasses;

import com.ube.salinlahifour.SalinlahiFour;

public class Log {
	public static void i(String tag, String string) {
		if(SalinlahiFour.DEBUGMODE)
			android.util.Log.i(tag, string);
	}

	public static void e(String tag, String string) {
		if(SalinlahiFour.DEBUGMODE)
			android.util.Log.e(tag, string);
	}

	public static void d(String tag, String string) {
		if(SalinlahiFour.DEBUGMODE)
			android.util.Log.d(tag, string);
	}

	public static void v(String tag, String string) {
		if(SalinlahiFour.DEBUGMODE)
			android.util.Log.v(tag, string);
	}

	public static void w(String tag, String string) {
		if(SalinlahiFour.DEBUGMODE)
			android.util.Log.w(tag, string);
	}
}
