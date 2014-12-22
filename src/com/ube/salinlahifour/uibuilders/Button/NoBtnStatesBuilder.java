package com.ube.salinlahifour.uibuilders.Button;

import com.ube.salinlahifour.R;

public class NoBtnStatesBuilder extends AbstractBtnStatesBuilder {

	@Override
	public void buildEnabledState() {
		setEnabledState(R.drawable.btn_no);
	}

	@Override
	public void buildPressedState() {
		setPressedState(R.drawable.btn_no_pressed);
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
