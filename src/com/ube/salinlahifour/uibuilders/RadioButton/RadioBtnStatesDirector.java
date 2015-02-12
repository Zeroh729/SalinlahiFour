package com.ube.salinlahifour.uibuilders.RadioButton;

import android.graphics.drawable.StateListDrawable;

public abstract class RadioBtnStatesDirector {
	private static AbstractRadioBtnStatesBuilder radioBtnStateListBuilder;

	public static StateListDrawable getImageDrawable(AbstractRadioBtnStatesBuilder builder){
		radioBtnStateListBuilder = builder;
		radioBtnStateListBuilder.createButtonStateListBuilder();
		radioBtnStateListBuilder.buildPressedState();
		radioBtnStateListBuilder.buildSelectedState();
		radioBtnStateListBuilder.buildEnabledState();
		return radioBtnStateListBuilder.getStateListDrawable();
	}
}
