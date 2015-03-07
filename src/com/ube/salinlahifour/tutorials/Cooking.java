package com.ube.salinlahifour.tutorials;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.util.Log;


import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.ube.salinlahifour.R;
import com.ube.salinlahifour.SalinlahiFour;
import com.ube.salinlahifour.uibuilders.Button.BtnNextArrowStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.BtnStatesDirector;


public class Cooking extends AbstractTutorialActivity {
	private TextView[] textviews;
	private ImageButton[] imgviews;
	private boolean[] pressed;
	private TextView tv_rule;
	private ImageButton btn_next;
	
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
//				imgviews[j].setImageResource(items.get(i).getImageID());
				j++;
			}
		}
//		textviews[0].setText("Pula\nThis is RED. It's like the color of an apple");
//	    textviews[1].setText("Dilaw\nThis is YELLOW. It's like the color of the sun");
//	    textviews[2].setText("Berde\nThis is GREEN. It's like the color of the grass and leaves");
//	    textviews[3].setText("Asul\nThis is BLUE. It's like the color of the sky");
	    textviews[0].setText("This is Asul. It's the color of the sky");
	    textviews[1].setText("This is Berde. It's the color of grass and leaves");
	    textviews[2].setText("This is Pula. It's the color of an apple");
	    textviews[3].setText("This is Dilaw. It's the color of the sun");
	    
	    imgviews[0].setBackgroundColor(Color.BLUE);
	    imgviews[1].setBackgroundColor(Color.GREEN);
	    imgviews[2].setBackgroundColor(Color.RED);
	    imgviews[3].setBackgroundColor(Color.YELLOW);
	    
		final AnimatorSet setRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),
				R.animator.flipout);
	 
		final AnimatorSet setLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),
				R.animator.flipleft);

		View.OnClickListener oclClick = new View.OnClickListener() {
		       @Override
		       public void onClick(View v) {
		    	   tv_rule.setVisibility(View.INVISIBLE);
		    	   switch (v.getId()) {
		           case R.id.imageView1:
		        	   pressed[0] = true;
		        	   items.get(0).playFilipinoSound();
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
		        	   pressed[1] = true;
		        	   items.get(1).playFilipinoSound();
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
		        	   pressed[2] = true;
		        	   items.get(2).playFilipinoSound();
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
		        	   pressed[3] = true;
		        	   items.get(3).playFilipinoSound();
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
		    	   
		    	   for(int i = 0; i < pressed.length; i++){
		    		   if(pressed[i]){
		    			   if((i+1) == pressed.length){
		    				   btn_next.setVisibility(View.VISIBLE);
		    			   }
		    		   }else{
		    			   break;
		    		   }
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
//				imgviews[j].setImageResource(items.get(i).getImageID());
				j++;
			}
		}
//		textviews[0].setText("Itim\nThis is BLACK. It's like the color of the night sky");
//	    textviews[1].setText("Puti\nThis is WHITE. It's like the color of the clouds");
		

	    textviews[0].setText("This is Itim. It's the color of darkness");
	    textviews[1].setText("This is Puti. It's the color of clouds");
	    
	    imgviews[0].setBackgroundColor(Color.BLACK);
	    imgviews[1].setBackgroundColor(Color.WHITE);
	    
	    findViewById(R.id.layout2).setVisibility(View.INVISIBLE);
	    
		final AnimatorSet setRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),
				R.animator.flipout);
	 
		final AnimatorSet setLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),
				R.animator.flipleft);

		View.OnClickListener oclClick = new View.OnClickListener() {
		       @Override
		       public void onClick(View v) {
		    	   tv_rule.setVisibility(View.INVISIBLE);
		    	   switch (v.getId()) {
		           case R.id.imageView1:
		        	   pressed[0] = true;
		        	   items.get(0).playFilipinoSound();
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
		        	   pressed[1] = true;
		        	   items.get(1).playFilipinoSound();
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
		        	   pressed[2] = true;
		        	   items.get(2).playFilipinoSound();
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
		        	   pressed[3] = true;
		        	   items.get(3).playFilipinoSound();
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
		    	   
		    	   for(int i = 0; i < pressed.length-2; i++){
		    		   if(pressed[i]){
		    			   if((i+1) == pressed.length-2){
		    				   btn_next.setVisibility(View.VISIBLE);
		    			   }
		    		   }else{
		    			   break;
		    		   }
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
//				imgviews[j].setImageResource(items.get(i).getImageID());
				j++;
			}
		}
//		textviews[0].setText("Lila\nThis is VIOLET. It's like the color of an eggplant");
//	    textviews[1].setText("Kayumanggi\nThis is BROWN. It's like the color of the trunk of a tree");	    
	    
	    textviews[0].setText("This is Lila. It's the color of eggplants");
	    textviews[1].setText("This is Kayumanggi. It's the color of wood");
	    
	    imgviews[0].setBackgroundColor(Color.parseColor("#993399"));
	    imgviews[1].setBackgroundColor(Color.parseColor("#CC6600"));
	    
	    findViewById(R.id.layout2).setVisibility(View.INVISIBLE);
		final AnimatorSet setRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),
				R.animator.flipout);
	 
		final AnimatorSet setLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),
				R.animator.flipleft);

		View.OnClickListener oclClick = new View.OnClickListener() {
		       @Override
		       public void onClick(View v) {
		    	   tv_rule.setVisibility(View.INVISIBLE);
		    	   switch (v.getId()) {
		           case R.id.imageView1:
		        	   pressed[0] = true;
		        	   items.get(0).playFilipinoSound();
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
		        	   pressed[1] = true;
		        	   items.get(1).playFilipinoSound();
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
		        	   pressed[2] = true;
		        	   items.get(2).playFilipinoSound();
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
		        	   pressed[3] = true;
		        	   items.get(3).playFilipinoSound();
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
		    	   
		    	   for(int i = 0; i < pressed.length-2; i++){
		    		   if(pressed[i]){
		    			   if((i+1) == pressed.length-2){
		    				   btn_next.setVisibility(View.VISIBLE);
		    			   }
		    		   }else{
		    			   break;
		    		   }
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
		
		pressed = new boolean[4];
		
		tv_rule = (TextView)findViewById(R.id.tv_rule);
		btn_next = (ImageButton)findViewById(R.id.button1);
		btn_next.setImageDrawable(BtnStatesDirector.getImageDrawable(new BtnNextArrowStatesBuilder()));
		
		tv_rule.setTypeface(SalinlahiFour.getFontKgsecondchances());
		
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

		pressed[0] = false;
		pressed[1] = false;
		pressed[2] = false;
		pressed[3] = false;
	}
}
