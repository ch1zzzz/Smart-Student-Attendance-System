package com.example.ssas_project.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class MyDAO implements DAO{

    private SQLiteDatabase db;
    private final DatabaseHelper dbHelper;

    public MyDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void insert() {
        db = dbHelper.getWritableDatabase();
        // TODO
    }

    public void delete() {
        // TODO
    }

    public void update() {
        // TODO
    }
}
