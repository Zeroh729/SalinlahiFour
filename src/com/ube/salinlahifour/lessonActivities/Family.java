package com.ube.salinlahifour.lessonActivities;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.LightingColorFilter;
import android.media.SoundPool;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
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
import com.ube.salinlahifour.database.UserLessonProgressOperations;
import com.ube.salinlahifour.database.UserRecordOperations;
import com.ube.salinlahifour.enumTypes.LevelType;
import com.ube.salinlahifour.evaluationModule.Evaluation;

public class Family extends AbstractLessonActivity implements OnClickListener, OnTouchListener {
	private TextView tv_dialog;
	private TextView tv_feedback;
	private ImageButton[] choices;
	private int itemno;
	private ImageView iv_swipe;
	private AnimatedButtonListener buttonAnimation;
	private SoundPool sfx_correct;
	private SoundPool sfx_wrong;
	
	private UserRecordOperations userRecordOperator = new UserRecordOperations(this);
	private UserLessonProgressOperations userLessonProgressOperator = new UserLessonProgressOperations(this);

	private String question;

	private String feedback = " ";
	//Timer Vars
	private TextView timerTextView;
	private CountDownTimer timer;
	
	public Family(){
		Log.d("Debug Family","Aldrin: Entered Family Class");
		layoutID = R.layout.lessonactivity_family;
	}
	


