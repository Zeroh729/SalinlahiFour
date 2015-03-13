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
						items.add(new Item(5,"Kayumanggi" , "Brown", "", 0, R.raw.color_kayumanggi, 0, LevelType.HARD));
						items.add(new Item(6,"Lila" , "Purple", "", 0, R.drawable.cooking_star, 0, LevelType.HARD));
					case "MEDIUM":			
						items.add(new Item(7, "Itim" , "Black", "", 0, R.raw.color_itim, R.raw.color_black, LevelType.MEDIUM));
						items.add(new Item(8,"Puti" , "White", "", 0, R.raw.color_puti, R.raw.color_white, LevelType.MEDIUM));
					case "EASY":	
						items.add(new Item(1,"Asul" , "Blue", "", 0,R.raw.color_asul, R.raw.color_blue, LevelType.EASY));
						items.add(new Item(2,"Berde" , "Green", "", 0,R.raw.color_berde, R.raw.color_green, LevelType.EASY));
						items.add(new Item(3,"Pula" , "Red", "", 0, R.raw.color_pula, R.raw.color_red, LevelType.EASY));
						items.add(new Item(4,"Dilaw" , "Yellow", "", 0, R.raw.color_dilao, R.raw.color_yellow, LevelType.EASY));
				}
				break;
			case "com.ube.salinlahifour.lessonActivities.Family":
				switch(activityLevel){
					case "HARD":
						items.add(new Item(8,"Tito" , "Uncle", "Can you find <font color=#299190>tito</font>?", R.drawable.family_tito, R.raw.family_tito, R.raw.family_uncle, LevelType.HARD));
						items.add(new Item(9,"Tita" , "Auntie", "Can you find <font color=#299190>tita</font>?", R.drawable.family_tita, R.raw.family_tita, R.raw.family_aunt, LevelType.HARD));
					case "MEDIUM":
						items.add(new Item(5,"Lolo" , "Grandfather", "Can you <font color=#299190>find lolo</font>?", R.drawable.family_lolo, R.raw.family_lolo, R.raw.family_grandfather, LevelType.MEDIUM));
						items.add(new Item(6,"Lola" , "Grandmother", "Can you <font color=#299190>find Lola</font>?", R.drawable.family_lola, R.raw.family_lola, R.raw.family_grandmother, LevelType.MEDIUM));
						items.add(new Item(7,"Bunso" , "Youngest", "Can you <font color=#299190>find bunso</font>?", R.drawable.family_bunso, R.raw.family_bunso, R.raw.family_youngestsibling, LevelType.MEDIUM));
					case "EASY":
						items.add(new Item(1,"Nanay" , "Mother", "Can you find <font color=#299190>nanay</font>?", R.drawable.family_nanay, R.raw.family_nanay, R.raw.family_mother, LevelType.EASY));
						items.add(new Item(2,"Tatay" , "Father", "Can you find <font color=#299190>tatay</font>?", R.drawable.family_tatay, R.raw.family_tatay, R.raw.family_father, LevelType.EASY));
						items.add(new Item(3,"Kuya" , "Brother", "Can you find <font color=#299190>kuya</font>?", R.drawable.family_kuya, R.raw.family_kuya, R.raw.family_olderbrother, LevelType.EASY));
						items.add(new Item(4,"ate" , "Sister", "Can you find <font color=#299190>ate</font>?", R.drawable.family_ate, R.raw.family_ate, R.raw.family_oldersister, LevelType.EASY));
				}	
				break;
			case "com.ube.salinlahifour.lessonActivities.House":
				switch(activityLevel){
					case "HARD":
						items.add(new Item(7,"Garahe" , "Garage", "Where is garahe?", R.drawable.house_pink,  0, 0, LevelType.HARD));
						items.add(new Item(8,"Bakuran" , "fence", "Where is bakuran?", R.drawable.house_blue, 0, 0, LevelType.HARD));
						
					case "MEDIUM":
						items.add(new Item(5,"Garahe" , "Garage", "Where is garahe?", R.drawable.house_pink, R.raw.house_garahe, R.raw.house_garage, LevelType.MEDIUM));
						items.add(new Item(6,"Bakuran" , "fence", "Where is bakuran?", R.drawable.house_blue, R.raw.house_bakod, R.raw.house_fence, LevelType.MEDIUM));
						
					case "EASY":
						items.add(new Item(1,"Bubong" , "Roof", "Where is Bubong?", R.drawable.roof_selected, R.raw.house_bubong, R.raw.house_roof, LevelType.EASY));
						items.add(new Item(2,"Dingding" , "Wall", "Where is Dingding?", R.drawable.body_selected, R.raw.house_dingding, R.raw.house_walls, LevelType.EASY));
						items.add(new Item(3,"Pinto" , "Door", "Where is Pinto?", R.drawable.door_selected, R.raw.house_pinto, R.raw.house_door, LevelType.EASY));
						items.add(new Item(4,"Bintana" , "Window", "Where is Bintana?", R.drawable.window_selected, R.raw.house_bintana, R.raw.house_windows, LevelType.EASY));
					
							}
				break;
			case "com.ube.salinlahifour.lessonActivities.Music":
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
