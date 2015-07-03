package com.ube.salinlahifour.lessonActivities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.CountDownTimer;
import android.text.Html;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.ube.salinlahifour.Item;
import com.ube.salinlahifour.R;
import com.ube.salinlahifour.SalinlahiFour;
import com.ube.salinlahifour.animation.AnimatedButtonListener;
import com.ube.salinlahifour.enumTypes.LevelType;
import com.ube.salinlahifour.uibuilders.Button.BtnNextArrowStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.BtnStatesDirector;

public class Family extends AbstractLessonActivity implements OnClickListener, OnTouchListener {
	private TextView tv_dialog;
	private TextView tv_feedback;
	private ImageButton[] choices;
	private ImageView iv_swipe;
	private ImageView scrollView;
	private AnimatedButtonListener buttonAnimation;
	private SoundPool sfx_correct;
	private SoundPool sfx_wrong;
	private TextView itemLabel;
	// private TextView tv_questionno;
	private ImageView img_itemLabel;
	private ImageButton btn_nxt;
	private int totalItems;
	private int width;
	private boolean choicesPlaced = false;


	// Timer Vars
	// private TextView timerTextView;
	// private CountDownTimer timer;

	public Family() {
		Log.d("Debug Family", "Aldrin: Entered Family Class");
		layoutID = R.layout.lessonactivity_family;

	}

	@Override
	protected void configureEasyLevel() {
		totalItems = 4;
	}

	@Override
	protected void configureMediumLevel() {
		totalItems = 7;
	}

	@Override
	protected void configureHardLevel() {
		totalItems = 9;
	}

	@Override
	protected void initiateViews() {
		Log.d("Debug Family", "Aldrin: Initiate Views");

		itemno = 0;
		// Starts Timer
		initiateTimer();
		// TextView DIALOG BOX (Questions)
		// tv_dialog = (TextView)findViewById(R.id.tv_dialog);//Var to XML
		// define layout
		// RelativeLayout.LayoutParams dialog_params = new
		// RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		// dialog_params.leftMargin=100; //X
		// dialog_params.topMargin = 30; //Y
		// tv_dialog.setLayoutParams(dialog_params);
		// TV DIALOG INIT END
		// Text View Feedback
		tv_feedback = (TextView) findViewById(R.id.tv_feedback);
		tv_feedback.setTypeface(SalinlahiFour.getFontAndy());
		tv_feedback.setText(" ");
		tv_feedback.setOnClickListener(this);
		// tv_questionno = (TextView)findViewById(R.id.tv_questionno);
		// tv_questionno.setTypeface(SalinlahiFour.getFontPlaytime());
		// ((TextView)findViewById(R.id.tv_score)).setTypeface(SalinlahiFour.getFontPlaytime());

		// RelativeLayout.LayoutParams feedback_params = new
		// RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		// feedback_params.leftMargin=100; //X
		// feedback_params.bottomMargin = 60; //Y
		// tv_feedback.setLayoutParams(feedback_params);
		// TV FEEDBACK END
		buttonAnimation = new AnimatedButtonListener();
		choices = new ImageButton[totalItems];
		if (activityLevel.equals(LevelType.EASY)) {
			choices[0] = (ImageButton) findViewById(R.id.img_choicea);
			choices[1] = (ImageButton) findViewById(R.id.img_choicec);
			choices[2] = (ImageButton) findViewById(R.id.img_choiced);
			choices[3] = (ImageButton) findViewById(R.id.img_choicef);
		} else if (activityLevel.equals(LevelType.MEDIUM)) {
			Log.d("Debug Family", "Aldrin: Initiate Choices");
			choices[0] = (ImageButton) findViewById(R.id.img_choicea);
			choices[1] = (ImageButton) findViewById(R.id.img_choiceb);
			choices[2] = (ImageButton) findViewById(R.id.img_choiced);
			choices[3] = (ImageButton) findViewById(R.id.img_choicee);
			choices[4] = (ImageButton) findViewById(R.id.img_choiceg);
			choices[5] = (ImageButton) findViewById(R.id.img_choiceh);
			choices[6] = (ImageButton) findViewById(R.id.img_choicei);
			setCntQuestions(7);
		} else {
			Log.d("Debug Family", "Aldrin: Initiate Choices");
			choices[0] = (ImageButton) findViewById(R.id.img_choicea);
			choices[1] = (ImageButton) findViewById(R.id.img_choiceb);
			choices[2] = (ImageButton) findViewById(R.id.img_choicec);
			choices[3] = (ImageButton) findViewById(R.id.img_choiced);
			choices[4] = (ImageButton) findViewById(R.id.img_choicee);
			choices[5] = (ImageButton) findViewById(R.id.img_choicef);
			choices[6] = (ImageButton) findViewById(R.id.img_choiceg);
			choices[7] = (ImageButton) findViewById(R.id.img_choiceh);
			choices[8] = (ImageButton) findViewById(R.id.img_choicei);
			setCntQuestions(9);
		}
		for (int i = 0; i < choices.length; i++) {
			choices[i].setVisibility(View.VISIBLE);
			// choices[i].setOnTouchListener(buttonAnimation);
		}

		scrollView = (ImageView) findViewById(R.id.inside_imageview);
		scrollView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener()
		{
			@Override
			public void onGlobalLayout()
			{
				// gets called after layout has been done but before display.
				layout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
				width = scrollView.getMeasuredWidth() - 200;
				Log.d("NEW WIDTH", " " + width);
				
				width /= totalItems;
				
				if(!choicesPlaced) {
					RelativeLayout.LayoutParams params;
					int leftMargin = 100;
					for (int i = 0; i < totalItems; i++) {
						params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
						params.topMargin = 200; // Y
						params.leftMargin = leftMargin;
						choices[i].setLayoutParams(params);
						leftMargin += width;
					}
					choicesPlaced = true;
				}
			}
		});

