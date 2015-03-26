package com.ube.salinlahifour.narrativeDialog;

import android.content.Intent;

import com.ube.salinlahifour.R;
import com.ube.salinlahifour.SalinlahiFour;

public class HouseNarrativeActivity extends NarrativeDialog{
	private Character maincharacter;
	private Character pichi;
	private Character house;
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
		pichi = new Character(this, tv_dialog, iv_characters[1], R.drawable.pichi);
		pichi.addExpression(Expression.SPECIAL_1, R.drawable.pichi_hands);
		pichi.addExpression(Expression.SPECIAL_2, R.drawable.pichi_handstalk);
		pichi.addExpression(Expression.SHOCKED, R.drawable.pichi_surprisedface);
		
		house = new Character(this, tv_dialog, iv_characters[2], R.drawable.house_tutthouse);
	}

	@Override
	public void initiateScript() {
		script.add(new ScriptLine("Hey " + SalinlahiFour.getLoggedInUser().getName() + "Halika! I want you to meet my cousin.", R.raw.narration_family_1));
		script.add(new ScriptLine("Her name is Pichi-Pichi.",R.raw.narration_family_3));
		script.add(new ScriptLine("Hi " + SalinlahiFour.getLoggedInUser().getName() + "! Pasok tayo, I'll show you something.",R.raw.narration_family_4));
		script.add(new ScriptLine("You saw my house earlier right?",R.raw.narration_family_5));
		script.add(new ScriptLine("Well I tried to make it by gluing things together...",R.raw.narration_family_6));
		script.add(new ScriptLine("... and look!",R.raw.narration_family_7));
		script.add(new ScriptLine("Hala! The pieces fell off!",R.raw.narration_family_8));
		script.add(new ScriptLine(SalinlahiFour.getLoggedInUser().getName() + " Patulong",R.raw.narration_family_6));
		script.add(new ScriptLine("Let’s help Pichi.",R.raw.narration_family_7));
		script.add(new ScriptLine("Here I’ll give you the pieces.",R.raw.narration_family_8));
	}

	@Override
	public void runDialog() {
		switch(dialogIndex){
			case 0:
				setBackground(R.drawable.house_tutt);
				maincharacter.entranceFromLeft();
				maincharacter.say(script.get(0));
				break;
			case 1:
				maincharacter.setExpression(Expression.POINT);
				maincharacter.say(script.get(1));
				break;
			case 2:
				maincharacter.setExpression(Expression.DEFAULT);
				pichi.entranceFromRight();
				pichi.say(script.get(2));
				break;
			case 3:
				pichi.setExpression(Expression.SPECIAL_1);
				pichi.say(script.get(3));
				break;
			case 4:
				pichi.say(script.get(4));
				pichi.setExpression(Expression.SPECIAL_2);
				break;
			case 5:
				pichi.say(script.get(5));
				house.entranceFromHeaven();
				pichi.setExpression(Expression.SHOCKED);
				house.exitToHell();
				break;
			case 6:
				maincharacter.setExpression(Expression.SHOCKED);
				maincharacter.say(script.get(6));
				break;
			case 7:
				pichi.say(script.get(7));
				pichi.exitToRight();
				break;
			case 8:
				maincharacter.setExpression(Expression.DEFAULT);
				maincharacter.say(script.get(8));
				break;
			case 9:
				maincharacter.say(script.get(9));
				break;
			default:
				finish();
		}
	}

}
