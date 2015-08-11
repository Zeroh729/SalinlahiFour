package com.ube.salinlahifour;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.qwerjk.better_text.MagicTextView;
import com.ube.salinlahifour.database.UserLessonProgressOperations;
import com.ube.salinlahifour.debugclasses.DebugUserModuleActivity;
import com.ube.salinlahifour.debugclasses.Log;
import com.ube.salinlahifour.enumTypes.LevelType;
import com.ube.salinlahifour.enumTypes.StarType;
import com.ube.salinlahifour.model.UserLessonProgress;
import com.ube.salinlahifour.tutorials.Tutorial;
import com.ube.salinlahifour.uibuilders.Button.BtnStatesDirector;
import com.ube.salinlahifour.uibuilders.Button.EasyBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.HardBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.LogoutBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.MapNextBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.MapPrevBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.MediumBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.NoBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.PopupcloseBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.ProgressBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.YesBtnStatesBuilder;

public class MapActivity extends Activity implements OnClickListener {
	private Context context;

	private ArrayList<Scene> scenes;
	private ImageButton[] imgBtns;
	private TextView[] txtViews;
	private Scene scene;
	private ImageButton btn_progress;
	private ImageButton btn_logout;
	private ImageButton btn_account;
	private TextView tv_friendTalk;

	private PopupWindow popupWindow;
	private View popupView;
	private MagicTextView tv_title;
	private TextView tv_desc;
	private ImageButton btn_popupclose;
	private ImageButton btn_easy;
	private ImageButton btn_medium;
	private ImageButton btn_hard;
	private ImageButton btn_nxtscene;
	private ImageButton btn_prevscene;
	private ImageView img_popupmap;
	private RelativeLayout parentView;

	private PopupWindow notifWindow;
	private View notifView;
	private MagicTextView notif_tv_title;
	private ImageButton notif_btn_popupclose;
	private TextView notif_tv_desc;
	private ImageButton notif_btn_ok;
	private ImageButton notif_btn_no;

	private PopupWindow logoutWindow;
	private View logoutView;
	private ImageButton logout_btn_popupclose;
	private ImageButton logout_btn_ok;
	private ImageButton logout_btn_no;
	private MagicTextView logout_tv_title;
	private TextView logout_tv_desc;

	private int UserID;
	private int sceneIndex;
	private Intent intent = null;

	private String[] greetings;
	private int greetingIndex = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;

		Log.d("ONCREATING", "TESTTESTTESTEST");

		Bundle extras = getIntent().getExtras();
		if(extras != null) {
			UserID = extras.getInt("UserID");
		} else {
			UserID = SalinlahiFour.getLoggedInUser().getId();
		}

		imgBtns = new ImageButton[5];
		txtViews = new TextView[5];
		greetings = new String[] { "<i>Magandang Araw</i> " + SalinlahiFour.getLoggedInUser().getName() + "!<br><font color=#8C8C8C>Good day " + SalinlahiFour.getLoggedInUser().getName() + "!</font>", "<i>Kamusta na?</i><br><font color=#8C8C8C>How are you?</font>", "Haha! That tickles!", "I want to eat already!", "Tara!<br><font color=#8C8C8C>Let's go!</font>" };

		// setContentView(R.layout.activity_map);

