package com.example.ssas_project.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.ssas_project.Constants;
import com.example.ssas_project.entity.Course;
import com.example.ssas_project.entity.Student;
import com.example.ssas_project.entity.Types;

import java.util.ArrayList;
import java.util.List;

public class MyDAO implements DAO{

    private SQLiteDatabase db;
    private final DatabaseHelper dbHelper;

    public MyDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    @Override
    public void resetDatabase() {
        //todo
    }

    @Override
    public void test() {
        db = dbHelper.getWritableDatabase();
    }

    @Override
    public List<Student> getStudent() {
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(Constants.TABLE_STUDENT, null, null, null, null, null, null);
        List<Student> list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                String payment_info = cursor.getString(cursor.getColumnIndexOrThrow("payment_info"));
                Types.StudentStatus status = Types.StudentStatus.valueOf(cursor.getString(cursor.getColumnIndexOrThrow("status")));
                Student s = new Student(id, name, email, status, payment_info);
                list.add(s);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    @Override
    public Student getStudent(int id) {
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + Constants.TABLE_STUDENT + " where _id = ?", new String[] {id+""});
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                String payment_info = cursor.getString(cursor.getColumnIndexOrThrow("payment_info"));
                Types.StudentStatus status = Types.StudentStatus.valueOf(cursor.getString(cursor.getColumnIndexOrThrow("status")));
                Student s = new Student(id, name, email, status, payment_info);
                return s;
            } while (cursor.moveToNext());
        }
        Log.i("DAO", "get student by id failed");
        return null;
    }

    @Override
    public void insertStudent(Student student) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("_id", student.getId());
        values.put("name", student.getName());
        values.put("email", student.getEmail());
        values.put("status", student.getStatus().toString());
        values.put("payment_info", student.getPayment_info());
        long rowid = db.insert(Constants.TABLE_STUDENT, null, values);
        if(rowid == -1) {
            Log.i("DAO", "insert student failed");
        }
        db.close();
    }

    @Override
    public void deleteStudent(int id) {
        db = dbHelper.getWritableDatabase();
        String where = "_id=" + id;
        int i = db.delete(Constants.TABLE_STUDENT, where, null);
        if (i <= 0)
            Log.i("DAO", "delete student failed");
    }

    @Override
    public void updateStudent(Student student) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("_id", student.getId());
        values.put("name", student.getName());
        values.put("email", student.getEmail());
        values.put("status", student.getStatus().toString());
        values.put("payment_info", student.getPayment_info());
        String where = "_id=" + student.getId();
        int i = db.update(Constants.TABLE_STUDENT, values, where, null);
        if(i <= 0)
            Log.i("DAO","update student failed");
    }

    @Override
    public List<Course> getCourse() {
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(Constants.TABLE_COURSE, null, null, null, null, null, null);
        List<Course> list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                boolean status = cursor.getInt(cursor.getColumnIndexOrThrow("status")) > 0;
                int numberOfferings = cursor.getInt(cursor.getColumnIndexOrThrow("numberOfferings"));
                Course c = new Course(id, name, status, numberOfferings);
                list.add(c);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    @Override
    public Course getCourse(int id) {
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + Constants.TABLE_COURSE + " where _id = ?", new String[] {id+""});
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                boolean status = cursor.getInt(cursor.getColumnIndexOrThrow("status")) > 0;
                int numberOfferings = cursor.getInt(cursor.getColumnIndexOrThrow("numberOfferings"));
                Course c = new Course(id, name, status, numberOfferings);
                return c;
            } while (cursor.moveToNext());
        }
        Log.i("DAO", "get course by id failed");
        return null;
    }

    @Override
    public void insertCourse(Course course) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("_id", course.getId());
        values.put("name", course.getName());
        values.put("status", course.isStatus());
        values.put("numberOfferings", course.getNumberOfferings());
        long rowid = db.insert(Constants.TABLE_COURSE, null, values);
        if(rowid == -1) {
            Log.i("DAO", "insert course failed");
        }
        db.close();
    }

    @Override
    public void deleteCourse(int id) {
        db = dbHelper.getWritableDatabase();
        String where = "_id=" + id;
        int i = db.delete(Constants.TABLE_COURSE, where, null);
        if (i <= 0)
            Log.i("DAO", "delete student failed");
    }

    @Override
    public void updateCourse(Course course) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("_id", course.getId());
        values.put("name", course.getName());
        values.put("status", course.isStatus());
        values.put("numberOfferings", course.getNumberOfferings());
        String where = "_id=" + course.getId();
        int i = db.update(Constants.TABLE_COURSE, values, where, null);
        if(i <= 0)
            Log.i("DAO","update course failed");
    }
    
}
