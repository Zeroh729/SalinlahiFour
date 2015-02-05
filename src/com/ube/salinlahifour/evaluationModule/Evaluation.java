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
	
	public Evaluation(String LessonName, String activityLevel){	
		//this.NLG = NLG;
		NLG = new iFeedback();
		NLG.readProperties();
		this.LessonName = LessonName;
		
	}
	public void recordUserAnswer(String LessonName, String correctAnswer, String Status, UserRecordOperations userRecordOperator, int UserID){	
		
		userRecordOperator.addUserRecord(UserID, LessonName, correctAnswer, Status);
	}
	
	public void updateUserLessonProgress(String LessonName, String activityLevel, UserLessonProgressOperations userLessonProgressOperator, int UserID){
		String star;
		if(score <= totscore/2)
			star = StarType.BRONZE.toString();
		else if(score >= totscore/2 && score != totscore)
			star = StarType.SILVER.toString();
		else 
			star = StarType.GOLD.toString();
		
		if(activityLevel == LevelType.EASY.toString())
				userLessonProgressOperator.addUserLessonProgress(UserID, LessonName, star, null, null);
			else if(activityLevel == LevelType.MEDIUM.toString())
				userLessonProgressOperator.addUserLessonProgress(UserID, LessonName, null, star, null);
			else
				userLessonProgressOperator.addUserLessonProgress(UserID, LessonName, null, null, star);
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
