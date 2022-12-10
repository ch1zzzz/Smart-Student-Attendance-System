package com.example.ssas_project;

import android.os.Bundle;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ssas_project.database.DAO;
import com.example.ssas_project.database.MyDAO;
import com.example.ssas_project.entity.CourseOffering;
import com.example.ssas_project.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class AddOfferingActivity extends AppCompatActivity{
    private EditText offer_id1, student_num1, class_room1;
    private Button back_button1, add_offer1;
    private DAO myDAO;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_offering_page);

        offer_id1 = findViewById(R.id.course_off_id);
        student_num1 = findViewById(R.id.student_num);
        class_room1 = findViewById(R.id.class_room1);
        back_button1 = findViewById(R.id.back_button122);
        add_offer1 = findViewById(R.id.add_course_button1);

        myDAO = new MyDAO(this);

        Intent pre_intent = getIntent();
        if(pre_intent.getExtras() == null){
        }
        else {

            Bundle bundle = getIntent().getExtras();
            String search_class_offering = bundle.getString("course_id");

            add_offer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String id = offer_id1.getText().toString();
                    String stu_num = student_num1.getText().toString();
                    String classroom = class_room1.getText().toString();
                    if(id.isEmpty() || stu_num.isEmpty() || classroom.isEmpty()){
                        Toast.makeText(AddOfferingActivity.this, "Please enter all the required field!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        CourseOffering id_verify = myDAO.getCourseOffering(Integer.parseInt(id));
                        if(id_verify == null) {
                            CourseOffering c1 = new CourseOffering(Integer.parseInt(id), Integer.parseInt(search_class_offering), Integer.parseInt(stu_num), classroom);
                            myDAO.insertCourseOffering(c1);
                            Toast.makeText(AddOfferingActivity.this, "Course Offering "+id+" was added!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AddOfferingActivity.this, CoursePageActivity.class);
                            intent.putExtra("course_id", search_class_offering);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(AddOfferingActivity.this, "Course Offering already exist!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

            back_button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(AddOfferingActivity.this, CoursePageActivity.class);
                    intent.putExtra("course_id", search_class_offering);
                    startActivity(intent);
                }
            });
        }
    }

}
