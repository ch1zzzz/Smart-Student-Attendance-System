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


    }
}
