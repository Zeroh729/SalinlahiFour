package com.ube.salinlahifour.uibuilders;

import com.ube.salinlahifour.R;

public class FemaleButtonStateListBuilder extends ButtonStateListBuilder{

	@Override
	public void buildEnabledState() {
		setEnabledState(R.drawable.register1_btn_pepay);
	}

	@Override
	public void buildPressedState() {
		setPressedState(R.drawable.register1_btn_pepay_pressed);
	}

	@Override
	public void buildDisabledState() {
		
	}

	@Override
	public void buildFocusedState() {
		
	}

}
