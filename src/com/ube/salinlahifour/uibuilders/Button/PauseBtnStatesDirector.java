package com.ube.salinlahifour.uibuilders.Button;

import com.ube.salinlahifour.R;

public class PauseBtnStatesDirector extends AbstractBtnStatesBuilder {

	@Override
	public void buildEnabledState() {
		setEnabledState(R.drawable.backbtn);
	}

	@Override
	public void buildPressedState() {
		setPressedState(R.drawable.backbtn_pressed);
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
