package com.example.ssas_project;

import android.os.Bundle;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ssas_project.database.DAO;
import com.example.ssas_project.database.MyDAO;
import com.example.ssas_project.entity.CourseOffering;
import com.example.ssas_project.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class CoursePageActivity extends AppCompatActivity {
    private TextView course_name;
    private ListView course_offering_list;
    private Button back_button1, add_offering1;
    private DAO myDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.before_class_info);

        course_name = findViewById(R.id.course_name1);
        course_offering_list = findViewById(R.id.course_offering_list);
        back_button1 = findViewById(R.id.back_button121);
        add_offering1 = findViewById(R.id.add_course_offering);

        myDAO = new MyDAO(this);

        String search_class_offering = "1";

        //Update List View
        ArrayList<CourseOffering> array_courseoffering = new ArrayList<>();
        List<List<Integer>> courseoffering_list = new ArrayList<>();

        courseoffering_list.add(myDAO.getCourseOffering(Integer.parseInt(search_class_offering)));

        for(int i = 0; i < courseoffering_list.size(); i ++){
            List<Integer> list = courseoffering_list.get(i);
            for(int j = 0; j < list.size(); j++){
                array_courseoffering.add(myDAO.getCourseOffering(list.get(j)));
            }
        }

        StudentViewListAdapter adapter = new StudentViewListAdapter(this, R.layout.student_class_item, array_courseoffering);
        course_offering_list.setAdapter(adapter);
        course_offering_list.setClickable(true);

        add_offering1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CoursePageActivity.this, AddOfferingActivity.class);
                startActivity(intent);
            }
        });

//        course_offering_list.setOnClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(getApplicationContext(), Class_Info_Activity.class);
//                intent.putExtra("student_id", String.valueOf(array_student.get(i).getId()));
//                intent.putExtra("offer_id", search_class_offering);
//                startActivity(intent);
//            }
//        });

    };
}
