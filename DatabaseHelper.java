package com.example.login;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DatabaseHelper extends SQLiteOpenHelper {
private static final String DATABASE_NAME = "UserDB";
private static final int DATABASE_VERSION = 1;
private static final String TABLE_NAME = "users";
private static final String COLUMN_USERNAME = "username";
private static final String COLUMN_PASSWORD = "password";
private static final String CREATE_TABLE =
"CREATE TABLE " + TABLE_NAME + " (" +
COLUMN_USERNAME + " TEXT PRIMARY KEY," +
COLUMN_PASSWORD + " TEXT)";
public DatabaseHelper(Context context) {
super(context, DATABASE_NAME, null, DATABASE_VERSION);
}
@Override
public void onCreate(SQLiteDatabase db) {
db.execSQL(CREATE_TABLE);
}
@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
onCreate(db);
}
public long addUser(User user) {
SQLiteDatabase db = this.getWritableDatabase();
ContentValues values = new ContentValues();
values.put(COLUMN_USERNAME, user.getUsername());
values.put(COLUMN_PASSWORD, user.getPassword());
long result = db.insert(TABLE_NAME, null, values);
db.close();
return result;
}
public boolean checkUser(String username, String password) {
SQLiteDatabase db = this.getReadableDatabase();
String[] columns = {COLUMN_USERNAME};
String selection = COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + "
= ?";
String[] selectionArgs = {username, password};
  Cursor cursor = db.query(
TABLE_NAME
,
columns,
selection,
selectionArgs,
null,
null,
null
);
int cursorCount
= cursor.getCount();
cursor.close();
db.close();
return cursorCount
> 0;
}
}
