package database;

import java.util.ArrayList;

import model.UserDetail;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class UserDetailOperations {
	private DatabaseHandler dbHandler;
	private String[] USERDETAIL_TABLE_COLUMNS = {dbHandler.USERDETAIL_ID, dbHandler.USERDETAIL_NAME, dbHandler.USERDETAIL_GENDER, dbHandler.USERDETAIL_DATECREATED};

	private SQLiteDatabase database;
	
	public UserDetailOperations(Context context){
		dbHandler = new DatabaseHandler(context);
	}
	
	public void open() throws SQLException{
		database = dbHandler.getWritableDatabase();
	}
	
	public void close(){
		dbHandler.close();
	}
	
	public UserDetail addUserDetail(String name, String gender, String dateCreated){
		ContentValues values = new ContentValues();
		values.put(dbHandler.USERDETAIL_NAME, name);
		values.put(dbHandler.USERDETAIL_GENDER, gender);
		values.put(dbHandler.USERDETAIL_DATECREATED, dateCreated);
		
		long userID = database.insert(dbHandler.TABLE_USERDETAIL, null, values);

		UserDetail userDetail = getLatestUserDetail(userID);
		
		//debugging
				name = userDetail.getName();
				Toast toast = Toast.makeText(dbHandler.getContext(), "Latest User: " + name, Toast.LENGTH_SHORT);
				toast.show();
				
		return userDetail;
	}
	
	public UserDetail getLatestUserDetail(long id){
		Cursor cursor = database.query(dbHandler.TABLE_USERDETAIL, USERDETAIL_TABLE_COLUMNS, dbHandler.USERDETAIL_ID + " = " + id, null, null, null, null);
		cursor.moveToFirst();
		
		UserDetail latestUserDetail = parseUserDetail(cursor);
		cursor.close();
		

		//debugging
				String name = latestUserDetail.getName();
				Toast toast = Toast.makeText(dbHandler.getContext(), "Latest User: " + name, Toast.LENGTH_SHORT);
				toast.show();
				
		return latestUserDetail;
	}
	
	public ArrayList<UserDetail> getAllUserDetails(){
		ArrayList userDetailList = new ArrayList();
		
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
				Toast toast = Toast.makeText(dbHandler.getContext(), "Users got: " + num, Toast.LENGTH_SHORT);
				toast.show();
				
		
		return userDetailList;
	}
	
	public UserDetail getUserDetail(long id){
		UserDetail userDetail;
		
		Cursor cursor = database.query(dbHandler.TABLE_USERDETAIL, USERDETAIL_TABLE_COLUMNS, dbHandler.USERDETAIL_ID + " = " + id, null, null, null, null);
		cursor.moveToFirst();
		
		userDetail = parseUserDetail(cursor);
		cursor.close();

		//debugging
		String name = userDetail.getName();
		Toast toast = Toast.makeText(dbHandler.getContext(), "User got: " + name, Toast.LENGTH_SHORT);
		toast.show();
		
		return userDetail;
	}
	
	public void deleteUserDetail(UserDetail userDetail){
		long id = userDetail.getId();

		//debugging
		String name = userDetail.getName();
		Toast toast = Toast.makeText(dbHandler.getContext(), "User vanised: " + name, Toast.LENGTH_SHORT);
		toast.show();
		
		database.delete(dbHandler.TABLE_USERDETAIL, dbHandler.USERDETAIL_ID + " = " + id, null);
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
