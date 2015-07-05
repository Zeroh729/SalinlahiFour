package com.ube.salinlahifour;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.qwerjk.better_text.MagicTextView;
import com.ube.salinlahifour.database.UserLessonProgressOperations;
import com.ube.salinlahifour.database.UserRecordOperations;
import com.ube.salinlahifour.enumTypes.LevelType;
import com.ube.salinlahifour.enumTypes.StatusType;
import com.ube.salinlahifour.model.ProgressListItems;
import com.ube.salinlahifour.model.UserDetail;
import com.ube.salinlahifour.model.UserLessonProgress;
import com.ube.salinlahifour.model.UserRecord;
import com.ube.salinlahifour.uibuilders.Button.BackBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.BtnStatesDirector;
import com.ube.salinlahifour.uibuilders.RadioButton.AllprogressRadioBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.RadioButton.RadioBtnStatesDirector;
import com.ube.salinlahifour.uibuilders.RadioButton.RecentRadioBtnStatesBuilder;

public class ProgressTreeActivity extends Activity implements OnCheckedChangeListener{
	private String username;
	private int USERID;
	private int stars_gold;
	private int stars_silver;
	private int stars_bronze;
	private ArrayList<Lesson> lessons;
	private ArrayList<ProgressListItems> recentdata;
	private ArrayList<ProgressListItems> totaldata;
	private MagicTextView tv_username;
	private TextView tv_goldcount;
	private TextView tv_silvercount;
	private TextView tv_bronzecount;
	private TextView tv_totalstarscount;
	private RadioGroup radiog_data;
	private RadioButton rdo_recent;
	private RadioButton rdo_all;
	private ListView listView;
	private ImageButton btn_back;

