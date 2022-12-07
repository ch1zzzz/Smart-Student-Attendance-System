package com.example.ssas_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ssas_project.database.DAO;

public class Class_Parameters_Activity extends AppCompatActivity {
    private DAO myDAO;
    private Button register_back_button, register_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_parameters);
        register_back_button = findViewById(R.id.register_backbutton2);
        register_button = findViewById(R.id.register_button2);
        register_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Class_Parameters_Activity.this, ClassActivity.class);
                startActivity(intent);
            }
        });
    }
}