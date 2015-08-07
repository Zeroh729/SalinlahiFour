package com.ube.salinlahifour.evaluationModule;

import java.io.IOException;
import org.jdom.JDOMException;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.ube.salinlahifour.Lesson;
import com.ube.salinlahifour.SalinlahiFour;
import com.ube.salinlahifour.database.*;
import com.ube.salinlahifour.model.UserLessonProgress;
import com.ube.salinlahifour.enumTypes.*;

import iFeedback.iFeedback;

public class Evaluation {

	protected iFeedback NLG;

	SharedPreferences prefs;
	private int score = 0;
	private int totscore = 0;
	private Context context;
	private String LessonName;
	private String status = "Incorrect";
	private StarType star;
	private int allowableMistakes = 4, mistakesRemaining;
	private boolean flag;

	private int lex_size = 0;

	public Evaluation(Context context, String LessonName, String activityLevel) {
		this.context = context;
		NLG = new iFeedback();
		NLG.readProperties();
		this.LessonName = LessonName;
		mistakesRemaining = allowableMistakes;
		flag = true;

	}

	public void recordUserAnswer(String LessonName, String correctAnswer, String Status, int UserID) {
		Log.d("Recording: Lesson Name: " + LessonName, "TEST");
		UserRecordOperations userRecordOperator = new UserRecordOperations(context);
		userRecordOperator.open();
		userRecordOperator.addUserRecord(SalinlahiFour.getLoggedInUser().getId(), LessonName, correctAnswer, Status);
		userRecordOperator.close();
	}

	public void updateUserLessonProgress(String lessonName, String activityLevel, int UserID) {
		String easyStar;
		String mediumStar;
		String hardStar;
		Log.d("Debug End Report", "SCORE stars: " + score);
		if(score == totscore && flag) {
			star = StarType.GOLD;
		} else if(score >= ((totscore) * 3 / 4)) {
			star = StarType.SILVER;
		} else if(score >= totscore / 2) {
			star = StarType.BRONZE;
		} else {
			star = StarType.NONE;
		}
		
		Log.d("STAR:", star.toString() + " " + flag);
		
		UserLessonProgressOperations userLessonProgressOperator = new UserLessonProgressOperations(context);
		userLessonProgressOperator.open();

		if(userLessonProgressOperator.getUserLessonProgress(UserID, lessonName) == null) {
			userLessonProgressOperator.addUserLessonProgress(UserID, lessonName, star.toString(), null, null);
		} else {
			UserLessonProgress lessonProgress = userLessonProgressOperator.getUserLessonProgress(UserID, lessonName);
			easyStar = lessonProgress.getEasyStar();
			mediumStar = lessonProgress.getMediumStar();
			hardStar = lessonProgress.getHardStar();
			
			if(activityLevel.equals(LevelType.EASY.toString())) {
				boolean mustUpdate = true;
				if(StarType.getStar(easyStar).getValue() >= star.getValue()) {
					mustUpdate = false;
				}
				if(mustUpdate)
					userLessonProgressOperator.updateUserLessonProgress(UserID, lessonName, star.toString(), mediumStar, hardStar);
			} else if(activityLevel.equals(LevelType.MEDIUM.toString())) {
				boolean mustUpdate = true;
				if(mediumStar != null) {
					if(StarType.getStar(mediumStar).getValue() >= star.getValue()) {
						mustUpdate = false;
					}
				}
				if(mustUpdate)
					userLessonProgressOperator.updateUserLessonProgress(UserID, lessonName, easyStar, star.toString(), hardStar);
			} else {
				boolean mustUpdate = true;
				if(hardStar != null) {
					if(StarType.getStar(hardStar).getValue() >= star.getValue()) {
						mustUpdate = false;
					}
				}
				if(mustUpdate)
					userLessonProgressOperator.updateUserLessonProgress(UserID, lessonName, easyStar, mediumStar, star.toString());
			}
		}
		userLessonProgressOperator.close();
	}

	public void setLexiconDir(String lexicon_name) {
		NLG.setLexiconDirectory("/sdcard/" + lexicon_name + ".xml");
	}

	public void setPassingGrade(int grade) {
		Log.d("Debug End Report", "Passing Grade: " +grade);
		NLG.SetPassingGrade(grade);
	}

	public void setNLGAllowableMistakes(int grade) {
		NLG.SetAllowableMistakes(grade);
	}

	public void setAllowableMistakes(int mistakes) { // 4 is the default
		this.allowableMistakes = mistakes;
		this.mistakesRemaining = mistakes;
	}

	public int getAllowableMistakes() {
		return allowableMistakes;
	}

	public int getMistakesRemaining() {
		return mistakesRemaining;
	}

	public void setLexiconSize(int size) {
		lex_size = size;
	}

	public int getLexiconSize() {
		return lex_size;
	}

	public int generateLexiconSize(Lesson lesson) {
		int size = 0;
		for(int i = 0; i < lesson.getItems().size(); i++) {
			switch (lesson.getItems().get(i).getDifficulty()) {
				case "HARD":
					size++;
					break;
				case "MEDIUM":
					size++;
					break;
				case "EASY":
					size++;
					break;
			}
		}
		return size;
	}

	public void resetMistakesRemaining() {
		mistakesRemaining = allowableMistakes;
	}

	public boolean isAlive() {
		if(mistakesRemaining > 0) {
			return true;
		} else {
			Log.d("Check Lives", "Game Status: Game Over!");
			return false;
		}
	}

	public String getImmediateFeedback(int index, String answer, int lessonNumber) {
		String Feedback = null;
		try {
			Feedback = NLG.GenerateImmediateFeedback(answer, index, lessonNumber);
		} catch(JDOMException | IOException e) {
			e.printStackTrace();
		}
		return Feedback;
	}

	public String getEndofActivityFeedback(int score, int lessonNumber, StarType star ) {
		String Feedback = null;
		int ans = 0;
		try {
		/*	if(score > 0) {
				ans = (score * lex_size) / totscore;
			}
			Feedback = NLG.GenerateDelayedFeedback(ans, lessonNumber);*/
			switch(star){
			case GOLD: ans = (int) (totscore * 1); break;
			case SILVER:ans = (int) (totscore * 1); break;
			case BRONZE:ans = (int) (totscore * 0.5); break;
			case NONE:ans = 0; break;
			}
			Log.d("Debug End Report", "Send to iFeedback!");
			Log.d("Debug End Report", "User Score: " + ans);
			Log.d("Debug End Report", "Total Score: " + totscore);
			Log.d("Debug End Report", "STAR: " + star);
			Feedback = NLG.GenerateDelayedFeedback(ans, lessonNumber);
			Log.d("Debug End Report", "Sent!");
		} catch(JDOMException | IOException e) {
			e.printStackTrace();
		}
		return Feedback;
	}

	public boolean evaluateAnswer(String CorrectAnswer, String UserAnswer, int UserID) {
		boolean evaluation = CorrectAnswer.equals(UserAnswer);

		if(evaluation) {
			score++;
			status = "Correct";
			//resetMistakesRemaining();
		} else {
			flag = false;
			mistakesRemaining--;
			status = "Incorrect";
		}
		
		Log.d("EVALUATION:", status + " " + flag);
		
		recordUserAnswer(LessonName, CorrectAnswer, status, UserID);
		return evaluation;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setTotScore(int total) {
		this.totscore = total;
	}
	
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public int getScore() {
		return score;
	}
	
	public boolean getFlag() {
		return flag;
	}

	public int getTotalScore() {
		return totscore;
	}

	public StarType getStar() {
		return star;
	}

}
