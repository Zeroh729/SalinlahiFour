package com.ube.salinlahifour;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.ube.salinlahifour.animation.AnimatedButtonListener;
import com.ube.salinlahifour.enumTypes.LevelType;
import com.ube.salinlahifour.enumTypes.StarType;
import com.ube.salinlahifour.evaluationModule.Evaluation;
import com.ube.salinlahifour.tutorials.Tutorial;

public class ReportCard extends PopupWindow implements OnClickListener {

	private Context context;
	private Lesson lesson;
	private LevelType level;
	private String activityName;
	private static View popupView;

	private ImageView img_star;
	private ImageButton btn_replay;
	private ImageButton btn_next;
	private ImageButton btn_home;
	private TextView tv_title;
	private TextView tv_level;
	private TextView tv_score;
	private TextView tv_evaluation;
	private TextView subtv_replay;
	private TextView subtv_next;

	private AnimatedButtonListener animBtnListener;

	public ReportCard(Context context, Lesson lesson, LevelType level, Evaluation evaluation, String nlg_evaluation, String ActivityName) {
		this.context = context;
		this.lesson = lesson;
		this.level = level;
		this.activityName = ActivityName;
		Log.d("Debug ReportCard", "Inside ReportCard Class");
		LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(this.context.LAYOUT_INFLATER_SERVICE);
		popupView = layoutInflater.inflate(R.layout.reportcard, null);

		setContentView(popupView);
		setHeight(LayoutParams.MATCH_PARENT);
		setWidth(LayoutParams.MATCH_PARENT);

		setOutsideTouchable(false);
		setBackgroundDrawable(new BitmapDrawable());

		img_star = (ImageView) popupView.findViewById(R.id.img_star);
		btn_replay = (ImageButton) popupView.findViewById(R.id.btn_replay);
		btn_next = (ImageButton) popupView.findViewById(R.id.btn_next);
		btn_home = (ImageButton) popupView.findViewById(R.id.btn_home);
		tv_title = (TextView) popupView.findViewById(R.id.tv_title);
		tv_level = (TextView) popupView.findViewById(R.id.tv_level);
		tv_score = (TextView) popupView.findViewById(R.id.tv_score);
		tv_evaluation = (TextView) popupView.findViewById(R.id.tv_evaluation);
		subtv_replay = (TextView) popupView.findViewById(R.id.tv_replaytxt);
		subtv_next = (TextView) popupView.findViewById(R.id.tv_nexttxt);

		animBtnListener = new AnimatedButtonListener();

		btn_replay.setOnTouchListener(animBtnListener);
		btn_home.setOnTouchListener(animBtnListener);
		btn_next.setOnTouchListener(animBtnListener);
		btn_replay.setOnClickListener(this);
		btn_home.setOnClickListener(this);
		btn_next.setOnClickListener(this);

		tv_title.setTypeface(SalinlahiFour.getFontKgsecondchances());
		tv_level.setTypeface(SalinlahiFour.getFontKgsecondchances());
		tv_score.setTypeface(SalinlahiFour.getFontKgtangledupinyou());
		tv_evaluation.setTypeface(SalinlahiFour.getFontPlaytime());

		subtv_replay.setTypeface(SalinlahiFour.getFontPlaytimeOblique());
		subtv_next.setTypeface(SalinlahiFour.getFontPlaytimeOblique());

		tv_title.setText(lesson.getName());
		tv_level.setText("(" + level.toString() + ")");
		Log.d("UGH", "Score:" + evaluation.getScore());
		Log.d("UGH", "LessonNum:" + lesson.getLessonNumber());
		Log.d("UGH", "ItmSize:" + evaluation.getTotalScore());
		tv_evaluation.setText("Hello");
		Log.d("EndFeedback Debug", "Feedback: " + evaluation.getEndofActivityFeedback(evaluation.getScore(), lesson.getLessonNumber()));
		//tv_evaluation.setText(evaluation.getEndofActivityFeedback(evaluation.getScore(), lesson.getLessonNumber()));

		switch (evaluation.getStar()) {
			case GOLD:
				img_star.setImageResource(R.drawable.report_card_gold);

				subtv_replay.setText("");
				break;
			case SILVER:
				img_star.setImageResource(R.drawable.report_card_silver);

				subtv_replay.setText("retry to get\n3 stars!");
				break;
			case BRONZE:
				img_star.setImageResource(R.drawable.report_card_bronze);

				subtv_next.setVisibility(View.INVISIBLE);
				btn_next.setVisibility(View.INVISIBLE);
				subtv_replay.setText("retry to get\n2 stars!");
				break;
			case NONE:
				img_star.setImageResource(R.drawable.report_card_none);

				subtv_next.setVisibility(View.INVISIBLE);
				btn_next.setVisibility(View.INVISIBLE);
				subtv_replay.setText("retry to get\n1 star!");
				break;
		}

		if(level.equals(LevelType.EASY)) {
			subtv_next.setText("play MEDIUM");
		} else if(level.equals(LevelType.MEDIUM)) {
			subtv_next.setText("play HARD");
		} else if(level.equals(LevelType.HARD)) {
			subtv_next.setText("");
			btn_next.setVisibility(View.INVISIBLE);
		} else {
			Log.d("Debug ReportCard", "playNext Missing");
		}
		
		if((evaluation.getStar().equals(StarType.GOLD) || evaluation.getStar().equals(StarType.SILVER))
				&& level.equals(LevelType.HARD)) {
			tv_evaluation.setText(evaluation.getEndofActivityFeedback(evaluation.getScore(), lesson.getLessonNumber()) + "Congratulations! You can now proceed to the next lesson!");
		} else {
			tv_evaluation.setText(evaluation.getEndofActivityFeedback(evaluation.getScore(), lesson.getLessonNumber()));
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_replay:
				((Activity) context).finish();
				Intent intent = new Intent(lesson.getActivity());
				intent.putExtra("activityName", this.activityName);
				intent.putExtra("UserID", SalinlahiFour.getLoggedInUser().getId());
				intent.putExtra("activityLevel", level.toString());
				Bundle bundle = new Bundle();
				bundle.putParcelable("lesson", lesson);
				intent.putExtras(bundle);
				context.startActivity(intent);
				// ((Activity)context).startActivity(((Activity)context).getIntent());
				// goToLesson(level);
				break;
			case R.id.btn_next:
				if(level.equals(LevelType.EASY))
					goToLesson(LevelType.MEDIUM);
				else if(level.equals(LevelType.MEDIUM))
					goToLesson(LevelType.HARD);
				break;
			case R.id.btn_home:
				goToMap();
				break;
		}
	}

	private void goToLesson(LevelType level) {
		Intent intent = new Intent(context, Tutorial.class);
		intent.putExtra("activityLevel", level.toString());
		intent.putExtra("UserID", SalinlahiFour.getLoggedInUser().getId());
		intent.putExtra("lessonName", lesson.getTheRealName());
		Bundle bundle = new Bundle();
		bundle.putParcelable("lesson", lesson);
		intent.putExtras(bundle);
		context.startActivity(intent);
	}

	private void goToMap() {
		Intent intent = new Intent();
		intent.setClass(this.context, MapActivity.class);
		context.startActivity(intent);
	}

	public void reveal() {
		showAsDropDown(popupView);
		Log.d("ReportCard", "Hillo");
		YoYo.with(Techniques.BounceIn).playOn(img_star);
	}
}
