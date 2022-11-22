package com.example.ssas_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ClassActivity extends AppCompatActivity {
    ListView listView;
    List list = new ArrayList();
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);

        listView = (ListView)findViewById(R.id.list_view);
        list.add("Class 1 name");
        list.add("Class 2 name");
        list.add("Class 3 name");
        list.add("Class 4 name");
        list.add("Class 5 name");
        list.add("Class 6 name");
        adapter = new ArrayAdapter(ClassActivity.this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);



    }
}