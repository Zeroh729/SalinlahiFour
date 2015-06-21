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
					question[8]="We need Tunod to that can shoot 3 at the same time";
					sEnemy[9] = "Gasuklay";
					question[9]="The gasuklay button can reach that";
					sEnemy[10] = "Puso";
					question[10]="Push the puso button to hea!";
		case "MEDIUM":
					sEnemy[5] = "Krus";
					question[5]="We need Krus to shoot in a wide area";
					sEnemy[6] = "Diamante";
					question[6]="We need the Diamante button to shield ourselves";
					sEnemy[7] = "Parihaba";
					question[7]="We need Parihaba that shoots from the side";
		case "EASY":
			sEnemy[0] = "Bilog";
			question[0]="We need Bilog to hit red enemies";
			sEnemy[1] = "Bilog";
			question[1]="We need Bilog to hit red enemies";
			sEnemy[2] = "Parisukat";
			question[2]="We just need a Parisukat to shoot one bullet at him";
			sEnemy[3] = "Bituin";
			question[3]="We need Bituin to hit a lot of enemies";
			sEnemy[4] = "Tatsulok";
			question[4]="We need Tatsulok that hits 3 enemies";
		}
	}
	public String getEnemy(int index){
		return sEnemy[index];
	}
	public String getEnemyQuestion(int index){
		return question[index];
	}
	
	
	
}
