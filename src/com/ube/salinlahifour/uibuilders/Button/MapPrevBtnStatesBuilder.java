package com.ube.salinlahifour.uibuilders.Button;

import com.ube.salinlahifour.R;

public class MapPrevBtnStatesBuilder extends AbstractBtnStatesBuilder{

	@Override
	public void buildEnabledState() {
		setEnabledState(R.drawable.btn_map_prev);
	}

	@Override
	public void buildPressedState() {
		setEnabledState(R.drawable.btn_map_prev_pressed);
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
