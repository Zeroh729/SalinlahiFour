package com.ube.salinlahifour.narrativeDialog;

import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.ube.salinlahifour.R;
import com.ube.salinlahifour.SalinlahiFour;
import com.ube.salinlahifour.narrativeStory.ScriptLine;

public class Character {
	private Context context;
	private TextView tv_dialog;
	private ImageView talkBubble;
	private ImageView view;
	private HashMap<String, Integer> states;
	
	
	private String exp = "";
	private String movement = "";
	private ScriptLine say;
	private String name = "";
	
	public Character(Context context){
		this.context = context;
		
		states = new HashMap<>();
		exp="default";
//		
//		states.put(Expression.DEFAULT, resID);
//		
//		setExpression(Expression.DEFAULT.toString());
	}
	
	public void setViews(TextView tv_dialog, ImageView view, ImageView talkbubble){
		this.tv_dialog = tv_dialog;
		this.view = view;
		this.talkBubble = talkbubble;
		
		view.setImageResource(states.get("default"));
		Log.d("TEST0", "States size: " + states.size());
	}
	
	public void addExpression(String ex, String resIdName){
		int resID = SalinlahiFour.getContext().getResources().getIdentifier(resIdName, "drawable", SalinlahiFour.getContext().getPackageName());
		
		if(resID != -1){
			states.put(ex, resID);		
		}else{
			SalinlahiFour.errorPopup(context, "No image file found",  "Add " + resIdName + "file to drawable resource folder");
		}
	}
	
//	public void setExpression(String ex){
//		if(state.get(ex) == null)
//			view.setImageResource(state.get(Expression.DEFAULT));
//		else
//			view.setImageResource(state.get(ex));
//	}

	public void setContext(Context context){
		this.context = context;
	}
	
	public void entranceFromLeft(){
		view.setVisibility(View.VISIBLE);
		YoYo.with(Techniques.BounceInLeft).playOn(view);
	}
	
	public void entranceFromRight(){
		view.setVisibility(View.VISIBLE);
		YoYo.with(Techniques.BounceInRight).playOn(view);
	}

	public void entranceFromHeaven(){
		view.setVisibility(View.VISIBLE);
		YoYo.with(Techniques.BounceInDown).playOn(view);
	}
	
	public void entranceFromHell(){
		view.setVisibility(View.VISIBLE);
		YoYo.with(Techniques.BounceInUp).playOn(view);
	}

	public void exitToLeft(){
		YoYo.with(Techniques.SlideOutLeft).playOn(view);
	}
	
	public void exitToRight(){
		YoYo.with(Techniques.SlideOutRight).playOn(view);
	}

	public void exitToHeaven(){
		YoYo.with(Techniques.SlideOutUp).playOn(view);
	}
	
	public void exitToHell(){
		YoYo.with(Techniques.SlideOutDown).playOn(view);
	}
	
	public void shake(){
		YoYo.with(Techniques.Shake).playOn(view);
	}

	public void rumble(){
		YoYo.with(Techniques.Wobble).playOn(view);
	}
	
	public void jump(){
		YoYo.with(Techniques.Bounce).playOn(view);
	}
	
	public void emphasize(){
		YoYo.with(Techniques.Pulse).playOn(view);
	}
	
	public void surprise(){
		YoYo.with(Techniques.Tada).playOn(view);
	}
	
	public void panic(){
		YoYo.with(Techniques.Swing).playOn(view);
	}
	
	public void say(int line, String phrase){
		MediaPlayer voice = MediaPlayer.create(context, line);
		voice.start();
		tv_dialog.setText(Html.fromHtml(phrase));

		RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		relativeParams.addRule(RelativeLayout.ABOVE, view.getId());
		relativeParams.addRule(RelativeLayout.ALIGN_LEFT, view.getId());
		relativeParams.addRule(RelativeLayout.ALIGN_RIGHT, view.getId());

		tv_dialog.setVisibility(View.VISIBLE);
		talkBubble.setVisibility(View.VISIBLE);
		talkBubble.setLayoutParams(relativeParams);
		
	}
	
	public String getPhrase(){
		return say.getLine();
	}
	
