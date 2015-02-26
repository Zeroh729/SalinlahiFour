package com.ube.salinlahifour.uibuilders.RadioButton;

import com.ube.salinlahifour.SalinlahiFour;

import android.graphics.drawable.StateListDrawable;

public abstract class AbstractRadioBtnStatesBuilder {
	protected StateListDrawable stateListDrawable;
	public abstract void buildEnabledState();
	public abstract void buildPressedState();
	public abstract void buildSelectedState();
	
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
	
	protected void setSelectedState(int drawableResId){
		stateListDrawable.addState(new int[] {android.R.attr.state_checked}, 
			      SalinlahiFour.getContext().getResources().getDrawable(drawableResId));
	}
}
