package com.ube.salinlahifour.tutorials;

import android.content.Intent;
import android.util.Log;


import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ube.salinlahifour.R;


public class Family extends AbstractTutorialActivity {
	private TextView[] textviews;
	private ImageView[] imgviews;
	
	public Family(){
		layoutID = R.layout.tutorial_family;
	}
	
	public void btn_play(View view){
		goToActivity();
	}

	@Override
	protected void setEasyTutorial() {
		Log.d(items.size() + "","TEST");
		int j = 0;
		for(int i = 0; i < items.size(); i++){
			if(items.get(i).getLevel().toString().equals(activityLevel)){
				textviews[j].setText(items.get(i).getWord());
				imgviews[j].setImageResource(items.get(i).getImageID());
				j++;
			}
		}
	}

	@Override
	protected void setMediumTutorial() {
		int j = 0;
		for(int i = 0; i < items.size(); i++){
			if(items.get(i).getLevel().toString().equals(activityLevel)){
				textviews[j].setText(items.get(i).getWord());
				imgviews[j].setImageResource(items.get(i).getImageID());
				j++;
			}
		}
	}

	@Override
	protected void setHardTutorial() {
		int j = 0;
		for(int i = 0; i < items.size(); i++){
			if(items.get(i).getLevel().toString().equals(activityLevel)){
				textviews[j].setText(items.get(i).getWord());
				imgviews[j].setImageResource(items.get(i).getImageID());
				j++;
			}
		}
	}

	@Override
	protected void initiateViews() {		
		textviews = new TextView[6];
		imgviews = new ImageView[6];
		
		textviews[0] = (TextView)findViewById(R.id.textView1);
		textviews[1] = (TextView)findViewById(R.id.TextView01);
		textviews[2] = (TextView)findViewById(R.id.TextView02);
		textviews[3] = (TextView)findViewById(R.id.textView2);
		textviews[4] = (TextView)findViewById(R.id.textView3);
		textviews[5] = (TextView)findViewById(R.id.textView4);
		imgviews[0] = (ImageView)findViewById(R.id.imageView1);
		imgviews[1] = (ImageView)findViewById(R.id.imageView2);
		imgviews[2] = (ImageView)findViewById(R.id.imageView3);
		imgviews[3] = (ImageView)findViewById(R.id.imageView4);
		imgviews[4] = (ImageView)findViewById(R.id.imageView5);
		imgviews[5] = (ImageView)findViewById(R.id.imageView6);
	}
}
