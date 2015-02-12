package com.ube.salinlahifour.uibuilders.Button;

import com.ube.salinlahifour.R;

public class BackBtnStatesBuilder extends AbstractBtnStatesBuilder{

	@Override
	public void buildEnabledState() {
		setEnabledState(R.drawable.btn_back);
	}

	@Override
	public void buildPressedState() {
		setPressedState(R.drawable.btn_back_pressed);
	}

	@Override
	public void buildDisabledState() {
		
	}

	@Override
	public void buildFocusedState() {
		
	}

}
