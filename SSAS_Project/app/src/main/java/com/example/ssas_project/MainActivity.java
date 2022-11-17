package com.example.ssas_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private EditText edit_username, edit_password;
    private TextView edit_recover, edit_register;
    private Button edit_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }
}