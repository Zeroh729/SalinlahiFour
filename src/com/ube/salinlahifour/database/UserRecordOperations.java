package com.ube.salinlahifour.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.ube.salinlahifour.SalinlahiFour;
import com.ube.salinlahifour.model.UserRecord;
import com.ube.salinlahifour.tools.DateTimeConverter;

public class UserRecordOperations {
	private DatabaseHandler dbHandler;
	private String[] USERRECORD_TABLE_COLUMNS = {dbHandler.USERRECORD_ID, dbHandler.USERRECORD_USERID, dbHandler.USERRECORD_LESSONNAME, dbHandler.USERRECORD_CORRECTANSWER, dbHandler.USERRECORD_STATUS, dbHandler.USERRECORD_DATECREATED};

	private SQLiteDatabase database;

	public UserRecordOperations(Context context){
		dbHandler = new DatabaseHandler(context);
	}
	
	public void open() throws SQLException{
		database = dbHandler.getWritableDatabase();
	}
	
	public void close(){
		dbHandler.close();
	}
	public UserRecord addUserRecord(int userID, String lessonName, String correctAnswer, String status){
		ContentValues values = new ContentValues();
		values.put(dbHandler.USERRECORD_USERID, userID);
		values.put(dbHandler.USERRECORD_LESSONNAME, lessonName);
		values.put(dbHandler.USERRECORD_CORRECTANSWER, correctAnswer);
		values.put(dbHandler.USERRECORD_STATUS, status);
		values.put(dbHandler.USERRECORD_DATECREATED, DateTimeConverter.getCurrentDateTime());
		
		
		long id = database.insert(dbHandler.TABLE_USERDETAIL, null, values);

		UserRecord latestUserRecord = getUserRecord(id);
		
		//debugging
				Toast toast = Toast.makeText(dbHandler.getContext(), "Latest Record: userid-" + latestUserRecord.getUserID() + " status-" + latestUserRecord.getStatus() + " datetime-" + latestUserRecord.getDateCreated(), Toast.LENGTH_LONG);
				toast.show();
				
		return latestUserRecord;
	}
	
	
	public UserRecord getUserRecord(long id){
		Cursor cursor = database.query(dbHandler.TABLE_USERRECORD, USERRECORD_TABLE_COLUMNS, dbHandler.USERDETAIL_ID + " = " + id, null, null, null, null);
		cursor.moveToFirst();
		
		UserRecord latestUserRecord = parseUserRecord(cursor);
		cursor.close();
		

		//debugging
				Toast toast = Toast.makeText(dbHandler.getContext(), "Latest Record: userid-" + latestUserRecord.getUserID() + " status-" + latestUserRecord.getStatus() + " datetime-" + latestUserRecord.getDateCreated(), Toast.LENGTH_LONG);
				toast.show();
				
		return latestUserRecord;
	}
	
	private UserRecord parseUserRecord(Cursor cursor){
		UserRecord userRecord = new UserRecord();
		
		userRecord.setId(cursor.getInt(0));		//for each attribute
		userRecord.setUserID(cursor.getInt(1));
		userRecord.setLessonName(cursor.getString(2));
		userRecord.setCorrectAnswer(cursor.getString(3));
		userRecord.setStatus(cursor.getString(4));
		userRecord.setDateCreated(cursor.getString(5));
		return userRecord;
	}	
	
	public ArrayList<UserRecord> getAllUserRecords(){
		ArrayList<UserRecord> userRecordList = new ArrayList<UserRecord>();
		Cursor cursor = database.query(dbHandler.TABLE_USERRECORD, 
				USERRECORD_TABLE_COLUMNS,
				null, null, null, null, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			UserRecord student = parseUserRecord(cursor);
			userRecordList.add(student);
			cursor.moveToNext();
		}
		cursor.close();
		return userRecordList;
	}
	
	public UserRecord getAllUserRecordsFromUserId(long userId){
		Cursor cursor = database.query(dbHandler.TABLE_USERRECORD, 
				USERRECORD_TABLE_COLUMNS,
				dbHandler.USERRECORD_USERID + " = " + userId, null, null, null, null);
		cursor.moveToFirst();

		UserRecord userRecord = parseUserRecord(cursor);
		cursor.close();

		return userRecord;
	}	
	
	//FIX THIS: should return records made within 30 days
	public UserRecord getRecentUserRecordsFromUserId(long userId){
		Cursor cursor = database.query(dbHandler.TABLE_USERRECORD, 
				USERRECORD_TABLE_COLUMNS,
				dbHandler.USERRECORD_USERID + " = " + userId, null, null, null, null);
		cursor.moveToFirst();

		UserRecord userRecord = parseUserRecord(cursor);
		cursor.close();

		return userRecord;
	}
	
}
