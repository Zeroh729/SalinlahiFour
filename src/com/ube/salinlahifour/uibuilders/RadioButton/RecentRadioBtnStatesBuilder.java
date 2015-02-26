package com.ube.salinlahifour.uibuilders.RadioButton;

import com.ube.salinlahifour.R;
import com.ube.salinlahifour.uibuilders.Button.AbstractBtnStatesBuilder;

public class RecentRadioBtnStatesBuilder extends AbstractRadioBtnStatesBuilder {

	@Override
	public void buildEnabledState() {
		setEnabledState(R.drawable.progress_btn_recent);
	}

	@Override
	public void buildPressedState() {
		setPressedState(R.drawable.progress_btn_recent_pressed);
	}

	@Override
	public void buildSelectedState() {
		setSelectedState(R.drawable.progress_btn_recent_selected);
	}

}
