package com.ube.salinlahifour.uibuilders.Button;

import com.ube.salinlahifour.R;

public class OkBtnStatesBuilder extends AbstractBtnStatesBuilder {

	@Override
	public void buildEnabledState() {
		setEnabledState(R.drawable.btn_ok);
	}

	@Override
	public void buildPressedState() {
		setPressedState(R.drawable.btn_ok_pressed);
	}

	@Override
	public void buildDisabledState() {
		setDisabledState(R.drawable.btn_ok_disabled);
	}

	@Override
	public void buildFocusedState() {
		// TODO Auto-generated method stub

	}

}
