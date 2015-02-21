package com.ube.salinlahifour;

import java.util.ArrayList;
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
import com.ube.salinlahifour.model.UserRecord;
import com.ube.salinlahifour.uibuilders.Button.BackBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.BtnStatesDirector;
import com.ube.salinlahifour.uibuilders.RadioButton.AllprogressRadioBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.RadioButton.RadioBtnStatesDirector;
import com.ube.salinlahifour.uibuilders.RadioButton.RecentRadioBtnStatesBuilder;

public class ProgressTree extends Activity implements OnCheckedChangeListener{
	private String username;
	private int stars_gold;
	private int stars_silver;
	private int stars_bronze;
	private int stars_total;
	private ArrayList<Lesson> lessons;
	private ArrayList<ProgressListItems> recentdata;
	private ArrayList<ProgressListItems> totaldata;
	private ArrayList<ItemCounter> itemCounter;
	
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
		
		Bundle bundles = getIntent().getExtras();
//		numLesson = bundles.getInt("numLessons");
//		numLesson*=3;

		Bundle bundle = getIntent().getExtras();
		lessons = (ArrayList) bundle.getParcelableArrayList("lessons");
		recentdata = new ArrayList();
		totaldata = new ArrayList();
		
		userprogressdb = new UserLessonProgressOperations(this);
		userrecorddb = new UserRecordOperations(this);

		
		instantiateViews();
		populateData();
	}

	private void populateData() {
		userdata = SalinlahiFour.getLoggedInUser();
		username = userdata.getName();
		userprogressdb.open();
		stars_gold = userprogressdb.getGoldStarsCount(userdata.getId());
		stars_silver = userprogressdb.getSilverStarsCount(userdata.getId());
		stars_bronze = userprogressdb.getBronzeStarsCount(userdata.getId());
		
		tv_username.setText(username + "'s");
		tv_goldcount.setText(stars_gold+"");
		tv_silvercount.setText(stars_silver+"");
		tv_bronzecount.setText(stars_bronze+"");
		tv_totalstarscount.setText(" /"+(lessons.size()*3));

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
//			recentdata.add(lesson.getName());
//			totaldata.add(lesson.getName());
			tempData.setLessonCategory(true);
			tempData.setLessonName(lesson.getName());

			userprogressdb.open();
			try{	
				tempData.setEasyStar(userprogressdb.getUserLessonProgress(SalinlahiFour.getLoggedInUser().getId(), lesson.getName()).getEasyStar());
			}catch(Exception e){
				tempData.setEasyStar("");
			}
			
			try{	
				tempData.setMedStar(userprogressdb.getUserLessonProgress(SalinlahiFour.getLoggedInUser().getId(), lesson.getName()).getMediumStar());
			}catch(Exception e){
				tempData.setMedStar("");
			}

			try{
				tempData.setHardStar(userprogressdb.getUserLessonProgress(SalinlahiFour.getLoggedInUser().getId(), lesson.getName()).getHardStar());
			}catch(Exception e){
				tempData.setHardStar("");
			}
			userprogressdb.close();
			
			recentdata.add(tempData);
			totaldata.add(tempData);
			
			ArrayList<Item> items = LessonItemLoader.getLessonItems(lesson.getActivity(), LevelType.HARD.toString());
				ArrayList<UserRecord> userrecords = userrecorddb.getAllUserRecordsFromUserId(SalinlahiFour.getLoggedInUser().getId(), lesson.getName());
				HashMap<String, ItemCounter> recentItemMap = new HashMap();				
				HashMap<String, ItemCounter> totalItemMap = new HashMap();
				
				if(items != null){					
					for(int i = 0; i < items.size(); i++){
						recentItemMap.put(items.get(i).getWord(), new ItemCounter(items.get(i).getWord()));
						totalItemMap.put(items.get(i).getWord(), new ItemCounter(items.get(i).getWord()));

					}
					for(int i = 0; i < userrecords.size(); i++){
							if(userrecords.get(i).getStatus().equalsIgnoreCase(StatusType.CORRECT.toString())){
								recentItemMap.get(userrecords.get(i).getCorrectAnswer()).correctAnswers++;
								if(totalItemMap.get(userrecords.get(i).getCorrectAnswer()).getTotal() < 20){
									totalItemMap.get(userrecords.get(i).getCorrectAnswer()).correctAnswers++;
								}
							}else{
								recentItemMap.get(userrecords.get(i).getCorrectAnswer()).wrongAnswers++;
								if(totalItemMap.get(userrecords.get(i).getCorrectAnswer()).getTotal() < 20){
									totalItemMap.get(userrecords.get(i).getCorrectAnswer()).wrongAnswers++;
								}
							}
					}
					for(Item item : items){
						ItemCounter ctr = recentItemMap.get(item.getWord());
						ProgressListItems tempRecent = new ProgressListItems();
						tempRecent.setLessonCategory(false);
						tempRecent.setItemName(item.getWord());

						recentdata.add(tempRecent);
						recentdata.get(recentdata.size()-1).setProgress((int)ctr.getPercentage());
						//recentdata.add("\t" + item.getWord() + " " + ctr.correctAnswers + "/" + ctr.getTotal() + " :" + String.format("%.2f", ctr.getPercentage()));

						totaldata.add(tempRecent);
						totaldata.get(recentdata.size()-1).setProgress((int)ctr.getPercentage());
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
		public String item;
		public int correctAnswers;
		public int wrongAnswers;
		public int totalAnswers;
		public float percentage;
		
		public ItemCounter(String itemName){
			item = itemName;
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
	
	private class LessonData{
		private Item item;
		private boolean recent;
		
		public LessonData(Item item, boolean recent){
			this.item = item;
			this.recent = recent;
		}
		
		public String toString(){
			return "\t" + item.getWord() + " ";
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
	
}
