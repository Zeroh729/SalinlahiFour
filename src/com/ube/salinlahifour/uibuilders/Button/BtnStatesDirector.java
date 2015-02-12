package com.ube.salinlahifour.uibuilders.Button;

import android.graphics.drawable.StateListDrawable;

public abstract class BtnStatesDirector{
	private static AbstractBtnStatesBuilder buttonStateListBuilder;
	
	public static StateListDrawable getImageDrawable(AbstractBtnStatesBuilder builder){
		buttonStateListBuilder = builder;
		buttonStateListBuilder.createButtonStateListBuilder();
		buttonStateListBuilder.buildPressedState();
		buttonStateListBuilder.buildDisabledState();
		buttonStateListBuilder.buildFocusedState();
		buttonStateListBuilder.buildEnabledState();
		return buttonStateListBuilder.getStateListDrawable();
	}
}
