package com.ube.salinlahifour.uibuilders.Button;

import com.ube.salinlahifour.R;

public class MediumBtnStatesBuilder extends AbstractBtnStatesBuilder {

	@Override
	public void buildEnabledState() {
		setEnabledState(R.drawable.btn_lvlselect_medium);
	}

	@Override
	public void buildPressedState() {
		setPressedState(R.drawable.btn_lvlselect_medium_pressed);
	}


	@Override
	public void buildDisabledState() {
		setDisabledState(R.drawable.btn_lvlselect_medium_disabled);
	}

	@Override
	public void buildFocusedState() {
		// TODO Auto-generated method stub

	}

}
