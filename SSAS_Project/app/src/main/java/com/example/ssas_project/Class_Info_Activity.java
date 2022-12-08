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
    private TextView class_offid_title1, class_offering_id, class_student_num1;
    private Button back_button1, date_view1, student_info1;
    private DAO myDAO;
    private ListView student_view_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_info);

        //Initialize Variables with the layout
        edit_class_name1 = findViewById(R.id.class_name1);
        class_date_show1 = findViewById(R.id.date_show1);
        class_student_num1 = findViewById(R.id.student_num1);
        date_title1 = findViewById(R.id.date1);
        back_button1 = findViewById(R.id.button2);
        date_view1 = findViewById(R.id.date_view1);
        student_info1 = findViewById(R.id.student_info1);
        student_view_list = findViewById(R.id.student_list1);
        class_offering_id = findViewById(R.id.class_offering_id1);

        //database line here
        myDAO = new MyDAO(this);


//        Bundle bundle = getIntent().getExtras();
//        String search_class_offering = bundle.getString("course_id");

        String search_class_offering = "1";


//        int search_class_offering = 1;

        //Update the info
        CourseOffering offer1 = myDAO.getCourseOffering(Integer.parseInt(search_class_offering));
        edit_class_name1.setText(""+offer1.getCourse_id());

        String offer_id = String.format("class offering ID: %s", offer1.getId());
        class_offering_id.setText(""+offer_id);

        String stu_num = String.format("studentsNum: %s", offer1.getStudentsNum());
        class_student_num1.setText(""+stu_num);



        ArrayList<Student> array_student = new ArrayList<>();
        List<List<Integer>> student_list = new ArrayList<>();
        student_list.add(myDAO.getEnrollStudents(Integer.parseInt(search_class_offering)));

        for(int i = 0; i < student_list.size(); i ++){
            List<Integer> list = student_list.get(i);
            for(int j = 0; j < list.size(); j++){
                array_student.add(myDAO.getStudent(list.get(j)));
            }
        }

        ClassOfferingViewListAdapter adapter = new ClassOfferingViewListAdapter(this, R.layout.student_view_item, array_student);
        student_view_list.setAdapter(adapter);
        student_view_list.setClickable(true);


        date_view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Class_date_Activity.class);
                startActivity(intent);
            }
        });
    }
}
