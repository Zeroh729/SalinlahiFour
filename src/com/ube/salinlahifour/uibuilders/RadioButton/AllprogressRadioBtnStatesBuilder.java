package com.ube.salinlahifour.uibuilders.RadioButton;

import com.ube.salinlahifour.R;

public class AllprogressRadioBtnStatesBuilder extends
		AbstractRadioBtnStatesBuilder {

	@Override
	public void buildEnabledState() {
		setEnabledState(R.drawable.progress_btn_all);
	}

	@Override
	public void buildPressedState() {
		setPressedState(R.drawable.progress_btn_all_pressed);
	}

	@Override
	public void buildSelectedState() {
		setSelectedState(R.drawable.progress_btn_all_selected);
	}


}
