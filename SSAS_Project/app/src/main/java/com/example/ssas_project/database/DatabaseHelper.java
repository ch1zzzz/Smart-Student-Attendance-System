package com.example.ssas_project.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.ssas_project.Constants;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.VERSION_CODE);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("Create Table IF NOT EXISTS " +
                Constants.TABLE_LOGIN +
                "(username TEXT primary key, password TEXT, first_name TEXT, last_name TEXT, email_addr TEXT)");
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", "admin");
        contentValues.put("password", "admin");
        contentValues.put("first_name", "admin");
        contentValues.put("last_name", "");
        contentValues.put("email_addr", "");
        db.insert(Constants.TABLE_LOGIN, null, contentValues);

        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                Constants.TABLE_STUDENT +
                "(_id integer primary key, name varchar not null, email varchar, status varchar, payment_info varchar)");

        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                Constants.TABLE_COURSE +
                "(_id integer primary key, name varchar not null, status boolean, numberOfferings integer)");

        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                Constants.TABLE_COURSEOFFERING +
                "(_id integer primary key, course_id integer not null, studentsNum integer, classroom varchar, " +
                "FOREIGN KEY (course_id) REFERENCES " +
                Constants.TABLE_COURSE + "(_id) ON DELETE CASCADE ON UPDATE CASCADE)");

        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                Constants.TABLE_ENROLL +
                "(student_id integer not null, courseOffering_id integer not null, FOREIGN KEY (student_id) REFERENCES " +
                Constants.TABLE_STUDENT + "(_id) ON DELETE CASCADE ON UPDATE CASCADE, FOREIGN KEY (courseOffering_id) REFERENCES " +
                Constants.TABLE_COURSEOFFERING + "(_id) ON DELETE CASCADE ON UPDATE CASCADE, PRIMARY KEY (student_id, courseOffering_id))");

        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                Constants.TABLE_SCHEDULE +
                "(courseOffering_id integer not null, class_time datetime not null, " +
                "PRIMARY KEY (courseOffering_id, class_time), FOREIGN KEY (courseOffering_id) REFERENCES " +
                Constants.TABLE_COURSEOFFERING + "(_id) ON DELETE CASCADE ON UPDATE CASCADE)");

        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                Constants.TABLE_ATTENDANCE +
                "(student_id integer not null, courseOffering_id integer not null, class_time datetime not null, attendance boolean not null, FOREIGN KEY (student_id) REFERENCES " +
                Constants.TABLE_STUDENT + "(_id) ON DELETE CASCADE  ON UPDATE CASCADE, FOREIGN KEY (courseOffering_id, class_time) REFERENCES " +
                Constants.TABLE_SCHEDULE + "(courseOffering_id, class_time) ON DELETE CASCADE  ON UPDATE CASCADE, PRIMARY KEY (student_id, courseOffering_id, class_time))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_LOGIN);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_STUDENT);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_COURSE);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_COURSEOFFERING);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_ENROLL);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_ATTENDANCE);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_SCHEDULE);
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase database) {
        super.onOpen(database);
        if (!database.isReadOnly()) {
            // Enable foreign key constraints
            database.execSQL("PRAGMA foreign_keys=ON;");
        }
    }
}
