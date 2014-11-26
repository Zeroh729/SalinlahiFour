package com.ube.salinlahifour.database;

import java.util.ArrayList;

import com.ube.salinlahifour.model.UserLessonProgress;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class UserLessonProgressOperations {
	private DatabaseHandler dbHandler;
	private String[] USERLESSONPROGRESS_TABLE_COLUMNS = {dbHandler.USERLESSONPROGRESS_ID, dbHandler.USERLESSONPROGRESS_USERID, dbHandler.USERLESSONPROGRESS_LESSONNAME, dbHandler.USERLESSONPROGRESS_EASYSTAR, dbHandler.USERLESSONPROGRESS_MEDIUMSTAR, dbHandler.USERLESSONPROGRESS_HARDSTAR};
	
	private SQLiteDatabase database;
	
	public UserLessonProgressOperations(Context context){
		dbHandler = new DatabaseHandler(context);
	}
	
	public void open() throws SQLException{
		database = dbHandler.getWritableDatabase();
	}
	
	public void close(){
		dbHandler.close();
	}
	
	
	
	public UserLessonProgress addUserLessonProgress(int userID, String lessonName, String easyStar, String medStar, String hardStar){ //add parameter for each attribute, id not included
		ContentValues value = new ContentValues();
		
		//for each attribute
		value.put(dbHandler.USERLESSONPROGRESS_USERID, userID);
		value.put(dbHandler.USERLESSONPROGRESS_LESSONNAME, lessonName);
		value.put(dbHandler.USERLESSONPROGRESS_EASYSTAR, easyStar);
		value.put(dbHandler.USERLESSONPROGRESS_MEDIUMSTAR, medStar);
		value.put(dbHandler.USERLESSONPROGRESS_HARDSTAR, hardStar);
		

		long userLessonProgressID = database.insert(dbHandler.TABLE_USERLESSONPROGRESS, null, value);
		
		UserLessonProgress latestProgress = getUserLessonProgress(userLessonProgressID, lessonName);
		
//		//debugging
//				Toast toast = Toast.makeText(dbHandler.getContext(), "Added Progress: lessonName-" + latestProgress.getLessonName() + " E-" + latestProgress.getEasyStar() + " M-" + latestProgress.getMediumStar()+ " H-" + latestProgress.getHardStar(), Toast.LENGTH_LONG);
//				toast.show();
				
		return getUserLessonProgress(userLessonProgressID, lessonName);
	}
	
	public ArrayList<UserLessonProgress> getAllUserLessonProgress(){
		ArrayList<UserLessonProgress> progressList = new ArrayList<UserLessonProgress>();
		Cursor cursor = database.query(dbHandler.TABLE_USERLESSONPROGRESS, 
				USERLESSONPROGRESS_TABLE_COLUMNS,
				null, null, null, null, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			UserLessonProgress student = parseUserLessonProgress(cursor);
			progressList.add(student);
			cursor.moveToNext();
		}
		cursor.close();
		return progressList;
	}
	
	public ArrayList<UserLessonProgress> getAllUserLessonProgressOfUser(int userID){
		ArrayList<UserLessonProgress> progressList = new ArrayList<UserLessonProgress>();
		Cursor cursor = database.query(dbHandler.TABLE_USERLESSONPROGRESS, 
				USERLESSONPROGRESS_TABLE_COLUMNS,
				dbHandler.USERLESSONPROGRESS_USERID + "="+ userID, null, null, null, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			UserLessonProgress student = parseUserLessonProgress(cursor);
			progressList.add(student);
			cursor.moveToNext();
		}
		cursor.close();
		return progressList;
	}
	
	public void updateUserLessonProgress(int userID, String lessonName, String easyStar, String medStar, String hardStar) {
	    ContentValues values = new ContentValues();
	    
	    values.put(dbHandler.USERLESSONPROGRESS_EASYSTAR, easyStar);
	    values.put(dbHandler.USERLESSONPROGRESS_MEDIUMSTAR, medStar);
	    values.put(dbHandler.USERLESSONPROGRESS_HARDSTAR, hardStar);
	 	 
	    database.update(dbHandler.TABLE_USERLESSONPROGRESS,
	        values,
	        dbHandler.USERLESSONPROGRESS_USERID +"="+userID+
	        " AND " + dbHandler.USERLESSONPROGRESS_LESSONNAME + "='" + lessonName+"'",
	        null);
	    
	    //debugging
//				UserLessonProgress latestProgress = getAllUserLessonProgressOfUser(userID).get(0);		
//				Toast toast = Toast.makeText(dbHandler.getContext(), "Updated Progress(0): lessonName-" + latestProgress.getLessonName() + " E-" + latestProgress.getEasyStar() + " M-" + latestProgress.getMediumStar()+ " H-" + latestProgress.getHardStar(), Toast.LENGTH_LONG);
//				toast.show();
	}
	
	public void deleteStudent(long id){
		database.delete(dbHandler.TABLE_USERLESSONPROGRESS,
				dbHandler.USERLESSONPROGRESS_ID + " = " + id, null);
	}
	
	private UserLessonProgress parseUserLessonProgress(Cursor cursor){
		UserLessonProgress userLessonProgress = new UserLessonProgress();
		
		userLessonProgress.setId(cursor.getInt(0));		//for each attribute
		userLessonProgress.setUserID(cursor.getInt(1));
		userLessonProgress.setLessonName(cursor.getString(2));
		userLessonProgress.setEasyStar(cursor.getString(3));
		userLessonProgress.setMediumStar(cursor.getString(4));
		userLessonProgress.setHardStar(cursor.getString(5));
		return userLessonProgress;
	}	
	
	public UserLessonProgress getUserLessonProgress(long id, String lessonName){
		try{
			Cursor cursor = database.query(dbHandler.TABLE_USERLESSONPROGRESS, 
				USERLESSONPROGRESS_TABLE_COLUMNS,
				dbHandler.USERLESSONPROGRESS_USERID + " = " + id + " AND " + dbHandler.USERLESSONPROGRESS_LESSONNAME + " = '" + lessonName + "'", null, null, null, null);

			cursor.moveToFirst();
			UserLessonProgress latestProgress = parseUserLessonProgress(cursor);
			cursor.close();
		
		    //debugging
//					Toast toast = Toast.makeText(dbHandler.getContext(), "Record Progress: lessonName-" + latestProgress.getLessonName() + " E-" + latestProgress.getEasyStar() + " M-" + latestProgress.getMediumStar()+ " H-" + latestProgress.getHardStar(), Toast.LENGTH_LONG);
//					toast.show();
					
			return latestProgress;

		}catch(Exception e){
			return null;
		}
	}
}
