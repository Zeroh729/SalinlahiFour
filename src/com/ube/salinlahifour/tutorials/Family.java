package com.ube.salinlahifour.tutorials;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.util.Log;


import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.ube.salinlahifour.R;


public class Family extends AbstractTutorialActivity {
	private TextView[] textviews;
	private ImageButton[] imgviews;
	
	public Family(){
		layoutID = R.layout.tutorial_family;
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
		textviews[0].setText("Nanay - She is my mother. She usually cooks for the family");
	    textviews[1].setText("Tatay - This is my father. He is the one that works for the family");
	    textviews[2].setText("Kuya - My older brother. He likes to play basketball and all different kinfs of sports");
	    textviews[3].setText("Ate - She is my older sister. She like to spend her time with her phone");
		final AnimatorSet setRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),
				R.animator.flipout);
	 
		final AnimatorSet setLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),
				R.animator.flipleft);

		View.OnClickListener oclClick = new View.OnClickListener() {
		       @Override
		       public void onClick(View v) {
		    	   switch (v.getId()) {
		           case R.id.imageView1:
		        	   if(!isBackVisibleimageView1){
		        		   textviews[0].setVisibility(android.view.View.VISIBLE);
							setRightOut.setTarget(imgviews[0]);
							setLeftIn.setTarget(textviews[0]);
							setRightOut.start();
							setLeftIn.start();
							isBackVisibleimageView1 = true;
						}
						else{
							setRightOut.setTarget(textviews[0]);
							setLeftIn.setTarget(imgviews[0]);
							setRightOut.start();
							setLeftIn.start();
							isBackVisibleimageView1 = false;
						}
		             break;
		           case R.id.imageView2:
		        	   if(!isBackVisibleimageView2){
		        		   textviews[1].setVisibility(android.view.View.VISIBLE);
							setRightOut.setTarget(imgviews[1]);
							setLeftIn.setTarget(textviews[1]);
							setRightOut.start();
							setLeftIn.start();
							isBackVisibleimageView2 = true;
						}
						else{
							setRightOut.setTarget(textviews[1]);
							setLeftIn.setTarget(imgviews[1]);
							setRightOut.start();
							setLeftIn.start();
							isBackVisibleimageView2 = false;
						} 
		             break;
		           case R.id.imageView3:
		        	   if(!isBackVisibleimageView3){
		        		   textviews[2].setVisibility(android.view.View.VISIBLE);
							setRightOut.setTarget(imgviews[2]);
							setLeftIn.setTarget(textviews[2]);
							setRightOut.start();
							setLeftIn.start();
							isBackVisibleimageView3 = true;
						}
						else{
							setRightOut.setTarget(textviews[2]);
							setLeftIn.setTarget(imgviews[2]);
							setRightOut.start();
							setLeftIn.start();
							isBackVisibleimageView3 = false;
						}    
		             break;		          
		           case R.id.imageView4:
		        	   if(!isBackVisibleimageView4){
		        		   textviews[3].setVisibility(android.view.View.VISIBLE);
							setRightOut.setTarget(imgviews[3]);
							setLeftIn.setTarget(textviews[3]);
							setRightOut.start();
							setLeftIn.start();
							isBackVisibleimageView4 = true;
						}
						else{
							setRightOut.setTarget(textviews[3]);
							setLeftIn.setTarget(imgviews[3]);
							setRightOut.start();
							setLeftIn.start();
							isBackVisibleimageView4 = false;
						}        
		             break;
		           }
		       }
		     };
		     imgviews[0].setOnClickListener(oclClick);
		     imgviews[1].setOnClickListener(oclClick);
		     imgviews[2].setOnClickListener(oclClick);
		     imgviews[3].setOnClickListener(oclClick);
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
		textviews[0].setText("Lolo - My grandfather. He uses glasses to read the newspaper");
	    textviews[1].setText("Lola - My grandmother. She walks around with a cane");
	    textviews[2].setText("Bunso - My younger sibling. He likes to play toys.");

		final AnimatorSet setRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),
				R.animator.flipout);
	 
		final AnimatorSet setLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),
				R.animator.flipleft);

		View.OnClickListener oclClick = new View.OnClickListener() {
		       @Override
		       public void onClick(View v) {
		    	   switch (v.getId()) {
		           case R.id.imageView1:
		        	   if(!isBackVisibleimageView1){
		        		   textviews[0].setVisibility(android.view.View.VISIBLE);
							setRightOut.setTarget(imgviews[0]);
							setLeftIn.setTarget(textviews[0]);
							setRightOut.start();
							setLeftIn.start();
							isBackVisibleimageView1 = true;
						}
						else{
							setRightOut.setTarget(textviews[0]);
							setLeftIn.setTarget(imgviews[0]);
							setRightOut.start();
							setLeftIn.start();
							isBackVisibleimageView1 = false;
						}
		             break;
		           case R.id.imageView2:
		        	   if(!isBackVisibleimageView2){
		        		   textviews[1].setVisibility(android.view.View.VISIBLE);
							setRightOut.setTarget(imgviews[1]);
							setLeftIn.setTarget(textviews[1]);
							setRightOut.start();
							setLeftIn.start();
							isBackVisibleimageView2 = true;
						}
						else{
							setRightOut.setTarget(textviews[1]);
							setLeftIn.setTarget(imgviews[1]);
							setRightOut.start();
							setLeftIn.start();
							isBackVisibleimageView2 = false;
						} 
		             break;
		           case R.id.imageView3:
		        	   if(!isBackVisibleimageView3){
		        		   textviews[2].setVisibility(android.view.View.VISIBLE);
							setRightOut.setTarget(imgviews[2]);
							setLeftIn.setTarget(textviews[2]);
							setRightOut.start();
							setLeftIn.start();
							isBackVisibleimageView3 = true;
						}
						else{
							setRightOut.setTarget(textviews[2]);
							setLeftIn.setTarget(imgviews[2]);
							setRightOut.start();
							setLeftIn.start();
							isBackVisibleimageView3 = false;
						}    
		             break;		          
		           case R.id.imageView4:
		        	   if(!isBackVisibleimageView4){
		        		   textviews[3].setVisibility(android.view.View.VISIBLE);
							setRightOut.setTarget(imgviews[3]);
							setLeftIn.setTarget(textviews[3]);
							setRightOut.start();
							setLeftIn.start();
							isBackVisibleimageView4 = true;
						}
						else{
							setRightOut.setTarget(textviews[3]);
							setLeftIn.setTarget(imgviews[3]);
							setRightOut.start();
							setLeftIn.start();
							isBackVisibleimageView4 = false;
						}        
		             break;
		           }
		       }
		     };
		     imgviews[0].setOnClickListener(oclClick);
		     imgviews[1].setOnClickListener(oclClick);
		     imgviews[2].setOnClickListener(oclClick);
		     imgviews[3].setOnClickListener(oclClick);
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
		textviews[0].setText("Tito - He is the brother of my parent. I do not know which one though.");
	    textviews[1].setText("Tita - The sister of my mother. She is a great cook too.");
		final AnimatorSet setRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),
				R.animator.flipout);
	 
		final AnimatorSet setLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),
				R.animator.flipleft);

		View.OnClickListener oclClick = new View.OnClickListener() {
		       @Override
		       public void onClick(View v) {
		    	   switch (v.getId()) {
		           case R.id.imageView1:
		        	   if(!isBackVisibleimageView1){
		        		   textviews[0].setVisibility(android.view.View.VISIBLE);
							setRightOut.setTarget(imgviews[0]);
							setLeftIn.setTarget(textviews[0]);
							setRightOut.start();
							setLeftIn.start();
							isBackVisibleimageView1 = true;
						}
						else{
							setRightOut.setTarget(textviews[0]);
							setLeftIn.setTarget(imgviews[0]);
							setRightOut.start();
							setLeftIn.start();
							isBackVisibleimageView1 = false;
						}
		             break;
		           case R.id.imageView2:
		        	   if(!isBackVisibleimageView2){
		        		   textviews[1].setVisibility(android.view.View.VISIBLE);
							setRightOut.setTarget(imgviews[1]);
							setLeftIn.setTarget(textviews[1]);
							setRightOut.start();
							setLeftIn.start();
							isBackVisibleimageView2 = true;
						}
						else{
							setRightOut.setTarget(textviews[1]);
							setLeftIn.setTarget(imgviews[1]);
							setRightOut.start();
							setLeftIn.start();
							isBackVisibleimageView2 = false;
						} 
		             break;
		           case R.id.imageView3:
		        	   if(!isBackVisibleimageView3){
		        		   textviews[2].setVisibility(android.view.View.VISIBLE);
							setRightOut.setTarget(imgviews[2]);
							setLeftIn.setTarget(textviews[2]);
							setRightOut.start();
							setLeftIn.start();
							isBackVisibleimageView3 = true;
						}
						else{
							setRightOut.setTarget(textviews[2]);
							setLeftIn.setTarget(imgviews[2]);
							setRightOut.start();
							setLeftIn.start();
							isBackVisibleimageView3 = false;
						}    
		             break;		          
		           case R.id.imageView4:
		        	   if(!isBackVisibleimageView4){
		        		   textviews[3].setVisibility(android.view.View.VISIBLE);
							setRightOut.setTarget(imgviews[3]);
							setLeftIn.setTarget(textviews[3]);
							setRightOut.start();
							setLeftIn.start();
							isBackVisibleimageView4 = true;
						}
						else{
							setRightOut.setTarget(textviews[3]);
							setLeftIn.setTarget(imgviews[3]);
							setRightOut.start();
							setLeftIn.start();
							isBackVisibleimageView4 = false;
						}        
		             break;
		           }
		       }
		     };
		     imgviews[0].setOnClickListener(oclClick);
		     imgviews[1].setOnClickListener(oclClick);
		     imgviews[2].setOnClickListener(oclClick);
		     imgviews[3].setOnClickListener(oclClick);
	}

	@Override
	protected void initiateViews() {		
		textviews = new TextView[9];
		imgviews = new ImageButton[9];
		textviews[0] = (TextView)findViewById(R.id.textView1);
		textviews[0].setVisibility(android.view.View.GONE);
		textviews[1] = (TextView)findViewById(R.id.textView2);
		textviews[1].setVisibility(android.view.View.GONE);
		textviews[2] = (TextView)findViewById(R.id.textView3);
		textviews[2].setVisibility(android.view.View.GONE);
		textviews[3] = (TextView)findViewById(R.id.textView4);
		textviews[3].setVisibility(android.view.View.GONE);
		imgviews[0] = (ImageButton)findViewById(R.id.imageView1);
		imgviews[1] = (ImageButton)findViewById(R.id.imageView2);
		imgviews[2] = (ImageButton)findViewById(R.id.imageView3);
		imgviews[3] = (ImageButton)findViewById(R.id.imageView4);
		
	}
}
