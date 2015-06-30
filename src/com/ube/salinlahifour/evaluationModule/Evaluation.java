package com.ube.salinlahifour.evaluationModule;

import java.io.IOException;
import org.jdom.JDOMException;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.ube.salinlahifour.Item;
import com.ube.salinlahifour.Lesson;
import com.ube.salinlahifour.SalinlahiFour;
import com.ube.salinlahifour.database.*;
import com.ube.salinlahifour.model.UserLessonProgress;
import com.ube.salinlahifour.enumTypes.*;

import iFeedback.iFeedback;

public class Evaluation {

	protected iFeedback NLG;

	SharedPreferences prefs;
	// private Narration narration = new Narration();
	private int score = 0;
	private int totscore = 0;
	private Context context;
	private Item item;
	private String LessonName;
	private String status = "Incorrect";
	private StarType star;
	private UserLessonProgress userLessonProgressor = new UserLessonProgress();
	private boolean commitMistake = false;
	private int allowableMistakes = 4, mistakesRemaining;

	private int lex_size = 0;

	public Evaluation(Context context, String LessonName, String activityLevel) {
		// this.NLG = NLG;
		this.context = context;
		NLG = new iFeedback();
		NLG.readProperties();
		this.LessonName = LessonName;
		mistakesRemaining = allowableMistakes;
		// totscore = totalScore;

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

		if (score <= totscore / 2)
			star = StarType.BRONZE;
		else if (score >= totscore / 2 && score != totscore)
			star = StarType.SILVER;
		else
			star = StarType.GOLD;

		UserLessonProgressOperations userLessonProgressOperator = new UserLessonProgressOperations(context);
		userLessonProgressOperator.open();

		if (userLessonProgressOperator.getUserLessonProgress(UserID, lessonName) == null) {
			userLessonProgressOperator.addUserLessonProgress(UserID, lessonName, star.toString(), null, null);
		} else {
			easyStar = userLessonProgressOperator.getUserLessonProgress(UserID, lessonName).getEasyStar();
			mediumStar = userLessonProgressOperator.getUserLessonProgress(UserID, lessonName).getMediumStar();
			hardStar = userLessonProgressOperator.getUserLessonProgress(UserID, lessonName).getHardStar();
			Log.d("Medal Debug", "Act Level: " + activityLevel);
			if (activityLevel == LevelType.EASY.toString() || activityLevel.equals(LevelType.EASY.toString())) {
				boolean mustUpdate = true;
				if (easyStar.equals(StarType.SILVER.toString()) && star.toString().equals(StarType.BRONZE.toString())) {
					mustUpdate = false;
				}
				if (easyStar.equals(StarType.GOLD.toString())) {
					mustUpdate = false;
				}
				if (mustUpdate)
					userLessonProgressOperator.updateUserLessonProgress(UserID, lessonName, star.toString(), mediumStar, hardStar);
			} else if (activityLevel == LevelType.MEDIUM.toString() || activityLevel.equals(LevelType.MEDIUM.toString())) {
				boolean mustUpdate = true;
				if (mediumStar != null) {
					if (mediumStar.equals(StarType.SILVER.toString()) && star.toString().equals(StarType.BRONZE.toString())) {
						mustUpdate = false;
					}
					if (mediumStar.equals(StarType.GOLD.toString())) {
						mustUpdate = false;
					}
				}
				if (mustUpdate)
					userLessonProgressOperator.updateUserLessonProgress(UserID, lessonName, easyStar, star.toString(), hardStar);
			} else {
				boolean mustUpdate = true;
				if (hardStar != null) {
					if (hardStar.equals(StarType.SILVER.toString()) && star.toString().equals(StarType.BRONZE.toString())) {
						mustUpdate = false;
					}
					if (hardStar.equals(StarType.GOLD.toString())) {
						mustUpdate = false;
					}
				}
				if (mustUpdate)
					userLessonProgressOperator.updateUserLessonProgress(UserID, lessonName, easyStar, mediumStar, star.toString());
			}
		}
		userLessonProgressOperator.close();
	}

	private Item getMostImprovedItem() {
		Item item = null;
		return item;
	}