	public int getVoiceResId(){
		int voiceResID = 0;
		voiceResID = SalinlahiFour.getContext().getResources().getIdentifier(say.getSoundFile(), "raw", SalinlahiFour.getContext().getPackageName());
		
		if(isMainCharacter()){
			Log.d("TEST0", "ScriptLine: Character speaking IS a main character: " + say.getSoundFile());
			int temp = 0;
			if(SalinlahiFour.getLoggedInUser().getGender().equals("female")){
				temp = SalinlahiFour.getContext().getResources().getIdentifier(say.getSoundFile()+"f", "raw", SalinlahiFour.getContext().getPackageName());
			}else{
				temp = SalinlahiFour.getContext().getResources().getIdentifier(say.getSoundFile()+"m", "raw", SalinlahiFour.getContext().getPackageName());
			}
			if(temp != 0){
				voiceResID = temp;
			}
		}
		Log.d("TEST0", "ScriptLine: voiceResID of " + say.getSoundFile() + " EXISTS");
		return voiceResID;
	}
	
	public void setCotnext(Context context){
		this.context = context;
	}

	public String getExpression() {
		return exp;
	}
	
	public void animateExpression(String exp){
		view.setImageResource(states.get(exp));
	}
	
	public void setExpression(String exp) {
		this.exp = exp;
//		Log.d("TEST0", "Set Expression: " + exp);
//		if(states.containsKey(exp)){
//			Log.d("TEST0", "Success! Expression exists");
//			view.setImageResource(states.get(exp));
//		}
//		else{
//			Log.d("TEST0", "Contains no such expression! : " + exp);
//			return false;
//		}
//		return true;
	}

	public boolean setExpressionFromCharacter(String name, String exp) {
		this.exp = exp;
		
		Log.d("TEST0", "Character: Setting expression of character : " + name + " with exp: " + exp);

		if(name.equalsIgnoreCase("main")){
			name = "popoi";
		}
		
		Character character;
		for(int i = 0; i < SalinlahiFour.getCharactersList().size(); i++){
			Log.d("TEST0", "Character: Checking character : " + SalinlahiFour.getCharactersList().get(i).getName());
			if(SalinlahiFour.getCharactersList().get(i).getName().equalsIgnoreCase(name)){
				Log.d("TEST0", "Character: Found character! : " + name);
				character = SalinlahiFour.getCharactersList().get(i);


				Log.d("TEST0", "Character: Checking if expression '" + exp + "' exists in character");
				if(character.getStates().containsKey(exp)){
					Log.d("TEST0", "Character: Sucess! It exists");
						setExpression(exp);
						return true;
				}
			}
		}
		
		
//		if(states.containsKey(exp)){
//			view.setImageResource(states.get(exp));
//		}
//		else{
//			return false;
//		}
		Log.d("TEST0", "Character: Contains no such expression! : " + exp);
		return false;
	}
	
	public HashMap<String, Integer> getStates(){
		return states;
	}
	
	public String getMovement() {
		return movement;
	}
	public void setMovement(String movement) {
		this.movement = movement;
	}
	public ScriptLine getSay() {
		return say;
	}
	public void setScriptLine(ScriptLine say) {
		this.say = say;
	}

	public String getRawName() {
		return name;
	}

	public String getMainName() {		
		String name = this.name;
		if(name.equalsIgnoreCase("pepay") || name.equalsIgnoreCase("popoi") ){
			name = "main";
		}
		return name;
	}
	
	public String getName() {		
		String name = this.name;
		if(name.equalsIgnoreCase("main")){
			if(SalinlahiFour.getLoggedInUser().getGender().equals("female")){
				name = "pepay";
			}else{
				name = "popoi";
			}
		}
		return name;
	}
	public void setName(String name) {
		Log.d("TEST0", "Setting string name of character: " + name);
		this.name = name;
		Log.d("TEST0", "DONE Setting string name of character: " + name);
	}
	
	public void setStates(){
		states = SalinlahiFour.getCharacter(name).getStates();
	}
	
	public boolean isMainCharacter(){
		if(name.equalsIgnoreCase("main")){
			return true;
		}
		return false;
	}
}
