package com.example.ssas_project;

import android.os.Bundle;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ssas_project.database.DAO;
import com.example.ssas_project.database.MyDAO;
import com.example.ssas_project.entity.CourseOffering;
import com.example.ssas_project.entity.Student;
import com.example.ssas_project.entity.Types;

import java.util.ArrayList;
import java.util.List;

public class AddStudentActivity extends AppCompatActivity {
    private EditText student_id, student_name1, student_email1, student_payment;
    private Spinner student_status1;
    private Button back_button1, add_button;
    private DAO myDAO;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_student_page);

        student_id = findViewById(R.id.student_id_input);
        student_name1 = findViewById(R.id.student_name_input);
        student_email1 = findViewById(R.id.student_email_input);
        student_payment = findViewById(R.id.student_payment_input);
        student_status1 = findViewById(R.id.student_status_select);
        back_button1 = findViewById(R.id.back_button_123);
        add_button = findViewById(R.id.add_student_button);

        myDAO = new MyDAO(this);

        Bundle bundle = getIntent().getExtras();
        String course_id1 = bundle.getString("course_id");
        String offering_id1 = bundle.getString("offer_id");

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = student_id.getText().toString();
                String name1 = student_name1.getText().toString();
                String email = student_email1.getText().toString();
                String status = student_status1.getSelectedItem().toString();
                String payment = student_payment.getText().toString();
                if(id.isEmpty() || name1.isEmpty() || email.isEmpty() || payment.isEmpty()|| status.equals("please-select-status")){
                    Toast.makeText(AddStudentActivity.this, "Please enter all the required field!", Toast.LENGTH_SHORT).show();
                }
                else {

                    String email1 = student_email1.getText().toString().trim();
                    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                    Types.StudentStatus status1 = Types.StudentStatus.valueOf(status);
                    if (email1.matches(emailPattern))
                    {
                        // Check student
                        Student student_verify1 = myDAO.getStudent(Integer.parseInt(id));
                        if (student_verify1 == null) {
                            Student s1 = new Student(Integer.parseInt(id), name1, email, status1, payment);
                            myDAO.insertStudent(s1);
                        }
                        List<Integer> check_list = myDAO.getEnrollStudents(Integer.parseInt(offering_id1));
                        if(check_list.contains(Integer.parseInt(id))){
                        Toast.makeText(AddStudentActivity.this, "Student already in the class!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            myDAO.insertEnroll(Integer.parseInt(offering_id1), Integer.parseInt(id));
                            Toast.makeText(AddStudentActivity.this, "Student " + name1 + " was added!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AddStudentActivity.this, Class_Info_Activity.class);
                            intent.putExtra("course_id", course_id1);
                            intent.putExtra("offer_id", offering_id1);
                            startActivity(intent);
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        back_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddStudentActivity.this, Class_Info_Activity.class);
                intent.putExtra("course_id", course_id1);
                intent.putExtra("offer_id", offering_id1);
                startActivity(intent);
            }
        });

    }
}
