package com.ube.salinlahifour.howtoplay;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.qwerjk.better_text.MagicTextView;
import com.ube.salinlahifour.R;
import com.ube.salinlahifour.SalinlahiFour;
import com.ube.salinlahifour.uibuilders.Button.BtnStatesDirector;
import com.ube.salinlahifour.uibuilders.Button.HowToPlayNextBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.HowToPlayPrevBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.StartBtnStatesBuilder;

public class HowToPlay extends Activity implements OnClickListener{
	private MagicTextView tv_title;
	public HowToPlaySet data;
	private ImageButton btn_next;
	private ImageView iv_screenshot;
	private ImageButton btn_prev;
	private ImageButton btn_start;
	private TextView tv_count;
	private int pageIndex; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.howtoplay);
		
		pageIndex = 0;
		
		data = new HowToPlaySet();
		
		Bundle bundle = getIntent().getExtras();
		String lessonname = bundle.getString("lessonName");
		data = SalinlahiFour.getTutorial(lessonname);
		
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
			tv_title.addOuterShadow(5, 0, 0, 0xFF066868);
		tv_title.setTypeface(SalinlahiFour.getFontKgsecondchances());
		
		tv_count = (TextView)findViewById(R.id.tv_count);
		tv_count.setTypeface(SalinlahiFour.getFontPlaytime());
		

	}

	
	public void doneAdding(){
		setUp();
		setShot();
	}
	

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
		iv_screenshot.setImageResource(data.imageRes.get(pageIndex));
	}
	
	protected void setUp() {
		btn_next.setVisibility(View.VISIBLE);
		btn_prev.setVisibility(View.VISIBLE);
		
		if(pageIndex >= data.imageRes.size()-1){
			btn_next.setVisibility(View.INVISIBLE);
		}
		if(pageIndex == 0){
			btn_prev.setVisibility(View.INVISIBLE);
		}
		
		tv_count.setText((pageIndex+1) + "/" + data.imageRes.size());
	}
}
