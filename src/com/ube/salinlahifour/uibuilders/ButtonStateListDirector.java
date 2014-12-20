package com.ube.salinlahifour.uibuilders;

import android.graphics.drawable.StateListDrawable;

public abstract class ButtonStateListDirector{
	private static ButtonStateListBuilder buttonStateListBuilder;
	
	public static StateListDrawable getImageDrawable(ButtonStateListBuilder builder){
		buttonStateListBuilder = builder;
		buttonStateListBuilder.createButtonStateListBuilder();
		buttonStateListBuilder.buildPressedState();
		buttonStateListBuilder.buildDisabledState();
		buttonStateListBuilder.buildFocusedState();
		buttonStateListBuilder.buildEnabledState();
		return buttonStateListBuilder.getStateListDrawable();
	}
	
}
