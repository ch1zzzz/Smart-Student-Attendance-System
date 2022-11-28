package com.example.ssas_project.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.ssas_project.Constants;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.VERSION_CODE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                Constants.TABLE_STUDENT +
                "(_id integer primary key, name varchar, email varchar, status varchar, payment_info varchar)");

        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                Constants.TABLE_COURSE +
                "(_id integer primary key, name varchar, status boolean, numberOfferings integer)");
        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                Constants.TABLE_COURSEOFFERING +
                "( _id integer primary key, studentsNum integer, classroom varchar, numberOfferings integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
