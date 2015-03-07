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
	
	public static ArrayList<Item> getLessonItems(String activityName, String activityLevel){
		ArrayList<Item> items = new ArrayList();

		switch(activityName){
			case "com.ube.salinlahifour.lessonActivities.Cooking":
				switch(activityLevel){
					case "HARD":
						items.add(new Item(7, "Itim" , "Black", "Itim - This is BLACK. it is also the color fot he night sky", R.drawable.black, 0, 0, LevelType.HARD));
						items.add(new Item(8,"Puti" , "White", "Puti - This is WHITE. It is also the color of clouds", R.drawable.white, 0, 0, LevelType.HARD));
					case "MEDIUM":			
						items.add(new Item(5,"Kayumanggi" , "Brown", "Kayumanggi - This is BROWN. It is the color of a trunk of a tree", R.drawable.brown, 0, 0, LevelType.MEDIUM));
						items.add(new Item(6,"Lila" , "Violet", "Lila - This is VIOLET. It is also the color of eggplants", R.drawable.violet, 0, 0, LevelType.MEDIUM));
					case "EASY":	
						items.add(new Item(1,"Asul" , "Blue", "Asul - This is BLUE. It is the color of the sky", R.drawable.blue, 0, 0, LevelType.EASY));
						items.add(new Item(2,"Berde" , "Green", "Berde - This is GREEN. It is the color of the grass and leaves", R.drawable.green,0, 0, LevelType.EASY));
						items.add(new Item(3,"Pula" , "Red", "Pula - This is RED. It is the color of an apple", R.drawable.red, 0, 0, LevelType.EASY));
						items.add(new Item(4,"Dilaw" , "Yellow", "Dilaw - This is YELLOW. It is the color of the sun.", R.drawable.yellow, 0, 0, LevelType.EASY));
				}
				break;
			case "com.ube.salinlahifour.lessonActivities.Family":
				switch(activityLevel){
					case "HARD":
						items.add(new Item(8,"Tito" , "Uncle", "Can you find tito?", R.drawable.family_tito, R.raw.family_tito, R.raw.family_uncle, LevelType.HARD));
						items.add(new Item(9,"Tita" , "Auntie", "Can you find tita?", R.drawable.family_tita, R.raw.family_tita, R.raw.family_aunt, LevelType.HARD));
					case "MEDIUM":
						items.add(new Item(5,"Lolo" , "Grandfather", "Can you find lolo?", R.drawable.family_lolo, R.raw.family_lolo, R.raw.family_grandfather, LevelType.MEDIUM));
						items.add(new Item(6,"Lola" , "Grandmother", "Can you find Lola?", R.drawable.family_lola, R.raw.family_lola, R.raw.family_grandmother, LevelType.MEDIUM));
						items.add(new Item(7,"Bunso" , "Youngest", "Can you find bunso?", R.drawable.family_bunso, R.raw.family_bunso, R.raw.family_youngestsibling, LevelType.MEDIUM));
					case "EASY":
						items.add(new Item(1,"Nanay" , "Mother", "Can you find nanay?", R.drawable.family_nanay, R.raw.family_nanay, R.raw.family_mother, LevelType.EASY));
						items.add(new Item(2,"Tatay" , "Father", "Can you find tatay?", R.drawable.family_tatay, R.raw.family_tatay, R.raw.family_father, LevelType.EASY));
						items.add(new Item(3,"Kuya" , "Brother", "Can you find kuya?", R.drawable.family_kuya, R.raw.family_kuya, R.raw.family_olderbrother, LevelType.EASY));
						items.add(new Item(4,"Ate" , "Sister", "Can you find ate?", R.drawable.family_ate, R.raw.family_ate, R.raw.family_oldersister, LevelType.EASY));
				}	
				break;
			case "com.ube.salinlahifour.lessonActivities.House":
				switch(activityLevel){
					case "HARD":
						items.add(new Item(7,"Tsimenea" , "Chimney", "Smoke comes out of here when tita cooks.", R.drawable.chimney,  0, 0, LevelType.HARD));
						items.add(new Item(8,"Hagdanan" , "Stairs", "Makes us go up!", R.drawable.stairs, 0, 0, LevelType.HARD));
						
					case "MEDIUM":
						items.add(new Item(5,"Garahe" , "Garage", "Garahe - This is where tito parks his car.", R.drawable.garage, 0, 0, LevelType.MEDIUM));
						items.add(new Item(6,"Bakuran" , "fence", "Bakuran - This protects our house from strangers", R.drawable.fence, 0, 0, LevelType.MEDIUM));
						
					case "EASY":
						items.add(new Item(1,"Bubong" , "Roof", "Bubong - It covers the top part of the house", R.drawable.roof, 0, 0, LevelType.EASY));
						items.add(new Item(2,"Dingding" , "Wall", "Dingding - Covers the front, side and back parts of the house", R.drawable.wall, 0, 0, LevelType.EASY));
						items.add(new Item(3,"Pinto" , "Door", "Pinto - We open this one to get inside the house", R.drawable.door, 0, 0, LevelType.EASY));
						items.add(new Item(4,"Bintana" , "Window", "Bintana - They are made of glass so that you can look from the inside", R.drawable.window, 0, 0, LevelType.EASY));
					
							}
				break;
			case "com.ube.salinlahifour.lessonActivities.Shape":
				switch(activityLevel){
					case "HARD":
						items.add(new Item(8,"Palaso" , "Arrow", "Palaso - An arrow. It looks like it's pointing to something", R.drawable.arrow, 0, 0, LevelType.HARD));
						items.add(new Item(9,"Gasuklay" , "Crescent", "Gasuklay - Looks like a banana or a fingernail.", R.drawable.cresent, 0, 0, LevelType.HARD));
						items.add(new Item(10,"Puso" , "Heart", "A heart - It's such a LOVELY shape.", R.drawable.heart, 0, 0, LevelType.HARD));

					case "MEDIUM":
						items.add(new Item(5,"Krus" , "Cross", "Krus - It looks like the letter t!", R.drawable.cross, 0, 0, LevelType.MEDIUM));
						items.add(new Item(6,"Diamante" , "Diamond", "Diamante - It's like a square standing on its edge.", R.drawable.diamond, 0, 0, LevelType.MEDIUM));
						items.add(new Item(7,"Parihaba" , "Rectangle", "Parihaba - It looks like a long box.", R.drawable.rectangle, 0, 0, LevelType.MEDIUM));

					case "EASY":
						items.add(new Item(1,"Bilog" , "Circle", "Bilog - It looks like a ball. It also puts a shield around us. Cool!", R.drawable.circle, 0, 0, LevelType.EASY));
						items.add(new Item(2,"Parisukat" , "Square", "Parisukat - It has 4 sides. It also shoots bullets", R.drawable.square, 0, 0, LevelType.EASY));
						items.add(new Item(3,"Bituin" , "Star", "Bituin - Teachers gives these when we do well.", R.drawable.star, 0, 0, LevelType.EASY));
						items.add(new Item(4,"Tatsulok" , "Triangle", "Tatsulok - It has 3 sides.", R.drawable.triangle, 0, 0, LevelType.EASY));
						
				}
				break;
			case "com.ube.salinlahifour.lessonActivities.Society":
				switch(activityLevel){
					case "HARD":
					case "MEDIUM":
					case "EASY":		
						items.add(new Item(1,"Pulis" , "Police", "Which one is police", 0, 0, 0, LevelType.EASY));
						items.add(new Item(2,"Bombero" , "Fireman", "Which one is Fireman", 0, 0, 0, LevelType.EASY));
						items.add(new Item(3,"Pulis" , "Police", "Which one is police", 0, 0, 0, LevelType.EASY));
						items.add(new Item(4,"Bombero" , "Fireman", "Which one is Fireman", 0, 0, 0, LevelType.EASY));
						items.add(new Item(5,"Pulis" , "Police", "Which one is police", 0, 0, 0, LevelType.EASY));
				}
			default:
				switch(activityLevel){
					case "HARD":
						items.add(new Item(1,"Sampu" , "Ten", "Which is Ten?", R.drawable.placeholder_lesson, 0, 0, LevelType.HARD));
						items.add(new Item(2,"Siyam" , "Nine", "Which is Nine?", R.drawable.placeholder_lesson, 0, 0, LevelType.HARD));
						items.add(new Item(3,"Walo" , "Eight", "Which is Eight?", R.drawable.placeholder_lesson, 0, 0, LevelType.HARD));
					case "MEDIUM":
						items.add(new Item(4,"Pito" , "Seven", "Which is Seven?", R.drawable.placeholder_lesson, 0, 0, LevelType.MEDIUM));
						items.add(new Item(5,"Anim" , "Six", "Which is Six?", R.drawable.placeholder_lesson, 0, 0, LevelType.MEDIUM));
						items.add(new Item(6,"Lima" , "Five", "Which is Five?", R.drawable.placeholder_lesson, 0, 0, LevelType.MEDIUM));
					case "EASY":
						items.add(new Item(1,"Apat" , "Four", "Which is Four?", R.drawable.placeholder_lesson, 0, 0, LevelType.EASY));
						items.add(new Item(2,"Tatlo" , "Three", "Which is Three?", R.drawable.placeholder_lesson, 0, 0, LevelType.EASY));
						items.add(new Item(3,"Dalawa" , "Two", "Which is Two?", R.drawable.placeholder_lesson, 0, 0, LevelType.EASY));
						items.add(new Item(4,"Isa" , "One", "Which is Isa?", R.drawable.placeholder_lesson, 0, 0, LevelType.EASY));
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
		
		SalinlahiFour.setLessonItems(items);
		return SalinlahiFour.getLessonItems();
	}
	
	public static String getError(){
		return error;
	}
	
}
