package com.example.ssas_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class RecoverActivity extends AppCompatActivity {

    //Declare Variables
    EditText edit_username, edit_password, edit_confirm_password;
    Button edit_button, edit_backbutton;
    LoginDatabase login_database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover);

        //Find contents based on the Layout ID
        edit_username = findViewById(R.id.Recover_username);
        edit_password = findViewById(R.id.Recover_password);
        edit_confirm_password = findViewById(R.id.Recover_confirm_pasword);
        edit_button = findViewById(R.id.Recover_button);
        edit_backbutton = findViewById(R.id.Recover_backbutton);

        //Declare database
        login_database = new LoginDatabase(this);

        //Set Listener for the Backbutton
        edit_backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecoverActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //Set Listener for the complete button
        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Get String from the contents
                String username             = edit_username.getText().toString();
                String password             = edit_password.getText().toString();
                String confirm_password     = edit_confirm_password.getText().toString();

                //If fields are empty
                if(username.isEmpty() || password.isEmpty() || confirm_password.isEmpty()){
                    Toast.makeText(RecoverActivity.this, "Please enter all the required field", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean user_verify = login_database.check_username(username);
                    //Check if user exists
                    if(user_verify == false){
                        Toast.makeText(RecoverActivity.this, "User does not exists, please sign up", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        //check if password equals confirm passwords
                        if(password.equals(confirm_password)){
                            login_database.update_username_password(username, password);
                            Toast.makeText(RecoverActivity.this, "Recover password Completed", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(RecoverActivity.this,"Password does not match", Toast.LENGTH_SHORT).show();
                        }
                    }

                }

            }
        });

    }
}