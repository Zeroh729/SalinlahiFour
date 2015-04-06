package com.ube.salinlahifour.narrativeDialog;

import android.content.Intent;

import com.ube.salinlahifour.R;
import com.ube.salinlahifour.SalinlahiFour;
import com.ube.salinlahifour.tutorials.Family;

public class FamilyNarrativeActivity extends NarrativeDialog{
	private Character maincharacter;
	private Character nanay;
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
		nanay = new Character(this, tv_dialog, iv_characters[3], R.drawable.family_nanay);
	}

	@Override
	public void initiateScript() {
		script.add(new ScriptLine("Hi " + SalinlahiFour.getLoggedInUser().getName() + "!", R.raw.narration_family_1));
		if(maincharacterName.equals("Pepai"))
			script.add(new ScriptLine("I am " + maincharacterName, R.raw.narration_family_2f));
		else
			script.add(new ScriptLine("I am " + maincharacterName, R.raw.narration_family_2m));
		script.add(new ScriptLine("Welcome to my house!",R.raw.narration_family_3));
		script.add(new ScriptLine("Ready to start our adventure?",R.raw.narration_family_4));
		script.add(new ScriptLine(maincharacterName + "! <i>Kain na!</i> <font color=#8C8C8C>(Let's eat!)</font> You can't go out with an empty stomach!",R.raw.narration_family_5));
		script.add(new ScriptLine("Oh ok nanay",R.raw.narration_family_6));
		script.add(new ScriptLine("Well, just like nanay said, let's eat!",R.raw.narration_family_7));
		script.add(new ScriptLine("Come on. I'll introduce you to the others.",R.raw.narration_family_8));
	}

	@Override
	public void runDialog() {
		switch(dialogIndex){
			case 0:
				maincharacter.entranceFromLeft();
				maincharacter.say(script.get(0));
				break;
			case 1:
				maincharacter.jump();
				maincharacter.say(script.get(1));
				break;
			case 2:
				maincharacter.setExpression(Expression.POINT);
				maincharacter.say(script.get(2));
				break;
			case 3:
				maincharacter.setExpression(Expression.DEFAULT);
				maincharacter.say(script.get(3));
				break;
			case 4:
				maincharacter.setExpression(Expression.SHOCKED);
				nanay.entranceFromRight();
				nanay.say(script.get(4));
				break;
			case 5:
				maincharacter.setExpression(Expression.DEFAULT);
				nanay.exitToRight();
				maincharacter.say(script.get(5));
				break;
			case 6:
				maincharacter.setExpression(Expression.QUESTION);
				maincharacter.say(script.get(6));
				break;
			case 7:
				maincharacter.setExpression(Expression.POINT);
				maincharacter.say(script.get(7));
				break;
			default:
				finish();
		}
	}

}
