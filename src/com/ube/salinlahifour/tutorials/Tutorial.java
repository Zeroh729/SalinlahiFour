package com.ube.salinlahifour.tutorials;

import java.util.ArrayList;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.Intent;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ube.salinlahifour.Item;
import com.ube.salinlahifour.Lesson;
import com.ube.salinlahifour.R;
import com.ube.salinlahifour.SalinlahiFour;
import com.ube.salinlahifour.enumTypes.LevelType;
import com.ube.salinlahifour.narrativeDialog.NarrativeDialog;
import com.ube.salinlahifour.uibuilders.Button.BtnNextArrowStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.BtnStatesDirector;

public class Tutorial extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.tutorial_animals);

		Bundle bundle = getIntent().getExtras();

		UserID = bundle.getInt("UserID");
		activityName = bundle.getString("lessonName");
		lesson = SalinlahiFour.getLesson(activityName);

		activityClass = lesson.getActivity();
		activityLevel = bundle.getString("activityLevel");
		Log.d("LESSON Name: ", lesson.getName());

		Log.d(activityClass, "TEST ActivityName");
		items = SalinlahiFour.getLessonItems(activityName, activityLevel);

		if (items == null) {
			// SalinlahiFour.errorPopup(this, "Lesson Items Insufficient",
			// "Check in " + lesson.getLexicon() +
			// " if:\nEASY items: have at least ");
		} else {
			Log.d("items size: " + items.size(), "TESTESTEST");
			initiateViews();

			if (activityLevel.equals(LevelType.EASY.toString())) {
				Intent intent = new Intent(this, NarrativeDialog.class);
				intent.putExtra("lessonName", lesson.getTheRealName());
				startActivity(intent);
			}

			for (int i = 0; i < items.size(); i++) {
				if (items.get(i).getDifficulty().toString().equalsIgnoreCase(activityLevel)) {
					textviews[itemcnt].setText(items.get(i).getWord() + " - " + items.get(i).getHint());
					imgviews[itemcnt].setImageResource(items.get(i).getImageID());
					frames[itemcnt].setVisibility(View.VISIBLE);
					itemcnt++;
				}
			}

			setClickListeners();
		}
		parent_view = (RelativeLayout) findViewById(R.id.parent_view);
		Log.d("TEST0", "Loaded Background Resources: " + lesson.getTutBackground());
		parent_view.setBackgroundResource(lesson.getTutBackground());
	}

	private Lesson lesson;
	private int backgroundResID;
	private ArrayList<SoundPool> voiceovers;
	private ArrayList<Item> items;
	private ArrayList<String> description;
	private String activityClass;
	private String activityName;
	private String activityLevel;
	private int layoutID;
	private int UserID;

	private TextView[] textviews;
	private ImageButton[] imgviews;
	private RelativeLayout[] frames;
	private boolean[] pressed;
	private TextView tv_rule;
	private ImageButton btn_next;
	private RelativeLayout parent_view;

	private int itemcnt = 0;
	
	public Tutorial() {
	}
	
	private void setClickListeners() {
		for(int i = 0; i < 4; i++) {
			imgviews[i].setOnClickListener(new imageListener(i));
		}
	}

	private void initiateViews() {
		textviews = new TextView[4];
		imgviews = new ImageButton[4];
		frames = new RelativeLayout[4];
		
		pressed = new boolean[4];

		tv_rule = (TextView) findViewById(R.id.tv_rule);
		btn_next = (ImageButton) findViewById(R.id.button1);
		btn_next.setImageDrawable(BtnStatesDirector.getImageDrawable(new BtnNextArrowStatesBuilder()));

		tv_rule.setTypeface(SalinlahiFour.getFontKgsecondchances());

		textviews[0] = (TextView) findViewById(R.id.textView1);
		textviews[1] = (TextView) findViewById(R.id.textView2);
		textviews[2] = (TextView) findViewById(R.id.textView3);
		textviews[3] = (TextView) findViewById(R.id.textView4);
		imgviews[0] = (ImageButton) findViewById(R.id.imageView1);
		imgviews[1] = (ImageButton) findViewById(R.id.imageView2);
		imgviews[2] = (ImageButton) findViewById(R.id.imageView3);
		imgviews[3] = (ImageButton) findViewById(R.id.imageView4);
		frames[0] = (RelativeLayout) findViewById(R.id.frame_1);
		frames[1] = (RelativeLayout) findViewById(R.id.frame_2);
		frames[2] = (RelativeLayout) findViewById(R.id.frame_3);
		frames[3] = (RelativeLayout) findViewById(R.id.frame_4);
		
		for(int i = 0; i < 4; i++) {
			pressed[i] = false;
			frames[i].setVisibility(View.INVISIBLE);
			textviews[i].setVisibility(android.view.View.GONE);
		}
		
		LinearLayout mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
		mainLayout.setBackgroundResource(lesson.getTutBackground());
	}

	public void btn_play(View view) {
		Log.d("BTN_PLAYCLICKED", "YES");
		goToActivity();
	}

	private void goToActivity() {
		try {
			Intent intent = new Intent(activityClass);
			intent.putExtra("activityName", lesson.getTheRealName());
			intent.putExtra("UserID", UserID);
			intent.putExtra("activityLevel", activityLevel);

			startActivity(intent);
		} catch (Exception e) {
			SalinlahiFour.errorPopup(this, "Activity Error", "Check if:\n" + "1. " + activityClass + " exists.\n" + "2. This <activity> has <intent-filter> tags in AndroidManifest.xml");
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		SalinlahiFour.getBgm().start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		SalinlahiFour.getBgm().pause();
	}

	public String getLessonName() {
		return lesson.getName();
	}
	
	class imageListener implements View.OnClickListener {
		private int index;
		private boolean toggle;
		final AnimatorSet setRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.flipout);
		final AnimatorSet setLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.flipleft);

		public imageListener(int index) {
			this.index = index;
			toggle = true;
		}

		@Override
		public void onClick(View v) {
			if(toggle) {
				pressed[index] = true;
				items.get(index).playFilipinoSound();	
				textviews[index].setVisibility(android.view.View.VISIBLE);
				setRightOut.setTarget(imgviews[index]);
				setLeftIn.setTarget(textviews[index]);
				setRightOut.start();
				setLeftIn.start();
			} else {
				setRightOut.setTarget(textviews[index]);
				setLeftIn.setTarget(imgviews[index]);
				setRightOut.start();
				setLeftIn.start();
			}
			
			toggle = !toggle;

			for (int i = 0; i < itemcnt; i++) {
				if (pressed[i]) {
					if ((i + 1) == itemcnt) {
						btn_next.setVisibility(View.VISIBLE);
					}
				} else {
					break;
				}
			}
		}
	}
}
