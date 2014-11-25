package com.ube.salinlahifour.evaluationModule;

import com.ube.salinlahifour.Item;

public class Evaluation {
	private int score = 0;
	public void recordUserAnswer(String LessonName, String Answer, boolean Status){
		
	}
	/*
	public void updateUserLessonProgress(String LessonName, StarType EasyStar, StartType MediumStar, StarType HardStar){
		
	}
	*/
	private Item getMostImprovedItem(){
		Item item = null;
		
		
		
		return item;
	}
	
	public String getImmediateFeedback(Item item, int index, String answer){
		String Feedback = null;
		if(item.getWord().equals(answer))
			Feedback = "Magaling!" + answer + " is " + item.getEnglish();
		else
			Feedback = "Oops. That's " + answer + ", Try Again!";
		return Feedback;
	}


	public String getEndofActivityFeedback(){
		
		String Feedback = "Nakakatuwa! You finished the game! You learned \"Bilog\"! Play again to practice more on \"Parisukat\" Your total score is:"+score;
		return Feedback;
		
	}
	
	public boolean evaluateAnswer(String CorrectAnswer, String UserAnswer){
		if(CorrectAnswer.equals(UserAnswer)){
			score++;
			return true;
		}
		else
			return false;
	}
	/*
	 * public boolean evaluateAnswer(String CorrectAnswer, String UserAnswer, something timeanswered){
			if(CorrectAnswer.equals(UserAnswer)){
				score++;
				getpercentage(timeanswered);
					record to the database;
				return true;
			}
			else
				return false;
		}
	 */
	public int getScore()
	{
		return score;
	}

	
}
