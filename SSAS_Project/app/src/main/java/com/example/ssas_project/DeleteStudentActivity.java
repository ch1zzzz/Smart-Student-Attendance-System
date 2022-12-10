package com.example.ssas_project;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ssas_project.database.DAO;
import com.example.ssas_project.database.MyDAO;
import com.example.ssas_project.entity.CourseOffering;
import com.example.ssas_project.entity.Student;

import java.util.ArrayList;

import java.util.List;

public class DeleteStudentActivity extends AppCompatActivity{
    private EditText del_student_id1;
    private ListView student_list1;
    private Button del_button, back_button2;
    private DAO myDAO;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.del_student_page);

        del_student_id1 = findViewById(R.id.del_student_id2);
        student_list1 = findViewById(R.id.student_view_list);
        del_button = findViewById(R.id.delete_student_button2);
        back_button2 = findViewById(R.id.back_button125);

        myDAO = new MyDAO(this);

        Intent pre_intent = getIntent();
        if(pre_intent.getExtras() == null){
        }
        else {

            Bundle bundle = getIntent().getExtras();
            String course_id1 = bundle.getString("course_id");
            String offering_id1 = bundle.getString("offer_id");

            ArrayList<Student> array_student = new ArrayList<>();
            List<List<Integer>> student_list = new ArrayList<>();
            student_list.add(myDAO.getEnrollStudents(Integer.parseInt(offering_id1)));

            for (int i = 0; i < student_list.size(); i++) {
                List<Integer> list = student_list.get(i);
                for (int j = 0; j < list.size(); j++) {
                    array_student.add(myDAO.getStudent(list.get(j)));
                }
            }

            ClassOfferingViewListAdapter adapter = new ClassOfferingViewListAdapter(this, R.layout.student_view_item, array_student);
            student_list1.setAdapter(adapter);
            student_list1.setClickable(true);

            del_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String del_student = del_student_id1.getText().toString();
                    if(del_student.isEmpty()){
                        Toast.makeText(DeleteStudentActivity.this, "Please enter the required field!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        List<Integer> check_list = myDAO.getEnrollStudents(Integer.parseInt(offering_id1));
                        if(check_list.contains(Integer.parseInt(del_student))){
                            myDAO.deleteEnroll(Integer.parseInt(offering_id1), Integer.parseInt(del_student));
                            Toast.makeText(DeleteStudentActivity.this, "Student was deleted!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(DeleteStudentActivity.this, Class_Info_Activity.class);
                            intent.putExtra("course_id", course_id1);
                            intent.putExtra("offer_id", offering_id1);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(DeleteStudentActivity.this, "Student not in the class!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

            back_button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(DeleteStudentActivity.this, Class_Info_Activity.class);
                    intent.putExtra("course_id", course_id1);
                    intent.putExtra("offer_id", offering_id1);
                    startActivity(intent);
                }
            });
        }
    }
}