	private UserLessonProgressOperations userprogressdb;
	private UserRecordOperations userrecorddb;
	private UserDetail userdata;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_progress_tree);

		lessons = SalinlahiFour.getLessonsList();
		recentdata = new ArrayList();
		totaldata = new ArrayList();

		userprogressdb = new UserLessonProgressOperations(this);
		userrecorddb = new UserRecordOperations(this);
		USERID = SalinlahiFour.getLoggedInUser().getId();

		instantiateViews();
		populateData();
	}

	private void populateData() {
		int totalStars = 0;
		userdata = SalinlahiFour.getLoggedInUser();
		username = userdata.getName();
		userprogressdb.open();
		stars_gold = userprogressdb.getGoldStarsCount(USERID);
		stars_silver = userprogressdb.getSilverStarsCount(USERID);
		stars_bronze = userprogressdb.getBronzeStarsCount(USERID);

		totalStars = (stars_gold * 3) + (stars_silver * 2) + stars_bronze;

		tv_username.setText(username + "'s");
		//tv_goldcount.setText(stars_gold+"");
		//tv_silvercount.setText(stars_silver+"");
		//tv_bronzecount.setText(stars_bronze+"");
		tv_goldcount.setText(totalStars+"");
		tv_totalstarscount.setText(" /"+(lessons.size()*3*3));

		if(stars_silver == 0){
			tv_silvercount.setVisibility(View.INVISIBLE);
			findViewById(R.id.img_silver).setVisibility(View.INVISIBLE);
		}
		if(stars_bronze == 0){
			tv_bronzecount.setVisibility(View.INVISIBLE);
			findViewById(R.id.img_bronze).setVisibility(View.INVISIBLE);
		}

		rdo_recent.setChecked(true);

		userrecorddb.open();
		for(Lesson lesson : lessons){
			ProgressListItems tempData = new ProgressListItems();
			String lessonName = lesson.getName();
			tempData.setLessonCategory(true);
			tempData.setLessonName(lessonName);

			userprogressdb.open();
			UserLessonProgress progress = userprogressdb.getUserLessonProgress(USERID, lessonName);
			try{	
				tempData.setEasyStar(progress.getEasyStar());
			}catch(Exception e){
				tempData.setEasyStar("");
			}

			try{	
				tempData.setMedStar(progress.getMediumStar());
			}catch(Exception e){
				tempData.setMedStar("");
			}

			try{
				tempData.setHardStar(progress.getHardStar());
			}catch(Exception e){
				tempData.setHardStar("");
			}
			userprogressdb.close();

			recentdata.add(tempData);
			totaldata.add(tempData);

			ArrayList<Item> items = lesson.getItems();
			ArrayList<UserRecord> userrecords = userrecorddb.getAllUserRecordsFromUserId(USERID, lessonName);
			HashMap<String, ItemCounter> recentItemMap = new HashMap();				
			HashMap<String, ItemCounter> totalItemMap = new HashMap();

			if(items != null) {
				for(Item item: items){
					recentItemMap.put(item.getWord(), new ItemCounter(item.getWord()));
					totalItemMap.put(item.getWord(), new ItemCounter(item.getWord()));
				}
				
				for(UserRecord record: userrecords) {
					String correctAnswer = record.getCorrectAnswer();
					if(record.getStatus().equalsIgnoreCase(StatusType.CORRECT.toString())){
						totalItemMap.get(correctAnswer).correctAnswers++;
						if(recentItemMap.get(correctAnswer).getTotal() < 20){
							recentItemMap.get(correctAnswer).correctAnswers++;
						}
					} else {
						totalItemMap.get(correctAnswer).wrongAnswers++;
						if(recentItemMap.get(correctAnswer).getTotal() < 20){
							recentItemMap.get(correctAnswer).wrongAnswers++;
						}
					}
				}
				
				for(Item item : items){
					ItemCounter ctr = recentItemMap.get(item.getWord());
					ProgressListItems tempRecent = new ProgressListItems();
					tempRecent.setLessonCategory(false);
					tempRecent.setItemName(item.getWord());
					tempRecent.setProgressBarLabel(ctr.correctAnswers + "/" + ctr.getTotal());

					recentdata.add(tempRecent);
					recentdata.get(recentdata.size() - 1).setProgress((int) ctr.getPercentage());
					//recentdata.add("\t" + item.getWord() + " " + ctr.correctAnswers + "/" + ctr.getTotal() + " :" + String.format("%.2f", ctr.getPercentage()));

					totaldata.add(tempRecent);
					totaldata.get(recentdata.size() - 1).setProgress((int) ctr.getPercentage());
					ctr = totalItemMap.get(item.getWord());
					//totaldata.add("\t" + item.getWord() + " " + ctr.correctAnswers + "/" + ctr.getTotal() + " :" + String.format("%.2f", ctr.getPercentage()));
				}
			}

		}

		userrecorddb.close();

		ProgressItemAdapter adapter = new ProgressItemAdapter(this, R.layout.progress_item, recentdata);
		listView.setAdapter(adapter);
	}

	private void instantiateViews() {
		tv_username = (MagicTextView)findViewById(R.id.tv_username);
		tv_goldcount = (TextView)findViewById(R.id.tv_goldcount);
		tv_silvercount = (TextView)findViewById(R.id.tv_silvercount);
		tv_bronzecount = (TextView)findViewById(R.id.tv_bronzecount);
		tv_totalstarscount = (TextView)findViewById(R.id.tv_totalstarscount);	
		rdo_all = (RadioButton)findViewById(R.id.rdo_all);
		rdo_recent = (RadioButton)findViewById(R.id.rdo_recent);
		listView = (ListView)findViewById(R.id.lv_data);
		radiog_data = (RadioGroup)findViewById(R.id.radiog_data);
		btn_back = (ImageButton)findViewById(R.id.btn_back);

		rdo_all.setBackgroundDrawable(RadioBtnStatesDirector.getImageDrawable(new AllprogressRadioBtnStatesBuilder()));
		rdo_recent.setBackgroundDrawable(RadioBtnStatesDirector.getImageDrawable(new RecentRadioBtnStatesBuilder()));
		btn_back.setImageDrawable(BtnStatesDirector.getImageDrawable(new BackBtnStatesBuilder()));

		radiog_data.setOnCheckedChangeListener(this);

		for(int i = 0; i < 30; i++)
			tv_username.addOuterShadow(5, 0, 0, 0xFF54460e);

		tv_username.setTypeface(SalinlahiFour.getFontBpreplay());
		tv_goldcount.setTypeface(SalinlahiFour.getFontBpreplay());
		tv_silvercount.setTypeface(SalinlahiFour.getFontBpreplay());
		tv_bronzecount.setTypeface(SalinlahiFour.getFontBpreplay());
		tv_totalstarscount.setTypeface(SalinlahiFour.getFontBpreplay());
	}

	private class ItemCounter{
		public int correctAnswers;
		public int wrongAnswers;
		public int totalAnswers;
		public float percentage;

		public ItemCounter(String itemName){
		}

		public int getTotal(){
			totalAnswers = correctAnswers + wrongAnswers;
			return totalAnswers;
		}

		public float getPercentage(){
			getTotal();
			if(totalAnswers == 0)
				return 0;
			percentage = ((float)correctAnswers/totalAnswers)*100;
			return percentage;
		}
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		ProgressItemAdapter adapter;
		switch(checkedId){
			case R.id.rdo_recent:
				adapter = new ProgressItemAdapter(this, R.layout.progress_item, recentdata);
				listView.setAdapter(adapter);
				break;
			case R.id.rdo_all:
				adapter = new ProgressItemAdapter(this, R.layout.progress_item, totaldata);
				listView.setAdapter(adapter);
				break;
		}
	}

	public void back(View view){
		this.finish();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		SalinlahiFour.getBgm().start();
	}


	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		SalinlahiFour.getBgm().pause();
	}

}
