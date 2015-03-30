package com.ube.salinlahifour.uibuilders.Button;

import com.ube.salinlahifour.R;

public class HowToPlayPrevBtnStatesBuilder extends AbstractBtnStatesBuilder {

	@Override
	public void buildEnabledState() {
		setEnabledState(R.drawable.tut_arrow_left);
	}

	@Override
	public void buildPressedState() {
		setPressedState(R.drawable.tut_arrow_left_pressed);
	}

	@Override
	public void buildDisabledState() {
		// TODO Auto-generated method stub

	}

	@Override
	public void buildFocusedState() {
		// TODO Auto-generated method stub

	}

}
