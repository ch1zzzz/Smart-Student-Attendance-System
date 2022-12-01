package com.example.ssas_project.entity;

public class Course {

    private int id;
    private String name;
    //true: offered; false: not offered
    private boolean status;
    private int numberOfferings;

    public Course(int id, String name, boolean status, int numberOfferings) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.numberOfferings = numberOfferings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getNumberOfferings() {
        return numberOfferings;
    }

    public void setNumberOfferings(int numberOfferings) {
        this.numberOfferings = numberOfferings;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", numberOfferings=" + numberOfferings +
                '}';
    }
}
