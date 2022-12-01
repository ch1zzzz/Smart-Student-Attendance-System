package com.example.ssas_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LoginDatabase extends SQLiteOpenHelper {

    private static final String Database_name = "SSAS_database";
    private static final String login_table = "login_table";

    public LoginDatabase(Context context) {
        super(context, Database_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase project_database) {
        //Create tables
        String table1 = "Create Table " +login_table+ "(username TEXT primary key, password TEXT, first_name TEXT, last_name TEXT, email_addr TEXT)";
        project_database.execSQL(table1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase project_database, int i, int i1) {
        project_database.execSQL("Drop Table If Exists " +login_table);
        onCreate(project_database);
    }
    //Insert user profile
    public Boolean insertData(String username, String password, String first_name, String last_name, String email_addr){
        SQLiteDatabase project_database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("first_name", first_name);
        contentValues.put("last_name", last_name);
        contentValues.put("email_addr", email_addr);

        project_database.insert(login_table, null, contentValues);

        return true;
    }

    public Boolean update_username_password(String username, String password ){
        SQLiteDatabase project_database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("password", password);

        project_database.update(login_table, contentValues, "username = ?", new String[]{username});
        return true;
    }

    //Check username
    public Boolean check_username(String username){
        SQLiteDatabase project_database = this.getWritableDatabase();
        Cursor cursor = project_database.rawQuery("Select * from " +login_table+ " where username = ?", new String[]{username});

        if(cursor.getCount() > 0) {
            return true;
        }
        else{
            return false;
        }
    }

    //Check username and password
    public Boolean check_username_password(String username, String password){
        SQLiteDatabase project_database = this.getWritableDatabase();
        Cursor cursor = project_database.rawQuery("Select * from " +login_table+ " where username = ? and password = ?", new String[]{username, password});

        if(cursor.getCount() > 0) {
            return true;
        }
        else{
            return false;
        }
    }
}

