package com.example.ssas_project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ssas_project.database.DAO;
import com.example.ssas_project.database.MyDAO;

import java.util.ArrayList;
import java.util.List;

public class Class_Info_Activity extends AppCompatActivity {
    private EditText class_name1, class_date_show1;
    private TextView date_title1;
    private Button back_button1, date_view1, student_info1;
    private DAO myDAO;
    List list = new ArrayList();

    @SuppressLint("WrongViewCast")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_info);

        //Initialize Variables with the layout
        class_name1 = findViewById(R.id.class_name1);
        class_date_show1 = findViewById(R.id.date_show1);
        date_title1 = findViewById(R.id.date1);
        back_button1 = findViewById(R.id.button2);
        date_view1 = findViewById(R.id.date_view1);
        student_info1 = findViewById(R.id.student_info1);
        list = findViewById(R.id.student_list1);

        //database line here
        myDAO = new MyDAO(this);

        date_view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Class_date_Activity.class);
                startActivity(intent);
            }
        });
    }
}
