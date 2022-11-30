package com.example.ssas_project.entity;

import java.util.Date;
import java.util.List;

public class CourseOffering {

    private int id;
    private int course_id;
    private int studentsNum;
    private String classroom;

    public CourseOffering(int id, int course_id, int studentsNum, String classroom) {
        this.id = id;
        this.course_id = course_id;
        this.studentsNum = studentsNum;
        this.classroom = classroom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getStudentsNum() {
        return studentsNum;
    }

    public void setStudentsNum(int studentsNum) {
        this.studentsNum = studentsNum;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    @Override
    public String toString() {
        return "CourseOffering{" +
                "id=" + id +
                ", course_id=" + course_id +
                ", studentsNum=" + studentsNum +
                ", classroom='" + classroom + '\'' +
                '}';
    }
}
