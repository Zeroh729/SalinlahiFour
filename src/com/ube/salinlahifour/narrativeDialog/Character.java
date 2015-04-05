package com.ube.salinlahifour.narrativeDialog;

import java.util.HashMap;

import android.content.Context;
import android.media.MediaPlayer;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class Character {
	private Context context;
	private TextView tv_dialog;
	private ImageView view;
	private HashMap<Expression, Integer> state;
	
	public Character(Context context, TextView tv_dialog, ImageView view, int resID){
		this.context = context;
		this.view = view;
		this.tv_dialog = tv_dialog;
		
		state = new HashMap<>();
		
		state.put(Expression.DEFAULT, resID);
		
		setExpression(Expression.DEFAULT);
	}
	
	public void addExpression(Expression ex, int resID){
		state.put(ex, resID);
	}
	
	public void setExpression(Expression ex){
		if(state.get(ex) == null)
			view.setImageResource(state.get(Expression.DEFAULT));
		else
			view.setImageResource(state.get(ex));
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
	
	public void say(ScriptLine line){
		MediaPlayer voice = MediaPlayer.create(context, line.voiceResID);
		voice.start();
		tv_dialog.setText(Html.fromHtml(line.line));
		
	}
}
