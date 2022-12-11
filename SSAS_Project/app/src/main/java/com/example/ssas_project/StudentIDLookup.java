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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_idlookup);

        edit_id = findViewById(R.id.studentid_id);
        edit_button = findViewById(R.id.studentid_button);

        //Initialize DAO
        myDAO = new MyDAO(this);

        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(2023, 1, 18, 19, 0, 0);

                String id = edit_id.getText().toString();
                Intent intent = new Intent(StudentIDLookup.this,CoursePageActivity.class);
                intent.putExtra("course_id", id);
                intent.putExtra("offer_id", id);
                intent.putExtra("month", calendar.get(Calendar.MONTH));
                intent.putExtra("year", calendar.get(Calendar.YEAR));
                intent.putExtra("dayOfMonth", calendar.get(Calendar.DAY_OF_MONTH));
                intent.putExtra("hourOfDay", calendar.get(Calendar.HOUR_OF_DAY));
                intent.putExtra("minute", calendar.get(Calendar.MINUTE));
                startActivity(intent);
            }
        });
    }
}