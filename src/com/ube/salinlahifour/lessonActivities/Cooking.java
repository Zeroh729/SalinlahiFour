package com.ube.salinlahifour.lessonActivities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.jdom.JDOMException;

import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

import com.ube.salinlahifour.R;
import com.ube.salinlahifour.database.UserLessonProgressOperations;
import com.ube.salinlahifour.database.UserRecordOperations;
import com.ube.salinlahifour.enumTypes.LevelType;


import com.ube.salinlahifour.evaluationModule.Evaluation;


import com.ube.salinlahifour.evaluationModule.Evaluation;

public class Cooking extends AbstractLessonActivity implements OnClickListener{
	private TextView tv_dialog;
	private TextView tv_feedback;
	private Evaluation evaluation = new Evaluation(NLG, activityName, activityLevel.toString());
	private ImageButton[] choices;
	private int itemno;
	private UserRecordOperations userRecordOperator = new UserRecordOperations(this);
	private UserLessonProgressOperations userLessonProgressOperator = new UserLessonProgressOperations(this);
	
	public Cooking(){
		layoutID = R.layout.lessonactivity_cooking;
	}

	@Override
	protected void run() {
		setChoices();
		tv_dialog.setText(questions.get(itemno).getLabel());
	}

	@Override
	protected void checkAnswer(String answer) {
		Log.d("Debug Family","Aldrin: Checking Answer");
		String feedback;
		userRecordOperator.open();
		userLessonProgressOperator.open();
		try {
			if(evaluation.evaluateAnswer(questions.get(1).getWord(), answer, userRecordOperator, UserID)){
				//NLG Part - Correct
				Log.d("Debug Family", "Aldrin: Answer: " + answer);
				Log.d("Debug Family", "Aldrin: Index: " + 1);
				feedback = evaluation.getImmediateFeedback(1, answer, 3);
				Log.d("Debug Family", "Aldrin: Feedback: "+ feedback);
				tv_feedback.setText(feedback + " ");
				Log.d("Debug Family", "Aldrin: Immediate Feedback Completed");
				if(1 < questions.size()-1){
					Log.d("Debug Family", "Aldrin: Next Question(Reruns)");
					run();
				}
				else{
					Log.d("Debug Family", "Aldrin: iFeedback says its finished (Delayed Feedback)");
					feedback = evaluation.getEndofActivityFeedback(evaluation.getScore(), 3);
					tv_feedback.setText(feedback);
					evaluation.updateUserLessonProgress("Cooking", activityLevel.toString(), userLessonProgressOperator, UserID);
					//feedback = NLG.GenerateDelayedFeedback(score, LessonNum);
					feedback = "feedback placeholder";
				//	timer.cancel();
					TextView tv_feedback_end;
					TextView tv_score_end;
					Log.d("Debug Family", "Aldrin: Start Popup");

					 LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);  
					 Log.d("Debug Family", "Aldrin: Initializing");
					 	View popupView = layoutInflater.inflate(R.layout.activity_end_popup, null);  
					 	 Log.d("Debug Family", "Aldrin: Popupview Done...");
					    
					             final PopupWindow popupWindow = new PopupWindow(popupView, LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);  
					             Log.d("Debug Family", "Aldrin: Popupwindow Done...");
					             //popupWindow.dismiss();
					             popupWindow.setOutsideTouchable(true);
					             popupWindow.setBackgroundDrawable(new BitmapDrawable());
					             Log.d("Debug Family", "Aldrin: Popupwindow settings Done...");
					             tv_feedback_end = (TextView) findViewById(R.id.tv_feedback_end);
					             Log.d("Debug Family", "Aldrin: feedback Done...");
								    tv_score_end = (TextView) findViewById(R.id.tv_score);
								    Log.d("Debug Family", "Aldrin: score Done...");
								    //tv_feedback_end.setText(feedback);
								    Log.d("Debug Family", "Aldrin: feedback loaded...");
								    //tv_score_end.setText(89);
								    Log.d("Debug Family", "Aldrin: feedback score...");
								    Log.d("Debug Family", "Aldrin: Initialized");
					             ImageButton close_btn = (ImageButton)popupView.findViewById(R.id.ib_no);
					             close_btn.setOnClickListener(new ImageButton.OnClickListener(){
					        	     @Override
					        	     public void onClick(View v) {
					        	      // TODO Auto-generated method stub
					        	    	 end_report(1);
					        	     }});
					             ImageButton retry_btn = (ImageButton)popupView.findViewById(R.id.ib_retry);
					             retry_btn.setOnClickListener(new ImageButton.OnClickListener(){

					        	     @Override
					        	     public void onClick(View v) {
					        	      // TODO Auto-generated method stub
					        	    	 end_report(2);
					        	     }});
					             //popupWindow.showAsDropDown(popupView, 50, -30);
					             Log.d("Debug Family", "Aldrin: Start ShowAtLocation");
					             
					             popupWindow.showAtLocation(this.findViewById(R.id.relative_view), Gravity.CENTER, 0, 0);
					             Log.d("Debug Family", "Aldrin: End ShowAtLocation");
				}		
			}else{
				Log.d("Debug Family", "Aldrin: Answer: " + answer);
				Log.d("Debug Family", "Aldrin: Index: " + 1);
				//NLG Part - Wrong
				//tv_feedback.setText("Oops. That's " + answer + ", Try Again!");
				feedback = NLG.GenerateImmediateFeedback(answer, 1+1, 1);
				Log.d("Debug Family", "Aldrin: Feedback: "+ feedback);
				tv_feedback.setText(feedback + " " );
				run();
			}
		
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d("Debug Family", "Aldrin: Something went wrong with JDOM(CATCH)");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.d("Debug Family", "Aldrin: Something went wrong in IO (CATCH)");
			e.printStackTrace();

		}
		
		Log.d("Debug Family","Aldrin: Answer Check");
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
				}while(taken.contains(rand));
					taken.add(rand);
					choices[i].setImageResource(items.get(rand).getImageID());
					choices[i].setTag(items.get(rand).getWord());
			}
		}

	}

	@Override
	protected void initiateViews() {
		itemno = 0;
		tv_dialog = (TextView)findViewById(R.id.tv_dialog);
		tv_feedback = (TextView) findViewById(R.id.tv_feedback);
		choices = new ImageButton[3];
		choices[0] = (ImageButton) findViewById(R.id.btn_choicea);
		choices[1] = (ImageButton) findViewById(R.id.btn_choiceb);
		choices[2] = (ImageButton) findViewById(R.id.btn_choicec);
	}

	@Override
	public void onClick(View view) {
		Log.d("OnClickListened", "Click!");
		tv_feedback.setText("in onclick");
		switch(view.getId()){
		case R.id.btn_choicea:
		case R.id.btn_choiceb:
		case R.id.btn_choicec:
			checkAnswer(((ImageButton)findViewById(view.getId())).getTag() + "");
			break;
			default: 
				tv_feedback.setText("error in onclick");
		}
		
	}

}
