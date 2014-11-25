package com.ube.salinlahifour;

import java.util.ArrayList;

import android.app.Activity;
import android.util.Log;

import com.ube.salinlahifour.enumTypes.LevelType;

public abstract class LessonItemLoader {
	public static ArrayList<Item> getLessonItems(Activity act, String activityName, String activityLevel){
		ArrayList<Item> items = new ArrayList();
		
		Log.d(activityName, "TESTTEST");
		switch(activityName){
			case "com.ube.salinlahifour.lessonActivities.Cooking":
				switch(activityLevel){
					case "HARD":
						items.add(new Item("Puso" , "Heart", "Which one is Puso", R.drawable.cooking_heart, null, LevelType.HARD));
						items.add(new Item("Linya" , "Line", "Which one is Linya", R.drawable.cooking_circle, null, LevelType.HARD));
					case "MEDIUM":			
						items.add(new Item("Parihaba" , "Rectangle", "Which one is Parihaba", R.drawable.cooking_rectangle, null, LevelType.MEDIUM));
						items.add(new Item("Bituin" , "Star", "Which one is Bituin", R.drawable.cooking_star, null, LevelType.MEDIUM));
					case "EASY":	
						items.add(new Item("Tatsulok" , "Triangle", "Which one is Tatsulok", R.drawable.cooking_triangle, null, LevelType.EASY));
						items.add(new Item("Bilog" , "Circle", "Which one is Bilog", R.drawable.cooking_circle, null, LevelType.EASY));
						items.add(new Item("Parisukat" , "Square", "Which one is Parisukat", R.drawable.cooking_square, null, LevelType.EASY));
				}
				break;
			case "com.ube.salinlahifour.lessonActivities.Family":
				switch(activityLevel){
					case "HARD":
						items.add(new Item("Nanay" , "Mother", "Can you find nanay?", R.drawable.family_nanay, null, LevelType.HARD));
						items.add(new Item("Tatay" , "Father", "Can you find tatay?", R.drawable.family_tatay, null, LevelType.HARD));
						items.add(new Item("Kuya" , "Brother", "Can you find kuya?", R.drawable.family_kuya, null, LevelType.HARD));
						items.add(new Item("ate" , "Sister", "Can you find ate?", R.drawable.family_ate, null, LevelType.HARD));
						
						items.add(new Item("Lolo" , "Grandfather", "Can you find lolo?", R.drawable.family_lolo, null, LevelType.HARD));
						items.add(new Item("Lola" , "Grandmother", "Can you find Lola?", R.drawable.family_lola, null, LevelType.HARD));
						items.add(new Item("Bunso" , "Youngest", "Can you find bunso?", R.drawable.family_bunso, null, LevelType.HARD));
						
						items.add(new Item("Tito" , "Uncle", "Can you find tito?", R.drawable.family_tito, null, LevelType.HARD));
						items.add(new Item("Tita" , "Auntie", "Can you find tita?", R.drawable.family_tita, null, LevelType.HARD)); break;
					case "MEDIUM":
						items.add(new Item("Nanay" , "Mother", "Can you find nanay?", R.drawable.family_nanay, null, LevelType.MEDIUM));
						items.add(new Item("Tatay" , "Father", "Can you find tatay?", R.drawable.family_tatay, null, LevelType.MEDIUM));
						items.add(new Item("Kuya" , "Brother", "Can you find kuya?", R.drawable.family_kuya, null, LevelType.MEDIUM));
						items.add(new Item("ate" , "Sister", "Can you find ate?", R.drawable.family_ate, null, LevelType.MEDIUM));
						items.add(new Item("Lolo" , "Grandfather", "Can you find lolo?", R.drawable.family_lolo, null, LevelType.MEDIUM));
						items.add(new Item("Lola" , "Grandmother", "Can you find Lola?", R.drawable.family_lola, null, LevelType.MEDIUM));
						items.add(new Item("Bunso" , "Youngest", "Can you find bunso?", R.drawable.family_bunso, null, LevelType.MEDIUM));
						break;
						
					case "EASY":
						items.add(new Item("Nanay" , "Mother", "Can you find nanay?", R.drawable.family_nanay, null, LevelType.EASY));
						items.add(new Item("Tatay" , "Father", "Can you find tatay?", R.drawable.family_tatay, null, LevelType.EASY));
						items.add(new Item("Kuya" , "Brother", "Can you find kuya?", R.drawable.family_kuya, null, LevelType.EASY));
						items.add(new Item("ate" , "Sister", "Can you find ate?", R.drawable.family_ate, null, LevelType.EASY));
						break;
				}	
				break;
			case "com.ube.salinlahifour.lessonActivities.House":
				switch(activityLevel){
					case "HARD":
					case "MEDIUM":
					case "EASY":
						items.add(new Item("Upuan" , "Chair", "Get the Upuan?", R.drawable.family_lolo, null, LevelType.EASY));
						items.add(new Item("Bola" , "Ball", "Get the Bola", R.drawable.family_lola, null, LevelType.EASY));
						items.add(new Item("Kama" , "Bed", "Get the Kama", R.drawable.family_nanay, null, LevelType.EASY));
						items.add(new Item("Unan" , "Pillow", "Get the Unan", R.drawable.family_tatay, null, LevelType.EASY));
						items.add(new Item("Inidoro" , "Toilet", "Get the Inidoro", R.drawable.family_kuya, null, LevelType.EASY));
						items.add(new Item("Sapatos" , "Shoes", "Get the Sapatos", R.drawable.family_ate, null, LevelType.EASY));
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
				}
			default:
				switch(activityLevel){
					case "HARD":
						items.add(new Item("Sampu" , "Ten", "Which is Ten?", R.drawable.placeholder_lesson, null, LevelType.HARD));
						items.add(new Item("Siyam" , "Nine", "Which is Nine?", R.drawable.placeholder_lesson, null, LevelType.HARD));
						items.add(new Item("Walo" , "Eight", "Which is Eight?", R.drawable.placeholder_lesson, null, LevelType.HARD));
					case "MEDIUM":
						items.add(new Item("Pito" , "Seven", "Which is Seven?", R.drawable.placeholder_lesson, null, LevelType.MEDIUM));
						items.add(new Item("Anim" , "Six", "Which is Six?", R.drawable.placeholder_lesson, null, LevelType.MEDIUM));
						items.add(new Item("Lima" , "Five", "Which is Five?", R.drawable.placeholder_lesson, null, LevelType.MEDIUM));
					case "EASY":
						items.add(new Item("Apat" , "Four", "Which is Four?", R.drawable.placeholder_lesson, null, LevelType.EASY));
						items.add(new Item("Tatlo" , "Three", "Which is Three?", R.drawable.placeholder_lesson, null, LevelType.EASY));
						items.add(new Item("Dalawa" , "Two", "Which is Two?", R.drawable.placeholder_lesson, null, LevelType.EASY));
						items.add(new Item("Isa" , "One", "Which is Isa?", R.drawable.placeholder_lesson, null, LevelType.EASY));
				}
		}
		
		((SalinlahiFour)act.getApplication()).setLessonItems(items);
		return ((SalinlahiFour)act.getApplication()).getLessonItems();
	}
}
