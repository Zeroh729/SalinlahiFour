package com.ube.salinlahifour;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ProgressTree extends Activity {
	private String username;
	private int stars_gold;
	private int stars_silver;
	private int stars_bronze;
	private int stars_total;
	private int numLesson;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_progress_tree);
		
		Bundle bundles = getIntent().getExtras();
		numLesson = bundles.getInt("numLessons");
	}
	
	
	
}
