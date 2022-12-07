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

public class Class_Info_Activity extends AppCompatActivity {
    private TextView date_title1, edit_class_name1, class_date_show1;
    private TextView class_offid_title1, class_offering_id;
    private Button back_button1, date_view1, student_info1;
    private DAO myDAO;
    private ListView class_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_info);

        //Initialize Variables with the layout
        edit_class_name1 = findViewById(R.id.class_name1);
        class_date_show1 = findViewById(R.id.date_show1);
        date_title1 = findViewById(R.id.date1);
        back_button1 = findViewById(R.id.button2);
        date_view1 = findViewById(R.id.date_view1);
        student_info1 = findViewById(R.id.student_info1);
        class_list = findViewById(R.id.student_list1);
        class_offid_title1 = findViewById(R.id.class_offid_title);
        class_offering_id = findViewById(R.id.class_offering_id1);

        //database line here
        myDAO = new MyDAO(this);

        date_view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Class_date_Activity.class);
                startActivity(intent);
            }
        });

        Bundle bundle = getIntent().getExtras();
        String search_id = bundle.getString("classofferingid");

        //Update the StudentView info
        Student student = myDAO.getStudent(Integer.parseInt(search_id));
        edit_studentname.setText(student.getName());

        String email_str = String.format("Email: %s", student.getEmail());
        edit_studentemail.setText(email_str);

        String str = String.format("Student ID: %s", search_id);
        edit_studentid.setText(str);

        ArrayList<CourseOffering> array_courseoffering = new ArrayList<>();
        List<List<Integer>> courseoffering_list = new ArrayList<>();

        for(int i = 0; i < courseoffering_list.size(); i ++){
            List<Integer> list = courseoffering_list.get(i);
            for(int j = 0; j < list.size(); j++){
                array_courseoffering.add(myDAO.getCourseOffering(list.get(j)));
            }
        }

        StudentViewListAdapter adapter = new StudentViewListAdapter(this, R.layout.student_class_item, array_courseoffering);
        class_list.setAdapter(adapter);
        class_list.setClickable(true);

        //Setting to transition to Data View
        class_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), StudentData.class);
                intent.putExtra("offer_id", String.valueOf(array_courseoffering.get(position).getId()));
                startActivity(intent);
            }
        });
    }
}
