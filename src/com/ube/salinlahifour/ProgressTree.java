package com.ube.salinlahifour;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ube.salinlahifour.database.UserLessonProgressOperations;
import com.ube.salinlahifour.database.UserRecordOperations;
import com.ube.salinlahifour.enumTypes.LevelType;
import com.ube.salinlahifour.enumTypes.StatusType;
import com.ube.salinlahifour.model.UserDetail;
import com.ube.salinlahifour.model.UserRecord;

public class ProgressTree extends Activity {
	private String username;
	private int stars_gold;
	private int stars_silver;
	private int stars_bronze;
	private int stars_total;
	private ArrayList<Lesson> lessons;
	private ArrayList<String> recentdata;
	private ArrayList<String> totaldata;
	private ArrayList<ItemCounter> itemCounter;
	
	private TextView tv_username;
	private TextView tv_goldcount;
	private TextView tv_silvercount;
	private TextView tv_bronzecount;
	private TextView tv_totalstarscount;
	private ListView lv_recentprogress;
	private ListView lv_totalprogress;
	

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
				
		tv_username.setText(username);
		tv_goldcount.setText(stars_gold+"");
		tv_silvercount.setText(stars_silver+"");
		tv_bronzecount.setText(stars_bronze+"");
		tv_totalstarscount.setText((lessons.size()*3)+"");
		
		userrecorddb.open();
		for(Lesson lesson : lessons){
			recentdata.add(lesson.getName());
			totaldata.add(lesson.getName());
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
						recentdata.add("\t" + item.getWord() + " " + ctr.correctAnswers + "/" + ctr.getTotal() + " :" + String.format("%.2f", ctr.getPercentage()));
						ctr = totalItemMap.get(item.getWord());
						totaldata.add("\t" + item.getWord() + " " + ctr.correctAnswers + "/" + ctr.getTotal() + " :" + String.format("%.2f", ctr.getPercentage()));
					}
				}
			
		}

		ArrayAdapter recentAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, recentdata);
		lv_recentprogress.setAdapter(recentAdapter);
		ArrayAdapter totalAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, totaldata);
		lv_totalprogress.setAdapter(totalAdapter);
	}

	private void instantiateViews() {
		tv_username = (TextView)findViewById(R.id.tv_username);
		tv_goldcount = (TextView)findViewById(R.id.tv_goldcount);
		tv_silvercount = (TextView)findViewById(R.id.tv_silvercount);
		tv_bronzecount = (TextView)findViewById(R.id.tv_bronzecount);
		tv_totalstarscount = (TextView)findViewById(R.id.tv_totalstarscount);		
		lv_recentprogress = (ListView)findViewById(R.id.lv_recentprogress);		
		lv_totalprogress = (ListView)findViewById(R.id.lv_totalprogress);
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
	
}
