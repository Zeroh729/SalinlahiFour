package com.ube.salinlahifour;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.Log;

import com.ube.salinlahifour.enumTypes.LevelType;

public abstract class LessonItemLoader {
	private static String error = "";
	
	public static ArrayList<Item> getLessonItems(Activity act, String activityName, String activityLevel){
		ArrayList<Item> items = new ArrayList();
		
		Log.d(activityName, "TESTTEST");
		switch(activityName){
			case "com.ube.salinlahifour.lessonActivities.Cooking":
				switch(activityLevel){
					case "HARD":
						items.add(new Item(1, "Puso" , "Heart", "Which one is Puso", R.drawable.cooking_heart, null, LevelType.HARD));
						items.add(new Item(2,"Linya" , "Line", "Which one is Linya", R.drawable.cooking_circle, null, LevelType.HARD));
					case "MEDIUM":			
						items.add(new Item(3,"Parihaba" , "Rectangle", "Which one is Parihaba", R.drawable.cooking_rectangle, null, LevelType.MEDIUM));
						items.add(new Item(4,"Bituin" , "Star", "Which one is Bituin", R.drawable.cooking_star, null, LevelType.MEDIUM));
					case "EASY":	
						items.add(new Item(5,"Tatsulok" , "Triangle", "Which one is Tatsulok", R.drawable.cooking_triangle, null, LevelType.EASY));
						items.add(new Item(6,"Bilog" , "Circle", "Which one is Bilog", R.drawable.cooking_circle, null, LevelType.EASY));
						items.add(new Item(7,"Parisukat" , "Square", "Which one is Parisukat", R.drawable.cooking_square, null, LevelType.EASY));
				}
				break;
			case "com.ube.salinlahifour.lessonActivities.Family":
				switch(activityLevel){
					case "HARD":
						items.add(new Item(1,"Nanay" , "Mother", "Can you find nanay?", R.drawable.family_nanay, null, LevelType.HARD));
						items.add(new Item(2,"Tatay" , "Father", "Can you find tatay?", R.drawable.family_tatay, null, LevelType.HARD));
						items.add(new Item(3,"Kuya" , "Brother", "Can you find kuya?", R.drawable.family_kuya, null, LevelType.HARD));
						items.add(new Item(4,"ate" , "Sister", "Can you find ate?", R.drawable.family_ate, null, LevelType.HARD));
					
						items.add(new Item(5,"Lolo" , "Grandfather", "Can you find lolo?", R.drawable.family_lolo, null, LevelType.HARD));
						items.add(new Item(6,"Lola" , "Grandmother", "Can you find Lola?", R.drawable.family_lola, null, LevelType.HARD));
						items.add(new Item(7,"Bunso" , "Youngest", "Can you find bunso?", R.drawable.family_bunso, null, LevelType.HARD));
						
						items.add(new Item(8,"Tito" , "Uncle", "Can you find tito?", R.drawable.family_tito, null, LevelType.HARD));
						items.add(new Item(9,"Tita" , "Auntie", "Can you find tita?", R.drawable.family_tita, null, LevelType.HARD)); break;
					case "MEDIUM":
						items.add(new Item(1,"Nanay" , "Mother", "Can you find nanay?", R.drawable.family_nanay, null, LevelType.MEDIUM));
						items.add(new Item(2,"Tatay" , "Father", "Can you find tatay?", R.drawable.family_tatay, null, LevelType.MEDIUM));
						items.add(new Item(3,"Kuya" , "Brother", "Can you find kuya?", R.drawable.family_kuya, null, LevelType.MEDIUM));
						items.add(new Item(4,"ate" , "Sister", "Can you find ate?", R.drawable.family_ate, null, LevelType.MEDIUM));
						items.add(new Item(5,"Lolo" , "Grandfather", "Can you find lolo?", R.drawable.family_lolo, null, LevelType.MEDIUM));
						items.add(new Item(6,"Lola" , "Grandmother", "Can you find Lola?", R.drawable.family_lola, null, LevelType.MEDIUM));
						items.add(new Item(7,"Bunso" , "Youngest", "Can you find bunso?", R.drawable.family_bunso, null, LevelType.MEDIUM));
						break;
						
					case "EASY":
						items.add(new Item(1,"Nanay" , "Mother", "Can you find nanay?", R.drawable.family_nanay, null, LevelType.EASY));
						items.add(new Item(2,"Tatay" , "Father", "Can you find tatay?", R.drawable.family_tatay, null, LevelType.EASY));
						items.add(new Item(3,"Kuya" , "Brother", "Can you find kuya?", R.drawable.family_kuya, null, LevelType.EASY));
						items.add(new Item(4,"ate" , "Sister", "Can you find ate?", R.drawable.family_ate, null, LevelType.EASY));
						break;
				}	
				break;
			case "com.ube.salinlahifour.lessonActivities.House":
				switch(activityLevel){
					case "HARD":
					case "MEDIUM":
					case "EASY":
						items.add(new Item(1,"Tulugan" , "Bedroom", "Where is Tulugan?", R.drawable.house_pink, null, LevelType.EASY));
						items.add(new Item(2,"Paliguan" , "Bathroom", "Where is Paliguan?", R.drawable.house_blue, null, LevelType.EASY));
						items.add(new Item(3,"Salas" , "Livingroom", "Where is salas?", R.drawable.house_skobe, null, LevelType.EASY));
						items.add(new Item(4,"Lutuan" , "Kitchen", "Where is lutuan?", R.drawable.house_yellow, null, LevelType.EASY));
							}
				break;
			case "com.ube.salinlahifour.lessonActivities.Music":
				switch(activityLevel){
					case "HARD":
					case "MEDIUM":
					case "EASY":
				}
				break;
			case "com.ube.salinlahifour.lessonActivities.Society":
				switch(activityLevel){
					case "HARD":
					case "MEDIUM":
					case "EASY":		
						items.add(new Item(1,"Pulis" , "Police", "Which one is police", 0, null, LevelType.EASY));
						items.add(new Item(2,"Bombero" , "Fireman", "Which one is Fireman", 0, null, LevelType.EASY));
						items.add(new Item(3,"Pulis" , "Police", "Which one is police", 0, null, LevelType.EASY));
						items.add(new Item(4,"Bombero" , "Fireman", "Which one is Fireman", 0, null, LevelType.EASY));
						items.add(new Item(5,"Pulis" , "Police", "Which one is police", 0, null, LevelType.EASY));
				}
			default:
				switch(activityLevel){
					case "HARD":
						items.add(new Item(1,"Sampu" , "Ten", "Which is Ten?", R.drawable.placeholder_lesson, null, LevelType.HARD));
						items.add(new Item(2,"Siyam" , "Nine", "Which is Nine?", R.drawable.placeholder_lesson, null, LevelType.HARD));
						items.add(new Item(3,"Walo" , "Eight", "Which is Eight?", R.drawable.placeholder_lesson, null, LevelType.HARD));
					case "MEDIUM":
						items.add(new Item(4,"Pito" , "Seven", "Which is Seven?", R.drawable.placeholder_lesson, null, LevelType.MEDIUM));
						items.add(new Item(5,"Anim" , "Six", "Which is Six?", R.drawable.placeholder_lesson, null, LevelType.MEDIUM));
						items.add(new Item(6,"Lima" , "Five", "Which is Five?", R.drawable.placeholder_lesson, null, LevelType.MEDIUM));
					case "EASY":
						items.add(new Item(1,"Apat" , "Four", "Which is Four?", R.drawable.placeholder_lesson, null, LevelType.EASY));
						items.add(new Item(2,"Tatlo" , "Three", "Which is Three?", R.drawable.placeholder_lesson, null, LevelType.EASY));
						items.add(new Item(3,"Dalawa" , "Two", "Which is Two?", R.drawable.placeholder_lesson, null, LevelType.EASY));
						items.add(new Item(4,"Isa" , "One", "Which is Isa?", R.drawable.placeholder_lesson, null, LevelType.EASY));
				}
		}
		
		int easyItems = 0;
		int mediumItems = 0;
		int hardItems = 0;
		error = "";
			
		for(Item item : items){
			
			if(item.getLevel() == LevelType.EASY)
				easyItems++;
			else if(item.getLevel() == LevelType.MEDIUM)
				mediumItems++;
			else if(item.getLevel() == LevelType.HARD)
				hardItems++;
		}
		switch(activityLevel){
			case "HARD":
				if(hardItems < 2)
					error = "ERROR in LessonItemLoader class -> " + activityName + " : HARD(" + hardItems + ") should have at least 2 items!\n";
			case "MEDIUM":
				if(mediumItems < 2)
					error += "ERROR in LessonItemLoader class -> " + activityName + " : MEDIUM(" + mediumItems + ") should have at least 2 items!\n";
			case "EASY":
				if(easyItems < 3)
					error += "ERROR in LessonItemLoader class -> " + activityName + " : EASY(" + easyItems + ") should have at least 3 items!";
		}
		if(!error.equals("")){
			return null;
		}
		
		((SalinlahiFour)act.getApplication()).setLessonItems(items);
		return ((SalinlahiFour)act.getApplication()).getLessonItems();
	}
	
	public static String getError(){
		return error;
	}
	
}
