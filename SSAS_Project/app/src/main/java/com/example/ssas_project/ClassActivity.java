package com.example.ssas_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.ssas_project.database.DAO;
import com.example.ssas_project.database.MyDAO;
import com.example.ssas_project.entity.Course;
import com.example.ssas_project.entity.CourseOffering;

import java.util.ArrayList;
import java.util.List;

public class ClassActivity extends AppCompatActivity {
    private DAO myDAO;
    ListView listView;
    private Button sign_out_button, add_class_button;
    List list = new ArrayList();
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        sign_out_button = findViewById(R.id.sign_out_button);
        add_class_button = findViewById(R.id.newclass_button);
        myDAO = new MyDAO(this);
        //Create Testing Database for Course Offering
        Course c1 = new Course(1, "CS111 OS", false, 1);
        myDAO.insertCourse(c1);

        CourseOffering c2 = new CourseOffering(1, 1, 40, "JCC101");
        myDAO.insertCourseOffering(c2);

        CourseOffering c3 = new CourseOffering(2, 1, 30, "JCC102");
        myDAO.insertCourseOffering(c3);

        CourseOffering c4 = new CourseOffering(3, 1, 30, "JCC102");
        myDAO.insertCourseOffering(c4);
        listView = (ListView)findViewById(R.id.course_list_view);

        //Update List View
        ArrayList<CourseOffering> array_courseoffering = new ArrayList<>();

        List<CourseOffering> courseoffering_list = myDAO.getCourseOffering();
        for(int i = 0; i < courseoffering_list.size(); i ++){
            CourseOffering temp = courseoffering_list.get(i);
            array_courseoffering.add(temp);
        }
        StudentViewListAdapter adapter = new StudentViewListAdapter(this, R.layout.student_class_item, array_courseoffering);
        listView.setAdapter(adapter);
        listView.setClickable(true);

        sign_out_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClassActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        add_class_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClassActivity.this, Class_Parameters_Activity.class);
                startActivity(intent);
            }
        });

    }
}