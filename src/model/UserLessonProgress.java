package model;

import enumTypes.StarType;

public class UserLessonProgress {
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
	public StarType getEasyStar() {
		return easyStar;
	}
	public void setEasyStar(StarType easyStar) {
		this.easyStar = easyStar;
	}
	public StarType getMediumStar() {
		return mediumStar;
	}
	public void setMediumStar(StarType mediumStar) {
		this.mediumStar = mediumStar;
	}
	public StarType getHardStar() {
		return hardStar;
	}
	public void setHardStar(StarType hardStar) {
		this.hardStar = hardStar;
	}
	private int id;
	private int userID;
	private String lessonName;
	private StarType easyStar; 
	private StarType mediumStar; 
	private StarType hardStar; 
}
