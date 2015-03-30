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
					question[8]="Arrow - We need something to that can shoot 3 at the same time";
					sEnemy[9] = "Gasuklay";
					question[9]="Cresent - They are too far, what can we do?";
					sEnemy[10] = "Puso";
					question[10]="Heart - Oh no! we're hit, lets heal up!";
		case "MEDIUM":
					sEnemy[5] = "Krus";
					question[5]="Cross - We need to shoot in a wide area";
					sEnemy[6] = "Diamante";
					question[6]="Diamond - We need a barier its a laser";
					sEnemy[7] = "Parihaba";
					question[7]="Rectangle - We need something that shoots from the side";
		case "EASY":
			sEnemy[0] = "Bilog";
			question[0]="Circle - We need something to hit red enemies";
			sEnemy[1] = "Bilog";
			question[1]="Circle We need something to hit red enemies";
			sEnemy[2] = "Parisukat";
			question[2]="Square - We just need to shoot one bullet at him";
			sEnemy[3] = "Bituin";
			question[3]="Star - We need something to hit a lot of enemies";
			sEnemy[4] = "Tatsulok";
			question[4]="Triangle - We need something that hits 3 enemies";
		}
	}
	public String getEnemy(int index){
		return sEnemy[index];
	}
	public String getEnemyQuestion(int index){
		return question[index];
	}
	
	
	
}
