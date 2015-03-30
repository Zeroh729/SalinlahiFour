package com.ube.salinlahifour.howtoplay;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.qwerjk.better_text.MagicTextView;
import com.ube.salinlahifour.R;
import com.ube.salinlahifour.SalinlahiFour;
import com.ube.salinlahifour.uibuilders.Button.BtnNextArrowStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.BtnStatesDirector;
import com.ube.salinlahifour.uibuilders.Button.HowToPlayNextBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.HowToPlayPrevBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.StartBtnStatesBuilder;

public abstract class AbstractHowToPlay extends Activity implements OnClickListener{
	protected ArrayList<Integer> imageRes;
	protected MagicTextView tv_title;
	protected ImageButton btn_next;
	protected ImageView iv_screenshot;
	protected ImageButton btn_prev;
	protected ImageButton btn_start;
	protected int pageIndex; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.howtoplay);
		
		pageIndex = 0;
		
		imageRes = new ArrayList();
		
		btn_next = (ImageButton)findViewById(R.id.btn_next);
		btn_prev = (ImageButton)findViewById(R.id.btn_prev);
		btn_start = (ImageButton)findViewById(R.id.btn_start);

		btn_next.setImageDrawable(BtnStatesDirector.getImageDrawable(new HowToPlayNextBtnStatesBuilder()));
		btn_prev.setImageDrawable(BtnStatesDirector.getImageDrawable(new HowToPlayPrevBtnStatesBuilder()));
		btn_start.setImageDrawable(BtnStatesDirector.getImageDrawable(new StartBtnStatesBuilder()));
		
		btn_next.setOnClickListener(this);
		btn_prev.setOnClickListener(this);
		btn_start.setOnClickListener(this);
		
		iv_screenshot = (ImageView)findViewById(R.id.iv_screenshot);

	    tv_title = (MagicTextView)findViewById(R.id.tv_title);
		for(int i = 0; i < 30; i++)
			tv_title.addOuterShadow(5, 0, 0, 0xFF164366);
		tv_title.setTypeface(SalinlahiFour.getFontKgsecondchances());
		
		setImageRes();
	}
	
	protected abstract void setImageRes();

	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.btn_next:
				pageIndex++;
				setUp();
				setShot();
				break;
			case R.id.btn_prev:
				pageIndex--;
				setUp();
				setShot();
				break;
			case R.id.btn_start:
				finish();
				break;
		}
	}

	protected void setShot() {
		iv_screenshot.setImageResource(imageRes.get(pageIndex));
	}
	
	protected void setUp() {
		btn_next.setVisibility(View.VISIBLE);
		btn_prev.setVisibility(View.VISIBLE);
		
		if(pageIndex >= imageRes.size()-1){
			btn_next.setVisibility(View.INVISIBLE);
		}
		if(pageIndex == 0){
			btn_prev.setVisibility(View.INVISIBLE);
		}
	}
}
