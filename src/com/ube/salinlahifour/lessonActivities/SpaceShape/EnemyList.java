package com.ube.salinlahifour.lessonActivities.SpaceShape;

import java.util.ArrayList;

public class EnemyList {

	private String[] sEnemy;
	private String[] question;
	private String ActivityLevel;
	public EnemyList(String Level){
		ActivityLevel = Level;
		switch(Level){
		case "HARD": sEnemy = new String[10+1];
					question = new String[10+1];break;
		case "MEDIUM": sEnemy = new String[7+1];
				question = new String[7+1];break;
		case "EASY": sEnemy = new String[4+1];
				question = new String[4+1];break;
		
		}
	}
	public void loadEnemy(){
		switch(ActivityLevel){
		case "HARD":
		case "MEDIUM":
		case "EASY":
			sEnemy[0] = "Bilog";
			question[0]="Its a Circle Formation!";
			sEnemy[1] = "Bilog";
			question[1]="Its a Circle Formation!";
			sEnemy[2] = "Parisukat";
			question[2]="Its a Square Formation!";
			sEnemy[3] = "Bituin";
			question[3]="Its a Star Formation!";
			sEnemy[4] = "Tatsulok";
			question[4]="Its a Triangle Formation!";
		}
	}
	public String getEnemy(int index){
		return sEnemy[index];
	}
	public String getEnemyQuestion(int index){
		return question[index];
	}
	
	
	
}
