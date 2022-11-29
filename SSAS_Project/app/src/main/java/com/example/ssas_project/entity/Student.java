package com.example.ssas_project.entity;

import com.example.ssas_project.entity.Types.StudentStatus;

public class Student {

    private int id;
    private String name;
    private String email;
    private StudentStatus status;
    private String payment_info;

    public Student(int id, String name, String email, StudentStatus status, String payment_info) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.status = status;
        this.payment_info = payment_info;
        System.out.println(status.toString());
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StudentStatus getStatus() {
        return status;
    }

    public void setStatus(StudentStatus status) {
        this.status = status;
    }

    public String getPayment_info() {
        return payment_info;
    }

    public void setPayment_info(String payment_info) {
        this.payment_info = payment_info;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", payment_info='" + payment_info + '\'' +
                '}';
    }
}
