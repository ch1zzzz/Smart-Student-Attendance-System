package com.example.ssas_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.example.ssas_project.database.DAO;
import com.example.ssas_project.database.MyDAO;
import com.example.ssas_project.entity.Student;
import com.example.ssas_project.entity.Types;

import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;

public class MainActivity extends AppCompatActivity {

    private DAO myDAO;
    private EditText edit_password, edit_username;
    private TextView edit_recover, edit_register;
    private Button edit_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent pre_intent = getIntent();
        SQLiteStudioService.instance().start(this);
        myDAO = new MyDAO(this);
        //ADDING ONE USER TO TEST REMOVE WHEN CHECK FOR NULL LOGIN-LIST CREATED
        //Initialize variables with the layout ID
        edit_username = findViewById(R.id.Login_username);
        edit_password = findViewById(R.id.Login_password);
        edit_recover = findViewById(R.id.Login_recover);
        edit_register = findViewById(R.id.Login_register);
        edit_login = findViewById(R.id.Login_button);

        //Click listener to the register button
        edit_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        //Click listener to the forgot pasword icon
        edit_recover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RecoverActivity.class);
                startActivity(intent);
            }
        });
        edit_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edit_username.getText().toString();
                String password = edit_password.getText().toString();
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter your username AND password", Toast.LENGTH_SHORT).show();
                } else {
                    if (myDAO.checkUserPass(username, password)) {
                        Intent intent = new Intent(getApplicationContext(), ClassActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Either your username or password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}