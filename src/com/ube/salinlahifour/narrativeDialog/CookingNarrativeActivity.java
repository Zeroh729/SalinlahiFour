package com.ube.salinlahifour.narrativeDialog;

import android.content.Intent;

import com.ube.salinlahifour.R;
import com.ube.salinlahifour.SalinlahiFour;

public class CookingNarrativeActivity extends NarrativeDialog{
	private Character maincharacter;
	private Character chef;
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
		chef = new Character(this, tv_dialog, iv_characters[3], R.drawable.chef);
		chef.addExpression(Expression.SPECIAL_1, R.drawable.chef_cross);
		chef.addExpression(Expression.SPECIAL_2, R.drawable.chef_talk);
	}

	@Override
	public void initiateScript() {
		script.add(new ScriptLine("Hey where are we?", R.raw.narration_family_1));
		script.add(new ScriptLine("You're in my kitchen!",R.raw.narration_family_3));
		script.add(new ScriptLine("What are you doing here?!",R.raw.narration_family_4));
		script.add(new ScriptLine("Di bale, I have better things to do.",R.raw.narration_family_5));
		script.add(new ScriptLine("Halika! Help me!",R.raw.narration_family_6));
		script.add(new ScriptLine("I don't think we have a choice here " +SalinlahiFour.getLoggedInUser().getName(),R.raw.narration_family_7));
		script.add(new ScriptLine("Tara!",R.raw.narration_family_8));
		script.add(new ScriptLine("Are you ready? Ok makinig! I'm only gonna do this once.",R.raw.narration_family_6));
	}

	@Override
	public void runDialog() {
		switch(dialogIndex){
			case 0:
				setBackground(R.drawable.colors_tutt);
				maincharacter.entranceFromLeft();
				maincharacter.setExpression(Expression.QUESTION);
				maincharacter.say(script.get(0));
				break;
			case 1:
				maincharacter.setExpression(Expression.SHOCKED);
				chef.entranceFromRight();
				chef.setExpression(Expression.SPECIAL_1);
				chef.say(script.get(1));
				break;
			case 2:
				chef.say(script.get(2));
				break;
			case 3:
				chef.setExpression(Expression.SPECIAL_2);
				chef.say(script.get(3));
				break;
			case 4:
				chef.say(script.get(4));
				break;
			case 5:
				maincharacter.setExpression(Expression.SHOCKED);
				maincharacter.say(script.get(5));
				break;
			case 6:
				maincharacter.say(script.get(6));
				break;
			case 7:
				chef.setExpression(Expression.SPECIAL_1);
				chef.say(script.get(7));
				chef.exitToRight();
				break;
			
			default:
				finish();
		}
	}

}