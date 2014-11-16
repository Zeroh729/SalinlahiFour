package com.ube.salinlahifour.lessonActivities;
import java.util.ArrayList;
import java.util.Random;

import android.content.ClipData;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

import com.ube.salinlahifour.Item;
import com.ube.salinlahifour.enumTypes.LevelType;
import com.ube.salinlahifour.R;

public class House  extends AbstractLessonActivity implements OnTouchListener, OnDragListener{
	private TextView tv_dialog;
	private TextView tv_feedback;
	private int index;
	//Timer Vars
	 //TextView timerTextView;
	 //CountDownTimer timer;
	private ImageButton[] choices;
	private LinearLayout[] options;
	private String question;
	private String feedback;
	TextView timerTextView;
	 CountDownTimer timer;
	public House(){
		Log.d("Debug Family","Aldrin: Entered Family Class");
		layoutID = R.layout.lessonactivity_house;
	}
	@Override
	protected void initiateViews() {
		// TODO Auto-generated method stub
		Log.d("Debug House","Aldrin: Initiate Views");
		index = 0;
		

		initiateTimer();
		RelativeLayout.LayoutParams dialog_params =  new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		dialog_params.leftMargin=100; //X
		dialog_params.topMargin = 30; //Y
		//TV DIALOG INIT END
		//Text View Feedback
		//Text View Feedback 
		
		tv_feedback = (TextView) findViewById(R.id.tv_feedback);
		tv_feedback.setText(" ");
		RelativeLayout.LayoutParams feedback_params =  new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		feedback_params.leftMargin=100; //X
		feedback_params.topMargin = 60; //Y
		tv_feedback.setLayoutParams(feedback_params);
		//TV FEEDBACK END
		choices = new ImageButton[5];
		Log.d("Debug Family","Aldrin: Initiate Choices");
		choices[0] = (ImageButton) findViewById(R.id.img_choicea);
		choices[1] = (ImageButton) findViewById(R.id.img_choiceb);
		choices[2] = (ImageButton) findViewById(R.id.img_choicec);
		choices[3] = (ImageButton) findViewById(R.id.img_choiced);
		choices[4] = (ImageButton) findViewById(R.id.img_choicee);
		
		//options = new ImageView[12];
		options = new LinearLayout[7];
		options[0] = (LinearLayout) findViewById(R.id.option1);
		options[1] = (LinearLayout) findViewById(R.id.option2);
		options[2] = (LinearLayout) findViewById(R.id.option3);
		options[3] = (LinearLayout) findViewById(R.id.option4);
		options[4] = (LinearLayout) findViewById(R.id.option5);
		options[5] = (LinearLayout) findViewById(R.id.option6);
		options[6] = (LinearLayout) findViewById(R.id.option7);
		
		for(int c = 0; c<choices.length; c++){
			choices[c].setOnTouchListener(this);
			
		}
		for(int c = 0; c<options.length; c++){
			options[c].setOnDragListener(this);
		}
	}
	@Override
	protected void initiateItems() {
		// TODO Auto-generated method stub
		Log.d("Debug House","Aldrin: Initiate Items");
		items = new ArrayList<Item>();
		items.add(new Item("Upuan" , "Chair", "Get the Upuan?", R.drawable.family_lolo, null, LevelType.EASY));
		items.add(new Item("Bola" , "Ball", "Get the Bola", R.drawable.family_lola, null, LevelType.EASY));
		items.add(new Item("Kama" , "Bed", "Get the Kama", R.drawable.family_nanay, null, LevelType.EASY));
		items.add(new Item("Unan" , "Pillow", "Get the Unan", R.drawable.family_tatay, null, LevelType.EASY));
		items.add(new Item("Inidoro" , "Toilet", "Get the Inidoro", R.drawable.family_kuya, null, LevelType.EASY));
		items.add(new Item("Sapatos" , "Shoes", "Get the Sapatos", R.drawable.family_ate, null, LevelType.EASY));
		Log.d("Debug House","Aldrin: Initiate Items...Done");
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
	@Override
	protected void run() {
		// TODO Auto-generated method stub
		Log.d("Debug House","Aldrin: Running");
		//setChoices();
		//tv_dialog.setText(questions.get(index).getLabel());
		question = questions.get(index).getLabel();
		tv_feedback.setText(question);
		timer.start();
		//timer.start();
		Log.d("Debug House","Aldrin: Running Done");
	}
	protected void rerun() {
		Log.d("Debug Family","Aldrin: Running");
		//setChoices();
		//tv_dialog.setText(questions.get(index).getLabel());
		question = questions.get(index).getLabel();
		 
		tv_feedback.setText(question);
		//timer.start();
		Log.d("Debug Family","Aldrin: Running Done");
		/*for(int choiceIndex = 0; choiceIndex < speech_bubble.length; choiceIndex++){
			speech_bubble[choiceIndex].setVisibility(View.GONE);
			feedback[choiceIndex].setVisibility(View.GONE);
		}*/
	}
	private void setChoices(){
		Log.d("Debug Family","Aldrin: Setting Choices");
		int answerIndex = new Random().nextInt(choices.length);
		ArrayList<Integer> taken = new ArrayList<Integer>();
		taken.add(index);
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
		Log.d("Debug Family","Aldrin: Setting Choices...Done");
	}

	@Override
	protected void checkAnswer(String answer) {
		Log.d("Debug Family","Aldrin: Checking Answer");
		if(questions.get(index).getWord().equals(answer)){
			//NLG Part - Correct
			feedback = "Magaling!" + answer + " is " + questions.get(index).getEnglish();
			tv_feedback.setText(feedback + " " + question);
			if(index < questions.size()-1){
				index++;
				rerun();
			}
			else{
				tv_feedback.setText("Nakakatuwa! You finished the game! You learned \"Bilog\"! Play again to practice more on \"Parisukat\"");
				timer.cancel();
			}
		}else{
			//NLG Part - Wrong
			//tv_feedback.setText("Oops. That's " + answer + ", Try Again!");
			feedback = "Oops. That's " + answer + ", Try Again!";
			tv_feedback.setText(feedback + " " + question);
		}
		Log.d("Debug Family","Aldrin: Answer Check");	
	}
	@Override
	public boolean onDrag(View v, DragEvent event) {
		// TODO Auto-generated method stub
		if (event.getAction()==DragEvent.ACTION_DROP) {
			View view = (View) event.getLocalState();
			ViewGroup from = (ViewGroup) view.getParent();
			from.removeView(view);
			LinearLayout to = (LinearLayout) v;
			to.addView(view);
			view.setVisibility(View.VISIBLE);
		}
		return true;
	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
		    //setup drag
			ClipData data = ClipData.newPlainText("", "");
			DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
			//start dragging the item touched
			v.startDrag(data, shadowBuilder, v, 0);
			v.setVisibility(View.INVISIBLE);
		    return true;
		} 
		else {
		    return false;
		}
		
	}
}
/*final class ChoiceTouchListener implements OnTouchListener {
	public boolean onTouch(View view, MotionEvent motionEvent) {
		if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
		    //setup drag
			ClipData data = ClipData.newPlainText("", "");
			DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
			//start dragging the item touched
			view.startDrag(data, shadowBuilder, view, 0);
			view.setVisibility(View.INVISIBLE);
		    return true;
		} 
		else {
		    return false;
		}
	}
}*/

