package com.ube.salinlahifour.uibuilders;

import com.ube.salinlahifour.R;

public class MaleButtonStateListBuilder extends ButtonStateListBuilder {

	@Override
	public void buildEnabledState() {
		setEnabledState(R.drawable.register1_btn_popoi);
	}

	@Override
	public void buildPressedState() {
		setPressedState(R.drawable.register1_btn_popoi_pressed);
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
