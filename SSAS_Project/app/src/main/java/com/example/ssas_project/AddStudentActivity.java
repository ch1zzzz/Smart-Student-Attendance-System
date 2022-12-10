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
                String payment = student_payment.getText().toString();
                Types.StudentStatus status = Types.StudentStatus.valueOf(student_status1.getSelectedItem().toString());
                Student s1 = new Student(Integer.parseInt(id),name1, email, status, payment);
                myDAO.insertStudent(s1);
                myDAO.insertEnroll(Integer.parseInt(id),Integer.parseInt(offering_id1));
                Intent intent = new Intent(AddStudentActivity.this, Class_Info_Activity.class);
                intent.putExtra("course_id", course_id1);
                intent.putExtra("offer_id", offering_id1);
                startActivity(intent);
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
