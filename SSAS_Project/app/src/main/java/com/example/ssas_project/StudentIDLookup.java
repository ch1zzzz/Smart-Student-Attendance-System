package com.example.ssas_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.ssas_project.database.DAO;
import com.example.ssas_project.database.MyDAO;
import com.example.ssas_project.entity.Course;
import com.example.ssas_project.entity.CourseOffering;
import com.example.ssas_project.entity.Student;
import com.example.ssas_project.entity.Types;

import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;
import android.content.Intent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class StudentIDLookup extends AppCompatActivity {

    private DAO myDAO;
    private EditText edit_id;
    private Button edit_button;

    public void InsertTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, 11, 3, 12,0,0);
        Date date = calendar.getTime();
        myDAO.insertTime(1, date);

        calendar.set(2022, 11, 4, 6,0,0);
        date = calendar.getTime();
        myDAO.insertTime(1, date);

        calendar.set(2022, 11, 5, 19,0,0);
        date = calendar.getTime();
        myDAO.insertTime(1, date);
    }


    public void insertAttendance() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, 11, 3, 12,0,0);
        Date date = calendar.getTime();
        myDAO.insertAttendance(3, 1, date, true);

        calendar.set(2022, 11, 4, 6,0,0);
        date = calendar.getTime();
        myDAO.insertAttendance(3, 1, date, false);

        calendar.set(2022, 11, 5, 19,0,0);
        date = calendar.getTime();
        myDAO.insertAttendance(3, 1, date, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_idlookup);

        edit_id = findViewById(R.id.studentid_id);
        edit_button = findViewById(R.id.studentid_button);

        //Initialize DAO
        myDAO = new MyDAO(this);

        //Create Testing Database for students
        Student student4 = new Student(3, "Duc Nguyen", "dnguye22@tufts.edu",
                Types.StudentStatus.junior, "Credit Card");
        myDAO.insertStudent(student4);

        //Create Testing Database for Course Offering
        Course c1 = new Course(1, "CS111 OS", false, 1);
        myDAO.insertCourse(c1);

        CourseOffering c2 = new CourseOffering(1, 1, 40, "JCC101");
        myDAO.insertCourseOffering(c2);

        CourseOffering c3 = new CourseOffering(2, 1, 30, "JCC102");
        myDAO.insertCourseOffering(c3);

        CourseOffering c4 = new CourseOffering(3, 1, 30, "JCC102");
        myDAO.insertCourseOffering(c4);

        //Enroll Students
        myDAO.insertEnroll(1, 3);
        myDAO.insertEnroll(2, 3);
        myDAO.insertEnroll(3, 3);

        InsertTime();
        insertAttendance();

        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = edit_id.getText().toString();
                Intent intent = new Intent(StudentIDLookup.this,StudentView.class);
                intent.putExtra("student_id", id);
                startActivity(intent);
            }
        });
    }
}