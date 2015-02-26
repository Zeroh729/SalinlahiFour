package com.ube.salinlahifour.uibuilders.Button;

import com.ube.salinlahifour.R;

public class RegisterBtnStatesBuilder extends
		AbstractBtnStatesBuilder {

	@Override
	public void buildEnabledState() {
		setEnabledState(R.drawable.login_btn_add);
	}

	@Override
	public void buildPressedState() {
		setPressedState(R.drawable.login_btn_add_pressed);
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
