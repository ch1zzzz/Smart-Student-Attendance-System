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

public class StudentSearchActivity extends AppCompatActivity{
    private EditText search_bar;
    private Button back_button1, search_button1;
    private DAO myDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_search_page);

        search_bar = findViewById(R.id.student_search_bar);
        back_button1 = findViewById(R.id.back_button127);
        search_button1 = findViewById(R.id.search_button2);

        myDAO = new MyDAO(this);

        Intent pre_intent = getIntent();
        if(pre_intent.getExtras() == null){
        }
        else{

            Bundle bundle = getIntent().getExtras();
            String course_id1 = bundle.getString("course_id");
            String offer_id1 = bundle.getString("offer_id");

            search_button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String student_id = search_bar.getText().toString();
                    if(student_id.isEmpty()){
                        Toast.makeText(StudentSearchActivity.this, "Please enter all the required field!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Student s1 = myDAO.getStudent(Integer.parseInt(student_id));
                        if(s1 != null){
                            Intent intent = new Intent(StudentSearchActivity.this,StudentView.class);
                            intent.putExtra("student_id", student_id);
                            intent.putExtra("course_id", course_id1);
                            intent.putExtra("offer_id", offer_id1);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(StudentSearchActivity.this, "Student does not exist!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

            back_button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(StudentSearchActivity.this, Class_Info_Activity.class);
                    intent.putExtra("course_id", course_id1);
                    intent.putExtra("offer_id", offer_id1);
                    startActivity(intent);
                }
            });
        }
    }
}

