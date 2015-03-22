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
					sEnemy[8] = "Tunod";
					question[8]="Its a Arrow Formation!";
					sEnemy[9] = "Gasuklay";
					question[9]="Its a Crecent Formation!";
					sEnemy[10] = "Puso";
					question[10]="Its a Heart Formation!";
		case "MEDIUM":
					sEnemy[5] = "Krus";
					question[5]="Its a Cross Formation!";
					sEnemy[6] = "Diamante";
					question[6]="Its a Diamond Formation!";
					sEnemy[7] = "Parihaba";
					question[7]="Its a Rectangle Formation!";
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
