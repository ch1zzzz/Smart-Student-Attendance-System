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
import com.example.ssas_project.entity.Student;
import com.example.ssas_project.entity.Types;

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
}