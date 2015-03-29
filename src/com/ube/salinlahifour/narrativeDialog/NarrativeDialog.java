package com.ube.salinlahifour.narrativeDialog;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ube.salinlahifour.R;
import com.ube.salinlahifour.uibuilders.Button.BtnNextArrowStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.BtnSkipStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.BtnStatesDirector;

public abstract class NarrativeDialog extends Activity{
	protected RelativeLayout parentView;
	protected TextView tv_dialog;
	protected ImageView[] iv_characters;
	protected Character[] characters;
	protected ArrayList<ScriptLine> script;
	protected int dialogIndex;
	protected int scriptline;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.narrativedialog_activity);
		
		parentView = (RelativeLayout)findViewById(R.id.parent_view);
		tv_dialog = (TextView)findViewById(R.id.tv_dialog);
		iv_characters = new ImageView[4];
		iv_characters[0] = (ImageView)findViewById(R.id.character1);
		iv_characters[1] = (ImageView)findViewById(R.id.character2);
		iv_characters[2] = (ImageView)findViewById(R.id.character3);
		iv_characters[3] = (ImageView)findViewById(R.id.character4);
		
		characters = new Character[4];
		script = new ArrayList<>();
		dialogIndex = 0;
		
		((ImageButton)findViewById(R.id.btn_next)).setImageDrawable(BtnStatesDirector.getImageDrawable(new BtnNextArrowStatesBuilder()));
		((ImageButton)findViewById(R.id.btn_skip)).setImageDrawable(BtnStatesDirector.getImageDrawable(new BtnSkipStatesBuilder()));
		
		initiateCharacters();
		initiateScript();
		setBackground(R.drawable.family_bg_livingroom);
		runDialog();
	}

	public abstract void initiateCharacters();
	public abstract void initiateScript();
	public abstract void runDialog();

	public void next(View view){
		dialogIndex++;
		runDialog();
	}
	
	public void skip(View view){
		finish();
	}
	
	public void setBackground(int resID){
		parentView.setBackgroundResource(resID);
	}
	
}

class ScriptLine{
	String line;
	int voiceResID;
	
	public ScriptLine(String line, int voiceResID){
		this.line = line;
		this.voiceResID = voiceResID;
	}
	
}
