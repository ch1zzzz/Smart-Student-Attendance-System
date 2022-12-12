package com.example.ssas_project.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.ssas_project.Constants;
import com.example.ssas_project.entity.Course;
import com.example.ssas_project.entity.CourseOffering;
import com.example.ssas_project.entity.Student;
import com.example.ssas_project.entity.Types;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyDAO implements DAO{

    private SQLiteDatabase db;
    private final DatabaseHelper dbHelper;

    public MyDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getReadableDatabase();
    }

    @Override
    public void resetDatabase() {
        //dbHelper.onUpgrade(db,1, 2);
    }

    @Override
    public void updateDatabase() {
        dbHelper.onCreate(db);
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
    public List<Student> searchStudentName(String search) {
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + Constants.TABLE_STUDENT + " where name like ?", new String[] {"%" + search + "%"});
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

    @Override
    public List<CourseOffering> getCourseOffering() {
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(Constants.TABLE_COURSEOFFERING, null, null, null, null, null, null);
        List<CourseOffering> list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                int course_id = cursor.getInt(cursor.getColumnIndexOrThrow("course_id"));
                int studentNum = cursor.getInt(cursor.getColumnIndexOrThrow("studentsNum"));
                String classroom = cursor.getString(cursor.getColumnIndexOrThrow("classroom"));
                CourseOffering c = new CourseOffering(id, course_id, studentNum, classroom);
                list.add(c);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    @Override
    public CourseOffering getCourseOffering(int id) {
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + Constants.TABLE_COURSEOFFERING + " where _id = ?", new String[] {id+""});
        if (cursor.moveToFirst()) {
            do {
                int course_id = cursor.getInt(cursor.getColumnIndexOrThrow("course_id"));
                int studentNum = cursor.getInt(cursor.getColumnIndexOrThrow("studentsNum"));
                String classroom = cursor.getString(cursor.getColumnIndexOrThrow("classroom"));
                CourseOffering c = new CourseOffering(id, course_id, studentNum, classroom);
                return c;
            } while (cursor.moveToNext());
        }
        cursor.close();
        return null;
    }

    @Override
    public List<CourseOffering> getCourseOfferingByCourse(int course_id) {
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + Constants.TABLE_COURSEOFFERING + " where course_id = ?", new String[] {course_id+""});
        List<CourseOffering> list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                int studentNum = cursor.getInt(cursor.getColumnIndexOrThrow("studentsNum"));
                String classroom = cursor.getString(cursor.getColumnIndexOrThrow("classroom"));
                CourseOffering c = new CourseOffering(id, course_id, studentNum, classroom);
                list.add(c);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    @Override
    public void insertCourseOffering(CourseOffering courseOffering) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("_id", courseOffering.getId());
        values.put("course_id", courseOffering.getCourse_id());
        values.put("studentsNum", courseOffering.getStudentsNum());
        values.put("classroom", courseOffering.getClassroom());
        long rowid = db.insert(Constants.TABLE_COURSEOFFERING, null, values);
        if(rowid == -1) {
            Log.i("DAO", "insert courseOffering failed");
        }
        db.close();
    }

    @Override
    public void deleteCourseOffering(int id) {
        db = dbHelper.getWritableDatabase();
        String where = "_id=" + id;
        int i = db.delete(Constants.TABLE_COURSEOFFERING, where, null);
        if (i <= 0)
            Log.i("DAO", "delete student failed");
    }

    @Override
    public void updateCourseOffering(CourseOffering courseOffering) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("_id", courseOffering.getId());
        values.put("course_id", courseOffering.getCourse_id());
        values.put("studentsNum", courseOffering.getStudentsNum());
        values.put("classroom", courseOffering.getClassroom());
        String where = "_id=" + courseOffering.getId();
        int i = db.update(Constants.TABLE_COURSEOFFERING, values, where, null);
        if(i <= 0)
            Log.i("DAO","update courseOffering failed");
    }

    @Override
    public void insertTime(int courseOfferingId, Date date) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("courseOffering_id", courseOfferingId);
        DateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ts = iso8601Format.format(date);
        values.put("class_time", ts);
        long rowid = db.insert(Constants.TABLE_SCHEDULE, null, values);
        if(rowid == -1) {
            Log.i("DAO", "insert courseOffering time failed");
        }
        db.close();
    }

    @Override
    public List<Date> getTime(int courseOfferingId) {
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + Constants.TABLE_SCHEDULE + " where courseOffering_id = ?", new String[] {courseOfferingId+""});
        List<Date> list = new ArrayList<>();
        DateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (cursor.moveToFirst()) {
            do {
                String rawdate = cursor.getString(cursor.getColumnIndexOrThrow("class_time"));
                try {
                    Date date = iso8601Format.parse(rawdate);
                    list.add(date);
                } catch (ParseException e) {
                    Log.e("DAO", "Parsing datetime failed", e);
                }
            } while (cursor.moveToNext());
        }
        return list;
    }

    @Override
    public List<CourseOffering> getCourseOfferingByTime(Date date) {
        db = dbHelper.getReadableDatabase();
        DateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d = iso8601Format.format(date);
        Cursor cursor = db.rawQuery("select * from " + Constants.TABLE_SCHEDULE + " as s inner join " + Constants.TABLE_COURSEOFFERING + " as c on s.courseOffering_id = c._id where class_time = ?", new String[] {d});

        List<CourseOffering> list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                int course_id = cursor.getInt(cursor.getColumnIndexOrThrow("course_id"));
                int studentNum = cursor.getInt(cursor.getColumnIndexOrThrow("studentsNum"));
                String classroom = cursor.getString(cursor.getColumnIndexOrThrow("classroom"));
                CourseOffering c = new CourseOffering(id, course_id, studentNum, classroom);
                list.add(c);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    @Override
    public List<CourseOffering> getCourseOfferingByDay(Date date) {
        db = dbHelper.getReadableDatabase();
        DateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d = iso8601Format.format(date).split("\\s+")[0];
        Cursor cursor = db.rawQuery("select * from " + Constants.TABLE_SCHEDULE + " as s inner join " + Constants.TABLE_COURSEOFFERING + " as c on s.courseOffering_id = c._id where class_time like ?", new String[] {d + "%"});

        List<CourseOffering> list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                int course_id = cursor.getInt(cursor.getColumnIndexOrThrow("course_id"));
                int studentNum = cursor.getInt(cursor.getColumnIndexOrThrow("studentsNum"));
                String classroom = cursor.getString(cursor.getColumnIndexOrThrow("classroom"));
                CourseOffering c = new CourseOffering(id, course_id, studentNum, classroom);
                list.add(c);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    @Override
    public void updateTime(int courseOfferingId, Date oldDate, Date newDate) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("courseOffering_id", courseOfferingId);
        DateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nd = iso8601Format.format(newDate);
        values.put("class_time", nd);
        String od = iso8601Format.format(oldDate);
        String where = "class_time=" + od + " AND courseOffering_id=" + courseOfferingId;
        int i = db.update(Constants.TABLE_SCHEDULE,
                values,
                "courseOffering_id" + " = ? AND " + "class_time" + " = ?",
                new String[]{courseOfferingId+"", od});
        if(i <= 0)
            Log.i("DAO","update time failed");
    }

    @Override
    public void deleteTime(int courseOfferingId, Date date) {
        db = dbHelper.getWritableDatabase();
        DateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d = iso8601Format.format(date);
        int i = db.delete(Constants.TABLE_SCHEDULE, "courseOffering_id" + " = ? AND " + "class_time" + " = ?",
                new String[]{courseOfferingId+"", d});
        if (i <= 0)
            Log.i("DAO", "delete time failed");
    }

    @Override
    public void insertEnroll(int courseOffering_id, int student_id) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("courseOffering_id", courseOffering_id);
        values.put("student_id", student_id);
        long rowid = db.insert(Constants.TABLE_ENROLL, null, values);
        if(rowid == -1) {
            Log.i("DAO", "insert enroll failed");
        }
        db.close();
    }

    @Override
    public void deleteEnroll(int courseOffering_id, int student_id) {
        db = dbHelper.getWritableDatabase();
        int i = db.delete(Constants.TABLE_ENROLL, "courseOffering_id" + " = ? AND " + "student_id" + " = ?",
                new String[]{courseOffering_id+"", student_id+""});
        if (i <= 0)
            Log.i("DAO", "delete enroll failed");
    }

    @Override
    public List<Integer> getEnrollStudents(int courseOffering_id) {
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + Constants.TABLE_ENROLL + " where courseOffering_id = ?", new String[] {courseOffering_id+""});
        List<Integer> list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                int student_id = cursor.getInt(cursor.getColumnIndexOrThrow("student_id"));
                list.add(student_id);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    @Override
    public List<Integer> getCourseofferingByCourseID(int course_id){
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + Constants.TABLE_COURSEOFFERING + " where course_id = ?", new String[] {course_id+""});
        List<Integer> list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                int _id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                list.add(_id);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    @Override
    public List<Integer> getEnrollCourses(int student_id) {
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + Constants.TABLE_ENROLL + " where student_id = ?", new String[] {student_id+""});
        List<Integer> list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                int courseOffering_id = cursor.getInt(cursor.getColumnIndexOrThrow("courseOffering_id"));
                list.add(courseOffering_id);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    @Override
    public int getAttendance(int student_id, int courseOffering_id, Date date) {
        db = dbHelper.getReadableDatabase();
        DateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d = iso8601Format.format(date);
        Cursor cursor = db.rawQuery("select * from " + Constants.TABLE_ATTENDANCE + " where student_id = ? and courseOffering_id = ? and class_time = ?", new String[] {student_id+"", courseOffering_id+"", d});
        if (cursor.moveToFirst()) {
            do {
                boolean status = cursor.getInt(cursor.getColumnIndexOrThrow("attendance")) > 0;
                if(status == true){
                    return 1;
                }
                else{
                    return 0;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        Log.i("DAO", "get attendance failed");
        return 2;
    }

    @Override
    public Map<Date, Boolean> getAttendance(int student_id, int courseOffering_id) {
        db = dbHelper.getReadableDatabase();
        DateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<Date, Boolean> map = new HashMap<>();
        Cursor cursor = db.rawQuery("select * from " + Constants.TABLE_ATTENDANCE + " where student_id = ? and courseOffering_id = ?", new String[] {student_id+"", courseOffering_id+""});
        if (cursor.moveToFirst()) {
            do {
                boolean attendance = cursor.getInt(cursor.getColumnIndexOrThrow("attendance")) > 0;
                String rawdate = cursor.getString(cursor.getColumnIndexOrThrow("class_time"));
                try {
                    Date date = iso8601Format.parse(rawdate);
                    map.put(date, attendance);
                } catch (ParseException e) {
                    Log.e("DAO", "Parsing datetime failed", e);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return map;
    }

    @Override
    public void insertAttendance(int student_id, int courseOffering_id, Date date, boolean attendance) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("student_id", student_id);
        values.put("courseOffering_id", courseOffering_id);
        values.put("attendance", attendance);
        DateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ts = iso8601Format.format(date);
        values.put("class_time", ts);
        long rowid = db.insert(Constants.TABLE_ATTENDANCE, null, values);
        if(rowid == -1) {
            Log.i("DAO", "insert attendance failed");
        }
        db.close();
    }

    @Override
    public void updateAttendance(int student_id, int courseOffering_id, Date date, boolean attendance) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("student_id", student_id);
        values.put("courseOffering_id", courseOffering_id);
        values.put("attendance", attendance);
        DateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ts = iso8601Format.format(date);
        values.put("class_time", ts);
        int i = db.update(Constants.TABLE_ATTENDANCE,
                values,
                "student_id = ? AND courseOffering_id = ? AND class_time = ?",
                new String[]{student_id+"", courseOffering_id+"", ts});
        if(i <= 0)
            Log.i("DAO","update attendance failed");
        db.close();
    }

    @Override
    public void insertUser(String username, String password, String first_name, String last_name, String email_addr){
        db = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("first_name", first_name);
        contentValues.put("last_name", last_name);
        contentValues.put("email_addr", email_addr);

        long rowid = db.insert(Constants.TABLE_LOGIN, null, contentValues);
        if(rowid == -1) {
            Log.i("DAO", "insert user failed");
        }
        db.close();
    }

    @Override
    public void updateUser(String username, String password){
        db = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("password", password);

        int i = db.update(Constants.TABLE_LOGIN, contentValues, "username = ?", new String[]{username});
        if(i <= 0)
            Log.i("DAO","update user's password failed");
        db.close();
    }

    @Override
    public Boolean checkUsername(String username){
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " +
                Constants.TABLE_LOGIN + " where username = ?", new String[]{username});

        if(cursor.getCount() > 0) {
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public Boolean checkUserPass(String username, String password){
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " +
                Constants.TABLE_LOGIN +
                " where username = ? and password = ?", new String[]{username, password});

        if(cursor.getCount() > 0) {
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public String[] getUserInfo(String username) {
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " +
                Constants.TABLE_LOGIN + " where username = ?", new String[]{username});
        String[] info = new String[3];
        if (cursor.moveToFirst()) {
            do {
                info[0] = cursor.getString(cursor.getColumnIndexOrThrow("first_name"));
                info[1] = cursor.getString(cursor.getColumnIndexOrThrow("last_name"));
                info[2] = cursor.getString(cursor.getColumnIndexOrThrow("email_addr"));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return info;
    }
}
