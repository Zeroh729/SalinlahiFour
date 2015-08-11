package com.ube.salinlahifour.narrativeDialog;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.ube.salinlahifour.debugclasses.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ube.salinlahifour.Lesson;
import com.ube.salinlahifour.R;
import com.ube.salinlahifour.SalinlahiFour;
import com.ube.salinlahifour.narrativeStory.StoryPlayer;
import com.ube.salinlahifour.uibuilders.Button.BtnNextArrowStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.BtnSkipStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.BtnStatesDirector;

public class NarrativeDialog extends Activity{
	private RelativeLayout parentView;
	private Lesson lesson;
	private StoryPlayer player;
	private Context context;
	
	private TextView tv_dialog;
	private ImageView img_talkbubble;
	private ImageView[] iv_characters;
	private ArrayList<Character> characters;
	private int dialogIndex;
	private int scriptline;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.narrativedialog_activity);
		
		context = this;
		
		parentView = (RelativeLayout)findViewById(R.id.parent_view);
		tv_dialog = (TextView)findViewById(R.id.tv_dialog);
		img_talkbubble = (ImageView)findViewById(R.id.img_talkbubble);
		iv_characters = new ImageView[4];
		iv_characters[0] = (ImageView)findViewById(R.id.character1);
		iv_characters[1] = (ImageView)findViewById(R.id.character2);
		iv_characters[2] = (ImageView)findViewById(R.id.character3);
		iv_characters[3] = (ImageView)findViewById(R.id.character4);
		
		Bundle bundle = getIntent().getExtras();
		String lessonName = bundle.getString("lessonName");
		Log.d("TEST0", "NarrativeDialog: recieved lessonName: " + lessonName);
		dialogIndex = 0;
		
		player = new StoryPlayer(lessonName, this);
		characters = new ArrayList<Character>();

		ArrayList<Character> tempcharacters = (ArrayList<Character>) SalinlahiFour.getCharactersList().clone();

		Log.d("TEST0", "Size of characters " + player.getStory().getCharacters().size());
		for(int i = 0; i < player.getStory().getCharacters().size(); i++){
			for(int j = 0; j < tempcharacters.size(); j++){
				if(player.getStory().getCharacters().get(i).getMainName().equals(tempcharacters.get(j).getMainName())) {
					if(tempcharacters.get(j).getMainName().equals("main"))
						if(SalinlahiFour.getLoggedInUser().getGender().equals("female") && tempcharacters.get(j).getRawName().equals("popoi")) {
							continue;
						} else if(SalinlahiFour.getLoggedInUser().getGender().equals("male") && tempcharacters.get(j).getRawName().equals("pepay")) {
							continue;
						}
					characters.add(tempcharacters.get(j));
					characters.get(characters.size()-1).setViews(tv_dialog, iv_characters[characters.size()-1], img_talkbubble);
					characters.get(characters.size()-1).setContext(context);
					break;
				}
			}
		}
		
		player.setCharacter(characters);
		
		((ImageButton)findViewById(R.id.btn_next)).setImageDrawable(BtnStatesDirector.getImageDrawable(new BtnNextArrowStatesBuilder()));
		((ImageButton)findViewById(R.id.btn_skip)).setImageDrawable(BtnStatesDirector.getImageDrawable(new BtnSkipStatesBuilder()));
		
		player.updateScene();
	}

	public void next(View view){
		img_talkbubble.setVisibility(View.INVISIBLE);
		tv_dialog.setVisibility(View.INVISIBLE);
		player.nextPage();
	}
	
	public void skip(View view){
		finish();
	}
	
}
