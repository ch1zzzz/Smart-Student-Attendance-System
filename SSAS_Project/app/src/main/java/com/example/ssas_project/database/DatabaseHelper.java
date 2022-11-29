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
                "(_id integer primary key, course_id integer, studentsNum integer, classroom varchar, " +
                "numberOfferings integer, FOREIGN KEY (course_id) REFERENCES " +
                Constants.TABLE_COURSE + "(_id) ON DELETE CASCADE)");

        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                Constants.TABLE_ENROLL +
                "(student_id integer, courseOffering_id integer, FOREIGN KEY (student_id) REFERENCES " +
                Constants.TABLE_STUDENT + "(_id) ON DELETE CASCADE, FOREIGN KEY (courseOffering_id) REFERENCES " +
                Constants.TABLE_COURSEOFFERING + "(_id) ON DELETE CASCADE, PRIMARY KEY (student_id, courseOffering_id))");

        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                Constants.TABLE_ATTENDANCE +
                "(student_id integer, courseOffering_id integer, class_time datetime, attendance boolean, FOREIGN KEY (student_id) REFERENCES " +
                Constants.TABLE_STUDENT + "(_id) ON DELETE CASCADE, FOREIGN KEY (courseOffering_id) REFERENCES " +
                Constants.TABLE_COURSEOFFERING + "(_id) ON DELETE CASCADE, PRIMARY KEY (student_id, courseOffering_id, class_time))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_STUDENT);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_COURSE);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_COURSEOFFERING);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_ENROLL);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_ATTENDANCE);
        onCreate(db);
    }
}
