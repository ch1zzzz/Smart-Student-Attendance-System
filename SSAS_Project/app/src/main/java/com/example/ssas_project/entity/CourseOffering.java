package com.example.ssas_project.entity;

import java.util.Date;
import java.util.List;

public class CourseOffering {

    private int id;
    private Course course;
    private int studentsNum;
    private String classroom;
    private List<Date> dateList;

    public CourseOffering(int id, Course course, int studentsNum, String classroom, List<Date> dateList) {
        this.id = id;
        this.course = course;
        this.studentsNum = studentsNum;
        this.classroom = classroom;
        this.dateList = dateList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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

    public List<Date> getDateList() {
        return dateList;
    }

    public void setDateList(List<Date> dateList) {
        this.dateList = dateList;
    }

    @Override
    public String toString() {
        return "CourseOffering{" +
                "id=" + id +
                ", course=" + course +
                ", studentsNum=" + studentsNum +
                ", classroom='" + classroom + '\'' +
                ", dateList=" + dateList +
                '}';
    }
}
