package com.example.ssas_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class Class_Parameters_Activity extends AppCompatActivity {
    private EditText edit_classname2, edit_class_time2, edit_class_duration2, edit_class_days2;
    private Button edit_register2, edit_backbutton2;
    private ClassDatabase ClassDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_parameters);

        //Initialize Variables with the layout
        edit_classname2          = findViewById(R.id.register_classname2);
        edit_class_time2           = findViewById(R.id.register_class_time2);
        edit_class_duration2           = findViewById(R.id.register_class_duration2);
        edit_class_days2           = findViewById(R.id.register_class_days2);
        edit_register2           = findViewById(R.id.register_button2);
        edit_backbutton2         = findViewById(R.id.register_backbutton2);

        ClassDatabase = new ClassDatabase(this);

        edit_register2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Getting the string values from the Layout
                String classname2 = edit_classname2.getText().toString();
                String class_time2 = edit_class_time2.getText().toString();
                String class_duration2 = edit_class_duration2.getText().toString();
                String class_days2 = edit_class_days2.getText().toString();

                if (classname2.isEmpty() || class_time2.isEmpty() || class_duration2.isEmpty() || class_days2.isEmpty()) {
                    Toast.makeText(Class_Parameters_Activity.this, "Please enter all the required field", Toast.LENGTH_SHORT).show();
                }else{
                    ClassDatabase.insertClassInfo(classname2, class_time2, class_duration2, class_days2);
                    Toast.makeText(Class_Parameters_Activity.this, "Registration Completed", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });
        edit_backbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Class_Parameters_Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}