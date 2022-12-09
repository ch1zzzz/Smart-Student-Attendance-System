package com.example.ssas_project;

import android.content.ContentValues;
import android.content.Context;
//import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ClassDatabase extends SQLiteOpenHelper {
    private static final String Database_name = "SSAS_Class_database";
    private static final String Class_table = "Class_table";

    public ClassDatabase(Context context) {
        super(context, Database_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase CreateClassDatabase) {
        //Create tables
        String table1 = "Create Table " + Class_table + "(Class_Name TEXT primary key, Class_Time TEXT, Length_of_Class TEXT, Days TEXT)";
        CreateClassDatabase.execSQL(table1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase CreateClassDatabase, int i, int i1) {
        CreateClassDatabase.execSQL("Drop Table If Exists " + Class_table);
        onCreate(CreateClassDatabase);
    }

    //Insert Class Information
    public Boolean insertClassInfo(String Class_Name, String Class_Time, String Length_of_Class, String Days) {
        SQLiteDatabase CreateClassDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("Class Name", Class_Name);
        contentValues.put("Class Time", Class_Time);
        contentValues.put("Length of class", Length_of_Class);
        contentValues.put("What days is your class hosted?", Days);

        CreateClassDatabase.insert(Class_table, null, contentValues);

        return true;
    }
}


