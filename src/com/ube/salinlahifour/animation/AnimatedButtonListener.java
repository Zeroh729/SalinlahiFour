package com.ube.salinlahifour.animation;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

public class AnimatedButtonListener implements OnTouchListener{
	private Animation animation;

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch(v.getId()){
		default:
			switch(event.getAction()){
			case MotionEvent.ACTION_DOWN:
				animation = new TranslateAnimation(0, 0, 0, 10);
				animation.setDuration(10);
				animation.setFillAfter(true);
				v.startAnimation(animation);
				break;
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_CANCEL:
				animation = new TranslateAnimation(0,0,0,10);
				v.startAnimation(animation);
				break;
			}
		}
		return false;
	}

}
