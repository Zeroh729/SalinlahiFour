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
	private Context context;
	private Item item;
	private String LessonName;
	private String status = "Incorrect";
	private StarType star;
	private UserLessonProgress userLessonProgressor = new UserLessonProgress();
	
	public Evaluation(Context context, String LessonName, String activityLevel){	
		//this.NLG = NLG;
		this.context = context;
		NLG = new iFeedback();
		NLG.readProperties();
		this.LessonName = LessonName;
		
	}
	public void recordUserAnswer(String LessonName, String correctAnswer, String Status, int UserID){	
		Log.d("Recording: Lesson Name: " + LessonName, "TEST");
		UserRecordOperations userRecordOperator = new UserRecordOperations(context);
		userRecordOperator.open();
		userRecordOperator.addUserRecord(SalinlahiFour.getLoggedInUser().getId(), LessonName, correctAnswer, Status);
		userRecordOperator.close();
	}
	
	public void updateUserLessonProgress(String lessonName, String activityLevel, int UserID){
		String easyStar;
		String mediumStar;
		String hardStar;
		
		if(score <= totscore/2)
			star = StarType.BRONZE;
		else if(score >= totscore/2 && score != totscore)
			star = StarType.SILVER;
		else 
			star = StarType.GOLD;
		
		UserLessonProgressOperations userLessonProgressOperator = new UserLessonProgressOperations(context);
		userLessonProgressOperator.open();

		if(userLessonProgressOperator.getUserLessonProgress(UserID, lessonName) == null){
			userLessonProgressOperator.addUserLessonProgress(UserID, lessonName, star.toString(), null, null);
		}else{
			easyStar = userLessonProgressOperator.getUserLessonProgress(UserID, lessonName).getEasyStar();
			mediumStar = userLessonProgressOperator.getUserLessonProgress(UserID, lessonName).getMediumStar();
			hardStar = userLessonProgressOperator.getUserLessonProgress(UserID, lessonName).getHardStar();
			Log.d("Medal Debug", "Act Level: " + activityLevel);
			if(activityLevel == LevelType.EASY.toString() || activityLevel.equals(LevelType.EASY.toString())){
					userLessonProgressOperator.updateUserLessonProgress(UserID, lessonName, star.toString(), mediumStar, hardStar);
			}
			else if(activityLevel == LevelType.MEDIUM.toString()|| activityLevel.equals(LevelType.MEDIUM.toString())){
				userLessonProgressOperator.updateUserLessonProgress(UserID, lessonName, easyStar, star.toString(), hardStar);
			}
			else{
				easyStar = userLessonProgressOperator.getUserLessonProgress(UserID, lessonName).getEasyStar();
				userLessonProgressOperator.updateUserLessonProgress(UserID, lessonName, easyStar, mediumStar, star.toString());
			}
		}
		userLessonProgressOperator.close();
	} 
	
	private Item getMostImprovedItem(){
		Item item = null;
		return item;
	}
	
	public void setLexiconDir(String filename){
		NLG.setLexiconDirectory("/sdcard/"+filename);
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
	
	public boolean evaluateAnswer(String CorrectAnswer, String UserAnswer, int UserID){
		if(CorrectAnswer.equals(UserAnswer)){
			score++;
			totscore++; //FIX THIS: must be items.getSize()
			status = "Correct";
			Log.d("Evaluation","Updating User Record");
			recordUserAnswer(LessonName, CorrectAnswer, status, UserID);
			Log.d("Evaluation","Updated User Record");
			return true;
		}
		else{
			status = "Incorrect";
			totscore++; //FIX THIS: must be items.getSize()
			Log.d("Evaluation","Updating User Record");
			recordUserAnswer(LessonName, CorrectAnswer, status, UserID);
			Log.d("Evaluation","Updating User Record");
			return false;
		}
	}

	public void setScore(int score){
		this.score = score;
	}
	public void setTotScore(int total){
		this.totscore = total;
	}
	
	public int getScore()
	{
		return score;
	}
	
	public int getTotalScore()
	{
		return totscore;
	}
	
	public StarType getStar()
	{
		return star;
	}

	
}
