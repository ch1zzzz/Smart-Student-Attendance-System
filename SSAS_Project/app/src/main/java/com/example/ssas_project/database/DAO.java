package com.example.ssas_project.database;

import com.example.ssas_project.entity.Course;
import com.example.ssas_project.entity.CourseOffering;
import com.example.ssas_project.entity.Student;

import java.util.List;

/*
Data Access Object
 */
public interface DAO {
    void resetDatabase();

    void test();

    List<Student> getStudent();

    List<Course> getCourse();

    Student getStudent(int id);

    Course getCourse(int id);

    void insertStudent(Student student);

    void insertCourse(Course course);

    void deleteStudent(int id);

    void deleteCourse(int id);

    void updateStudent(Student student);

    void updateCourse(Course course);
}
