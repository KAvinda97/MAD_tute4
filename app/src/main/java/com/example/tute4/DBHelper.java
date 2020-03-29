package com.example.tute4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "UserInfo.db";

    public DBHelper(Context context){super(context,DATABASE_NAME,null,1);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES = "CREATE TABLE " +UsersMaster.Users.TABLE_NAME+"("+UsersMaster.Users._ID + "INTEGER PRIMARY KEY," + UsersMaster.Users.COLUMN_NAME_USERNAME+ "TEXT," + UsersMaster.Users.COLUMN_NAME_PASSWORD + "TEXT)";
        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void addInfo(String userName, String password){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UsersMaster.Users.COLUMN_NAME_USERNAME,userName);
        values.put(UsersMaster.Users.COLUMN_NAME_PASSWORD,password);

        long newRowId = db.insert(UsersMaster.Users.TABLE_NAME,null,values);
    }

    public void readAllInfo(){
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {UsersMaster.Users._ID, UsersMaster.Users.COLUMN_NAME_USERNAME, UsersMaster.Users.COLUMN_NAME_PASSWORD};
        String sortOrder = UsersMaster.Users.COLUMN_NAME_USERNAME + "DESC";

        Cursor  cursor = db.query(UsersMaster.Users.TABLE_NAME,projection,null,null,null,null,sortOrder);

        List userNames = new ArrayList<>();
        List passwords = new ArrayList<>();

        while (cursor.moveToNext()){
            String username = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Users.COLUMN_NAME_USERNAME));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Users.COLUMN_NAME_PASSWORD));
            userNames.add(username);
            passwords.add(password);
        }

    }
