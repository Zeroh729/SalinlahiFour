package com.ube.salinlahifour.uibuilders.Button;

import com.ube.salinlahifour.R;

public class LoginBtnStatesBuilder extends AbstractBtnStatesBuilder{

	@Override
	public void buildEnabledState() {
		setEnabledState(R.drawable.login_btn_login);
	}

	@Override
	public void buildPressedState() {
		setPressedState(R.drawable.login_btn_login_pressed);
	}

	@Override
	public void buildDisabledState() {
		setDisabledState(R.drawable.login_btn_login_disabled);
	}

	@Override
	public void buildFocusedState() {
		// TODO Auto-generated method stub
		
	}

}
