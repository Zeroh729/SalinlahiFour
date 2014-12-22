package com.ube.salinlahifour.uibuilders.RadioButton;

import com.ube.salinlahifour.R;

public class MaleRadioBtnStatesBuilder extends AbstractRadioBtnStatesBuilder{

	@Override
	public void buildEnabledState() {
		setEnabledState(R.drawable.login_btn_char_boy);
	}

	@Override
	public void buildPressedState() {
		setPressedState(R.drawable.login_btn_char_boy_pressed);
	}

	@Override
	public void buildSelectedState() {
		setSelectedState(R.drawable.login_btn_char_boy_selected);
	}

}
