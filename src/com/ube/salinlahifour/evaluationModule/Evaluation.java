package com.ube.salinlahifour.evaluationModule;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.ube.salinlahifour.Item;
import com.ube.salinlahifour.SalinlahiFour;
import com.ube.salinlahifour.database.*;
import com.ube.salinlahifour.model.UserDetail;
import com.ube.salinlahifour.enumTypes.*;


public class Evaluation {
	private UserRecordOperations userRecordOperator;
	private UserLessonProgressOperations userLessonProgressOperator;
	private UserDetailOperations userDetailOperator;
	private UserDetail user;
	
	SharedPreferences prefs;
	//private Narration narration = new Narration();
	private int score = 0;
	private String lessonName;
	private String activityLevel;
	private Item item;
	private int lessonNumber;
	private int UserID;
	private Context context;
	private String status = "Incorrect";
	
	public Evaluation(String lessonName, Context context, int lessonNumber, String activityLevel, int UserID){
		this.context = context;		
		this.UserID = UserID;
		this.lessonNumber = lessonNumber;
		this.activityLevel = activityLevel;
		this.lessonName = lessonName;
		userRecordOperator = new UserRecordOperations(context);
		userLessonProgressOperator = new UserLessonProgressOperations(context);
	}
	public void recordUserAnswer(String LessonName, String correctAnswer, String Status){
		
		userRecordOperator.open();
		Log.d("ARCS","Hai");
		userRecordOperator.addUserRecord(UserID, LessonName, correctAnswer, Status);
	}
	
	public void updateUserLessonProgress(String LessonName, StarType EasyStar, StarType MediumStar, StarType HardStar){
		
	}
	
	private Item getMostImprovedItem(){
		Item item = null;
		return item;
	}
	
	public String getImmediateFeedback(Item item, int index, String answer){
		//
		//narration.generateImmediateFeedback(answer,index, lessonNumber);
		//
		
		String Feedback = null;
		if(item.getWord().equals(answer))
			Feedback = "Magaling!" + answer + " is " + item.getEnglish();
		else
			Feedback = "Oops. That's " + answer + ", Try Again!";
		return Feedback; 
	}


	public String getEndofActivityFeedback(int score, String lessonNumber){
		//
		//narration.generateEndofactivityFeedback(score, lessonName);
		//
		
		String Feedback = "Nakakatuwa! You finished the game! You learned \"Bilog\"! Play again to practice more on \"Parisukat\" Your total score is:"+score;
		return Feedback;
		
	}
	
	public boolean evaluateAnswer(String CorrectAnswer, String UserAnswer){
		if(CorrectAnswer.equals(UserAnswer)){
			score++;
			status = "Correct";
			recordUserAnswer(lessonName, CorrectAnswer, status);
			return true;
		}
		else{
			status = "Incorrect";
			recordUserAnswer(lessonName, CorrectAnswer, status);
			return false;
		}
	}

	public int getScore()
	{
		return score;
	}

	
}
