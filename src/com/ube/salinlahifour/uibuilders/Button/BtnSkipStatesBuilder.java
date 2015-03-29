package com.ube.salinlahifour.uibuilders.Button;

import com.ube.salinlahifour.R;

public class BtnSkipStatesBuilder extends AbstractBtnStatesBuilder{

	@Override
	public void buildEnabledState() {
		setEnabledState(R.drawable.btn_skip);
	}

	@Override
	public void buildPressedState() {
		setPressedState(R.drawable.btn_skip_pressed);
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