		if(SalinlahiFour.getLoggedInUser() == null) {
			Intent intent = new Intent();
			intent.setClass(getApplicationContext(), RegistrationActivityName.class);
			startActivity(intent);
		} else {
			Toast toast = Toast.makeText(getApplicationContext(), "Welcome " + ((SalinlahiFour) getApplication()).getLoggedInUser().getName() + "!!!", Toast.LENGTH_SHORT);
			toast.show();
			
			parseXML();
			setLevelStatuses();

			sceneIndex = 0;
			scene = scenes.get(sceneIndex);
			setLayout();
		}

	}

	private void setMapMenuButtons() {
		btn_account = new ImageButton(this);
		btn_logout = new ImageButton(this);
		btn_progress = new ImageButton(this);
		btn_nxtscene = new ImageButton(this);
		btn_prevscene = new ImageButton(this);
		tv_friendTalk = new TextView(this);
		
		if(!SalinlahiFour.DEBUGMODE) {
			Button register = (Button) findViewById(R.id.btn_register);
			Button debugProgress = (Button) findViewById(R.id.btn_usermodule);
			
			register.setVisibility(Button.GONE);
			register.setEnabled(false);
			debugProgress.setVisibility(Button.GONE);
			debugProgress.setEnabled(false);
		}

		LayoutParams p_account = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

		p_account.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		p_account.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		p_account.setMargins(10, 10, 0, 0);

		btn_account.setScaleX(0.8f);
		btn_account.setScaleY(0.8f);
		btn_account.setLayoutParams(p_account);
		btn_account.setBackgroundDrawable(null);
		btn_account.setOnClickListener(this);
		btn_account.setId(2);

		LayoutParams p_friendtalk = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

		p_friendtalk.addRule(RelativeLayout.RIGHT_OF, btn_account.getId());
		p_friendtalk.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		p_friendtalk.setMargins(10, 10, 0, 0);

		tv_friendTalk.setLayoutParams(p_friendtalk);
		tv_friendTalk.setBackgroundResource(R.drawable.dialog);
		tv_friendTalk.setTextColor(Color.argb(255, 37, 37, 37));
		tv_friendTalk.setPadding(20, 20, 20, 20);
		tv_friendTalk.setTextSize(19);

		LayoutParams p_logout = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

		p_logout.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		p_logout.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		p_logout.setMargins(0, 18, 10, 0);

		btn_logout.setLayoutParams(p_logout);
		btn_logout.setBackgroundDrawable(null);
		btn_logout.setOnClickListener(this);
		btn_logout.setId(1);

		LayoutParams p_progress = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

		p_progress.addRule(RelativeLayout.LEFT_OF, btn_logout.getId());
		p_progress.addRule(RelativeLayout.ALIGN_TOP, btn_logout.getId());
		p_progress.addRule(RelativeLayout.ALIGN_BASELINE, btn_logout.getId());

		btn_progress.setLayoutParams(p_progress);
		btn_progress.setBackgroundDrawable(null);
		btn_progress.setOnClickListener(this);

		LayoutParams p_nxtscene = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

		p_nxtscene.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		p_nxtscene.addRule(RelativeLayout.CENTER_VERTICAL);

		btn_nxtscene.setLayoutParams(p_nxtscene);
		btn_nxtscene.setBackgroundDrawable(null);
		btn_nxtscene.setOnClickListener(this);

		LayoutParams p_prevscene = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

		p_prevscene.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		p_prevscene.addRule(RelativeLayout.CENTER_VERTICAL);

		btn_prevscene.setLayoutParams(p_prevscene);
		btn_prevscene.setBackgroundDrawable(null);
		btn_prevscene.setOnClickListener(this);

		tv_friendTalk.setText(Html.fromHtml(greetings[greetingIndex]));

		parentView.addView(btn_account);
		parentView.addView(btn_logout);
		parentView.addView(btn_progress);
		parentView.addView(btn_nxtscene);
		parentView.addView(btn_prevscene);
		parentView.addView(tv_friendTalk);
	}

	private void setLevelStatuses() {
		UserLessonProgressOperations userdb = new UserLessonProgressOperations(this);
		userdb.open();
		Log.d("scene count: " + scenes.size(), "FINAL CHECKING");
		ArrayList<Lesson> lessonList = SalinlahiFour.getLessonsList();

		for(Lesson lesson : lessonList) {
			int preReqIndex = lesson.getPreReq();
			if(preReqIndex != 0) {
				Lesson preReq = lessonList.get(preReqIndex - 1);
				UserLessonProgress progress = userdb.getUserLessonProgress(UserID, preReq.getName());

				if(progress != null) {
					if(StarType.getStar(progress.getHardStar()).getValue() > StarType.BRONZE.getValue()) {
						lesson.setLocked(false);
					}
				}
			} else {
				lesson.setLocked(false);
			}
		}

		try {
			scenes.get(0).getLessons().get(0).setLocked(false);
		} catch(Exception e) {
			Toast.makeText(this, "Can't unlock", Toast.LENGTH_SHORT).show();
		}

		userdb.close();
	}

	private void instantiateViews() {
		btn_logout.setImageDrawable(BtnStatesDirector.getImageDrawable(new LogoutBtnStatesBuilder()));
		btn_progress.setImageDrawable(BtnStatesDirector.getImageDrawable(new ProgressBtnStatesBuilder()));
		btn_nxtscene.setImageDrawable(BtnStatesDirector.getImageDrawable(new MapNextBtnStatesBuilder()));
		btn_prevscene.setImageDrawable(BtnStatesDirector.getImageDrawable(new MapPrevBtnStatesBuilder()));
	}

	private void errorPopup(String title, String error) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(title);
		builder.setMessage(error);
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		builder.setPositiveButton("I'll debug it right away!", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				System.exit(0);

			}
		});
		builder.show();
	}

	public void instantiatePopupView() {
		LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
		popupView = layoutInflater.inflate(R.layout.activity_difficulty, null);

		popupWindow = new PopupWindow(popupView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		popupWindow.setOutsideTouchable(false);
		popupWindow.setBackgroundDrawable(new BitmapDrawable());

		btn_easy = (ImageButton) popupView.findViewById(R.id.btn_easy);
		btn_medium = (ImageButton) popupView.findViewById(R.id.btn_med);
		btn_hard = (ImageButton) popupView.findViewById(R.id.btn_hard);
		btn_popupclose = (ImageButton) popupView.findViewById(R.id.btn_popupclose);
		img_popupmap = (ImageView) popupView.findViewById(R.id.popup_mapimg);

		tv_desc = (TextView) popupView.findViewById(R.id.tv_description);
		tv_desc.setTypeface(SalinlahiFour.getFontBpreplay());
		tv_title = (MagicTextView) popupView.findViewById(R.id.tv_title);
		for(int i = 0; i < 30; i++)
			tv_title.addOuterShadow(5, 0, 0, 0xFF164366);
		tv_title.setTypeface(SalinlahiFour.getFontKgsecondchances());

		btn_popupclose.setImageDrawable(BtnStatesDirector.getImageDrawable(new PopupcloseBtnStatesBuilder()));
		btn_easy.setImageDrawable(BtnStatesDirector.getImageDrawable(new EasyBtnStatesBuilder()));
		btn_medium.setImageDrawable(BtnStatesDirector.getImageDrawable(new MediumBtnStatesBuilder()));
		btn_hard.setImageDrawable(BtnStatesDirector.getImageDrawable(new HardBtnStatesBuilder()));

	}

	public void instantiateNotifView() {
		LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
		notifView = layoutInflater.inflate(R.layout.popup_notification, null);

		notif_btn_popupclose = (ImageButton) notifView.findViewById(R.id.notif_btn_popupclose);

		notifWindow = new PopupWindow(notifView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		notifWindow.setOutsideTouchable(false);
		notifWindow.setBackgroundDrawable(new BitmapDrawable());

		notif_tv_desc = (TextView) notifView.findViewById(R.id.tv_description);
		notif_tv_desc.setTypeface(SalinlahiFour.getFontBpreplay());
		notif_tv_title = (MagicTextView) notifView.findViewById(R.id.tv_title);
		
		for(int i = 0; i < 30; i++)
			notif_tv_title.addOuterShadow(5, 0, 0, 0xFF164366);
		notif_tv_title.setTypeface(SalinlahiFour.getFontKgsecondchances());

		notif_btn_popupclose.setImageDrawable(BtnStatesDirector.getImageDrawable(new PopupcloseBtnStatesBuilder()));

		notif_tv_desc.setText("Get at least 2 stars from the previous lesson in hard mode to unlock this lesson.");
		notif_tv_title.setText("Lesson Locked");
	}

	public void instantiateLogoutView() {
		LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
		logoutView = layoutInflater.inflate(R.layout.popup_logout, null);

		logout_btn_popupclose = (ImageButton) logoutView.findViewById(R.id.logout_btn_popupclose);
		logout_btn_no = (ImageButton) logoutView.findViewById(R.id.logout_btn_no);
		logout_btn_ok = (ImageButton) logoutView.findViewById(R.id.logout_btn_yes);

		logoutWindow = new PopupWindow(logoutView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		logoutWindow.setOutsideTouchable(false);
		logoutWindow.setBackgroundDrawable(new BitmapDrawable());

		logout_tv_desc = (TextView) logoutView.findViewById(R.id.tv_description);
		logout_tv_desc.setTypeface(SalinlahiFour.getFontBpreplay());
		logout_tv_title = (MagicTextView) logoutView.findViewById(R.id.tv_title);
		for(int i = 0; i < 30; i++)
			logout_tv_title.addOuterShadow(5, 0, 0, 0xFF164366);
		logout_tv_title.setTypeface(SalinlahiFour.getFontKgsecondchances());

		logout_btn_popupclose.setImageDrawable(BtnStatesDirector.getImageDrawable(new PopupcloseBtnStatesBuilder()));
		logout_btn_ok.setImageDrawable(BtnStatesDirector.getImageDrawable(new YesBtnStatesBuilder()));
		logout_btn_no.setImageDrawable(BtnStatesDirector.getImageDrawable(new NoBtnStatesBuilder()));
	}

	public void navigateWidget(int choice) {
		switch (choice) {
			case 1:
				Log.d("debug", "register intent go!");
				intent = new Intent(this, RegistrationActivityName.class);
				startActivity(intent);
				break;
			case 2:
				intent = new Intent(this, LoginActivity.class);
				startActivity(intent);
				break;
		}
	}

	public void parseXML() {
		scenes = new ArrayList();
		scene = makeNewScene();

		for(int i = 0; i < SalinlahiFour.getLessonsList().size(); i++) {
			if(scene.getLessons().size() > 5) {
				scene = makeNewScene();
			}
			Lesson lesson = SalinlahiFour.getLessonsList().get(i);
			lesson.setLocked(true);
			scene.addLesson(lesson);
		}

	}

	private Scene makeNewScene() {
		scenes.add(new Scene(null, SalinlahiFour.getMapPage(scenes.size())));

		return scenes.get(scenes.size() - 1);
	}

	private void setLayout() {
		setContentView(scene.getLayoutID());

		try {
			imgBtns[0] = (ImageButton) findViewById(R.id.img_lesson1);
			imgBtns[1] = (ImageButton) findViewById(R.id.img_lesson2);
			imgBtns[2] = (ImageButton) findViewById(R.id.img_lesson3);
			imgBtns[3] = (ImageButton) findViewById(R.id.img_lesson4);
			imgBtns[4] = (ImageButton) findViewById(R.id.img_lesson5);

			for(ImageButton img : imgBtns) {
				img.setImageDrawable(null);
				img.setScaleX(0.8f);
				img.setScaleY(0.8f);
				img.setBackgroundColor(Color.TRANSPARENT);
			}

		} catch(NullPointerException e) {
			errorPopup("View not found", e.getMessage() + "\nCheck if each scene contains views with ids img_lesson1, img_lesson2, img_lesson3, img_lesson4, and img_lesson5.");
		}

		parentView = (RelativeLayout) findViewById(R.id.parent_view);

		Log.d("Scene:" + scenes.indexOf(scene) + " Lesson size:" + scene.getLessons().size(), "TEST");

		for(int i = 0; i < scene.getLessons().size(); i++) {
			imgBtns[i].setVisibility(View.VISIBLE);
			if(!scene.getLessons().get(i).getLocked()) {
				Log.d("TEST0", "Setting map icons for lesson: " + scene.getLessons().get(i).getTheRealName() + " -> " + scene.getLessons().get(i).getImage());
				imgBtns[i].setImageResource(scene.getLessons().get(i).getImage());
				imgBtns[i].setTag(i);
				imgBtns[i].setOnClickListener(this);

				RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
				int margin = ((RelativeLayout.LayoutParams) imgBtns[i].getLayoutParams()).bottomMargin;
				margin = (margin < 0) ? Math.abs(margin) : 0;

				p.addRule(RelativeLayout.BELOW, imgBtns[i].getId());
				p.addRule(RelativeLayout.ALIGN_LEFT, imgBtns[i].getId());
				p.addRule(RelativeLayout.ALIGN_RIGHT, imgBtns[i].getId());
				p.setMargins(0, margin, 0, 0);

				txtViews[i] = new TextView(this);
				txtViews[i].setText(scene.getLessons().get(i).getName());
				txtViews[i].setTextAppearance(getApplicationContext(), R.style.mapLabel);
				txtViews[i].setTypeface(SalinlahiFour.getFontPlaytime());
				txtViews[i].setGravity(Gravity.CENTER_HORIZONTAL);

				txtViews[i].setLayoutParams(p);
				txtViews[i].setBackgroundResource(R.drawable.map_labels);

				parentView.addView(txtViews[i]);
			} else {
				imgBtns[i].setImageResource(R.drawable.lesson0_icon);
				imgBtns[i].setOnClickListener(this);
			}
		}
		setMapMenuButtons();
		instantiateViews();

		if(SalinlahiFour.getLoggedInUser().getGender().equals("female")) {
			btn_account.setBackgroundResource(R.drawable.map_hud_pepay_talking);
		} else {
			btn_account.setBackgroundResource(R.drawable.map_hud_popoi_talking);
		}

		btn_prevscene.setVisibility(View.VISIBLE);
		btn_nxtscene.setVisibility(View.VISIBLE);
		Log.d("sceneIndex: " + sceneIndex, "TEST");
		if(sceneIndex == 0) {
			btn_prevscene.setVisibility(View.INVISIBLE);
		}
		if(sceneIndex >= scenes.size() - 1) {
			btn_nxtscene.setVisibility(View.INVISIBLE);
		}
	}

	@Override
	public void onClick(View view) {
		int index = -1;

		if(view == btn_account) {
			if((greetingIndex + 1) >= greetings.length) {
				greetingIndex = 0;
			} else {
				greetingIndex++;
			}
			tv_friendTalk.setText(Html.fromHtml(greetings[greetingIndex]));

		} else if(view == btn_logout) {
			instantiateLogoutView();
			logoutWindow.showAsDropDown(logoutView);
		} else if(view == btn_progress) {
			intent = new Intent(this, ProgressTreeActivity.class);
			startActivity(intent);
		} else if(view == btn_nxtscene) {
			sceneIndex++;
			scene = scenes.get(sceneIndex);
			setLayout();
		} else if(view == btn_prevscene) {
			sceneIndex--;
			scene = scenes.get(sceneIndex);
			setLayout();
		} else {

			switch (view.getId()) {
				case R.id.img_lesson1:
					index = 0;
					break;
				case R.id.img_lesson2:
					index = 1;
					break;
				case R.id.img_lesson3:
					index = 2;
					break;
				case R.id.img_lesson4:
					index = 3;
					break;
				case R.id.img_lesson5:
					index = 4;
					break;
				case R.id.btn_usermodule:
					intent = new Intent(this, DebugUserModuleActivity.class);
					startActivity(intent);
					break;
				case R.id.btn_register:
					intent = new Intent(this, RegistrationActivityName.class);
					startActivity(intent);
					break;
				case R.id.btn_popupclose:
					popupWindow.dismiss();
					break;
				case R.id.notif_btn_popupclose:
					notifWindow.dismiss();
					break;
				case R.id.logout_btn_popupclose:
				case R.id.logout_btn_no:
					logoutWindow.dismiss();
					break;
				case R.id.logout_btn_yes:
					intent = new Intent(this, LoginActivity.class);
					startActivity(intent);
					break;
			}

			final int fIndex = index;

			if(index != -1) { // If lesson is pressed, continue
				final Lesson lessonDetails = scene.getLessons().get(index);

				if(!lessonDetails.getLocked()) {
					instantiatePopupView();

					tv_title.setText(lessonDetails.getName());
					tv_desc.setText(lessonDetails.getDescription());
					img_popupmap.setImageResource(lessonDetails.getImage());

					Log.d("Pressed a lesson", "TEST");

					ImageButton easy = (ImageButton) popupView.findViewById(R.id.btn_easy);
					easy.setOnClickListener(new ImageButton.OnClickListener() {
						@Override
						public void onClick(View v) {
							intent = new Intent(context, Tutorial.class);
							intent.putExtra("lessonName", scene.getLessons().get(fIndex).getTheRealName());
							intent.putExtra("activityLevel", LevelType.EASY.toString());

							startActivity(intent);
						}
					});
					ImageButton medium = (ImageButton) popupView.findViewById(R.id.btn_med);
					medium.setOnClickListener(new ImageButton.OnClickListener() {

						@Override
						public void onClick(View v) {
							intent = new Intent(context, Tutorial.class);
							intent.putExtra("lessonName", scene.getLessons().get(fIndex).getTheRealName());
							intent.putExtra("activityLevel", LevelType.MEDIUM.toString());
							startActivity(intent);
						}
					});
					ImageButton hard = (ImageButton) popupView.findViewById(R.id.btn_hard);
					hard.setOnClickListener(new ImageButton.OnClickListener() {

						@Override
						public void onClick(View v) {
							intent = new Intent(context, Tutorial.class);
							intent.putExtra("lessonName", scene.getLessons().get(fIndex).getTheRealName());
							intent.putExtra("activityLevel", LevelType.HARD.toString());
							startActivity(intent);
						}
					});

					UserLessonProgressOperations userdb = new UserLessonProgressOperations(this);
					userdb.open();
					UserLessonProgress lesson = userdb.getUserLessonProgress(SalinlahiFour.getLoggedInUser().getId(), lessonDetails.getName());

					if(lesson == null) {
						lesson = new UserLessonProgress();
						lesson.setEasyStar(null);
					}
					if(lesson.getEasyStar() != null) {
						if(!setStars(lesson.getEasyStar(), (ImageView) popupView.findViewById(R.id.star1))) {
							btn_medium.setEnabled(false);
							btn_hard.setEnabled(false);
						}
					} else {
						// ((ImageView)popupView.findViewById(R.id.star1)).setImageResource(R.drawable.lvlselect_null);
						btn_medium.setEnabled(false);
						btn_hard.setEnabled(false);
					}
					if(lesson.getMediumStar() != null) {
						if(!setStars(lesson.getMediumStar(), (ImageView) popupView.findViewById(R.id.star2))) {
							btn_hard.setEnabled(false);
						}
					} else {
						// ((ImageView)popupView.findViewById(R.id.star2)).setImageResource(R.drawable.lvlselect_null);
						btn_hard.setEnabled(false);
					}
					if(lesson.getHardStar() != null) {
						setStars(lesson.getHardStar(), (ImageView) popupView.findViewById(R.id.star3));
					} else {
						// ((ImageView)popupView.findViewById(R.id.star3)).setImageResource(R.drawable.lvlselect_null);
					}

					if(!btn_medium.isEnabled()) {
						((ImageView) popupView.findViewById(R.id.star2)).setImageResource(R.drawable.lvlselect_null);
					}
					if(!btn_hard.isEnabled()) {
						((ImageView) popupView.findViewById(R.id.star3)).setImageResource(R.drawable.lvlselect_null);
					}

					userdb.close();
					popupWindow.showAsDropDown(popupView);
				} else {
					instantiateNotifView();
					notifWindow.showAsDropDown(notifView);
				}
			}
		}
	}

	private boolean setStars(String star, ImageView imageView) {
		boolean b = true;
		switch (star) {
			case "GOLD":
				imageView.setImageResource(R.drawable.lvlselect_gold);
				break;
			case "SILVER":
				imageView.setImageResource(R.drawable.lvlselect_silver);
				break;
			case "BRONZE":
				imageView.setImageResource(R.drawable.lvlselect_bronze);
				b = false;
				break;
			case "NONE":
				imageView.setImageResource(R.drawable.lvlselect_none);
				b = false;
				break;
		}
		return b;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		SalinlahiFour.getBgm().start();

		Log.d("onResume", "MapActivity TEST");

	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();

		Log.d("onRestart", "MapActivity TEST");

		finish();
		Intent intent = new Intent(this, MapActivity.class);
		startActivity(intent);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		SalinlahiFour.getBgm().pause();
	}
}