	public void setLexiconDir(String lexicon_name) {
		Log.d("Feedback", "Setting Lexicon Directory to: " + lexicon_name);
		NLG.setLexiconDirectory("/sdcard/" + lexicon_name + ".xml");
		Log.d("Feedback", "Done Setting!: " + lexicon_name);
	}

	public void setPassingGrade(int grade) {
		Log.d("Feedback", "Setting Passing grade to: " + grade);
		NLG.SetPassingGrade(grade);
		Log.d("Feedback", "Setting Passing grade Done!: " + grade);
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
		for (int i = 0; i < lesson.getItems().size(); i++) {
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
		Log.d("Check Answer", "Mistake Counter Reset to " + mistakesRemaining);
	}

	public boolean isAlive() {
		if (mistakesRemaining > 0) {
			Log.d("Check Lives", "Game Status: Still Alive!");
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
		} catch (JDOMException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Feedback;
	}

	public String getEndofActivityFeedback(int score, int lessonNumber) {
		String Feedback = null;
		int ans = 0, reminder = 0;
		try {
			if (score > 0) {
				// reminder = lex_size % score;
				// ans = (lex_size / score)+reminder;
				Log.d("Scoring Debug", "User Score: " + score);
				Log.d("Scoring Debug", "Lexicon Size: " + lex_size);
				Log.d("Scoring Debug", "Total Score: " + totscore);
				ans = (score * lex_size) / totscore;
				Log.d("Scoring Debug", "Real Score:" + ans);
			}
			Feedback = NLG.GenerateDelayedFeedback(ans, lessonNumber);
		} catch (JDOMException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Feedback;
	}

	public boolean evaluateAnswer(String CorrectAnswer, String UserAnswer, int UserID) {
		if (commitMistake == false) {
			if (CorrectAnswer.equals(UserAnswer)) {
				score++;
				// totscore++; //FIX THIS: must be items.getSize()
				status = "Correct";
				resetMistakesRemaining();
				Log.d("Check Answer", status + ": " + score);
				Log.d("Check Answer", "CommitMistake? " + commitMistake);
				Log.d("Check Answer", "Mistakes Remaining: " + mistakesRemaining);
				Log.d("Evaluation", "Updating User Record");
				recordUserAnswer(LessonName, CorrectAnswer, status, UserID);
				Log.d("Evaluation", "Updated User Record");
				return true;
			} else {
				commitMistake = true;
				mistakesRemaining--;
				status = "Incorrect";
				Log.d("Check Answer", status + ": " + score);
				Log.d("Check Answer", "CommitMistake? " + commitMistake);
				Log.d("Check Answer", "Mistakes Remaining: " + mistakesRemaining);
				// totscore++; //FIX THIS: must be items.getSize()
				Log.d("Evaluation", "Updating User Record");
				recordUserAnswer(LessonName, CorrectAnswer, status, UserID);
				Log.d("Evaluation", "Updating User Record");
				return false;
			}
		} else {
			if (CorrectAnswer.equals(UserAnswer)) {
				commitMistake = false;

				// totscore++; //FIX THIS: must be items.getSize()
				status = "Correct";
				resetMistakesRemaining();
				Log.d("Check Answer", status + ": " + score);
				Log.d("Check Answer", "CommitMistake? " + commitMistake);
				Log.d("Check Answer", "Mistakes Remaining: " + mistakesRemaining);
				Log.d("Evaluation", "Updating User Record");
				recordUserAnswer(LessonName, CorrectAnswer, status, UserID);
				Log.d("Evaluation", "Updated User Record");
				return true;
			} else {
				commitMistake = true;
				mistakesRemaining--;
				status = "Incorrect";
				Log.d("Check Answer", status + ": " + score);
				Log.d("Check Answer", "CommitMistake? " + commitMistake);
				Log.d("Check Answer", "Mistakes Remaining: " + mistakesRemaining);
				// totscore++; //FIX THIS: must be items.getSize()
				Log.d("Evaluation", "Updating User Record");
				recordUserAnswer(LessonName, CorrectAnswer, status, UserID);
				Log.d("Evaluation", "Updating User Record");
				return false;
			}
		}
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setTotScore(int total) {
		this.totscore = total;
	}

	public int getScore() {
		return score;
	}

	public int getTotalScore() {
		return totscore;
	}

	public StarType getStar() {
		return star;
	}

}
