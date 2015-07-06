package com.ube.salinlahifour.model;

import com.ube.salinlahifour.R;
import com.ube.salinlahifour.enumTypes.StarType;

public class ProgressListItems {
	private String lessonName = "";
	private String easyStar;
	private String medStar;
	private String hardStar;

	private String itemName = "";
	private int progress = -1;
	private String progressBarLabel = "";
	
	private boolean isLessonCategory;
	
	public boolean isLessonCategory() {
		return isLessonCategory;
	}
	public void setLessonCategory(boolean isLessonCategory) {
		this.isLessonCategory = isLessonCategory;
	}
	
	public String getLessonName() {
		return lessonName;
	}
	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}
	public int getEasyStarDrawableId() {
		return getDrawableOfStar(easyStar);
	}
	public void setEasyStar(String easyStar) {
		this.easyStar = easyStar;
	}
	public int getMedStarDrawableId() {
		return getDrawableOfStar(medStar);
	}
	public void setMedStar(String medStar) {
		this.medStar = medStar;
	}
	public int getHardStarDrawableId() {
		return getDrawableOfStar(hardStar);
	}
	public void setHardStar(String hardStar) {
		this.hardStar = hardStar;
	}
	private int getDrawableOfStar(String star){
		if(star != null){
			switch(star){
				case "GOLD":
					return R.drawable.map_star_gold;
				case "SILVER":
					return R.drawable.map_star_silver;
				case "BRONZE":
					return R.drawable.map_star_bronze;
			}
		}
		return R.drawable.map_star_null;
	}
	

	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getProgress() {
		return progress;
	}
	public void setProgress(int progress) {
		this.progress = progress;
	}
	public String getProgressBarLabel(){
		return progressBarLabel;
	}
	public void setProgressBarLabel(String label){
		progressBarLabel = label;
	}
}
