package com.example.ssas_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ssas_project.database.DAO;

public class Class_Parameters_Activity extends AppCompatActivity {
    private DAO myDAO;
    private Button register_back_button, register_button;
    private EditText register_class_name, register_class_id,register_class_offerings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_parameters);
        register_back_button = findViewById(R.id.register_backbutton2);
        register_button = findViewById(R.id.register_button2);
        register_class_name = findViewById(R.id.register_classname2);
        register_class_id = findViewById(R.id.register_class_id);
        register_class_offerings = findViewById(R.id.register_class_offerings);


        register_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Class_Parameters_Activity.this, ClassActivity.class);
                startActivity(intent);
            }
        });

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = register_class_id.getText().toString();
                String name = register_class_name.getText().toString();
                String offerings = register_class_offerings.getText().toString();
                if(id.isEmpty() || name.isEmpty() || offerings.isEmpty()){
                    Toast.makeText(Class_Parameters_Activity.this, "Please enter all the required field", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(Class_Parameters_Activity.this, ClassActivity.class);
                    Bundle extras = new Bundle();
                    extras.putString("id", id);
                    extras.putString("name", name);
                    extras.putString("offerings", offerings);
                    intent.putExtras(extras);
                    startActivity(intent);
                }
            }
        });
    }
}