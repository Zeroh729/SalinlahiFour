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


public class Cooking extends AbstractTutorialActivity {
	private TextView[] textviews;
	private ImageButton[] imgviews;
	
	public Cooking(){
		layoutID = R.layout.tutorial_cooking;
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
		textviews[0].setText("Pula - This is RED. It's like the color of an apple");
	    textviews[1].setText("Dilaw - This is YELLOW. It's like the color of the sun");
	    textviews[2].setText("Berde - This is GREEN. It's like the color of the grass and leaves");
	    textviews[3].setText("Asul - This is BLUE. It's like the color of the sky");
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
		textviews[0].setText("Itim - This is BLACK. It's like the color of the night sky");
	    textviews[1].setText("Puti - This is WHITE. It's like the color of the clouds");

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
		textviews[0].setText("Lila - This is VIOLET. It's like the color of an eggplant");
	    textviews[1].setText("Kayumanggi - This is BROWN. It's like the color of the trunk of a tree");
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
