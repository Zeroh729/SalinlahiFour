package com.ube.salinlahifour.lessonActivities;

import com.ube.salinlahifour.R;
import com.ube.salinlahifour.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public abstract class AbstractLessonActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lesson);
	}
}
