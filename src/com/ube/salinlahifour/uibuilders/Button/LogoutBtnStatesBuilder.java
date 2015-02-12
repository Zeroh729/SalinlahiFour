package com.ube.salinlahifour.uibuilders.Button;

import com.ube.salinlahifour.R;

public class LogoutBtnStatesBuilder extends AbstractBtnStatesBuilder{

	@Override
	public void buildEnabledState() {
		setEnabledState(R.drawable.map_hud_btn_logout);
	}

	@Override
	public void buildPressedState() {
		setPressedState(R.drawable.map_hud_btn_logout_pressed);
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
