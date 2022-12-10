package com.example.ssas_project;

import androidx.appcompat.app.AppCompatActivity;

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

import com.example.ssas_project.database.DAO;
import com.example.ssas_project.database.MyDAO;
import com.example.ssas_project.entity.Course;
import com.example.ssas_project.entity.CourseOffering;
import com.example.ssas_project.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class Class_Info_Activity extends AppCompatActivity {
    private TextView  edit_class_name1, class_date_show1;
    private TextView class_offering_id, class_student_num1;
    private Button back_button1, offering_search, add_student1, del_student1;
    private EditText search_name1;
    private DAO myDAO;
    private ListView student_view_list;

//    public void changeStudent(String name){
//        student_view_list.clear();
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_info);

        //Initialize Variables with the layout
        edit_class_name1 = findViewById(R.id.class_name1);
        //class_date_show1 = findViewById(R.id.date_show1);
        class_student_num1 = findViewById(R.id.student_num1);
//        date_title1 = findViewById(R.id.date1);
        back_button1 = findViewById(R.id.button2);
        student_view_list = findViewById(R.id.student_list1);
        class_offering_id = findViewById(R.id.class_offering_id1);
        search_name1 = findViewById(R.id.search_name1);
        add_student1 = findViewById(R.id.add_student);
        del_student1 = findViewById(R.id.delete_student);

//        setSupportActionBar(student_view_list);

        //database line here
        myDAO = new MyDAO(this);

        Intent pre_intent = getIntent();
        if(pre_intent.getExtras() == null){
        }
        else {
            Bundle bundle = getIntent().getExtras();
            String offering_id = bundle.getString("offer_id");
            String course_id1 = bundle.getString("course_id");


            //        int search_class_offering = 1;

            //Update the info
            CourseOffering offer1 = myDAO.getCourseOffering(Integer.parseInt(offering_id));
            Course course = myDAO.getCourse(Integer.parseInt(course_id1));
            edit_class_name1.setText(course.getName());

            String offer_id = String.format("Class Offering ID: %s", offer1.getId());
            class_offering_id.setText("" + offer_id);

            String stu_num = String.format("Max Student Num: %s", offer1.getStudentsNum());
            class_student_num1.setText("" + stu_num);


            ArrayList<Student> array_student = new ArrayList<>();
            List<List<Integer>> student_list = new ArrayList<>();
            student_list.add(myDAO.getEnrollStudents(Integer.parseInt(offering_id)));

            for (int i = 0; i < student_list.size(); i++) {
                List<Integer> list = student_list.get(i);
                for (int j = 0; j < list.size(); j++) {
                    array_student.add(myDAO.getStudent(list.get(j)));
                }
            }

            ClassOfferingViewListAdapter adapter = new ClassOfferingViewListAdapter(this, R.layout.student_view_item, array_student);
            student_view_list.setAdapter(adapter);
            student_view_list.setClickable(true);


            search_name1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String name = search_name1.getText().toString();
//                    if()
                    
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });


            // date select button
//            date_view1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(Class_Info_Activity.this, Class_date_Activity.class);
//                    intent.putExtra("course_id", search_class_offering);
//                    pre_intent.putExtra("offer_id", back_string);
//                    startActivity(intent);
//                }
//            });

            student_view_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(getApplicationContext(), StudentView.class);
                    intent.putExtra("student_id", String.valueOf(array_student.get(i).getId()));
                    intent.putExtra("offer_id", offering_id);
                    startActivity(intent);
                }
            });

            add_student1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), AddStudentActivity.class);
                    intent.putExtra("course_id", course_id1);
                    intent.putExtra("offer_id", offering_id);
                    startActivity(intent);
                }
            });

            del_student1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), DeleteStudentActivity.class);
                    intent.putExtra("offer_id", offering_id);
                    intent.putExtra("course_id", course_id1);
                    startActivity(intent);
                }
            });

            // back button
            back_button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Class_Info_Activity.this, CoursePageActivity.class);
                    intent.putExtra("course_id", course_id1);
                    startActivity(intent);
                }
            });
        }
    }
}
