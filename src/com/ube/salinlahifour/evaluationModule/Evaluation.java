package com.ube.salinlahifour.evaluationModule;

import java.io.IOException;

import org.jdom.JDOMException;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.ube.salinlahifour.Item;
import com.ube.salinlahifour.SalinlahiFour;
import com.ube.salinlahifour.database.*;
import com.ube.salinlahifour.model.UserDetail;
import com.ube.salinlahifour.model.UserLessonProgress;
import com.ube.salinlahifour.enumTypes.*;

import iFeedback.iFeedback;



public class Evaluation {
	
	protected iFeedback NLG;
	
	
	SharedPreferences prefs;
	//private Narration narration = new Narration();
	private int score = 0;
	private int totscore = 0;
	private Item item;
	private String LessonName;
	private String status = "Incorrect";
	private UserLessonProgress userLessonProgressor = new UserLessonProgress();
	public Evaluation(iFeedback NLG, String LessonName, String activityLevel){	
		this.NLG = NLG;
		this.LessonName = LessonName;
		
	}
	public void recordUserAnswer(String LessonName, String correctAnswer, String Status, UserRecordOperations userRecordOperator, int UserID){	
		
		userRecordOperator.addUserRecord(UserID, LessonName, correctAnswer, Status);
	}
	
	public void updateUserLessonProgress(String LessonName, String activityLevel, UserLessonProgressOperations userLessonProgressOperator, int UserID){
		String star;
		if(score <= totscore/2)
			star = "Bronze";
		else if(score >= totscore/2 && score != totscore)
			star = "Silver";
		else 
			star = "Gold";
		
		
		userLessonProgressor = userLessonProgressOperator.getUserLessonProgress(UserID, LessonName); 
		if(activityLevel == LevelType.EASY.toString())
			userLessonProgressor.setEasyStar(star);
		else if(activityLevel == LevelType.MEDIUM.toString())
			userLessonProgressor.setMediumStar(star);
		else
			userLessonProgressor.setHardStar(star);
		
		userLessonProgressOperator.updateUserLessonProgress(UserID, LessonName, userLessonProgressor.getEasyStar(), userLessonProgressor.getMediumStar(), userLessonProgressor.getHardStar());
	}
	
	private Item getMostImprovedItem(){
		Item item = null;
		return item;
	}
	
	public String getImmediateFeedback(int index, String answer, int lessonNumber){
		String Feedback = null;
		try {
			Feedback = NLG.GenerateImmediateFeedback(answer,index, lessonNumber);
		} catch (JDOMException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Feedback; 
	}


	public String getEndofActivityFeedback(int score, int lessonNumber){
		String Feedback = null;
		try {
			Feedback = NLG.GenerateDelayedFeedback(score, lessonNumber);
		} catch (JDOMException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Feedback;
		
	}
	
	public boolean evaluateAnswer(String CorrectAnswer, String UserAnswer, UserRecordOperations userRecordOperator, int UserID){
		if(CorrectAnswer.equals(UserAnswer)){
			score++;
			totscore++;
			status = "Correct";
			Log.d("Evaluation","Updating User Record");
			recordUserAnswer(LessonName, CorrectAnswer, status, userRecordOperator, UserID);
			Log.d("Evaluation","Updated User Record");
			return true;
		}
		else{
			status = "Incorrect";
			totscore++;
			Log.d("Evaluation","Updating User Record");
			recordUserAnswer(LessonName, CorrectAnswer, status, userRecordOperator, UserID);
			Log.d("Evaluation","Updating User Record");
			return false;
		}
	}

	public int getScore()
	{
		return score;
	}

	
}
