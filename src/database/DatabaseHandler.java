package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "SalinlahiFour0.db";

	public static final String TABLE_USERDETAIL = "userDetail";
    public static final String TABLE_USERRECORD = "userRecord";
	public static final String TABLE_USERLESSONPROGRESS = "userLessonProgress";

	public static final String USERDETAIL_ID = "id";
	public static final String USERDETAIL_NAME = "name";
	public static final String USERDETAIL_GENDER = "gender";
	public static final String USERDETAIL_DATECREATED = "dateCreated";
	
	public static final String USERRECORD_ID = "id";
	public static final String USERRECORD_USERID = "userID";
	public static final String USERRECORD_LESSONNAME = "lessonName";
	public static final String USERRECORD_CORRECTANSWER = "correctAnswer";
	public static final String USERRECORD_STATUS = "status";
	public static final String USERRECORD_DATECREATED = "dateCreated";

	public static final String USERLESSONPROGRESS_ID = "id";
	public static final String USERLESSONPROGRESS_USERID = "userID";
	public static final String USERLESSONPROGRESS_LESSONNAME = "lessonName";
	public static final String USERLESSONPROGRESS_EASYSTAR = "easyStar";
	public static final String USERLESSONPROGRESS_MEDIUMSTAR = "mediumStar";
	public static final String USERLESSONPROGRESS_HARDSTAR = "hardStar";
    
	private Context context;
	
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        
    }

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_TABLE_USERDETAIL = "CREATE TABLE " + TABLE_USERDETAIL + "("
				+ USERDETAIL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ USERDETAIL_NAME + " TEXT,"
				+ USERDETAIL_GENDER + " TEXT,"
				+ USERDETAIL_DATECREATED + " TEXT" + ")";
		db.execSQL(CREATE_TABLE_USERDETAIL);
		
		String CREATE_TABLE_USERLESSONPROGRESS = "CREATE TABLE " + TABLE_USERLESSONPROGRESS + "("
				+ USERLESSONPROGRESS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ USERLESSONPROGRESS_USERID + " INTEGER,"
				+ USERLESSONPROGRESS_LESSONNAME + " TEXT,"
				+ USERLESSONPROGRESS_EASYSTAR + " TEXT,"
				+ USERLESSONPROGRESS_MEDIUMSTAR + " TEXT,"
				+ USERLESSONPROGRESS_HARDSTAR + " TEXT" + ")";
		
		db.execSQL(CREATE_TABLE_USERLESSONPROGRESS);
		
		String CREATE_TABLE_USERRECORD = "CREATE TABLE " + TABLE_USERRECORD + "("
				+ USERRECORD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ USERRECORD_USERID + " INTEGER,"
				+ USERRECORD_LESSONNAME + " TEXT,"
				+ USERRECORD_CORRECTANSWER + " TEXT,"
				+ USERRECORD_STATUS + " TEXT,"
				+ USERRECORD_DATECREATED + " TEXT" + ")";
		db.execSQL(CREATE_TABLE_USERRECORD);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERDETAIL);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERLESSONPROGRESS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERRECORD);
		
		onCreate(db);
	}
	
	public Context getContext(){
		return context;
	}

}