	@Override
	protected void initiateViews() {
		Log.d("Debug Family","Aldrin: Initiate Views");
		evaluation =  new Evaluation(NLG, lesson.getName(), activityLevel.toString());
		itemno = 0;
		//Starts Timer
		initiateTimer();
		//TextView DIALOG BOX (Questions)
		//tv_dialog = (TextView)findViewById(R.id.tv_dialog);//Var to XML
		//define layout
		//RelativeLayout.LayoutParams dialog_params =  new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		//dialog_params.leftMargin=100; //X
		//dialog_params.topMargin = 30; //Y
		//tv_dialog.setLayoutParams(dialog_params);
		//TV DIALOG INIT END
		//Text View Feedback 
		tv_feedback = (TextView) findViewById(R.id.tv_feedback);
		tv_feedback.setTypeface(SalinlahiFour.getFontAndy());
		tv_feedback.setText(" ");
		//RelativeLayout.LayoutParams feedback_params =  new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		//feedback_params.leftMargin=100; //X
		//feedback_params.bottomMargin = 60; //Y
		//tv_feedback.setLayoutParams(feedback_params);
		//TV FEEDBACK END
		buttonAnimation = new AnimatedButtonListener();
		if(activityLevel.equals(LevelType.EASY)){
			choices = new ImageButton[4];
			choices[0] = (ImageButton) findViewById(R.id.img_choicea);
			choices[1] = (ImageButton) findViewById(R.id.img_choicec);
			choices[2] = (ImageButton) findViewById(R.id.img_choiced);
			choices[3] = (ImageButton) findViewById(R.id.img_choicef);
		}
		else if(activityLevel.equals(LevelType.MEDIUM)){
			choices = new ImageButton[6];
			Log.d("Debug Family","Aldrin: Initiate Choices");
			choices[0] = (ImageButton) findViewById(R.id.img_choicea);
			choices[1] = (ImageButton) findViewById(R.id.img_choicec);
			choices[2] = (ImageButton) findViewById(R.id.img_choicee);
			choices[3] = (ImageButton) findViewById(R.id.img_choicef);
			choices[4] = (ImageButton) findViewById(R.id.img_choiceg);
			choices[5] = (ImageButton) findViewById(R.id.img_choicei);
		}else{
			choices = new ImageButton[9];
			Log.d("Debug Family","Aldrin: Initiate Choices");
			choices[0] = (ImageButton) findViewById(R.id.img_choicea);
			choices[1] = (ImageButton) findViewById(R.id.img_choiceb);
			choices[2] = (ImageButton) findViewById(R.id.img_choicec);
			choices[3] = (ImageButton) findViewById(R.id.img_choiced);
			choices[4] = (ImageButton) findViewById(R.id.img_choicee);
			choices[5] = (ImageButton) findViewById(R.id.img_choicef);
			choices[6] = (ImageButton) findViewById(R.id.img_choiceg);
			choices[7] = (ImageButton) findViewById(R.id.img_choiceh);
			choices[8] = (ImageButton) findViewById(R.id.img_choicei);
		}
		for(int i = 0; i<choices.length;i++){
			choices[i].setVisibility(View.VISIBLE);
		//	choices[i].setOnTouchListener(buttonAnimation);
		}
		
		int MAX_CHOICES = 9;
		RelativeLayout.LayoutParams params[] = new RelativeLayout.LayoutParams[MAX_CHOICES];
		
		for(int i = 0; i< MAX_CHOICES; i++){
			params[i] = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT); 
			params[i].topMargin = 200; //Y
		}
		params[0].leftMargin=500; //X
		params[1].leftMargin=1000; //X
		params[2].leftMargin=1500; //X
		params[3].leftMargin=2000; //X
		params[4].leftMargin=2500; //X
		params[5].leftMargin=2900; //X
		params[6].leftMargin=3100; //X
		params[7].leftMargin=3300; //X
		params[8].leftMargin=3600; //X
		for(int i = 0; i< choices.length; i++){
			if(activityLevel.equals(LevelType.EASY.toString()))
				choices[i].setLayoutParams(params[i*2]);
			else 
				choices[i].setLayoutParams(params[i]);
		}
		//End Placing
		//Initialize Swipe Indicator
		iv_swipe = (ImageView) findViewById(R.id.swipe_ind);
		Log.d("Debug Family","Aldrin: Initiate Views...Done");
		
	}

	protected void initiateItems() {
		//tv_feedback.setText("");	//delete
		Log.d("Debug Family","Aldrin: Initiate Items");
		items = new ArrayList<Item>();

		Log.d("Debug Family","Aldrin: Initiate Items...Done");
	}

	@Override
	protected void run() {
		Log.d("Debug Family","Aldrin: Running");
		setChoices();
		question = questions.get(itemno).getLabel();
		tv_feedback.setText(question);
		timer.start();
		Log.d("Debug Family","Aldrin: Running Done");
		
	}
	protected void rerun() {
		Log.d("Debug Family","Aldrin: Running");
		question = questions.get(itemno).getLabel();
		 
		//tv_feedback.setText(question);
		tv_feedback.setText(feedback + " " + question);
		//timer.start();
		Log.d("Debug Family","Aldrin: Running Done");
	}
	public void initiateTimer(){
		Log.d("Debug Family","Aldrin: Initiate Timer");
		timerTextView = (TextView) findViewById(R.id.timer_dialog);
		timerTextView.setText("");
		timer = new CountDownTimer(180000, 1000){

		     public void onTick(long millisUntilFinished) {
		    	 timerTextView.setText("seconds remaining: " + millisUntilFinished / 1000);
		     }

		     public void onFinish() {
		    	 timerTextView.setText("done!");
		     }
		  };
		
		Log.d("Debug Family","Aldrin: Initiate Timer...Done");
	}
	
	protected boolean checkAnswer(String answer) {
		Log.d("Debug Family","Aldrin: Checking Answer");
		userRecordOperator.open();
		userLessonProgressOperator.open();
		if(evaluation.evaluateAnswer(questions.get(itemno).getWord(), answer, userRecordOperator, UserID)){
			//NLG Part - Correct
			Log.d("Debug Family", "Aldrin: Answer: " + answer);
			Log.d("Debug Family", "Aldrin: Index: " + itemno);
			feedback = evaluation.getImmediateFeedback(questions.get(itemno).getQ_num(), answer, lesson.getLessonNumber());
			Log.d("Debug Family", "Aldrin: Feedback: "+ feedback);
			tv_feedback.setText(feedback + " " + question);
			Log.d("Debug Family", "Aldrin: Immediate Feedback Completed");
			
			if(itemno < questions.size()-1){
				Log.d("Debug Family", "Aldrin: Next Question(Reruns)");
				itemno++;
				rerun();
			}
			else{
				Log.d("Debug Family", "Aldrin: iFeedback says its finished (Delayed Feedback)");
				feedback = evaluation.getEndofActivityFeedback(evaluation.getScore(), lesson.getLessonNumber());
				tv_feedback.setText(feedback);
				evaluation.updateUserLessonProgress(lesson.getName(), activityLevel.toString(), userLessonProgressOperator, UserID);
				//feedback = NLG.GenerateDelayedFeedback(score, LessonNum);
				//feedback = "feedback placeholder";
				timer.cancel();
				showReportCard(this);
			}		
			return true;
		}else{
			Log.d("Debug Family", "Aldrin: Answer: " + answer);
			Log.d("Debug Family", "Aldrin: Index: " + itemno);
			//NLG Part - Wrong
			//tv_feedback.setText("Oops. That's " + answer + ", Try Again!");
			feedback = evaluation.getImmediateFeedback(questions.get(itemno).getQ_num(), answer, lesson.getLessonNumber());
			Log.d("Debug Family", "Aldrin: Feedback: "+ feedback);
			tv_feedback.setText(feedback + " " + question);
			return false;
		}
	}

	private void setChoices(){
		int answerIndex = new Random().nextInt(choices.length);
		ArrayList<Integer> taken = new ArrayList();
		taken.add(items.indexOf(questions.get(itemno)));
		for(int i = 0; i < choices.length; i++){
			if(i == answerIndex){
				choices[i].setImageResource(questions.get(itemno).getImageID());
				choices[i].setTag(questions.get(itemno).getWord());
			}
			else{
				int rand;
				do{
					rand = new Random().nextInt(items.size());
				//	Log.d("Debug Family","choices.length: " +choices.length + " answerIndex: " + answerIndex + " taken: " + taken.toString() + " i:" + i);
				}while(taken.contains(rand));
					taken.add(rand);
					choices[i].setImageResource(items.get(rand).getImageID());
					choices[i].setTag(items.get(rand).getWord());
			}
			Log.d("Debug Family","Aldrin: setting choices: " + i);
		}
		Log.d("Debug Family","Aldrin: setting choices: done");
	}

	@Override
	public void onClick(View v) {
		int choice = 0;
		switch(v.getId()){
			case R.id.img_choicea: 	
			case R.id.img_choiceb:	
			case R.id.img_choicec:	
			case R.id.img_choiced:	
			case R.id.img_choicee:	
			case R.id.img_choicef:	
			case R.id.img_choiceg:	
			case R.id.img_choiceh:	
			case R.id.img_choicei:	
				for(int i = 0; i < choices.length; i++){
					ImageView img = choices[i];
					img.setColorFilter(new LightingColorFilter(0xffffffff, 0x000000));
				}
					if(checkAnswer(v.getTag().toString())){
						YoYo.with(Techniques.Pulse).playOn(v);
					}else{
						YoYo.with(Techniques.Shake).playOn(v);
						ImageView img = (ImageButton)v;
						img.setColorFilter(new LightingColorFilter(0xffcc0000, 0x000000));
						
						//v.getBackground().setColorFilter(new LightingColorFilter(0xff888888, 0x000000));
					}
					break;
		
			default: 
				//tv_feedback.setText("error in onclick");
		}
		}

	

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		if (event.getAction() == MotionEvent.ACTION_SCROLL) {
			iv_swipe.setVisibility(View.GONE);
		    return true;
		} 
		else {
		    return false;
		}
	}

}

