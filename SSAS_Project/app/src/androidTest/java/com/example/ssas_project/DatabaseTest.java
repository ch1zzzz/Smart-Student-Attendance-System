package com.example.ssas_project;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import com.example.ssas_project.database.DAO;
import com.example.ssas_project.database.MyDAO;
import com.example.ssas_project.entity.Course;
import com.example.ssas_project.entity.CourseOffering;
import com.example.ssas_project.entity.Student;
import com.example.ssas_project.entity.Types;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class DatabaseTest {

    private DAO dao;

    @Before
    public void createDAO() {
        Context context = ApplicationProvider.getApplicationContext();
        dao = new MyDAO(context);
    }

    @Test
    public void test1() {

        for(int i = 0; i < 100; i++) {
            Student student4 = new Student(i, "44444", "44444@tufts.edu",
                    Types.StudentStatus.junior, "44444");
            dao.insertStudent(student4);
        }
    }

    @Test
    public void testInsertStudent() {
        Student student4 = new Student(4, "44444", "44444@tufts.edu",
                    Types.StudentStatus.junior, "44444");
        dao.insertStudent(student4);
        Student student5 = new Student(5, "55555", "55555@tufts.edu",
                Types.StudentStatus.junior, "55555");
        dao.insertStudent(student5);
        Student student6 = new Student(6, "66666", "66666@tufts.edu",
                Types.StudentStatus.sophomore, "66666");
        dao.insertStudent(student6);
        Student student7 = new Student(7, "77777", "77777@tufts.edu",
                Types.StudentStatus.junior, "77777");
        dao.insertStudent(student7);
        Student student8 = new Student(8, "88888", "88888@tufts.edu",
                Types.StudentStatus.doctor, "88888");
        dao.insertStudent(student8);
    }

    @Test
    public void testGetStudents() {
        for(Student s : dao.getStudent()) {
            System.out.println(s);
        }
    }

    @Test
    public void testGetStudentById() {
        Student s = dao.getStudent(5);
        System.out.println(s);
    }

    @Test
    public void testUpdateStudents() {
        Student up = new Student(5, "jackson", "692@qq.com",Types.StudentStatus.master, "debit card");
        dao.updateStudent(up);
        Student s = dao.getStudent(5);
        System.out.println(s);
    }

    @Test
    public void testDeleteStudents() {
        dao.deleteStudent(6);
        for(Student s : dao.getStudent()) {
            System.out.println(s);
        }
    }

    @Test
    public void testSearchStudent() {
        for(Student s : dao.searchStudentName("77")) {
            System.out.println(s);
        }
    }

    @Test
    public void testInsertCourse() {
        Course c1 = new Course(1, "CS111 OS", false, 1);
        dao.insertCourse(c1);
        Course c2 = new Course(2, "CS115 Database", true, 1);
        dao.insertCourse(c2);
        Course c3 = new Course(3, "CS119 Big Data", true, 2);
        dao.insertCourse(c3);
        Course c4 = new Course(4, "CS121 Software", true, 2);
        dao.insertCourse(c4);
    }

    @Test
    public void testGetCourses() {
        for(Course c : dao.getCourse()) {
            System.out.println(c);
        }
    }

    @Test
    public void testGetCourseById() {
        Course c = dao.getCourse(2);
        System.out.println(c);
    }

    @Test
    public void testUpdateCourses() {
        Course up = new Course(1, "Oeparating System", false, 0);
        dao.updateCourse(up);
        Course c = dao.getCourse(1);
        System.out.println(c);
    }

    @Test
    public void testDeleteCourses() {
        dao.deleteCourse(3);
    }

    @Test
    public void testInsertCourseOffering() {
        CourseOffering c1 = new CourseOffering(1, 1, 40, "JCC101");
        dao.insertCourseOffering(c1);
        CourseOffering c2 = new CourseOffering(2, 2, 30, "JCC102");
        dao.insertCourseOffering(c2);
        CourseOffering c3 = new CourseOffering(3, 4, 20, "JCC103");
        dao.insertCourseOffering(c3);
        CourseOffering c4 = new CourseOffering(4, 4, 20, "JCC104");
        dao.insertCourseOffering(c4);
    }

    @Test
    //insert courseOffering with not exist course_id
    public void testInsertCourseOfferingError() {
        CourseOffering c1 = new CourseOffering(5, 5, 40, "JCC105");
        dao.insertCourseOffering(c1);
    }

    @Test
    public void testGetCourseOfferings() {
        for(CourseOffering c : dao.getCourseOffering()) {
            System.out.println(c);
        }
    }

    @Test
    public void testGetCourseOfferingById() {
        CourseOffering c = dao.getCourseOffering(4);
        System.out.println(c);
    }

    @Test
    public void testGetCourseOfferingByCourse() {
        for(CourseOffering c : dao.getCourseOfferingByCourse(4)) {
            System.out.println(c);
        }
    }

    @Test
    public void testUpdateCourseOfferings() {
        CourseOffering up = new CourseOffering(3, 4, 100, "online");
        dao.updateCourseOffering(up);
        CourseOffering c = dao.getCourseOffering(3);
        System.out.println(c);
    }

    @Test
    public void testDeleteCourseOfferings() {
        dao.deleteCourseOffering(4);
    }

    @Test
    public void testInsertTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, 11, 3, 12,0,0);
        Date date = calendar.getTime();
        dao.insertTime(1, date);
        calendar.set(2022, 1, 3, 6,0,0);
        date = calendar.getTime();
        dao.insertTime(2, date);
        calendar.set(2023, 2, 3, 19,0,0);
        date = calendar.getTime();
        dao.insertTime(3, date);
    }

    @Test
    public void testGetTime() {
        DateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(Date date : dao.getTime(2)) {
            System.out.println(iso8601Format.format(date));
        }
    }

    @Test
    public void testUpdateTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, 11, 3, 12,0,0);
        Date oldDate = calendar.getTime();
        calendar.set(2022, 11, 3, 15,0,0);
        Date newDate = calendar.getTime();
        dao.updateTime(1, oldDate, newDate);
    }

    @Test
    public void testDeleteTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, 2, 3, 19,0,0);
        Date date = calendar.getTime();
        dao.deleteTime(3, date);
    }

    @Test
    public void testInsertEnroll() {
        dao.insertEnroll(1, 4);
        dao.insertEnroll(1, 5);
        dao.insertEnroll(1, 6);
        dao.insertEnroll(1, 7);
        dao.insertEnroll(1, 8);
        dao.insertEnroll(2, 5);
        dao.insertEnroll(3, 6);
    }

    @Test
    public void testDeleteEnroll() {
        dao.deleteEnroll(1, 8);
    }

    @Test
    public void testGetEnroll() {
        for(int i : dao.getEnrollStudents(1)) {
            System.out.println(i);
        }
        for(int i : dao.getEnrollCourses(5)) {
            System.out.println(i);
        }
    }

    @Test
    public void insertAttendance() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, 11, 1, 12,0,0);
        Date date = calendar.getTime();
        dao.insertAttendance(4, 1, date, true);
        calendar.set(2022, 11, 3, 15,0,0);
        date = calendar.getTime();
        dao.insertAttendance(4, 1, date, false);
        calendar.set(2023, 2, 1, 19,0,0);
        date = calendar.getTime();
        dao.insertAttendance(6, 3, date, true);
    }

    @Test
    public void getAttendance() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, 11, 1, 12,0,0);
        Date date = calendar.getTime();
        System.out.println(dao.getAttendance(4, 1, date));
    }

    @Test
    public void getAttendance2() {
        System.out.println(dao.getAttendance(4, 1));
    }

    @Test
    public void updateAttendance() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, 2, 1, 19,0,0);
        Date date = calendar.getTime();
        dao.updateAttendance(6, 3, date, false);
    }

    @Test
    public void cascadeDeleteStudent() {
        dao.deleteStudent(6);
    }

    @Test
    public void cascadeDeleteCourse() {
        dao.deleteCourse(2);
    }
}