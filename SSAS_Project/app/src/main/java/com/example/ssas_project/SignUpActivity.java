

package com.example.ssas_project;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import com.example.ssas_project.database.DAO;
import com.example.ssas_project.database.MyDAO;

public class SignUpActivity extends AppCompatActivity {

    private EditText edit_firstname, edit_lastname, edit_username, edit_password, edit_email, edit_confirm_password;
    private Button edit_register, edit_backbutton;
    private DAO myDAO;

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
        edit_backbutton         = findViewById(R.id.Register_backbutton);

        //Invoke the database
        myDAO = new MyDAO(this);
        //Set Listener for the Backbutton
        edit_backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

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
                    Toast.makeText(SignUpActivity.this, "Please enter all the required fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    //Compare Password with confirm Password
                    if(!password.equals(confirm_password)){
                        Toast.makeText(SignUpActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        //Check if the user exists
                        Boolean user_verify = myDAO.checkUsername(username);
                        if(user_verify == true){
                            Toast.makeText(SignUpActivity.this, "Users already exist", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            System.out.println(username + password + first_name + last_name + email);
                            myDAO.insertUser(username, password, first_name, last_name, email);
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