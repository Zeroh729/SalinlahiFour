package com.ube.salinlahifour.uibuilders;

import com.ube.salinlahifour.R;

public class OkButtonStateListBuilder extends ButtonStateListBuilder {

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
		// TODO Auto-generated method stub

	}

	@Override
	public void buildFocusedState() {
		// TODO Auto-generated method stub

	}

}
