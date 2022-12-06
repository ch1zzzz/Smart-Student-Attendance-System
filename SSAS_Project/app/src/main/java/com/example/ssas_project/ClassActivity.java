package com.example.ssas_project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ClassActivity extends AppCompatActivity {
    ListView listView;
    List list = new ArrayList();
    ArrayAdapter adapter;
    private Button edit_add_class, sign_out;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        edit_add_class = findViewById(R.id.newclass_button);
        sign_out = findViewById(R.id.class_banner);
        listView = (ListView)findViewById(R.id.list_view);
        list.add("Class 1 name");
        list.add("Class 2 name");
        list.add("Class 3 name");
        list.add("Class 4 name");
        list.add("Class 5 name");
        list.add("Class 6 name");
        adapter = new ArrayAdapter(ClassActivity.this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);

        edit_add_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClassActivity.this, Class_Parameters_Activity.class);
                startActivity(intent);
            }
        });
        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClassActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}