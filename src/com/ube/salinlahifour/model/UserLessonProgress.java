package com.ube.salinlahifour.model;

import com.ube.salinlahifour.enumTypes.StarType;

public class UserLessonProgress {
	private int id;
	private int userID;
	private String lessonName;
	private String easyStar; 
	private String mediumStar; 
	private String hardStar; 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getLessonName() {
		return lessonName;
	}
	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}
	public String getEasyStar() {
		return easyStar;
	}
	public void setEasyStar(String easyStar) {
		this.easyStar = easyStar;
	}
	public String getMediumStar() {
		return mediumStar;
	}
	public void setMediumStar(String mediumStar) {
		this.mediumStar = mediumStar;
	}
	public String getHardStar() {
		return hardStar;
	}
	public void setHardStar(String hardStar) {
		this.hardStar = hardStar;
	}

}
