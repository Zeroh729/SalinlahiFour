package com.ube.salinlahifour.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.ube.salinlahifour.model.UserDetail;
import com.ube.salinlahifour.tools.DateTimeConverter;

public class UserDetailOperations {
	private DatabaseHandler dbHandler;
	private String[] USERDETAIL_TABLE_COLUMNS = {dbHandler.USERDETAIL_ID, dbHandler.USERDETAIL_NAME, dbHandler.USERDETAIL_GENDER, dbHandler.USERDETAIL_DATECREATED};
	private Context context;
	
	private SQLiteDatabase database;
	
	public UserDetailOperations(Context context){
		dbHandler = new DatabaseHandler(context);
		this.context = context;
	}
	
	public void open() throws SQLException{
		database = dbHandler.getWritableDatabase();
	}
	
	public void close(){
		dbHandler.close();
	}
	
	public UserDetail addUserDetail(String name, String gender){
		ContentValues values = new ContentValues();
		values.put(dbHandler.USERDETAIL_NAME, name);
		values.put(dbHandler.USERDETAIL_GENDER, gender);
		values.put(dbHandler.USERDETAIL_DATECREATED, DateTimeConverter.getCurrentDateTime());
		
		long userID = database.insert(dbHandler.TABLE_USERDETAIL, null, values);

		UserDetail userDetail = getUserDetail(userID);
		
		//debugging
				name = userDetail.getName();
				Toast toast = Toast.makeText(dbHandler.getContext(), "Latest User: " + name, Toast.LENGTH_SHORT);
				toast.show();
				
		return userDetail;
	}
	
	public ArrayList<UserDetail> getAllUserDetails(){
		ArrayList<UserDetail> userDetailList = new ArrayList();
		
		Cursor cursor = database.query(dbHandler.TABLE_USERDETAIL, USERDETAIL_TABLE_COLUMNS, null, null, null, null, null);
		
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			UserDetail userDetail = parseUserDetail(cursor);
			userDetailList.add(userDetail);
			cursor.moveToNext();
		}
		cursor.close();
		
		//debugging
				int num = userDetailList.size();
				Toast toast = Toast.makeText(dbHandler.getContext(), "Users got: " + num + " with first ID as: " + userDetailList.get(0).getId(), Toast.LENGTH_SHORT);
				toast.show();
				
		
		return userDetailList;
	}
	
	public UserDetail getUserDetail(long id){
		UserDetail userDetail;
		
		Cursor cursor = database.query(dbHandler.TABLE_USERDETAIL, USERDETAIL_TABLE_COLUMNS, dbHandler.USERDETAIL_ID + " = " + id, null, null, null, null);
		
		try{
			if(cursor != null){
				cursor.moveToFirst();
				userDetail = parseUserDetail(cursor);
				cursor.close();
		
				//debugging
					String name = userDetail.getName();
					Toast toast = Toast.makeText(dbHandler.getContext(), "User got: " + name, Toast.LENGTH_SHORT);
					toast.show();
				
				return userDetail;
			}
		}catch(Exception e){
			return null;
		}
		return null;
	}
	
	public void deleteUserDetail(UserDetail userDetail, int loggedInID){
		long id = userDetail.getId();

		//debugging
		String name = userDetail.getName();
		Toast toast = Toast.makeText(dbHandler.getContext(), "User vanised: " + name, Toast.LENGTH_SHORT);
		toast.show();
		
		database.delete(dbHandler.TABLE_USERDETAIL, dbHandler.USERDETAIL_ID + " = " + id, null);
		
		if(id == loggedInID){
			SharedPreferences prefs = context.getSharedPreferences("appData", context.MODE_PRIVATE);
			Editor editor = prefs.edit();
			editor.putInt("lastUserID", -1);
			editor.commit();
		}
	}
	
	private UserDetail parseUserDetail(Cursor cursor){
		UserDetail userDetail = new UserDetail();
		userDetail.setId(cursor.getInt(0));
		userDetail.setName(cursor.getString(1));
		userDetail.setGender(cursor.getString(2));
		userDetail.setDateCreated(cursor.getString(3));
		
		return userDetail;
	}
	
}