		// End Placing
		// Initialize Swipe Indicator
		Log.d("Debug Family", "Aldrin: Initiate Views...Done");

		itemLabel = (TextView) findViewById(R.id.tv_itemlabel);
		itemLabel.setTypeface(SalinlahiFour.getFontAndy());
		img_itemLabel = (ImageView) findViewById(R.id.img_itemLabel);
		if (SalinlahiFour.getLoggedInUser().getGender().equals("female"))
			img_itemLabel.setBackgroundResource(R.drawable.animals_pepaitalking);
		else
			img_itemLabel.setBackgroundResource(R.drawable.animals_popoitalking);

		btn_nxt = (ImageButton) findViewById(R.id.btn_next);
		btn_nxt.setImageDrawable(BtnStatesDirector.getImageDrawable(new BtnNextArrowStatesBuilder()));
		btn_nxt.setOnClickListener(this);

		// ((RelativeLayout)findViewById(R.id.parent_view)).addView(getPauseButton());
	}

	protected void initiateItems() {
		// tv_feedback.setText(""); //delete
		Log.d("Debug Family", "Aldrin: Initiate Items");
		items = new ArrayList<Item>();

		Log.d("Debug Family", "Aldrin: Initiate Items...Done");
	}

	@Override
	protected void update() {
		// tv_questionno.setText((itemno + 1) + "");
		// ((TextView)findViewById(R.id.tv_score)).setText(" / " +
		// questions.size());

		Log.d("Debug Family", "Aldrin: Running");
		setChoices();
		question = questions.get(itemno).getLabel();
		// questions.get(itemno).playFilipinoSound();
		tv_feedback.setText(Html.fromHtml(question));
		// timer.start();
		Log.d("Debug Family", "Aldrin: Running Done");

		itemLabel.setVisibility(View.INVISIBLE);
		img_itemLabel.setVisibility(View.INVISIBLE);
		btn_nxt.setVisibility(View.INVISIBLE);
	}

	protected void rerun() {
		// tv_questionno.setText((itemno + 1) + "");
		// ((TextView)findViewById(R.id.tv_score)).setText(" / " +
		// questions.size());

		Log.d("Debug Family", "Aldrin: Running");
		question = questions.get(itemno).getLabel();
		// questions.get(itemno).playFilipinoSound();
		// setChoices();

		// tv_feedback.setText(question);
		tv_feedback.setText(Html.fromHtml(question));
		// timer.start();
		Log.d("Debug Family", "Aldrin: Running Done");

		itemLabel.setVisibility(View.INVISIBLE);
		img_itemLabel.setVisibility(View.INVISIBLE);
		btn_nxt.setVisibility(View.INVISIBLE);
	}

	public void initiateTimer() {
		Log.d("Debug Family", "Aldrin: Initiate Timer");
		// timerTextView = (TextView) findViewById(R.id.timer_dialog);
		// timerTextView.setText("");
		// timer = new CountDownTimer(180000, 1000){
		//
		// public void onTick(long millisUntilFinished) {
		// timerTextView.setText("seconds remaining: " + millisUntilFinished /
		// 1000);
		// }
		//
		// public void onFinish() {
		// timerTextView.setText("done!");
		// }
		// };

		Log.d("Debug Family", "Aldrin: Initiate Timer...Done");
	}

	protected boolean checkAnswer(String answer) {
		Log.d("Debug Family", "Aldrin: Checking Answer");

		for (int i = 0; i < questions.size(); i++) {
			if (questions.get(i).getWord().equals(answer)) {
				MediaPlayer sound = MediaPlayer.create(this, questions.get(i).getVoiceFilID());
				sound.start();
			}
		}

		return evaluate(answer);
	}

	@Override
	protected void ifAnswerIsCorrect() {
		Log.d("Debug Family", "Aldrin: Feedback: " + feedback);
		tv_feedback.setText(Html.fromHtml(question));
		tv_feedback.setVisibility(View.INVISIBLE);
		Log.d("Debug Family", "Aldrin: Immediate Feedback Completed");

		rerun();
	}

	@Override
	protected void ifAnswerIsWrong() {
		Log.d("Debug Family", "Aldrin: Feedback: " + feedback);
		tv_feedback.setText(Html.fromHtml(feedback + "\n" + question));
	}

	private void setChoices() {
		// int answerIndex = new Random().nextInt(choices.length);
		ArrayList<Integer> taken = new ArrayList();
		// taken.add(items.indexOf(questions.get(itemno)));
		for (int i = 0; i < choices.length; i++) {
			// if(i == answerIndex){
			// choices[i].setImageResource(questions.get(itemno).getImageID());
			// choices[i].setTag(questions.get(itemno).getWord());
			// }
			// else{
			int rand;
			do {
				rand = new Random().nextInt(questions.size());
				// Log.d("Debug Family","choices.length: " +choices.length +
				// " answerIndex: " + answerIndex + " taken: " +
				// taken.toString() + " i:" + i);
			} while (taken.contains(rand));

			taken.add(rand);
			choices[i].setImageResource(questions.get(rand).getImageID());
			choices[i].setTag(questions.get(rand).getWord());
			// }
			Log.d("Debug Family", "Aldrin: setting choices: " + i);
		}
		Log.d("Debug Family", "Aldrin: setting choices: done");
	}

	@Override
	public void onClick(View v) {
		int choice = 0;
		if (img_itemLabel.getVisibility() == View.INVISIBLE) {
			switch (v.getId()) {
			case R.id.img_choicea:
			case R.id.img_choiceb:
			case R.id.img_choicec:
			case R.id.img_choiced:
			case R.id.img_choicee:
			case R.id.img_choicef:
			case R.id.img_choiceg:
			case R.id.img_choiceh:
			case R.id.img_choicei:

				for (int i = 0; i < choices.length; i++) {
					ImageView img = choices[i];
					img.setColorFilter(new LightingColorFilter(0xffffffff, 0x000000));
				}
				if (checkAnswer(v.getTag().toString())) {
					// itemLabel.setTextColor(Color.GREEN);
					// itemLabel.setText("Correct! That's " +
					// v.getTag().toString());
					itemLabel.setText(feedback);
					itemLabel.setVisibility(View.VISIBLE);
					btn_nxt.setVisibility(View.VISIBLE);
					img_itemLabel.setVisibility(View.VISIBLE);
					tv_feedback.setVisibility(View.INVISIBLE);
					YoYo.with(Techniques.Pulse).playOn(v);
					// setChoices();
				} else {
					// itemLabel.setTextColor(Color.RED);
					// itemLabel.setText("That's " + v.getTag().toString());
					YoYo.with(Techniques.Shake).playOn(v);
					ImageView img = (ImageButton) v;
					img.setColorFilter(new LightingColorFilter(0xffcc0000, 0x000000));
					// questions.get(itemno).playFilipinoSound();
					// v.getBackground().setColorFilter(new
					// LightingColorFilter(0xff888888, 0x000000));
				}
				break;
			case R.id.tv_feedback:
				questions.get(itemno).playFilipinoSound();
				break;
			default:
				// tv_feedback.setText("error in onclick");
			}
		} else {
			switch (v.getId()) {
			case R.id.btn_next:
				btn_nxt.setVisibility(View.INVISIBLE);
				img_itemLabel.setVisibility(View.INVISIBLE);
				itemLabel.setVisibility(View.INVISIBLE);
				tv_feedback.setVisibility(View.VISIBLE);
				break;
			}
		}

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		if (event.getAction() == MotionEvent.ACTION_SCROLL) {
			return true;
		} else {
			return false;
		}
	}
}
