package com.ube.salinlahifour.uibuilders.Button;

import com.ube.salinlahifour.R;

public class StartBtnStatesBuilder extends AbstractBtnStatesBuilder {

	@Override
	public void buildEnabledState() {
		setEnabledState(R.drawable.register3_btn_start);
	}

	@Override
	public void buildPressedState() {
		setPressedState(R.drawable.register3_btn_start_pressed);
	}

	@Override
	public void buildDisabledState() {
	}

	@Override
	public void buildFocusedState() {
	}

}
