package com.example.ssas_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import android.os.Bundle;
import com.example.ssas_project.database.DAO;
import com.example.ssas_project.database.MyDAO;
import com.example.ssas_project.entity.Course;
import com.example.ssas_project.entity.CourseOffering;
import com.example.ssas_project.entity.Student;
import com.example.ssas_project.entity.Types;

import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.view.View;
import android.content.Intent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class DateRange extends AppCompatActivity {

    private TextView start_text, end_text, time_text;
    private Button insert_button, start_date, end_date, time_button, back_button;
    private CheckBox mon_check, tue_check, wed_check, th_check, fri_check, sat_check, sun_check;
    private DAO myDAO;

    public int start_year, start_month, start_day;
    public int end_year, end_month, end_day;
    public int hour_var, minute_var;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_range);

        myDAO = new MyDAO(this);

        //Initialize Variable with their IDs
        start_date = findViewById(R.id.daterange_start);
        end_date = findViewById(R.id.daterange_end);
        time_button = findViewById(R.id.daterange_time);

        start_text = findViewById(R.id.daterange_start_text);
        end_text = findViewById(R.id.daterange_end_text);
        time_text = findViewById(R.id.daterange_time_text);

        //Date CheckBox
        mon_check = findViewById(R.id.daterange_mon);
        tue_check = findViewById(R.id.daterange_tue);
        wed_check = findViewById(R.id.daterange_wed);
        th_check  = findViewById(R.id.daterange_th);
        fri_check = findViewById(R.id.daterange_fri);
        sat_check = findViewById(R.id.daterange_sat);
        sun_check = findViewById(R.id.daterange_sun);
        back_button = findViewById(R.id.back_button128);


        insert_button = findViewById(R.id.daterange_insertDate);

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        //Retrieve ID from the last activity
        Intent pre_intent = getIntent();
        if(pre_intent.getExtras() == null){
        }
        else{
            //Retrieve value from last activity
            Bundle bundle = getIntent().getExtras();
            String offer_id = bundle.getString("offer_id");
            String course_id = bundle.getString("course_id");

            //Start Date Button Select
            start_date.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatePickerDialog dialog = new DatePickerDialog(DateRange.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            start_year = year;
                            start_month = month;
                            start_day = dayOfMonth;
                            month = month+1;
                            String date = dayOfMonth+"/"+month+"/"+year;
                            start_text.setText(date);
                        }
                    },year, month,day);
                    dialog.show();
                }
            });

            //End Date Button Select
            end_date.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatePickerDialog dialog = new DatePickerDialog(DateRange.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            end_year = year;
                            end_month = month;
                            end_day = dayOfMonth;
                            month = month+1;
                            String date = dayOfMonth+"/"+month+"/"+year;
                            end_text.setText(date);
                        }
                    },year, month,day);
                    dialog.show();
                }
            });

            //Time Date Button Select
            time_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    TimePickerDialog timePickerDialog = new TimePickerDialog(DateRange.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            hour_var = hourOfDay;
                            minute_var = minute;
                            //Calendar calendar1 = Calendar.getInstance();
                            //calendar1.set(0,0,0, hourOfDay, minute);

                            //SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa");
                            String time = ""+ hourOfDay +":"+minute;
                            time_text.setText(String.format("Time: %s", time));
                        }
                    },12,0,false);
                    timePickerDialog.show();

                }
            });

            //Check Box Handler
            ArrayList<Integer> daysofweek = new ArrayList<>();

            //Monday Checkbox
            mon_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(buttonView.isChecked()){
                        daysofweek.add(2);
                    }
                }
            });

            //Tuesday Checkbox
            tue_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(buttonView.isChecked()){
                        daysofweek.add(3);
                    }
                }
            });

            //Wednesday Checkbox
            wed_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(buttonView.isChecked()){
                        daysofweek.add(4);
                    }
                }
            });

            //Thursday Checkbox
            th_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(buttonView.isChecked()){
                        daysofweek.add(5);
                    }
                }
            });

            //Friday Checkbox

            fri_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(buttonView.isChecked()){
                        daysofweek.add(6);
                    }
                }
            });

            //Saturday Checkbox

            sat_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(buttonView.isChecked()){
                        daysofweek.add(7);
                    }
                }
            });
            //Sunday Checkbox

            sun_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(buttonView.isChecked()){
                        daysofweek.add(1);
                    }
                }
            });

            //Insert Event Button
            insert_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Calendar start = Calendar.getInstance();
                    start.set(start_year, start_month, start_day, hour_var, minute_var , 0);

                    Calendar end = Calendar.getInstance();
                    end.set(end_year, end_month, end_day, hour_var, minute_var , 0);

                    if(daysofweek.isEmpty() || start_text.getText().equals("Date") || end_text.getText().equals("Date") || time_text.getText().equals("Time")){
                        Toast.makeText(DateRange.this, "Please fill in the Date", Toast.LENGTH_SHORT).show();
                    }
                    else if(start.getTime().compareTo(end.getTime()) > 0){
                        Toast.makeText(DateRange.this, "Start Date should be before End Date", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
                            for(int i = 0; i < daysofweek.size(); i++){
                                if(start.get(Calendar.DAY_OF_WEEK) == daysofweek.get(i)){
                                    myDAO.insertTime(Integer.parseInt(offer_id), date);
                                }
                            }
                        }
                        Toast.makeText(DateRange.this, "Events created", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DateRange.this, Class_Info_Activity.class);
                        intent.putExtra("offer_id", offer_id);
                        intent.putExtra("course_id", course_id);
                        startActivity(intent);
                    }

                }
            });

            back_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(DateRange.this, Class_Info_Activity.class);
                    intent.putExtra("offer_id", offer_id);
                    intent.putExtra("course_id",course_id);
                    startActivity(intent);
                }
            });
        }
    }
}