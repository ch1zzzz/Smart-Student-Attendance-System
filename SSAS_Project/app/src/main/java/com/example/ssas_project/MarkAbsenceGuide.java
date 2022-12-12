package com.example.ssas_project;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ssas_project.database.DAO;
import com.example.ssas_project.database.MyDAO;
import com.example.ssas_project.entity.Course;
import com.example.ssas_project.entity.CourseOffering;
import com.example.ssas_project.entity.Student;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;

public class MarkAbsenceGuide extends AppCompatActivity{
    private ListView date_list;
    private DAO myDAO;
    private EditText year_text, month_text, day_text, hour_text, minute_text;
    private Button back_button1, choose_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_select_mark);

        date_list = findViewById(R.id.mark_date_list);
        back_button1 = findViewById(R.id.back_button129);
        year_text = findViewById(R.id.year);
        month_text = findViewById(R.id.month);
        day_text = findViewById(R.id.day);
        hour_text = findViewById(R.id.hour);
        minute_text = findViewById(R.id.minute);
        choose_button = findViewById(R.id.choose_button);


        myDAO = new MyDAO(this);

        Intent pre_intent = getIntent();
        if(pre_intent.getExtras() == null){

        }
        else{
            Bundle bundle = getIntent().getExtras();
            String offering_id = bundle.getString("offer_id");
            String course_id1 = bundle.getString("course_id");

            List date = myDAO.getTime(Integer.parseInt(offering_id));

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, date);
            date_list.setAdapter(adapter);

            choose_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String year = year_text.getText().toString();
                    String month = month_text.getText().toString();
                    String day = day_text.getText().toString();
                    String hour = hour_text.getText().toString();
                    String minute = minute_text.getText().toString();

//                    Calendar calendar = Calendar.getInstance();
//                    calendar.set(Integer.parseInt(year),Integer.parseInt(month) - 1,Integer.parseInt(day), Integer.parseInt(hour), Integer.parseInt(minute));
//                    Date date = calendar.getTime();
                    List<Date> offering_date = myDAO.getTime(Integer.parseInt(offering_id));

                    //Check if the date enter is correct
                    boolean status = false;
                    for(int i = 0; i < offering_date.size(); i ++){
                        Calendar calendar1 = Calendar.getInstance();
                        calendar1.setTime(offering_date.get(i));
                        if(calendar1.get(Calendar.YEAR) == Integer.parseInt(year) && calendar1.get(Calendar.MONTH) == (Integer.parseInt(month) - 1) && calendar1.get(Calendar.DAY_OF_MONTH) == Integer.parseInt(day) && calendar1.get(Calendar.HOUR_OF_DAY) == Integer.parseInt(hour) && calendar1.get(Calendar.MINUTE) == Integer.parseInt(minute)){
                            status = true;
                        }
                    }

                    if(year.isEmpty() || month.isEmpty() || day.isEmpty() || hour.isEmpty() || minute.isEmpty()){
                        Toast.makeText(MarkAbsenceGuide.this, "Please enter all the required field!", Toast.LENGTH_SHORT).show();
                    }
                    else if(status == false){
                        Toast.makeText(MarkAbsenceGuide.this, "Enter date does not exist", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Intent intent = new Intent(MarkAbsenceGuide.this, MarkAbsence.class);
                        intent.putExtra("course_id", course_id1);
                        intent.putExtra("offer_id", offering_id);
                        intent.putExtra("year", Integer.parseInt(year));
                        intent.putExtra("month", Integer.parseInt(month) - 1);
                        intent.putExtra("dayOfMonth", Integer.parseInt(day));
                        intent.putExtra("hourOfDay", Integer.parseInt(hour));
                        intent.putExtra("minute", Integer.parseInt(minute));
                        startActivity(intent);
                    }
                }
            });

            back_button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MarkAbsenceGuide.this, Class_Info_Activity.class);
                    intent.putExtra("course_id", course_id1);
                    intent.putExtra("offer_id", offering_id);
                    startActivity(intent);
                }
            });

//            date_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
////                    String year = String.valueOf(date.get(i).getId())
////                    Intent intent = new Intent(getApplicationContext(), MarkAbsenceGuide.class);
////                    intent.putExtra("offer_id", offering_id);
////                    intent.putExtra("course_id", course_id1);
////                    intent.putExtra("year",);
////                    intent.putExtra("month", );
////                    intent.putExtra("dayOfMonth",);
////                    intent.putExtra("hourOfDay",);
////                    intent.putExtra("minute",);
//                }
//            });
            }
        }
    }
