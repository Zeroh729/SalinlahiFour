package com.ube.salinlahifour.tutorials;

import android.content.Intent;
import android.util.Log;


import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.ube.salinlahifour.R;


public class Family extends AbstractTutorialActivity  {
	int page_num = 0;
	ImageView[] iv_pages;
	ImageButton[] ib_nav;
	public Family(){
		layoutID = R.layout.tutorial_family;
		
	}
	
	public void btn_play(View view){
		Log.d("Debug Family", "Aldrin: Play Button Clicked");
		Intent intent = new Intent(activityName);
		startActivity(intent);
		Log.d("Debug Family", "Aldrin: Tutorial Finished");
	}
	public void btn_next(View view){
		Log.d("Debug Family", "Aldrin: Next Button Clicked");
		if(page_num <= 2){
			//proceed to next page
			Log.d("Debug Family", "Aldrin: Proceed to Next Page");
			page_num++;
			switch(page_num){
			case 0:
			   iv_pages[0].setVisibility(View.VISIBLE);
			   iv_pages[1].setVisibility(View.GONE);
			   iv_pages[2].setVisibility(View.GONE);
			   ib_nav[0].setVisibility(View.INVISIBLE);
			   ib_nav[1].setVisibility(View.INVISIBLE);
			   ib_nav[2].setVisibility(View.VISIBLE);
			   break;
		case 1:
		   iv_pages[0].setVisibility(View.GONE);
		   iv_pages[1].setVisibility(View.VISIBLE);
		   iv_pages[2].setVisibility(View.GONE);
		   ib_nav[0].setVisibility(View.INVISIBLE);
		   ib_nav[1].setVisibility(View.VISIBLE);
		   ib_nav[2].setVisibility(View.VISIBLE);
				break;
		case 2:
		   iv_pages[0].setVisibility(View.GONE);
		   iv_pages[1].setVisibility(View.GONE);
		   iv_pages[2].setVisibility(View.VISIBLE);
		   ib_nav[0].setVisibility(View.VISIBLE);
		   ib_nav[1].setVisibility(View.VISIBLE);
		   ib_nav[2].setVisibility(View.INVISIBLE);
				break;
			}
		}else{
			Log.d("Debug Family", "Aldrin: IMPOSSIBLE - This is the last page");
		}
	}
	public void btn_prev(View view){
		Log.d("Debug Family", "Aldrin: Prev Button Clicked");
		Log.d("Debug Family", "Aldrin: Page: " + page_num);
		if(page_num != 0){
		//proceed to prev page
			Log.d("Debug Family", "Aldrin: Proceed To Previous Page");
			page_num--;
			switch(page_num){
			case 0:iv_pages[0].setVisibility(View.VISIBLE);//cover
				   iv_pages[1].setVisibility(View.GONE);//1st
				   iv_pages[2].setVisibility(View.GONE);//2nd
				   ib_nav[0].setVisibility(View.INVISIBLE);//start
				   ib_nav[1].setVisibility(View.INVISIBLE);//left
				   ib_nav[2].setVisibility(View.VISIBLE);//right
				   break;
			case 1:
			   iv_pages[0].setVisibility(View.GONE);
			   iv_pages[1].setVisibility(View.VISIBLE);
			   iv_pages[2].setVisibility(View.GONE);
			   ib_nav[0].setVisibility(View.INVISIBLE);
			   ib_nav[1].setVisibility(View.VISIBLE);
			   ib_nav[2].setVisibility(View.VISIBLE);
					break;
			case 2:iv_pages[0].setVisibility(View.GONE);
			   iv_pages[1].setVisibility(View.GONE);
			   iv_pages[2].setVisibility(View.VISIBLE);
			   ib_nav[0].setVisibility(View.VISIBLE);
			   ib_nav[1].setVisibility(View.VISIBLE);
			   ib_nav[2].setVisibility(View.INVISIBLE);
					break;
			}
		}else{
			Log.d("Debug Family", "Aldrin: IMPOSSIBLE - This is the 1st page");
		}
	}
	@Override
	protected void initiateViews() {
		// TODO Auto-generated method stub
		iv_pages = new ImageView[3];
		iv_pages[0] = (ImageView) findViewById(R.id.coverpage);
		iv_pages[1] = (ImageView) findViewById(R.id.firstpage);
		iv_pages[2] = (ImageView) findViewById(R.id.secondpage);
		ib_nav = new ImageButton[3];
		ib_nav[0] = (ImageButton) findViewById(R.id.start_btn);
		ib_nav[1] = (ImageButton) findViewById(R.id.left_arrow);
		ib_nav[2] = (ImageButton) findViewById(R.id.right_arrow);
		page_num = 0;
	}
}
