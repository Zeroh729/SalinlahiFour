package com.ube.salinlahifour.uibuilders.Button;

import com.ube.salinlahifour.R;

public class HowToPlayNextBtnStatesBuilder extends AbstractBtnStatesBuilder {

	@Override
	public void buildEnabledState() {
		setEnabledState(R.drawable.tut_arrow_right);
	}

	@Override
	public void buildPressedState() {
		setPressedState(R.drawable.tut_arrow_right_pressed);
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
