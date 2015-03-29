package com.ube.salinlahifour.narrativeDialog;

import android.content.Intent;

import com.ube.salinlahifour.R;
import com.ube.salinlahifour.SalinlahiFour;

public class AnimalsNarrativeActivity extends NarrativeDialog{
	private Character maincharacter;
	private Character guard;
	private String maincharacterName;
	
	@Override
	public void initiateCharacters() {
		
		if(SalinlahiFour.getLoggedInUser().getGender().equals("female")){
			maincharacter = new Character(this, tv_dialog, iv_characters[0], R.drawable.pepay_handsonwaist);
			maincharacter.addExpression(Expression.POINT, R.drawable.pepay_wave);
			maincharacter.addExpression(Expression.QUESTION, R.drawable.pepay);
			maincharacter.addExpression(Expression.SHOCKED, R.drawable.pepai_surprisedface);
			maincharacterName = "Pepai";
		}
		else{
			maincharacter = new Character(this, tv_dialog, iv_characters[0], R.drawable.popoy_handsonwaist);
			maincharacter.addExpression(Expression.POINT, R.drawable.popoy_wave);
			maincharacter.addExpression(Expression.QUESTION, R.drawable.popoy);
			maincharacter.addExpression(Expression.SHOCKED, R.drawable.popoy_surprisedface);
			maincharacterName = "Popoi";
		}
		guard = new Character(this, tv_dialog, iv_characters[3], R.drawable.police_waist);
		guard.addExpression(Expression.SPECIAL_1, R.drawable.police_waisttalk);
		guard.addExpression(Expression.WAVE, R.drawable.police_wave);
		guard.addExpression(Expression.SPECIAL_2, R.drawable.police_wavetalk);
		
	}

	@Override
	public void initiateScript() {
		script.add(new ScriptLine("This is quite the adventure right?", R.raw.narration_animals_1));
		script.add(new ScriptLine("Let's just rest for a while.",R.raw.narration_animals_2));
		script.add(new ScriptLine("Alam ko na! Let's take a break inside the zoo!",R.raw.narration_animals_3));
		script.add(new ScriptLine("Hi! Can we go inside?",R.raw.narration_animals_4));
		script.add(new ScriptLine("Sure! It's 2000 pesos each.",R.raw.narration_animals_5));
		script.add(new ScriptLine("What? We don't have that much",R.raw.narration_animals_6));
		script.add(new ScriptLine(SalinlahiFour.getLoggedInUser().getName() + " , what do we do now?",R.raw.narration_animals_7));
		script.add(new ScriptLine("If you can answer my quiz, I will let you in for free.",R.raw.narration_animals_8));
		script.add(new ScriptLine("Don't worry, madali lang.",R.raw.narration_animals_9));
		script.add(new ScriptLine("Talaga?",R.raw.narration_animals_10));
		script.add(new ScriptLine("But, can we review first?",R.raw.narration_animals_11));
		script.add(new ScriptLine("Ok!",R.raw.narration_animals_12));
	}

	@Override
	public void runDialog() {
		switch(dialogIndex){
			case 0:
				setBackground(R.drawable.animals_tutt);
				maincharacter.entranceFromLeft();
				maincharacter.setExpression(Expression.DEFAULT);
				maincharacter.say(script.get(0));
				break;
			case 1:
				maincharacter.say(script.get(1));
				break;
			case 2:
				maincharacter.setExpression(Expression.POINT);
				maincharacter.say(script.get(2));
				break;
			case 3:
				maincharacter.setExpression(Expression.QUESTION);
				maincharacter.say(script.get(3));
				break;
			case 4:
				guard.entranceFromRight();
				guard.say(script.get(4));
				break;
			case 5:
				maincharacter.setExpression(Expression.SHOCKED);
				maincharacter.say(script.get(5));
				break;
			case 6:
				maincharacter.say(script.get(6));
				break;
			case 7:
				guard.setExpression(Expression.SPECIAL_1);
				guard.say(script.get(7));
				break;
			case 8:
				guard.setExpression(Expression.WAVE);
				guard.say(script.get(8));
				break;
			case 9:
				maincharacter.setExpression(Expression.POINT);
				maincharacter.say(script.get(9));
				break;
			
			case 10:
				maincharacter.setExpression(Expression.QUESTION);
				maincharacter.say(script.get(10));
				break;
			case 11:
				guard.setExpression(Expression.SPECIAL_2);
				guard.say(script.get(11));
				break;
			
			default:
				finish();
		}
	}

}