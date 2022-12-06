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

import java.util.ArrayList;
import java.util.List;

public class Class_date_Activity extends AppCompatActivity {
    private EditText class_name2;
    private Button back_button2, date_view2;
    List list = new ArrayList();

    @SuppressLint("WrongViewCast")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_date_select);

        //Initialize Variables with the layout
        class_name2 = findViewById(R.id.class_name2);
        back_button2 = findViewById(R.id.back_button_date);
        date_view2 = findViewById(R.id.view_info_date);
        list = findViewById(R.id.class_date_list);

        //database line here

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
