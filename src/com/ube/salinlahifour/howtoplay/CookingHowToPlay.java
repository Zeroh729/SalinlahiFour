package com.ube.salinlahifour.howtoplay;

import com.ube.salinlahifour.R;

public class CookingHowToPlay extends AbstractHowToPlay {

	@Override
	protected void setImageRes() {
		imageRes.add(R.drawable.howtoplay_color_1);
		imageRes.add(R.drawable.howtoplay_color_2);
		imageRes.add(R.drawable.howtoplay_color_3);
		
		setUp();
		setShot();
	}

}
