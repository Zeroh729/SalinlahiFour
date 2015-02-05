package com.ube.salinlahifour.lessonActivities;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.jdom.JDOMException;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

import com.ube.salinlahifour.Item;
import com.ube.salinlahifour.MapActivity;
import com.ube.salinlahifour.enumTypes.LevelType;
import com.ube.salinlahifour.R;

public class HouseOLD extends AbstractLessonActivity implements OnClickListener{

	private TextView tv_dialog;
	private TextView tv_feedback;
	private int index;
	//Timer Vars
	 //TextView timerTextView;
	 //CountDownTimer timer;
	int lessonNumber = 1;
	private ImageButton[] choices;
	private String question;
	private String feedback;
	//TextView timerTextView;
	// CountDownTimer timer;
	public HouseOLD(){
		Log.d("Debug Family","Aldrin: Entered Family Class");
		layoutID = R.layout.lessonactivity_house;
	}
	@Override
	protected void initiateViews() {
		// TODO Auto-generated method stub
		Log.d("Debug House","Aldrin: Initiate Views");
		index = 0;
		

		tv_feedback = (TextView) findViewById(R.id.tv_feedback);
		tv_feedback.setText(" ");
	
		//TV FEEDBACK END
		choices = new ImageButton[4];
		Log.d("Debug Family","Aldrin: Initiate Choices");
		choices[0] = (ImageButton) findViewById(R.id.house_choice1);
		choices[1] = (ImageButton) findViewById(R.id.house_choice2);
		choices[2] = (ImageButton) findViewById(R.id.house_choice3);
		choices[3] = (ImageButton) findViewById(R.id.house_choice4);
		
	
		
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		Log.d("Debug House","Aldrin: Running");
		setChoices();
		question = questions.get(index).getLabel();
		tv_feedback.setText(question);
		
		//timer.start();
		Log.d("Debug House","Aldrin: Running Done");
	}
	protected void rerun() {
		Log.d("Debug House","Aldrin: Running");
		question = questions.get(index).getLabel();
		 
		tv_feedback.setText(feedback + " " + question);
		//timer.start();
		Log.d("Debug House","Aldrin: Running Done");
	}
	private void setChoices(){
		int answerIndex = new Random().nextInt(choices.length);
		ArrayList<Integer> taken = new ArrayList();
		taken.add(items.indexOf(questions.get(index)));
		for(int i = 0; i < choices.length; i++){
			if(i == answerIndex){
				choices[i].setImageResource(questions.get(index).getImageID());
				choices[i].setTag(questions.get(index).getWord());
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
	protected void checkAnswer(String answer) {
		Log.d("Debug House","Aldrin: Checking Answer");
		
			//NLG Part - Correct
			//feedback = "Magaling!" + answer + " is " + questions.get(index).getEnglish();
			try {
				if(questions.get(index).getWord().equals(answer)){
				feedback = NLG.GenerateImmediateFeedback(answer, index+1, 2);
				tv_feedback.setText(feedback + " " + question);
			
			
			if(index < questions.size()-1){
				index++;
				rerun();
			}
			else{
				tv_feedback.setText("Nakakatuwa! You finished the game! You learned \"Bilog\"! Play again to practice more on \"Parisukat\"");
				//timer.cancel();
				//feedback = NLG.GenerateDelayedFeedback(score, LessonNum);
				feedback = "feedback placeholder";
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
			Log.d("Debug Family", "Aldrin: Index: " + index);
			//NLG Part - Wrong
			//tv_feedback.setText("Oops. That's " + answer + ", Try Again!");
			feedback = NLG.GenerateImmediateFeedback(answer, index+1, 2);
			Log.d("Debug Family", "Aldrin: Feedback: "+ feedback);
			tv_feedback.setText(feedback + " " + question);
		}
			} catch (JDOMException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		Log.d("Debug Family","Aldrin: Answer Check");	
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Log.d("OnClickListened", "Aldrin: Click!");
		//tv_feedback.setText("in onclick");
		Log.d("OnClickListened", "Aldrin: "+ ((ImageButton)findViewById(v.getId())).getTag() + " " + v.getId() );
		
		switch(v.getId()){
		
		case R.id.house_choice1:	
		case R.id.house_choice2:	
		case R.id.house_choice3:	
		case R.id.house_choice4:	
				checkAnswer(((ImageButton)findViewById(v.getId())).getTag() + "");
							break;
	
		default: 
			tv_feedback.setText("error in onclick");
	}
	}
	
}