/*class ChoiceDragListener implements OnDragListener {
	@Override
	public boolean onDrag(View v, DragEvent event) {
	    //handle drag events
		switch (event.getAction()) {
	    case DragEvent.ACTION_DRAG_STARTED:
	        //no action necessary
	        break;
	    case DragEvent.ACTION_DRAG_ENTERED:
	        //no action necessary
	        break;
	    case DragEvent.ACTION_DRAG_EXITED:        
	        //no action necessary
	        break;
	    case DragEvent.ACTION_DROP:
	    	Log.d("Debug Family","Aldrin: Dropping");
	        //handle the dragged view being dropped over a drop view
	    	/*View view = (View) event.getLocalState();
	    	view.setVisibility(View.INVISIBLE);
	    	TextView dropTarget = (TextView) v;
	    	TextView dropped = (TextView) view;
	    	dropTarget.setText(dropped.getText());*/
	    	//dropTarget.setTypeface(Typeface.DEFAULT_BOLD);
	/*    	 View view = (View) event.getLocalState();
	    	 Log.d("Debug Family","Aldrin: Dropping1");
	         ViewGroup owner = (ViewGroup) view.getParent();
	         Log.d("Debug Family","Aldrin: Dropping2");
	         owner.removeView(view);
	         Log.d("Debug Family","Aldrin: Dropping3");
	         LinearLayout container = (LinearLayout) v;
	         Log.d("Debug Family","Aldrin: Dropping4");
	         container.addView(view);
	         Log.d("Debug Family","Aldrin: Dropping5");
	         view.setVisibility(View.VISIBLE);
	    	Log.d("Debug Family","Aldrin: Dropped Correctly");
	        break;
	    case DragEvent.ACTION_DRAG_ENDED:
	        //no action necessary
	    	Log.d("Debug Family","Aldrin: Dropping end");
	        break;
	    default:
	        break;
	}
	    return true;
	}
}
*/
