package com.ube.salinlahifour.uibuilders.Button;

import com.ube.salinlahifour.R;
import com.ube.salinlahifour.SalinlahiFour;

import android.graphics.drawable.StateListDrawable;

public abstract class AbstractBtnStatesBuilder {
	protected StateListDrawable stateListDrawable;
	
	public abstract void buildEnabledState();
	public abstract void buildPressedState();
	public abstract void buildDisabledState();
	public abstract void buildFocusedState();
	
	public void createButtonStateListBuilder(){
		stateListDrawable = new StateListDrawable();
	}
	
	public StateListDrawable getStateListDrawable() {
		return stateListDrawable;
	}

	
	protected void setEnabledState(int drawableResId){
		stateListDrawable.addState(new int[] {android.R.attr.state_enabled}, 
			      SalinlahiFour.getContext().getResources().getDrawable(drawableResId));
	}
	
	protected void setPressedState(int drawableResId){
		stateListDrawable.addState(new int[] {android.R.attr.state_pressed}, 
			      SalinlahiFour.getContext().getResources().getDrawable(drawableResId));
	}
	
	protected void setDisabledState(int drawableResId){
		stateListDrawable.addState(new int[] {-android.R.attr.state_enabled}, 
			      SalinlahiFour.getContext().getResources().getDrawable(drawableResId));
	}
	
	protected void setFocusedState(int drawableResId){
		stateListDrawable.addState(new int[] {android.R.attr.state_focused}, 
			      SalinlahiFour.getContext().getResources().getDrawable(drawableResId));
	}
}
