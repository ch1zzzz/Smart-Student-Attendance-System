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

public class OfferingSearchActivity extends AppCompatActivity{
    private EditText search_bar;
    private Button back_button1, search_button1;
    private DAO myDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offering_search_page);

        search_bar = findViewById(R.id.offering_search_bar);
        back_button1 = findViewById(R.id.back_button126);
        search_button1 = findViewById(R.id.search_button1);

        myDAO = new MyDAO(this);

        Intent pre_intent = getIntent();
        if(pre_intent.getExtras() == null){
        }
        else{

            Bundle bundle = getIntent().getExtras();
            String course_id1 = bundle.getString("course_id");

            search_button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String offer_id = search_bar.getText().toString();
                    if(offer_id.isEmpty()){
                        Toast.makeText(OfferingSearchActivity.this, "Please enter all the required field!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Intent intent = new Intent(OfferingSearchActivity.this,Class_Info_Activity.class);
                        intent.putExtra("course_id", course_id1);
                        intent.putExtra("offer_id", offer_id);
                        startActivity(intent);
                    }
                }
            });

            back_button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(OfferingSearchActivity.this, CoursePageActivity.class);
                    intent.putExtra("course_id", course_id1);
                    startActivity(intent);
                }
            });
        }
    }
}
