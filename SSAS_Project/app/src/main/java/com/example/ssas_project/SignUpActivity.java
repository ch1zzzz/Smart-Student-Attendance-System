package com.example.ssas_project;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;


public class SignUpActivity extends AppCompatActivity {

    private EditText edit_firstname, edit_lastname, edit_username, edit_password, edit_email, edit_confirm_password;
    private Button edit_register;
    private LoginDatabase LoginDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Initialize Variables with the layout
        edit_firstname          = findViewById(R.id.Register_firstname);
        edit_lastname           = findViewById(R.id.Register_lastname);
        edit_username           = findViewById(R.id.Register_username);
        edit_password           = findViewById(R.id.Register_password);
        edit_confirm_password   = findViewById(R.id.Register_confirm_password);
        edit_email              = findViewById(R.id.Register_email);
        edit_register           = findViewById(R.id.Register_button);

        //Invoke the database
        LoginDatabase = new LoginDatabase(this);

        //Create Function to listen to the Register Button
        edit_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Getting the string values from the Layout
                String username         = edit_username.getText().toString();
                String password         = edit_password.getText().toString();
                String confirm_password = edit_confirm_password.getText().toString();
                String first_name       = edit_firstname.getText().toString();
                String last_name        = edit_lastname.getText().toString();
                String email            = edit_email.getText().toString();

                //If any of the field is empty, raise a notification
                if(username.isEmpty() || password.isEmpty() || confirm_password.isEmpty() || first_name.isEmpty() || last_name.isEmpty() || email.isEmpty()){
                    Toast.makeText(SignUpActivity.this, "Please enter all the required field", Toast.LENGTH_SHORT).show();
                }
                else{
                    //Compare Password with confirm Password
                    if(!password.equals(confirm_password)){
                        Toast.makeText(SignUpActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        //Check if the user exists
                        Boolean user_verify = LoginDatabase.check_username(username);
                        if(user_verify == true){
                            Toast.makeText(SignUpActivity.this, "Users already exist", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            LoginDatabase.insertData(username, password, first_name, last_name, email);
                            Toast.makeText(SignUpActivity.this, "Registration Completed", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    }
                }
            }
        });




    }
}