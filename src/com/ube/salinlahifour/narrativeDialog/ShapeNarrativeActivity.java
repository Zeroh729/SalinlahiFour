package com.ube.salinlahifour.narrativeDialog;

import android.content.Intent;

import com.ube.salinlahifour.R;
import com.ube.salinlahifour.SalinlahiFour;

public class ShapeNarrativeActivity extends NarrativeDialog{
	private Character maincharacter;
	private Character crew;
	private Character alien;
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
		crew = new Character(this, tv_dialog, iv_characters[3], R.drawable.crew_handstalk);
	}

	@Override
	public void initiateScript() {
		script.add(new ScriptLine("Wow we finally got away from that chef.", R.raw.narration_shape_1));
		script.add(new ScriptLine("Now we're in a rocket ship! Excited na ako!",R.raw.narration_shape_2));
		script.add(new ScriptLine("What's going on here?",R.raw.narration_shape_3));
		script.add(new ScriptLine("One of our actors for the show is sick! Can you help us with the show?",R.raw.narration_shape_4));
		script.add(new ScriptLine("I guess a little acting won't hurt.",R.raw.narration_shape_5));
		script.add(new ScriptLine("Tignan mo. Someone is on the screen " +SalinlahiFour.getLoggedInUser().getName(),R.raw.narration_shape_6));
		script.add(new ScriptLine("WORLD DOMINATION",R.raw.narration_shape_7));
		script.add(new ScriptLine("Naku! We have to defeat the aliens quickly!",R.raw.narration_shape_8));
		script.add(new ScriptLine("Let's figure out what these buttons do.",R.raw.narration_shape_9));
	}

	@Override
	public void runDialog() {
		switch(dialogIndex){
			case 0:
				setBackground(R.drawable.shapes_tutt);
				maincharacter.entranceFromLeft();
				maincharacter.setExpression(Expression.POINT);
				maincharacter.say(script.get(0));
				break;
			case 1:
				maincharacter.say(script.get(1));
				break;
			case 2:
				maincharacter.setExpression(Expression.QUESTION);
				maincharacter.say(script.get(2));
				break;
			case 3:
				crew.entranceFromRight();
				crew.say(script.get(3));
				break;
			case 4:
				maincharacter.setExpression(Expression.DEFAULT);
				maincharacter.say(script.get(4));
				crew.exitToRight();
				break;
			case 5:
				maincharacter.setExpression(Expression.SHOCKED);
				maincharacter.say(script.get(5));
				break;
			case 6:
				setBackground(R.drawable.shapes_alien);
				crew.say(script.get(6));
				break;
			case 7:
				maincharacter.panic();
				maincharacter.say(script.get(7));
				break;
			case 8:
				maincharacter.say(script.get(7));
				break;
			default:
				finish();
		}
	}

}