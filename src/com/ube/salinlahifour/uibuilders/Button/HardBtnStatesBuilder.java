package com.ube.salinlahifour.uibuilders.Button;

import com.ube.salinlahifour.R;

public class HardBtnStatesBuilder extends AbstractBtnStatesBuilder {

	@Override
	public void buildEnabledState() {
		setEnabledState(R.drawable.btn_lvlselect_hard);
	}

	@Override
	public void buildPressedState() {
		setPressedState(R.drawable.btn_lvlselect_hard_pressed);
	}


	@Override
	public void buildDisabledState() {
		setDisabledState(R.drawable.btn_lvlselect_hard_disabled);
	}

	@Override
	public void buildFocusedState() {
		// TODO Auto-generated method stub

	}

}
