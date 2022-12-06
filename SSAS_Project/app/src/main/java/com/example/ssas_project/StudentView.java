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
import com.example.ssas_project.entity.CourseOffering;
import com.example.ssas_project.entity.Student;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class StudentView extends AppCompatActivity {


    private DAO myDAO;
    private TextView edit_studentname,edit_studentid, edit_studentemail;
    private Button edit_backbutton, edit_viewdata;
    private ListView edit_listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_student_view);

        myDAO = new MyDAO(this);

        //Connect the variables to their ID
        edit_studentname = findViewById(R.id.student_view_info);
        edit_studentid = findViewById(R.id.student_view_id);
        edit_studentemail = findViewById(R.id.student_view_email);
        edit_backbutton = findViewById(R.id.student_view_backbutton);

        edit_listview =findViewById(R.id.student_view_classlist);

        //Retrieve ID from the last activity
        Intent pre_intent = getIntent();
        if(pre_intent.getExtras() == null){
        }
        else{
            //Set on click listener for Back Button
            //Click listener to the back button
            edit_backbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(StudentView.this, StudentIDLookup.class);
                    startActivity(intent);
                }
            });

            Bundle bundle = getIntent().getExtras();
            String search_id = bundle.getString("student_id");

            //Update the StudentView info
            Student student = myDAO.getStudent(Integer.parseInt(search_id));
            edit_studentname.setText(student.getName());

            String email_str = String.format("Email: %s", student.getEmail());
            edit_studentemail.setText(email_str);

            String str = String.format("Student ID: %s", search_id);
            edit_studentid.setText(str);

            //Update List View
            ArrayList<CourseOffering> array_courseoffering = new ArrayList<>();
            List<List<Integer>> courseoffering_list = new ArrayList<>();

            courseoffering_list.add(myDAO.getEnrollCourses(Integer.parseInt(search_id)));

            for(int i = 0; i < courseoffering_list.size(); i ++){
                List<Integer> list = courseoffering_list.get(i);
                for(int j = 0; j < list.size(); j++){
                    array_courseoffering.add(myDAO.getCourseOffering(list.get(j)));
                }
            }

            StudentViewListAdapter adapter = new StudentViewListAdapter(this, R.layout.student_class_item, array_courseoffering);
            edit_listview.setAdapter(adapter);
            edit_listview.setClickable(true);

            //Setting to transition to Data View
            edit_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    Intent intent = new Intent(getApplicationContext(), StudentData.class);
                    intent.putExtra("offer_id", String.valueOf(array_courseoffering.get(position).getId()));
                    intent.putExtra("student_id", search_id);
                    startActivity(intent);
                }
            });
        }
    }
}