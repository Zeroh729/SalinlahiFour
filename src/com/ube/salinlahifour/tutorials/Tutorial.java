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

		//setContentView(R.layout.tutorial_animals);

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
				setEasyTutorial();
			} else if (activityLevel.equals(LevelType.MEDIUM.toString())) {
				setMediumTutorial();
			} else if (activityLevel.equals(LevelType.HARD.toString())) {
				setHardTutorial();
			}
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
	private boolean isBackVisibleimageView1 = false; // Boolean variable to
														// check if the back
														// image is vis
	private boolean isBackVisibleimageView2 = false; // Boolean variable to
														// check if the back
														// image is vis
	private boolean isBackVisibleimageView3 = false; // Boolean variable to
														// check if the back
														// image is vis
	private boolean isBackVisibleimageView4 = false; // Boolean variable to
														// check if the back
														// image is vis
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

	private void setEasyTutorial() {
		Intent intent = new Intent(this, NarrativeDialog.class);
		intent.putExtra("lessonName", lesson.getTheRealName());
		startActivity(intent);
		Log.d(items.size() + "", "TEST");
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getDifficulty().toString().equalsIgnoreCase(activityLevel)) {
				Log.d("TEST0", "Tutorial class : Setup... name " + items.get(i).getWord() + " & desc: " + items.get(i).getNote());
				textviews[itemcnt].setText(items.get(i).getHint());
				imgviews[itemcnt].setImageResource(items.get(i).getImageID());
				frames[itemcnt].setVisibility(View.VISIBLE);
				itemcnt++;
			}
		}

		final AnimatorSet setRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.flipout);

		final AnimatorSet setLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.flipleft);

		View.OnClickListener oclClick = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				tv_rule.setVisibility(View.INVISIBLE);
				switch (v.getId()) {
				case R.id.imageView1:
					pressed[0] = true;
					items.get(0).playFilipinoSound();
					if (!isBackVisibleimageView1) {
						textviews[0].setVisibility(android.view.View.VISIBLE);
						setRightOut.setTarget(imgviews[0]);
						setLeftIn.setTarget(textviews[0]);
						setRightOut.start();
						setLeftIn.start();
						isBackVisibleimageView1 = true;
					} else {
						setRightOut.setTarget(textviews[0]);
						setLeftIn.setTarget(imgviews[0]);
						setRightOut.start();
						setLeftIn.start();
						isBackVisibleimageView1 = false;
					}
					break;
				case R.id.imageView2:
					pressed[1] = true;
					items.get(1).playFilipinoSound();
					if (!isBackVisibleimageView2) {
						textviews[1].setVisibility(android.view.View.VISIBLE);
						setRightOut.setTarget(imgviews[1]);
						setLeftIn.setTarget(textviews[1]);
						setRightOut.start();
						setLeftIn.start();
						isBackVisibleimageView2 = true;
					} else {
						setRightOut.setTarget(textviews[1]);
						setLeftIn.setTarget(imgviews[1]);
						setRightOut.start();
						setLeftIn.start();
						isBackVisibleimageView2 = false;
					}
					break;
				case R.id.imageView3:
					pressed[2] = true;
					items.get(2).playFilipinoSound();
					if (!isBackVisibleimageView3) {
						textviews[2].setVisibility(android.view.View.VISIBLE);
						setRightOut.setTarget(imgviews[2]);
						setLeftIn.setTarget(textviews[2]);
						setRightOut.start();
						setLeftIn.start();
						isBackVisibleimageView3 = true;
					} else {
						setRightOut.setTarget(textviews[2]);
						setLeftIn.setTarget(imgviews[2]);
						setRightOut.start();
						setLeftIn.start();
						isBackVisibleimageView3 = false;
					}
					break;
				case R.id.imageView4:
					pressed[3] = true;
					items.get(3).playFilipinoSound();
					if (!isBackVisibleimageView4) {
						textviews[3].setVisibility(android.view.View.VISIBLE);
						setRightOut.setTarget(imgviews[3]);
						setLeftIn.setTarget(textviews[3]);
						setRightOut.start();
						setLeftIn.start();
						isBackVisibleimageView4 = true;
					} else {
						setRightOut.setTarget(textviews[3]);
						setLeftIn.setTarget(imgviews[3]);
						setRightOut.start();
						setLeftIn.start();
						isBackVisibleimageView4 = false;
					}
					break;
				}
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
		};
		imgviews[0].setOnClickListener(oclClick);
		imgviews[1].setOnClickListener(oclClick);
		imgviews[2].setOnClickListener(oclClick);
		imgviews[3].setOnClickListener(oclClick);
	}

	private void setMediumTutorial() {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getDifficulty().toString().equalsIgnoreCase(activityLevel)) {
				textviews[itemcnt].setText(items.get(i).getHint());
				imgviews[itemcnt].setImageResource(items.get(i).getImageID());
				frames[itemcnt].setVisibility(View.VISIBLE);
				itemcnt++;
			}
		}

		final AnimatorSet setRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.flipout);

		final AnimatorSet setLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.flipleft);

		View.OnClickListener oclClick = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				tv_rule.setVisibility(View.INVISIBLE);
				switch (v.getId()) {
				case R.id.imageView1:
					pressed[0] = true;
					items.get(0).playFilipinoSound();
					if (!isBackVisibleimageView1) {
						textviews[0].setVisibility(android.view.View.VISIBLE);
						setRightOut.setTarget(imgviews[0]);
						setLeftIn.setTarget(textviews[0]);
						setRightOut.start();
						setLeftIn.start();
						isBackVisibleimageView1 = true;
					} else {
						setRightOut.setTarget(textviews[0]);
						setLeftIn.setTarget(imgviews[0]);
						setRightOut.start();
						setLeftIn.start();
						isBackVisibleimageView1 = false;
					}
					break;
				case R.id.imageView2:
					pressed[1] = true;
					items.get(1).playFilipinoSound();
					if (!isBackVisibleimageView2) {
						textviews[1].setVisibility(android.view.View.VISIBLE);
						setRightOut.setTarget(imgviews[1]);
						setLeftIn.setTarget(textviews[1]);
						setRightOut.start();
						setLeftIn.start();
						isBackVisibleimageView2 = true;
					} else {
						setRightOut.setTarget(textviews[1]);
						setLeftIn.setTarget(imgviews[1]);
						setRightOut.start();
						setLeftIn.start();
						isBackVisibleimageView2 = false;
					}
					break;
				case R.id.imageView3:
					pressed[2] = true;
					items.get(2).playFilipinoSound();
					if (!isBackVisibleimageView3) {
						textviews[2].setVisibility(android.view.View.VISIBLE);
						setRightOut.setTarget(imgviews[2]);
						setLeftIn.setTarget(textviews[2]);
						setRightOut.start();
						setLeftIn.start();
						isBackVisibleimageView3 = true;
					} else {
						setRightOut.setTarget(textviews[2]);
						setLeftIn.setTarget(imgviews[2]);
						setRightOut.start();
						setLeftIn.start();
						isBackVisibleimageView3 = false;
					}
					break;
				case R.id.imageView4:
					pressed[3] = true;
					items.get(3).playFilipinoSound();
					if (!isBackVisibleimageView4) {
						textviews[3].setVisibility(android.view.View.VISIBLE);
						setRightOut.setTarget(imgviews[3]);
						setLeftIn.setTarget(textviews[3]);
						setRightOut.start();
						setLeftIn.start();
						isBackVisibleimageView4 = true;
					} else {
						setRightOut.setTarget(textviews[3]);
						setLeftIn.setTarget(imgviews[3]);
						setRightOut.start();
						setLeftIn.start();
						isBackVisibleimageView4 = false;
					}
					break;
				}
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
		};
		imgviews[0].setOnClickListener(oclClick);
		imgviews[1].setOnClickListener(oclClick);
		imgviews[2].setOnClickListener(oclClick);
		imgviews[3].setOnClickListener(oclClick);
	}

	private void setHardTutorial() {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getDifficulty().toString().equalsIgnoreCase(activityLevel)) {
				textviews[itemcnt].setText(items.get(i).getHint());
				imgviews[itemcnt].setImageResource(items.get(i).getImageID());
				frames[itemcnt].setVisibility(View.VISIBLE);
				itemcnt++;
			}
		}

		final AnimatorSet setRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),

		R.animator.flipout);

		final AnimatorSet setLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.flipleft);

		View.OnClickListener oclClick = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				tv_rule.setVisibility(View.INVISIBLE);
				switch (v.getId()) {
				case R.id.imageView1:
					pressed[0] = true;
					items.get(0).playFilipinoSound();
					if (!isBackVisibleimageView1) {
						textviews[0].setVisibility(android.view.View.VISIBLE);
						setRightOut.setTarget(imgviews[0]);
						setLeftIn.setTarget(textviews[0]);
						setRightOut.start();
						setLeftIn.start();
						isBackVisibleimageView1 = true;
					} else {
						setRightOut.setTarget(textviews[0]);
						setLeftIn.setTarget(imgviews[0]);
						setRightOut.start();
						setLeftIn.start();
						isBackVisibleimageView1 = false;
					}
					break;
				case R.id.imageView2:
					pressed[1] = true;
					items.get(1).playFilipinoSound();
					if (!isBackVisibleimageView2) {
						textviews[1].setVisibility(android.view.View.VISIBLE);
						setRightOut.setTarget(imgviews[1]);
						setLeftIn.setTarget(textviews[1]);
						setRightOut.start();
						setLeftIn.start();
						isBackVisibleimageView2 = true;
					} else {
						setRightOut.setTarget(textviews[1]);
						setLeftIn.setTarget(imgviews[1]);
						setRightOut.start();
						setLeftIn.start();
						isBackVisibleimageView2 = false;
					}
					break;
				case R.id.imageView3:
					pressed[2] = true;
					items.get(2).playFilipinoSound();
					if (!isBackVisibleimageView3) {
						textviews[2].setVisibility(android.view.View.VISIBLE);
						setRightOut.setTarget(imgviews[2]);
						setLeftIn.setTarget(textviews[2]);
						setRightOut.start();
						setLeftIn.start();
						isBackVisibleimageView3 = true;
					} else {
						setRightOut.setTarget(textviews[2]);
						setLeftIn.setTarget(imgviews[2]);
						setRightOut.start();
						setLeftIn.start();
						isBackVisibleimageView3 = false;
					}
					break;
				case R.id.imageView4:
					pressed[3] = true;
					items.get(3).playFilipinoSound();
					if (!isBackVisibleimageView4) {
						textviews[3].setVisibility(android.view.View.VISIBLE);
						setRightOut.setTarget(imgviews[3]);
						setLeftIn.setTarget(textviews[3]);
						setRightOut.start();
						setLeftIn.start();
						isBackVisibleimageView4 = true;
					} else {
						setRightOut.setTarget(textviews[3]);
						setLeftIn.setTarget(imgviews[3]);
						setRightOut.start();
						setLeftIn.start();
						isBackVisibleimageView4 = false;
					}
					break;
				}
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
		};
		imgviews[0].setOnClickListener(oclClick);
		imgviews[1].setOnClickListener(oclClick);
		imgviews[2].setOnClickListener(oclClick);
		imgviews[3].setOnClickListener(oclClick);
	}

	private void initiateViews() {
		textviews = new TextView[9];
		imgviews = new ImageButton[9];
		frames = new RelativeLayout[4];

		pressed = new boolean[4];

		tv_rule = (TextView) findViewById(R.id.tv_rule);
		btn_next = (ImageButton) findViewById(R.id.button1);
		btn_next.setImageDrawable(BtnStatesDirector.getImageDrawable(new BtnNextArrowStatesBuilder()));

		tv_rule.setTypeface(SalinlahiFour.getFontKgsecondchances());

		textviews[0] = (TextView) findViewById(R.id.textView1);
		textviews[0].setVisibility(android.view.View.GONE);
		textviews[1] = (TextView) findViewById(R.id.textView2);
		textviews[1].setVisibility(android.view.View.GONE);
		textviews[2] = (TextView) findViewById(R.id.textView3);
		textviews[2].setVisibility(android.view.View.GONE);
		textviews[3] = (TextView) findViewById(R.id.textView4);
		textviews[3].setVisibility(android.view.View.GONE);
		imgviews[0] = (ImageButton) findViewById(R.id.imageView1);
		imgviews[1] = (ImageButton) findViewById(R.id.imageView2);
		imgviews[2] = (ImageButton) findViewById(R.id.imageView3);
		imgviews[3] = (ImageButton) findViewById(R.id.imageView4);
		pressed[0] = false;
		pressed[1] = false;
		pressed[2] = false;
		pressed[3] = false;
		frames[0] = (RelativeLayout) findViewById(R.id.frame_1);
		frames[1] = (RelativeLayout) findViewById(R.id.frame_2);
		frames[2] = (RelativeLayout) findViewById(R.id.frame_3);
		frames[3] = (RelativeLayout) findViewById(R.id.frame_4);
		frames[0].setVisibility(View.INVISIBLE);
		frames[1].setVisibility(View.INVISIBLE);
		frames[2].setVisibility(View.INVISIBLE);
		frames[3].setVisibility(View.INVISIBLE);

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
}
