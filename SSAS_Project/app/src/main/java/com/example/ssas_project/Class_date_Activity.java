package com.example.ssas_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ListView;

import com.example.ssas_project.database.DAO;
import com.example.ssas_project.database.MyDAO;
import com.example.ssas_project.entity.Course;
import com.example.ssas_project.entity.CourseOffering;
import com.example.ssas_project.entity.Student;
import com.example.ssas_project.entity.Types;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Class_date_Activity extends AppCompatActivity {
    private TextView class_name2;
    private Button back_button2, date_view2;
    private ListView class_date_list;
    private DAO myDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_date_select);

        //Initialize Variables with the layout
        class_name2 = findViewById(R.id.class_name2);
        back_button2 = findViewById(R.id.back_button_date);
//        date_view2 = findViewById(R.id.view_info_date);
        class_date_list = findViewById(R.id.class_date_list);


        //database line here
        myDAO = new MyDAO(this);

        Intent pre_intent = getIntent();
        Bundle bundle = getIntent().getExtras();
        String offer_id = bundle.getString("_id");

        CourseOffering offer1 = myDAO.getCourseOffering(Integer.parseInt(offer_id));
        class_name2.setText("" + offer1.getCourse_id());

        back_button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Class_Info_Activity.class);
                startActivity(intent);
            }
        });

//        date_view2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }
}
