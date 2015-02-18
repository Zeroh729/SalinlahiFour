package com.ube.salinlahifour.animation;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.ube.salinlahifour.R;

public class AnimatedButtonListener implements OnTouchListener{
	private Animation animation;

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch(v.getId()){
			case R.id.img_choicea:
			case R.id.img_choiceb:
			case R.id.img_choicec:
			case R.id.img_choiced:
			case R.id.img_choicee:
			case R.id.img_choicef:
			case R.id.img_choiceg:
			case R.id.img_choiceh:
				switch(event.getAction()){
				case MotionEvent.ACTION_DOWN:
					YoYo.with(Techniques.Pulse).playOn(v);
					break;
				case MotionEvent.ACTION_UP:
				case MotionEvent.ACTION_CANCEL:
					break;
				}
				break;
			
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
